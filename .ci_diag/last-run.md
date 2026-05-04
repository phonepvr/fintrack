# Build summary (run 25299406551, sha e7d0ef19d52f7a6fbd6839a650065f86b32a3985)

| Step | Outcome |
|---|---|
| compile | failure |
| assemble | skipped |
| tests | skipped |
| detekt | skipped |
| locate APK | skipped |

## compile.log — error/warning lines

```
2308:e: file:///home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/picker/ProfilePickerScreen.kt:158:14 This foundation API is experimental and is likely to change or be removed in the future.
2309:e: file:///home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/snapshots/entry/SnapshotEntryScreen.kt:57:8 Unresolved reference 'BigDecimal'.
2310:e: file:///home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/snapshots/entry/SnapshotEntryScreen.kt:79:24 Cannot infer type for this parameter. Specify it explicitly.
2311:e: file:///home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/snapshots/entry/SnapshotEntryScreen.kt:79:24 Not enough information to infer type argument for 'T'.
2312:e: file:///home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/snapshots/entry/SnapshotEntryScreen.kt:80:20 Cannot infer type for this parameter. Specify it explicitly.
2313:e: file:///home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/snapshots/entry/SnapshotEntryScreen.kt:80:20 Not enough information to infer type argument for 'R'.
2314:e: file:///home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/snapshots/entry/SnapshotEntryScreen.kt:80:25 Unresolved reference 'BigDecimal'.
2315:e: file:///home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/snapshots/entry/SnapshotEntryScreen.kt:80:44 Cannot infer type for this parameter. Specify it explicitly.
2316:e: file:///home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/snapshots/entry/SnapshotEntryScreen.kt:343:36 Unresolved reference 'BigDecimal'.
2323:FAILURE: Build failed with an exception.
2368:Caused by: org.gradle.workers.internal.DefaultWorkerExecutor$WorkExecutionException: A failure occurred while executing org.jetbrains.kotlin.compilerRunner.GradleCompilerRunnerWithWorkers$GradleKotlinCompilerWorkAction
2497:Caused by: org.jetbrains.kotlin.gradle.tasks.CompilationErrorException: Compilation error. See log for more details
```

## compile.log — full log (2531 lines)

```
[truncated to first 200 + last 400 lines]
Initialized native services in: /home/runner/.gradle/native
Initialized jansi services in: /home/runner/.gradle/native
To honour the JVM settings for this build a single-use Daemon process will be forked. For more on this, please refer to https://docs.gradle.org/8.11.1/userguide/gradle_daemon.html#sec:disabling_the_daemon in the Gradle documentation.
Starting process 'Gradle build daemon'. Working directory: /home/runner/.gradle/daemon/8.11.1 Command: /usr/lib/jvm/temurin-17-jdk-amd64/bin/java --add-opens=java.base/java.lang=ALL-UNNAMED --add-opens=java.base/java.lang.invoke=ALL-UNNAMED --add-opens=java.base/java.util=ALL-UNNAMED --add-opens=java.prefs/java.util.prefs=ALL-UNNAMED --add-exports=jdk.compiler/com.sun.tools.javac.api=ALL-UNNAMED --add-exports=jdk.compiler/com.sun.tools.javac.util=ALL-UNNAMED --add-opens=java.base/java.util=ALL-UNNAMED --add-opens=java.prefs/java.util.prefs=ALL-UNNAMED --add-opens=java.base/java.nio.charset=ALL-UNNAMED --add-opens=java.base/java.net=ALL-UNNAMED --add-opens=java.base/java.util.concurrent.atomic=ALL-UNNAMED --add-opens=java.xml/javax.xml.namespace=ALL-UNNAMED -XX:+UseParallelGC -Xmx4g -Dfile.encoding=UTF-8 -Duser.country -Duser.language=en -Duser.variant -cp /home/runner/.gradle/wrapper/dists/gradle-8.11.1-bin/bpt9gzteqjrbo1mjrsomdt32c/gradle-8.11.1/lib/gradle-daemon-main-8.11.1.jar -javaagent:/home/runner/.gradle/wrapper/dists/gradle-8.11.1-bin/bpt9gzteqjrbo1mjrsomdt32c/gradle-8.11.1/lib/agents/gradle-instrumentation-agent-8.11.1.jar org.gradle.launcher.daemon.bootstrap.GradleDaemon 8.11.1
Successfully started process 'Gradle build daemon'
An attempt to start the daemon took 1.115 secs.
The client will now receive all logging from the daemon (pid: 2506). The daemon log file: /home/runner/.gradle/daemon/8.11.1/daemon-2506.out.log
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
Transforming shared-31.7.3.jar (com.android.tools.analytics-library:shared:31.7.3) with InstrumentationAnalysisTransform
Transforming shared-31.7.3.jar (com.android.tools.analytics-library:shared:31.7.3) with MergeInstrumentationAnalysisTransform
Transforming databinding-compiler-common-8.7.3.jar (androidx.databinding:databinding-compiler-common:8.7.3) with InstrumentationAnalysisTransform
Transforming databinding-compiler-common-8.7.3.jar (androidx.databinding:databinding-compiler-common:8.7.3) with MergeInstrumentationAnalysisTransform
Transforming android-test-plugin-host-emulator-control-proto-31.7.3.jar (com.android.tools.utp:android-test-plugin-host-emulator-control-proto:31.7.3) with InstrumentationAnalysisTransform
Transforming android-test-plugin-host-emulator-control-proto-31.7.3.jar (com.android.tools.utp:android-test-plugin-host-emulator-control-proto:31.7.3) with MergeInstrumentationAnalysisTransform
Transforming android-test-plugin-host-retention-proto-31.7.3.jar (com.android.tools.utp:android-test-plugin-host-retention-proto:31.7.3) with InstrumentationAnalysisTransform

... (1931 lines elided) ...

Transforming core-common-2.2.0.jar with StructureTransformAction
Transforming collection-jvm-1.4.4.jar with StructureTransformAction
Transforming annotation-jvm-1.9.1.jar with StructureTransformAction
Transforming kotlin-stdlib-jdk8-1.8.22.jar with StructureTransformAction
Transforming kotlin-stdlib-jdk7-1.8.22.jar with StructureTransformAction
Transforming kotlin-stdlib-2.1.20.jar with StructureTransformAction
Transforming sqlcipher-android-4.6.1-api.jar with StructureTransformAction
Transforming compose-2.0.2-api.jar with StructureTransformAction
Transforming core-2.0.2-api.jar with StructureTransformAction
Transforming annotations-23.0.0.jar with StructureTransformAction
Transforming startup-runtime-1.1.1-api.jar with StructureTransformAction
Transforming hilt-core-2.54.jar with StructureTransformAction
Transforming dagger-2.54.jar with StructureTransformAction
Transforming jakarta.inject-api-2.0.1.jar with StructureTransformAction
Transforming javax.inject-1.jar with StructureTransformAction
Transforming jspecify-1.0.0.jar with StructureTransformAction
Transforming dagger-lint-aar-2.54-api.jar with StructureTransformAction
Transforming jsr305-3.0.2.jar with StructureTransformAction
file or directory '/home/runner/work/fintrack/fintrack/app/src/debug/kotlin', not found
file or directory '/home/runner/work/fintrack/fintrack/app/src/debug/java', not found
file or directory '/home/runner/work/fintrack/fintrack/app/src/main/java', not found
file or directory '/home/runner/work/fintrack/fintrack/app/src/main/java', not found
file or directory '/home/runner/work/fintrack/fintrack/app/src/debug/java', not found
Build cache key for task ':app:kspDebugKotlin' is 44ce64821289aa8ce4c030cc7439767b
Task ':app:kspDebugKotlin' is not up-to-date because:
  No history is available.
Loaded cache entry for task ':app:kspDebugKotlin' with cache key 44ce64821289aa8ce4c030cc7439767b
Resolve mutations for :app:compileDebugKotlin (Thread[Execution worker Thread 3,5,main]) started.
:app:compileDebugKotlin (Thread[Execution worker Thread 3,5,main]) started.

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
Transforming annotations-23.0.0.jar with BuildToolsApiClasspathEntrySnapshotTransform
Transforming startup-runtime-1.1.1-api.jar with BuildToolsApiClasspathEntrySnapshotTransform
Transforming hilt-core-2.54.jar with BuildToolsApiClasspathEntrySnapshotTransform
Transforming dagger-2.54.jar with BuildToolsApiClasspathEntrySnapshotTransform
Transforming jakarta.inject-api-2.0.1.jar with BuildToolsApiClasspathEntrySnapshotTransform
Transforming javax.inject-1.jar with BuildToolsApiClasspathEntrySnapshotTransform
Transforming jspecify-1.0.0.jar with BuildToolsApiClasspathEntrySnapshotTransform
Transforming dagger-lint-aar-2.54-api.jar with BuildToolsApiClasspathEntrySnapshotTransform
Transforming jsr305-3.0.2.jar with BuildToolsApiClasspathEntrySnapshotTransform
Build cache key for task ':app:compileDebugKotlin' is 1a99fe54d0248754fcd436c7928a92f1
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
Kotlin source files: /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/kotlin/com/fintrack/data/db/dao/SnapshotDao_Impl.kt, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/kotlin/com/fintrack/data/db/dao/HoldingValueDao_Impl.kt, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/kotlin/com/fintrack/data/db/dao/GlobalSettingsDao_Impl.kt, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/kotlin/com/fintrack/data/db/dao/HoldingDao_Impl.kt, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/kotlin/com/fintrack/data/db/dao/UserSettingsDao_Impl.kt, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/kotlin/com/fintrack/data/db/dao/UserDao_Impl.kt, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/kotlin/com/fintrack/data/db/FintrackDatabase_Impl.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/MainActivity.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/lock/LockScreen.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/lock/BiometricUnavailableScreen.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/AppViewModel.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/picker/ProfilePickerViewModel.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/picker/ProfilePickerScreen.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/snapshots/entry/SnapshotEntryViewModel.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/snapshots/entry/SnapshotEntryScreen.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/snapshots/entry/AmountParsing.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/navigation/AppNavGraph.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/home/snapshots/SnapshotsListViewModel.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/home/snapshots/SnapshotsTab.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/home/HomeScreen.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/theme/Color.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/theme/Theme.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/onboarding/CreateFirstProfileScreen.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/domain/util/CurrencyFormatting.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/domain/UserScope.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/domain/model/AssetClass.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/FintrackApp.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/di/DatabaseModule.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/security/BiometricAuthenticator.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/security/KeystorePassphraseStore.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/security/InactivityTracker.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/data/repo/HoldingRepository.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/data/repo/GlobalSettingsRepository.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/data/repo/SnapshotRepository.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/data/repo/UserRepository.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/data/db/entities/HoldingEntity.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/data/db/entities/UserEntity.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/data/db/entities/GlobalSettingsEntity.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/data/db/entities/UserSettingsEntity.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/data/db/entities/HoldingValueEntity.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/data/db/entities/SnapshotEntity.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/data/db/seed/DevSeedRunner.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/data/db/seed/DevSeedData.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/data/db/seed/SeedData.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/data/db/dao/SnapshotDao.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/data/db/dao/GlobalSettingsDao.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/data/db/dao/UserDao.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/data/db/dao/HoldingValueDao.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/data/db/dao/UserSettingsDao.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/data/db/dao/HoldingDao.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/data/db/Converters.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/data/db/FintrackDatabase.kt
Java source files: /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/com/fintrack/MainActivity_GeneratedInjector.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/com/fintrack/ui/AppViewModel_HiltModules_KeyModule_Provide_LazyMapKey.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/com/fintrack/ui/AppViewModel_HiltModules_KeyModule_ProvideFactory.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/com/fintrack/ui/picker/ProfilePickerViewModel_HiltModules.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/com/fintrack/ui/picker/ProfilePickerViewModel_HiltModules_BindsModule_Binds_LazyMapKey.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/com/fintrack/ui/picker/ProfilePickerViewModel_HiltModules_KeyModule_ProvideFactory.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/com/fintrack/ui/picker/ProfilePickerViewModel_HiltModules_KeyModule_Provide_LazyMapKey.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/com/fintrack/ui/picker/ProfilePickerViewModel_Factory.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/com/fintrack/ui/snapshots/entry/SnapshotEntryViewModel_HiltModules.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/com/fintrack/ui/snapshots/entry/SnapshotEntryViewModel_Factory.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/com/fintrack/ui/snapshots/entry/SnapshotEntryViewModel_HiltModules_KeyModule_Provide_LazyMapKey.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/com/fintrack/ui/snapshots/entry/SnapshotEntryViewModel_HiltModules_KeyModule_ProvideFactory.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/com/fintrack/ui/snapshots/entry/SnapshotEntryViewModel_HiltModules_BindsModule_Binds_LazyMapKey.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/com/fintrack/ui/home/HomeViewModel_Factory.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/com/fintrack/ui/home/HomeViewModel_HiltModules_KeyModule_Provide_LazyMapKey.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/com/fintrack/ui/home/HomeViewModel_HiltModules.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/com/fintrack/ui/home/snapshots/SnapshotsListViewModel_HiltModules_BindsModule_Binds_LazyMapKey.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/com/fintrack/ui/home/snapshots/SnapshotsListViewModel_HiltModules_KeyModule_ProvideFactory.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/com/fintrack/ui/home/snapshots/SnapshotsListViewModel_Factory.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/com/fintrack/ui/home/snapshots/SnapshotsListViewModel_HiltModules.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/com/fintrack/ui/home/snapshots/SnapshotsListViewModel_HiltModules_KeyModule_Provide_LazyMapKey.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/com/fintrack/ui/home/HomeViewModel_HiltModules_KeyModule_ProvideFactory.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/com/fintrack/ui/home/HomeViewModel_HiltModules_BindsModule_Binds_LazyMapKey.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/com/fintrack/ui/AppViewModel_HiltModules.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/com/fintrack/ui/AppViewModel_Factory.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/com/fintrack/ui/onboarding/OnboardingViewModel_Factory.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/com/fintrack/ui/onboarding/OnboardingViewModel_HiltModules_BindsModule_Binds_LazyMapKey.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/com/fintrack/ui/onboarding/OnboardingViewModel_HiltModules_KeyModule_ProvideFactory.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/com/fintrack/ui/onboarding/OnboardingViewModel_HiltModules.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/com/fintrack/ui/onboarding/OnboardingViewModel_HiltModules_KeyModule_Provide_LazyMapKey.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/com/fintrack/ui/AppViewModel_HiltModules_BindsModule_Binds_LazyMapKey.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/com/fintrack/MainActivity_MembersInjector.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/com/fintrack/domain/UserScope_Factory.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/com/fintrack/di/DatabaseModule_ProvideHoldingValueDaoFactory.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/com/fintrack/di/DatabaseModule_ProvideFintrackDatabaseFactory.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/com/fintrack/di/DatabaseModule_ProvideGlobalSettingsDaoFactory.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/com/fintrack/di/DatabaseModule_ProvideUserSettingsDaoFactory.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/com/fintrack/di/DatabaseModule_ProvideHoldingDaoFactory.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/com/fintrack/di/DatabaseModule_ProvideUserDaoFactory.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/com/fintrack/di/DatabaseModule_ProvideSnapshotDaoFactory.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/com/fintrack/Hilt_MainActivity.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/com/fintrack/FintrackApp_MembersInjector.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/com/fintrack/FintrackApp_GeneratedInjector.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/com/fintrack/security/BiometricAuthenticator_Factory.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/com/fintrack/security/KeystorePassphraseStore_Factory.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/com/fintrack/security/InactivityTracker_Factory.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/com/fintrack/data/repo/HoldingRepository_Factory.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/com/fintrack/data/repo/UserRepository_Factory.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/com/fintrack/data/repo/SnapshotRepository_Factory.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/com/fintrack/data/repo/GlobalSettingsRepository_Factory.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/com/fintrack/data/db/seed/DevSeedRunner_Factory.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/hilt_aggregated_deps/_com_fintrack_ui_snapshots_entry_SnapshotEntryViewModel_HiltModules_BindsModule.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/hilt_aggregated_deps/_com_fintrack_ui_home_snapshots_SnapshotsListViewModel_HiltModules_BindsModule.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/hilt_aggregated_deps/_com_fintrack_ui_snapshots_entry_SnapshotEntryViewModel_HiltModules_KeyModule.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/hilt_aggregated_deps/_com_fintrack_ui_home_HomeViewModel_HiltModules_KeyModule.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/hilt_aggregated_deps/_com_fintrack_ui_home_HomeViewModel_HiltModules_BindsModule.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/hilt_aggregated_deps/_com_fintrack_ui_onboarding_OnboardingViewModel_HiltModules_KeyModule.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/hilt_aggregated_deps/_com_fintrack_ui_AppViewModel_HiltModules_BindsModule.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/hilt_aggregated_deps/_com_fintrack_ui_home_snapshots_SnapshotsListViewModel_HiltModules_KeyModule.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/hilt_aggregated_deps/_com_fintrack_MainActivity_GeneratedInjector.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/hilt_aggregated_deps/_com_fintrack_ui_AppViewModel_HiltModules_KeyModule.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/hilt_aggregated_deps/_com_fintrack_FintrackApp_GeneratedInjector.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/hilt_aggregated_deps/_com_fintrack_ui_onboarding_OnboardingViewModel_HiltModules_BindsModule.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/hilt_aggregated_deps/_com_fintrack_ui_picker_ProfilePickerViewModel_HiltModules_KeyModule.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/hilt_aggregated_deps/_com_fintrack_ui_picker_ProfilePickerViewModel_HiltModules_BindsModule.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/hilt_aggregated_deps/_com_fintrack_di_DatabaseModule.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/dagger/hilt/internal/aggregatedroot/codegen/_com_fintrack_FintrackApp.java, /home/runner/work/fintrack/fintrack/app/build/generated/source/buildConfig/debug/com/fintrack/BuildConfig.java
Script source files: 
Script file extensions: 
Using Kotlin/JVM incremental compilation
[KOTLIN] Kotlin compilation 'jdkHome' argument: null
i: starting the daemon as: /usr/lib/jvm/temurin-17-jdk-amd64/bin/java -cp /home/runner/.gradle/caches/modules-2/files-2.1/org.jetbrains.kotlin/kotlin-compiler-embeddable/2.1.20/4ef56b3316798316bfac7a0ae443391c9e900ea1/kotlin-compiler-embeddable-2.1.20.jar:/home/runner/.gradle/caches/modules-2/files-2.1/org.jetbrains.kotlin/kotlin-stdlib/2.1.20/aa8ca79cd50578314f6d1180c47cbe14c0fee567/kotlin-stdlib-2.1.20.jar:/home/runner/.gradle/caches/modules-2/files-2.1/org.jetbrains.kotlin/kotlin-script-runtime/2.1.20/f7c623d7f7bdb01f5ccd6b437bc0a937fcd7c57e/kotlin-script-runtime-2.1.20.jar:/home/runner/.gradle/caches/modules-2/files-2.1/org.jetbrains.kotlin/kotlin-reflect/1.6.10/1cbe9c92c12a94eea200d23c2bbaedaf3daf5132/kotlin-reflect-1.6.10.jar:/home/runner/.gradle/caches/modules-2/files-2.1/org.jetbrains.kotlin/kotlin-daemon-embeddable/2.1.20/95670fce77befd02a70a0bc3abe8ee4533521334/kotlin-daemon-embeddable-2.1.20.jar:/home/runner/.gradle/caches/modules-2/files-2.1/org.jetbrains.intellij.deps/trove4j/1.0.20200330/3afb14d5f9ceb459d724e907a21145e8ff394f02/trove4j-1.0.20200330.jar:/home/runner/.gradle/caches/modules-2/files-2.1/org.jetbrains.kotlinx/kotlinx-coroutines-core-jvm/1.8.0/ac1dc37a30a93150b704022f8d895ee1bd3a36b3/kotlinx-coroutines-core-jvm-1.8.0.jar:/home/runner/.gradle/caches/modules-2/files-2.1/org.jetbrains/annotations/13.0/919f0dfe192fb4e063e7dacadee7f8bb9a2672a9/annotations-13.0.jar -Djava.awt.headless=true -Djava.rmi.server.hostname=127.0.0.1 -Xmx4g -XX:ReservedCodeCacheSize=320m -Dkotlin.environment.keepalive -ea -XX:+UseCodeCacheFlushing -XX:+UseParallelGC -Dkotlin.daemon.initiator.marker.file=/tmp/kotlin-compiler-in-fintrack-7303448297796062996.alive --add-exports java.base/sun.nio.ch=ALL-UNNAMED org.jetbrains.kotlin.daemon.KotlinCompileDaemon --daemon-runFilesPath /home/runner/.kotlin/daemon --daemon-autoshutdownIdleSeconds=7200 --daemon-compilerClasspath /home/runner/.gradle/caches/modules-2/files-2.1/org.jetbrains.kotlin/kotlin-compiler-embeddable/2.1.20/4ef56b3316798316bfac7a0ae443391c9e900ea1/kotlin-compiler-embeddable-2.1.20.jar:/home/runner/.gradle/caches/modules-2/files-2.1/org.jetbrains.kotlin/kotlin-stdlib/2.1.20/aa8ca79cd50578314f6d1180c47cbe14c0fee567/kotlin-stdlib-2.1.20.jar:/home/runner/.gradle/caches/modules-2/files-2.1/org.jetbrains.kotlin/kotlin-script-runtime/2.1.20/f7c623d7f7bdb01f5ccd6b437bc0a937fcd7c57e/kotlin-script-runtime-2.1.20.jar:/home/runner/.gradle/caches/modules-2/files-2.1/org.jetbrains.kotlin/kotlin-reflect/1.6.10/1cbe9c92c12a94eea200d23c2bbaedaf3daf5132/kotlin-reflect-1.6.10.jar:/home/runner/.gradle/caches/modules-2/files-2.1/org.jetbrains.kotlin/kotlin-daemon-embeddable/2.1.20/95670fce77befd02a70a0bc3abe8ee4533521334/kotlin-daemon-embeddable-2.1.20.jar:/home/runner/.gradle/caches/modules-2/files-2.1/org.jetbrains.intellij.deps/trove4j/1.0.20200330/3afb14d5f9ceb459d724e907a21145e8ff394f02/trove4j-1.0.20200330.jar:/home/runner/.gradle/caches/modules-2/files-2.1/org.jetbrains.kotlinx/kotlinx-coroutines-core-jvm/1.8.0/ac1dc37a30a93150b704022f8d895ee1bd3a36b3/kotlinx-coroutines-core-jvm-1.8.0.jar:/home/runner/.gradle/caches/modules-2/files-2.1/org.jetbrains/annotations/13.0/919f0dfe192fb4e063e7dacadee7f8bb9a2672a9/annotations-13.0.jar
i: #1 retrying connecting to the daemon 
Options for KOTLIN DAEMON: IncrementalCompilationOptions(super=CompilationOptions(compilerMode=INCREMENTAL_COMPILER, targetPlatform=JVM, reportCategories=[0, 3], reportSeverity=2, requestedCompilationResults=[0], kotlinScriptExtensions=[]), sourceChanges=org.jetbrains.kotlin.buildtools.api.SourcesChanges$Unknown@d514333, classpathChanges=NotAvailableForNonIncrementalRun, workingDir=/home/runner/work/fintrack/fintrack/app/build/kotlin/compileDebugKotlin/cacheable, multiModuleICSettings=MultiModuleICSettings(buildHistoryFile=/home/runner/work/fintrack/fintrack/app/build/kotlin/compileDebugKotlin/local-state/build-history.bin, useModuleDetection=true), usePreciseJavaTracking=true, icFeatures=IncrementalCompilationFeatures(withAbiSnapshot=false, preciseCompilationResultsBackup=true, keepIncrementalCompilationCachesInMemory=true, enableUnsafeIncrementalCompilationForMultiplatform=false, enableMonotonousIncrementalCompileSetExpansion=true), outputFiles=[/home/runner/work/fintrack/fintrack/app/build/tmp/kotlin-classes/debug, /home/runner/work/fintrack/fintrack/app/build/kotlin/compileDebugKotlin/cacheable, /home/runner/work/fintrack/fintrack/app/build/kotlin/compileDebugKotlin/local-state])
e: file:///home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/picker/ProfilePickerScreen.kt:158:14 This foundation API is experimental and is likely to change or be removed in the future.
e: file:///home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/snapshots/entry/SnapshotEntryScreen.kt:57:8 Unresolved reference 'BigDecimal'.
e: file:///home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/snapshots/entry/SnapshotEntryScreen.kt:79:24 Cannot infer type for this parameter. Specify it explicitly.
e: file:///home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/snapshots/entry/SnapshotEntryScreen.kt:79:24 Not enough information to infer type argument for 'T'.
e: file:///home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/snapshots/entry/SnapshotEntryScreen.kt:80:20 Cannot infer type for this parameter. Specify it explicitly.
e: file:///home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/snapshots/entry/SnapshotEntryScreen.kt:80:20 Not enough information to infer type argument for 'R'.
e: file:///home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/snapshots/entry/SnapshotEntryScreen.kt:80:25 Unresolved reference 'BigDecimal'.
e: file:///home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/snapshots/entry/SnapshotEntryScreen.kt:80:44 Cannot infer type for this parameter. Specify it explicitly.
e: file:///home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/snapshots/entry/SnapshotEntryScreen.kt:343:36 Unresolved reference 'BigDecimal'.
Finished executing kotlin compiler using DAEMON strategy

> Task :app:compileDebugKotlin FAILED
gradle/actions: Writing build results to /home/runner/work/_temp/.gradle-actions/build-results/compile-1777864978679.json
Build b4062336-f19e-43da-963d-f258e787b6c7 is closed

FAILURE: Build failed with an exception.

* What went wrong:
Execution failed for task ':app:compileDebugKotlin'.
> A failure occurred while executing org.jetbrains.kotlin.compilerRunner.GradleCompilerRunnerWithWorkers$GradleKotlinCompilerWorkAction
   > Compilation error. See log for more details

* Try:
> Run with --debug option to get more log output.
> Run with --scan to get full insights.
> Get more help at https://help.gradle.org.

* Exception is:
org.gradle.api.tasks.TaskExecutionException: Execution failed for task ':app:compileDebugKotlin'.
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


BUILD FAILED in 24s
16 actionable tasks: 4 executed, 12 from cache
```

