# Build summary (run 25287041086, sha c3d4eeb713b0d1ec86ea256d20bd0b77299a7cb6)

| Step | Outcome |
|---|---|
| compile | success |
| assemble | success |
| tests | failure |
| detekt | success |
| locate APK | success |

## compile.log — error/warning lines

```
2429:w: file:///home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/di/DatabaseModule.kt:83:17 Type argument for reified type parameter 'T' was inferred to the intersection of ['Comparable<*>' & 'Serializable']. Reification of an intersection type results in the common supertype being used. This may lead to subtle issues and an explicit type argument is encouraged. This will become an error in a future release.
```

## compile.log — full log (2436 lines)

```
[truncated to first 200 + last 400 lines]
Initialized native services in: /home/runner/.gradle/native
Initialized jansi services in: /home/runner/.gradle/native
To honour the JVM settings for this build a single-use Daemon process will be forked. For more on this, please refer to https://docs.gradle.org/8.11.1/userguide/gradle_daemon.html#sec:disabling_the_daemon in the Gradle documentation.
Starting process 'Gradle build daemon'. Working directory: /home/runner/.gradle/daemon/8.11.1 Command: /usr/lib/jvm/temurin-17-jdk-amd64/bin/java --add-opens=java.base/java.lang=ALL-UNNAMED --add-opens=java.base/java.lang.invoke=ALL-UNNAMED --add-opens=java.base/java.util=ALL-UNNAMED --add-opens=java.prefs/java.util.prefs=ALL-UNNAMED --add-exports=jdk.compiler/com.sun.tools.javac.api=ALL-UNNAMED --add-exports=jdk.compiler/com.sun.tools.javac.util=ALL-UNNAMED --add-opens=java.base/java.util=ALL-UNNAMED --add-opens=java.prefs/java.util.prefs=ALL-UNNAMED --add-opens=java.base/java.nio.charset=ALL-UNNAMED --add-opens=java.base/java.net=ALL-UNNAMED --add-opens=java.base/java.util.concurrent.atomic=ALL-UNNAMED --add-opens=java.xml/javax.xml.namespace=ALL-UNNAMED -XX:+UseParallelGC -Xmx4g -Dfile.encoding=UTF-8 -Duser.country -Duser.language=en -Duser.variant -cp /home/runner/.gradle/wrapper/dists/gradle-8.11.1-bin/bpt9gzteqjrbo1mjrsomdt32c/gradle-8.11.1/lib/gradle-daemon-main-8.11.1.jar -javaagent:/home/runner/.gradle/wrapper/dists/gradle-8.11.1-bin/bpt9gzteqjrbo1mjrsomdt32c/gradle-8.11.1/lib/agents/gradle-instrumentation-agent-8.11.1.jar org.gradle.launcher.daemon.bootstrap.GradleDaemon 8.11.1
Successfully started process 'Gradle build daemon'
An attempt to start the daemon took 1.236 secs.
The client will now receive all logging from the daemon (pid: 2521). The daemon log file: /home/runner/.gradle/daemon/8.11.1/daemon-2521.out.log
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

... (1836 lines elided) ...

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
Build cache key for task ':app:kspDebugKotlin' is 1298f43c4b469e3e5b5228f244e100e3
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
Kotlin source files: /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/MainActivity.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/lock/LockScreen.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/lock/BiometricUnavailableScreen.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/AppViewModel.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/navigation/AppNavGraph.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/home/HomeScreen.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/theme/Color.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/theme/Theme.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/onboarding/CreateFirstProfileScreen.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/domain/UserScope.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/domain/model/AssetClass.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/FintrackApp.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/di/DatabaseModule.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/security/BiometricAuthenticator.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/security/KeystorePassphraseStore.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/security/InactivityTracker.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/data/repo/HoldingRepository.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/data/repo/GlobalSettingsRepository.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/data/repo/UserRepository.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/data/db/entities/HoldingEntity.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/data/db/entities/UserEntity.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/data/db/entities/GlobalSettingsEntity.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/data/db/entities/UserSettingsEntity.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/data/db/entities/HoldingValueEntity.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/data/db/entities/SnapshotEntity.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/data/db/seed/SeedData.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/data/db/dao/SnapshotDao.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/data/db/dao/GlobalSettingsDao.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/data/db/dao/UserDao.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/data/db/dao/HoldingValueDao.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/data/db/dao/UserSettingsDao.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/data/db/dao/HoldingDao.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/data/db/Converters.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/data/db/FintrackDatabase.kt
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
Transforming annotations-23.0.0.jar with StructureTransformAction
Transforming startup-runtime-1.1.1-api.jar with StructureTransformAction
Transforming hilt-core-2.54.jar with StructureTransformAction
Transforming dagger-2.54.jar with StructureTransformAction
Transforming jakarta.inject-api-2.0.1.jar with StructureTransformAction
Transforming javax.inject-1.jar with StructureTransformAction
Transforming jspecify-1.0.0.jar with StructureTransformAction
Transforming dagger-lint-aar-2.54-api.jar with StructureTransformAction
Transforming jsr305-3.0.2.jar with StructureTransformAction
[KOTLIN] Kotlin compilation 'jdkHome' argument: null
i: starting the daemon as: /usr/lib/jvm/temurin-17-jdk-amd64/bin/java -cp /home/runner/.gradle/caches/modules-2/files-2.1/org.jetbrains.kotlin/kotlin-compiler-embeddable/2.1.20/4ef56b3316798316bfac7a0ae443391c9e900ea1/kotlin-compiler-embeddable-2.1.20.jar:/home/runner/.gradle/caches/modules-2/files-2.1/org.jetbrains.kotlin/kotlin-stdlib/2.1.20/aa8ca79cd50578314f6d1180c47cbe14c0fee567/kotlin-stdlib-2.1.20.jar:/home/runner/.gradle/caches/modules-2/files-2.1/org.jetbrains.kotlin/kotlin-script-runtime/2.1.20/f7c623d7f7bdb01f5ccd6b437bc0a937fcd7c57e/kotlin-script-runtime-2.1.20.jar:/home/runner/.gradle/caches/modules-2/files-2.1/org.jetbrains.kotlin/kotlin-reflect/1.6.10/1cbe9c92c12a94eea200d23c2bbaedaf3daf5132/kotlin-reflect-1.6.10.jar:/home/runner/.gradle/caches/modules-2/files-2.1/org.jetbrains.kotlin/kotlin-daemon-embeddable/2.1.20/95670fce77befd02a70a0bc3abe8ee4533521334/kotlin-daemon-embeddable-2.1.20.jar:/home/runner/.gradle/caches/modules-2/files-2.1/org.jetbrains.intellij.deps/trove4j/1.0.20200330/3afb14d5f9ceb459d724e907a21145e8ff394f02/trove4j-1.0.20200330.jar:/home/runner/.gradle/caches/modules-2/files-2.1/org.jetbrains.kotlinx/kotlinx-coroutines-core-jvm/1.8.0/ac1dc37a30a93150b704022f8d895ee1bd3a36b3/kotlinx-coroutines-core-jvm-1.8.0.jar:/home/runner/.gradle/caches/modules-2/files-2.1/org.jetbrains/annotations/13.0/919f0dfe192fb4e063e7dacadee7f8bb9a2672a9/annotations-13.0.jar -Djava.awt.headless=true -Djava.rmi.server.hostname=127.0.0.1 -Xmx4g -XX:ReservedCodeCacheSize=320m -Dkotlin.environment.keepalive -ea -XX:+UseCodeCacheFlushing -XX:+UseParallelGC -Dkotlin.daemon.initiator.marker.file=/tmp/kotlin-compiler-in-fintrack-1049177900031360242.alive --add-exports java.base/sun.nio.ch=ALL-UNNAMED org.jetbrains.kotlin.daemon.KotlinCompileDaemon --daemon-runFilesPath /home/runner/.kotlin/daemon --daemon-autoshutdownIdleSeconds=7200 --daemon-compilerClasspath /home/runner/.gradle/caches/modules-2/files-2.1/org.jetbrains.kotlin/kotlin-compiler-embeddable/2.1.20/4ef56b3316798316bfac7a0ae443391c9e900ea1/kotlin-compiler-embeddable-2.1.20.jar:/home/runner/.gradle/caches/modules-2/files-2.1/org.jetbrains.kotlin/kotlin-stdlib/2.1.20/aa8ca79cd50578314f6d1180c47cbe14c0fee567/kotlin-stdlib-2.1.20.jar:/home/runner/.gradle/caches/modules-2/files-2.1/org.jetbrains.kotlin/kotlin-script-runtime/2.1.20/f7c623d7f7bdb01f5ccd6b437bc0a937fcd7c57e/kotlin-script-runtime-2.1.20.jar:/home/runner/.gradle/caches/modules-2/files-2.1/org.jetbrains.kotlin/kotlin-reflect/1.6.10/1cbe9c92c12a94eea200d23c2bbaedaf3daf5132/kotlin-reflect-1.6.10.jar:/home/runner/.gradle/caches/modules-2/files-2.1/org.jetbrains.kotlin/kotlin-daemon-embeddable/2.1.20/95670fce77befd02a70a0bc3abe8ee4533521334/kotlin-daemon-embeddable-2.1.20.jar:/home/runner/.gradle/caches/modules-2/files-2.1/org.jetbrains.intellij.deps/trove4j/1.0.20200330/3afb14d5f9ceb459d724e907a21145e8ff394f02/trove4j-1.0.20200330.jar:/home/runner/.gradle/caches/modules-2/files-2.1/org.jetbrains.kotlinx/kotlinx-coroutines-core-jvm/1.8.0/ac1dc37a30a93150b704022f8d895ee1bd3a36b3/kotlinx-coroutines-core-jvm-1.8.0.jar:/home/runner/.gradle/caches/modules-2/files-2.1/org.jetbrains/annotations/13.0/919f0dfe192fb4e063e7dacadee7f8bb9a2672a9/annotations-13.0.jar
i: #1 retrying connecting to the daemon 
i: [ksp] loaded provider(s): [dagger.hilt.processor.internal.uninstallmodules.KspUninstallModulesProcessor$Provider, dagger.hilt.processor.internal.definecomponent.KspDefineComponentProcessor$Provider, dagger.hilt.android.processor.internal.androidentrypoint.KspAndroidEntryPointProcessor$Provider, dagger.hilt.processor.internal.root.KspRootProcessor$Provider, dagger.hilt.android.processor.internal.viewmodel.KspViewModelProcessor$Provider, dagger.hilt.android.processor.internal.bindvalue.KspBindValueProcessor$Provider, dagger.hilt.processor.internal.root.KspComponentTreeDepsProcessor$Provider, dagger.hilt.processor.internal.aliasof.KspAliasOfProcessor$Provider, dagger.hilt.processor.internal.generatesrootinput.KspGeneratesRootInputProcessor$Provider, dagger.hilt.processor.internal.originatingelement.KspOriginatingElementProcessor$Provider, dagger.hilt.processor.internal.earlyentrypoint.KspEarlyEntryPointProcessor$Provider, dagger.hilt.android.processor.internal.customtestapplication.KspCustomTestApplicationProcessor$Provider, dagger.hilt.processor.internal.aggregateddeps.KspAggregatedDepsProcessor$Provider, androidx.room.RoomKspProcessor$Provider, dagger.internal.codegen.KspComponentProcessor$Provider]
Finished executing kotlin compiler using DAEMON strategy
Stored cache entry for task ':app:kspDebugKotlin' with cache key 1298f43c4b469e3e5b5228f244e100e3
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
Transforming annotations-23.0.0.jar with BuildToolsApiClasspathEntrySnapshotTransform
Transforming startup-runtime-1.1.1-api.jar with BuildToolsApiClasspathEntrySnapshotTransform
Transforming hilt-core-2.54.jar with BuildToolsApiClasspathEntrySnapshotTransform
Transforming dagger-2.54.jar with BuildToolsApiClasspathEntrySnapshotTransform
Transforming jakarta.inject-api-2.0.1.jar with BuildToolsApiClasspathEntrySnapshotTransform
Transforming javax.inject-1.jar with BuildToolsApiClasspathEntrySnapshotTransform
Transforming jspecify-1.0.0.jar with BuildToolsApiClasspathEntrySnapshotTransform
Transforming dagger-lint-aar-2.54-api.jar with BuildToolsApiClasspathEntrySnapshotTransform
Transforming jsr305-3.0.2.jar with BuildToolsApiClasspathEntrySnapshotTransform
Build cache key for task ':app:compileDebugKotlin' is 64ce5cca5890a984ec52c2ec4574c70c
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
Kotlin source files: /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/kotlin/com/fintrack/data/db/dao/SnapshotDao_Impl.kt, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/kotlin/com/fintrack/data/db/dao/HoldingValueDao_Impl.kt, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/kotlin/com/fintrack/data/db/dao/GlobalSettingsDao_Impl.kt, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/kotlin/com/fintrack/data/db/dao/HoldingDao_Impl.kt, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/kotlin/com/fintrack/data/db/dao/UserSettingsDao_Impl.kt, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/kotlin/com/fintrack/data/db/dao/UserDao_Impl.kt, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/kotlin/com/fintrack/data/db/FintrackDatabase_Impl.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/MainActivity.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/lock/LockScreen.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/lock/BiometricUnavailableScreen.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/AppViewModel.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/navigation/AppNavGraph.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/home/HomeScreen.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/theme/Color.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/theme/Theme.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/onboarding/CreateFirstProfileScreen.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/domain/UserScope.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/domain/model/AssetClass.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/FintrackApp.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/di/DatabaseModule.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/security/BiometricAuthenticator.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/security/KeystorePassphraseStore.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/security/InactivityTracker.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/data/repo/HoldingRepository.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/data/repo/GlobalSettingsRepository.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/data/repo/UserRepository.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/data/db/entities/HoldingEntity.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/data/db/entities/UserEntity.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/data/db/entities/GlobalSettingsEntity.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/data/db/entities/UserSettingsEntity.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/data/db/entities/HoldingValueEntity.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/data/db/entities/SnapshotEntity.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/data/db/seed/SeedData.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/data/db/dao/SnapshotDao.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/data/db/dao/GlobalSettingsDao.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/data/db/dao/UserDao.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/data/db/dao/HoldingValueDao.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/data/db/dao/UserSettingsDao.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/data/db/dao/HoldingDao.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/data/db/Converters.kt, /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/data/db/FintrackDatabase.kt
Java source files: /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/com/fintrack/MainActivity_GeneratedInjector.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/com/fintrack/ui/AppViewModel_HiltModules_KeyModule_Provide_LazyMapKey.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/com/fintrack/ui/AppViewModel_HiltModules_KeyModule_ProvideFactory.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/com/fintrack/ui/home/HomeViewModel_Factory.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/com/fintrack/ui/home/HomeViewModel_HiltModules_KeyModule_Provide_LazyMapKey.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/com/fintrack/ui/home/HomeViewModel_HiltModules.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/com/fintrack/ui/home/HomeViewModel_HiltModules_KeyModule_ProvideFactory.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/com/fintrack/ui/home/HomeViewModel_HiltModules_BindsModule_Binds_LazyMapKey.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/com/fintrack/ui/AppViewModel_HiltModules.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/com/fintrack/ui/AppViewModel_Factory.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/com/fintrack/ui/onboarding/OnboardingViewModel_Factory.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/com/fintrack/ui/onboarding/OnboardingViewModel_HiltModules_BindsModule_Binds_LazyMapKey.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/com/fintrack/ui/onboarding/OnboardingViewModel_HiltModules_KeyModule_ProvideFactory.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/com/fintrack/ui/onboarding/OnboardingViewModel_HiltModules.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/com/fintrack/ui/onboarding/OnboardingViewModel_HiltModules_KeyModule_Provide_LazyMapKey.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/com/fintrack/ui/AppViewModel_HiltModules_BindsModule_Binds_LazyMapKey.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/com/fintrack/MainActivity_MembersInjector.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/com/fintrack/domain/UserScope_Factory.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/com/fintrack/di/DatabaseModule_ProvideHoldingValueDaoFactory.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/com/fintrack/di/DatabaseModule_ProvideFintrackDatabaseFactory.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/com/fintrack/di/DatabaseModule_ProvideGlobalSettingsDaoFactory.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/com/fintrack/di/DatabaseModule_ProvideUserSettingsDaoFactory.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/com/fintrack/di/DatabaseModule_ProvideHoldingDaoFactory.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/com/fintrack/di/DatabaseModule_ProvideUserDaoFactory.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/com/fintrack/di/DatabaseModule_ProvideSnapshotDaoFactory.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/com/fintrack/Hilt_MainActivity.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/com/fintrack/FintrackApp_MembersInjector.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/com/fintrack/FintrackApp_GeneratedInjector.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/com/fintrack/security/BiometricAuthenticator_Factory.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/com/fintrack/security/KeystorePassphraseStore_Factory.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/com/fintrack/security/InactivityTracker_Factory.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/com/fintrack/data/repo/HoldingRepository_Factory.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/com/fintrack/data/repo/UserRepository_Factory.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/com/fintrack/data/repo/GlobalSettingsRepository_Factory.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/hilt_aggregated_deps/_com_fintrack_ui_home_HomeViewModel_HiltModules_KeyModule.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/hilt_aggregated_deps/_com_fintrack_ui_home_HomeViewModel_HiltModules_BindsModule.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/hilt_aggregated_deps/_com_fintrack_ui_onboarding_OnboardingViewModel_HiltModules_KeyModule.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/hilt_aggregated_deps/_com_fintrack_ui_AppViewModel_HiltModules_BindsModule.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/hilt_aggregated_deps/_com_fintrack_MainActivity_GeneratedInjector.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/hilt_aggregated_deps/_com_fintrack_ui_AppViewModel_HiltModules_KeyModule.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/hilt_aggregated_deps/_com_fintrack_FintrackApp_GeneratedInjector.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/hilt_aggregated_deps/_com_fintrack_ui_onboarding_OnboardingViewModel_HiltModules_BindsModule.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/hilt_aggregated_deps/_com_fintrack_di_DatabaseModule.java, /home/runner/work/fintrack/fintrack/app/build/generated/ksp/debug/java/dagger/hilt/internal/aggregatedroot/codegen/_com_fintrack_FintrackApp.java, /home/runner/work/fintrack/fintrack/app/build/generated/source/buildConfig/debug/com/fintrack/BuildConfig.java
Script source files: 
Script file extensions: 
Using Kotlin/JVM incremental compilation
[KOTLIN] Kotlin compilation 'jdkHome' argument: null
Options for KOTLIN DAEMON: IncrementalCompilationOptions(super=CompilationOptions(compilerMode=INCREMENTAL_COMPILER, targetPlatform=JVM, reportCategories=[0, 3], reportSeverity=2, requestedCompilationResults=[0], kotlinScriptExtensions=[]), sourceChanges=org.jetbrains.kotlin.buildtools.api.SourcesChanges$Unknown@1300a173, classpathChanges=NotAvailableForNonIncrementalRun, workingDir=/home/runner/work/fintrack/fintrack/app/build/kotlin/compileDebugKotlin/cacheable, multiModuleICSettings=MultiModuleICSettings(buildHistoryFile=/home/runner/work/fintrack/fintrack/app/build/kotlin/compileDebugKotlin/local-state/build-history.bin, useModuleDetection=true), usePreciseJavaTracking=true, icFeatures=IncrementalCompilationFeatures(withAbiSnapshot=false, preciseCompilationResultsBackup=true, keepIncrementalCompilationCachesInMemory=true, enableUnsafeIncrementalCompilationForMultiplatform=false, enableMonotonousIncrementalCompileSetExpansion=true), outputFiles=[/home/runner/work/fintrack/fintrack/app/build/tmp/kotlin-classes/debug, /home/runner/work/fintrack/fintrack/app/build/kotlin/compileDebugKotlin/cacheable, /home/runner/work/fintrack/fintrack/app/build/kotlin/compileDebugKotlin/local-state])
w: file:///home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/di/DatabaseModule.kt:83:17 Type argument for reified type parameter 'T' was inferred to the intersection of ['Comparable<*>' & 'Serializable']. Reification of an intersection type results in the common supertype being used. This may lead to subtle issues and an explicit type argument is encouraged. This will become an error in a future release.
Finished executing kotlin compiler using DAEMON strategy
Stored cache entry for task ':app:compileDebugKotlin' with cache key 64ce5cca5890a984ec52c2ec4574c70c
gradle/actions: Writing build results to /home/runner/work/_temp/.gradle-actions/build-results/compile-1777832507936.json
Build 7bfb9f57-ed50-459a-92b3-13f5427ed3ca is closed

BUILD SUCCESSFUL in 38s
16 actionable tasks: 5 executed, 11 from cache
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
> Task :app:compressDebugAssets
> Task :app:desugarDebugFileDependencies
> Task :app:hiltAggregateDepsDebug
> Task :app:checkDebugDuplicateClasses
> Task :app:hiltJavaCompileDebug
> Task :app:processDebugJavaRes
> Task :app:transformDebugClassesWithAsm
> Task :app:mergeDebugJavaResource
> Task :app:dexBuilderDebug
> Task :app:mergeDebugGlobalSynthetics
> Task :app:mergeLibDexDebug
> Task :app:mergeDebugJniLibFolders
> Task :app:mergeProjectDexDebug
> Task :app:mergeDebugNativeLibs
> Task :app:validateSigningDebug
> Task :app:writeDebugAppMetadata
> Task :app:writeDebugSigningConfigVersions

> Task :app:stripDebugDebugSymbols
Unable to strip the following libraries, packaging them as they are: libandroidx.graphics.path.so, libsqlcipher.so.

> Task :app:mergeExtDexDebug
> Task :app:packageDebug
> Task :app:createDebugApkListingFileRedirect
> Task :app:assembleDebug
gradle/actions: Writing build results to /home/runner/work/_temp/.gradle-actions/build-results/assemble-1777832544765.json

BUILD SUCCESSFUL in 1m 58s
41 actionable tasks: 24 executed, 1 from cache, 16 up-to-date
```

## tests.log — error/warning lines

```
98:FAILURE: Build failed with an exception.
140:Caused by: org.gradle.api.internal.exceptions.MarkedVerificationException: There were failing tests. See the report at: file:///home/runner/work/fintrack/fintrack/app/build/reports/tests/testDebugUnitTest/index.html
```

## tests.log — full log (274 lines)

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
> Task :app:bundleDebugClassesToCompileJar
> Task :app:javaPreCompileDebugUnitTest
> Task :app:kspDebugUnitTestKotlin
> Task :app:compileDebugUnitTestKotlin
> Task :app:compileDebugUnitTestJavaWithJavac NO-SOURCE
> Task :app:hiltAggregateDepsDebugUnitTest
> Task :app:hiltJavaCompileDebugUnitTest NO-SOURCE
> Task :app:processDebugUnitTestJavaRes
> Task :app:transformDebugUnitTestClassesWithAsm

> Task :app:testDebugUnitTest

SeedDataTest > Default holdings contain exactly the 13 rows from spec §3.2 PASSED

SeedDataTest > Holding ids are deterministic across rebuilds PASSED

SeedDataTest > Bank rows are flagged correctly PASSED

SeedDataTest > All four asset classes are represented in the seed catalog PASSED

SeedDataTest > Default aim percentages sum to 100 PASSED

DependencyGraphTest > Version catalog contains no networking / analytics SDKs PASSED

DependencyGraphTest > App build.gradle.kts contains no networking / analytics SDKs PASSED

FlagSecureTest > FLAG_SECURE is set BEFORE setContent so no frame is ever rendered insecurely FAILED
    com.google.common.truth.AssertionErrorWithFacts at FlagSecureTest.kt:38

FlagSecureTest > MainActivity extends FragmentActivity (required by BiometricPrompt) PASSED

FlagSecureTest > MainActivity sets FLAG_SECURE on its window PASSED

ManifestPermissionsTest > AndroidManifest must explicitly forbid cleartext traffic PASSED

ManifestPermissionsTest > AndroidManifest must not declare ACCESS_NETWORK_STATE PASSED

ManifestPermissionsTest > AndroidManifest must disable Auto Backup PASSED

ManifestPermissionsTest > AndroidManifest must not declare android.permission.INTERNET PASSED

ManifestPermissionsTest > AndroidManifest must not declare ACCESS_WIFI_STATE PASSED

MoneyTypesTest > Data and domain layers contain no Double or Float fields PASSED

SnapshotDaoUserScopedTest > HoldingValueDao read methods all take a userId parameter PASSED

SnapshotDaoUserScopedTest > SnapshotDao read methods all take a userId parameter PASSED

SqlCipherEncryptionTest > DatabaseModule loads the SQLCipher native library PASSED

SqlCipherEncryptionTest > DatabaseModule wires Room through SupportOpenHelperFactory with a passphrase PASSED

SqlCipherEncryptionTest > KeystorePassphraseStore generates 256 random bits via SecureRandom PASSED

SqlCipherEncryptionTest > DatabaseModule sources the passphrase from KeystorePassphraseStore PASSED

22 tests completed, 1 failed

> Task :app:testDebugUnitTest FAILED
gradle/actions: Writing build results to /home/runner/work/_temp/.gradle-actions/build-results/tests-1777832663285.json

FAILURE: Build failed with an exception.

* What went wrong:
Execution failed for task ':app:testDebugUnitTest'.
> There were failing tests. See the report at: file:///home/runner/work/fintrack/fintrack/app/build/reports/tests/testDebugUnitTest/index.html

* Try:
> Run with --scan to get full insights.

* Exception is:
org.gradle.api.tasks.TaskExecutionException: Execution failed for task ':app:testDebugUnitTest'.
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
Caused by: org.gradle.api.internal.exceptions.MarkedVerificationException: There were failing tests. See the report at: file:///home/runner/work/fintrack/fintrack/app/build/reports/tests/testDebugUnitTest/index.html
	at org.gradle.api.tasks.testing.AbstractTestTask.handleTestFailures(AbstractTestTask.java:707)
	at org.gradle.api.tasks.testing.AbstractTestTask.handleCollectedResults(AbstractTestTask.java:541)
	at org.gradle.api.tasks.testing.AbstractTestTask.executeTests(AbstractTestTask.java:536)
	at org.gradle.api.tasks.testing.Test.executeTests(Test.java:731)
	at com.android.build.gradle.tasks.factory.AndroidUnitTest.executeTests(AndroidUnitTest.java:171)
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


BUILD FAILED in 42s
35 actionable tasks: 11 executed, 24 up-to-date
```

## detekt.log — error/warning lines

```
149:FAILURE: Build failed with an exception.
193:Caused by: org.gradle.api.GradleException: Analysis failed with 39 weighted issues.
321:Caused by: java.lang.reflect.InvocationTargetException
327:Caused by: io.github.detekt.tooling.api.MaxIssuesReached: Analysis failed with 39 weighted issues.
```

## detekt.log — full log (340 lines)

```
To honour the JVM settings for this build a single-use Daemon process will be forked. For more on this, please refer to https://docs.gradle.org/8.11.1/userguide/gradle_daemon.html#sec:disabling_the_daemon in the Gradle documentation.
Daemon will be stopped at the end of the build 

> Task :detekt FAILED
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/onboarding/CreateFirstProfileScreen.kt:73:5: The function CreateFirstProfileRoute is too long (91). The maximum length is 80. [LongMethod]
/home/runner/work/fintrack/fintrack/app/src/test/kotlin/com/fintrack/privacy/MoneyTypesTest.kt:23:9: Function noFloatingPointInMoneyLayers is nested too deeply. [NestedBlockDepth]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/MainActivity.kt:3:1: Imports must be ordered in lexicographic order without any empty lines in-between with "java", "javax", "kotlin" and aliases in the end [ImportOrdering]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/home/HomeScreen.kt:3:1: Imports must be ordered in lexicographic order without any empty lines in-between with "java", "javax", "kotlin" and aliases in the end [ImportOrdering]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/home/HomeScreen.kt:10:1: Unused import [NoUnusedImports]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/theme/Color.kt:14:46: Unnecessary long whitespace [NoMultipleSpaces]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/theme/Color.kt:15:44: Unnecessary long whitespace [NoMultipleSpaces]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/theme/Color.kt:16:43: Unnecessary long whitespace [NoMultipleSpaces]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/onboarding/CreateFirstProfileScreen.kt:141:61: Missing { ... } [MultiLineIfElse]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/onboarding/CreateFirstProfileScreen.kt:142:50: Missing { ... } [MultiLineIfElse]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/onboarding/CreateFirstProfileScreen.kt:142:1: Unexpected indentation (44) (should be 36) [Indentation]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/onboarding/CreateFirstProfileScreen.kt:8:1: Unused import [NoUnusedImports]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/onboarding/CreateFirstProfileScreen.kt:31:1: Unused import [NoUnusedImports]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/di/DatabaseModule.kt:98:1: Declarations and declarations with annotations should have an empty space between. [SpacingBetweenDeclarationsWithAnnotations]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/di/DatabaseModule.kt:99:1: Declarations and declarations with annotations should have an empty space between. [SpacingBetweenDeclarationsWithAnnotations]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/di/DatabaseModule.kt:100:1: Declarations and declarations with annotations should have an empty space between. [SpacingBetweenDeclarationsWithAnnotations]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/di/DatabaseModule.kt:101:1: Declarations and declarations with annotations should have an empty space between. [SpacingBetweenDeclarationsWithAnnotations]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/di/DatabaseModule.kt:102:1: Declarations and declarations with annotations should have an empty space between. [SpacingBetweenDeclarationsWithAnnotations]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/data/db/dao/GlobalSettingsDao.kt:26:12: Argument should be on a separate line (unless all arguments can fit a single line) [ArgumentListWrapping]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/data/db/dao/GlobalSettingsDao.kt:26:123: Missing newline before ")" [ArgumentListWrapping]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/data/db/dao/GlobalSettingsDao.kt:29:12: Argument should be on a separate line (unless all arguments can fit a single line) [ArgumentListWrapping]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/data/db/dao/GlobalSettingsDao.kt:29:124: Missing newline before ")" [ArgumentListWrapping]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/data/db/dao/HoldingDao.kt:26:12: Argument should be on a separate line (unless all arguments can fit a single line) [ArgumentListWrapping]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/data/db/dao/HoldingDao.kt:26:123: Missing newline before ")" [ArgumentListWrapping]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/data/db/Converters.kt:23:1: Declarations and declarations with annotations should have an empty space between. [SpacingBetweenDeclarationsWithAnnotations]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/data/db/Converters.kt:26:1: Declarations and declarations with annotations should have an empty space between. [SpacingBetweenDeclarationsWithAnnotations]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/data/db/Converters.kt:29:1: Declarations and declarations with annotations should have an empty space between. [SpacingBetweenDeclarationsWithAnnotations]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/data/db/Converters.kt:32:1: Declarations and declarations with annotations should have an empty space between. [SpacingBetweenDeclarationsWithAnnotations]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/data/db/Converters.kt:35:1: Declarations and declarations with annotations should have an empty space between. [SpacingBetweenDeclarationsWithAnnotations]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/lock/LockScreen.kt:31:5: Function names should match the pattern: ^([a-z$][a-zA-Z$0-9]*)|(`.*`)$ [FunctionNaming]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/lock/BiometricUnavailableScreen.kt:27:5: Function names should match the pattern: ^([a-z$][a-zA-Z$0-9]*)|(`.*`)$ [FunctionNaming]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/navigation/AppNavGraph.kt:26:5: Function names should match the pattern: ^([a-z$][a-zA-Z$0-9]*)|(`.*`)$ [FunctionNaming]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/navigation/AppNavGraph.kt:58:13: Function names should match the pattern: ^([a-z$][a-zA-Z$0-9]*)|(`.*`)$ [FunctionNaming]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/home/HomeScreen.kt:76:5: Function names should match the pattern: ^([a-z$][a-zA-Z$0-9]*)|(`.*`)$ [FunctionNaming]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/home/HomeScreen.kt:148:13: Function names should match the pattern: ^([a-z$][a-zA-Z$0-9]*)|(`.*`)$ [FunctionNaming]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/theme/Theme.kt:35:5: Function names should match the pattern: ^([a-z$][a-zA-Z$0-9]*)|(`.*`)$ [FunctionNaming]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/onboarding/CreateFirstProfileScreen.kt:48:7: The file name 'CreateFirstProfileScreen' does not match the name of the single top-level declaration 'OnboardingViewModel'. [MatchingDeclarationName]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/onboarding/CreateFirstProfileScreen.kt:73:5: Function names should match the pattern: ^([a-z$][a-zA-Z$0-9]*)|(`.*`)$ [FunctionNaming]
/home/runner/work/fintrack/fintrack/app/src/test/kotlin/com/fintrack/privacy/SnapshotDaoUserScopedTest.kt:47:9: The loop contains more than one break or continue statement. The code should be refactored to increase readability. [LoopWithTooManyJumpStatements]

/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/onboarding/CreateFirstProfileScreen.kt - 55min debt
	[33mLongMethod - 91/80 - [The function CreateFirstProfileRoute is too long (91). The maximum length is 80.] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/onboarding/CreateFirstProfileScreen.kt:73:5[0m
	[33mMultiLineIfElse - [Missing { ... }] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/onboarding/CreateFirstProfileScreen.kt:141:61[0m
	[33mMultiLineIfElse - [Missing { ... }] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/onboarding/CreateFirstProfileScreen.kt:142:50[0m
	[33mIndentation - [Unexpected indentation (44) (should be 36)] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/onboarding/CreateFirstProfileScreen.kt:142:1[0m
	[33mNoUnusedImports - [Unused import] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/onboarding/CreateFirstProfileScreen.kt:8:1[0m
	[33mNoUnusedImports - [Unused import] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/onboarding/CreateFirstProfileScreen.kt:31:1[0m
	[33mMatchingDeclarationName - [The file name 'CreateFirstProfileScreen' does not match the name of the single t(...)] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/onboarding/CreateFirstProfileScreen.kt:48:7[0m
	[33mFunctionNaming - [Function names should match the pattern: ^([a-z$][a-zA-Z$0-9]*)|(`.*`)$] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/onboarding/CreateFirstProfileScreen.kt:73:5[0m
/home/runner/work/fintrack/fintrack/app/src/test/kotlin/com/fintrack/privacy/MoneyTypesTest.kt - 20min debt
	[33mNestedBlockDepth - 4/4 - [Function noFloatingPointInMoneyLayers is nested too deeply.] at /home/runner/work/fintrack/fintrack/app/src/test/kotlin/com/fintrack/privacy/MoneyTypesTest.kt:23:9[0m
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/MainActivity.kt - 5min debt
	[33mImportOrdering - [Imports must be ordered in lexicographic order without any empty lines in-betwee(...)] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/MainActivity.kt:3:1[0m
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/home/HomeScreen.kt - 20min debt
	[33mImportOrdering - [Imports must be ordered in lexicographic order without any empty lines in-betwee(...)] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/home/HomeScreen.kt:3:1[0m
	[33mNoUnusedImports - [Unused import] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/home/HomeScreen.kt:10:1[0m
	[33mFunctionNaming - [Function names should match the pattern: ^([a-z$][a-zA-Z$0-9]*)|(`.*`)$] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/home/HomeScreen.kt:76:5[0m
	[33mFunctionNaming - [Function names should match the pattern: ^([a-z$][a-zA-Z$0-9]*)|(`.*`)$] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/home/HomeScreen.kt:148:13[0m
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/theme/Color.kt - 15min debt
	[33mNoMultipleSpaces - [Unnecessary long whitespace] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/theme/Color.kt:14:46[0m
	[33mNoMultipleSpaces - [Unnecessary long whitespace] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/theme/Color.kt:15:44[0m
	[33mNoMultipleSpaces - [Unnecessary long whitespace] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/theme/Color.kt:16:43[0m
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/di/DatabaseModule.kt - 25min debt
	[33mSpacingBetweenDeclarationsWithAnnotations - [Declarations and declarations with annotations should have an empty space betwee(...)] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/di/DatabaseModule.kt:98:1[0m
	[33mSpacingBetweenDeclarationsWithAnnotations - [Declarations and declarations with annotations should have an empty space betwee(...)] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/di/DatabaseModule.kt:99:1[0m
	[33mSpacingBetweenDeclarationsWithAnnotations - [Declarations and declarations with annotations should have an empty space betwee(...)] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/di/DatabaseModule.kt:100:1[0m
	[33mSpacingBetweenDeclarationsWithAnnotations - [Declarations and declarations with annotations should have an empty space betwee(...)] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/di/DatabaseModule.kt:101:1[0m
	[33mSpacingBetweenDeclarationsWithAnnotations - [Declarations and declarations with annotations should have an empty space betwee(...)] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/di/DatabaseModule.kt:102:1[0m
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/data/db/dao/GlobalSettingsDao.kt - 20min debt
	[33mArgumentListWrapping - [Argument should be on a separate line (unless all arguments can fit a single lin(...)] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/data/db/dao/GlobalSettingsDao.kt:26:12[0m
	[33mArgumentListWrapping - [Missing newline before ")"] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/data/db/dao/GlobalSettingsDao.kt:26:123[0m
	[33mArgumentListWrapping - [Argument should be on a separate line (unless all arguments can fit a single lin(...)] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/data/db/dao/GlobalSettingsDao.kt:29:12[0m
	[33mArgumentListWrapping - [Missing newline before ")"] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/data/db/dao/GlobalSettingsDao.kt:29:124[0m
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/data/db/dao/HoldingDao.kt - 10min debt
	[33mArgumentListWrapping - [Argument should be on a separate line (unless all arguments can fit a single lin(...)] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/data/db/dao/HoldingDao.kt:26:12[0m
	[33mArgumentListWrapping - [Missing newline before ")"] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/data/db/dao/HoldingDao.kt:26:123[0m
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/data/db/Converters.kt - 25min debt
	[33mSpacingBetweenDeclarationsWithAnnotations - [Declarations and declarations with annotations should have an empty space betwee(...)] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/data/db/Converters.kt:23:1[0m
	[33mSpacingBetweenDeclarationsWithAnnotations - [Declarations and declarations with annotations should have an empty space betwee(...)] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/data/db/Converters.kt:26:1[0m
	[33mSpacingBetweenDeclarationsWithAnnotations - [Declarations and declarations with annotations should have an empty space betwee(...)] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/data/db/Converters.kt:29:1[0m
	[33mSpacingBetweenDeclarationsWithAnnotations - [Declarations and declarations with annotations should have an empty space betwee(...)] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/data/db/Converters.kt:32:1[0m
	[33mSpacingBetweenDeclarationsWithAnnotations - [Declarations and declarations with annotations should have an empty space betwee(...)] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/data/db/Converters.kt:35:1[0m
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/lock/LockScreen.kt - 5min debt
	[33mFunctionNaming - [Function names should match the pattern: ^([a-z$][a-zA-Z$0-9]*)|(`.*`)$] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/lock/LockScreen.kt:31:5[0m
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/lock/BiometricUnavailableScreen.kt - 5min debt
	[33mFunctionNaming - [Function names should match the pattern: ^([a-z$][a-zA-Z$0-9]*)|(`.*`)$] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/lock/BiometricUnavailableScreen.kt:27:5[0m
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/navigation/AppNavGraph.kt - 10min debt
	[33mFunctionNaming - [Function names should match the pattern: ^([a-z$][a-zA-Z$0-9]*)|(`.*`)$] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/navigation/AppNavGraph.kt:26:5[0m
	[33mFunctionNaming - [Function names should match the pattern: ^([a-z$][a-zA-Z$0-9]*)|(`.*`)$] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/navigation/AppNavGraph.kt:58:13[0m
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/theme/Theme.kt - 5min debt
	[33mFunctionNaming - [Function names should match the pattern: ^([a-z$][a-zA-Z$0-9]*)|(`.*`)$] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/theme/Theme.kt:35:5[0m
/home/runner/work/fintrack/fintrack/app/src/test/kotlin/com/fintrack/privacy/SnapshotDaoUserScopedTest.kt - 10min debt
	[33mLoopWithTooManyJumpStatements - [The loop contains more than one break or continue statement. The code should be (...)] at /home/runner/work/fintrack/fintrack/app/src/test/kotlin/com/fintrack/privacy/SnapshotDaoUserScopedTest.kt:47:9[0m

Overall debt: 3h 50min

complexity - 40min debt
	[33mLongMethod - 91/80 - [The function CreateFirstProfileRoute is too long (91). The maximum length is 80.] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/onboarding/CreateFirstProfileScreen.kt:73:5[0m
	[33mNestedBlockDepth - 4/4 - [Function noFloatingPointInMoneyLayers is nested too deeply.] at /home/runner/work/fintrack/fintrack/app/src/test/kotlin/com/fintrack/privacy/MoneyTypesTest.kt:23:9[0m
formatting - 2h 15min debt
	[33mImportOrdering - [Imports must be ordered in lexicographic order without any empty lines in-betwee(...)] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/MainActivity.kt:3:1[0m
	[33mImportOrdering - [Imports must be ordered in lexicographic order without any empty lines in-betwee(...)] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/home/HomeScreen.kt:3:1[0m
	[33mNoUnusedImports - [Unused import] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/home/HomeScreen.kt:10:1[0m
	[33mNoMultipleSpaces - [Unnecessary long whitespace] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/theme/Color.kt:14:46[0m
	[33mNoMultipleSpaces - [Unnecessary long whitespace] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/theme/Color.kt:15:44[0m
	[33mNoMultipleSpaces - [Unnecessary long whitespace] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/theme/Color.kt:16:43[0m
	[33mMultiLineIfElse - [Missing { ... }] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/onboarding/CreateFirstProfileScreen.kt:141:61[0m
	[33mMultiLineIfElse - [Missing { ... }] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/onboarding/CreateFirstProfileScreen.kt:142:50[0m
	[33mIndentation - [Unexpected indentation (44) (should be 36)] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/onboarding/CreateFirstProfileScreen.kt:142:1[0m
	[33mNoUnusedImports - [Unused import] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/onboarding/CreateFirstProfileScreen.kt:8:1[0m
	[33mNoUnusedImports - [Unused import] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/onboarding/CreateFirstProfileScreen.kt:31:1[0m
	[33mSpacingBetweenDeclarationsWithAnnotations - [Declarations and declarations with annotations should have an empty space betwee(...)] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/di/DatabaseModule.kt:98:1[0m
	[33mSpacingBetweenDeclarationsWithAnnotations - [Declarations and declarations with annotations should have an empty space betwee(...)] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/di/DatabaseModule.kt:99:1[0m
	[33mSpacingBetweenDeclarationsWithAnnotations - [Declarations and declarations with annotations should have an empty space betwee(...)] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/di/DatabaseModule.kt:100:1[0m
	[33mSpacingBetweenDeclarationsWithAnnotations - [Declarations and declarations with annotations should have an empty space betwee(...)] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/di/DatabaseModule.kt:101:1[0m
	[33mSpacingBetweenDeclarationsWithAnnotations - [Declarations and declarations with annotations should have an empty space betwee(...)] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/di/DatabaseModule.kt:102:1[0m
	[33mArgumentListWrapping - [Argument should be on a separate line (unless all arguments can fit a single lin(...)] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/data/db/dao/GlobalSettingsDao.kt:26:12[0m
	[33mArgumentListWrapping - [Missing newline before ")"] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/data/db/dao/GlobalSettingsDao.kt:26:123[0m
	[33mArgumentListWrapping - [Argument should be on a separate line (unless all arguments can fit a single lin(...)] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/data/db/dao/GlobalSettingsDao.kt:29:12[0m
	[33mArgumentListWrapping - [Missing newline before ")"] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/data/db/dao/GlobalSettingsDao.kt:29:124[0m
	[33mArgumentListWrapping - [Argument should be on a separate line (unless all arguments can fit a single lin(...)] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/data/db/dao/HoldingDao.kt:26:12[0m
	[33mArgumentListWrapping - [Missing newline before ")"] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/data/db/dao/HoldingDao.kt:26:123[0m
	[33mSpacingBetweenDeclarationsWithAnnotations - [Declarations and declarations with annotations should have an empty space betwee(...)] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/data/db/Converters.kt:23:1[0m
	[33mSpacingBetweenDeclarationsWithAnnotations - [Declarations and declarations with annotations should have an empty space betwee(...)] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/data/db/Converters.kt:26:1[0m
	[33mSpacingBetweenDeclarationsWithAnnotations - [Declarations and declarations with annotations should have an empty space betwee(...)] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/data/db/Converters.kt:29:1[0m
	[33mSpacingBetweenDeclarationsWithAnnotations - [Declarations and declarations with annotations should have an empty space betwee(...)] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/data/db/Converters.kt:32:1[0m
	[33mSpacingBetweenDeclarationsWithAnnotations - [Declarations and declarations with annotations should have an empty space betwee(...)] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/data/db/Converters.kt:35:1[0m
naming - 45min debt
	[33mFunctionNaming - [Function names should match the pattern: ^([a-z$][a-zA-Z$0-9]*)|(`.*`)$] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/lock/LockScreen.kt:31:5[0m
	[33mFunctionNaming - [Function names should match the pattern: ^([a-z$][a-zA-Z$0-9]*)|(`.*`)$] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/lock/BiometricUnavailableScreen.kt:27:5[0m
	[33mFunctionNaming - [Function names should match the pattern: ^([a-z$][a-zA-Z$0-9]*)|(`.*`)$] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/navigation/AppNavGraph.kt:26:5[0m
	[33mFunctionNaming - [Function names should match the pattern: ^([a-z$][a-zA-Z$0-9]*)|(`.*`)$] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/navigation/AppNavGraph.kt:58:13[0m
	[33mFunctionNaming - [Function names should match the pattern: ^([a-z$][a-zA-Z$0-9]*)|(`.*`)$] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/home/HomeScreen.kt:76:5[0m
	[33mFunctionNaming - [Function names should match the pattern: ^([a-z$][a-zA-Z$0-9]*)|(`.*`)$] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/home/HomeScreen.kt:148:13[0m
	[33mFunctionNaming - [Function names should match the pattern: ^([a-z$][a-zA-Z$0-9]*)|(`.*`)$] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/theme/Theme.kt:35:5[0m
	[33mMatchingDeclarationName - [The file name 'CreateFirstProfileScreen' does not match the name of the single t(...)] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/onboarding/CreateFirstProfileScreen.kt:48:7[0m
	[33mFunctionNaming - [Function names should match the pattern: ^([a-z$][a-zA-Z$0-9]*)|(`.*`)$] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/onboarding/CreateFirstProfileScreen.kt:73:5[0m
style - 10min debt
	[33mLoopWithTooManyJumpStatements - [The loop contains more than one break or continue statement. The code should be (...)] at /home/runner/work/fintrack/fintrack/app/src/test/kotlin/com/fintrack/privacy/SnapshotDaoUserScopedTest.kt:47:9[0m

Overall debt: 3h 50min

gradle/actions: Writing build results to /home/runner/work/_temp/.gradle-actions/build-results/detekt-1777832706143.json

FAILURE: Build failed with an exception.

* What went wrong:
Execution failed for task ':detekt'.
> Analysis failed with 39 weighted issues.

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
Caused by: org.gradle.api.GradleException: Analysis failed with 39 weighted issues.
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
Caused by: io.github.detekt.tooling.api.MaxIssuesReached: Analysis failed with 39 weighted issues.
	at io.gitlab.arturbosch.detekt.core.config.MaxIssueCheck.check(MaxIssueCheck.kt:36)
	at io.gitlab.arturbosch.detekt.core.tooling.AnalysisFacade.checkMaxIssuesReachedReturningErrors(AnalysisFacade.kt:66)
	at io.gitlab.arturbosch.detekt.core.tooling.AnalysisFacade.runAnalysis$lambda$8(AnalysisFacade.kt:52)
	at io.gitlab.arturbosch.detekt.core.tooling.ProcessingSpecSettingsBridgeKt.withSettings(ProcessingSpecSettingsBridge.kt:26)
	at io.gitlab.arturbosch.detekt.core.tooling.AnalysisFacade.runAnalysis$detekt_core(AnalysisFacade.kt:47)
	at io.gitlab.arturbosch.detekt.core.tooling.AnalysisFacade.run(AnalysisFacade.kt:25)
	at io.gitlab.arturbosch.detekt.cli.runners.Runner.call(Runner.kt:33)
	at io.gitlab.arturbosch.detekt.cli.runners.Runner.execute(Runner.kt:23)
	... 130 more


BUILD FAILED in 18s
1 actionable task: 1 executed
```

## Failed test: app/build/test-results/testDebugUnitTest/TEST-com.fintrack.privacy.FlagSecureTest.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<testsuite name="com.fintrack.privacy.FlagSecureTest" tests="3" skipped="0" failures="1" errors="0" timestamp="2026-05-03T18:25:01" hostname="runnervmeorf1" time="0.071">
  <properties/>
  <testcase name="FLAG_SECURE is set BEFORE setContent so no frame is ever rendered insecurely" classname="com.fintrack.privacy.FlagSecureTest" time="0.068">
    <failure message="expected to be less than: 114&#10;but was                 : 615" type="com.google.common.truth.AssertionErrorWithFacts">expected to be less than: 114
but was                 : 615
	at app//com.fintrack.privacy.FlagSecureTest.flagBeforeSetContent(FlagSecureTest.kt:38)
	at [[Reflective call: 4 frames collapsed (https://goo.gl/aH3UyP)]].(:0)
	at [[Testing framework: 27 frames collapsed (https://goo.gl/aH3UyP)]].(:0)
	at java.base@17.0.18/java.util.ArrayList.forEach(ArrayList.java:1511)
	at [[Testing framework: 9 frames collapsed (https://goo.gl/aH3UyP)]].(:0)
	at java.base@17.0.18/java.util.ArrayList.forEach(ArrayList.java:1511)
	at [[Testing framework: 21 frames collapsed (https://goo.gl/aH3UyP)]].(:0)
	at org.gradle.api.internal.tasks.testing.junitplatform.JUnitPlatformTestClassProcessor$CollectAllTestClassesExecutor.processAllTestClasses(JUnitPlatformTestClassProcessor.java:124)
	at org.gradle.api.internal.tasks.testing.junitplatform.JUnitPlatformTestClassProcessor$CollectAllTestClassesExecutor.access$000(JUnitPlatformTestClassProcessor.java:99)
	at org.gradle.api.internal.tasks.testing.junitplatform.JUnitPlatformTestClassProcessor.stop(JUnitPlatformTestClassProcessor.java:94)
	at org.gradle.api.internal.tasks.testing.SuiteTestClassProcessor.stop(SuiteTestClassProcessor.java:63)
	at [[Reflective call: 4 frames collapsed (https://goo.gl/aH3UyP)]].(:0)
	at org.gradle.internal.dispatch.ReflectionDispatch.dispatch(ReflectionDispatch.java:36)
	at org.gradle.internal.dispatch.ReflectionDispatch.dispatch(ReflectionDispatch.java:24)
	at org.gradle.internal.dispatch.ContextClassLoaderDispatch.dispatch(ContextClassLoaderDispatch.java:33)
	at org.gradle.internal.dispatch.ProxyDispatchAdapter$DispatchingInvocationHandler.invoke(ProxyDispatchAdapter.java:92)
	at jdk.proxy1/jdk.proxy1.$Proxy4.stop(Unknown Source)
	at org.gradle.api.internal.tasks.testing.worker.TestWorker$3.run(TestWorker.java:200)
	at org.gradle.api.internal.tasks.testing.worker.TestWorker.executeAndMaintainThreadName(TestWorker.java:132)
	at org.gradle.api.internal.tasks.testing.worker.TestWorker.execute(TestWorker.java:103)
	at org.gradle.api.internal.tasks.testing.worker.TestWorker.execute(TestWorker.java:63)
	at org.gradle.process.internal.worker.child.ActionExecutionWorker.execute(ActionExecutionWorker.java:56)
	at org.gradle.process.internal.worker.child.SystemApplicationClassLoaderWorker.call(SystemApplicationClassLoaderWorker.java:121)
	at org.gradle.process.internal.worker.child.SystemApplicationClassLoaderWorker.call(SystemApplicationClassLoaderWorker.java:71)
	at app//worker.org.gradle.process.internal.worker.GradleWorkerMain.run(GradleWorkerMain.java:69)
	at app//worker.org.gradle.process.internal.worker.GradleWorkerMain.main(GradleWorkerMain.java:74)
</failure>
  </testcase>
  <testcase name="MainActivity extends FragmentActivity (required by BiometricPrompt)" classname="com.fintrack.privacy.FlagSecureTest" time="0.002"/>
  <testcase name="MainActivity sets FLAG_SECURE on its window" classname="com.fintrack.privacy.FlagSecureTest" time="0.001"/>
  <system-out><![CDATA[]]></system-out>
  <system-err><![CDATA[]]></system-err>
</testsuite>
```

