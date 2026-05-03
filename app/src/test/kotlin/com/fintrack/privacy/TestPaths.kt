package com.fintrack.privacy

import java.io.File

/**
 * Tests that read source files have to handle two possible working directories:
 * Gradle 8.x runs JVM unit tests with the working directory set to the module
 * (`app/`), but some IDE runners launch from the project root. Resolve relative
 * paths against both candidates and return whichever exists.
 */
internal object TestPaths {

    fun appFile(relativeToAppModule: String): File {
        val candidates = listOf(
            File(relativeToAppModule),
            File("app/$relativeToAppModule"),
            File("../app/$relativeToAppModule"),
        )
        return candidates.firstOrNull { it.exists() }
            ?: error(
                "Could not locate '$relativeToAppModule' from working directory " +
                    "${File(".").absolutePath}. Tried: ${candidates.map { it.absolutePath }}",
            )
    }

    fun rootFile(relativeToProjectRoot: String): File {
        val candidates = listOf(
            File("../$relativeToProjectRoot"),
            File(relativeToProjectRoot),
            File("./$relativeToProjectRoot"),
        )
        return candidates.firstOrNull { it.exists() }
            ?: error(
                "Could not locate '$relativeToProjectRoot' from working directory " +
                    "${File(".").absolutePath}. Tried: ${candidates.map { it.absolutePath }}",
            )
    }
}
