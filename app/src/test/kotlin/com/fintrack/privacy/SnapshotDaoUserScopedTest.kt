package com.fintrack.privacy

import com.fintrack.data.db.dao.HoldingValueDao
import com.fintrack.data.db.dao.SnapshotDao
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.flow.Flow
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import java.lang.reflect.Method
import java.util.UUID
import kotlin.reflect.KClass
import kotlin.reflect.full.functions
import kotlin.reflect.full.valueParameters
import kotlin.reflect.jvm.javaMethod

/**
 * Privacy gate #3 from spec §6:
 *   Every read method on [SnapshotDao] and [HoldingValueDao] must declare a
 *   `userId` parameter so per-user scoping is enforced at the DAO boundary.
 *   The build fails if a new method skips it.
 *
 * "Read method" = anything returning rows, i.e. a Flow<*>, a List<*>, the
 * entity type itself, or its nullable. Pure scalar aggregates that never
 * leak per-user content (Int counts, etc.) are exempted ONLY when explicitly
 * named as such — currently no such exemption exists.
 *
 * Write methods (`@Insert`, `@Update`, raw `@Query` UPDATEs, returning Unit)
 * are exempt because they pass entity payloads which already carry the
 * snapshot/user binding.
 */
class SnapshotDaoUserScopedTest {

    @Test
    @DisplayName("SnapshotDao read methods all take a userId parameter")
    fun snapshotDaoIsUserScoped() {
        assertAllReadMethodsTakeUserId(SnapshotDao::class)
    }

    @Test
    @DisplayName("HoldingValueDao read methods all take a userId parameter")
    fun holdingValueDaoIsUserScoped() {
        assertAllReadMethodsTakeUserId(HoldingValueDao::class)
    }

    private fun assertAllReadMethodsTakeUserId(clazz: KClass<*>) {
        val violations = mutableListOf<String>()
        for (function in clazz.functions) {
            val method: Method = function.javaMethod ?: continue
            // Skip kotlin synthetic / Object methods (toString, equals, hashCode).
            if (method.declaringClass != clazz.java) continue

            if (!returnsRows(function.returnType.classifier)) continue

            val hasUserId = function.valueParameters.any { param ->
                param.name == "userId" && (param.type.classifier == UUID::class)
            }
            if (!hasUserId) {
                violations += "${clazz.simpleName}.${function.name}(...)"
            }
        }
        assertThat(violations).isEmpty()
    }

    private fun returnsRows(classifier: Any?): Boolean = when (classifier) {
        Flow::class, List::class -> true
        // Returning a single entity (or its nullable) also leaks rows.
        // The reflection check sees the raw classifier; suspend single-entity
        // returns are concrete entity classes.
        is KClass<*> -> classifier.qualifiedName?.startsWith("com.fintrack.data.db.entities.") == true
        else -> false
    }
}
