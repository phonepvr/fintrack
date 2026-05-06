# Build summary (run 25427150362, sha 3bfedf34dc1c332b34f767112f188d27c82e8a72)

| Step | Outcome |
|---|---|
| compile | success |
| assemble | success |
| tests | failure |
| detekt | success |
| locate APK | success |

## compile.log — error/warning lines

```
3153:w: file:///home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/di/DatabaseModule.kt:92:17 Type argument for reified type parameter 'T' was inferred to the intersection of ['Comparable<*>' & 'Serializable']. Reification of an intersection type results in the common supertype being used. This may lead to subtle issues and an explicit type argument is encouraged. This will become an error in a future release.
3154:w: file:///home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/di/DatabaseModule.kt:101:17 Type argument for reified type parameter 'T' was inferred to the intersection of ['Comparable<*>' & 'Serializable']. Reification of an intersection type results in the common supertype being used. This may lead to subtle issues and an explicit type argument is encouraged. This will become an error in a future release.
3155:w: file:///home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/di/DatabaseModule.kt:113:17 Type argument for reified type parameter 'T' was inferred to the intersection of ['Comparable<*>' & 'Serializable']. Reification of an intersection type results in the common supertype being used. This may lead to subtle issues and an explicit type argument is encouraged. This will become an error in a future release.
3156:w: file:///home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/security/MoneyTextField.kt:42:27 'constructor(capitalization: KeyboardCapitalization = ..., autoCorrect: Boolean, keyboardType: KeyboardType = ..., imeAction: ImeAction = ..., platformImeOptions: PlatformImeOptions? = ..., showKeyboardOnFocus: Boolean? = ..., hintLocales: LocaleList? = ...): KeyboardOptions' is deprecated. Please use the new constructor that takes optional autoCorrectEnabled parameter.
3157:w: file:///home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/security/PrivacyOverlay.kt:32:26 'val LocalLifecycleOwner: ProvidableCompositionLocal<LifecycleOwner>' is deprecated. Moved to lifecycle-runtime-compose library in androidx.lifecycle.compose package.
3158:w: file:///home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/metrics/MetricInfo.kt:130:46 'val Icons.Outlined.HelpOutline: ImageVector' is deprecated. Use the AutoMirrored version at Icons.AutoMirrored.Outlined.HelpOutline.
3159:w: file:///home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/onboarding/OnboardingPagerScreen.kt:102:29 'val Icons.Filled.TrendingUp: ImageVector' is deprecated. Use the AutoMirrored version at Icons.AutoMirrored.Filled.TrendingUp.
```

## compile.log — full log (3173 lines)

```
[truncated to first 200 + last 400 lines]
Initialized native services in: /home/runner/.gradle/native
Initialized jansi services in: /home/runner/.gradle/native
To honour the JVM settings for this build a single-use Daemon process will be forked. For more on this, please refer to https://docs.gradle.org/8.11.1/userguide/gradle_daemon.html#sec:disabling_the_daemon in the Gradle documentation.
Starting process 'Gradle build daemon'. Working directory: /home/runner/.gradle/daemon/8.11.1 Command: /usr/lib/jvm/temurin-17-jdk-amd64/bin/java --add-opens=java.base/java.lang=ALL-UNNAMED --add-opens=java.base/java.lang.invoke=ALL-UNNAMED --add-opens=java.base/java.util=ALL-UNNAMED --add-opens=java.prefs/java.util.prefs=ALL-UNNAMED --add-exports=jdk.compiler/com.sun.tools.javac.api=ALL-UNNAMED --add-exports=jdk.compiler/com.sun.tools.javac.util=ALL-UNNAMED --add-opens=java.base/java.util=ALL-UNNAMED --add-opens=java.prefs/java.util.prefs=ALL-UNNAMED --add-opens=java.base/java.nio.charset=ALL-UNNAMED --add-opens=java.base/java.net=ALL-UNNAMED --add-opens=java.base/java.util.concurrent.atomic=ALL-UNNAMED --add-opens=java.xml/javax.xml.namespace=ALL-UNNAMED -XX:+UseParallelGC -Xmx4g -Dfile.encoding=UTF-8 -Duser.country -Duser.language=en -Duser.variant -cp /home/runner/.gradle/wrapper/dists/gradle-8.11.1-bin/bpt9gzteqjrbo1mjrsomdt32c/gradle-8.11.1/lib/gradle-daemon-main-8.11.1.jar -javaagent:/home/runner/.gradle/wrapper/dists/gradle-8.11.1-bin/bpt9gzteqjrbo1mjrsomdt32c/gradle-8.11.1/lib/agents/gradle-instrumentation-agent-8.11.1.jar org.gradle.launcher.daemon.bootstrap.GradleDaemon 8.11.1
Successfully started process 'Gradle build daemon'
An attempt to start the daemon took 1.177 secs.
The client will now receive all logging from the daemon (pid: 2445). The daemon log file: /home/runner/.gradle/daemon/8.11.1/daemon-2445.out.log
Daemon will be stopped at the end of the build 
Using 4 worker leases.
Received JVM installation metadata from '/usr/lib/jvm/temurin-17-jdk-amd64': {JAVA_HOME=/usr/lib/jvm/temurin-17-jdk-amd64, JAVA_VERSION=17.0.18, JAVA_VENDOR=Eclipse Adoptium, RUNTIME_NAME=OpenJDK Runtime Environment, RUNTIME_VERSION=17.0.18+8, VM_NAME=OpenJDK 64-Bit Server VM, VM_VERSION=17.0.18+8, VM_VENDOR=Eclipse Adoptium, OS_ARCH=amd64}
Watching the file system is configured to be enabled if available
Now considering [/home/runner/work/fintrack/fintrack] as hierarchies to watch
File system watching is active
Starting Build
Settings evaluated using settings file '/home/runner/work/fintrack/fintrack/settings.gradle.kts'.
Using local directory build cache for the root build (location = /home/runner/.gradle/caches/build-cache-1, remove unused entries = after 7 days).
Projects loaded. Root project using build file '/home/runner/work/fintrack/fintrack/build.gradle.kts'.
Included projects: [root project 'fintrack', project ':app']

> Configure project :
Evaluating root project 'fintrack' using build file '/home/runner/work/fintrack/fintrack/build.gradle.kts'.
Resource missing. [HTTP HEAD: https://dl.google.com/dl/android/maven2/com/android/application/com.android.application.gradle.plugin/8.7.3/com.android.application.gradle.plugin-8.7.3.jar]
Resource missing. [HTTP HEAD: https://repo.maven.apache.org/maven2/org/jetbrains/kotlin/android/org.jetbrains.kotlin.android.gradle.plugin/2.1.20/org.jetbrains.kotlin.android.gradle.plugin-2.1.20.jar]
Resource missing. [HTTP HEAD: https://repo.maven.apache.org/maven2/org/jetbrains/kotlin/plugin/compose/org.jetbrains.kotlin.plugin.compose.gradle.plugin/2.1.20/org.jetbrains.kotlin.plugin.compose.gradle.plugin-2.1.20.jar]
Resource missing. [HTTP HEAD: https://repo.maven.apache.org/maven2/org/jetbrains/kotlin/plugin/serialization/org.jetbrains.kotlin.plugin.serialization.gradle.plugin/2.1.20/org.jetbrains.kotlin.plugin.serialization.gradle.plugin-2.1.20.jar]
Resource missing. [HTTP HEAD: https://repo.maven.apache.org/maven2/com/google/devtools/ksp/com.google.devtools.ksp.gradle.plugin/2.1.20-1.0.32/com.google.devtools.ksp.gradle.plugin-2.1.20-1.0.32.jar]
Resource missing. [HTTP HEAD: https://repo.maven.apache.org/maven2/com/google/dagger/hilt/android/com.google.dagger.hilt.android.gradle.plugin/2.54/com.google.dagger.hilt.android.gradle.plugin-2.54.jar]
Resource missing. [HTTP HEAD: https://repo.maven.apache.org/maven2/io/gitlab/arturbosch/detekt/io.gitlab.arturbosch.detekt.gradle.plugin/1.23.7/io.gitlab.arturbosch.detekt.gradle.plugin-1.23.7.jar]
Transforming hilt-android-gradle-plugin-2.54.jar (com.google.dagger:hilt-android-gradle-plugin:2.54) with InstrumentationAnalysisTransform
Transforming gradle-8.7.3.jar (com.android.tools.build:gradle:8.7.3) with InstrumentationAnalysisTransform
Transforming gradle-settings-api-8.7.3.jar (com.android.tools.build:gradle-settings-api:8.7.3) with InstrumentationAnalysisTransform
Transforming lint-model-31.7.3.jar (com.android.tools.lint:lint-model:31.7.3) with InstrumentationAnalysisTransform
Transforming builder-8.7.3.jar (com.android.tools.build:builder:8.7.3) with InstrumentationAnalysisTransform
Transforming manifest-merger-31.7.3.jar (com.android.tools.build:manifest-merger:31.7.3) with InstrumentationAnalysisTransform
Transforming sdk-common-31.7.3.jar (com.android.tools:sdk-common:31.7.3) with InstrumentationAnalysisTransform
Transforming sdklib-31.7.3.jar (com.android.tools:sdklib:31.7.3) with InstrumentationAnalysisTransform
Transforming repository-31.7.3.jar (com.android.tools:repository:31.7.3) with InstrumentationAnalysisTransform
Transforming aaptcompiler-8.7.3.jar (com.android.tools.build:aaptcompiler:8.7.3) with InstrumentationAnalysisTransform
Transforming tracker-31.7.3.jar (com.android.tools.analytics-library:tracker:31.7.3) with InstrumentationAnalysisTransform
Transforming shared-31.7.3.jar (com.android.tools.analytics-library:shared:31.7.3) with InstrumentationAnalysisTransform
Transforming databinding-compiler-common-8.7.3.jar (androidx.databinding:databinding-compiler-common:8.7.3) with InstrumentationAnalysisTransform
Transforming android-test-plugin-host-emulator-control-proto-31.7.3.jar (com.android.tools.utp:android-test-plugin-host-emulator-control-proto:31.7.3) with InstrumentationAnalysisTransform
Transforming android-test-plugin-host-retention-proto-31.7.3.jar (com.android.tools.utp:android-test-plugin-host-retention-proto:31.7.3) with InstrumentationAnalysisTransform
Transforming builder-model-8.7.3.jar (com.android.tools.build:builder-model:8.7.3) with InstrumentationAnalysisTransform
Transforming gradle-api-8.7.3.jar (com.android.tools.build:gradle-api:8.7.3) with InstrumentationAnalysisTransform
Transforming builder-test-api-8.7.3.jar (com.android.tools.build:builder-test-api:8.7.3) with InstrumentationAnalysisTransform
Transforming ddmlib-31.7.3.jar (com.android.tools.ddms:ddmlib:31.7.3) with InstrumentationAnalysisTransform
Transforming layoutlib-api-31.7.3.jar (com.android.tools.layoutlib:layoutlib-api:31.7.3) with InstrumentationAnalysisTransform
Transforming dvlib-31.7.3.jar (com.android.tools:dvlib:31.7.3) with InstrumentationAnalysisTransform
Transforming common-31.7.3.jar (com.android.tools:common:31.7.3) with InstrumentationAnalysisTransform
Transforming kotlin-stdlib-jdk8-1.9.20.jar (org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.9.20) with InstrumentationAnalysisTransform
Transforming jetifier-processor-1.0.0-beta10.jar (com.android.tools.build.jetifier:jetifier-processor:1.0.0-beta10) with InstrumentationAnalysisTransform
Transforming jetifier-core-1.0.0-beta10.jar (com.android.tools.build.jetifier:jetifier-core:1.0.0-beta10) with InstrumentationAnalysisTransform
Transforming symbol-processing-gradle-plugin-2.1.20-1.0.32.jar (com.google.devtools.ksp:symbol-processing-gradle-plugin:2.1.20-1.0.32) with InstrumentationAnalysisTransform
Transforming symbol-processing-api-2.1.20-1.0.32.jar (com.google.devtools.ksp:symbol-processing-api:2.1.20-1.0.32) with InstrumentationAnalysisTransform
Transforming symbol-processing-common-deps-2.1.20-1.0.32.jar (com.google.devtools.ksp:symbol-processing-common-deps:2.1.20-1.0.32) with InstrumentationAnalysisTransform
Transforming kotlin-reflect-2.0.20.jar (org.jetbrains.kotlin:kotlin-reflect:2.0.20) with InstrumentationAnalysisTransform
Transforming kotlin-stdlib-jdk7-1.9.20.jar (org.jetbrains.kotlin:kotlin-stdlib-jdk7:1.9.20) with InstrumentationAnalysisTransform
Transforming kotlin-stdlib-2.0.20.jar (org.jetbrains.kotlin:kotlin-stdlib:2.0.20) with InstrumentationAnalysisTransform
Transforming annotations-13.0.jar (org.jetbrains:annotations:13.0) with InstrumentationAnalysisTransform
Transforming kotlin-serialization-2.1.20-gradle85.jar (org.jetbrains.kotlin:kotlin-serialization:2.1.20) with InstrumentationAnalysisTransform
Transforming kotlin-gradle-plugin-model-2.1.20.jar (org.jetbrains.kotlin:kotlin-gradle-plugin-model:2.1.20) with InstrumentationAnalysisTransform
Transforming fus-statistics-gradle-plugin-2.1.20-gradle85.jar (org.jetbrains.kotlin:fus-statistics-gradle-plugin:2.1.20) with InstrumentationAnalysisTransform
Transforming kotlin-gradle-plugin-api-2.1.20.jar (org.jetbrains.kotlin:kotlin-gradle-plugin-api:2.1.20) with InstrumentationAnalysisTransform
Transforming kotlin-gradle-plugin-api-2.1.20-gradle85.jar (org.jetbrains.kotlin:kotlin-gradle-plugin-api:2.1.20) with InstrumentationAnalysisTransform
Transforming compose-compiler-gradle-plugin-2.1.20-gradle85.jar (org.jetbrains.kotlin:compose-compiler-gradle-plugin:2.1.20) with InstrumentationAnalysisTransform
Transforming kotlin-gradle-plugin-2.1.20-gradle85.jar (org.jetbrains.kotlin:kotlin-gradle-plugin:2.1.20) with InstrumentationAnalysisTransform
Transforming detekt-gradle-plugin-1.23.7.jar (io.gitlab.arturbosch.detekt:detekt-gradle-plugin:1.23.7) with InstrumentationAnalysisTransform
Transforming bundletool-1.17.1.jar (com.android.tools.build:bundletool:1.17.1) with InstrumentationAnalysisTransform
Transforming aapt2-proto-8.7.3-12006047.jar (com.android.tools.build:aapt2-proto:8.7.3-12006047) with InstrumentationAnalysisTransform
Transforming crash-31.7.3.jar (com.android.tools.analytics-library:crash:31.7.3) with InstrumentationAnalysisTransform
Transforming lint-typedef-remover-31.7.3.jar (com.android.tools.lint:lint-typedef-remover:31.7.3) with InstrumentationAnalysisTransform
Transforming databinding-common-8.7.3.jar (androidx.databinding:databinding-common:8.7.3) with InstrumentationAnalysisTransform
Transforming baseLibrary-8.7.3.jar (com.android.databinding:baseLibrary:8.7.3) with InstrumentationAnalysisTransform
Transforming android-device-provider-ddmlib-proto-31.7.3.jar (com.android.tools.utp:android-device-provider-ddmlib-proto:31.7.3) with InstrumentationAnalysisTransform
Transforming android-device-provider-gradle-proto-31.7.3.jar (com.android.tools.utp:android-device-provider-gradle-proto:31.7.3) with InstrumentationAnalysisTransform
Transforming android-device-provider-profile-proto-31.7.3.jar (com.android.tools.utp:android-device-provider-profile-proto:31.7.3) with InstrumentationAnalysisTransform
Transforming android-test-plugin-host-additional-test-output-proto-31.7.3.jar (com.android.tools.utp:android-test-plugin-host-additional-test-output-proto:31.7.3) with InstrumentationAnalysisTransform
Transforming android-test-plugin-host-coverage-proto-31.7.3.jar (com.android.tools.utp:android-test-plugin-host-coverage-proto:31.7.3) with InstrumentationAnalysisTransform
Transforming android-test-plugin-host-logcat-proto-31.7.3.jar (com.android.tools.utp:android-test-plugin-host-logcat-proto:31.7.3) with InstrumentationAnalysisTransform
Transforming android-test-plugin-host-apk-installer-proto-31.7.3.jar (com.android.tools.utp:android-test-plugin-host-apk-installer-proto:31.7.3) with InstrumentationAnalysisTransform
Transforming android-test-plugin-result-listener-gradle-proto-31.7.3.jar (com.android.tools.utp:android-test-plugin-result-listener-gradle-proto:31.7.3) with InstrumentationAnalysisTransform
Transforming transform-api-2.0.0-deprecated-use-gradle-api.jar (com.android.tools.build:transform-api:2.0.0-deprecated-use-gradle-api) with InstrumentationAnalysisTransform
Transforming httpmime-4.5.6.jar (org.apache.httpcomponents:httpmime:4.5.6) with InstrumentationAnalysisTransform
Transforming commons-io-2.13.0.jar (commons-io:commons-io:2.13.0) with InstrumentationAnalysisTransform
Transforming asm-commons-9.6.jar (org.ow2.asm:asm-commons:9.6) with InstrumentationAnalysisTransform
Transforming asm-util-9.6.jar (org.ow2.asm:asm-util:9.6) with InstrumentationAnalysisTransform
Transforming asm-analysis-9.6.jar (org.ow2.asm:asm-analysis:9.6) with InstrumentationAnalysisTransform
Transforming asm-tree-9.6.jar (org.ow2.asm:asm-tree:9.6) with InstrumentationAnalysisTransform
Transforming asm-9.6.jar (org.ow2.asm:asm:9.6) with InstrumentationAnalysisTransform
Transforming apkzlib-8.7.3.jar (com.android.tools.build:apkzlib:8.7.3) with InstrumentationAnalysisTransform
Transforming bcpkix-jdk18on-1.77.jar (org.bouncycastle:bcpkix-jdk18on:1.77) with InstrumentationAnalysisTransform
Transforming jaxb-runtime-2.3.2.jar (org.glassfish.jaxb:jaxb-runtime:2.3.2) with InstrumentationAnalysisTransform
Transforming jopt-simple-4.9.jar (net.sf.jopt-simple:jopt-simple:4.9) with InstrumentationAnalysisTransform
Transforming javapoet-1.13.0.jar (com.squareup:javapoet:1.13.0) with InstrumentationAnalysisTransform
Transforming protobuf-java-util-3.22.3.jar (com.google.protobuf:protobuf-java-util:3.22.3) with InstrumentationAnalysisTransform
Transforming grpc-protobuf-1.57.0.jar (io.grpc:grpc-protobuf:1.57.0) with InstrumentationAnalysisTransform
Transforming tink-1.7.0.jar (com.google.crypto.tink:tink:1.7.0) with InstrumentationAnalysisTransform
Transforming protos-31.7.3.jar (com.android.tools.analytics-library:protos:31.7.3) with InstrumentationAnalysisTransform
Transforming proto-google-common-protos-2.17.0.jar (com.google.api.grpc:proto-google-common-protos:2.17.0) with InstrumentationAnalysisTransform
Transforming protobuf-java-3.22.3.jar (com.google.protobuf:protobuf-java:3.22.3) with InstrumentationAnalysisTransform
Transforming grpc-netty-1.57.0.jar (io.grpc:grpc-netty:1.57.0) with InstrumentationAnalysisTransform
Transforming grpc-core-1.57.0.jar (io.grpc:grpc-core:1.57.0) with InstrumentationAnalysisTransform
Transforming kotlin-build-statistics-2.1.20.jar (org.jetbrains.kotlin:kotlin-build-statistics:2.1.20) with InstrumentationAnalysisTransform
Transforming gson-2.11.0.jar (com.google.code.gson:gson:2.11.0) with InstrumentationAnalysisTransform
Transforming grpc-stub-1.57.0.jar (io.grpc:grpc-stub:1.57.0) with InstrumentationAnalysisTransform
Transforming core-proto-0.0.9-alpha02.jar (com.google.testing.platform:core-proto:0.0.9-alpha02) with InstrumentationAnalysisTransform
Transforming tensorflow-lite-metadata-0.1.0-rc2.jar (org.tensorflow:tensorflow-lite-metadata:0.1.0-rc2) with InstrumentationAnalysisTransform
Transforming flatbuffers-java-1.12.0.jar (com.google.flatbuffers:flatbuffers-java:1.12.0) with InstrumentationAnalysisTransform
Transforming kotlin-gradle-plugin-idea-proto-2.1.20.jar (org.jetbrains.kotlin:kotlin-gradle-plugin-idea-proto:2.1.20) with InstrumentationAnalysisTransform
Transforming kotlin-gradle-plugin-idea-2.1.20.jar (org.jetbrains.kotlin:kotlin-gradle-plugin-idea:2.1.20) with InstrumentationAnalysisTransform
Transforming kotlin-klib-commonizer-api-2.1.20.jar (org.jetbrains.kotlin:kotlin-klib-commonizer-api:2.1.20) with InstrumentationAnalysisTransform
Transforming kotlin-util-klib-metadata-2.1.20.jar (org.jetbrains.kotlin:kotlin-util-klib-metadata:2.1.20) with InstrumentationAnalysisTransform
Transforming kotlin-compiler-runner-2.1.20.jar (org.jetbrains.kotlin:kotlin-compiler-runner:2.1.20) with InstrumentationAnalysisTransform
Transforming jimfs-1.1.jar (com.google.jimfs:jimfs:1.1) with InstrumentationAnalysisTransform
Transforming grpc-context-1.57.0.jar (io.grpc:grpc-context:1.57.0) with InstrumentationAnalysisTransform
Transforming grpc-protobuf-lite-1.57.0.jar (io.grpc:grpc-protobuf-lite:1.57.0) with InstrumentationAnalysisTransform
Transforming grpc-api-1.57.0.jar (io.grpc:grpc-api:1.57.0) with InstrumentationAnalysisTransform
Transforming guava-32.0.1-jre.jar (com.google.guava:guava:32.0.1-jre) with InstrumentationAnalysisTransform
Transforming dagger-2.28.3.jar (com.google.dagger:dagger:2.28.3) with InstrumentationAnalysisTransform
Transforming javax.inject-1.jar (javax.inject:javax.inject:1) with InstrumentationAnalysisTransform
Transforming kxml2-2.3.0.jar (net.sf.kxml:kxml2:2.3.0) with InstrumentationAnalysisTransform
Transforming bcutil-jdk18on-1.77.jar (org.bouncycastle:bcutil-jdk18on:1.77) with InstrumentationAnalysisTransform
Transforming bcprov-jdk18on-1.77.jar (org.bouncycastle:bcprov-jdk18on:1.77) with InstrumentationAnalysisTransform
Transforming trove4j-1.0.20200330.jar (org.jetbrains.intellij.deps:trove4j:1.0.20200330) with InstrumentationAnalysisTransform
Transforming commons-compress-1.21.jar (org.apache.commons:commons-compress:1.21) with InstrumentationAnalysisTransform
Transforming httpclient-4.5.14.jar (org.apache.httpcomponents:httpclient:4.5.14) with InstrumentationAnalysisTransform
Transforming httpcore-4.4.16.jar (org.apache.httpcomponents:httpcore:4.4.16) with InstrumentationAnalysisTransform
Transforming javax.activation-1.2.0.jar (com.sun.activation:javax.activation:1.2.0) with InstrumentationAnalysisTransform
Transforming signflinger-8.7.3.jar (com.android:signflinger:8.7.3) with InstrumentationAnalysisTransform
Transforming zipflinger-8.7.3.jar (com.android:zipflinger:8.7.3) with InstrumentationAnalysisTransform
Transforming annotations-31.7.3.jar (com.android.tools:annotations:31.7.3) with InstrumentationAnalysisTransform
Transforming jna-platform-5.6.0.jar (net.java.dev.jna:jna-platform:5.6.0) with InstrumentationAnalysisTransform
Transforming juniversalchardet-1.0.3.jar (com.googlecode.juniversalchardet:juniversalchardet:1.0.3) with InstrumentationAnalysisTransform
Transforming javax.annotation-api-1.3.2.jar (javax.annotation:javax.annotation-api:1.3.2) with InstrumentationAnalysisTransform
Transforming stax-ex-1.8.1.jar (org.jvnet.staxex:stax-ex:1.8.1) with InstrumentationAnalysisTransform
Transforming jakarta.xml.bind-api-2.3.2.jar (jakarta.xml.bind:jakarta.xml.bind-api:2.3.2) with InstrumentationAnalysisTransform
Transforming txw2-2.3.2.jar (org.glassfish.jaxb:txw2:2.3.2) with InstrumentationAnalysisTransform
Transforming istack-commons-runtime-3.0.8.jar (com.sun.istack:istack-commons-runtime:3.0.8) with InstrumentationAnalysisTransform
Transforming FastInfoset-1.2.16.jar (com.sun.xml.fastinfoset:FastInfoset:1.2.16) with InstrumentationAnalysisTransform
Transforming jakarta.activation-api-1.2.1.jar (jakarta.activation:jakarta.activation-api:1.2.1) with InstrumentationAnalysisTransform
Transforming auto-value-annotations-1.6.2.jar (com.google.auto.value:auto-value-annotations:1.6.2) with InstrumentationAnalysisTransform
Transforming error_prone_annotations-2.27.0.jar (com.google.errorprone:error_prone_annotations:2.27.0) with InstrumentationAnalysisTransform
Transforming jose4j-0.9.5.jar (org.bitbucket.b_c:jose4j:0.9.5) with InstrumentationAnalysisTransform
Transforming slf4j-api-1.7.30.jar (org.slf4j:slf4j-api:1.7.30) with InstrumentationAnalysisTransform
Transforming jdom2-2.0.6.jar (org.jdom:jdom2:2.0.6) with InstrumentationAnalysisTransform
Transforming jsr305-3.0.2.jar (com.google.code.findbugs:jsr305:3.0.2) with InstrumentationAnalysisTransform
Transforming j2objc-annotations-2.8.jar (com.google.j2objc:j2objc-annotations:2.8) with InstrumentationAnalysisTransform
Transforming annotations-4.1.1.4.jar (com.google.android:annotations:4.1.1.4) with InstrumentationAnalysisTransform
Transforming animal-sniffer-annotations-1.23.jar (org.codehaus.mojo:animal-sniffer-annotations:1.23) with InstrumentationAnalysisTransform
Transforming perfmark-api-0.26.0.jar (io.perfmark:perfmark-api:0.26.0) with InstrumentationAnalysisTransform
Transforming netty-codec-http2-4.1.93.Final.jar (io.netty:netty-codec-http2:4.1.93.Final) with InstrumentationAnalysisTransform
Transforming netty-handler-proxy-4.1.93.Final.jar (io.netty:netty-handler-proxy:4.1.93.Final) with InstrumentationAnalysisTransform
Transforming netty-codec-http-4.1.93.Final.jar (io.netty:netty-codec-http:4.1.93.Final) with InstrumentationAnalysisTransform
Transforming netty-handler-4.1.93.Final.jar (io.netty:netty-handler:4.1.93.Final) with InstrumentationAnalysisTransform
Transforming netty-transport-native-unix-common-4.1.93.Final.jar (io.netty:netty-transport-native-unix-common:4.1.93.Final) with InstrumentationAnalysisTransform
Transforming checker-qual-3.33.0.jar (org.checkerframework:checker-qual:3.33.0) with InstrumentationAnalysisTransform
Transforming commons-codec-1.11.jar (commons-codec:commons-codec:1.11) with InstrumentationAnalysisTransform
Transforming apksig-8.7.3.jar (com.android.tools.build:apksig:8.7.3) with InstrumentationAnalysisTransform
Transforming javawriter-2.5.0.jar (com.squareup:javawriter:2.5.0) with InstrumentationAnalysisTransform
Transforming kotlin-gradle-plugin-annotations-2.1.20.jar (org.jetbrains.kotlin:kotlin-gradle-plugin-annotations:2.1.20) with InstrumentationAnalysisTransform
Transforming kotlin-native-utils-2.1.20.jar (org.jetbrains.kotlin:kotlin-native-utils:2.1.20) with InstrumentationAnalysisTransform
Transforming kotlin-tooling-core-2.1.20.jar (org.jetbrains.kotlin:kotlin-tooling-core:2.1.20) with InstrumentationAnalysisTransform
Transforming kotlin-build-tools-api-2.1.20.jar (org.jetbrains.kotlin:kotlin-build-tools-api:2.1.20) with InstrumentationAnalysisTransform
Transforming kotlin-util-klib-2.1.20.jar (org.jetbrains.kotlin:kotlin-util-klib:2.1.20) with InstrumentationAnalysisTransform
Transforming kotlin-util-io-2.1.20.jar (org.jetbrains.kotlin:kotlin-util-io:2.1.20) with InstrumentationAnalysisTransform
Transforming kotlin-daemon-client-2.1.20.jar (org.jetbrains.kotlin:kotlin-daemon-client:2.1.20) with InstrumentationAnalysisTransform
Transforming kotlinx-coroutines-core-jvm-1.8.0.jar (org.jetbrains.kotlinx:kotlinx-coroutines-core-jvm:1.8.0) with InstrumentationAnalysisTransform
Transforming failureaccess-1.0.1.jar (com.google.guava:failureaccess:1.0.1) with InstrumentationAnalysisTransform
Transforming listenablefuture-9999.0-empty-to-avoid-conflict-with-guava.jar (com.google.guava:listenablefuture:9999.0-empty-to-avoid-conflict-with-guava) with InstrumentationAnalysisTransform
Transforming commons-logging-1.2.jar (commons-logging:commons-logging:1.2) with InstrumentationAnalysisTransform
Transforming jna-5.6.0.jar (net.java.dev.jna:jna:5.6.0) with InstrumentationAnalysisTransform
Transforming netty-codec-socks-4.1.93.Final.jar (io.netty:netty-codec-socks:4.1.93.Final) with InstrumentationAnalysisTransform
Transforming netty-codec-4.1.93.Final.jar (io.netty:netty-codec:4.1.93.Final) with InstrumentationAnalysisTransform
Transforming netty-transport-4.1.93.Final.jar (io.netty:netty-transport:4.1.93.Final) with InstrumentationAnalysisTransform
Transforming netty-buffer-4.1.93.Final.jar (io.netty:netty-buffer:4.1.93.Final) with InstrumentationAnalysisTransform
Transforming netty-resolver-4.1.93.Final.jar (io.netty:netty-resolver:4.1.93.Final) with InstrumentationAnalysisTransform
Transforming netty-common-4.1.93.Final.jar (io.netty:netty-common:4.1.93.Final) with InstrumentationAnalysisTransform
Transforming hilt-android-gradle-plugin-2.54.jar (com.google.dagger:hilt-android-gradle-plugin:2.54) with InstrumentationAnalysisTransform
Transforming hilt-android-gradle-plugin-2.54.jar (com.google.dagger:hilt-android-gradle-plugin:2.54) with MergeInstrumentationAnalysisTransform
Transforming gradle-8.7.3.jar (com.android.tools.build:gradle:8.7.3) with InstrumentationAnalysisTransform
Transforming gradle-8.7.3.jar (com.android.tools.build:gradle:8.7.3) with MergeInstrumentationAnalysisTransform
Transforming gradle-settings-api-8.7.3.jar (com.android.tools.build:gradle-settings-api:8.7.3) with InstrumentationAnalysisTransform
Transforming gradle-settings-api-8.7.3.jar (com.android.tools.build:gradle-settings-api:8.7.3) with MergeInstrumentationAnalysisTransform
Transforming lint-model-31.7.3.jar (com.android.tools.lint:lint-model:31.7.3) with InstrumentationAnalysisTransform
Transforming lint-model-31.7.3.jar (com.android.tools.lint:lint-model:31.7.3) with MergeInstrumentationAnalysisTransform
Transforming builder-8.7.3.jar (com.android.tools.build:builder:8.7.3) with InstrumentationAnalysisTransform
Transforming builder-8.7.3.jar (com.android.tools.build:builder:8.7.3) with MergeInstrumentationAnalysisTransform
Transforming manifest-merger-31.7.3.jar (com.android.tools.build:manifest-merger:31.7.3) with InstrumentationAnalysisTransform
Transforming manifest-merger-31.7.3.jar (com.android.tools.build:manifest-merger:31.7.3) with MergeInstrumentationAnalysisTransform
Transforming sdk-common-31.7.3.jar (com.android.tools:sdk-common:31.7.3) with InstrumentationAnalysisTransform
Transforming sdk-common-31.7.3.jar (com.android.tools:sdk-common:31.7.3) with MergeInstrumentationAnalysisTransform
Transforming sdklib-31.7.3.jar (com.android.tools:sdklib:31.7.3) with InstrumentationAnalysisTransform
Transforming sdklib-31.7.3.jar (com.android.tools:sdklib:31.7.3) with MergeInstrumentationAnalysisTransform
Transforming repository-31.7.3.jar (com.android.tools:repository:31.7.3) with InstrumentationAnalysisTransform
Transforming repository-31.7.3.jar (com.android.tools:repository:31.7.3) with MergeInstrumentationAnalysisTransform
Transforming aaptcompiler-8.7.3.jar (com.android.tools.build:aaptcompiler:8.7.3) with InstrumentationAnalysisTransform
Transforming aaptcompiler-8.7.3.jar (com.android.tools.build:aaptcompiler:8.7.3) with MergeInstrumentationAnalysisTransform
Transforming tracker-31.7.3.jar (com.android.tools.analytics-library:tracker:31.7.3) with InstrumentationAnalysisTransform
Transforming tracker-31.7.3.jar (com.android.tools.analytics-library:tracker:31.7.3) with MergeInstrumentationAnalysisTransform

... (2573 lines elided) ...

Transforming lifecycle-viewmodel-savedstate-2.8.7-api.jar with StructureTransformAction
Transforming lifecycle-runtime-compose-release-api.jar with StructureTransformAction
Transforming lifecycle-process-2.8.7-api.jar with StructureTransformAction
Transforming lifecycle-viewmodel-compose-release-api.jar with StructureTransformAction
Transforming material3-release-api.jar with StructureTransformAction
Transforming foundation-layout-release-api.jar with StructureTransformAction
Transforming material-ripple-release-api.jar with StructureTransformAction
Transforming foundation-release-api.jar with StructureTransformAction
Transforming animation-core-release-api.jar with StructureTransformAction
Transforming animation-release-api.jar with StructureTransformAction
Transforming ui-util-release-api.jar with StructureTransformAction
Transforming ui-unit-release-api.jar with StructureTransformAction
Transforming ui-text-release-api.jar with StructureTransformAction
Transforming ui-geometry-release-api.jar with StructureTransformAction
Transforming ui-tooling-data-release-api.jar with StructureTransformAction
Transforming ui-tooling-preview-release-api.jar with StructureTransformAction
Transforming ui-graphics-release-api.jar with StructureTransformAction
Transforming material-icons-extended-release-api.jar with StructureTransformAction
Transforming material-icons-core-release-api.jar with StructureTransformAction
Transforming ui-release-api.jar with StructureTransformAction
Transforming ui-tooling-release-api.jar with StructureTransformAction
Transforming ui-test-manifest-1.7.6-api.jar with StructureTransformAction
Transforming activity-1.9.3-api.jar with StructureTransformAction
Transforming activity-compose-1.9.3-api.jar with StructureTransformAction
Transforming activity-ktx-1.9.3-api.jar with StructureTransformAction
Transforming core-ktx-1.15.0-api.jar with StructureTransformAction
Transforming room-common-2.6.1.jar with StructureTransformAction
Transforming room-runtime-2.6.1-api.jar with StructureTransformAction
Transforming room-ktx-2.6.1-api.jar with StructureTransformAction
Transforming sqlite-framework-2.4.0-api.jar with StructureTransformAction
Transforming sqlite-2.4.0-api.jar with StructureTransformAction
Transforming sqlite-ktx-2.4.0-api.jar with StructureTransformAction
Transforming compose-m3-2.0.2-api.jar with StructureTransformAction
Transforming runtime-saveable-release-api.jar with StructureTransformAction
Transforming runtime-release-api.jar with StructureTransformAction
Transforming annotation-experimental-1.4.1-api.jar with StructureTransformAction
Transforming savedstate-ktx-1.2.1-api.jar with StructureTransformAction
Transforming savedstate-1.2.1-api.jar with StructureTransformAction
Transforming kotlinx-coroutines-core-jvm-1.10.1.jar with StructureTransformAction
Transforming kotlinx-coroutines-android-1.10.1.jar with StructureTransformAction
Transforming kotlinx-datetime-jvm-0.6.1.jar with StructureTransformAction
Transforming kotlinx-serialization-core-jvm-1.7.3.jar with StructureTransformAction
Transforming kotlinx-serialization-json-jvm-1.7.3.jar with StructureTransformAction
Transforming security-crypto-1.1.0-alpha06-api.jar with StructureTransformAction
Transforming versionedparcelable-1.1.1-api.jar with StructureTransformAction
Transforming core-runtime-2.2.0-api.jar with StructureTransformAction
Transforming core-common-2.2.0.jar with StructureTransformAction
Transforming collection-jvm-1.4.4.jar with StructureTransformAction
Transforming annotation-jvm-1.9.1.jar with StructureTransformAction
Transforming kotlin-stdlib-jdk8-1.8.22.jar with StructureTransformAction
Transforming kotlin-stdlib-jdk7-1.8.22.jar with StructureTransformAction
Transforming kotlin-stdlib-2.1.20.jar with StructureTransformAction
Transforming sqlcipher-android-4.6.1-api.jar with StructureTransformAction
Transforming compose-2.0.2-api.jar with StructureTransformAction
Transforming core-2.0.2-api.jar with StructureTransformAction
Transforming poi-ooxml-5.2.5.jar with StructureTransformAction
Transforming poi-ooxml-lite-5.2.5.jar with StructureTransformAction
Transforming annotations-23.0.0.jar with StructureTransformAction
Transforming startup-runtime-1.1.1-api.jar with StructureTransformAction
Transforming hilt-core-2.54.jar with StructureTransformAction
Transforming dagger-2.54.jar with StructureTransformAction
Transforming jakarta.inject-api-2.0.1.jar with StructureTransformAction
Transforming javax.inject-1.jar with StructureTransformAction
Transforming jspecify-1.0.0.jar with StructureTransformAction
Transforming dagger-lint-aar-2.54-api.jar with StructureTransformAction
Transforming jsr305-3.0.2.jar with StructureTransformAction
Transforming poi-5.2.5.jar with StructureTransformAction
Transforming commons-codec-1.16.0.jar with StructureTransformAction
Transforming commons-collections4-4.4.jar with StructureTransformAction
Transforming commons-math3-3.6.1.jar with StructureTransformAction
Transforming commons-io-2.15.0.jar with StructureTransformAction
Transforming SparseBitSet-1.3.jar with StructureTransformAction
Transforming xmlbeans-5.2.0.jar with StructureTransformAction
Transforming log4j-api-2.21.1.jar with StructureTransformAction
Transforming commons-compress-1.25.0.jar with StructureTransformAction
Transforming curvesapi-1.08.jar with StructureTransformAction
file or directory '/home/runner/work/fintrack/fintrack/app/src/debug/kotlin', not found
file or directory '/home/runner/work/fintrack/fintrack/app/src/debug/java', not found
file or directory '/home/runner/work/fintrack/fintrack/app/src/main/java', not found
file or directory '/home/runner/work/fintrack/fintrack/app/src/main/java', not found
file or directory '/home/runner/work/fintrack/fintrack/app/src/debug/java', not found
Build cache key for task ':app:kspDebugKotlin' is 55abc2d9b9f46ff8a92169eebd626ae8
Task ':app:kspDebugKotlin' is not up-to-date because:
  No history is available.
The input changes require a full rebuild for incremental task ':app:kspDebugKotlin'.
file or directory '/home/runner/work/fintrack/fintrack/app/src/debug/kotlin', not found
file or directory '/home/runner/work/fintrack/fintrack/app/src/debug/java', not found
file or directory '/home/runner/work/fintrack/fintrack/app/src/main/java', not found
file or directory '/home/runner/work/fintrack/fintrack/app/src/main/java', not found
file or directory '/home/runner/work/fintrack/fintrack/app/src/debug/java', not found
file or directory '/home/runner/work/fintrack/fintrack/app/src/debug/kotlin', not found
file or directory '/home/runner/work/fintrack/fintrack/app/src/debug/java', not found
file or directory '/home/runner/work/fintrack/fintrack/app/src/debug/kotlin', not found
file or directory '/home/runner/work/fintrack/fintrack/app/src/debug/java', not found
file or directory '/home/runner/work/fintrack/fintrack/app/src/main/java', not found
file or directory '/home/runner/work/fintrack/fintrack/app/src/main/java', not found
file or directory '/home/runner/work/fintrack/fintrack/app/src/debug/java', not found
file or directory '/home/runner/work/fintrack/fintrack/app/src/debug/kotlin', not found
file or directory '/home/runner/work/fintrack/fintrack/app/src/debug/java', not found
file or directory '/home/runner/work/fintrack/fintrack/app/src/main/java', not found
file or directory '/home/runner/work/fintrack/fintrack/app/src/main/java', not found
file or directory '/home/runner/work/fintrack/fintrack/app/src/debug/java', not found
file or directory '/home/runner/work/fintrack/fintrack/app/src/debug/kotlin', not found
file or directory '/home/runner/work/fintrack/fintrack/app/src/debug/java', not found
file or directory '/home/runner/work/fintrack/fintrack/app/src/main/java', not found
file or directory '/home/runner/work/fintrack/fintrack/app/src/main/java', not found
file or directory '/home/runner/work/fintrack/fintrack/app/src/debug/java', not found
Kotlin source files: /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/MainActivity.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/journey/goals/JourneyGoalsViewModel.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/journey/goals/JourneyGoalsCard.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/journey/wins/WinsTimeline.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/lock/LockScreen.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/lock/BiometricUnavailableScreen.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/AppViewModel.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/picker/ProfilePickerViewModel.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/picker/ProfilePickerScreen.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/snapshots/entry/SnapshotEntryViewModel.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/snapshots/entry/SnapshotEntryScreen.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/snapshots/entry/AmountParsing.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/snapshots/detail/SnapshotDetailViewModel.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/snapshots/detail/SnapshotDetailScreen.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/snapshots/common/SnapshotDeleteDialog.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/navigation/AppNavGraph.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/home/snapshots/SnapshotsListViewModel.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/home/snapshots/SnapshotsTab.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/home/overview/LineChart.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/home/overview/OverviewViewModel.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/home/overview/OverviewTab.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/home/HomeScreen.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/help/HelpSheetContent.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/help/HelpSheet.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/metrics/MetricInfo.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/theme/Color.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/theme/Theme.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/settings/excel/ExcelViewModel.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/settings/excel/ExcelScreen.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/settings/goals/GoalsManagementViewModel.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/settings/goals/GoalsManagementScreen.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/settings/users/ManageUsersScreen.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/settings/loans/LoansManagementScreen.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/settings/loans/LoansManagementViewModel.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/settings/aim/AimEditorScreen.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/settings/about/AboutScreen.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/settings/about/ManifestViewerScreen.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/settings/about/AboutViewModel.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/settings/backup/BackupScreen.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/settings/SettingsTab.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/settings/SettingsViewModel.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/settings/holdings/HoldingsManagementScreen.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/onboarding/CreateFirstProfileScreen.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/onboarding/OnboardingPagerScreen.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/common/CommonDatePickerSheet.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/celebration/CelebrationSheet.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/domain/milestones/MilestoneDetector.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/domain/glossary/GlossaryContent.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/domain/snapshots/SnapshotDeleteImpact.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/domain/goals/GoalAchievementDetector.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/domain/goals/GoalCalculator.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/domain/util/CurrencyFormatting.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/domain/util/DateFormatter.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/domain/UserScope.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/domain/streaks/StreakCalculator.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/domain/analytics/SnapshotAnalytics.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/domain/model/GoalType.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/domain/model/MilestoneType.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/FintrackApp.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/di/DatabaseModule.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/security/PrivacyOverlay.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/security/BiometricAuthenticator.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/security/KeystorePassphraseStore.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/security/InactivityTracker.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/security/ClipboardAutoClear.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/security/MoneyTextField.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/data/repo/GoalRepository.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/data/repo/StreakRepository.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/data/repo/LoanRepository.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/data/repo/MilestoneRepository.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/data/repo/HoldingRepository.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/data/repo/AimAllocationRepository.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/data/repo/TaxonomyRepository.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/data/repo/GlobalSettingsRepository.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/data/repo/SnapshotRepository.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/data/repo/UserRepository.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/data/xlsx/XlsxImportApplier.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/data/xlsx/XlsxCodec.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/data/db/migrations/Migrations.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/data/db/entities/LoanEntity.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/data/db/entities/HoldingEntity.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/data/db/entities/UserEntity.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/data/db/entities/GlobalSettingsEntity.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/data/db/entities/LoanValueEntity.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/data/db/entities/StreakStateEntity.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/data/db/entities/HoldingValueEntity.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/data/db/entities/SubBucketEntity.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/data/db/entities/AimAllocationEntity.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/data/db/entities/AssetClassEntity.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/data/db/entities/SnapshotEntity.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/data/db/entities/MilestoneEntity.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/data/db/entities/GoalEntity.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/data/db/seed/DevSeedRunner.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/data/db/seed/DevSeedData.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/data/db/seed/SeedData.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/data/db/dao/SnapshotDao.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/data/db/dao/GlobalSettingsDao.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/data/db/dao/UserDao.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/data/db/dao/LoanDao.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/data/db/dao/HoldingValueDao.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/data/db/dao/MilestoneDao.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/data/db/dao/AssetClassDao.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/data/db/dao/StreakStateDao.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/data/db/dao/AimAllocationDao.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/data/db/dao/HoldingDao.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/data/db/dao/SubBucketDao.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/data/db/dao/GoalDao.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/data/db/dao/LoanValueDao.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/data/db/Converters.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/data/db/FintrackDatabase.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/data/backup/CryptoBox.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/data/backup/BackupModels.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/data/backup/CsvCodec.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/data/backup/BackupRepository.kt
Java source files: /home/runner/work/fintrack/fintrack/app/build/generated/source/buildConfig/debug/com/fintrack/BuildConfig.java
Script source files: 
Script file extensions: 
Transforming android.jar with StructureTransformAction
Transforming core-lambda-stubs.jar with StructureTransformAction
Transforming R.jar with StructureTransformAction
Transforming hilt-navigation-compose-1.2.0-api.jar with StructureTransformAction
Transforming hilt-navigation-1.2.0-api.jar with StructureTransformAction
Transforming navigation-common-2.8.5-api.jar with StructureTransformAction
Transforming navigation-runtime-2.8.5-api.jar with StructureTransformAction
Transforming navigation-common-ktx-2.8.5-api.jar with StructureTransformAction
Transforming navigation-runtime-ktx-2.8.5-api.jar with StructureTransformAction
Transforming navigation-compose-2.8.5-api.jar with StructureTransformAction
Transforming hilt-android-2.54-api.jar with StructureTransformAction
Transforming biometric-ktx-1.2.0-alpha05-api.jar with StructureTransformAction
Transforming biometric-1.2.0-alpha05-api.jar with StructureTransformAction
Transforming fragment-1.5.4-api.jar with StructureTransformAction
Transforming loader-1.0.0-api.jar with StructureTransformAction
Transforming viewpager-1.0.0-api.jar with StructureTransformAction
Transforming customview-1.0.0-api.jar with StructureTransformAction
Transforming core-1.15.0-api.jar with StructureTransformAction
Transforming lifecycle-livedata-core-ktx-2.8.7-api.jar with StructureTransformAction
Transforming lifecycle-livedata-2.8.7-api.jar with StructureTransformAction
Transforming lifecycle-runtime-ktx-release-api.jar with StructureTransformAction
Transforming lifecycle-livedata-core-2.8.7-api.jar with StructureTransformAction
Transforming lifecycle-common-jvm-2.8.7.jar with StructureTransformAction
Transforming lifecycle-runtime-release-api.jar with StructureTransformAction
Transforming lifecycle-viewmodel-2.8.7-api.jar with StructureTransformAction
Transforming lifecycle-viewmodel-release-api.jar with StructureTransformAction
Transforming lifecycle-viewmodel-ktx-2.8.7-api.jar with StructureTransformAction
Transforming lifecycle-viewmodel-savedstate-2.8.7-api.jar with StructureTransformAction
Transforming lifecycle-runtime-compose-release-api.jar with StructureTransformAction
Transforming lifecycle-process-2.8.7-api.jar with StructureTransformAction
Transforming lifecycle-viewmodel-compose-release-api.jar with StructureTransformAction
Transforming material3-release-api.jar with StructureTransformAction
Transforming foundation-layout-release-api.jar with StructureTransformAction
Transforming material-ripple-release-api.jar with StructureTransformAction
Transforming foundation-release-api.jar with StructureTransformAction
Transforming animation-core-release-api.jar with StructureTransformAction
Transforming animation-release-api.jar with StructureTransformAction
Transforming ui-util-release-api.jar with StructureTransformAction
Transforming ui-unit-release-api.jar with StructureTransformAction
Transforming ui-text-release-api.jar with StructureTransformAction
Transforming ui-geometry-release-api.jar with StructureTransformAction
Transforming ui-tooling-data-release-api.jar with StructureTransformAction
Transforming ui-tooling-preview-release-api.jar with StructureTransformAction
Transforming ui-graphics-release-api.jar with StructureTransformAction
Transforming material-icons-extended-release-api.jar with StructureTransformAction
Transforming material-icons-core-release-api.jar with StructureTransformAction
Transforming ui-release-api.jar with StructureTransformAction
Transforming ui-tooling-release-api.jar with StructureTransformAction
Transforming ui-test-manifest-1.7.6-api.jar with StructureTransformAction
Transforming activity-1.9.3-api.jar with StructureTransformAction
Transforming activity-compose-1.9.3-api.jar with StructureTransformAction
Transforming activity-ktx-1.9.3-api.jar with StructureTransformAction
Transforming core-ktx-1.15.0-api.jar with StructureTransformAction
Transforming room-common-2.6.1.jar with StructureTransformAction
Transforming room-runtime-2.6.1-api.jar with StructureTransformAction
Transforming room-ktx-2.6.1-api.jar with StructureTransformAction
Transforming sqlite-framework-2.4.0-api.jar with StructureTransformAction
Transforming sqlite-2.4.0-api.jar with StructureTransformAction
Transforming sqlite-ktx-2.4.0-api.jar with StructureTransformAction
Transforming compose-m3-2.0.2-api.jar with StructureTransformAction
Transforming runtime-saveable-release-api.jar with StructureTransformAction
Transforming runtime-release-api.jar with StructureTransformAction
Transforming annotation-experimental-1.4.1-api.jar with StructureTransformAction
Transforming savedstate-ktx-1.2.1-api.jar with StructureTransformAction
Transforming savedstate-1.2.1-api.jar with StructureTransformAction
Transforming kotlinx-coroutines-core-jvm-1.10.1.jar with StructureTransformAction
Transforming kotlinx-coroutines-android-1.10.1.jar with StructureTransformAction
Transforming kotlinx-datetime-jvm-0.6.1.jar with StructureTransformAction
Transforming kotlinx-serialization-core-jvm-1.7.3.jar with StructureTransformAction
Transforming kotlinx-serialization-json-jvm-1.7.3.jar with StructureTransformAction
Transforming security-crypto-1.1.0-alpha06-api.jar with StructureTransformAction
Transforming versionedparcelable-1.1.1-api.jar with StructureTransformAction
Transforming core-runtime-2.2.0-api.jar with StructureTransformAction
Transforming core-common-2.2.0.jar with StructureTransformAction
Transforming collection-jvm-1.4.4.jar with StructureTransformAction
Transforming annotation-jvm-1.9.1.jar with StructureTransformAction
Transforming kotlin-stdlib-jdk8-1.8.22.jar with StructureTransformAction
Transforming kotlin-stdlib-jdk7-1.8.22.jar with StructureTransformAction
Transforming kotlin-stdlib-2.1.20.jar with StructureTransformAction
Transforming sqlcipher-android-4.6.1-api.jar with StructureTransformAction
Transforming compose-2.0.2-api.jar with StructureTransformAction
Transforming core-2.0.2-api.jar with StructureTransformAction
Transforming poi-ooxml-5.2.5.jar with StructureTransformAction
Transforming poi-ooxml-lite-5.2.5.jar with StructureTransformAction
Transforming annotations-23.0.0.jar with StructureTransformAction
Transforming startup-runtime-1.1.1-api.jar with StructureTransformAction
Transforming hilt-core-2.54.jar with StructureTransformAction
Transforming dagger-2.54.jar with StructureTransformAction
Transforming jakarta.inject-api-2.0.1.jar with StructureTransformAction
Transforming javax.inject-1.jar with StructureTransformAction
Transforming jspecify-1.0.0.jar with StructureTransformAction
Transforming dagger-lint-aar-2.54-api.jar with StructureTransformAction
Transforming jsr305-3.0.2.jar with StructureTransformAction
Transforming poi-5.2.5.jar with StructureTransformAction
Transforming commons-codec-1.16.0.jar with StructureTransformAction
Transforming commons-collections4-4.4.jar with StructureTransformAction
Transforming commons-math3-3.6.1.jar with StructureTransformAction
Transforming commons-io-2.15.0.jar with StructureTransformAction
Transforming SparseBitSet-1.3.jar with StructureTransformAction
Transforming xmlbeans-5.2.0.jar with StructureTransformAction
Transforming log4j-api-2.21.1.jar with StructureTransformAction
Transforming commons-compress-1.25.0.jar with StructureTransformAction
Transforming curvesapi-1.08.jar with StructureTransformAction
[KOTLIN] Kotlin compilation 'jdkHome' argument: null
i: starting the daemon as: /usr/lib/jvm/temurin-17-jdk-amd64/bin/java -cp /home/runner/.gradle/caches/modules-2/files-2.1/org.jetbrains.kotlin/kotlin-compiler-embeddable/2.1.20/4ef56b3316798316bfac7a0ae443391c9e900ea1/kotlin-compiler-embeddable-2.1.20.jar:/home/runner/.gradle/caches/modules-2/files-2.1/org.jetbrains.kotlin/kotlin-stdlib/2.1.20/aa8ca79cd50578314f6d1180c47cbe14c0fee567/kotlin-stdlib-2.1.20.jar:/home/runner/.gradle/caches/modules-2/files-2.1/org.jetbrains.kotlin/kotlin-script-runtime/2.1.20/f7c623d7f7bdb01f5ccd6b437bc0a937fcd7c57e/kotlin-script-runtime-2.1.20.jar:/home/runner/.gradle/caches/modules-2/files-2.1/org.jetbrains.kotlin/kotlin-reflect/1.6.10/1cbe9c92c12a94eea200d23c2bbaedaf3daf5132/kotlin-reflect-1.6.10.jar:/home/runner/.gradle/caches/modules-2/files-2.1/org.jetbrains.kotlin/kotlin-daemon-embeddable/2.1.20/95670fce77befd02a70a0bc3abe8ee4533521334/kotlin-daemon-embeddable-2.1.20.jar:/home/runner/.gradle/caches/modules-2/files-2.1/org.jetbrains.intellij.deps/trove4j/1.0.20200330/3afb14d5f9ceb459d724e907a21145e8ff394f02/trove4j-1.0.20200330.jar:/home/runner/.gradle/caches/modules-2/files-2.1/org.jetbrains.kotlinx/kotlinx-coroutines-core-jvm/1.8.0/ac1dc37a30a93150b704022f8d895ee1bd3a36b3/kotlinx-coroutines-core-jvm-1.8.0.jar:/home/runner/.gradle/caches/modules-2/files-2.1/org.jetbrains/annotations/13.0/919f0dfe192fb4e063e7dacadee7f8bb9a2672a9/annotations-13.0.jar -Djava.awt.headless=true -Djava.rmi.server.hostname=127.0.0.1 -Xmx4g -XX:ReservedCodeCacheSize=320m -Dkotlin.environment.keepalive -ea -XX:+UseCodeCacheFlushing -XX:+UseParallelGC -Dkotlin.daemon.initiator.marker.file=/tmp/kotlin-compiler-in-fintrack-15188590312953407547.alive --add-exports java.base/sun.nio.ch=ALL-UNNAMED org.jetbrains.kotlin.daemon.KotlinCompileDaemon --daemon-runFilesPath /home/runner/.kotlin/daemon --daemon-autoshutdownIdleSeconds=7200 --daemon-compilerClasspath /home/runner/.gradle/caches/modules-2/files-2.1/org.jetbrains.kotlin/kotlin-compiler-embeddable/2.1.20/4ef56b3316798316bfac7a0ae443391c9e900ea1/kotlin-compiler-embeddable-2.1.20.jar:/home/runner/.gradle/caches/modules-2/files-2.1/org.jetbrains.kotlin/kotlin-stdlib/2.1.20/aa8ca79cd50578314f6d1180c47cbe14c0fee567/kotlin-stdlib-2.1.20.jar:/home/runner/.gradle/caches/modules-2/files-2.1/org.jetbrains.kotlin/kotlin-script-runtime/2.1.20/f7c623d7f7bdb01f5ccd6b437bc0a937fcd7c57e/kotlin-script-runtime-2.1.20.jar:/home/runner/.gradle/caches/modules-2/files-2.1/org.jetbrains.kotlin/kotlin-reflect/1.6.10/1cbe9c92c12a94eea200d23c2bbaedaf3daf5132/kotlin-reflect-1.6.10.jar:/home/runner/.gradle/caches/modules-2/files-2.1/org.jetbrains.kotlin/kotlin-daemon-embeddable/2.1.20/95670fce77befd02a70a0bc3abe8ee4533521334/kotlin-daemon-embeddable-2.1.20.jar:/home/runner/.gradle/caches/modules-2/files-2.1/org.jetbrains.intellij.deps/trove4j/1.0.20200330/3afb14d5f9ceb459d724e907a21145e8ff394f02/trove4j-1.0.20200330.jar:/home/runner/.gradle/caches/modules-2/files-2.1/org.jetbrains.kotlinx/kotlinx-coroutines-core-jvm/1.8.0/ac1dc37a30a93150b704022f8d895ee1bd3a36b3/kotlinx-coroutines-core-jvm-1.8.0.jar:/home/runner/.gradle/caches/modules-2/files-2.1/org.jetbrains/annotations/13.0/919f0dfe192fb4e063e7dacadee7f8bb9a2672a9/annotations-13.0.jar
i: #1 retrying connecting to the daemon 
i: [ksp] loaded provider(s): [dagger.hilt.processor.internal.uninstallmodules.KspUninstallModulesProcessor$Provider, dagger.hilt.processor.internal.definecomponent.KspDefineComponentProcessor$Provider, dagger.hilt.android.processor.internal.androidentrypoint.KspAndroidEntryPointProcessor$Provider, dagger.hilt.processor.internal.root.KspRootProcessor$Provider, dagger.hilt.android.processor.internal.viewmodel.KspViewModelProcessor$Provider, dagger.hilt.android.processor.internal.bindvalue.KspBindValueProcessor$Provider, dagger.hilt.processor.internal.root.KspComponentTreeDepsProcessor$Provider, dagger.hilt.processor.internal.aliasof.KspAliasOfProcessor$Provider, dagger.hilt.processor.internal.generatesrootinput.KspGeneratesRootInputProcessor$Provider, dagger.hilt.processor.internal.originatingelement.KspOriginatingElementProcessor$Provider, dagger.hilt.processor.internal.earlyentrypoint.KspEarlyEntryPointProcessor$Provider, dagger.hilt.android.processor.internal.customtestapplication.KspCustomTestApplicationProcessor$Provider, dagger.hilt.processor.internal.aggregateddeps.KspAggregatedDepsProcessor$Provider, androidx.room.RoomKspProcessor$Provider, dagger.internal.codegen.KspComponentProcessor$Provider]
Finished executing kotlin compiler using DAEMON strategy
Stored cache entry for task ':app:kspDebugKotlin' with cache key 55abc2d9b9f46ff8a92169eebd626ae8
Resolve mutations for :app:compileDebugKotlin (Thread[Execution worker Thread 2,5,main]) started.
:app:compileDebugKotlin (Thread[Execution worker Thread 2,5,main]) started.

> Task :app:compileDebugKotlin
Transforming android.jar with BuildToolsApiClasspathEntrySnapshotTransform
Transforming core-lambda-stubs.jar with BuildToolsApiClasspathEntrySnapshotTransform
Transforming R.jar with BuildToolsApiClasspathEntrySnapshotTransform
Transforming hilt-navigation-compose-1.2.0-api.jar with BuildToolsApiClasspathEntrySnapshotTransform
Transforming hilt-navigation-1.2.0-api.jar with BuildToolsApiClasspathEntrySnapshotTransform
Transforming navigation-common-2.8.5-api.jar with BuildToolsApiClasspathEntrySnapshotTransform
Transforming navigation-runtime-2.8.5-api.jar with BuildToolsApiClasspathEntrySnapshotTransform
Transforming navigation-common-ktx-2.8.5-api.jar with BuildToolsApiClasspathEntrySnapshotTransform
Transforming navigation-runtime-ktx-2.8.5-api.jar with BuildToolsApiClasspathEntrySnapshotTransform
Transforming navigation-compose-2.8.5-api.jar with BuildToolsApiClasspathEntrySnapshotTransform
Transforming hilt-android-2.54-api.jar with BuildToolsApiClasspathEntrySnapshotTransform
Transforming biometric-ktx-1.2.0-alpha05-api.jar with BuildToolsApiClasspathEntrySnapshotTransform
Transforming biometric-1.2.0-alpha05-api.jar with BuildToolsApiClasspathEntrySnapshotTransform
Transforming fragment-1.5.4-api.jar with BuildToolsApiClasspathEntrySnapshotTransform
Transforming loader-1.0.0-api.jar with BuildToolsApiClasspathEntrySnapshotTransform
Transforming viewpager-1.0.0-api.jar with BuildToolsApiClasspathEntrySnapshotTransform
Transforming customview-1.0.0-api.jar with BuildToolsApiClasspathEntrySnapshotTransform
Transforming core-1.15.0-api.jar with BuildToolsApiClasspathEntrySnapshotTransform
Transforming lifecycle-livedata-core-ktx-2.8.7-api.jar with BuildToolsApiClasspathEntrySnapshotTransform
Transforming lifecycle-livedata-2.8.7-api.jar with BuildToolsApiClasspathEntrySnapshotTransform
Transforming lifecycle-runtime-ktx-release-api.jar with BuildToolsApiClasspathEntrySnapshotTransform
Transforming lifecycle-livedata-core-2.8.7-api.jar with BuildToolsApiClasspathEntrySnapshotTransform
Transforming lifecycle-common-jvm-2.8.7.jar with BuildToolsApiClasspathEntrySnapshotTransform
Transforming lifecycle-runtime-release-api.jar with BuildToolsApiClasspathEntrySnapshotTransform
Transforming lifecycle-viewmodel-2.8.7-api.jar with BuildToolsApiClasspathEntrySnapshotTransform
Transforming lifecycle-viewmodel-release-api.jar with BuildToolsApiClasspathEntrySnapshotTransform
Transforming lifecycle-viewmodel-ktx-2.8.7-api.jar with BuildToolsApiClasspathEntrySnapshotTransform
Transforming lifecycle-viewmodel-savedstate-2.8.7-api.jar with BuildToolsApiClasspathEntrySnapshotTransform
Transforming lifecycle-runtime-compose-release-api.jar with BuildToolsApiClasspathEntrySnapshotTransform
Transforming lifecycle-process-2.8.7-api.jar with BuildToolsApiClasspathEntrySnapshotTransform
Transforming lifecycle-viewmodel-compose-release-api.jar with BuildToolsApiClasspathEntrySnapshotTransform
Transforming material3-release-api.jar with BuildToolsApiClasspathEntrySnapshotTransform
Transforming foundation-layout-release-api.jar with BuildToolsApiClasspathEntrySnapshotTransform
Transforming material-ripple-release-api.jar with BuildToolsApiClasspathEntrySnapshotTransform
Transforming foundation-release-api.jar with BuildToolsApiClasspathEntrySnapshotTransform
Transforming animation-core-release-api.jar with BuildToolsApiClasspathEntrySnapshotTransform
Transforming animation-release-api.jar with BuildToolsApiClasspathEntrySnapshotTransform
Transforming ui-util-release-api.jar with BuildToolsApiClasspathEntrySnapshotTransform
Transforming ui-unit-release-api.jar with BuildToolsApiClasspathEntrySnapshotTransform
Transforming ui-text-release-api.jar with BuildToolsApiClasspathEntrySnapshotTransform
Transforming ui-geometry-release-api.jar with BuildToolsApiClasspathEntrySnapshotTransform
Transforming ui-tooling-data-release-api.jar with BuildToolsApiClasspathEntrySnapshotTransform
Transforming ui-tooling-preview-release-api.jar with BuildToolsApiClasspathEntrySnapshotTransform
Transforming ui-graphics-release-api.jar with BuildToolsApiClasspathEntrySnapshotTransform
Transforming material-icons-extended-release-api.jar with BuildToolsApiClasspathEntrySnapshotTransform
Transforming material-icons-core-release-api.jar with BuildToolsApiClasspathEntrySnapshotTransform
Transforming ui-release-api.jar with BuildToolsApiClasspathEntrySnapshotTransform
Transforming ui-tooling-release-api.jar with BuildToolsApiClasspathEntrySnapshotTransform
Transforming ui-test-manifest-1.7.6-api.jar with BuildToolsApiClasspathEntrySnapshotTransform
Transforming activity-1.9.3-api.jar with BuildToolsApiClasspathEntrySnapshotTransform
Transforming activity-compose-1.9.3-api.jar with BuildToolsApiClasspathEntrySnapshotTransform
Transforming activity-ktx-1.9.3-api.jar with BuildToolsApiClasspathEntrySnapshotTransform
Transforming core-ktx-1.15.0-api.jar with BuildToolsApiClasspathEntrySnapshotTransform
Transforming room-common-2.6.1.jar with BuildToolsApiClasspathEntrySnapshotTransform
Transforming room-runtime-2.6.1-api.jar with BuildToolsApiClasspathEntrySnapshotTransform
Transforming room-ktx-2.6.1-api.jar with BuildToolsApiClasspathEntrySnapshotTransform
Transforming sqlite-framework-2.4.0-api.jar with BuildToolsApiClasspathEntrySnapshotTransform
Transforming sqlite-2.4.0-api.jar with BuildToolsApiClasspathEntrySnapshotTransform
Transforming sqlite-ktx-2.4.0-api.jar with BuildToolsApiClasspathEntrySnapshotTransform
Transforming compose-m3-2.0.2-api.jar with BuildToolsApiClasspathEntrySnapshotTransform
Transforming runtime-saveable-release-api.jar with BuildToolsApiClasspathEntrySnapshotTransform
Transforming runtime-release-api.jar with BuildToolsApiClasspathEntrySnapshotTransform
Transforming annotation-experimental-1.4.1-api.jar with BuildToolsApiClasspathEntrySnapshotTransform
Transforming savedstate-ktx-1.2.1-api.jar with BuildToolsApiClasspathEntrySnapshotTransform
Transforming savedstate-1.2.1-api.jar with BuildToolsApiClasspathEntrySnapshotTransform
Transforming kotlinx-coroutines-core-jvm-1.10.1.jar with BuildToolsApiClasspathEntrySnapshotTransform
Transforming kotlinx-coroutines-android-1.10.1.jar with BuildToolsApiClasspathEntrySnapshotTransform
Transforming kotlinx-datetime-jvm-0.6.1.jar with BuildToolsApiClasspathEntrySnapshotTransform
Transforming kotlinx-serialization-core-jvm-1.7.3.jar with BuildToolsApiClasspathEntrySnapshotTransform
Transforming kotlinx-serialization-json-jvm-1.7.3.jar with BuildToolsApiClasspathEntrySnapshotTransform
Transforming security-crypto-1.1.0-alpha06-api.jar with BuildToolsApiClasspathEntrySnapshotTransform
Transforming versionedparcelable-1.1.1-api.jar with BuildToolsApiClasspathEntrySnapshotTransform
Transforming core-runtime-2.2.0-api.jar with BuildToolsApiClasspathEntrySnapshotTransform
Transforming core-common-2.2.0.jar with BuildToolsApiClasspathEntrySnapshotTransform
Transforming collection-jvm-1.4.4.jar with BuildToolsApiClasspathEntrySnapshotTransform
Transforming annotation-jvm-1.9.1.jar with BuildToolsApiClasspathEntrySnapshotTransform
Transforming kotlin-stdlib-jdk8-1.8.22.jar with BuildToolsApiClasspathEntrySnapshotTransform
Transforming kotlin-stdlib-jdk7-1.8.22.jar with BuildToolsApiClasspathEntrySnapshotTransform
Transforming kotlin-stdlib-2.1.20.jar with BuildToolsApiClasspathEntrySnapshotTransform
Transforming sqlcipher-android-4.6.1-api.jar with BuildToolsApiClasspathEntrySnapshotTransform
Transforming compose-2.0.2-api.jar with BuildToolsApiClasspathEntrySnapshotTransform
Transforming core-2.0.2-api.jar with BuildToolsApiClasspathEntrySnapshotTransform
Transforming poi-ooxml-5.2.5.jar with BuildToolsApiClasspathEntrySnapshotTransform
Transforming poi-ooxml-lite-5.2.5.jar with BuildToolsApiClasspathEntrySnapshotTransform
Transforming annotations-23.0.0.jar with BuildToolsApiClasspathEntrySnapshotTransform
Transforming startup-runtime-1.1.1-api.jar with BuildToolsApiClasspathEntrySnapshotTransform
Transforming hilt-core-2.54.jar with BuildToolsApiClasspathEntrySnapshotTransform
Transforming dagger-2.54.jar with BuildToolsApiClasspathEntrySnapshotTransform
Transforming jakarta.inject-api-2.0.1.jar with BuildToolsApiClasspathEntrySnapshotTransform
Transforming javax.inject-1.jar with BuildToolsApiClasspathEntrySnapshotTransform
Transforming jspecify-1.0.0.jar with BuildToolsApiClasspathEntrySnapshotTransform
Transforming dagger-lint-aar-2.54-api.jar with BuildToolsApiClasspathEntrySnapshotTransform
Transforming jsr305-3.0.2.jar with BuildToolsApiClasspathEntrySnapshotTransform
Transforming poi-5.2.5.jar with BuildToolsApiClasspathEntrySnapshotTransform
Transforming commons-codec-1.16.0.jar with BuildToolsApiClasspathEntrySnapshotTransform
Transforming commons-collections4-4.4.jar with BuildToolsApiClasspathEntrySnapshotTransform
Transforming commons-math3-3.6.1.jar with BuildToolsApiClasspathEntrySnapshotTransform
Transforming commons-io-2.15.0.jar with BuildToolsApiClasspathEntrySnapshotTransform
Transforming SparseBitSet-1.3.jar with BuildToolsApiClasspathEntrySnapshotTransform
Transforming xmlbeans-5.2.0.jar with BuildToolsApiClasspathEntrySnapshotTransform
Transforming log4j-api-2.21.1.jar with BuildToolsApiClasspathEntrySnapshotTransform
Transforming commons-compress-1.25.0.jar with BuildToolsApiClasspathEntrySnapshotTransform
Transforming curvesapi-1.08.jar with BuildToolsApiClasspathEntrySnapshotTransform
Build cache key for task ':app:compileDebugKotlin' is 4cd2db2d37e68d368aff79c98792af8e
Task ':app:compileDebugKotlin' is not up-to-date because:
  No history is available.
The input changes require a full rebuild for incremental task ':app:compileDebugKotlin'.
file or directory '/home/runner/work/fintrack/fintrack/app/src/debug/kotlin', not found
file or directory '/home/runner/work/fintrack/fintrack/app/src/debug/java', not found
file or directory '/home/runner/work/fintrack/fintrack/app/src/debug/kotlin', not found
file or directory '/home/runner/work/fintrack/fintrack/app/src/debug/kotlin', not found
file or directory '/home/runner/work/fintrack/fintrack/app/src/debug/java', not found
file or directory '/home/runner/work/fintrack/fintrack/app/src/main/java', not found
file or directory '/home/runner/work/fintrack/fintrack/app/src/main/java', not found
file or directory '/home/runner/work/fintrack/fintrack/app/src/main/java', not found
file or directory '/home/runner/work/fintrack/fintrack/app/src/debug/java', not found
file or directory '/home/runner/work/fintrack/fintrack/app/src/debug/kotlin', not found
file or directory '/home/runner/work/fintrack/fintrack/app/src/debug/java', not found
file or directory '/home/runner/work/fintrack/fintrack/app/src/debug/kotlin', not found
file or directory '/home/runner/work/fintrack/fintrack/app/src/debug/kotlin', not found
file or directory '/home/runner/work/fintrack/fintrack/app/src/debug/java', not found
file or directory '/home/runner/work/fintrack/fintrack/app/src/debug/kotlin', not found
file or directory '/home/runner/work/fintrack/fintrack/app/src/debug/java', not found
file or directory '/home/runner/work/fintrack/fintrack/app/src/debug/kotlin', not found
file or directory '/home/runner/work/fintrack/fintrack/app/src/debug/kotlin', not found
file or directory '/home/runner/work/fintrack/fintrack/app/src/debug/java', not found
file or directory '/home/runner/work/fintrack/fintrack/app/src/main/java', not found
file or directory '/home/runner/work/fintrack/fintrack/app/src/main/java', not found
file or directory '/home/runner/work/fintrack/fintrack/app/src/main/java', not found
file or directory '/home/runner/work/fintrack/fintrack/app/src/debug/java', not found
file or directory '/home/runner/work/fintrack/fintrack/app/src/debug/kotlin', not found
file or directory '/home/runner/work/fintrack/fintrack/app/src/debug/java', not found
file or directory '/home/runner/work/fintrack/fintrack/app/src/debug/kotlin', not found
file or directory '/home/runner/work/fintrack/fintrack/app/src/debug/kotlin', not found
file or directory '/home/runner/work/fintrack/fintrack/app/src/debug/java', not found
file or directory '/home/runner/work/fintrack/fintrack/app/src/main/java', not found
file or directory '/home/runner/work/fintrack/fintrack/app/src/main/java', not found
file or directory '/home/runner/work/fintrack/fintrack/app/src/main/java', not found
file or directory '/home/runner/work/fintrack/fintrack/app/src/debug/java', not found
file or directory '/home/runner/work/fintrack/fintrack/app/src/debug/kotlin', not found
file or directory '/home/runner/work/fintrack/fintrack/app/src/debug/java', not found
file or directory '/home/runner/work/fintrack/fintrack/app/src/debug/kotlin', not found
file or directory '/home/runner/work/fintrack/fintrack/app/src/debug/kotlin', not found
file or directory '/home/runner/work/fintrack/fintrack/app/src/debug/java', not found
file or directory '/home/runner/work/fintrack/fintrack/app/src/main/java', not found
file or directory '/home/runner/work/fintrack/fintrack/app/src/main/java', not found
file or directory '/home/runner/work/fintrack/fintrack/app/src/main/java', not found
file or directory '/home/runner/work/fintrack/fintrack/app/src/debug/java', not found
Kotlin source files: /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/kotlin/com/fintrack/data/db/dao/GoalDao_Impl.kt, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/kotlin/com/fintrack/data/db/dao/SnapshotDao_Impl.kt, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/kotlin/com/fintrack/data/db/dao/LoanValueDao_Impl.kt, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/kotlin/com/fintrack/data/db/dao/HoldingValueDao_Impl.kt, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/kotlin/com/fintrack/data/db/dao/GlobalSettingsDao_Impl.kt, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/kotlin/com/fintrack/data/db/dao/HoldingDao_Impl.kt, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/kotlin/com/fintrack/data/db/dao/AimAllocationDao_Impl.kt, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/kotlin/com/fintrack/data/db/dao/LoanDao_Impl.kt, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/kotlin/com/fintrack/data/db/dao/MilestoneDao_Impl.kt, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/kotlin/com/fintrack/data/db/dao/UserDao_Impl.kt, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/kotlin/com/fintrack/data/db/dao/SubBucketDao_Impl.kt, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/kotlin/com/fintrack/data/db/dao/StreakStateDao_Impl.kt, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/kotlin/com/fintrack/data/db/dao/AssetClassDao_Impl.kt, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/kotlin/com/fintrack/data/db/FintrackDatabase_Impl.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/MainActivity.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/journey/goals/JourneyGoalsViewModel.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/journey/goals/JourneyGoalsCard.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/journey/wins/WinsTimeline.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/lock/LockScreen.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/lock/BiometricUnavailableScreen.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/AppViewModel.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/picker/ProfilePickerViewModel.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/picker/ProfilePickerScreen.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/snapshots/entry/SnapshotEntryViewModel.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/snapshots/entry/SnapshotEntryScreen.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/snapshots/entry/AmountParsing.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/snapshots/detail/SnapshotDetailViewModel.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/snapshots/detail/SnapshotDetailScreen.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/snapshots/common/SnapshotDeleteDialog.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/navigation/AppNavGraph.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/home/snapshots/SnapshotsListViewModel.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/home/snapshots/SnapshotsTab.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/home/overview/LineChart.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/home/overview/OverviewViewModel.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/home/overview/OverviewTab.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/home/HomeScreen.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/help/HelpSheetContent.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/help/HelpSheet.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/metrics/MetricInfo.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/theme/Color.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/theme/Theme.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/settings/excel/ExcelViewModel.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/settings/excel/ExcelScreen.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/settings/goals/GoalsManagementViewModel.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/settings/goals/GoalsManagementScreen.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/settings/users/ManageUsersScreen.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/settings/loans/LoansManagementScreen.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/settings/loans/LoansManagementViewModel.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/settings/aim/AimEditorScreen.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/settings/about/AboutScreen.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/settings/about/ManifestViewerScreen.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/settings/about/AboutViewModel.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/settings/backup/BackupScreen.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/settings/SettingsTab.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/settings/SettingsViewModel.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/settings/holdings/HoldingsManagementScreen.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/onboarding/CreateFirstProfileScreen.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/onboarding/OnboardingPagerScreen.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/common/CommonDatePickerSheet.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/celebration/CelebrationSheet.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/domain/milestones/MilestoneDetector.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/domain/glossary/GlossaryContent.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/domain/snapshots/SnapshotDeleteImpact.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/domain/goals/GoalAchievementDetector.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/domain/goals/GoalCalculator.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/domain/util/CurrencyFormatting.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/domain/util/DateFormatter.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/domain/UserScope.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/domain/streaks/StreakCalculator.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/domain/analytics/SnapshotAnalytics.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/domain/model/GoalType.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/domain/model/MilestoneType.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/FintrackApp.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/di/DatabaseModule.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/security/PrivacyOverlay.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/security/BiometricAuthenticator.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/security/KeystorePassphraseStore.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/security/InactivityTracker.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/security/ClipboardAutoClear.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/security/MoneyTextField.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/data/repo/GoalRepository.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/data/repo/StreakRepository.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/data/repo/LoanRepository.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/data/repo/MilestoneRepository.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/data/repo/HoldingRepository.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/data/repo/AimAllocationRepository.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/data/repo/TaxonomyRepository.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/data/repo/GlobalSettingsRepository.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/data/repo/SnapshotRepository.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/data/repo/UserRepository.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/data/xlsx/XlsxImportApplier.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/data/xlsx/XlsxCodec.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/data/db/migrations/Migrations.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/data/db/entities/LoanEntity.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/data/db/entities/HoldingEntity.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/data/db/entities/UserEntity.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/data/db/entities/GlobalSettingsEntity.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/data/db/entities/LoanValueEntity.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/data/db/entities/StreakStateEntity.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/data/db/entities/HoldingValueEntity.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/data/db/entities/SubBucketEntity.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/data/db/entities/AimAllocationEntity.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/data/db/entities/AssetClassEntity.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/data/db/entities/SnapshotEntity.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/data/db/entities/MilestoneEntity.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/data/db/entities/GoalEntity.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/data/db/seed/DevSeedRunner.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/data/db/seed/DevSeedData.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/data/db/seed/SeedData.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/data/db/dao/SnapshotDao.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/data/db/dao/GlobalSettingsDao.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/data/db/dao/UserDao.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/data/db/dao/LoanDao.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/data/db/dao/HoldingValueDao.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/data/db/dao/MilestoneDao.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/data/db/dao/AssetClassDao.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/data/db/dao/StreakStateDao.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/data/db/dao/AimAllocationDao.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/data/db/dao/HoldingDao.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/data/db/dao/SubBucketDao.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/data/db/dao/GoalDao.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/data/db/dao/LoanValueDao.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/data/db/Converters.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/data/db/FintrackDatabase.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/data/backup/CryptoBox.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/data/backup/BackupModels.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/data/backup/CsvCodec.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/data/backup/BackupRepository.kt
Java source files: /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/com/fintrack/MainActivity_GeneratedInjector.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/com/fintrack/ui/AppViewModel_HiltModules_KeyModule_Provide_LazyMapKey.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/com/fintrack/ui/journey/goals/JourneyGoalsViewModel_HiltModules_KeyModule_Provide_LazyMapKey.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/com/fintrack/ui/journey/goals/JourneyGoalsViewModel_HiltModules_KeyModule_ProvideFactory.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/com/fintrack/ui/journey/goals/JourneyGoalsViewModel_HiltModules.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/com/fintrack/ui/journey/goals/JourneyGoalsViewModel_HiltModules_BindsModule_Binds_LazyMapKey.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/com/fintrack/ui/journey/goals/JourneyGoalsViewModel_Factory.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/com/fintrack/ui/journey/wins/WinsTimelineViewModel_HiltModules.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/com/fintrack/ui/journey/wins/WinsTimelineViewModel_HiltModules_KeyModule_ProvideFactory.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/com/fintrack/ui/journey/wins/WinsTimelineViewModel_HiltModules_KeyModule_Provide_LazyMapKey.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/com/fintrack/ui/journey/wins/WinsTimelineViewModel_Factory.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/com/fintrack/ui/journey/wins/WinsTimelineViewModel_HiltModules_BindsModule_Binds_LazyMapKey.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/com/fintrack/ui/AppViewModel_HiltModules_KeyModule_ProvideFactory.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/com/fintrack/ui/picker/ProfilePickerViewModel_HiltModules.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/com/fintrack/ui/picker/ProfilePickerViewModel_HiltModules_BindsModule_Binds_LazyMapKey.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/com/fintrack/ui/picker/ProfilePickerViewModel_HiltModules_KeyModule_ProvideFactory.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/com/fintrack/ui/picker/ProfilePickerViewModel_HiltModules_KeyModule_Provide_LazyMapKey.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/com/fintrack/ui/picker/ProfilePickerViewModel_Factory.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/com/fintrack/ui/snapshots/entry/SnapshotEntryViewModel_HiltModules.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/com/fintrack/ui/snapshots/entry/SnapshotEntryViewModel_Factory.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/com/fintrack/ui/snapshots/entry/SnapshotEntryViewModel_HiltModules_KeyModule_Provide_LazyMapKey.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/com/fintrack/ui/snapshots/entry/SnapshotEntryViewModel_HiltModules_KeyModule_ProvideFactory.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/com/fintrack/ui/snapshots/entry/SnapshotEntryViewModel_HiltModules_BindsModule_Binds_LazyMapKey.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/com/fintrack/ui/snapshots/detail/SnapshotDetailViewModel_HiltModules.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/com/fintrack/ui/snapshots/detail/SnapshotDetailViewModel_HiltModules_BindsModule_Binds_LazyMapKey.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/com/fintrack/ui/snapshots/detail/SnapshotDetailViewModel_HiltModules_KeyModule_Provide_LazyMapKey.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/com/fintrack/ui/snapshots/detail/SnapshotDetailViewModel_HiltModules_KeyModule_ProvideFactory.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/com/fintrack/ui/snapshots/detail/SnapshotDetailViewModel_Factory.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/com/fintrack/ui/home/HomeViewModel_Factory.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/com/fintrack/ui/home/HomeViewModel_HiltModules_KeyModule_Provide_LazyMapKey.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/com/fintrack/ui/home/HomeViewModel_HiltModules.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/com/fintrack/ui/home/snapshots/SnapshotsListViewModel_HiltModules_BindsModule_Binds_LazyMapKey.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/com/fintrack/ui/home/snapshots/SnapshotsListViewModel_HiltModules_KeyModule_ProvideFactory.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/com/fintrack/ui/home/snapshots/SnapshotsListViewModel_Factory.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/com/fintrack/ui/home/snapshots/SnapshotsListViewModel_HiltModules.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/com/fintrack/ui/home/snapshots/SnapshotsListViewModel_HiltModules_KeyModule_Provide_LazyMapKey.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/com/fintrack/ui/home/overview/OverviewViewModel_HiltModules_KeyModule_ProvideFactory.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/com/fintrack/ui/home/overview/OverviewViewModel_HiltModules.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/com/fintrack/ui/home/overview/OverviewViewModel_HiltModules_BindsModule_Binds_LazyMapKey.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/com/fintrack/ui/home/overview/OverviewViewModel_Factory.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/com/fintrack/ui/home/overview/OverviewViewModel_HiltModules_KeyModule_Provide_LazyMapKey.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/com/fintrack/ui/home/HomeViewModel_HiltModules_KeyModule_ProvideFactory.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/com/fintrack/ui/home/HomeViewModel_HiltModules_BindsModule_Binds_LazyMapKey.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/com/fintrack/ui/AppViewModel_HiltModules.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/com/fintrack/ui/settings/SettingsTabViewModel_HiltModules_KeyModule_Provide_LazyMapKey.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/com/fintrack/ui/settings/excel/ExcelViewModel_HiltModules_KeyModule_Provide_LazyMapKey.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/com/fintrack/ui/settings/excel/ExcelViewModel_HiltModules_KeyModule_ProvideFactory.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/com/fintrack/ui/settings/excel/ExcelViewModel_HiltModules.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/com/fintrack/ui/settings/excel/ExcelViewModel_HiltModules_BindsModule_Binds_LazyMapKey.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/com/fintrack/ui/settings/excel/ExcelViewModel_Factory.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/com/fintrack/ui/settings/SettingsTabViewModel_HiltModules_KeyModule_ProvideFactory.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/com/fintrack/ui/settings/goals/GoalsManagementViewModel_HiltModules.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/com/fintrack/ui/settings/goals/GoalsManagementViewModel_HiltModules_KeyModule_ProvideFactory.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/com/fintrack/ui/settings/goals/GoalsManagementViewModel_HiltModules_BindsModule_Binds_LazyMapKey.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/com/fintrack/ui/settings/goals/GoalsManagementViewModel_Factory.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/com/fintrack/ui/settings/goals/GoalsManagementViewModel_HiltModules_KeyModule_Provide_LazyMapKey.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/com/fintrack/ui/settings/loans/LoansManagementViewModel_HiltModules_BindsModule_Binds_LazyMapKey.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/com/fintrack/ui/settings/loans/LoansManagementViewModel_HiltModules_KeyModule_Provide_LazyMapKey.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/com/fintrack/ui/settings/loans/LoansManagementViewModel_HiltModules_KeyModule_ProvideFactory.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/com/fintrack/ui/settings/loans/LoansManagementViewModel_Factory.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/com/fintrack/ui/settings/loans/LoansManagementViewModel_HiltModules.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/com/fintrack/ui/settings/aim/AimEditorViewModel_HiltModules_KeyModule_Provide_LazyMapKey.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/com/fintrack/ui/settings/aim/AimEditorViewModel_HiltModules.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/com/fintrack/ui/settings/aim/AimEditorViewModel_HiltModules_BindsModule_Binds_LazyMapKey.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/com/fintrack/ui/settings/aim/AimEditorViewModel_Factory.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/com/fintrack/ui/settings/aim/AimEditorViewModel_HiltModules_KeyModule_ProvideFactory.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/com/fintrack/ui/settings/SettingsTabViewModel_HiltModules_BindsModule_Binds_LazyMapKey.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/com/fintrack/ui/settings/about/AboutViewModel_HiltModules_KeyModule_Provide_LazyMapKey.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/com/fintrack/ui/settings/about/AboutViewModel_HiltModules_KeyModule_ProvideFactory.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/com/fintrack/ui/settings/about/AboutViewModel_Factory.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/com/fintrack/ui/settings/about/AboutViewModel_HiltModules_BindsModule_Binds_LazyMapKey.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/com/fintrack/ui/settings/about/AboutViewModel_HiltModules.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/com/fintrack/ui/settings/backup/BackupViewModel_HiltModules_KeyModule_ProvideFactory.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/com/fintrack/ui/settings/backup/BackupViewModel_Factory.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/com/fintrack/ui/settings/backup/BackupViewModel_HiltModules_KeyModule_Provide_LazyMapKey.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/com/fintrack/ui/settings/backup/BackupViewModel_HiltModules.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/com/fintrack/ui/settings/backup/BackupViewModel_HiltModules_BindsModule_Binds_LazyMapKey.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/com/fintrack/ui/settings/SettingsTabViewModel_Factory.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/com/fintrack/ui/settings/holdings/HoldingsManagementViewModel_HiltModules_KeyModule_Provide_LazyMapKey.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/com/fintrack/ui/settings/holdings/HoldingsManagementViewModel_HiltModules.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/com/fintrack/ui/settings/holdings/HoldingsManagementViewModel_Factory.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/com/fintrack/ui/settings/holdings/HoldingsManagementViewModel_HiltModules_KeyModule_ProvideFactory.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/com/fintrack/ui/settings/holdings/HoldingsManagementViewModel_HiltModules_BindsModule_Binds_LazyMapKey.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/com/fintrack/ui/settings/SettingsTabViewModel_HiltModules.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/com/fintrack/ui/AppViewModel_Factory.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/com/fintrack/ui/onboarding/OnboardingViewModel_Factory.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/com/fintrack/ui/onboarding/OnboardingViewModel_HiltModules_BindsModule_Binds_LazyMapKey.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/com/fintrack/ui/onboarding/OnboardingViewModel_HiltModules_KeyModule_ProvideFactory.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/com/fintrack/ui/onboarding/OnboardingViewModel_HiltModules.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/com/fintrack/ui/onboarding/OnboardingViewModel_HiltModules_KeyModule_Provide_LazyMapKey.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/com/fintrack/ui/AppViewModel_HiltModules_BindsModule_Binds_LazyMapKey.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/com/fintrack/ui/celebration/CelebrationViewModel_Factory.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/com/fintrack/ui/celebration/CelebrationViewModel_HiltModules_KeyModule_Provide_LazyMapKey.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/com/fintrack/ui/celebration/CelebrationViewModel_HiltModules.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/com/fintrack/ui/celebration/CelebrationViewModel_HiltModules_BindsModule_Binds_LazyMapKey.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/com/fintrack/ui/celebration/CelebrationViewModel_HiltModules_KeyModule_ProvideFactory.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/com/fintrack/MainActivity_MembersInjector.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/com/fintrack/domain/UserScope_Factory.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/com/fintrack/di/DatabaseModule_ProvideMilestoneDaoFactory.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/com/fintrack/di/DatabaseModule_ProvideAimAllocationDaoFactory.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/com/fintrack/di/DatabaseModule_ProvideSubBucketDaoFactory.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/com/fintrack/di/DatabaseModule_ProvideLoanDaoFactory.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/com/fintrack/di/DatabaseModule_ProvideHoldingValueDaoFactory.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/com/fintrack/di/DatabaseModule_ProvideFintrackDatabaseFactory.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/com/fintrack/di/DatabaseModule_ProvideGlobalSettingsDaoFactory.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/com/fintrack/di/DatabaseModule_ProvideAssetClassDaoFactory.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/com/fintrack/di/DatabaseModule_ProvideGoalDaoFactory.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/com/fintrack/di/DatabaseModule_ProvideStreakStateDaoFactory.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/com/fintrack/di/DatabaseModule_ProvideHoldingDaoFactory.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/com/fintrack/di/DatabaseModule_ProvideLoanValueDaoFactory.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/com/fintrack/di/DatabaseModule_ProvideUserDaoFactory.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/com/fintrack/di/DatabaseModule_ProvideSnapshotDaoFactory.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/com/fintrack/Hilt_MainActivity.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/com/fintrack/FintrackApp_MembersInjector.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/com/fintrack/FintrackApp_GeneratedInjector.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/com/fintrack/security/BiometricAuthenticator_Factory.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/com/fintrack/security/KeystorePassphraseStore_Factory.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/com/fintrack/security/ClipboardAutoClear_Factory.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/com/fintrack/security/InactivityTracker_Factory.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/com/fintrack/data/repo/HoldingRepository_Factory.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/com/fintrack/data/repo/TaxonomyRepository_Factory.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/com/fintrack/data/repo/GoalRepository_Factory.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/com/fintrack/data/repo/UserRepository_Factory.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/com/fintrack/data/repo/SnapshotRepository_Factory.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/com/fintrack/data/repo/LoanRepository_Factory.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/com/fintrack/data/repo/GlobalSettingsRepository_Factory.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/com/fintrack/data/repo/AimAllocationRepository_Factory.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/com/fintrack/data/repo/MilestoneRepository_Factory.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/com/fintrack/data/repo/StreakRepository_Factory.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/com/fintrack/data/xlsx/XlsxImportApplier_Factory.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/com/fintrack/data/db/seed/DevSeedRunner_Factory.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/com/fintrack/data/backup/BackupRepository_Factory.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/hilt_aggregated_deps/_com_fintrack_ui_settings_excel_ExcelViewModel_HiltModules_KeyModule.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/hilt_aggregated_deps/_com_fintrack_ui_snapshots_entry_SnapshotEntryViewModel_HiltModules_BindsModule.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/hilt_aggregated_deps/_com_fintrack_ui_journey_goals_JourneyGoalsViewModel_HiltModules_KeyModule.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/hilt_aggregated_deps/_com_fintrack_ui_settings_aim_AimEditorViewModel_HiltModules_KeyModule.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/hilt_aggregated_deps/_com_fintrack_ui_settings_SettingsTabViewModel_HiltModules_BindsModule.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/hilt_aggregated_deps/_com_fintrack_ui_settings_loans_LoansManagementViewModel_HiltModules_KeyModule.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/hilt_aggregated_deps/_com_fintrack_ui_settings_backup_BackupViewModel_HiltModules_BindsModule.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/hilt_aggregated_deps/_com_fintrack_ui_celebration_CelebrationViewModel_HiltModules_BindsModule.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/hilt_aggregated_deps/_com_fintrack_ui_home_snapshots_SnapshotsListViewModel_HiltModules_BindsModule.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/hilt_aggregated_deps/_com_fintrack_ui_snapshots_entry_SnapshotEntryViewModel_HiltModules_KeyModule.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/hilt_aggregated_deps/_com_fintrack_ui_settings_SettingsTabViewModel_HiltModules_KeyModule.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/hilt_aggregated_deps/_com_fintrack_ui_home_HomeViewModel_HiltModules_KeyModule.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/hilt_aggregated_deps/_com_fintrack_ui_home_HomeViewModel_HiltModules_BindsModule.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/hilt_aggregated_deps/_com_fintrack_ui_onboarding_OnboardingViewModel_HiltModules_KeyModule.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/hilt_aggregated_deps/_com_fintrack_ui_AppViewModel_HiltModules_BindsModule.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/hilt_aggregated_deps/_com_fintrack_ui_home_overview_OverviewViewModel_HiltModules_BindsModule.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/hilt_aggregated_deps/_com_fintrack_ui_settings_goals_GoalsManagementViewModel_HiltModules_KeyModule.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/hilt_aggregated_deps/_com_fintrack_ui_settings_about_AboutViewModel_HiltModules_KeyModule.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/hilt_aggregated_deps/_com_fintrack_ui_snapshots_detail_SnapshotDetailViewModel_HiltModules_KeyModule.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/hilt_aggregated_deps/_com_fintrack_ui_settings_holdings_HoldingsManagementViewModel_HiltModules_KeyModule.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/hilt_aggregated_deps/_com_fintrack_ui_settings_aim_AimEditorViewModel_HiltModules_BindsModule.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/hilt_aggregated_deps/_com_fintrack_ui_celebration_CelebrationViewModel_HiltModules_KeyModule.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/hilt_aggregated_deps/_com_fintrack_ui_settings_goals_GoalsManagementViewModel_HiltModules_BindsModule.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/hilt_aggregated_deps/_com_fintrack_ui_home_overview_OverviewViewModel_HiltModules_KeyModule.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/hilt_aggregated_deps/_com_fintrack_ui_settings_about_AboutViewModel_HiltModules_BindsModule.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/hilt_aggregated_deps/_com_fintrack_ui_home_snapshots_SnapshotsListViewModel_HiltModules_KeyModule.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/hilt_aggregated_deps/_com_fintrack_ui_journey_goals_JourneyGoalsViewModel_HiltModules_BindsModule.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/hilt_aggregated_deps/_com_fintrack_ui_settings_excel_ExcelViewModel_HiltModules_BindsModule.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/hilt_aggregated_deps/_com_fintrack_ui_journey_wins_WinsTimelineViewModel_HiltModules_BindsModule.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/hilt_aggregated_deps/_com_fintrack_MainActivity_GeneratedInjector.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/hilt_aggregated_deps/_com_fintrack_ui_AppViewModel_HiltModules_KeyModule.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/hilt_aggregated_deps/_com_fintrack_FintrackApp_GeneratedInjector.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/hilt_aggregated_deps/_com_fintrack_ui_onboarding_OnboardingViewModel_HiltModules_BindsModule.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/hilt_aggregated_deps/_com_fintrack_ui_journey_wins_WinsTimelineViewModel_HiltModules_KeyModule.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/hilt_aggregated_deps/_com_fintrack_ui_settings_loans_LoansManagementViewModel_HiltModules_BindsModule.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/hilt_aggregated_deps/_com_fintrack_ui_picker_ProfilePickerViewModel_HiltModules_KeyModule.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/hilt_aggregated_deps/_com_fintrack_ui_settings_backup_BackupViewModel_HiltModules_KeyModule.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/hilt_aggregated_deps/_com_fintrack_ui_picker_ProfilePickerViewModel_HiltModules_BindsModule.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/hilt_aggregated_deps/_com_fintrack_ui_snapshots_detail_SnapshotDetailViewModel_HiltModules_BindsModule.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/hilt_aggregated_deps/_com_fintrack_ui_settings_holdings_HoldingsManagementViewModel_HiltModules_BindsModule.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/hilt_aggregated_deps/_com_fintrack_di_DatabaseModule.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/dagger/hilt/internal/aggregatedroot/codegen/_com_fintrack_FintrackApp.java, /home/runner/work/fintrack/fintrack/app/build/generated/source/buildConfig/debug/com/fintrack/BuildConfig.java
Script source files: 
Script file extensions: 
Using Kotlin/JVM incremental compilation
[KOTLIN] Kotlin compilation 'jdkHome' argument: null
Options for KOTLIN DAEMON: IncrementalCompilationOptions(super=CompilationOptions(compilerMode=INCREMENTAL_COMPILER, targetPlatform=JVM, reportCategories=[0, 3], reportSeverity=2, requestedCompilationResults=[0], kotlinScriptExtensions=[]), sourceChanges=org.jetbrains.kotlin.buildtools.api.SourcesChanges$Unknown@1173d9d2, classpathChanges=NotAvailableForNonIncrementalRun, workingDir=/home/runner/work/fintrack/fintrack/app/build/kotlin/compileDebugKotlin/cacheable, multiModuleICSettings=MultiModuleICSettings(buildHistoryFile=/home/runner/work/fintrack/fintrack/app/build/kotlin/compileDebugKotlin/local-state/build-history.bin, useModuleDetection=true), usePreciseJavaTracking=true, icFeatures=IncrementalCompilationFeatures(withAbiSnapshot=false, preciseCompilationResultsBackup=true, keepIncrementalCompilationCachesInMemory=true, enableUnsafeIncrementalCompilationForMultiplatform=false, enableMonotonousIncrementalCompileSetExpansion=true), outputFiles=[/home/runner/work/fintrack/fintrack/app/build/tmp/kotlin-classes/debug, /home/runner/work/fintrack/fintrack/app/build/kotlin/compileDebugKotlin/cacheable, /home/runner/work/fintrack/fintrack/app/build/kotlin/compileDebugKotlin/local-state])
w: file:///home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/di/DatabaseModule.kt:92:17 Type argument for reified type parameter 'T' was inferred to the intersection of ['Comparable<*>' & 'Serializable']. Reification of an intersection type results in the common supertype being used. This may lead to subtle issues and an explicit type argument is encouraged. This will become an error in a future release.
w: file:///home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/di/DatabaseModule.kt:101:17 Type argument for reified type parameter 'T' was inferred to the intersection of ['Comparable<*>' & 'Serializable']. Reification of an intersection type results in the common supertype being used. This may lead to subtle issues and an explicit type argument is encouraged. This will become an error in a future release.
w: file:///home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/di/DatabaseModule.kt:113:17 Type argument for reified type parameter 'T' was inferred to the intersection of ['Comparable<*>' & 'Serializable']. Reification of an intersection type results in the common supertype being used. This may lead to subtle issues and an explicit type argument is encouraged. This will become an error in a future release.
w: file:///home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/security/MoneyTextField.kt:42:27 'constructor(capitalization: KeyboardCapitalization = ..., autoCorrect: Boolean, keyboardType: KeyboardType = ..., imeAction: ImeAction = ..., platformImeOptions: PlatformImeOptions? = ..., showKeyboardOnFocus: Boolean? = ..., hintLocales: LocaleList? = ...): KeyboardOptions' is deprecated. Please use the new constructor that takes optional autoCorrectEnabled parameter.
w: file:///home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/security/PrivacyOverlay.kt:32:26 'val LocalLifecycleOwner: ProvidableCompositionLocal<LifecycleOwner>' is deprecated. Moved to lifecycle-runtime-compose library in androidx.lifecycle.compose package.
w: file:///home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/metrics/MetricInfo.kt:130:46 'val Icons.Outlined.HelpOutline: ImageVector' is deprecated. Use the AutoMirrored version at Icons.AutoMirrored.Outlined.HelpOutline.
w: file:///home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/onboarding/OnboardingPagerScreen.kt:102:29 'val Icons.Filled.TrendingUp: ImageVector' is deprecated. Use the AutoMirrored version at Icons.AutoMirrored.Filled.TrendingUp.
Finished executing kotlin compiler using DAEMON strategy
Stored cache entry for task ':app:compileDebugKotlin' with cache key 4cd2db2d37e68d368aff79c98792af8e
AAPT2 aapt2-8.7.3-12006047-linux Daemon #0: shutdown
gradle/actions: Writing build results to /home/runner/work/_temp/.gradle-actions/build-results/compile-1778059582710.json
Build 194afe8d-b22d-41e5-a73b-d7dbae3de763 is closed

BUILD SUCCESSFUL in 1m 14s
16 actionable tasks: 10 executed, 6 from cache
Build cache (/home/runner/.gradle/caches/build-cache-1) removing files not accessed on or after Wed Apr 29 09:27:32 UTC 2026.
Build cache (/home/runner/.gradle/caches/build-cache-1) cleanup deleted 0 files/directories.
Build cache (/home/runner/.gradle/caches/build-cache-1) cleaned up in 0.077 secs.
dependencies-accessors (/home/runner/.gradle/caches/8.11.1/dependencies-accessors) removing files not accessed on or after Wed Apr 29 09:27:32 UTC 2026.
dependencies-accessors (/home/runner/.gradle/caches/8.11.1/dependencies-accessors) cleanup deleted 0 files/directories.
dependencies-accessors (/home/runner/.gradle/caches/8.11.1/dependencies-accessors) cleaned up in 0.0 secs.
```

## assemble.log — error/warning lines

```
```

## assemble.log — full log (57 lines)

```
To honour the JVM settings for this build a single-use Daemon process will be forked. For more on this, please refer to https://docs.gradle.org/8.11.1/userguide/gradle_daemon.html#sec:disabling_the_daemon in the Gradle documentation.
Daemon will be stopped at the end of the build 
> Task :app:preBuild UP-TO-DATE
> Task :app:preDebugBuild UP-TO-DATE
> Task :app:mergeDebugNativeDebugMetadata NO-SOURCE
> Task :app:checkKotlinGradlePluginConfigurationErrors SKIPPED
> Task :app:generateDebugBuildConfig UP-TO-DATE
> Task :app:checkDebugAarMetadata UP-TO-DATE
> Task :app:generateDebugResValues UP-TO-DATE
> Task :app:mapDebugSourceSetPaths UP-TO-DATE
> Task :app:generateDebugResources UP-TO-DATE
> Task :app:mergeDebugResources UP-TO-DATE
> Task :app:packageDebugResources UP-TO-DATE
> Task :app:parseDebugLocalResources UP-TO-DATE
> Task :app:createDebugCompatibleScreenManifests UP-TO-DATE
> Task :app:extractDeepLinksDebug UP-TO-DATE
> Task :app:processDebugMainManifest UP-TO-DATE
> Task :app:processDebugManifest UP-TO-DATE
> Task :app:processDebugManifestForPackage UP-TO-DATE
> Task :app:processDebugResources UP-TO-DATE
> Task :app:kspDebugKotlin UP-TO-DATE
> Task :app:compileDebugKotlin UP-TO-DATE
> Task :app:javaPreCompileDebug FROM-CACHE
> Task :app:compileDebugJavaWithJavac
> Task :app:mergeDebugShaders
> Task :app:compileDebugShaders NO-SOURCE
> Task :app:generateDebugAssets UP-TO-DATE
> Task :app:mergeDebugAssets
> Task :app:compressDebugAssets FROM-CACHE
> Task :app:desugarDebugFileDependencies FROM-CACHE
> Task :app:hiltAggregateDepsDebug FROM-CACHE
> Task :app:hiltJavaCompileDebug
> Task :app:processDebugJavaRes
> Task :app:mergeDebugJniLibFolders
> Task :app:checkDebugDuplicateClasses
> Task :app:mergeDebugNativeLibs
> Task :app:mergeExtDexDebug FROM-CACHE
> Task :app:transformDebugClassesWithAsm
> Task :app:mergeLibDexDebug FROM-CACHE
> Task :app:mergeDebugJavaResource
> Task :app:dexBuilderDebug
> Task :app:mergeDebugGlobalSynthetics FROM-CACHE
> Task :app:validateSigningDebug
> Task :app:writeDebugAppMetadata
> Task :app:writeDebugSigningConfigVersions

> Task :app:stripDebugDebugSymbols
Unable to strip the following libraries, packaging them as they are: libandroidx.graphics.path.so, libsqlcipher.so.

> Task :app:mergeProjectDexDebug
> Task :app:packageDebug
> Task :app:createDebugApkListingFileRedirect
> Task :app:assembleDebug
gradle/actions: Writing build results to /home/runner/work/_temp/.gradle-actions/build-results/assemble-1778059656251.json

BUILD SUCCESSFUL in 33s
41 actionable tasks: 18 executed, 7 from cache, 16 up-to-date
```

## tests.log — error/warning lines

```
41:e: file:///home/runner/work/fintrack/fintrack/app/src/test/kotlin/com/fintrack/domain/goals/GoalAchievementDetectorTest.kt:188:21 Argument type mismatch: actual type is 'Unit', but 'List<GoalEntity>' was expected.
44:FAILURE: Build failed with an exception.
89:Caused by: org.gradle.workers.internal.DefaultWorkerExecutor$WorkExecutionException: A failure occurred while executing org.jetbrains.kotlin.compilerRunner.GradleCompilerRunnerWithWorkers$GradleKotlinCompilerWorkAction
218:Caused by: org.jetbrains.kotlin.gradle.tasks.CompilationErrorException: Compilation error. See log for more details
```

## tests.log — full log (252 lines)

```
To honour the JVM settings for this build a single-use Daemon process will be forked. For more on this, please refer to https://docs.gradle.org/8.11.1/userguide/gradle_daemon.html#sec:disabling_the_daemon in the Gradle documentation.
Daemon will be stopped at the end of the build 
> Task :app:preBuild UP-TO-DATE
> Task :app:preDebugBuild UP-TO-DATE
> Task :app:checkKotlinGradlePluginConfigurationErrors SKIPPED
> Task :app:generateDebugBuildConfig UP-TO-DATE
> Task :app:checkDebugAarMetadata UP-TO-DATE
> Task :app:generateDebugResValues UP-TO-DATE
> Task :app:mapDebugSourceSetPaths UP-TO-DATE
> Task :app:generateDebugResources UP-TO-DATE
> Task :app:mergeDebugResources UP-TO-DATE
> Task :app:packageDebugResources UP-TO-DATE
> Task :app:parseDebugLocalResources UP-TO-DATE
> Task :app:createDebugCompatibleScreenManifests UP-TO-DATE
> Task :app:extractDeepLinksDebug UP-TO-DATE
> Task :app:processDebugMainManifest UP-TO-DATE
> Task :app:processDebugManifest UP-TO-DATE
> Task :app:processDebugManifestForPackage UP-TO-DATE
> Task :app:processDebugResources UP-TO-DATE
> Task :app:kspDebugKotlin UP-TO-DATE
> Task :app:compileDebugKotlin UP-TO-DATE
> Task :app:javaPreCompileDebug UP-TO-DATE
> Task :app:compileDebugJavaWithJavac UP-TO-DATE
> Task :app:hiltAggregateDepsDebug UP-TO-DATE
> Task :app:hiltJavaCompileDebug UP-TO-DATE
> Task :app:transformDebugClassesWithAsm UP-TO-DATE
> Task :app:mergeDebugShaders UP-TO-DATE
> Task :app:compileDebugShaders NO-SOURCE
> Task :app:generateDebugAssets UP-TO-DATE
> Task :app:mergeDebugAssets UP-TO-DATE
> Task :app:preDebugUnitTestBuild UP-TO-DATE
> Task :app:bundleDebugClassesToRuntimeJar
> Task :app:packageDebugUnitTestForUnitTest
> Task :app:generateDebugUnitTestConfig
> Task :app:processDebugJavaRes UP-TO-DATE
> Task :app:javaPreCompileDebugUnitTest FROM-CACHE
> Task :app:bundleDebugClassesToCompileJar
> Task :app:kspDebugUnitTestKotlin

> Task :app:compileDebugUnitTestKotlin FAILED
e: file:///home/runner/work/fintrack/fintrack/app/src/test/kotlin/com/fintrack/domain/goals/GoalAchievementDetectorTest.kt:188:21 Argument type mismatch: actual type is 'Unit', but 'List<GoalEntity>' was expected.
gradle/actions: Writing build results to /home/runner/work/_temp/.gradle-actions/build-results/tests-1778059689224.json

FAILURE: Build failed with an exception.

* What went wrong:
Execution failed for task ':app:compileDebugUnitTestKotlin'.
> A failure occurred while executing org.jetbrains.kotlin.compilerRunner.GradleCompilerRunnerWithWorkers$GradleKotlinCompilerWorkAction
   > Compilation error. See log for more details

* Try:
> Run with --info or --debug option to get more log output.
> Run with --scan to get full insights.
> Get more help at https://help.gradle.org.

* Exception is:
org.gradle.api.tasks.TaskExecutionException: Execution failed for task ':app:compileDebugUnitTestKotlin'.
	at org.gradle.api.internal.tasks.execution.ExecuteActionsTaskExecuter.lambda$executeIfValid$1(ExecuteActionsTaskExecuter.java:130)
	at org.gradle.internal.Try$Failure.ifSuccessfulOrElse(Try.java:293)
	at org.gradle.api.internal.tasks.execution.ExecuteActionsTaskExecuter.executeIfValid(ExecuteActionsTaskExecuter.java:128)
	at org.gradle.api.internal.tasks.execution.ExecuteActionsTaskExecuter.execute(ExecuteActionsTaskExecuter.java:116)
	at org.gradle.api.internal.tasks.execution.FinalizePropertiesTaskExecuter.execute(FinalizePropertiesTaskExecuter.java:46)
	at org.gradle.api.internal.tasks.execution.ResolveTaskExecutionModeExecuter.execute(ResolveTaskExecutionModeExecuter.java:51)
	at org.gradle.api.internal.tasks.execution.SkipTaskWithNoActionsExecuter.execute(SkipTaskWithNoActionsExecuter.java:57)
	at org.gradle.api.internal.tasks.execution.SkipOnlyIfTaskExecuter.execute(SkipOnlyIfTaskExecuter.java:74)
	at org.gradle.api.internal.tasks.execution.CatchExceptionTaskExecuter.execute(CatchExceptionTaskExecuter.java:36)
	at org.gradle.api.internal.tasks.execution.EventFiringTaskExecuter$1.executeTask(EventFiringTaskExecuter.java:77)
	at org.gradle.api.internal.tasks.execution.EventFiringTaskExecuter$1.call(EventFiringTaskExecuter.java:55)
	at org.gradle.api.internal.tasks.execution.EventFiringTaskExecuter$1.call(EventFiringTaskExecuter.java:52)
	at org.gradle.internal.operations.DefaultBuildOperationRunner$CallableBuildOperationWorker.execute(DefaultBuildOperationRunner.java:209)
	at org.gradle.internal.operations.DefaultBuildOperationRunner$CallableBuildOperationWorker.execute(DefaultBuildOperationRunner.java:204)
	at org.gradle.internal.operations.DefaultBuildOperationRunner$2.execute(DefaultBuildOperationRunner.java:66)
	at org.gradle.internal.operations.DefaultBuildOperationRunner$2.execute(DefaultBuildOperationRunner.java:59)
	at org.gradle.internal.operations.DefaultBuildOperationRunner.execute(DefaultBuildOperationRunner.java:166)
	at org.gradle.internal.operations.DefaultBuildOperationRunner.execute(DefaultBuildOperationRunner.java:59)
	at org.gradle.internal.operations.DefaultBuildOperationRunner.call(DefaultBuildOperationRunner.java:53)
	at org.gradle.api.internal.tasks.execution.EventFiringTaskExecuter.execute(EventFiringTaskExecuter.java:52)
	at org.gradle.execution.plan.LocalTaskNodeExecutor.execute(LocalTaskNodeExecutor.java:42)
	at org.gradle.execution.taskgraph.DefaultTaskExecutionGraph$InvokeNodeExecutorsAction.execute(DefaultTaskExecutionGraph.java:331)
	at org.gradle.execution.taskgraph.DefaultTaskExecutionGraph$InvokeNodeExecutorsAction.execute(DefaultTaskExecutionGraph.java:318)
	at org.gradle.execution.taskgraph.DefaultTaskExecutionGraph$BuildOperationAwareExecutionAction.lambda$execute$0(DefaultTaskExecutionGraph.java:314)
	at org.gradle.internal.operations.CurrentBuildOperationRef.with(CurrentBuildOperationRef.java:85)
	at org.gradle.execution.taskgraph.DefaultTaskExecutionGraph$BuildOperationAwareExecutionAction.execute(DefaultTaskExecutionGraph.java:314)
	at org.gradle.execution.taskgraph.DefaultTaskExecutionGraph$BuildOperationAwareExecutionAction.execute(DefaultTaskExecutionGraph.java:303)
	at org.gradle.execution.plan.DefaultPlanExecutor$ExecutorWorker.execute(DefaultPlanExecutor.java:459)
	at org.gradle.execution.plan.DefaultPlanExecutor$ExecutorWorker.run(DefaultPlanExecutor.java:376)
	at org.gradle.internal.concurrent.ExecutorPolicy$CatchAndRecordFailures.onExecute(ExecutorPolicy.java:64)
	at org.gradle.internal.concurrent.AbstractManagedExecutor$1.run(AbstractManagedExecutor.java:48)
Caused by: org.gradle.workers.internal.DefaultWorkerExecutor$WorkExecutionException: A failure occurred while executing org.jetbrains.kotlin.compilerRunner.GradleCompilerRunnerWithWorkers$GradleKotlinCompilerWorkAction
	at org.gradle.workers.internal.DefaultWorkerExecutor$WorkItemExecution.waitForCompletion(DefaultWorkerExecutor.java:287)
	at org.gradle.internal.work.DefaultAsyncWorkTracker.lambda$waitForItemsAndGatherFailures$2(DefaultAsyncWorkTracker.java:130)
	at org.gradle.internal.Factories$1.create(Factories.java:31)
	at org.gradle.internal.work.DefaultWorkerLeaseService.withoutLocks(DefaultWorkerLeaseService.java:335)
	at org.gradle.internal.work.DefaultWorkerLeaseService.withoutLocks(DefaultWorkerLeaseService.java:318)
	at org.gradle.internal.work.DefaultWorkerLeaseService.withoutLock(DefaultWorkerLeaseService.java:323)
	at org.gradle.internal.work.DefaultAsyncWorkTracker.waitForItemsAndGatherFailures(DefaultAsyncWorkTracker.java:126)
	at org.gradle.internal.work.DefaultAsyncWorkTracker.waitForItemsAndGatherFailures(DefaultAsyncWorkTracker.java:92)
	at org.gradle.internal.work.DefaultAsyncWorkTracker.waitForAll(DefaultAsyncWorkTracker.java:78)
	at org.gradle.internal.work.DefaultAsyncWorkTracker.waitForCompletion(DefaultAsyncWorkTracker.java:66)
	at org.gradle.api.internal.tasks.execution.TaskExecution$3.run(TaskExecution.java:252)
	at org.gradle.internal.operations.DefaultBuildOperationRunner$1.execute(DefaultBuildOperationRunner.java:29)
	at org.gradle.internal.operations.DefaultBuildOperationRunner$1.execute(DefaultBuildOperationRunner.java:26)
	at org.gradle.internal.operations.DefaultBuildOperationRunner$2.execute(DefaultBuildOperationRunner.java:66)
	at org.gradle.internal.operations.DefaultBuildOperationRunner$2.execute(DefaultBuildOperationRunner.java:59)
	at org.gradle.internal.operations.DefaultBuildOperationRunner.execute(DefaultBuildOperationRunner.java:166)
	at org.gradle.internal.operations.DefaultBuildOperationRunner.execute(DefaultBuildOperationRunner.java:59)
	at org.gradle.internal.operations.DefaultBuildOperationRunner.run(DefaultBuildOperationRunner.java:47)
	at org.gradle.api.internal.tasks.execution.TaskExecution.executeAction(TaskExecution.java:229)
	at org.gradle.api.internal.tasks.execution.TaskExecution.executeActions(TaskExecution.java:212)
	at org.gradle.api.internal.tasks.execution.TaskExecution.executeWithPreviousOutputFiles(TaskExecution.java:195)
	at org.gradle.api.internal.tasks.execution.TaskExecution.execute(TaskExecution.java:162)
	at org.gradle.internal.execution.steps.ExecuteStep.executeInternal(ExecuteStep.java:105)
	at org.gradle.internal.execution.steps.ExecuteStep.access$000(ExecuteStep.java:44)
	at org.gradle.internal.execution.steps.ExecuteStep$1.call(ExecuteStep.java:59)
	at org.gradle.internal.execution.steps.ExecuteStep$1.call(ExecuteStep.java:56)
	at org.gradle.internal.operations.DefaultBuildOperationRunner$CallableBuildOperationWorker.execute(DefaultBuildOperationRunner.java:209)
	at org.gradle.internal.operations.DefaultBuildOperationRunner$CallableBuildOperationWorker.execute(DefaultBuildOperationRunner.java:204)
	at org.gradle.internal.operations.DefaultBuildOperationRunner$2.execute(DefaultBuildOperationRunner.java:66)
	at org.gradle.internal.operations.DefaultBuildOperationRunner$2.execute(DefaultBuildOperationRunner.java:59)
	at org.gradle.internal.operations.DefaultBuildOperationRunner.execute(DefaultBuildOperationRunner.java:166)
	at org.gradle.internal.operations.DefaultBuildOperationRunner.execute(DefaultBuildOperationRunner.java:59)
	at org.gradle.internal.operations.DefaultBuildOperationRunner.call(DefaultBuildOperationRunner.java:53)
	at org.gradle.internal.execution.steps.ExecuteStep.execute(ExecuteStep.java:56)
	at org.gradle.internal.execution.steps.ExecuteStep.execute(ExecuteStep.java:44)
	at org.gradle.internal.execution.steps.CancelExecutionStep.execute(CancelExecutionStep.java:42)
	at org.gradle.internal.execution.steps.TimeoutStep.executeWithoutTimeout(TimeoutStep.java:75)
	at org.gradle.internal.execution.steps.TimeoutStep.execute(TimeoutStep.java:55)
	at org.gradle.internal.execution.steps.PreCreateOutputParentsStep.execute(PreCreateOutputParentsStep.java:50)
	at org.gradle.internal.execution.steps.PreCreateOutputParentsStep.execute(PreCreateOutputParentsStep.java:28)
	at org.gradle.internal.execution.steps.RemovePreviousOutputsStep.execute(RemovePreviousOutputsStep.java:67)
	at org.gradle.internal.execution.steps.RemovePreviousOutputsStep.execute(RemovePreviousOutputsStep.java:37)
	at org.gradle.internal.execution.steps.BroadcastChangingOutputsStep.execute(BroadcastChangingOutputsStep.java:61)
	at org.gradle.internal.execution.steps.BroadcastChangingOutputsStep.execute(BroadcastChangingOutputsStep.java:26)
	at org.gradle.internal.execution.steps.CaptureOutputsAfterExecutionStep.execute(CaptureOutputsAfterExecutionStep.java:69)
	at org.gradle.internal.execution.steps.CaptureOutputsAfterExecutionStep.execute(CaptureOutputsAfterExecutionStep.java:46)
	at org.gradle.internal.execution.steps.ResolveInputChangesStep.execute(ResolveInputChangesStep.java:40)
	at org.gradle.internal.execution.steps.ResolveInputChangesStep.execute(ResolveInputChangesStep.java:29)
	at org.gradle.internal.execution.steps.BuildCacheStep.executeWithoutCache(BuildCacheStep.java:189)
	at org.gradle.internal.execution.steps.BuildCacheStep.executeAndStoreInCache(BuildCacheStep.java:145)
	at org.gradle.internal.execution.steps.BuildCacheStep.lambda$executeWithCache$4(BuildCacheStep.java:101)
	at org.gradle.internal.execution.steps.BuildCacheStep.lambda$executeWithCache$5(BuildCacheStep.java:101)
	at org.gradle.internal.Try$Success.map(Try.java:175)
	at org.gradle.internal.execution.steps.BuildCacheStep.executeWithCache(BuildCacheStep.java:85)
	at org.gradle.internal.execution.steps.BuildCacheStep.lambda$execute$0(BuildCacheStep.java:74)
	at org.gradle.internal.Either$Left.fold(Either.java:115)
	at org.gradle.internal.execution.caching.CachingState.fold(CachingState.java:62)
	at org.gradle.internal.execution.steps.BuildCacheStep.execute(BuildCacheStep.java:73)
	at org.gradle.internal.execution.steps.BuildCacheStep.execute(BuildCacheStep.java:48)
	at org.gradle.internal.execution.steps.StoreExecutionStateStep.execute(StoreExecutionStateStep.java:46)
	at org.gradle.internal.execution.steps.StoreExecutionStateStep.execute(StoreExecutionStateStep.java:35)
	at org.gradle.internal.execution.steps.SkipUpToDateStep.executeBecause(SkipUpToDateStep.java:75)
	at org.gradle.internal.execution.steps.SkipUpToDateStep.lambda$execute$2(SkipUpToDateStep.java:53)
	at org.gradle.internal.execution.steps.SkipUpToDateStep.execute(SkipUpToDateStep.java:53)
	at org.gradle.internal.execution.steps.SkipUpToDateStep.execute(SkipUpToDateStep.java:35)
	at org.gradle.internal.execution.steps.legacy.MarkSnapshottingInputsFinishedStep.execute(MarkSnapshottingInputsFinishedStep.java:37)
	at org.gradle.internal.execution.steps.legacy.MarkSnapshottingInputsFinishedStep.execute(MarkSnapshottingInputsFinishedStep.java:27)
	at org.gradle.internal.execution.steps.ResolveIncrementalCachingStateStep.executeDelegate(ResolveIncrementalCachingStateStep.java:49)
	at org.gradle.internal.execution.steps.ResolveIncrementalCachingStateStep.executeDelegate(ResolveIncrementalCachingStateStep.java:27)
	at org.gradle.internal.execution.steps.AbstractResolveCachingStateStep.execute(AbstractResolveCachingStateStep.java:71)
	at org.gradle.internal.execution.steps.AbstractResolveCachingStateStep.execute(AbstractResolveCachingStateStep.java:39)
	at org.gradle.internal.execution.steps.ResolveChangesStep.execute(ResolveChangesStep.java:65)
	at org.gradle.internal.execution.steps.ResolveChangesStep.execute(ResolveChangesStep.java:36)
	at org.gradle.internal.execution.steps.ValidateStep.execute(ValidateStep.java:107)
	at org.gradle.internal.execution.steps.ValidateStep.execute(ValidateStep.java:56)
	at org.gradle.internal.execution.steps.AbstractCaptureStateBeforeExecutionStep.execute(AbstractCaptureStateBeforeExecutionStep.java:64)
	at org.gradle.internal.execution.steps.AbstractCaptureStateBeforeExecutionStep.execute(AbstractCaptureStateBeforeExecutionStep.java:43)
	at org.gradle.internal.execution.steps.AbstractSkipEmptyWorkStep.executeWithNonEmptySources(AbstractSkipEmptyWorkStep.java:125)
	at org.gradle.internal.execution.steps.AbstractSkipEmptyWorkStep.execute(AbstractSkipEmptyWorkStep.java:61)
	at org.gradle.internal.execution.steps.AbstractSkipEmptyWorkStep.execute(AbstractSkipEmptyWorkStep.java:36)
	at org.gradle.internal.execution.steps.legacy.MarkSnapshottingInputsStartedStep.execute(MarkSnapshottingInputsStartedStep.java:38)
	at org.gradle.internal.execution.steps.LoadPreviousExecutionStateStep.execute(LoadPreviousExecutionStateStep.java:36)
	at org.gradle.internal.execution.steps.LoadPreviousExecutionStateStep.execute(LoadPreviousExecutionStateStep.java:23)
	at org.gradle.internal.execution.steps.HandleStaleOutputsStep.execute(HandleStaleOutputsStep.java:75)
	at org.gradle.internal.execution.steps.HandleStaleOutputsStep.execute(HandleStaleOutputsStep.java:41)
	at org.gradle.internal.execution.steps.AssignMutableWorkspaceStep.lambda$execute$0(AssignMutableWorkspaceStep.java:35)
	at org.gradle.api.internal.tasks.execution.TaskExecution$4.withWorkspace(TaskExecution.java:289)
	at org.gradle.internal.execution.steps.AssignMutableWorkspaceStep.execute(AssignMutableWorkspaceStep.java:31)
	at org.gradle.internal.execution.steps.AssignMutableWorkspaceStep.execute(AssignMutableWorkspaceStep.java:22)
	at org.gradle.internal.execution.steps.ChoosePipelineStep.execute(ChoosePipelineStep.java:40)
	at org.gradle.internal.execution.steps.ChoosePipelineStep.execute(ChoosePipelineStep.java:23)
	at org.gradle.internal.execution.steps.ExecuteWorkBuildOperationFiringStep.lambda$execute$2(ExecuteWorkBuildOperationFiringStep.java:67)
	at org.gradle.internal.execution.steps.ExecuteWorkBuildOperationFiringStep.execute(ExecuteWorkBuildOperationFiringStep.java:67)
	at org.gradle.internal.execution.steps.ExecuteWorkBuildOperationFiringStep.execute(ExecuteWorkBuildOperationFiringStep.java:39)
	at org.gradle.internal.execution.steps.IdentityCacheStep.execute(IdentityCacheStep.java:46)
	at org.gradle.internal.execution.steps.IdentityCacheStep.execute(IdentityCacheStep.java:34)
	at org.gradle.internal.execution.steps.IdentifyStep.execute(IdentifyStep.java:48)
	at org.gradle.internal.execution.steps.IdentifyStep.execute(IdentifyStep.java:35)
	at org.gradle.internal.execution.impl.DefaultExecutionEngine$1.execute(DefaultExecutionEngine.java:61)
	at org.gradle.api.internal.tasks.execution.ExecuteActionsTaskExecuter.executeIfValid(ExecuteActionsTaskExecuter.java:127)
	at org.gradle.api.internal.tasks.execution.ExecuteActionsTaskExecuter.execute(ExecuteActionsTaskExecuter.java:116)
	at org.gradle.api.internal.tasks.execution.FinalizePropertiesTaskExecuter.execute(FinalizePropertiesTaskExecuter.java:46)
	at org.gradle.api.internal.tasks.execution.ResolveTaskExecutionModeExecuter.execute(ResolveTaskExecutionModeExecuter.java:51)
	at org.gradle.api.internal.tasks.execution.SkipTaskWithNoActionsExecuter.execute(SkipTaskWithNoActionsExecuter.java:57)
	at org.gradle.api.internal.tasks.execution.SkipOnlyIfTaskExecuter.execute(SkipOnlyIfTaskExecuter.java:74)
	at org.gradle.api.internal.tasks.execution.CatchExceptionTaskExecuter.execute(CatchExceptionTaskExecuter.java:36)
	at org.gradle.api.internal.tasks.execution.EventFiringTaskExecuter$1.executeTask(EventFiringTaskExecuter.java:77)
	at org.gradle.api.internal.tasks.execution.EventFiringTaskExecuter$1.call(EventFiringTaskExecuter.java:55)
	at org.gradle.api.internal.tasks.execution.EventFiringTaskExecuter$1.call(EventFiringTaskExecuter.java:52)
	at org.gradle.internal.operations.DefaultBuildOperationRunner$CallableBuildOperationWorker.execute(DefaultBuildOperationRunner.java:209)
	at org.gradle.internal.operations.DefaultBuildOperationRunner$CallableBuildOperationWorker.execute(DefaultBuildOperationRunner.java:204)
	at org.gradle.internal.operations.DefaultBuildOperationRunner$2.execute(DefaultBuildOperationRunner.java:66)
	at org.gradle.internal.operations.DefaultBuildOperationRunner$2.execute(DefaultBuildOperationRunner.java:59)
	at org.gradle.internal.operations.DefaultBuildOperationRunner.execute(DefaultBuildOperationRunner.java:166)
	at org.gradle.internal.operations.DefaultBuildOperationRunner.execute(DefaultBuildOperationRunner.java:59)
	at org.gradle.internal.operations.DefaultBuildOperationRunner.call(DefaultBuildOperationRunner.java:53)
	at org.gradle.api.internal.tasks.execution.EventFiringTaskExecuter.execute(EventFiringTaskExecuter.java:52)
	at org.gradle.execution.plan.LocalTaskNodeExecutor.execute(LocalTaskNodeExecutor.java:42)
	at org.gradle.execution.taskgraph.DefaultTaskExecutionGraph$InvokeNodeExecutorsAction.execute(DefaultTaskExecutionGraph.java:331)
	at org.gradle.execution.taskgraph.DefaultTaskExecutionGraph$InvokeNodeExecutorsAction.execute(DefaultTaskExecutionGraph.java:318)
	at org.gradle.execution.taskgraph.DefaultTaskExecutionGraph$BuildOperationAwareExecutionAction.lambda$execute$0(DefaultTaskExecutionGraph.java:314)
	at org.gradle.internal.operations.CurrentBuildOperationRef.with(CurrentBuildOperationRef.java:85)
	at org.gradle.execution.taskgraph.DefaultTaskExecutionGraph$BuildOperationAwareExecutionAction.execute(DefaultTaskExecutionGraph.java:314)
	at org.gradle.execution.taskgraph.DefaultTaskExecutionGraph$BuildOperationAwareExecutionAction.execute(DefaultTaskExecutionGraph.java:303)
	at org.gradle.execution.plan.DefaultPlanExecutor$ExecutorWorker.execute(DefaultPlanExecutor.java:459)
	at org.gradle.execution.plan.DefaultPlanExecutor$ExecutorWorker.run(DefaultPlanExecutor.java:376)
	at org.gradle.internal.concurrent.ExecutorPolicy$CatchAndRecordFailures.onExecute(ExecutorPolicy.java:64)
	at org.gradle.internal.concurrent.AbstractManagedExecutor$1.run(AbstractManagedExecutor.java:48)
Caused by: org.jetbrains.kotlin.gradle.tasks.CompilationErrorException: Compilation error. See log for more details
	at org.jetbrains.kotlin.gradle.tasks.TasksUtilsKt.throwExceptionIfCompilationFailed(tasksUtils.kt:21)
	at org.jetbrains.kotlin.compilerRunner.GradleKotlinCompilerWork.run(GradleKotlinCompilerWork.kt:119)
	at org.jetbrains.kotlin.compilerRunner.GradleCompilerRunnerWithWorkers$GradleKotlinCompilerWorkAction.execute(GradleCompilerRunnerWithWorkers.kt:76)
	at org.gradle.workers.internal.DefaultWorkerServer.execute(DefaultWorkerServer.java:63)
	at org.gradle.workers.internal.NoIsolationWorkerFactory$1$1.create(NoIsolationWorkerFactory.java:66)
	at org.gradle.workers.internal.NoIsolationWorkerFactory$1$1.create(NoIsolationWorkerFactory.java:62)
	at org.gradle.internal.classloader.ClassLoaderUtils.executeInClassloader(ClassLoaderUtils.java:100)
	at org.gradle.workers.internal.NoIsolationWorkerFactory$1.lambda$execute$0(NoIsolationWorkerFactory.java:62)
	at org.gradle.workers.internal.AbstractWorker$1.call(AbstractWorker.java:44)
	at org.gradle.workers.internal.AbstractWorker$1.call(AbstractWorker.java:41)
	at org.gradle.internal.operations.DefaultBuildOperationRunner$CallableBuildOperationWorker.execute(DefaultBuildOperationRunner.java:209)
	at org.gradle.internal.operations.DefaultBuildOperationRunner$CallableBuildOperationWorker.execute(DefaultBuildOperationRunner.java:204)
	at org.gradle.internal.operations.DefaultBuildOperationRunner$2.execute(DefaultBuildOperationRunner.java:66)
	at org.gradle.internal.operations.DefaultBuildOperationRunner$2.execute(DefaultBuildOperationRunner.java:59)
	at org.gradle.internal.operations.DefaultBuildOperationRunner.execute(DefaultBuildOperationRunner.java:166)
	at org.gradle.internal.operations.DefaultBuildOperationRunner.execute(DefaultBuildOperationRunner.java:59)
	at org.gradle.internal.operations.DefaultBuildOperationRunner.call(DefaultBuildOperationRunner.java:53)
	at org.gradle.workers.internal.AbstractWorker.executeWrappedInBuildOperation(AbstractWorker.java:41)
	at org.gradle.workers.internal.NoIsolationWorkerFactory$1.execute(NoIsolationWorkerFactory.java:59)
	at org.gradle.workers.internal.DefaultWorkerExecutor.lambda$submitWork$0(DefaultWorkerExecutor.java:174)
	at org.gradle.internal.work.DefaultConditionalExecutionQueue$ExecutionRunner.runExecution(DefaultConditionalExecutionQueue.java:194)
	at org.gradle.internal.work.DefaultConditionalExecutionQueue$ExecutionRunner.access$700(DefaultConditionalExecutionQueue.java:127)
	at org.gradle.internal.work.DefaultConditionalExecutionQueue$ExecutionRunner$1.run(DefaultConditionalExecutionQueue.java:169)
	at org.gradle.internal.Factories$1.create(Factories.java:31)
	at org.gradle.internal.work.DefaultWorkerLeaseService.withLocks(DefaultWorkerLeaseService.java:263)
	at org.gradle.internal.work.DefaultWorkerLeaseService.runAsWorkerThread(DefaultWorkerLeaseService.java:127)
	at org.gradle.internal.work.DefaultWorkerLeaseService.runAsWorkerThread(DefaultWorkerLeaseService.java:132)
	at org.gradle.internal.work.DefaultConditionalExecutionQueue$ExecutionRunner.runBatch(DefaultConditionalExecutionQueue.java:164)
	at org.gradle.internal.work.DefaultConditionalExecutionQueue$ExecutionRunner.run(DefaultConditionalExecutionQueue.java:133)
	... 2 more


BUILD FAILED in 22s
31 actionable tasks: 6 executed, 1 from cache, 24 up-to-date
```

## detekt.log — error/warning lines

```
2398:FAILURE: Build failed with an exception.
2442:Caused by: org.gradle.api.GradleException: Analysis failed with 771 weighted issues.
2570:Caused by: java.lang.reflect.InvocationTargetException
2576:Caused by: io.github.detekt.tooling.api.MaxIssuesReached: Analysis failed with 771 weighted issues.
```

## detekt.log — full log (2589 lines)

```
[truncated to first 200 + last 400 lines]
To honour the JVM settings for this build a single-use Daemon process will be forked. For more on this, please refer to https://docs.gradle.org/8.11.1/userguide/gradle_daemon.html#sec:disabling_the_daemon in the Gradle documentation.
Daemon will be stopped at the end of the build 

> Task :detekt
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/snapshots/entry/SnapshotEntryScreen.kt:142:30: The function SnapshotEntryForm(state: SnapshotEntryUiState, onSetDate: (LocalDate) -> Unit, onSetEarnings: (String) -> Unit, onSetInvested: (UUID, String) -> Unit, onSetCurrent: (UUID, String) -> Unit, onSetSip: (UUID, String) -> Unit, onSetNotes: (String) -> Unit, onSetLoanOutstanding: (UUID, String) -> Unit, onAddLoan: (String, BigDecimal, LocalDate, BigDecimal) -> Unit, onShowEarningsHelp: () -> Unit, modifier: Modifier) has too many parameters. The current threshold is set to 8. [LongParameterList]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/snapshots/entry/SnapshotEntryScreen.kt:142:13: The function SnapshotEntryForm is too long (120). The maximum length is 80. [LongMethod]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/snapshots/detail/SnapshotDetailScreen.kt:1:1: File '/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/snapshots/detail/SnapshotDetailScreen.kt' with '13' functions detected. Defined threshold inside files is set to '11' [TooManyFunctions]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/navigation/AppNavGraph.kt:87:13: The function AuthenticatedNavHost is too long (108). The maximum length is 80. [LongMethod]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/home/snapshots/SnapshotsTab.kt:108:13: The function SnapshotRow is too long (160). The maximum length is 80. [LongMethod]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/home/overview/LineChart.kt:46:5: The function LineChart is too long (139). The maximum length is 80. [LongMethod]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/home/overview/LineChart.kt:46:5: The function LineChart appears to be too complex based on Cyclomatic Complexity (complexity: 21). Defined complexity threshold for methods is set to '15' [CyclomaticComplexMethod]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/home/overview/OverviewTab.kt:109:26: The function ChartsContent(analytics: List<SnapshotAnalytics>, history: List<HistoryRow>, period: Period, chartView: ChartView, goals: List<com.fintrack.domain.goals.GoalProgress>, selectedGoalId: UUID?, onSelectGoal: (UUID?) -> Unit, onPeriod: (Period) -> Unit, onChartView: (ChartView) -> Unit, onSnapshotDetail: (UUID) -> Unit) has too many parameters. The current threshold is set to 8. [LongParameterList]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/home/overview/OverviewTab.kt:1:1: File '/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/home/overview/OverviewTab.kt' with '12' functions detected. Defined threshold inside files is set to '11' [TooManyFunctions]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/home/HomeScreen.kt:85:14: The function HomeRoute(onSwitchUser: () -> Unit, onNewSnapshot: () -> Unit, onSnapshotDetail: (UUID) -> Unit, onEditSnapshot: (UUID) -> Unit, onAimEditor: () -> Unit, onHoldings: () -> Unit, onLoans: () -> Unit, onGoals: () -> Unit, onManageUsers: () -> Unit, onBackup: () -> Unit, onExcel: () -> Unit, onAbout: () -> Unit, onNavigateToAbout: (anchor: String) -> Unit, viewModel: HomeViewModel) has too many parameters. The current threshold is set to 8. [LongParameterList]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/home/HomeScreen.kt:85:5: The function HomeRoute is too long (81). The maximum length is 80. [LongMethod]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/settings/excel/ExcelScreen.kt:48:5: The function ExcelRoute is too long (93). The maximum length is 80. [LongMethod]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/settings/goals/GoalsManagementScreen.kt:240:13: The function GoalFormDialog is too long (90). The maximum length is 80. [LongMethod]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/settings/goals/GoalsManagementScreen.kt:66:5: The function GoalsManagementRoute is too long (111). The maximum length is 80. [LongMethod]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/settings/users/ManageUsersScreen.kt:57:5: The function ManageUsersRoute is too long (96). The maximum length is 80. [LongMethod]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/settings/loans/LoansManagementScreen.kt:62:5: The function LoansManagementRoute is too long (134). The maximum length is 80. [LongMethod]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/settings/about/AboutScreen.kt:73:5: The function AboutRoute is too long (101). The maximum length is 80. [LongMethod]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/settings/about/AboutScreen.kt:1:1: File '/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/settings/about/AboutScreen.kt' with '17' functions detected. Defined threshold inside files is set to '11' [TooManyFunctions]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/settings/backup/BackupScreen.kt:187:5: The function BackupRoute is too long (177). The maximum length is 80. [LongMethod]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/settings/SettingsTab.kt:40:16: The function SettingsTab(onAimEditor: () -> Unit, onHoldings: () -> Unit, onLoans: () -> Unit, onGoals: () -> Unit, onManageUsers: () -> Unit, onBackup: () -> Unit, onExcel: () -> Unit, onAbout: () -> Unit, viewModel: SettingsTabViewModel) has too many parameters. The current threshold is set to 8. [LongParameterList]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/settings/holdings/HoldingsManagementScreen.kt:492:27: The function AssetClassCard(section: HoldingsSection, expanded: Boolean, onToggleExpanded: () -> Unit, onMoveUp: () -> Unit, onMoveDown: () -> Unit, onToggleActive: (Boolean) -> Unit, onRename: () -> Unit, onAddSubBucket: () -> Unit, onSubBucketMoveUp: (UUID) -> Unit, onSubBucketMoveDown: (UUID) -> Unit, onSubBucketToggleActive: (UUID, Boolean) -> Unit, onSubBucketRename: (SubBucketEntity) -> Unit, onSubBucketAddHolding: (UUID) -> Unit, onHoldingMoveUp: (UUID, UUID) -> Unit, onHoldingMoveDown: (UUID, UUID) -> Unit, onHoldingToggleActive: (UUID, Boolean) -> Unit, onHoldingRename: (HoldingEntity) -> Unit, onHoldingDelete: (HoldingEntity) -> Unit) has too many parameters. The current threshold is set to 8. [LongParameterList]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/settings/holdings/HoldingsManagementScreen.kt:574:27: The function SubBucketBlock(bucket: HoldingsBucket, onMoveUp: () -> Unit, onMoveDown: () -> Unit, onToggleActive: (Boolean) -> Unit, onRename: () -> Unit, onAddHolding: () -> Unit, onHoldingMoveUp: (UUID) -> Unit, onHoldingMoveDown: (UUID) -> Unit, onHoldingToggleActive: (UUID, Boolean) -> Unit, onHoldingRename: (HoldingEntity) -> Unit, onHoldingDelete: (HoldingEntity) -> Unit) has too many parameters. The current threshold is set to 8. [LongParameterList]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/settings/holdings/HoldingsManagementScreen.kt:287:5: The function HoldingsManagementRoute is too long (141). The maximum length is 80. [LongMethod]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/onboarding/CreateFirstProfileScreen.kt:73:5: The function CreateFirstProfileRoute is too long (91). The maximum length is 80. [LongMethod]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/onboarding/OnboardingPagerScreen.kt:114:13: The function OnboardingPager is too long (84). The maximum length is 80. [LongMethod]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/domain/milestones/MilestoneDetector.kt:42:9: The function detect is too long (101). The maximum length is 80. [LongMethod]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/domain/milestones/MilestoneDetector.kt:42:9: The function detect appears to be too complex based on Cyclomatic Complexity (complexity: 34). Defined complexity threshold for methods is set to '15' [CyclomaticComplexMethod]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/domain/goals/GoalCalculator.kt:40:9: The function progress appears to be too complex based on Cyclomatic Complexity (complexity: 15). Defined complexity threshold for methods is set to '15' [CyclomaticComplexMethod]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/domain/analytics/SnapshotAnalytics.kt:115:16: The function compute(snapshot: SnapshotEntity, values: List<HoldingValueEntity>, catalog: List<HoldingEntity>, subBuckets: List<SubBucketEntity>, assetClasses: List<AssetClassEntity>, aimAllocations: List<AimAllocationEntity>, loans: List<LoanEntity>, loanValues: List<LoanValueEntity>, previousNetWorth: BigDecimal?) has too many parameters. The current threshold is set to 8. [LongParameterList]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/domain/analytics/SnapshotAnalytics.kt:115:9: The function compute is too long (108). The maximum length is 80. [LongMethod]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/domain/analytics/SnapshotAnalytics.kt:115:9: The function compute appears to be too complex based on Cyclomatic Complexity (complexity: 17). Defined complexity threshold for methods is set to '15' [CyclomaticComplexMethod]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/data/xlsx/XlsxImportApplier.kt:57:17: The function apply is too long (158). The maximum length is 80. [LongMethod]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/data/xlsx/XlsxImportApplier.kt:57:17: The function apply appears to be too complex based on Cyclomatic Complexity (complexity: 31). Defined complexity threshold for methods is set to '15' [CyclomaticComplexMethod]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/data/backup/CsvCodec.kt:21:9: The function parse appears to be too complex based on Cyclomatic Complexity (complexity: 16). Defined complexity threshold for methods is set to '15' [CyclomaticComplexMethod]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/data/backup/CsvCodec.kt:21:9: Function parse is nested too deeply. [NestedBlockDepth]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/data/backup/BackupRepository.kt:143:17: The function importCsvZip is too long (146). The maximum length is 80. [LongMethod]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/data/backup/BackupRepository.kt:296:25: The function buildPayload is too long (113). The maximum length is 80. [LongMethod]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/data/backup/BackupRepository.kt:143:17: The function importCsvZip appears to be too complex based on Cyclomatic Complexity (complexity: 23). Defined complexity threshold for methods is set to '15' [CyclomaticComplexMethod]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/data/backup/BackupRepository.kt:413:25: The function applyPayload appears to be too complex based on Cyclomatic Complexity (complexity: 27). Defined complexity threshold for methods is set to '15' [CyclomaticComplexMethod]
/home/runner/work/fintrack/fintrack/app/src/test/kotlin/com/fintrack/privacy/MoneyTypesTest.kt:23:9: Function noFloatingPointInMoneyLayers is nested too deeply. [NestedBlockDepth]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/MainActivity.kt:3:1: Imports must be ordered in lexicographic order without any empty lines in-between with "java", "javax", "kotlin" and aliases in the end [ImportOrdering]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/journey/goals/JourneyGoalsViewModel.kt:41:30: Missing { ... } [MultiLineIfElse]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/journey/goals/JourneyGoalsViewModel.kt:42:18: Missing { ... } [MultiLineIfElse]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/journey/goals/JourneyGoalsCard.kt:112:30: Argument should be on a separate line (unless all arguments can fit a single line) [ArgumentListWrapping]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/journey/goals/JourneyGoalsCard.kt:112:52: Argument should be on a separate line (unless all arguments can fit a single line) [ArgumentListWrapping]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/journey/goals/JourneyGoalsCard.kt:112:64: Missing newline before ")" [ArgumentListWrapping]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/journey/goals/JourneyGoalsCard.kt:112:102: Argument should be on a separate line (unless all arguments can fit a single line) [ArgumentListWrapping]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/journey/goals/JourneyGoalsCard.kt:112:128: Argument should be on a separate line (unless all arguments can fit a single line) [ArgumentListWrapping]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/journey/goals/JourneyGoalsCard.kt:112:140: Missing newline before ")" [ArgumentListWrapping]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/journey/goals/JourneyGoalsCard.kt:112:1: Exceeded max line length (140) [MaximumLineLength]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/journey/goals/JourneyGoalsCard.kt:4:1: Unused import [NoUnusedImports]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/journey/wins/WinsTimeline.kt:61:30: Missing { ... } [MultiLineIfElse]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/journey/wins/WinsTimeline.kt:62:18: Missing { ... } [MultiLineIfElse]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/journey/wins/WinsTimeline.kt:172:1: Needless blank line(s) [NoConsecutiveBlankLines]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/journey/wins/WinsTimeline.kt:3:1: Imports must be ordered in lexicographic order without any empty lines in-between with "java", "javax", "kotlin" and aliases in the end [ImportOrdering]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/picker/ProfilePickerScreen.kt:282:66: Missing { ... } [MultiLineIfElse]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/picker/ProfilePickerScreen.kt:283:42: Missing { ... } [MultiLineIfElse]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/picker/ProfilePickerScreen.kt:3:1: Imports must be ordered in lexicographic order without any empty lines in-between with "java", "javax", "kotlin" and aliases in the end [ImportOrdering]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/picker/ProfilePickerScreen.kt:50:1: Unused import [NoUnusedImports]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/snapshots/entry/SnapshotEntryViewModel.kt:134:28: Missing { ... } [MultiLineIfElse]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/snapshots/entry/SnapshotEntryViewModel.kt:134:37: Missing { ... } [MultiLineIfElse]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/snapshots/detail/SnapshotDetailScreen.kt:176:30: Missing newline after "(" [Wrapping]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/snapshots/detail/SnapshotDetailScreen.kt:177:78: Missing newline before ")" [Wrapping]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/snapshots/detail/SnapshotDetailScreen.kt:405:25: Missing newline after "(" [Wrapping]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/snapshots/detail/SnapshotDetailScreen.kt:406:72: Missing newline before ")" [Wrapping]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/snapshots/detail/SnapshotDetailScreen.kt:407:30: Missing newline after "(" [Wrapping]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/snapshots/detail/SnapshotDetailScreen.kt:408:58: Missing newline before ")" [Wrapping]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/snapshots/detail/SnapshotDetailScreen.kt:409:30: Missing newline after "(" [Wrapping]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/snapshots/detail/SnapshotDetailScreen.kt:412:44: Missing newline before ")" [Wrapping]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/snapshots/detail/SnapshotDetailScreen.kt:428:17: Missing newline after "(" [Wrapping]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/snapshots/detail/SnapshotDetailScreen.kt:429:64: Missing newline before ")" [Wrapping]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/snapshots/detail/SnapshotDetailScreen.kt:444:13: Missing newline after "(" [Wrapping]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/snapshots/detail/SnapshotDetailScreen.kt:445:60: Missing newline before ")" [Wrapping]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/snapshots/detail/SnapshotDetailScreen.kt:446:18: Missing newline after "(" [Wrapping]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/snapshots/detail/SnapshotDetailScreen.kt:447:46: Missing newline before ")" [Wrapping]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/snapshots/detail/SnapshotDetailScreen.kt:448:18: Missing newline after "(" [Wrapping]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/snapshots/detail/SnapshotDetailScreen.kt:450:48: Missing newline before ")" [Wrapping]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/snapshots/detail/SnapshotDetailScreen.kt:470:22: Missing newline after "(" [Wrapping]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/snapshots/detail/SnapshotDetailScreen.kt:471:70: Missing newline before ")" [Wrapping]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/snapshots/detail/SnapshotDetailScreen.kt:240:1: Unexpected indentation (19) (should be 4) [Indentation]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/snapshots/detail/SnapshotDetailScreen.kt:239:32: Missing { ... } [MultiLineIfElse]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/snapshots/detail/SnapshotDetailScreen.kt:240:25: Missing { ... } [MultiLineIfElse]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/snapshots/detail/SnapshotDetailScreen.kt:176:30: Argument should be on a separate line (unless all arguments can fit a single line) [ArgumentListWrapping]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/snapshots/detail/SnapshotDetailScreen.kt:176:39: Argument should be on a separate line (unless all arguments can fit a single line) [ArgumentListWrapping]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/snapshots/detail/SnapshotDetailScreen.kt:177:79: Missing newline before ")" [ArgumentListWrapping]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/snapshots/detail/SnapshotDetailScreen.kt:405:25: Argument should be on a separate line (unless all arguments can fit a single line) [ArgumentListWrapping]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/snapshots/detail/SnapshotDetailScreen.kt:406:73: Missing newline before ")" [ArgumentListWrapping]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/snapshots/detail/SnapshotDetailScreen.kt:407:30: Argument should be on a separate line (unless all arguments can fit a single line) [ArgumentListWrapping]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/snapshots/detail/SnapshotDetailScreen.kt:407:40: Argument should be on a separate line (unless all arguments can fit a single line) [ArgumentListWrapping]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/snapshots/detail/SnapshotDetailScreen.kt:408:59: Missing newline before ")" [ArgumentListWrapping]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/snapshots/detail/SnapshotDetailScreen.kt:409:30: Argument should be on a separate line (unless all arguments can fit a single line) [ArgumentListWrapping]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/snapshots/detail/SnapshotDetailScreen.kt:412:45: Missing newline before ")" [ArgumentListWrapping]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/snapshots/detail/SnapshotDetailScreen.kt:428:17: Argument should be on a separate line (unless all arguments can fit a single line) [ArgumentListWrapping]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/snapshots/detail/SnapshotDetailScreen.kt:429:65: Missing newline before ")" [ArgumentListWrapping]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/snapshots/detail/SnapshotDetailScreen.kt:444:13: Argument should be on a separate line (unless all arguments can fit a single line) [ArgumentListWrapping]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/snapshots/detail/SnapshotDetailScreen.kt:445:61: Missing newline before ")" [ArgumentListWrapping]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/snapshots/detail/SnapshotDetailScreen.kt:446:18: Argument should be on a separate line (unless all arguments can fit a single line) [ArgumentListWrapping]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/snapshots/detail/SnapshotDetailScreen.kt:446:28: Argument should be on a separate line (unless all arguments can fit a single line) [ArgumentListWrapping]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/snapshots/detail/SnapshotDetailScreen.kt:447:47: Missing newline before ")" [ArgumentListWrapping]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/snapshots/detail/SnapshotDetailScreen.kt:448:18: Argument should be on a separate line (unless all arguments can fit a single line) [ArgumentListWrapping]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/snapshots/detail/SnapshotDetailScreen.kt:450:49: Missing newline before ")" [ArgumentListWrapping]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/snapshots/detail/SnapshotDetailScreen.kt:470:22: Argument should be on a separate line (unless all arguments can fit a single line) [ArgumentListWrapping]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/snapshots/detail/SnapshotDetailScreen.kt:470:29: Argument should be on a separate line (unless all arguments can fit a single line) [ArgumentListWrapping]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/snapshots/detail/SnapshotDetailScreen.kt:471:71: Missing newline before ")" [ArgumentListWrapping]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/snapshots/detail/SnapshotDetailScreen.kt:45:1: Unused import [NoUnusedImports]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/home/snapshots/SnapshotsListViewModel.kt:176:30: Missing { ... } [MultiLineIfElse]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/home/snapshots/SnapshotsListViewModel.kt:177:18: Missing { ... } [MultiLineIfElse]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/home/overview/LineChart.kt:221:1: Needless blank line(s) [NoConsecutiveBlankLines]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/home/overview/LineChart.kt:4:1: Unused import [NoUnusedImports]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/home/overview/LineChart.kt:9:1: Unused import [NoUnusedImports]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/home/overview/OverviewViewModel.kt:96:33: Missing { ... } [MultiLineIfElse]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/home/overview/OverviewViewModel.kt:97:18: Missing { ... } [MultiLineIfElse]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/home/overview/OverviewViewModel.kt:54:35: Unnecessary long whitespace [NoMultipleSpaces]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/home/overview/OverviewViewModel.kt:112:1: Declarations and declarations with annotations should have an empty space between. [SpacingBetweenDeclarationsWithAnnotations]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/home/overview/OverviewViewModel.kt:114:1: Declarations and declarations with annotations should have an empty space between. [SpacingBetweenDeclarationsWithAnnotations]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/home/overview/OverviewViewModel.kt:116:1: Declarations and declarations with annotations should have an empty space between. [SpacingBetweenDeclarationsWithAnnotations]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/home/overview/OverviewViewModel.kt:118:1: Declarations and declarations with annotations should have an empty space between. [SpacingBetweenDeclarationsWithAnnotations]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/home/overview/OverviewViewModel.kt:120:1: Declarations and declarations with annotations should have an empty space between. [SpacingBetweenDeclarationsWithAnnotations]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/home/overview/OverviewViewModel.kt:122:1: Declarations and declarations with annotations should have an empty space between. [SpacingBetweenDeclarationsWithAnnotations]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/home/overview/OverviewViewModel.kt:124:1: Declarations and declarations with annotations should have an empty space between. [SpacingBetweenDeclarationsWithAnnotations]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/home/overview/OverviewTab.kt:158:1: Needless blank line(s) [NoConsecutiveBlankLines]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/home/overview/OverviewTab.kt:412:1: Needless blank line(s) [NoConsecutiveBlankLines]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/home/overview/OverviewTab.kt:321:45: Unnecessary long whitespace [NoMultipleSpaces]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/home/overview/OverviewTab.kt:322:46: Unnecessary long whitespace [NoMultipleSpaces]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/home/overview/OverviewTab.kt:323:49: Unnecessary long whitespace [NoMultipleSpaces]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/home/overview/OverviewTab.kt:32:1: Unused import [NoUnusedImports]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/home/overview/OverviewTab.kt:223:20: Missing { ... } [MultiLineIfElse]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/home/overview/OverviewTab.kt:226:49: Missing { ... } [MultiLineIfElse]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/home/overview/OverviewTab.kt:227:39: Missing { ... } [MultiLineIfElse]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/home/overview/OverviewTab.kt:373:60: Missing { ... } [MultiLineIfElse]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/home/overview/OverviewTab.kt:374:18: Missing { ... } [MultiLineIfElse]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/home/overview/OverviewTab.kt:397:32: Missing { ... } [MultiLineIfElse]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/home/overview/OverviewTab.kt:398:25: Missing { ... } [MultiLineIfElse]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/home/overview/OverviewTab.kt:227:1: Unexpected indentation (33) (should be 16) [Indentation]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/home/overview/OverviewTab.kt:398:1: Unexpected indentation (19) (should be 4) [Indentation]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/home/HomeScreen.kt:3:1: Imports must be ordered in lexicographic order without any empty lines in-between with "java", "javax", "kotlin" and aliases in the end [ImportOrdering]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/home/HomeScreen.kt:10:1: Unused import [NoUnusedImports]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/theme/Color.kt:14:46: Unnecessary long whitespace [NoMultipleSpaces]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/theme/Color.kt:15:44: Unnecessary long whitespace [NoMultipleSpaces]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/theme/Color.kt:16:43: Unnecessary long whitespace [NoMultipleSpaces]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/settings/excel/ExcelScreen.kt:242:1: Needless blank line(s) [NoConsecutiveBlankLines]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/settings/goals/GoalsManagementViewModel.kt:44:30: Missing { ... } [MultiLineIfElse]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/settings/goals/GoalsManagementViewModel.kt:45:18: Missing { ... } [MultiLineIfElse]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/settings/goals/GoalsManagementViewModel.kt:61:30: Missing { ... } [MultiLineIfElse]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/settings/goals/GoalsManagementViewModel.kt:62:18: Missing { ... } [MultiLineIfElse]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/settings/goals/GoalsManagementScreen.kt:258:1: Unexpected indentation (4) (should be 8) [Indentation]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/settings/goals/GoalsManagementScreen.kt:3:1: Imports must be ordered in lexicographic order without any empty lines in-between with "java", "javax", "kotlin" and aliases in the end [ImportOrdering]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/settings/goals/GoalsManagementScreen.kt:197:51: Missing { ... } [MultiLineIfElse]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/settings/goals/GoalsManagementScreen.kt:198:18: Missing { ... } [MultiLineIfElse]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/settings/goals/GoalsManagementScreen.kt:320:57: Missing { ... } [MultiLineIfElse]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/settings/goals/GoalsManagementScreen.kt:321:30: Missing { ... } [MultiLineIfElse]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/settings/goals/GoalsManagementScreen.kt:217:96: Missing newline after ";" [Wrapping]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/settings/goals/GoalsManagementScreen.kt:219:103: Missing newline after ";" [Wrapping]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/settings/goals/GoalsManagementScreen.kt:221:98: Missing newline after ";" [Wrapping]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/settings/users/ManageUsersScreen.kt:102:26: Missing newline after "(" [Wrapping]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/settings/users/ManageUsersScreen.kt:104:54: Missing newline before ")" [Wrapping]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/settings/users/ManageUsersScreen.kt:106:30: Missing newline after "(" [Wrapping]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/settings/users/ManageUsersScreen.kt:107:77: Missing newline before ")" [Wrapping]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/settings/users/ManageUsersScreen.kt:110:30: Missing newline after "(" [Wrapping]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/settings/users/ManageUsersScreen.kt:112:101: Missing newline before ")" [Wrapping]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/settings/users/ManageUsersScreen.kt:249:35: Missing newline after "(" [Wrapping]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/settings/users/ManageUsersScreen.kt:250:73: Missing newline before ")" [Wrapping]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/settings/users/ManageUsersScreen.kt:3:1: Imports must be ordered in lexicographic order without any empty lines in-between with "java", "javax", "kotlin" and aliases in the end [ImportOrdering]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/settings/users/ManageUsersScreen.kt:112:1: Unexpected indentation (35) (should be 28) [Indentation]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/settings/users/ManageUsersScreen.kt:209:1: Unexpected indentation (44) (should be 36) [Indentation]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/settings/users/ManageUsersScreen.kt:102:26: Argument should be on a separate line (unless all arguments can fit a single line) [ArgumentListWrapping]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/settings/users/ManageUsersScreen.kt:102:37: Argument should be on a separate line (unless all arguments can fit a single line) [ArgumentListWrapping]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/settings/users/ManageUsersScreen.kt:104:55: Missing newline before ")" [ArgumentListWrapping]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/settings/users/ManageUsersScreen.kt:106:30: Argument should be on a separate line (unless all arguments can fit a single line) [ArgumentListWrapping]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/settings/users/ManageUsersScreen.kt:106:49: Argument should be on a separate line (unless all arguments can fit a single line) [ArgumentListWrapping]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/settings/users/ManageUsersScreen.kt:107:78: Missing newline before ")" [ArgumentListWrapping]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/settings/users/ManageUsersScreen.kt:110:30: Argument should be on a separate line (unless all arguments can fit a single line) [ArgumentListWrapping]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/settings/users/ManageUsersScreen.kt:110:51: Argument should be on a separate line (unless all arguments can fit a single line) [ArgumentListWrapping]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/settings/users/ManageUsersScreen.kt:112:102: Missing newline before ")" [ArgumentListWrapping]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/settings/users/ManageUsersScreen.kt:160:16: Argument should be on a separate line (unless all arguments can fit a single line) [ArgumentListWrapping]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/settings/users/ManageUsersScreen.kt:160:27: Argument should be on a separate line (unless all arguments can fit a single line) [ArgumentListWrapping]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/settings/users/ManageUsersScreen.kt:160:38: Argument should be on a separate line (unless all arguments can fit a single line) [ArgumentListWrapping]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/settings/users/ManageUsersScreen.kt:161:16: Argument should be on a separate line (unless all arguments can fit a single line) [ArgumentListWrapping]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/settings/users/ManageUsersScreen.kt:161:27: Argument should be on a separate line (unless all arguments can fit a single line) [ArgumentListWrapping]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/settings/users/ManageUsersScreen.kt:161:38: Argument should be on a separate line (unless all arguments can fit a single line) [ArgumentListWrapping]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/settings/users/ManageUsersScreen.kt:249:35: Argument should be on a separate line (unless all arguments can fit a single line) [ArgumentListWrapping]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/settings/users/ManageUsersScreen.kt:249:50: Argument should be on a separate line (unless all arguments can fit a single line) [ArgumentListWrapping]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/settings/users/ManageUsersScreen.kt:250:40: Argument should be on a separate line (unless all arguments can fit a single line) [ArgumentListWrapping]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/settings/users/ManageUsersScreen.kt:250:74: Missing newline before ")" [ArgumentListWrapping]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/settings/users/ManageUsersScreen.kt:111:56: Missing { ... } [MultiLineIfElse]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/settings/users/ManageUsersScreen.kt:112:41: Missing { ... } [MultiLineIfElse]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/settings/users/ManageUsersScreen.kt:208:66: Missing { ... } [MultiLineIfElse]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/settings/users/ManageUsersScreen.kt:209:50: Missing { ... } [MultiLineIfElse]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/settings/loans/LoansManagementScreen.kt:128:29: Missing { ... } [MultiLineIfElse]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/settings/loans/LoansManagementScreen.kt:129:30: Missing { ... } [MultiLineIfElse]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/settings/loans/LoansManagementScreen.kt:235:58: Missing newline after ";" [Wrapping]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/settings/loans/LoansManagementScreen.kt:240:62: Missing newline after ";" [Wrapping]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/settings/loans/LoansManagementScreen.kt:245:58: Missing newline after ";" [Wrapping]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/settings/loans/LoansManagementViewModel.kt:39:30: Missing { ... } [MultiLineIfElse]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/settings/loans/LoansManagementViewModel.kt:40:18: Missing { ... } [MultiLineIfElse]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/settings/aim/AimEditorScreen.kt:98:33: Missing { ... } [MultiLineIfElse]

... (1989 lines elided) ...

	[33mArgumentListWrapping - [Argument should be on a separate line (unless all arguments can fit a single lin(...)] at /home/runner/work/fintrack/fintrack/app/src/test/kotlin/com/fintrack/domain/analytics/SnapshotAnalyticsTest.kt:27:26[0m
	[33mArgumentListWrapping - [Argument should be on a separate line (unless all arguments can fit a single lin(...)] at /home/runner/work/fintrack/fintrack/app/src/test/kotlin/com/fintrack/domain/analytics/SnapshotAnalyticsTest.kt:30:33[0m
	[33mArgumentListWrapping - [Argument should be on a separate line (unless all arguments can fit a single lin(...)] at /home/runner/work/fintrack/fintrack/app/src/test/kotlin/com/fintrack/domain/analytics/SnapshotAnalyticsTest.kt:30:56[0m
	[33mArgumentListWrapping - [Argument should be on a separate line (unless all arguments can fit a single lin(...)] at /home/runner/work/fintrack/fintrack/app/src/test/kotlin/com/fintrack/domain/analytics/SnapshotAnalyticsTest.kt:31:26[0m
	[33mArgumentListWrapping - [Argument should be on a separate line (unless all arguments can fit a single lin(...)] at /home/runner/work/fintrack/fintrack/app/src/test/kotlin/com/fintrack/domain/analytics/SnapshotAnalyticsTest.kt:34:33[0m
	[33mArgumentListWrapping - [Argument should be on a separate line (unless all arguments can fit a single lin(...)] at /home/runner/work/fintrack/fintrack/app/src/test/kotlin/com/fintrack/domain/analytics/SnapshotAnalyticsTest.kt:34:50[0m
	[33mArgumentListWrapping - [Argument should be on a separate line (unless all arguments can fit a single lin(...)] at /home/runner/work/fintrack/fintrack/app/src/test/kotlin/com/fintrack/domain/analytics/SnapshotAnalyticsTest.kt:35:26[0m
	[33mArgumentListWrapping - [Argument should be on a separate line (unless all arguments can fit a single lin(...)] at /home/runner/work/fintrack/fintrack/app/src/test/kotlin/com/fintrack/domain/analytics/SnapshotAnalyticsTest.kt:48:33[0m
	[33mArgumentListWrapping - [Argument should be on a separate line (unless all arguments can fit a single lin(...)] at /home/runner/work/fintrack/fintrack/app/src/test/kotlin/com/fintrack/domain/analytics/SnapshotAnalyticsTest.kt:48:53[0m
	[33mArgumentListWrapping - [Argument should be on a separate line (unless all arguments can fit a single lin(...)] at /home/runner/work/fintrack/fintrack/app/src/test/kotlin/com/fintrack/domain/analytics/SnapshotAnalyticsTest.kt:49:31[0m
	[33mArgumentListWrapping - [Argument should be on a separate line (unless all arguments can fit a single lin(...)] at /home/runner/work/fintrack/fintrack/app/src/test/kotlin/com/fintrack/domain/analytics/SnapshotAnalyticsTest.kt:52:33[0m
	[33mArgumentListWrapping - [Argument should be on a separate line (unless all arguments can fit a single lin(...)] at /home/runner/work/fintrack/fintrack/app/src/test/kotlin/com/fintrack/domain/analytics/SnapshotAnalyticsTest.kt:52:47[0m
	[33mArgumentListWrapping - [Argument should be on a separate line (unless all arguments can fit a single lin(...)] at /home/runner/work/fintrack/fintrack/app/src/test/kotlin/com/fintrack/domain/analytics/SnapshotAnalyticsTest.kt:53:32[0m
	[33mArgumentListWrapping - [Argument should be on a separate line (unless all arguments can fit a single lin(...)] at /home/runner/work/fintrack/fintrack/app/src/test/kotlin/com/fintrack/domain/analytics/SnapshotAnalyticsTest.kt:56:33[0m
	[33mArgumentListWrapping - [Argument should be on a separate line (unless all arguments can fit a single lin(...)] at /home/runner/work/fintrack/fintrack/app/src/test/kotlin/com/fintrack/domain/analytics/SnapshotAnalyticsTest.kt:56:48[0m
	[33mArgumentListWrapping - [Argument should be on a separate line (unless all arguments can fit a single lin(...)] at /home/runner/work/fintrack/fintrack/app/src/test/kotlin/com/fintrack/domain/analytics/SnapshotAnalyticsTest.kt:57:32[0m
	[33mArgumentListWrapping - [Argument should be on a separate line (unless all arguments can fit a single lin(...)] at /home/runner/work/fintrack/fintrack/app/src/test/kotlin/com/fintrack/domain/analytics/SnapshotAnalyticsTest.kt:60:33[0m
	[33mArgumentListWrapping - [Argument should be on a separate line (unless all arguments can fit a single lin(...)] at /home/runner/work/fintrack/fintrack/app/src/test/kotlin/com/fintrack/domain/analytics/SnapshotAnalyticsTest.kt:60:51[0m
	[33mArgumentListWrapping - [Argument should be on a separate line (unless all arguments can fit a single lin(...)] at /home/runner/work/fintrack/fintrack/app/src/test/kotlin/com/fintrack/domain/analytics/SnapshotAnalyticsTest.kt:61:31[0m
	[33mArgumentListWrapping - [Argument should be on a separate line (unless all arguments can fit a single lin(...)] at /home/runner/work/fintrack/fintrack/app/src/test/kotlin/com/fintrack/domain/analytics/SnapshotAnalyticsTest.kt:64:33[0m
	[33mArgumentListWrapping - [Argument should be on a separate line (unless all arguments can fit a single lin(...)] at /home/runner/work/fintrack/fintrack/app/src/test/kotlin/com/fintrack/domain/analytics/SnapshotAnalyticsTest.kt:64:59[0m
	[33mArgumentListWrapping - [Argument should be on a separate line (unless all arguments can fit a single lin(...)] at /home/runner/work/fintrack/fintrack/app/src/test/kotlin/com/fintrack/domain/analytics/SnapshotAnalyticsTest.kt:65:31[0m
	[33mArgumentListWrapping - [Argument should be on a separate line (unless all arguments can fit a single lin(...)] at /home/runner/work/fintrack/fintrack/app/src/test/kotlin/com/fintrack/domain/analytics/SnapshotAnalyticsTest.kt:77:33[0m
	[33mArgumentListWrapping - [Argument should be on a separate line (unless all arguments can fit a single lin(...)] at /home/runner/work/fintrack/fintrack/app/src/test/kotlin/com/fintrack/domain/analytics/SnapshotAnalyticsTest.kt:77:54[0m
	[33mArgumentListWrapping - [Argument should be on a separate line (unless all arguments can fit a single lin(...)] at /home/runner/work/fintrack/fintrack/app/src/test/kotlin/com/fintrack/domain/analytics/SnapshotAnalyticsTest.kt:78:31[0m
	[33mArgumentListWrapping - [Argument should be on a separate line (unless all arguments can fit a single lin(...)] at /home/runner/work/fintrack/fintrack/app/src/test/kotlin/com/fintrack/domain/analytics/SnapshotAnalyticsTest.kt:78:48[0m
	[33mArgumentListWrapping - [Argument should be on a separate line (unless all arguments can fit a single lin(...)] at /home/runner/work/fintrack/fintrack/app/src/test/kotlin/com/fintrack/domain/analytics/SnapshotAnalyticsTest.kt:78:65[0m
	[33mArgumentListWrapping - [Argument should be on a separate line (unless all arguments can fit a single lin(...)] at /home/runner/work/fintrack/fintrack/app/src/test/kotlin/com/fintrack/domain/analytics/SnapshotAnalyticsTest.kt:193:41[0m
	[33mArgumentListWrapping - [Argument should be on a separate line (unless all arguments can fit a single lin(...)] at /home/runner/work/fintrack/fintrack/app/src/test/kotlin/com/fintrack/domain/analytics/SnapshotAnalyticsTest.kt:193:60[0m
	[33mWrapping - [Missing newline after "("] at /home/runner/work/fintrack/fintrack/app/src/test/kotlin/com/fintrack/domain/analytics/SnapshotAnalyticsTest.kt:192:33[0m
	[33mWrapping - [Missing newline before ")"] at /home/runner/work/fintrack/fintrack/app/src/test/kotlin/com/fintrack/domain/analytics/SnapshotAnalyticsTest.kt:195:13[0m
	[33mNoUnusedImports - [Unused import] at /home/runner/work/fintrack/fintrack/app/src/test/kotlin/com/fintrack/privacy/LogPatternTest.kt:6:1[0m
	[33mNoMultipleSpaces - [Unnecessary long whitespace] at /home/runner/work/fintrack/fintrack/app/src/test/kotlin/com/fintrack/data/SeedDataTest.kt:39:52[0m
	[33mNoMultipleSpaces - [Unnecessary long whitespace] at /home/runner/work/fintrack/fintrack/app/src/test/kotlin/com/fintrack/data/SeedDataTest.kt:40:20[0m
	[33mNoMultipleSpaces - [Unnecessary long whitespace] at /home/runner/work/fintrack/fintrack/app/src/test/kotlin/com/fintrack/data/SeedDataTest.kt:41:32[0m
	[33mNoMultipleSpaces - [Unnecessary long whitespace] at /home/runner/work/fintrack/fintrack/app/src/test/kotlin/com/fintrack/data/SeedDataTest.kt:42:20[0m
	[33mNoMultipleSpaces - [Unnecessary long whitespace] at /home/runner/work/fintrack/fintrack/app/src/test/kotlin/com/fintrack/data/SeedDataTest.kt:43:20[0m
	[33mNoMultipleSpaces - [Unnecessary long whitespace] at /home/runner/work/fintrack/fintrack/app/src/test/kotlin/com/fintrack/data/SeedDataTest.kt:44:19[0m
	[33mNoMultipleSpaces - [Unnecessary long whitespace] at /home/runner/work/fintrack/fintrack/app/src/test/kotlin/com/fintrack/data/SeedDataTest.kt:45:28[0m
	[33mNoMultipleSpaces - [Unnecessary long whitespace] at /home/runner/work/fintrack/fintrack/app/src/test/kotlin/com/fintrack/data/SeedDataTest.kt:46:45[0m
	[33mNoMultipleSpaces - [Unnecessary long whitespace] at /home/runner/work/fintrack/fintrack/app/src/test/kotlin/com/fintrack/data/SeedDataTest.kt:47:24[0m
	[33mArgumentListWrapping - [Argument should be on a separate line (unless all arguments can fit a single lin(...)] at /home/runner/work/fintrack/fintrack/app/src/test/kotlin/com/fintrack/data/SeedDataTest.kt:30:19[0m
	[33mArgumentListWrapping - [Argument should be on a separate line (unless all arguments can fit a single lin(...)] at /home/runner/work/fintrack/fintrack/app/src/test/kotlin/com/fintrack/data/SeedDataTest.kt:30:26[0m
	[33mArgumentListWrapping - [Argument should be on a separate line (unless all arguments can fit a single lin(...)] at /home/runner/work/fintrack/fintrack/app/src/test/kotlin/com/fintrack/data/SeedDataTest.kt:30:32[0m
	[33mArgumentListWrapping - [Argument should be on a separate line (unless all arguments can fit a single lin(...)] at /home/runner/work/fintrack/fintrack/app/src/test/kotlin/com/fintrack/data/SeedDataTest.kt:30:47[0m
naming - 10h 55min debt
	[33mFunctionNaming - [Function names should match the pattern: ^([a-z$][a-zA-Z$0-9]*)|(`.*`)$] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/journey/goals/JourneyGoalsCard.kt:36:5[0m
	[33mFunctionNaming - [Function names should match the pattern: ^([a-z$][a-zA-Z$0-9]*)|(`.*`)$] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/journey/goals/JourneyGoalsCard.kt:69:13[0m
	[33mFunctionNaming - [Function names should match the pattern: ^([a-z$][a-zA-Z$0-9]*)|(`.*`)$] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/journey/goals/JourneyGoalsCard.kt:121:13[0m
	[33mMatchingDeclarationName - [The file name 'WinsTimeline' does not match the name of the single top-level dec(...)] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/journey/wins/WinsTimeline.kt:54:7[0m
	[33mFunctionNaming - [Function names should match the pattern: ^([a-z$][a-zA-Z$0-9]*)|(`.*`)$] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/journey/wins/WinsTimeline.kt:70:5[0m
	[33mFunctionNaming - [Function names should match the pattern: ^([a-z$][a-zA-Z$0-9]*)|(`.*`)$] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/journey/wins/WinsTimeline.kt:88:13[0m
	[33mFunctionNaming - [Function names should match the pattern: ^([a-z$][a-zA-Z$0-9]*)|(`.*`)$] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/journey/wins/WinsTimeline.kt:121:13[0m
	[33mFunctionNaming - [Function names should match the pattern: ^([a-z$][a-zA-Z$0-9]*)|(`.*`)$] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/journey/wins/WinsTimeline.kt:151:13[0m
	[33mFunctionNaming - [Function names should match the pattern: ^([a-z$][a-zA-Z$0-9]*)|(`.*`)$] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/lock/LockScreen.kt:31:5[0m
	[33mFunctionNaming - [Function names should match the pattern: ^([a-z$][a-zA-Z$0-9]*)|(`.*`)$] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/lock/BiometricUnavailableScreen.kt:27:5[0m
	[33mFunctionNaming - [Function names should match the pattern: ^([a-z$][a-zA-Z$0-9]*)|(`.*`)$] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/picker/ProfilePickerScreen.kt:58:5[0m
	[33mFunctionNaming - [Function names should match the pattern: ^([a-z$][a-zA-Z$0-9]*)|(`.*`)$] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/picker/ProfilePickerScreen.kt:145:13[0m
	[33mFunctionNaming - [Function names should match the pattern: ^([a-z$][a-zA-Z$0-9]*)|(`.*`)$] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/picker/ProfilePickerScreen.kt:203:13[0m
	[33mFunctionNaming - [Function names should match the pattern: ^([a-z$][a-zA-Z$0-9]*)|(`.*`)$] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/picker/ProfilePickerScreen.kt:239:13[0m
	[33mFunctionNaming - [Function names should match the pattern: ^([a-z$][a-zA-Z$0-9]*)|(`.*`)$] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/picker/ProfilePickerScreen.kt:303:13[0m
	[33mFunctionNaming - [Function names should match the pattern: ^([a-z$][a-zA-Z$0-9]*)|(`.*`)$] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/snapshots/entry/SnapshotEntryScreen.kt:59:5[0m
	[33mFunctionNaming - [Function names should match the pattern: ^([a-z$][a-zA-Z$0-9]*)|(`.*`)$] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/snapshots/entry/SnapshotEntryScreen.kt:142:13[0m
	[33mFunctionNaming - [Function names should match the pattern: ^([a-z$][a-zA-Z$0-9]*)|(`.*`)$] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/snapshots/entry/SnapshotEntryScreen.kt:283:13[0m
	[33mFunctionNaming - [Function names should match the pattern: ^([a-z$][a-zA-Z$0-9]*)|(`.*`)$] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/snapshots/entry/SnapshotEntryScreen.kt:299:13[0m
	[33mFunctionNaming - [Function names should match the pattern: ^([a-z$][a-zA-Z$0-9]*)|(`.*`)$] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/snapshots/entry/SnapshotEntryScreen.kt:314:13[0m
	[33mFunctionNaming - [Function names should match the pattern: ^([a-z$][a-zA-Z$0-9]*)|(`.*`)$] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/snapshots/entry/SnapshotEntryScreen.kt:325:13[0m
	[33mFunctionNaming - [Function names should match the pattern: ^([a-z$][a-zA-Z$0-9]*)|(`.*`)$] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/snapshots/entry/SnapshotEntryScreen.kt:347:13[0m
	[33mFunctionNaming - [Function names should match the pattern: ^([a-z$][a-zA-Z$0-9]*)|(`.*`)$] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/snapshots/entry/SnapshotEntryScreen.kt:428:13[0m
	[33mFunctionNaming - [Function names should match the pattern: ^([a-z$][a-zA-Z$0-9]*)|(`.*`)$] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/snapshots/entry/SnapshotEntryScreen.kt:474:13[0m
	[33mFunctionNaming - [Function names should match the pattern: ^([a-z$][a-zA-Z$0-9]*)|(`.*`)$] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/snapshots/entry/SnapshotEntryScreen.kt:499:13[0m
	[33mFunctionNaming - [Function names should match the pattern: ^([a-z$][a-zA-Z$0-9]*)|(`.*`)$] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/snapshots/detail/SnapshotDetailScreen.kt:67:5[0m
	[33mFunctionNaming - [Function names should match the pattern: ^([a-z$][a-zA-Z$0-9]*)|(`.*`)$] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/snapshots/detail/SnapshotDetailScreen.kt:145:13[0m
	[33mFunctionNaming - [Function names should match the pattern: ^([a-z$][a-zA-Z$0-9]*)|(`.*`)$] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/snapshots/detail/SnapshotDetailScreen.kt:188:13[0m
	[33mFunctionNaming - [Function names should match the pattern: ^([a-z$][a-zA-Z$0-9]*)|(`.*`)$] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/snapshots/detail/SnapshotDetailScreen.kt:208:13[0m
	[33mFunctionNaming - [Function names should match the pattern: ^([a-z$][a-zA-Z$0-9]*)|(`.*`)$] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/snapshots/detail/SnapshotDetailScreen.kt:219:13[0m
	[33mFunctionNaming - [Function names should match the pattern: ^([a-z$][a-zA-Z$0-9]*)|(`.*`)$] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/snapshots/detail/SnapshotDetailScreen.kt:235:22[0m
	[33mFunctionNaming - [Function names should match the pattern: ^([a-z$][a-zA-Z$0-9]*)|(`.*`)$] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/snapshots/detail/SnapshotDetailScreen.kt:253:13[0m
	[33mFunctionNaming - [Function names should match the pattern: ^([a-z$][a-zA-Z$0-9]*)|(`.*`)$] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/snapshots/detail/SnapshotDetailScreen.kt:329:13[0m
	[33mFunctionNaming - [Function names should match the pattern: ^([a-z$][a-zA-Z$0-9]*)|(`.*`)$] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/snapshots/detail/SnapshotDetailScreen.kt:356:13[0m
	[33mFunctionNaming - [Function names should match the pattern: ^([a-z$][a-zA-Z$0-9]*)|(`.*`)$] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/snapshots/detail/SnapshotDetailScreen.kt:390:13[0m
	[33mFunctionNaming - [Function names should match the pattern: ^([a-z$][a-zA-Z$0-9]*)|(`.*`)$] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/snapshots/detail/SnapshotDetailScreen.kt:442:13[0m
	[33mFunctionNaming - [Function names should match the pattern: ^([a-z$][a-zA-Z$0-9]*)|(`.*`)$] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/snapshots/detail/SnapshotDetailScreen.kt:465:13[0m
	[33mFunctionNaming - [Function names should match the pattern: ^([a-z$][a-zA-Z$0-9]*)|(`.*`)$] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/snapshots/common/SnapshotDeleteDialog.kt:22:5[0m
	[33mFunctionNaming - [Function names should match the pattern: ^([a-z$][a-zA-Z$0-9]*)|(`.*`)$] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/navigation/AppNavGraph.kt:46:5[0m
	[33mFunctionNaming - [Function names should match the pattern: ^([a-z$][a-zA-Z$0-9]*)|(`.*`)$] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/navigation/AppNavGraph.kt:87:13[0m
	[33mFunctionNaming - [Function names should match the pattern: ^([a-z$][a-zA-Z$0-9]*)|(`.*`)$] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/navigation/AppNavGraph.kt:202:13[0m
	[33mFunctionNaming - [Function names should match the pattern: ^([a-z$][a-zA-Z$0-9]*)|(`.*`)$] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/home/snapshots/SnapshotsTab.kt:57:5[0m
	[33mFunctionNaming - [Function names should match the pattern: ^([a-z$][a-zA-Z$0-9]*)|(`.*`)$] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/home/snapshots/SnapshotsTab.kt:108:13[0m
	[33mFunctionNaming - [Function names should match the pattern: ^([a-z$][a-zA-Z$0-9]*)|(`.*`)$] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/home/snapshots/SnapshotsTab.kt:280:13[0m
	[33mFunctionNaming - [Function names should match the pattern: ^([a-z$][a-zA-Z$0-9]*)|(`.*`)$] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/home/snapshots/SnapshotsTab.kt:306:13[0m
	[33mFunctionNaming - [Function names should match the pattern: ^([a-z$][a-zA-Z$0-9]*)|(`.*`)$] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/home/snapshots/SnapshotsTab.kt:325:13[0m
	[33mFunctionNaming - [Function names should match the pattern: ^([a-z$][a-zA-Z$0-9]*)|(`.*`)$] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/home/snapshots/SnapshotsTab.kt:356:13[0m
	[33mMatchingDeclarationName - [The file name 'LineChart' does not match the name of the single top-level declar(...)] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/home/overview/LineChart.kt:32:12[0m
	[33mFunctionNaming - [Function names should match the pattern: ^([a-z$][a-zA-Z$0-9]*)|(`.*`)$] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/home/overview/LineChart.kt:46:5[0m
	[33mFunctionNaming - [Function names should match the pattern: ^([a-z$][a-zA-Z$0-9]*)|(`.*`)$] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/home/overview/OverviewTab.kt:60:5[0m
	[33mFunctionNaming - [Function names should match the pattern: ^([a-z$][a-zA-Z$0-9]*)|(`.*`)$] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/home/overview/OverviewTab.kt:109:13[0m
	[33mFunctionNaming - [Function names should match the pattern: ^([a-z$][a-zA-Z$0-9]*)|(`.*`)$] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/home/overview/OverviewTab.kt:160:13[0m
	[33mFunctionNaming - [Function names should match the pattern: ^([a-z$][a-zA-Z$0-9]*)|(`.*`)$] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/home/overview/OverviewTab.kt:174:13[0m
	[33mFunctionNaming - [Function names should match the pattern: ^([a-z$][a-zA-Z$0-9]*)|(`.*`)$] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/home/overview/OverviewTab.kt:187:13[0m
	[33mFunctionNaming - [Function names should match the pattern: ^([a-z$][a-zA-Z$0-9]*)|(`.*`)$] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/home/overview/OverviewTab.kt:205:13[0m
	[33mFunctionNaming - [Function names should match the pattern: ^([a-z$][a-zA-Z$0-9]*)|(`.*`)$] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/home/overview/OverviewTab.kt:235:13[0m
	[33mFunctionNaming - [Function names should match the pattern: ^([a-z$][a-zA-Z$0-9]*)|(`.*`)$] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/home/overview/OverviewTab.kt:325:13[0m
	[33mFunctionNaming - [Function names should match the pattern: ^([a-z$][a-zA-Z$0-9]*)|(`.*`)$] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/home/overview/OverviewTab.kt:333:13[0m
	[33mFunctionNaming - [Function names should match the pattern: ^([a-z$][a-zA-Z$0-9]*)|(`.*`)$] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/home/overview/OverviewTab.kt:354:13[0m
	[33mFunctionNaming - [Function names should match the pattern: ^([a-z$][a-zA-Z$0-9]*)|(`.*`)$] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/home/overview/OverviewTab.kt:392:13[0m
	[33mFunctionNaming - [Function names should match the pattern: ^([a-z$][a-zA-Z$0-9]*)|(`.*`)$] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/home/HomeScreen.kt:85:5[0m
	[33mFunctionNaming - [Function names should match the pattern: ^([a-z$][a-zA-Z$0-9]*)|(`.*`)$] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/home/HomeScreen.kt:185:13[0m
	[33mFunctionNaming - [Function names should match the pattern: ^([a-z$][a-zA-Z$0-9]*)|(`.*`)$] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/help/HelpSheet.kt:36:5[0m
	[33mFunctionNaming - [Function names should match the pattern: ^([a-z$][a-zA-Z$0-9]*)|(`.*`)$] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/help/HelpSheet.kt:54:5[0m
	[33mFunctionNaming - [Function names should match the pattern: ^([a-z$][a-zA-Z$0-9]*)|(`.*`)$] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/metrics/MetricInfo.kt:115:5[0m
	[33mFunctionNaming - [Function names should match the pattern: ^([a-z$][a-zA-Z$0-9]*)|(`.*`)$] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/metrics/MetricInfo.kt:143:13[0m
	[33mFunctionNaming - [Function names should match the pattern: ^([a-z$][a-zA-Z$0-9]*)|(`.*`)$] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/theme/Theme.kt:35:5[0m
	[33mFunctionNaming - [Function names should match the pattern: ^([a-z$][a-zA-Z$0-9]*)|(`.*`)$] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/settings/excel/ExcelScreen.kt:48:5[0m
	[33mFunctionNaming - [Function names should match the pattern: ^([a-z$][a-zA-Z$0-9]*)|(`.*`)$] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/settings/excel/ExcelScreen.kt:150:13[0m
	[33mFunctionNaming - [Function names should match the pattern: ^([a-z$][a-zA-Z$0-9]*)|(`.*`)$] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/settings/excel/ExcelScreen.kt:198:13[0m
	[33mFunctionNaming - [Function names should match the pattern: ^([a-z$][a-zA-Z$0-9]*)|(`.*`)$] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/settings/excel/ExcelScreen.kt:209:13[0m
	[33mFunctionNaming - [Function names should match the pattern: ^([a-z$][a-zA-Z$0-9]*)|(`.*`)$] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/settings/goals/GoalsManagementScreen.kt:66:5[0m
	[33mFunctionNaming - [Function names should match the pattern: ^([a-z$][a-zA-Z$0-9]*)|(`.*`)$] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/settings/goals/GoalsManagementScreen.kt:187:13[0m
	[33mFunctionNaming - [Function names should match the pattern: ^([a-z$][a-zA-Z$0-9]*)|(`.*`)$] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/settings/goals/GoalsManagementScreen.kt:240:13[0m
	[33mFunctionNaming - [Function names should match the pattern: ^([a-z$][a-zA-Z$0-9]*)|(`.*`)$] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/settings/users/ManageUsersScreen.kt:57:5[0m
	[33mFunctionNaming - [Function names should match the pattern: ^([a-z$][a-zA-Z$0-9]*)|(`.*`)$] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/settings/users/ManageUsersScreen.kt:167:13[0m
	[33mFunctionNaming - [Function names should match the pattern: ^([a-z$][a-zA-Z$0-9]*)|(`.*`)$] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/settings/users/ManageUsersScreen.kt:229:13[0m
	[33mFunctionNaming - [Function names should match the pattern: ^([a-z$][a-zA-Z$0-9]*)|(`.*`)$] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/settings/loans/LoansManagementScreen.kt:62:5[0m
	[33mFunctionNaming - [Function names should match the pattern: ^([a-z$][a-zA-Z$0-9]*)|(`.*`)$] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/settings/loans/LoansManagementScreen.kt:205:13[0m
	[33mFunctionNaming - [Function names should match the pattern: ^([a-z$][a-zA-Z$0-9]*)|(`.*`)$] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/settings/loans/LoansManagementScreen.kt:264:13[0m
	[33mFunctionNaming - [Function names should match the pattern: ^([a-z$][a-zA-Z$0-9]*)|(`.*`)$] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/settings/loans/LoansManagementScreen.kt:272:13[0m
	[33mFunctionNaming - [Function names should match the pattern: ^([a-z$][a-zA-Z$0-9]*)|(`.*`)$] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/settings/loans/LoansManagementScreen.kt:354:13[0m
	[33mFunctionNaming - [Function names should match the pattern: ^([a-z$][a-zA-Z$0-9]*)|(`.*`)$] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/settings/aim/AimEditorScreen.kt:194:5[0m
	[33mFunctionNaming - [Function names should match the pattern: ^([a-z$][a-zA-Z$0-9]*)|(`.*`)$] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/settings/aim/AimEditorScreen.kt:273:13[0m
	[33mFunctionNaming - [Function names should match the pattern: ^([a-z$][a-zA-Z$0-9]*)|(`.*`)$] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/settings/aim/AimEditorScreen.kt:292:13[0m
	[33mFunctionNaming - [Function names should match the pattern: ^([a-z$][a-zA-Z$0-9]*)|(`.*`)$] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/settings/about/AboutScreen.kt:73:5[0m
	[33mFunctionNaming - [Function names should match the pattern: ^([a-z$][a-zA-Z$0-9]*)|(`.*`)$] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/settings/about/AboutScreen.kt:199:13[0m
	[33mFunctionNaming - [Function names should match the pattern: ^([a-z$][a-zA-Z$0-9]*)|(`.*`)$] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/settings/about/AboutScreen.kt:232:13[0m
	[33mFunctionNaming - [Function names should match the pattern: ^([a-z$][a-zA-Z$0-9]*)|(`.*`)$] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/settings/about/AboutScreen.kt:253:13[0m
	[33mFunctionNaming - [Function names should match the pattern: ^([a-z$][a-zA-Z$0-9]*)|(`.*`)$] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/settings/about/AboutScreen.kt:306:13[0m
	[33mFunctionNaming - [Function names should match the pattern: ^([a-z$][a-zA-Z$0-9]*)|(`.*`)$] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/settings/about/AboutScreen.kt:329:13[0m
	[33mFunctionNaming - [Function names should match the pattern: ^([a-z$][a-zA-Z$0-9]*)|(`.*`)$] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/settings/about/AboutScreen.kt:374:13[0m
	[33mFunctionNaming - [Function names should match the pattern: ^([a-z$][a-zA-Z$0-9]*)|(`.*`)$] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/settings/about/AboutScreen.kt:387:13[0m
	[33mFunctionNaming - [Function names should match the pattern: ^([a-z$][a-zA-Z$0-9]*)|(`.*`)$] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/settings/about/AboutScreen.kt:440:13[0m
	[33mFunctionNaming - [Function names should match the pattern: ^([a-z$][a-zA-Z$0-9]*)|(`.*`)$] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/settings/about/AboutScreen.kt:477:13[0m
	[33mFunctionNaming - [Function names should match the pattern: ^([a-z$][a-zA-Z$0-9]*)|(`.*`)$] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/settings/about/AboutScreen.kt:491:14[0m
	[33mFunctionNaming - [Function names should match the pattern: ^([a-z$][a-zA-Z$0-9]*)|(`.*`)$] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/settings/about/AboutScreen.kt:503:14[0m
	[33mFunctionNaming - [Function names should match the pattern: ^([a-z$][a-zA-Z$0-9]*)|(`.*`)$] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/settings/about/AboutScreen.kt:526:14[0m
	[33mFunctionNaming - [Function names should match the pattern: ^([a-z$][a-zA-Z$0-9]*)|(`.*`)$] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/settings/about/AboutScreen.kt:552:14[0m
	[33mFunctionNaming - [Function names should match the pattern: ^([a-z$][a-zA-Z$0-9]*)|(`.*`)$] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/settings/about/AboutScreen.kt:577:14[0m
	[33mFunctionNaming - [Function names should match the pattern: ^([a-z$][a-zA-Z$0-9]*)|(`.*`)$] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/settings/about/AboutScreen.kt:616:13[0m
	[33mFunctionNaming - [Function names should match the pattern: ^([a-z$][a-zA-Z$0-9]*)|(`.*`)$] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/settings/about/ManifestViewerScreen.kt:42:5[0m
	[33mFunctionNaming - [Function names should match the pattern: ^([a-z$][a-zA-Z$0-9]*)|(`.*`)$] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/settings/about/ManifestViewerScreen.kt:110:13[0m
	[33mFunctionNaming - [Function names should match the pattern: ^([a-z$][a-zA-Z$0-9]*)|(`.*`)$] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/settings/about/ManifestViewerScreen.kt:125:13[0m
	[33mFunctionNaming - [Function names should match the pattern: ^([a-z$][a-zA-Z$0-9]*)|(`.*`)$] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/settings/backup/BackupScreen.kt:187:5[0m
	[33mFunctionNaming - [Function names should match the pattern: ^([a-z$][a-zA-Z$0-9]*)|(`.*`)$] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/settings/backup/BackupScreen.kt:376:13[0m
	[33mFunctionNaming - [Function names should match the pattern: ^([a-z$][a-zA-Z$0-9]*)|(`.*`)$] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/settings/backup/BackupScreen.kt:394:13[0m
	[33mFunctionNaming - [Function names should match the pattern: ^([a-z$][a-zA-Z$0-9]*)|(`.*`)$] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/settings/backup/BackupScreen.kt:422:13[0m
	[33mFunctionNaming - [Function names should match the pattern: ^([a-z$][a-zA-Z$0-9]*)|(`.*`)$] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/settings/SettingsTab.kt:40:5[0m
	[33mFunctionNaming - [Function names should match the pattern: ^([a-z$][a-zA-Z$0-9]*)|(`.*`)$] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/settings/SettingsTab.kt:109:13[0m
	[33mFunctionNaming - [Function names should match the pattern: ^([a-z$][a-zA-Z$0-9]*)|(`.*`)$] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/settings/SettingsTab.kt:139:13[0m
	[33mFunctionNaming - [Function names should match the pattern: ^([a-z$][a-zA-Z$0-9]*)|(`.*`)$] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/settings/SettingsTab.kt:164:13[0m
	[33mFunctionNaming - [Function names should match the pattern: ^([a-z$][a-zA-Z$0-9]*)|(`.*`)$] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/settings/holdings/HoldingsManagementScreen.kt:287:5[0m
	[33mFunctionNaming - [Function names should match the pattern: ^([a-z$][a-zA-Z$0-9]*)|(`.*`)$] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/settings/holdings/HoldingsManagementScreen.kt:439:13[0m
	[33mFunctionNaming - [Function names should match the pattern: ^([a-z$][a-zA-Z$0-9]*)|(`.*`)$] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/settings/holdings/HoldingsManagementScreen.kt:492:13[0m
	[33mFunctionNaming - [Function names should match the pattern: ^([a-z$][a-zA-Z$0-9]*)|(`.*`)$] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/settings/holdings/HoldingsManagementScreen.kt:574:13[0m
	[33mFunctionNaming - [Function names should match the pattern: ^([a-z$][a-zA-Z$0-9]*)|(`.*`)$] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/settings/holdings/HoldingsManagementScreen.kt:630:13[0m
	[33mFunctionNaming - [Function names should match the pattern: ^([a-z$][a-zA-Z$0-9]*)|(`.*`)$] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/settings/holdings/HoldingsManagementScreen.kt:681:13[0m
	[33mFunctionNaming - [Function names should match the pattern: ^([a-z$][a-zA-Z$0-9]*)|(`.*`)$] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/settings/holdings/HoldingsManagementScreen.kt:701:13[0m
	[33mFunctionNaming - [Function names should match the pattern: ^([a-z$][a-zA-Z$0-9]*)|(`.*`)$] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/settings/holdings/HoldingsManagementScreen.kt:729:13[0m
	[33mMatchingDeclarationName - [The file name 'CreateFirstProfileScreen' does not match the name of the single t(...)] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/onboarding/CreateFirstProfileScreen.kt:48:7[0m
	[33mFunctionNaming - [Function names should match the pattern: ^([a-z$][a-zA-Z$0-9]*)|(`.*`)$] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/onboarding/CreateFirstProfileScreen.kt:73:5[0m
	[33mFunctionNaming - [Function names should match the pattern: ^([a-z$][a-zA-Z$0-9]*)|(`.*`)$] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/onboarding/OnboardingPagerScreen.kt:62:5[0m
	[33mFunctionNaming - [Function names should match the pattern: ^([a-z$][a-zA-Z$0-9]*)|(`.*`)$] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/onboarding/OnboardingPagerScreen.kt:114:13[0m
	[33mFunctionNaming - [Function names should match the pattern: ^([a-z$][a-zA-Z$0-9]*)|(`.*`)$] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/onboarding/OnboardingPagerScreen.kt:211:13[0m
	[33mFunctionNaming - [Function names should match the pattern: ^([a-z$][a-zA-Z$0-9]*)|(`.*`)$] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/onboarding/OnboardingPagerScreen.kt:243:13[0m
	[33mFunctionNaming - [Function names should match the pattern: ^([a-z$][a-zA-Z$0-9]*)|(`.*`)$] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/common/CommonDatePickerSheet.kt:21:5[0m
	[33mMatchingDeclarationName - [The file name 'CelebrationSheet' does not match the name of the single top-level(...)] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/celebration/CelebrationSheet.kt:53:7[0m
	[33mFunctionNaming - [Function names should match the pattern: ^([a-z$][a-zA-Z$0-9]*)|(`.*`)$] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/celebration/CelebrationSheet.kt:73:5[0m
	[33mFunctionNaming - [Function names should match the pattern: ^([a-z$][a-zA-Z$0-9]*)|(`.*`)$] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/security/PrivacyOverlay.kt:31:5[0m
	[33mFunctionNaming - [Function names should match the pattern: ^([a-z$][a-zA-Z$0-9]*)|(`.*`)$] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/security/MoneyTextField.kt:27:5[0m
style - 2h 40min debt
	[33mMaxLineLength - [Line detected, which is longer than the defined maximum line length in the code (...)] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/journey/goals/JourneyGoalsCard.kt:112:1[0m
	[33mUnusedPrivateMember - [Private function `EmptyPlaceholder` is unused.] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/home/HomeScreen.kt:185:13[0m
	[33mMaxLineLength - [Line detected, which is longer than the defined maximum line length in the code (...)] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/settings/backup/BackupScreen.kt:287:1[0m
	[33mMaxLineLength - [Line detected, which is longer than the defined maximum line length in the code (...)] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/settings/backup/BackupScreen.kt:304:1[0m
	[33mMaxLineLength - [Line detected, which is longer than the defined maximum line length in the code (...)] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/settings/backup/BackupScreen.kt:385:1[0m
	[33mUnusedPrivateProperty - [Private property `context` is unused.] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/settings/backup/BackupScreen.kt:192:9[0m
	[33mLoopWithTooManyJumpStatements - [The loop contains more than one break or continue statement. The code should be (...)] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/domain/milestones/MilestoneDetector.kt:68:9[0m
	[33mLoopWithTooManyJumpStatements - [The loop contains more than one break or continue statement. The code should be (...)] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/domain/milestones/MilestoneDetector.kt:103:13[0m
	[33mMaxLineLength - [Line detected, which is longer than the defined maximum line length in the code (...)] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/domain/glossary/GlossaryContent.kt:39:1[0m
	[33mMaxLineLength - [Line detected, which is longer than the defined maximum line length in the code (...)] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/domain/glossary/GlossaryContent.kt:51:1[0m
	[33mMaxLineLength - [Line detected, which is longer than the defined maximum line length in the code (...)] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/domain/glossary/GlossaryContent.kt:55:1[0m
	[33mMaxLineLength - [Line detected, which is longer than the defined maximum line length in the code (...)] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/domain/glossary/GlossaryContent.kt:59:1[0m
	[33mMaxLineLength - [Line detected, which is longer than the defined maximum line length in the code (...)] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/domain/glossary/GlossaryContent.kt:108:1[0m
	[33mMaxLineLength - [Line detected, which is longer than the defined maximum line length in the code (...)] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/domain/glossary/GlossaryContent.kt:112:1[0m
	[33mMaxLineLength - [Line detected, which is longer than the defined maximum line length in the code (...)] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/di/DatabaseModule.kt:69:1[0m
	[33mMaxLineLength - [Line detected, which is longer than the defined maximum line length in the code (...)] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/di/DatabaseModule.kt:80:1[0m
	[33mLoopWithTooManyJumpStatements - [The loop contains more than one break or continue statement. The code should be (...)] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/data/xlsx/XlsxImportApplier.kt:141:13[0m
	[33mLoopWithTooManyJumpStatements - [The loop contains more than one break or continue statement. The code should be (...)] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/data/xlsx/XlsxImportApplier.kt:166:13[0m
	[33mLoopWithTooManyJumpStatements - [The loop contains more than one break or continue statement. The code should be (...)] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/data/xlsx/XlsxImportApplier.kt:203:13[0m
	[33mLoopWithTooManyJumpStatements - [The loop contains more than one break or continue statement. The code should be (...)] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/data/xlsx/XlsxCodec.kt:269:9[0m
	[33mLoopWithTooManyJumpStatements - [The loop contains more than one break or continue statement. The code should be (...)] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/data/backup/CsvCodec.kt:27:9[0m
	[33mLoopWithTooManyJumpStatements - [The loop contains more than one break or continue statement. The code should be (...)] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/data/backup/BackupRepository.kt:567:13[0m
	[33mLoopWithTooManyJumpStatements - [The loop contains more than one break or continue statement. The code should be (...)] at /home/runner/work/fintrack/fintrack/app/src/test/kotlin/com/fintrack/privacy/SnapshotDaoUserScopedTest.kt:65:9[0m

Overall debt: 3d 3h


> Task :detekt FAILED
gradle/actions: Writing build results to /home/runner/work/_temp/.gradle-actions/build-results/detekt-1778059712637.json

FAILURE: Build failed with an exception.

* What went wrong:
Execution failed for task ':detekt'.
> Analysis failed with 771 weighted issues.

* Try:
> Run with --info or --debug option to get more log output.
> Run with --scan to get full insights.
> Get more help at https://help.gradle.org.

* Exception is:
org.gradle.api.tasks.TaskExecutionException: Execution failed for task ':detekt'.
	at org.gradle.api.internal.tasks.execution.ExecuteActionsTaskExecuter.lambda$executeIfValid$1(ExecuteActionsTaskExecuter.java:130)
	at org.gradle.internal.Try$Failure.ifSuccessfulOrElse(Try.java:293)
	at org.gradle.api.internal.tasks.execution.ExecuteActionsTaskExecuter.executeIfValid(ExecuteActionsTaskExecuter.java:128)
	at org.gradle.api.internal.tasks.execution.ExecuteActionsTaskExecuter.execute(ExecuteActionsTaskExecuter.java:116)
	at org.gradle.api.internal.tasks.execution.FinalizePropertiesTaskExecuter.execute(FinalizePropertiesTaskExecuter.java:46)
	at org.gradle.api.internal.tasks.execution.ResolveTaskExecutionModeExecuter.execute(ResolveTaskExecutionModeExecuter.java:51)
	at org.gradle.api.internal.tasks.execution.SkipTaskWithNoActionsExecuter.execute(SkipTaskWithNoActionsExecuter.java:57)
	at org.gradle.api.internal.tasks.execution.SkipOnlyIfTaskExecuter.execute(SkipOnlyIfTaskExecuter.java:74)
	at org.gradle.api.internal.tasks.execution.CatchExceptionTaskExecuter.execute(CatchExceptionTaskExecuter.java:36)
	at org.gradle.api.internal.tasks.execution.EventFiringTaskExecuter$1.executeTask(EventFiringTaskExecuter.java:77)
	at org.gradle.api.internal.tasks.execution.EventFiringTaskExecuter$1.call(EventFiringTaskExecuter.java:55)
	at org.gradle.api.internal.tasks.execution.EventFiringTaskExecuter$1.call(EventFiringTaskExecuter.java:52)
	at org.gradle.internal.operations.DefaultBuildOperationRunner$CallableBuildOperationWorker.execute(DefaultBuildOperationRunner.java:209)
	at org.gradle.internal.operations.DefaultBuildOperationRunner$CallableBuildOperationWorker.execute(DefaultBuildOperationRunner.java:204)
	at org.gradle.internal.operations.DefaultBuildOperationRunner$2.execute(DefaultBuildOperationRunner.java:66)
	at org.gradle.internal.operations.DefaultBuildOperationRunner$2.execute(DefaultBuildOperationRunner.java:59)
	at org.gradle.internal.operations.DefaultBuildOperationRunner.execute(DefaultBuildOperationRunner.java:166)
	at org.gradle.internal.operations.DefaultBuildOperationRunner.execute(DefaultBuildOperationRunner.java:59)
	at org.gradle.internal.operations.DefaultBuildOperationRunner.call(DefaultBuildOperationRunner.java:53)
	at org.gradle.api.internal.tasks.execution.EventFiringTaskExecuter.execute(EventFiringTaskExecuter.java:52)
	at org.gradle.execution.plan.LocalTaskNodeExecutor.execute(LocalTaskNodeExecutor.java:42)
	at org.gradle.execution.taskgraph.DefaultTaskExecutionGraph$InvokeNodeExecutorsAction.execute(DefaultTaskExecutionGraph.java:331)
	at org.gradle.execution.taskgraph.DefaultTaskExecutionGraph$InvokeNodeExecutorsAction.execute(DefaultTaskExecutionGraph.java:318)
	at org.gradle.execution.taskgraph.DefaultTaskExecutionGraph$BuildOperationAwareExecutionAction.lambda$execute$0(DefaultTaskExecutionGraph.java:314)
	at org.gradle.internal.operations.CurrentBuildOperationRef.with(CurrentBuildOperationRef.java:85)
	at org.gradle.execution.taskgraph.DefaultTaskExecutionGraph$BuildOperationAwareExecutionAction.execute(DefaultTaskExecutionGraph.java:314)
	at org.gradle.execution.taskgraph.DefaultTaskExecutionGraph$BuildOperationAwareExecutionAction.execute(DefaultTaskExecutionGraph.java:303)
	at org.gradle.execution.plan.DefaultPlanExecutor$ExecutorWorker.execute(DefaultPlanExecutor.java:459)
	at org.gradle.execution.plan.DefaultPlanExecutor$ExecutorWorker.run(DefaultPlanExecutor.java:376)
	at org.gradle.internal.concurrent.ExecutorPolicy$CatchAndRecordFailures.onExecute(ExecutorPolicy.java:64)
	at org.gradle.internal.concurrent.AbstractManagedExecutor$1.run(AbstractManagedExecutor.java:48)
Caused by: org.gradle.api.GradleException: Analysis failed with 771 weighted issues.
	at io.gitlab.arturbosch.detekt.invoke.DefaultCliInvoker.invokeCli(DetektInvoker.kt:102)
	at io.gitlab.arturbosch.detekt.Detekt.check(Detekt.kt:269)
	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
	at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at org.gradle.internal.reflect.JavaMethod.invoke(JavaMethod.java:125)
	at org.gradle.api.internal.project.taskfactory.StandardTaskAction.doExecute(StandardTaskAction.java:58)
	at org.gradle.api.internal.project.taskfactory.StandardTaskAction.execute(StandardTaskAction.java:51)
	at org.gradle.api.internal.project.taskfactory.StandardTaskAction.execute(StandardTaskAction.java:29)
	at org.gradle.api.internal.tasks.execution.TaskExecution$3.run(TaskExecution.java:244)
	at org.gradle.internal.operations.DefaultBuildOperationRunner$1.execute(DefaultBuildOperationRunner.java:29)
	at org.gradle.internal.operations.DefaultBuildOperationRunner$1.execute(DefaultBuildOperationRunner.java:26)
	at org.gradle.internal.operations.DefaultBuildOperationRunner$2.execute(DefaultBuildOperationRunner.java:66)
	at org.gradle.internal.operations.DefaultBuildOperationRunner$2.execute(DefaultBuildOperationRunner.java:59)
	at org.gradle.internal.operations.DefaultBuildOperationRunner.execute(DefaultBuildOperationRunner.java:166)
	at org.gradle.internal.operations.DefaultBuildOperationRunner.execute(DefaultBuildOperationRunner.java:59)
	at org.gradle.internal.operations.DefaultBuildOperationRunner.run(DefaultBuildOperationRunner.java:47)
	at org.gradle.api.internal.tasks.execution.TaskExecution.executeAction(TaskExecution.java:229)
	at org.gradle.api.internal.tasks.execution.TaskExecution.executeActions(TaskExecution.java:212)
	at org.gradle.api.internal.tasks.execution.TaskExecution.executeWithPreviousOutputFiles(TaskExecution.java:195)
	at org.gradle.api.internal.tasks.execution.TaskExecution.execute(TaskExecution.java:162)
	at org.gradle.internal.execution.steps.ExecuteStep.executeInternal(ExecuteStep.java:105)
	at org.gradle.internal.execution.steps.ExecuteStep.access$000(ExecuteStep.java:44)
	at org.gradle.internal.execution.steps.ExecuteStep$1.call(ExecuteStep.java:59)
	at org.gradle.internal.execution.steps.ExecuteStep$1.call(ExecuteStep.java:56)
	at org.gradle.internal.operations.DefaultBuildOperationRunner$CallableBuildOperationWorker.execute(DefaultBuildOperationRunner.java:209)
	at org.gradle.internal.operations.DefaultBuildOperationRunner$CallableBuildOperationWorker.execute(DefaultBuildOperationRunner.java:204)
	at org.gradle.internal.operations.DefaultBuildOperationRunner$2.execute(DefaultBuildOperationRunner.java:66)
	at org.gradle.internal.operations.DefaultBuildOperationRunner$2.execute(DefaultBuildOperationRunner.java:59)
	at org.gradle.internal.operations.DefaultBuildOperationRunner.execute(DefaultBuildOperationRunner.java:166)
	at org.gradle.internal.operations.DefaultBuildOperationRunner.execute(DefaultBuildOperationRunner.java:59)
	at org.gradle.internal.operations.DefaultBuildOperationRunner.call(DefaultBuildOperationRunner.java:53)
	at org.gradle.internal.execution.steps.ExecuteStep.execute(ExecuteStep.java:56)
	at org.gradle.internal.execution.steps.ExecuteStep.execute(ExecuteStep.java:44)
	at org.gradle.internal.execution.steps.CancelExecutionStep.execute(CancelExecutionStep.java:42)
	at org.gradle.internal.execution.steps.TimeoutStep.executeWithoutTimeout(TimeoutStep.java:75)
	at org.gradle.internal.execution.steps.TimeoutStep.execute(TimeoutStep.java:55)
	at org.gradle.internal.execution.steps.PreCreateOutputParentsStep.execute(PreCreateOutputParentsStep.java:50)
	at org.gradle.internal.execution.steps.PreCreateOutputParentsStep.execute(PreCreateOutputParentsStep.java:28)
	at org.gradle.internal.execution.steps.RemovePreviousOutputsStep.execute(RemovePreviousOutputsStep.java:67)
	at org.gradle.internal.execution.steps.RemovePreviousOutputsStep.execute(RemovePreviousOutputsStep.java:37)
	at org.gradle.internal.execution.steps.BroadcastChangingOutputsStep.execute(BroadcastChangingOutputsStep.java:61)
	at org.gradle.internal.execution.steps.BroadcastChangingOutputsStep.execute(BroadcastChangingOutputsStep.java:26)
	at org.gradle.internal.execution.steps.CaptureOutputsAfterExecutionStep.execute(CaptureOutputsAfterExecutionStep.java:69)
	at org.gradle.internal.execution.steps.CaptureOutputsAfterExecutionStep.execute(CaptureOutputsAfterExecutionStep.java:46)
	at org.gradle.internal.execution.steps.ResolveInputChangesStep.execute(ResolveInputChangesStep.java:40)
	at org.gradle.internal.execution.steps.ResolveInputChangesStep.execute(ResolveInputChangesStep.java:29)
	at org.gradle.internal.execution.steps.BuildCacheStep.executeWithoutCache(BuildCacheStep.java:189)
	at org.gradle.internal.execution.steps.BuildCacheStep.executeAndStoreInCache(BuildCacheStep.java:145)
	at org.gradle.internal.execution.steps.BuildCacheStep.lambda$executeWithCache$4(BuildCacheStep.java:101)
	at org.gradle.internal.execution.steps.BuildCacheStep.lambda$executeWithCache$5(BuildCacheStep.java:101)
	at org.gradle.internal.Try$Success.map(Try.java:175)
	at org.gradle.internal.execution.steps.BuildCacheStep.executeWithCache(BuildCacheStep.java:85)
	at org.gradle.internal.execution.steps.BuildCacheStep.lambda$execute$0(BuildCacheStep.java:74)
	at org.gradle.internal.Either$Left.fold(Either.java:115)
	at org.gradle.internal.execution.caching.CachingState.fold(CachingState.java:62)
	at org.gradle.internal.execution.steps.BuildCacheStep.execute(BuildCacheStep.java:73)
	at org.gradle.internal.execution.steps.BuildCacheStep.execute(BuildCacheStep.java:48)
	at org.gradle.internal.execution.steps.StoreExecutionStateStep.execute(StoreExecutionStateStep.java:46)
	at org.gradle.internal.execution.steps.StoreExecutionStateStep.execute(StoreExecutionStateStep.java:35)
	at org.gradle.internal.execution.steps.SkipUpToDateStep.executeBecause(SkipUpToDateStep.java:75)
	at org.gradle.internal.execution.steps.SkipUpToDateStep.lambda$execute$2(SkipUpToDateStep.java:53)
	at org.gradle.internal.execution.steps.SkipUpToDateStep.execute(SkipUpToDateStep.java:53)
	at org.gradle.internal.execution.steps.SkipUpToDateStep.execute(SkipUpToDateStep.java:35)
	at org.gradle.internal.execution.steps.legacy.MarkSnapshottingInputsFinishedStep.execute(MarkSnapshottingInputsFinishedStep.java:37)
	at org.gradle.internal.execution.steps.legacy.MarkSnapshottingInputsFinishedStep.execute(MarkSnapshottingInputsFinishedStep.java:27)
	at org.gradle.internal.execution.steps.ResolveIncrementalCachingStateStep.executeDelegate(ResolveIncrementalCachingStateStep.java:49)
	at org.gradle.internal.execution.steps.ResolveIncrementalCachingStateStep.executeDelegate(ResolveIncrementalCachingStateStep.java:27)
	at org.gradle.internal.execution.steps.AbstractResolveCachingStateStep.execute(AbstractResolveCachingStateStep.java:71)
	at org.gradle.internal.execution.steps.AbstractResolveCachingStateStep.execute(AbstractResolveCachingStateStep.java:39)
	at org.gradle.internal.execution.steps.ResolveChangesStep.execute(ResolveChangesStep.java:65)
	at org.gradle.internal.execution.steps.ResolveChangesStep.execute(ResolveChangesStep.java:36)
	at org.gradle.internal.execution.steps.ValidateStep.execute(ValidateStep.java:107)
	at org.gradle.internal.execution.steps.ValidateStep.execute(ValidateStep.java:56)
	at org.gradle.internal.execution.steps.AbstractCaptureStateBeforeExecutionStep.execute(AbstractCaptureStateBeforeExecutionStep.java:64)
	at org.gradle.internal.execution.steps.AbstractCaptureStateBeforeExecutionStep.execute(AbstractCaptureStateBeforeExecutionStep.java:43)
	at org.gradle.internal.execution.steps.AbstractSkipEmptyWorkStep.executeWithNonEmptySources(AbstractSkipEmptyWorkStep.java:125)
	at org.gradle.internal.execution.steps.AbstractSkipEmptyWorkStep.execute(AbstractSkipEmptyWorkStep.java:61)
	at org.gradle.internal.execution.steps.AbstractSkipEmptyWorkStep.execute(AbstractSkipEmptyWorkStep.java:36)
	at org.gradle.internal.execution.steps.legacy.MarkSnapshottingInputsStartedStep.execute(MarkSnapshottingInputsStartedStep.java:38)
	at org.gradle.internal.execution.steps.LoadPreviousExecutionStateStep.execute(LoadPreviousExecutionStateStep.java:36)
	at org.gradle.internal.execution.steps.LoadPreviousExecutionStateStep.execute(LoadPreviousExecutionStateStep.java:23)
	at org.gradle.internal.execution.steps.HandleStaleOutputsStep.execute(HandleStaleOutputsStep.java:75)
	at org.gradle.internal.execution.steps.HandleStaleOutputsStep.execute(HandleStaleOutputsStep.java:41)
	at org.gradle.internal.execution.steps.AssignMutableWorkspaceStep.lambda$execute$0(AssignMutableWorkspaceStep.java:35)
	at org.gradle.api.internal.tasks.execution.TaskExecution$4.withWorkspace(TaskExecution.java:289)
	at org.gradle.internal.execution.steps.AssignMutableWorkspaceStep.execute(AssignMutableWorkspaceStep.java:31)
	at org.gradle.internal.execution.steps.AssignMutableWorkspaceStep.execute(AssignMutableWorkspaceStep.java:22)
	at org.gradle.internal.execution.steps.ChoosePipelineStep.execute(ChoosePipelineStep.java:40)
	at org.gradle.internal.execution.steps.ChoosePipelineStep.execute(ChoosePipelineStep.java:23)
	at org.gradle.internal.execution.steps.ExecuteWorkBuildOperationFiringStep.lambda$execute$2(ExecuteWorkBuildOperationFiringStep.java:67)
	at org.gradle.internal.execution.steps.ExecuteWorkBuildOperationFiringStep.execute(ExecuteWorkBuildOperationFiringStep.java:67)
	at org.gradle.internal.execution.steps.ExecuteWorkBuildOperationFiringStep.execute(ExecuteWorkBuildOperationFiringStep.java:39)
	at org.gradle.internal.execution.steps.IdentityCacheStep.execute(IdentityCacheStep.java:46)
	at org.gradle.internal.execution.steps.IdentityCacheStep.execute(IdentityCacheStep.java:34)
	at org.gradle.internal.execution.steps.IdentifyStep.execute(IdentifyStep.java:48)
	at org.gradle.internal.execution.steps.IdentifyStep.execute(IdentifyStep.java:35)
	at org.gradle.internal.execution.impl.DefaultExecutionEngine$1.execute(DefaultExecutionEngine.java:61)
	at org.gradle.api.internal.tasks.execution.ExecuteActionsTaskExecuter.executeIfValid(ExecuteActionsTaskExecuter.java:127)
	at org.gradle.api.internal.tasks.execution.ExecuteActionsTaskExecuter.execute(ExecuteActionsTaskExecuter.java:116)
	at org.gradle.api.internal.tasks.execution.FinalizePropertiesTaskExecuter.execute(FinalizePropertiesTaskExecuter.java:46)
	at org.gradle.api.internal.tasks.execution.ResolveTaskExecutionModeExecuter.execute(ResolveTaskExecutionModeExecuter.java:51)
	at org.gradle.api.internal.tasks.execution.SkipTaskWithNoActionsExecuter.execute(SkipTaskWithNoActionsExecuter.java:57)
	at org.gradle.api.internal.tasks.execution.SkipOnlyIfTaskExecuter.execute(SkipOnlyIfTaskExecuter.java:74)
	at org.gradle.api.internal.tasks.execution.CatchExceptionTaskExecuter.execute(CatchExceptionTaskExecuter.java:36)
	at org.gradle.api.internal.tasks.execution.EventFiringTaskExecuter$1.executeTask(EventFiringTaskExecuter.java:77)
	at org.gradle.api.internal.tasks.execution.EventFiringTaskExecuter$1.call(EventFiringTaskExecuter.java:55)
	at org.gradle.api.internal.tasks.execution.EventFiringTaskExecuter$1.call(EventFiringTaskExecuter.java:52)
	at org.gradle.internal.operations.DefaultBuildOperationRunner$CallableBuildOperationWorker.execute(DefaultBuildOperationRunner.java:209)
	at org.gradle.internal.operations.DefaultBuildOperationRunner$CallableBuildOperationWorker.execute(DefaultBuildOperationRunner.java:204)
	at org.gradle.internal.operations.DefaultBuildOperationRunner$2.execute(DefaultBuildOperationRunner.java:66)
	at org.gradle.internal.operations.DefaultBuildOperationRunner$2.execute(DefaultBuildOperationRunner.java:59)
	at org.gradle.internal.operations.DefaultBuildOperationRunner.execute(DefaultBuildOperationRunner.java:166)
	at org.gradle.internal.operations.DefaultBuildOperationRunner.execute(DefaultBuildOperationRunner.java:59)
	at org.gradle.internal.operations.DefaultBuildOperationRunner.call(DefaultBuildOperationRunner.java:53)
	at org.gradle.api.internal.tasks.execution.EventFiringTaskExecuter.execute(EventFiringTaskExecuter.java:52)
	at org.gradle.execution.plan.LocalTaskNodeExecutor.execute(LocalTaskNodeExecutor.java:42)
	at org.gradle.execution.taskgraph.DefaultTaskExecutionGraph$InvokeNodeExecutorsAction.execute(DefaultTaskExecutionGraph.java:331)
	at org.gradle.execution.taskgraph.DefaultTaskExecutionGraph$InvokeNodeExecutorsAction.execute(DefaultTaskExecutionGraph.java:318)
	at org.gradle.execution.taskgraph.DefaultTaskExecutionGraph$BuildOperationAwareExecutionAction.lambda$execute$0(DefaultTaskExecutionGraph.java:314)
	at org.gradle.internal.operations.CurrentBuildOperationRef.with(CurrentBuildOperationRef.java:85)
	at org.gradle.execution.taskgraph.DefaultTaskExecutionGraph$BuildOperationAwareExecutionAction.execute(DefaultTaskExecutionGraph.java:314)
	at org.gradle.execution.taskgraph.DefaultTaskExecutionGraph$BuildOperationAwareExecutionAction.execute(DefaultTaskExecutionGraph.java:303)
	at org.gradle.execution.plan.DefaultPlanExecutor$ExecutorWorker.execute(DefaultPlanExecutor.java:459)
	at org.gradle.execution.plan.DefaultPlanExecutor$ExecutorWorker.run(DefaultPlanExecutor.java:376)
	at org.gradle.internal.concurrent.ExecutorPolicy$CatchAndRecordFailures.onExecute(ExecutorPolicy.java:64)
	at org.gradle.internal.concurrent.AbstractManagedExecutor$1.run(AbstractManagedExecutor.java:48)
Caused by: java.lang.reflect.InvocationTargetException
	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
	at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at io.gitlab.arturbosch.detekt.invoke.DefaultCliInvoker.invokeCli(DetektInvoker.kt:96)
	... 126 more
Caused by: io.github.detekt.tooling.api.MaxIssuesReached: Analysis failed with 771 weighted issues.
	at io.gitlab.arturbosch.detekt.core.config.MaxIssueCheck.check(MaxIssueCheck.kt:36)
	at io.gitlab.arturbosch.detekt.core.tooling.AnalysisFacade.checkMaxIssuesReachedReturningErrors(AnalysisFacade.kt:66)
	at io.gitlab.arturbosch.detekt.core.tooling.AnalysisFacade.runAnalysis$lambda$8(AnalysisFacade.kt:52)
	at io.gitlab.arturbosch.detekt.core.tooling.ProcessingSpecSettingsBridgeKt.withSettings(ProcessingSpecSettingsBridge.kt:26)
	at io.gitlab.arturbosch.detekt.core.tooling.AnalysisFacade.runAnalysis$detekt_core(AnalysisFacade.kt:47)
	at io.gitlab.arturbosch.detekt.core.tooling.AnalysisFacade.run(AnalysisFacade.kt:25)
	at io.gitlab.arturbosch.detekt.cli.runners.Runner.call(Runner.kt:33)
	at io.gitlab.arturbosch.detekt.cli.runners.Runner.execute(Runner.kt:23)
	... 130 more


BUILD FAILED in 17s
1 actionable task: 1 executed
```

