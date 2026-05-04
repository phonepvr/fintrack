# Build summary (run 25327346249, sha 5d05d7bc546176e71fb68afff9ba81fde026fddf)

| Step | Outcome |
|---|---|
| compile | failure |
| assemble | skipped |
| tests | skipped |
| detekt | skipped |
| locate APK | skipped |

## compile.log — error/warning lines

```
631:e: file:///home/runner/work/fintrack/fintrack/app/build.gradle.kts:28:18: Unresolved reference: io
632:e: file:///home/runner/work/fintrack/fintrack/app/build.gradle.kts:54:34: Unresolved reference: io
636:FAILURE: Build failed with an exception.
```

## compile.log — full log (913 lines)

```
[truncated to first 200 + last 400 lines]
Initialized native services in: /home/runner/.gradle/native
Initialized jansi services in: /home/runner/.gradle/native
To honour the JVM settings for this build a single-use Daemon process will be forked. For more on this, please refer to https://docs.gradle.org/8.11.1/userguide/gradle_daemon.html#sec:disabling_the_daemon in the Gradle documentation.
Starting process 'Gradle build daemon'. Working directory: /home/runner/.gradle/daemon/8.11.1 Command: /usr/lib/jvm/temurin-17-jdk-amd64/bin/java --add-opens=java.base/java.lang=ALL-UNNAMED --add-opens=java.base/java.lang.invoke=ALL-UNNAMED --add-opens=java.base/java.util=ALL-UNNAMED --add-opens=java.prefs/java.util.prefs=ALL-UNNAMED --add-exports=jdk.compiler/com.sun.tools.javac.api=ALL-UNNAMED --add-exports=jdk.compiler/com.sun.tools.javac.util=ALL-UNNAMED --add-opens=java.base/java.util=ALL-UNNAMED --add-opens=java.prefs/java.util.prefs=ALL-UNNAMED --add-opens=java.base/java.nio.charset=ALL-UNNAMED --add-opens=java.base/java.net=ALL-UNNAMED --add-opens=java.base/java.util.concurrent.atomic=ALL-UNNAMED --add-opens=java.xml/javax.xml.namespace=ALL-UNNAMED -XX:+UseParallelGC -Xmx4g -Dfile.encoding=UTF-8 -Duser.country -Duser.language=en -Duser.variant -cp /home/runner/.gradle/wrapper/dists/gradle-8.11.1-bin/bpt9gzteqjrbo1mjrsomdt32c/gradle-8.11.1/lib/gradle-daemon-main-8.11.1.jar -javaagent:/home/runner/.gradle/wrapper/dists/gradle-8.11.1-bin/bpt9gzteqjrbo1mjrsomdt32c/gradle-8.11.1/lib/agents/gradle-instrumentation-agent-8.11.1.jar org.gradle.launcher.daemon.bootstrap.GradleDaemon 8.11.1
Successfully started process 'Gradle build daemon'
An attempt to start the daemon took 1.173 secs.
The client will now receive all logging from the daemon (pid: 2509). The daemon log file: /home/runner/.gradle/daemon/8.11.1/daemon-2509.out.log
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

... (313 lines elided) ...

Transforming aapt2-proto-8.7.3-12006047.jar (com.android.tools.build:aapt2-proto:8.7.3-12006047) with ExternalDependencyInstrumentingArtifactTransform
Transforming lint-typedef-remover-31.7.3.jar (com.android.tools.lint:lint-typedef-remover:31.7.3) with ExternalDependencyInstrumentingArtifactTransform
Transforming databinding-common-8.7.3.jar (androidx.databinding:databinding-common:8.7.3) with ExternalDependencyInstrumentingArtifactTransform
Transforming baseLibrary-8.7.3.jar (com.android.databinding:baseLibrary:8.7.3) with ExternalDependencyInstrumentingArtifactTransform
Transforming android-device-provider-ddmlib-proto-31.7.3.jar (com.android.tools.utp:android-device-provider-ddmlib-proto:31.7.3) with ExternalDependencyInstrumentingArtifactTransform
Transforming android-device-provider-profile-proto-31.7.3.jar (com.android.tools.utp:android-device-provider-profile-proto:31.7.3) with ExternalDependencyInstrumentingArtifactTransform
Transforming android-device-provider-gradle-proto-31.7.3.jar (com.android.tools.utp:android-device-provider-gradle-proto:31.7.3) with ExternalDependencyInstrumentingArtifactTransform
Transforming android-test-plugin-host-additional-test-output-proto-31.7.3.jar (com.android.tools.utp:android-test-plugin-host-additional-test-output-proto:31.7.3) with ExternalDependencyInstrumentingArtifactTransform
Transforming android-test-plugin-host-logcat-proto-31.7.3.jar (com.android.tools.utp:android-test-plugin-host-logcat-proto:31.7.3) with ExternalDependencyInstrumentingArtifactTransform
Transforming android-test-plugin-result-listener-gradle-proto-31.7.3.jar (com.android.tools.utp:android-test-plugin-result-listener-gradle-proto:31.7.3) with ExternalDependencyInstrumentingArtifactTransform
Transforming android-test-plugin-host-coverage-proto-31.7.3.jar (com.android.tools.utp:android-test-plugin-host-coverage-proto:31.7.3) with ExternalDependencyInstrumentingArtifactTransform
Transforming transform-api-2.0.0-deprecated-use-gradle-api.jar (com.android.tools.build:transform-api:2.0.0-deprecated-use-gradle-api) with ExternalDependencyInstrumentingArtifactTransform
Transforming android-test-plugin-host-apk-installer-proto-31.7.3.jar (com.android.tools.utp:android-test-plugin-host-apk-installer-proto:31.7.3) with ExternalDependencyInstrumentingArtifactTransform
Transforming httpmime-4.5.6.jar (org.apache.httpcomponents:httpmime:4.5.6) with ExternalDependencyInstrumentingArtifactTransform
Transforming commons-io-2.13.0.jar (commons-io:commons-io:2.13.0) with ExternalDependencyInstrumentingArtifactTransform
Transforming asm-analysis-9.6.jar (org.ow2.asm:asm-analysis:9.6) with ExternalDependencyInstrumentingArtifactTransform
Transforming asm-commons-9.6.jar (org.ow2.asm:asm-commons:9.6) with ExternalDependencyInstrumentingArtifactTransform
Transforming asm-util-9.6.jar (org.ow2.asm:asm-util:9.6) with ExternalDependencyInstrumentingArtifactTransform
Transforming asm-tree-9.6.jar (org.ow2.asm:asm-tree:9.6) with ExternalDependencyInstrumentingArtifactTransform
Transforming asm-9.6.jar (org.ow2.asm:asm:9.6) with ExternalDependencyInstrumentingArtifactTransform
Transforming apkzlib-8.7.3.jar (com.android.tools.build:apkzlib:8.7.3) with ExternalDependencyInstrumentingArtifactTransform
Transforming bcpkix-jdk18on-1.77.jar (org.bouncycastle:bcpkix-jdk18on:1.77) with ExternalDependencyInstrumentingArtifactTransform
Transforming jaxb-runtime-2.3.2.jar (org.glassfish.jaxb:jaxb-runtime:2.3.2) with ExternalDependencyInstrumentingArtifactTransform
Transforming jopt-simple-4.9.jar (net.sf.jopt-simple:jopt-simple:4.9) with ExternalDependencyInstrumentingArtifactTransform
Transforming javapoet-1.13.0.jar (com.squareup:javapoet:1.13.0) with ExternalDependencyInstrumentingArtifactTransform
Transforming protobuf-java-util-3.22.3.jar (com.google.protobuf:protobuf-java-util:3.22.3) with ExternalDependencyInstrumentingArtifactTransform
Transforming grpc-protobuf-1.57.0.jar (io.grpc:grpc-protobuf:1.57.0) with ExternalDependencyInstrumentingArtifactTransform
Transforming tink-1.7.0.jar (com.google.crypto.tink:tink:1.7.0) with ExternalDependencyInstrumentingArtifactTransform
Transforming protos-31.7.3.jar (com.android.tools.analytics-library:protos:31.7.3) with ExternalDependencyInstrumentingArtifactTransform
Transforming proto-google-common-protos-2.17.0.jar (com.google.api.grpc:proto-google-common-protos:2.17.0) with ExternalDependencyInstrumentingArtifactTransform
Transforming grpc-netty-1.57.0.jar (io.grpc:grpc-netty:1.57.0) with ExternalDependencyInstrumentingArtifactTransform
Transforming protobuf-java-3.22.3.jar (com.google.protobuf:protobuf-java:3.22.3) with ExternalDependencyInstrumentingArtifactTransform
Transforming grpc-core-1.57.0.jar (io.grpc:grpc-core:1.57.0) with ExternalDependencyInstrumentingArtifactTransform
Transforming kotlin-build-statistics-2.1.20.jar (org.jetbrains.kotlin:kotlin-build-statistics:2.1.20) with ExternalDependencyInstrumentingArtifactTransform
Transforming gson-2.11.0.jar (com.google.code.gson:gson:2.11.0) with ExternalDependencyInstrumentingArtifactTransform
Transforming core-proto-0.0.9-alpha02.jar (com.google.testing.platform:core-proto:0.0.9-alpha02) with ExternalDependencyInstrumentingArtifactTransform
Transforming grpc-stub-1.57.0.jar (io.grpc:grpc-stub:1.57.0) with ExternalDependencyInstrumentingArtifactTransform
Transforming tensorflow-lite-metadata-0.1.0-rc2.jar (org.tensorflow:tensorflow-lite-metadata:0.1.0-rc2) with ExternalDependencyInstrumentingArtifactTransform
Transforming flatbuffers-java-1.12.0.jar (com.google.flatbuffers:flatbuffers-java:1.12.0) with ExternalDependencyInstrumentingArtifactTransform
Transforming kotlin-gradle-plugin-idea-proto-2.1.20.jar (org.jetbrains.kotlin:kotlin-gradle-plugin-idea-proto:2.1.20) with ExternalDependencyInstrumentingArtifactTransform
Transforming kotlin-gradle-plugin-idea-2.1.20.jar (org.jetbrains.kotlin:kotlin-gradle-plugin-idea:2.1.20) with ExternalDependencyInstrumentingArtifactTransform
Transforming kotlin-klib-commonizer-api-2.1.20.jar (org.jetbrains.kotlin:kotlin-klib-commonizer-api:2.1.20) with ExternalDependencyInstrumentingArtifactTransform
Transforming kotlin-util-klib-metadata-2.1.20.jar (org.jetbrains.kotlin:kotlin-util-klib-metadata:2.1.20) with ExternalDependencyInstrumentingArtifactTransform
Transforming kotlin-compiler-runner-2.1.20.jar (org.jetbrains.kotlin:kotlin-compiler-runner:2.1.20) with ExternalDependencyInstrumentingArtifactTransform
Transforming jimfs-1.1.jar (com.google.jimfs:jimfs:1.1) with ExternalDependencyInstrumentingArtifactTransform
Transforming grpc-context-1.57.0.jar (io.grpc:grpc-context:1.57.0) with ExternalDependencyInstrumentingArtifactTransform
Transforming grpc-protobuf-lite-1.57.0.jar (io.grpc:grpc-protobuf-lite:1.57.0) with ExternalDependencyInstrumentingArtifactTransform
Transforming grpc-api-1.57.0.jar (io.grpc:grpc-api:1.57.0) with ExternalDependencyInstrumentingArtifactTransform
Transforming guava-32.0.1-jre.jar (com.google.guava:guava:32.0.1-jre) with ExternalDependencyInstrumentingArtifactTransform
Transforming dagger-2.28.3.jar (com.google.dagger:dagger:2.28.3) with ExternalDependencyInstrumentingArtifactTransform
Transforming javax.inject-1.jar (javax.inject:javax.inject:1) with ExternalDependencyInstrumentingArtifactTransform
Transforming kxml2-2.3.0.jar (net.sf.kxml:kxml2:2.3.0) with ExternalDependencyInstrumentingArtifactTransform
Transforming bcutil-jdk18on-1.77.jar (org.bouncycastle:bcutil-jdk18on:1.77) with ExternalDependencyInstrumentingArtifactTransform
Transforming trove4j-1.0.20200330.jar (org.jetbrains.intellij.deps:trove4j:1.0.20200330) with ExternalDependencyInstrumentingArtifactTransform
Transforming commons-compress-1.21.jar (org.apache.commons:commons-compress:1.21) with ExternalDependencyInstrumentingArtifactTransform
Transforming bcprov-jdk18on-1.77.jar (org.bouncycastle:bcprov-jdk18on:1.77) with ExternalDependencyInstrumentingArtifactTransform
Transforming httpcore-4.4.16.jar (org.apache.httpcomponents:httpcore:4.4.16) with ExternalDependencyInstrumentingArtifactTransform
Transforming httpclient-4.5.14.jar (org.apache.httpcomponents:httpclient:4.5.14) with ExternalDependencyInstrumentingArtifactTransform
Transforming javax.activation-1.2.0.jar (com.sun.activation:javax.activation:1.2.0) with ExternalDependencyInstrumentingArtifactTransform
Transforming annotations-31.7.3.jar (com.android.tools:annotations:31.7.3) with ExternalDependencyInstrumentingArtifactTransform
Transforming signflinger-8.7.3.jar (com.android:signflinger:8.7.3) with ExternalDependencyInstrumentingArtifactTransform
Transforming zipflinger-8.7.3.jar (com.android:zipflinger:8.7.3) with ExternalDependencyInstrumentingArtifactTransform
Transforming jna-platform-5.6.0.jar (net.java.dev.jna:jna-platform:5.6.0) with ExternalDependencyInstrumentingArtifactTransform
Transforming juniversalchardet-1.0.3.jar (com.googlecode.juniversalchardet:juniversalchardet:1.0.3) with ExternalDependencyInstrumentingArtifactTransform
Transforming stax-ex-1.8.1.jar (org.jvnet.staxex:stax-ex:1.8.1) with ExternalDependencyInstrumentingArtifactTransform
Transforming javax.annotation-api-1.3.2.jar (javax.annotation:javax.annotation-api:1.3.2) with ExternalDependencyInstrumentingArtifactTransform
Transforming txw2-2.3.2.jar (org.glassfish.jaxb:txw2:2.3.2) with ExternalDependencyInstrumentingArtifactTransform
Transforming jakarta.xml.bind-api-2.3.2.jar (jakarta.xml.bind:jakarta.xml.bind-api:2.3.2) with ExternalDependencyInstrumentingArtifactTransform
Transforming istack-commons-runtime-3.0.8.jar (com.sun.istack:istack-commons-runtime:3.0.8) with ExternalDependencyInstrumentingArtifactTransform
Transforming FastInfoset-1.2.16.jar (com.sun.xml.fastinfoset:FastInfoset:1.2.16) with ExternalDependencyInstrumentingArtifactTransform
Transforming error_prone_annotations-2.27.0.jar (com.google.errorprone:error_prone_annotations:2.27.0) with ExternalDependencyInstrumentingArtifactTransform
Transforming auto-value-annotations-1.6.2.jar (com.google.auto.value:auto-value-annotations:1.6.2) with ExternalDependencyInstrumentingArtifactTransform
Transforming jakarta.activation-api-1.2.1.jar (jakarta.activation:jakarta.activation-api:1.2.1) with ExternalDependencyInstrumentingArtifactTransform
Transforming jdom2-2.0.6.jar (org.jdom:jdom2:2.0.6) with ExternalDependencyInstrumentingArtifactTransform
Transforming jose4j-0.9.5.jar (org.bitbucket.b_c:jose4j:0.9.5) with ExternalDependencyInstrumentingArtifactTransform
Transforming slf4j-api-1.7.30.jar (org.slf4j:slf4j-api:1.7.30) with ExternalDependencyInstrumentingArtifactTransform
Transforming jsr305-3.0.2.jar (com.google.code.findbugs:jsr305:3.0.2) with ExternalDependencyInstrumentingArtifactTransform
Transforming j2objc-annotations-2.8.jar (com.google.j2objc:j2objc-annotations:2.8) with ExternalDependencyInstrumentingArtifactTransform
Transforming annotations-4.1.1.4.jar (com.google.android:annotations:4.1.1.4) with ExternalDependencyInstrumentingArtifactTransform
Transforming animal-sniffer-annotations-1.23.jar (org.codehaus.mojo:animal-sniffer-annotations:1.23) with ExternalDependencyInstrumentingArtifactTransform
Transforming perfmark-api-0.26.0.jar (io.perfmark:perfmark-api:0.26.0) with ExternalDependencyInstrumentingArtifactTransform
Transforming netty-codec-http2-4.1.93.Final.jar (io.netty:netty-codec-http2:4.1.93.Final) with ExternalDependencyInstrumentingArtifactTransform
Transforming netty-handler-proxy-4.1.93.Final.jar (io.netty:netty-handler-proxy:4.1.93.Final) with ExternalDependencyInstrumentingArtifactTransform
Transforming netty-codec-http-4.1.93.Final.jar (io.netty:netty-codec-http:4.1.93.Final) with ExternalDependencyInstrumentingArtifactTransform
Transforming netty-handler-4.1.93.Final.jar (io.netty:netty-handler:4.1.93.Final) with ExternalDependencyInstrumentingArtifactTransform
Transforming checker-qual-3.33.0.jar (org.checkerframework:checker-qual:3.33.0) with ExternalDependencyInstrumentingArtifactTransform
Transforming netty-transport-native-unix-common-4.1.93.Final.jar (io.netty:netty-transport-native-unix-common:4.1.93.Final) with ExternalDependencyInstrumentingArtifactTransform
Transforming apksig-8.7.3.jar (com.android.tools.build:apksig:8.7.3) with ExternalDependencyInstrumentingArtifactTransform
Transforming commons-codec-1.11.jar (commons-codec:commons-codec:1.11) with ExternalDependencyInstrumentingArtifactTransform
Transforming javawriter-2.5.0.jar (com.squareup:javawriter:2.5.0) with ExternalDependencyInstrumentingArtifactTransform
Transforming kotlin-gradle-plugin-annotations-2.1.20.jar (org.jetbrains.kotlin:kotlin-gradle-plugin-annotations:2.1.20) with ExternalDependencyInstrumentingArtifactTransform
Transforming kotlin-native-utils-2.1.20.jar (org.jetbrains.kotlin:kotlin-native-utils:2.1.20) with ExternalDependencyInstrumentingArtifactTransform
Transforming kotlin-tooling-core-2.1.20.jar (org.jetbrains.kotlin:kotlin-tooling-core:2.1.20) with ExternalDependencyInstrumentingArtifactTransform
Transforming kotlin-util-klib-2.1.20.jar (org.jetbrains.kotlin:kotlin-util-klib:2.1.20) with ExternalDependencyInstrumentingArtifactTransform
Transforming kotlin-build-tools-api-2.1.20.jar (org.jetbrains.kotlin:kotlin-build-tools-api:2.1.20) with ExternalDependencyInstrumentingArtifactTransform
Transforming kotlin-util-io-2.1.20.jar (org.jetbrains.kotlin:kotlin-util-io:2.1.20) with ExternalDependencyInstrumentingArtifactTransform
Transforming failureaccess-1.0.1.jar (com.google.guava:failureaccess:1.0.1) with ExternalDependencyInstrumentingArtifactTransform
Transforming kotlin-daemon-client-2.1.20.jar (org.jetbrains.kotlin:kotlin-daemon-client:2.1.20) with ExternalDependencyInstrumentingArtifactTransform
Transforming kotlinx-coroutines-core-jvm-1.8.0.jar (org.jetbrains.kotlinx:kotlinx-coroutines-core-jvm:1.8.0) with ExternalDependencyInstrumentingArtifactTransform
Transforming commons-logging-1.2.jar (commons-logging:commons-logging:1.2) with ExternalDependencyInstrumentingArtifactTransform
Transforming listenablefuture-9999.0-empty-to-avoid-conflict-with-guava.jar (com.google.guava:listenablefuture:9999.0-empty-to-avoid-conflict-with-guava) with ExternalDependencyInstrumentingArtifactTransform
Transforming jna-5.6.0.jar (net.java.dev.jna:jna:5.6.0) with ExternalDependencyInstrumentingArtifactTransform
Transforming netty-codec-socks-4.1.93.Final.jar (io.netty:netty-codec-socks:4.1.93.Final) with ExternalDependencyInstrumentingArtifactTransform
Transforming netty-codec-4.1.93.Final.jar (io.netty:netty-codec:4.1.93.Final) with ExternalDependencyInstrumentingArtifactTransform
Transforming netty-buffer-4.1.93.Final.jar (io.netty:netty-buffer:4.1.93.Final) with ExternalDependencyInstrumentingArtifactTransform
Transforming netty-transport-4.1.93.Final.jar (io.netty:netty-transport:4.1.93.Final) with ExternalDependencyInstrumentingArtifactTransform
Transforming netty-resolver-4.1.93.Final.jar (io.netty:netty-resolver:4.1.93.Final) with ExternalDependencyInstrumentingArtifactTransform
Transforming netty-common-4.1.93.Final.jar (io.netty:netty-common:4.1.93.Final) with ExternalDependencyInstrumentingArtifactTransform

> Configure project :app
Evaluating project ':app' using build file '/home/runner/work/fintrack/fintrack/app/build.gradle.kts'.
Build cache key for Kotlin DSL script compilation (Project/TopLevel/stage1) is 00a6b8b02ff06150621a259646c096c6
Stored cache entry for Kotlin DSL script compilation (Project/TopLevel/stage1) with cache key 00a6b8b02ff06150621a259646c096c6
Using default execution profile
Build f302d808-1a00-45af-8a2f-97736ca5367e is started
Using Kotlin Gradle Plugin gradle85 variant
Build cache key for Kotlin DSL script compilation (Project/TopLevel/stage2) is 7d8b6060dd07f223b1d0b372c14dbe57
e: file:///home/runner/work/fintrack/fintrack/app/build.gradle.kts:28:18: Unresolved reference: io
e: file:///home/runner/work/fintrack/fintrack/app/build.gradle.kts:54:34: Unresolved reference: io
gradle/actions: Writing build results to /home/runner/work/_temp/.gradle-actions/build-results/compile-1777907964842.json
Build f302d808-1a00-45af-8a2f-97736ca5367e is closed

FAILURE: Build failed with an exception.

* Where:
Build file '/home/runner/work/fintrack/fintrack/app/build.gradle.kts' line: 28

* What went wrong:
Script compilation errors:

  Line 28:     ?.let { java.io.File(it).exists() } == true
                            ^ Unresolved reference: io

  Line 54:                 storeFile = java.io.File(keystoreProps.getProperty("storeFile"))
                                            ^ Unresolved reference: io

2 errors

* Try:
> Run with --debug option to get more log output.
> Run with --scan to get full insights.
> Get more help at https://help.gradle.org.

* Exception is:
ScriptCompilationException(scriptCompilationErrors=[ScriptCompilationError(message=Unresolved reference: io, location=/home/runner/.gradle/.tmp/gradle-kotlin-dsl-17239376798619672513.tmp/build.gradle.kts (28:18)), ScriptCompilationError(message=Unresolved reference: io, location=/home/runner/.gradle/.tmp/gradle-kotlin-dsl-17239376798619672513.tmp/build.gradle.kts (54:34))])
	at org.gradle.kotlin.dsl.support.KotlinCompilerKt.compileKotlinScriptModuleTo(KotlinCompiler.kt:190)
	at org.gradle.kotlin.dsl.support.KotlinCompilerKt.compileKotlinScriptToDirectory(KotlinCompiler.kt:148)
	at org.gradle.kotlin.dsl.execution.ResidualProgramCompiler$compileScript$1.invoke(ResidualProgramCompiler.kt:713)
	at org.gradle.kotlin.dsl.execution.ResidualProgramCompiler$compileScript$1.invoke(ResidualProgramCompiler.kt:712)
	at org.gradle.kotlin.dsl.provider.StandardKotlinScriptEvaluator$InterpreterHost$runCompileBuildOperation$1.call(KotlinScriptEvaluator.kt:206)
	at org.gradle.kotlin.dsl.provider.StandardKotlinScriptEvaluator$InterpreterHost$runCompileBuildOperation$1.call(KotlinScriptEvaluator.kt:203)
	at org.gradle.internal.operations.DefaultBuildOperationRunner$CallableBuildOperationWorker.execute(DefaultBuildOperationRunner.java:209)
	at org.gradle.internal.operations.DefaultBuildOperationRunner$CallableBuildOperationWorker.execute(DefaultBuildOperationRunner.java:204)
	at org.gradle.internal.operations.DefaultBuildOperationRunner$2.execute(DefaultBuildOperationRunner.java:66)
	at org.gradle.internal.operations.DefaultBuildOperationRunner$2.execute(DefaultBuildOperationRunner.java:59)
	at org.gradle.internal.operations.DefaultBuildOperationRunner.execute(DefaultBuildOperationRunner.java:166)
	at org.gradle.internal.operations.DefaultBuildOperationRunner.execute(DefaultBuildOperationRunner.java:59)
	at org.gradle.internal.operations.DefaultBuildOperationRunner.call(DefaultBuildOperationRunner.java:53)
	at org.gradle.kotlin.dsl.provider.StandardKotlinScriptEvaluator$InterpreterHost.runCompileBuildOperation(KotlinScriptEvaluator.kt:203)
	at org.gradle.kotlin.dsl.execution.Interpreter$ProgramHost$compileSecondStageOf$cacheDir$1$1$1$1$1.invoke(Interpreter.kt:492)
	at org.gradle.kotlin.dsl.execution.Interpreter$ProgramHost$compileSecondStageOf$cacheDir$1$1$1$1$1.invoke(Interpreter.kt:492)
	at org.gradle.kotlin.dsl.execution.ResidualProgramCompiler.compileScript-C5AE47M(ResidualProgramCompiler.kt:712)
	at org.gradle.kotlin.dsl.execution.ResidualProgramCompiler.compileScript-C5AE47M$default(ResidualProgramCompiler.kt:705)
	at org.gradle.kotlin.dsl.execution.ResidualProgramCompiler.emitStage2ProgramFor(ResidualProgramCompiler.kt:364)
	at org.gradle.kotlin.dsl.execution.Interpreter$ProgramHost$compileSecondStageOf$cacheDir$1.invoke(Interpreter.kt:493)
	at org.gradle.kotlin.dsl.execution.Interpreter$ProgramHost$compileSecondStageOf$cacheDir$1.invoke(Interpreter.kt:469)
	at org.gradle.kotlin.dsl.provider.StandardKotlinScriptEvaluator$KotlinScriptCompilationAndInstrumentation.compile(KotlinScriptEvaluator.kt:437)
	at org.gradle.internal.scripts.BuildScriptCompilationAndInstrumentation.execute(BuildScriptCompilationAndInstrumentation.java:136)
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
	at org.gradle.internal.execution.steps.BroadcastChangingOutputsStep.execute(BroadcastChangingOutputsStep.java:61)
	at org.gradle.internal.execution.steps.BroadcastChangingOutputsStep.execute(BroadcastChangingOutputsStep.java:26)
	at org.gradle.internal.execution.steps.NoInputChangesStep.execute(NoInputChangesStep.java:30)
	at org.gradle.internal.execution.steps.NoInputChangesStep.execute(NoInputChangesStep.java:21)
	at org.gradle.internal.execution.steps.CaptureOutputsAfterExecutionStep.execute(CaptureOutputsAfterExecutionStep.java:69)
	at org.gradle.internal.execution.steps.CaptureOutputsAfterExecutionStep.execute(CaptureOutputsAfterExecutionStep.java:46)
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
	at org.gradle.internal.execution.steps.NeverUpToDateStep.execute(NeverUpToDateStep.java:34)
	at org.gradle.internal.execution.steps.NeverUpToDateStep.execute(NeverUpToDateStep.java:22)
	at org.gradle.internal.execution.steps.legacy.MarkSnapshottingInputsFinishedStep.execute(MarkSnapshottingInputsFinishedStep.java:37)
	at org.gradle.internal.execution.steps.legacy.MarkSnapshottingInputsFinishedStep.execute(MarkSnapshottingInputsFinishedStep.java:27)
	at org.gradle.internal.execution.steps.ResolveNonIncrementalCachingStateStep.executeDelegate(ResolveNonIncrementalCachingStateStep.java:50)
	at org.gradle.internal.execution.steps.AbstractResolveCachingStateStep.execute(AbstractResolveCachingStateStep.java:71)
	at org.gradle.internal.execution.steps.AbstractResolveCachingStateStep.execute(AbstractResolveCachingStateStep.java:39)
	at org.gradle.internal.execution.steps.ValidateStep.execute(ValidateStep.java:107)
	at org.gradle.internal.execution.steps.ValidateStep.execute(ValidateStep.java:56)
	at org.gradle.internal.execution.steps.AbstractCaptureStateBeforeExecutionStep.execute(AbstractCaptureStateBeforeExecutionStep.java:64)
	at org.gradle.internal.execution.steps.AbstractCaptureStateBeforeExecutionStep.execute(AbstractCaptureStateBeforeExecutionStep.java:43)
	at org.gradle.internal.execution.steps.legacy.MarkSnapshottingInputsStartedStep.execute(MarkSnapshottingInputsStartedStep.java:38)
	at org.gradle.internal.execution.steps.AssignImmutableWorkspaceStep.lambda$executeInTemporaryWorkspace$3(AssignImmutableWorkspaceStep.java:209)
	at org.gradle.internal.execution.workspace.impl.CacheBasedImmutableWorkspaceProvider$1.withTemporaryWorkspace(CacheBasedImmutableWorkspaceProvider.java:116)
	at org.gradle.internal.execution.steps.AssignImmutableWorkspaceStep.executeInTemporaryWorkspace(AssignImmutableWorkspaceStep.java:199)
	at org.gradle.internal.execution.steps.AssignImmutableWorkspaceStep.lambda$execute$0(AssignImmutableWorkspaceStep.java:121)
	at org.gradle.internal.execution.steps.AssignImmutableWorkspaceStep.execute(AssignImmutableWorkspaceStep.java:121)
	at org.gradle.internal.execution.steps.AssignImmutableWorkspaceStep.execute(AssignImmutableWorkspaceStep.java:90)
	at org.gradle.internal.execution.steps.ChoosePipelineStep.execute(ChoosePipelineStep.java:38)
	at org.gradle.internal.execution.steps.ChoosePipelineStep.execute(ChoosePipelineStep.java:23)
	at org.gradle.internal.execution.steps.ExecuteWorkBuildOperationFiringStep.lambda$execute$2(ExecuteWorkBuildOperationFiringStep.java:67)
	at org.gradle.internal.execution.steps.ExecuteWorkBuildOperationFiringStep.execute(ExecuteWorkBuildOperationFiringStep.java:67)
	at org.gradle.internal.execution.steps.ExecuteWorkBuildOperationFiringStep.execute(ExecuteWorkBuildOperationFiringStep.java:39)
	at org.gradle.internal.execution.steps.IdentityCacheStep.execute(IdentityCacheStep.java:46)
	at org.gradle.internal.execution.steps.IdentityCacheStep.execute(IdentityCacheStep.java:34)
	at org.gradle.internal.execution.steps.IdentifyStep.execute(IdentifyStep.java:48)
	at org.gradle.internal.execution.steps.IdentifyStep.execute(IdentifyStep.java:35)
	at org.gradle.internal.execution.impl.DefaultExecutionEngine$1.execute(DefaultExecutionEngine.java:61)
	at org.gradle.kotlin.dsl.provider.StandardKotlinScriptEvaluator$InterpreterHost.cachedDirFor(KotlinScriptEvaluator.kt:301)
	at org.gradle.kotlin.dsl.execution.Interpreter$ProgramHost.compileSecondStageOf(Interpreter.kt:469)
	at Program.loadSecondStageFor(Unknown Source)
	at org.gradle.kotlin.dsl.execution.Interpreter$ProgramHost.evaluateSecondStageOf(Interpreter.kt:433)
	at Program.execute(Unknown Source)
	at org.gradle.kotlin.dsl.execution.Interpreter$ProgramHost.eval(Interpreter.kt:516)
	at org.gradle.kotlin.dsl.execution.Interpreter.eval(Interpreter.kt:214)
	at org.gradle.kotlin.dsl.provider.StandardKotlinScriptEvaluator.evaluate(KotlinScriptEvaluator.kt:130)
	at org.gradle.kotlin.dsl.provider.KotlinScriptPluginFactory$create$1.invoke(KotlinScriptPluginFactory.kt:46)
	at org.gradle.kotlin.dsl.provider.KotlinScriptPluginFactory$create$1.invoke(KotlinScriptPluginFactory.kt:43)
	at org.gradle.kotlin.dsl.provider.KotlinScriptPlugin.apply(KotlinScriptPlugin.kt:35)
	at org.gradle.configuration.BuildOperationScriptPlugin$1.run(BuildOperationScriptPlugin.java:68)
	at org.gradle.internal.operations.DefaultBuildOperationRunner$1.execute(DefaultBuildOperationRunner.java:29)
	at org.gradle.internal.operations.DefaultBuildOperationRunner$1.execute(DefaultBuildOperationRunner.java:26)
	at org.gradle.internal.operations.DefaultBuildOperationRunner$2.execute(DefaultBuildOperationRunner.java:66)
	at org.gradle.internal.operations.DefaultBuildOperationRunner$2.execute(DefaultBuildOperationRunner.java:59)
	at org.gradle.internal.operations.DefaultBuildOperationRunner.execute(DefaultBuildOperationRunner.java:166)
	at org.gradle.internal.operations.DefaultBuildOperationRunner.execute(DefaultBuildOperationRunner.java:59)
	at org.gradle.internal.operations.DefaultBuildOperationRunner.run(DefaultBuildOperationRunner.java:47)
	at org.gradle.configuration.BuildOperationScriptPlugin.lambda$apply$0(BuildOperationScriptPlugin.java:65)
	at org.gradle.internal.code.DefaultUserCodeApplicationContext.apply(DefaultUserCodeApplicationContext.java:44)
	at org.gradle.configuration.BuildOperationScriptPlugin.apply(BuildOperationScriptPlugin.java:65)
	at org.gradle.api.internal.project.DefaultProjectStateRegistry$ProjectStateImpl.lambda$applyToMutableState$1(DefaultProjectStateRegistry.java:411)
	at org.gradle.api.internal.project.DefaultProjectStateRegistry$ProjectStateImpl.fromMutableState(DefaultProjectStateRegistry.java:429)
	at org.gradle.api.internal.project.DefaultProjectStateRegistry$ProjectStateImpl.applyToMutableState(DefaultProjectStateRegistry.java:410)
	at org.gradle.configuration.project.BuildScriptProcessor.execute(BuildScriptProcessor.java:46)
	at org.gradle.configuration.project.BuildScriptProcessor.execute(BuildScriptProcessor.java:27)
	at org.gradle.configuration.project.ConfigureActionsProjectEvaluator.evaluate(ConfigureActionsProjectEvaluator.java:35)
	at org.gradle.configuration.project.LifecycleProjectEvaluator$EvaluateProject.lambda$run$0(LifecycleProjectEvaluator.java:109)
	at org.gradle.api.internal.project.DefaultProjectStateRegistry$ProjectStateImpl.lambda$applyToMutableState$1(DefaultProjectStateRegistry.java:411)
	at org.gradle.api.internal.project.DefaultProjectStateRegistry$ProjectStateImpl.lambda$fromMutableState$2(DefaultProjectStateRegistry.java:434)
	at org.gradle.internal.work.DefaultWorkerLeaseService.withReplacedLocks(DefaultWorkerLeaseService.java:359)
	at org.gradle.api.internal.project.DefaultProjectStateRegistry$ProjectStateImpl.fromMutableState(DefaultProjectStateRegistry.java:434)
	at org.gradle.api.internal.project.DefaultProjectStateRegistry$ProjectStateImpl.applyToMutableState(DefaultProjectStateRegistry.java:410)
	at org.gradle.configuration.project.LifecycleProjectEvaluator$EvaluateProject.run(LifecycleProjectEvaluator.java:100)
	at org.gradle.internal.operations.DefaultBuildOperationRunner$1.execute(DefaultBuildOperationRunner.java:29)
	at org.gradle.internal.operations.DefaultBuildOperationRunner$1.execute(DefaultBuildOperationRunner.java:26)
	at org.gradle.internal.operations.DefaultBuildOperationRunner$2.execute(DefaultBuildOperationRunner.java:66)
	at org.gradle.internal.operations.DefaultBuildOperationRunner$2.execute(DefaultBuildOperationRunner.java:59)
	at org.gradle.internal.operations.DefaultBuildOperationRunner.execute(DefaultBuildOperationRunner.java:166)
	at org.gradle.internal.operations.DefaultBuildOperationRunner.execute(DefaultBuildOperationRunner.java:59)
	at org.gradle.internal.operations.DefaultBuildOperationRunner.run(DefaultBuildOperationRunner.java:47)
	at org.gradle.configuration.project.LifecycleProjectEvaluator.evaluate(LifecycleProjectEvaluator.java:72)
	at org.gradle.api.internal.project.DefaultProject.evaluateUnchecked(DefaultProject.java:825)
	at org.gradle.api.internal.project.ProjectLifecycleController.lambda$ensureSelfConfigured$2(ProjectLifecycleController.java:85)
	at org.gradle.internal.model.StateTransitionController.lambda$doTransition$14(StateTransitionController.java:255)
	at org.gradle.internal.model.StateTransitionController.doTransition(StateTransitionController.java:266)
	at org.gradle.internal.model.StateTransitionController.doTransition(StateTransitionController.java:254)
	at org.gradle.internal.model.StateTransitionController.lambda$maybeTransitionIfNotCurrentlyTransitioning$10(StateTransitionController.java:199)
	at org.gradle.internal.work.DefaultSynchronizer.withLock(DefaultSynchronizer.java:36)
	at org.gradle.internal.model.StateTransitionController.maybeTransitionIfNotCurrentlyTransitioning(StateTransitionController.java:195)
	at org.gradle.api.internal.project.ProjectLifecycleController.ensureSelfConfigured(ProjectLifecycleController.java:85)
	at org.gradle.api.internal.project.DefaultProjectStateRegistry$ProjectStateImpl.ensureConfigured(DefaultProjectStateRegistry.java:385)
	at org.gradle.execution.TaskPathProjectEvaluator.configure(TaskPathProjectEvaluator.java:42)
	at org.gradle.execution.TaskPathProjectEvaluator.configureHierarchy(TaskPathProjectEvaluator.java:58)
	at org.gradle.configuration.DefaultProjectsPreparer.prepareProjects(DefaultProjectsPreparer.java:50)
	at org.gradle.configuration.BuildTreePreparingProjectsPreparer.prepareProjects(BuildTreePreparingProjectsPreparer.java:65)
	at org.gradle.configuration.BuildOperationFiringProjectsPreparer$ConfigureBuild.run(BuildOperationFiringProjectsPreparer.java:52)
	at org.gradle.internal.operations.DefaultBuildOperationRunner$1.execute(DefaultBuildOperationRunner.java:29)
	at org.gradle.internal.operations.DefaultBuildOperationRunner$1.execute(DefaultBuildOperationRunner.java:26)
	at org.gradle.internal.operations.DefaultBuildOperationRunner$2.execute(DefaultBuildOperationRunner.java:66)
	at org.gradle.internal.operations.DefaultBuildOperationRunner$2.execute(DefaultBuildOperationRunner.java:59)
	at org.gradle.internal.operations.DefaultBuildOperationRunner.execute(DefaultBuildOperationRunner.java:166)
	at org.gradle.internal.operations.DefaultBuildOperationRunner.execute(DefaultBuildOperationRunner.java:59)
	at org.gradle.internal.operations.DefaultBuildOperationRunner.run(DefaultBuildOperationRunner.java:47)
	at org.gradle.configuration.BuildOperationFiringProjectsPreparer.prepareProjects(BuildOperationFiringProjectsPreparer.java:40)
	at org.gradle.initialization.VintageBuildModelController.lambda$prepareProjects$2(VintageBuildModelController.java:84)
	at org.gradle.internal.model.StateTransitionController.lambda$doTransition$14(StateTransitionController.java:255)
	at org.gradle.internal.model.StateTransitionController.doTransition(StateTransitionController.java:266)
	at org.gradle.internal.model.StateTransitionController.doTransition(StateTransitionController.java:254)
	at org.gradle.internal.model.StateTransitionController.lambda$transitionIfNotPreviously$11(StateTransitionController.java:213)
	at org.gradle.internal.work.DefaultSynchronizer.withLock(DefaultSynchronizer.java:36)
	at org.gradle.internal.model.StateTransitionController.transitionIfNotPreviously(StateTransitionController.java:209)
	at org.gradle.initialization.VintageBuildModelController.prepareProjects(VintageBuildModelController.java:84)
	at org.gradle.initialization.VintageBuildModelController.prepareToScheduleTasks(VintageBuildModelController.java:71)
	at org.gradle.internal.build.DefaultBuildLifecycleController.lambda$prepareToScheduleTasks$6(DefaultBuildLifecycleController.java:175)
	at org.gradle.internal.model.StateTransitionController.lambda$doTransition$14(StateTransitionController.java:255)
	at org.gradle.internal.model.StateTransitionController.doTransition(StateTransitionController.java:266)
	at org.gradle.internal.model.StateTransitionController.doTransition(StateTransitionController.java:254)
	at org.gradle.internal.model.StateTransitionController.lambda$maybeTransition$9(StateTransitionController.java:190)
	at org.gradle.internal.work.DefaultSynchronizer.withLock(DefaultSynchronizer.java:36)
	at org.gradle.internal.model.StateTransitionController.maybeTransition(StateTransitionController.java:186)
	at org.gradle.internal.build.DefaultBuildLifecycleController.prepareToScheduleTasks(DefaultBuildLifecycleController.java:173)
	at org.gradle.internal.buildtree.DefaultBuildTreeWorkPreparer.scheduleRequestedTasks(DefaultBuildTreeWorkPreparer.java:36)
	at org.gradle.internal.cc.impl.VintageBuildTreeWorkController$scheduleAndRunRequestedTasks$1.apply(VintageBuildTreeWorkController.kt:36)
	at org.gradle.internal.cc.impl.VintageBuildTreeWorkController$scheduleAndRunRequestedTasks$1.apply(VintageBuildTreeWorkController.kt:35)
	at org.gradle.composite.internal.DefaultIncludedBuildTaskGraph.withNewWorkGraph(DefaultIncludedBuildTaskGraph.java:112)
	at org.gradle.internal.cc.impl.VintageBuildTreeWorkController.scheduleAndRunRequestedTasks(VintageBuildTreeWorkController.kt:35)
	at org.gradle.internal.buildtree.DefaultBuildTreeLifecycleController.lambda$scheduleAndRunTasks$1(DefaultBuildTreeLifecycleController.java:77)
	at org.gradle.internal.buildtree.DefaultBuildTreeLifecycleController.lambda$runBuild$4(DefaultBuildTreeLifecycleController.java:120)
	at org.gradle.internal.model.StateTransitionController.lambda$transition$6(StateTransitionController.java:169)
	at org.gradle.internal.model.StateTransitionController.doTransition(StateTransitionController.java:266)
	at org.gradle.internal.model.StateTransitionController.lambda$transition$7(StateTransitionController.java:169)
	at org.gradle.internal.work.DefaultSynchronizer.withLock(DefaultSynchronizer.java:46)
	at org.gradle.internal.model.StateTransitionController.transition(StateTransitionController.java:169)
	at org.gradle.internal.buildtree.DefaultBuildTreeLifecycleController.runBuild(DefaultBuildTreeLifecycleController.java:117)
	at org.gradle.internal.buildtree.DefaultBuildTreeLifecycleController.scheduleAndRunTasks(DefaultBuildTreeLifecycleController.java:77)
	at org.gradle.internal.buildtree.DefaultBuildTreeLifecycleController.scheduleAndRunTasks(DefaultBuildTreeLifecycleController.java:72)
	at org.gradle.tooling.internal.provider.ExecuteBuildActionRunner.run(ExecuteBuildActionRunner.java:31)
	at org.gradle.launcher.exec.ChainingBuildActionRunner.run(ChainingBuildActionRunner.java:35)
	at org.gradle.internal.buildtree.ProblemReportingBuildActionRunner.run(ProblemReportingBuildActionRunner.java:49)
	at org.gradle.launcher.exec.BuildOutcomeReportingBuildActionRunner.run(BuildOutcomeReportingBuildActionRunner.java:66)
	at org.gradle.tooling.internal.provider.FileSystemWatchingBuildActionRunner.run(FileSystemWatchingBuildActionRunner.java:140)
	at org.gradle.launcher.exec.BuildCompletionNotifyingBuildActionRunner.run(BuildCompletionNotifyingBuildActionRunner.java:41)
	at org.gradle.launcher.exec.RootBuildLifecycleBuildActionExecutor.lambda$execute$0(RootBuildLifecycleBuildActionExecutor.java:54)
	at org.gradle.composite.internal.DefaultRootBuildState.run(DefaultRootBuildState.java:130)
	at org.gradle.launcher.exec.RootBuildLifecycleBuildActionExecutor.execute(RootBuildLifecycleBuildActionExecutor.java:54)
	at org.gradle.internal.buildtree.InitDeprecationLoggingActionExecutor.execute(InitDeprecationLoggingActionExecutor.java:62)
	at org.gradle.internal.buildtree.InitProblems.execute(InitProblems.java:36)
	at org.gradle.internal.buildtree.DefaultBuildTreeContext.execute(DefaultBuildTreeContext.java:40)
	at org.gradle.launcher.exec.BuildTreeLifecycleBuildActionExecutor.lambda$execute$0(BuildTreeLifecycleBuildActionExecutor.java:71)
	at org.gradle.internal.buildtree.BuildTreeState.run(BuildTreeState.java:60)
	at org.gradle.launcher.exec.BuildTreeLifecycleBuildActionExecutor.execute(BuildTreeLifecycleBuildActionExecutor.java:71)
	at org.gradle.launcher.exec.RunAsBuildOperationBuildActionExecutor$3.call(RunAsBuildOperationBuildActionExecutor.java:61)
	at org.gradle.launcher.exec.RunAsBuildOperationBuildActionExecutor$3.call(RunAsBuildOperationBuildActionExecutor.java:57)
	at org.gradle.internal.operations.DefaultBuildOperationRunner$CallableBuildOperationWorker.execute(DefaultBuildOperationRunner.java:209)
	at org.gradle.internal.operations.DefaultBuildOperationRunner$CallableBuildOperationWorker.execute(DefaultBuildOperationRunner.java:204)
	at org.gradle.internal.operations.DefaultBuildOperationRunner$2.execute(DefaultBuildOperationRunner.java:66)
	at org.gradle.internal.operations.DefaultBuildOperationRunner$2.execute(DefaultBuildOperationRunner.java:59)
	at org.gradle.internal.operations.DefaultBuildOperationRunner.execute(DefaultBuildOperationRunner.java:166)
	at org.gradle.internal.operations.DefaultBuildOperationRunner.execute(DefaultBuildOperationRunner.java:59)
	at org.gradle.internal.operations.DefaultBuildOperationRunner.call(DefaultBuildOperationRunner.java:53)
	at org.gradle.launcher.exec.RunAsBuildOperationBuildActionExecutor.execute(RunAsBuildOperationBuildActionExecutor.java:57)
	at org.gradle.launcher.exec.RunAsWorkerThreadBuildActionExecutor.lambda$execute$0(RunAsWorkerThreadBuildActionExecutor.java:36)
	at org.gradle.internal.work.DefaultWorkerLeaseService.withLocks(DefaultWorkerLeaseService.java:263)
	at org.gradle.internal.work.DefaultWorkerLeaseService.runAsWorkerThread(DefaultWorkerLeaseService.java:127)
	at org.gradle.launcher.exec.RunAsWorkerThreadBuildActionExecutor.execute(RunAsWorkerThreadBuildActionExecutor.java:36)
	at org.gradle.tooling.internal.provider.continuous.ContinuousBuildActionExecutor.execute(ContinuousBuildActionExecutor.java:110)
	at org.gradle.tooling.internal.provider.SubscribableBuildActionExecutor.execute(SubscribableBuildActionExecutor.java:64)
	at org.gradle.internal.session.DefaultBuildSessionContext.execute(DefaultBuildSessionContext.java:46)
	at org.gradle.internal.buildprocess.execution.BuildSessionLifecycleBuildActionExecutor$ActionImpl.apply(BuildSessionLifecycleBuildActionExecutor.java:92)
	at org.gradle.internal.buildprocess.execution.BuildSessionLifecycleBuildActionExecutor$ActionImpl.apply(BuildSessionLifecycleBuildActionExecutor.java:80)
	at org.gradle.internal.session.BuildSessionState.run(BuildSessionState.java:71)
	at org.gradle.internal.buildprocess.execution.BuildSessionLifecycleBuildActionExecutor.execute(BuildSessionLifecycleBuildActionExecutor.java:62)
	at org.gradle.internal.buildprocess.execution.BuildSessionLifecycleBuildActionExecutor.execute(BuildSessionLifecycleBuildActionExecutor.java:41)
	at org.gradle.internal.buildprocess.execution.StartParamsValidatingActionExecutor.execute(StartParamsValidatingActionExecutor.java:64)
	at org.gradle.internal.buildprocess.execution.StartParamsValidatingActionExecutor.execute(StartParamsValidatingActionExecutor.java:32)
	at org.gradle.internal.buildprocess.execution.SessionFailureReportingActionExecutor.execute(SessionFailureReportingActionExecutor.java:51)
	at org.gradle.internal.buildprocess.execution.SessionFailureReportingActionExecutor.execute(SessionFailureReportingActionExecutor.java:39)
	at org.gradle.internal.buildprocess.execution.SetupLoggingActionExecutor.execute(SetupLoggingActionExecutor.java:47)
	at org.gradle.internal.buildprocess.execution.SetupLoggingActionExecutor.execute(SetupLoggingActionExecutor.java:31)
	at org.gradle.launcher.daemon.server.exec.ExecuteBuild.doBuild(ExecuteBuild.java:70)
	at org.gradle.launcher.daemon.server.exec.BuildCommandOnly.execute(BuildCommandOnly.java:37)
	at org.gradle.launcher.daemon.server.api.DaemonCommandExecution.proceed(DaemonCommandExecution.java:104)
	at org.gradle.launcher.daemon.server.exec.WatchForDisconnection.execute(WatchForDisconnection.java:39)
	at org.gradle.launcher.daemon.server.api.DaemonCommandExecution.proceed(DaemonCommandExecution.java:104)
	at org.gradle.launcher.daemon.server.exec.ResetDeprecationLogger.execute(ResetDeprecationLogger.java:29)
	at org.gradle.launcher.daemon.server.api.DaemonCommandExecution.proceed(DaemonCommandExecution.java:104)
	at org.gradle.launcher.daemon.server.exec.RequestStopIfSingleUsedDaemon.execute(RequestStopIfSingleUsedDaemon.java:35)
	at org.gradle.launcher.daemon.server.api.DaemonCommandExecution.proceed(DaemonCommandExecution.java:104)
	at org.gradle.launcher.daemon.server.exec.ForwardClientInput.lambda$execute$0(ForwardClientInput.java:40)
	at org.gradle.internal.daemon.clientinput.ClientInputForwarder.forwardInput(ClientInputForwarder.java:80)
	at org.gradle.launcher.daemon.server.exec.ForwardClientInput.execute(ForwardClientInput.java:37)
	at org.gradle.launcher.daemon.server.api.DaemonCommandExecution.proceed(DaemonCommandExecution.java:104)
	at org.gradle.launcher.daemon.server.exec.LogAndCheckHealth.execute(LogAndCheckHealth.java:53)
	at org.gradle.launcher.daemon.server.api.DaemonCommandExecution.proceed(DaemonCommandExecution.java:104)
	at org.gradle.launcher.daemon.server.exec.LogToClient.doBuild(LogToClient.java:63)
	at org.gradle.launcher.daemon.server.exec.BuildCommandOnly.execute(BuildCommandOnly.java:37)
	at org.gradle.launcher.daemon.server.api.DaemonCommandExecution.proceed(DaemonCommandExecution.java:104)
	at org.gradle.launcher.daemon.server.exec.EstablishBuildEnvironment.doBuild(EstablishBuildEnvironment.java:84)
	at org.gradle.launcher.daemon.server.exec.BuildCommandOnly.execute(BuildCommandOnly.java:37)
	at org.gradle.launcher.daemon.server.api.DaemonCommandExecution.proceed(DaemonCommandExecution.java:104)
	at org.gradle.launcher.daemon.server.exec.StartBuildOrRespondWithBusy$1.run(StartBuildOrRespondWithBusy.java:52)
	at org.gradle.launcher.daemon.server.DaemonStateCoordinator.lambda$runCommand$0(DaemonStateCoordinator.java:321)
	at org.gradle.internal.concurrent.ExecutorPolicy$CatchAndRecordFailures.onExecute(ExecutorPolicy.java:64)
	at org.gradle.internal.concurrent.AbstractManagedExecutor$1.run(AbstractManagedExecutor.java:48)


BUILD FAILED in 16s
```

