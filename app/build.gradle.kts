import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt)
}

// Optional release signing: drop a keystore.properties file next to this
// build script with the four entries below. When the file exists, release
// builds are signed automatically; when it doesn't, the release variant is
// produced unsigned. Debug builds always use the auto-generated debug keystore.
//
//   storeFile=/abs/path/to/fintrack-release.jks
//   storePassword=...
//   keyAlias=fintrack
//   keyPassword=...
//
// keystore.properties and *.jks are .gitignore'd at the repo root.
val keystorePropsFile = rootProject.file("keystore.properties")
val keystoreProps = Properties().apply {
    if (keystorePropsFile.exists()) keystorePropsFile.inputStream().use(::load)
}
val releaseSigningEnabled = keystoreProps.getProperty("storeFile")
    ?.let { rootProject.file(it).exists() } == true

android {
    namespace = "com.fintrack"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.fintrack"
        minSdk = 26
        targetSdk = 35
        versionCode = 6
        versionName = "3.2.1"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables { useSupportLibrary = true }
    }

    sourceSets {
        named("main")        { kotlin.srcDirs("src/main/kotlin") }
        named("test")        { kotlin.srcDirs("src/test/kotlin") }
        named("androidTest") { kotlin.srcDirs("src/androidTest/kotlin") }
    }

    if (releaseSigningEnabled) {
        signingConfigs {
            create("release") {
                storeFile = rootProject.file(keystoreProps.getProperty("storeFile"))
                storePassword = keystoreProps.getProperty("storePassword")
                keyAlias = keystoreProps.getProperty("keyAlias")
                keyPassword = keystoreProps.getProperty("keyPassword")
            }
        }
    }

    // Stable debug keystore committed to the repo so every CI runner signs
    // with the same key. Without this, GitHub Actions' ephemeral ~/.android
    // generates a fresh key per run and Android refuses to upgrade across
    // builds ("package conflicts with an existing package"). The key inside
    // app/keystore/debug.keystore is the standard Android debug identity —
    // public by design, not a release/Play Store signing key.
    signingConfigs {
        create("debug") {
            storeFile = rootProject.file("app/keystore/debug.keystore")
            storePassword = "android"
            keyAlias = "androiddebugkey"
            keyPassword = "android"
        }
    }

    buildTypes {
        debug {
            isMinifyEnabled = false
            applicationIdSuffix = ".debug"
            isDebuggable = true
            signingConfig = signingConfigs.getByName("debug")
        }
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
            if (releaseSigningEnabled) {
                signingConfig = signingConfigs.getByName("release")
            }
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
        isCoreLibraryDesugaringEnabled = false
    }

    kotlinOptions {
        jvmTarget = "17"
        freeCompilerArgs += listOf(
            "-opt-in=kotlin.RequiresOptIn",
            "-opt-in=androidx.compose.material3.ExperimentalMaterial3Api",
        )
    }

    buildFeatures {
        compose = true
        buildConfig = true
    }

    packaging {
        resources {
            excludes += setOf(
                "/META-INF/{AL2.0,LGPL2.1}",
                "/META-INF/LICENSE.md",
                "/META-INF/LICENSE-notice.md",
                "/META-INF/INDEX.LIST",
                "/META-INF/io.netty.versions.properties",
                // Apache POI / XmlBeans bring license + notice files that
                // collide across artifacts; drop everything but the actual
                // schema metadata.
                "/META-INF/DEPENDENCIES",
                "/META-INF/LICENSE",
                "/META-INF/LICENSE.txt",
                "/META-INF/NOTICE",
                "/META-INF/NOTICE.txt",
                "/META-INF/versions/9/OSGI-INF/MANIFEST.MF",
            )
            pickFirsts += setOf(
                "META-INF/services/org.apache.xmlbeans.impl.regex.FactoryProvider",
            )
        }
        // SQLCipher native libs are required at runtime; don't strip them.
        jniLibs.useLegacyPackaging = false
    }

    testOptions {
        unitTests {
            isIncludeAndroidResources = true
            isReturnDefaultValues = true
        }
    }

    lint {
        warningsAsErrors = true
        abortOnError = true
        checkReleaseBuilds = true
        // baseline = file("lint-baseline.xml")  // re-enable once Phase 6 stabilises lint output
    }
}

ksp {
    arg("room.schemaLocation", "$projectDir/schemas")
    arg("room.incremental", "true")
    arg("room.generateKotlin", "true")
}

dependencies {
    // Core / lifecycle
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.lifecycle.runtime.compose)
    implementation(libs.androidx.lifecycle.process)
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.androidx.navigation.compose)

    // Compose (BOM-managed)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.material.icons)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)

    // DI
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)
    implementation(libs.androidx.hilt.navigation.compose)

    // Persistence
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)
    ksp(libs.androidx.room.compiler)
    implementation(libs.androidx.sqlite.ktx)
    implementation(libs.sqlcipher.android)

    // Security
    implementation(libs.androidx.biometric.ktx)
    implementation(libs.androidx.security.crypto)

    // Charts (Phase 4 will use them; depend now so the dependency-graph tests are stable)
    implementation(libs.vico.compose.m3)
    implementation(libs.vico.compose)
    implementation(libs.vico.core)

    // Dates / serialization
    implementation(libs.kotlinx.datetime)
    implementation(libs.kotlinx.serialization.json)

    // XLSX (Apache POI). poi-ooxml-lite carries the smaller schema set;
    // we don't use charts / streaming, so the lite jar is enough.
    implementation(libs.poi.ooxml)
    implementation(libs.poi.ooxml.lite)

    // Unit test
    testImplementation(libs.junit.jupiter)
    testRuntimeOnly(libs.junit.jupiter.engine)
    testRuntimeOnly(libs.junit.platform.launcher)
    testImplementation(libs.turbine)
    testImplementation(libs.mockk)
    testImplementation(libs.robolectric)
    testImplementation(libs.truth)
    testImplementation(libs.androidx.room.testing)
    testImplementation(libs.sqlite.jdbc)
    testImplementation(libs.kotlin.reflect)

    // Instrumented test (Phase 2+)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.androidx.test.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    androidTestImplementation(libs.mockk.android)
}

tasks.withType<Test>().configureEach {
    useJUnitPlatform()
    testLogging {
        events("passed", "skipped", "failed")
        showStandardStreams = false
    }
    systemProperty("robolectric.logging.enabled", "false")
}
