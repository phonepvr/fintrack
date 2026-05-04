package com.fintrack.privacy

import com.fintrack.data.db.dao.GoalDao
import com.fintrack.data.db.dao.HoldingValueDao
import com.fintrack.data.db.dao.LoanDao
import com.fintrack.data.db.dao.LoanValueDao
import com.fintrack.data.db.dao.MilestoneDao
import com.fintrack.data.db.dao.SnapshotDao
import com.fintrack.data.db.dao.StreakStateDao
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
 * Privacy gate from spec §1.8 / §9: every read method on a per-user DAO
 * must declare a `userId` parameter so per-user scoping is enforced at the
 * DAO boundary. The build fails if a new method skips it.
 *
 * v3 expanded the per-user DAO surface — the reflection check now covers
 * SnapshotDao, HoldingValueDao, LoanDao, LoanValueDao, MilestoneDao,
 * GoalDao, and StreakStateDao.
 *
 * "Read method" = anything returning rows: a Flow<*>, a List<*>, an entity
 * type, or its nullable.
 */
class SnapshotDaoUserScopedTest {

    @Test
    @DisplayName("SnapshotDao read methods all take a userId parameter")
    fun snapshotDaoIsUserScoped() = assertAllReadMethodsTakeUserId(SnapshotDao::class)

    @Test
    @DisplayName("HoldingValueDao read methods all take a userId parameter")
    fun holdingValueDaoIsUserScoped() = assertAllReadMethodsTakeUserId(HoldingValueDao::class)

    @Test
    @DisplayName("LoanDao read methods all take a userId parameter")
    fun loanDaoIsUserScoped() = assertAllReadMethodsTakeUserId(LoanDao::class)

    @Test
    @DisplayName("LoanValueDao read methods all take a userId parameter")
    fun loanValueDaoIsUserScoped() = assertAllReadMethodsTakeUserId(LoanValueDao::class)

    @Test
    @DisplayName("MilestoneDao read methods all take a userId parameter")
    fun milestoneDaoIsUserScoped() = assertAllReadMethodsTakeUserId(MilestoneDao::class)

    @Test
    @DisplayName("GoalDao read methods all take a userId parameter")
    fun goalDaoIsUserScoped() = assertAllReadMethodsTakeUserId(GoalDao::class)

    @Test
    @DisplayName("StreakStateDao read methods all take a userId parameter")
    fun streakStateDaoIsUserScoped() = assertAllReadMethodsTakeUserId(StreakStateDao::class)

    private fun assertAllReadMethodsTakeUserId(clazz: KClass<*>) {
        val violations = mutableListOf<String>()
        for (function in clazz.functions) {
            val method: Method = function.javaMethod ?: continue
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
        is KClass<*> -> classifier.qualifiedName?.startsWith("com.fintrack.data.db.entities.") == true
        else -> false
    }
}
