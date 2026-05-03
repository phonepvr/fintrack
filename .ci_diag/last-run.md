# Build summary (run 25287170073, sha bc506ce8c971088b0e2b81372bcf1061ac8880ea)

| Step | Outcome |
|---|---|
| compile | success |
| assemble | success |
| tests | success |
| detekt | success |
| locate APK | success |

## compile.log — error/warning lines

```
```

## compile.log — full log (2262 lines)

```
[truncated to first 200 + last 400 lines]
Initialized native services in: /home/runner/.gradle/native
Initialized jansi services in: /home/runner/.gradle/native
To honour the JVM settings for this build a single-use Daemon process will be forked. For more on this, please refer to https://docs.gradle.org/8.11.1/userguide/gradle_daemon.html#sec:disabling_the_daemon in the Gradle documentation.
Starting process 'Gradle build daemon'. Working directory: /home/runner/.gradle/daemon/8.11.1 Command: /usr/lib/jvm/temurin-17-jdk-amd64/bin/java --add-opens=java.base/java.lang=ALL-UNNAMED --add-opens=java.base/java.lang.invoke=ALL-UNNAMED --add-opens=java.base/java.util=ALL-UNNAMED --add-opens=java.prefs/java.util.prefs=ALL-UNNAMED --add-exports=jdk.compiler/com.sun.tools.javac.api=ALL-UNNAMED --add-exports=jdk.compiler/com.sun.tools.javac.util=ALL-UNNAMED --add-opens=java.base/java.util=ALL-UNNAMED --add-opens=java.prefs/java.util.prefs=ALL-UNNAMED --add-opens=java.base/java.nio.charset=ALL-UNNAMED --add-opens=java.base/java.net=ALL-UNNAMED --add-opens=java.base/java.util.concurrent.atomic=ALL-UNNAMED --add-opens=java.xml/javax.xml.namespace=ALL-UNNAMED -XX:+UseParallelGC -Xmx4g -Dfile.encoding=UTF-8 -Duser.country -Duser.language=en -Duser.variant -cp /home/runner/.gradle/wrapper/dists/gradle-8.11.1-bin/bpt9gzteqjrbo1mjrsomdt32c/gradle-8.11.1/lib/gradle-daemon-main-8.11.1.jar -javaagent:/home/runner/.gradle/wrapper/dists/gradle-8.11.1-bin/bpt9gzteqjrbo1mjrsomdt32c/gradle-8.11.1/lib/agents/gradle-instrumentation-agent-8.11.1.jar org.gradle.launcher.daemon.bootstrap.GradleDaemon 8.11.1
Successfully started process 'Gradle build daemon'
An attempt to start the daemon took 0.919 secs.
The client will now receive all logging from the daemon (pid: 2498). The daemon log file: /home/runner/.gradle/daemon/8.11.1/daemon-2498.out.log
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

... (1662 lines elided) ...

Transforming dagger-lint-aar-2.54.aar (com.google.dagger:dagger-lint-aar:2.54) with LibrarySymbolTableTransform
Build cache key for task ':app:processDebugResources' is 4a0c42215ad2adf714865cd3e019d97c
Task ':app:processDebugResources' is not up-to-date because:
  No history is available.
Loaded cache entry for task ':app:processDebugResources' with cache key 4a0c42215ad2adf714865cd3e019d97c
Resolve mutations for :app:kspDebugKotlin (Thread[Execution worker,5,main]) started.
:app:kspDebugKotlin (Thread[Execution worker,5,main]) started.

> Task :app:kspDebugKotlin FROM-CACHE
file or directory '/home/runner/work/fintrack/fintrack/app/src/debug/kotlin', not found
file or directory '/home/runner/work/fintrack/fintrack/app/src/debug/java', not found
file or directory '/home/runner/work/fintrack/fintrack/app/src/main/java', not found
file or directory '/home/runner/work/fintrack/fintrack/app/src/main/java', not found
file or directory '/home/runner/work/fintrack/fintrack/app/src/debug/java', not found
Transforming hilt-navigation-compose-1.2.0.aar (androidx.hilt:hilt-navigation-compose:1.2.0) with AarToClassTransform
Transforming hilt-navigation-1.2.0.aar (androidx.hilt:hilt-navigation:1.2.0) with AarToClassTransform
Transforming navigation-common-2.8.5.aar (androidx.navigation:navigation-common:2.8.5) with AarToClassTransform
Transforming navigation-runtime-2.8.5.aar (androidx.navigation:navigation-runtime:2.8.5) with AarToClassTransform
Transforming navigation-common-ktx-2.8.5.aar (androidx.navigation:navigation-common-ktx:2.8.5) with AarToClassTransform
Transforming navigation-runtime-ktx-2.8.5.aar (androidx.navigation:navigation-runtime-ktx:2.8.5) with AarToClassTransform
Transforming navigation-compose-2.8.5.aar (androidx.navigation:navigation-compose:2.8.5) with AarToClassTransform
Transforming hilt-android-2.54.aar (com.google.dagger:hilt-android:2.54) with AarToClassTransform
Transforming biometric-ktx-1.2.0-alpha05.aar (androidx.biometric:biometric-ktx:1.2.0-alpha05) with AarToClassTransform
Transforming biometric-1.2.0-alpha05.aar (androidx.biometric:biometric:1.2.0-alpha05) with AarToClassTransform
Transforming fragment-1.5.4.aar (androidx.fragment:fragment:1.5.4) with AarToClassTransform
Transforming loader-1.0.0.aar (androidx.loader:loader:1.0.0) with AarToClassTransform
Transforming viewpager-1.0.0.aar (androidx.viewpager:viewpager:1.0.0) with AarToClassTransform
Transforming customview-1.0.0.aar (androidx.customview:customview:1.0.0) with AarToClassTransform
Transforming core-1.15.0.aar (androidx.core:core:1.15.0) with AarToClassTransform
Transforming core-1.15.0.aar (androidx.core:core:1.15.0) with AarToClassTransform
Transforming core-1.15.0.aar (androidx.core:core:1.15.0) with AarToClassTransform
Transforming core-1.15.0.aar (androidx.core:core:1.15.0) with AarToClassTransform
Transforming lifecycle-livedata-core-ktx-2.8.7.aar (androidx.lifecycle:lifecycle-livedata-core-ktx:2.8.7) with AarToClassTransform
Transforming lifecycle-livedata-2.8.7.aar (androidx.lifecycle:lifecycle-livedata:2.8.7) with AarToClassTransform
Transforming lifecycle-runtime-ktx-release.aar (androidx.lifecycle:lifecycle-runtime-ktx-android:2.8.7) with AarToClassTransform
Transforming lifecycle-livedata-core-2.8.7.aar (androidx.lifecycle:lifecycle-livedata-core:2.8.7) with AarToClassTransform
Transforming lifecycle-runtime-release.aar (androidx.lifecycle:lifecycle-runtime-android:2.8.7) with AarToClassTransform
Transforming lifecycle-viewmodel-2.8.7.aar (androidx.lifecycle:lifecycle-viewmodel:2.8.7) with AarToClassTransform
Transforming lifecycle-viewmodel-release.aar (androidx.lifecycle:lifecycle-viewmodel-android:2.8.7) with AarToClassTransform
Transforming lifecycle-viewmodel-ktx-2.8.7.aar (androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.7) with AarToClassTransform
Transforming lifecycle-viewmodel-savedstate-2.8.7.aar (androidx.lifecycle:lifecycle-viewmodel-savedstate:2.8.7) with AarToClassTransform
Transforming lifecycle-runtime-compose-release.aar (androidx.lifecycle:lifecycle-runtime-compose-android:2.8.7) with AarToClassTransform
Transforming lifecycle-process-2.8.7.aar (androidx.lifecycle:lifecycle-process:2.8.7) with AarToClassTransform
Transforming lifecycle-viewmodel-compose-release.aar (androidx.lifecycle:lifecycle-viewmodel-compose-android:2.8.7) with AarToClassTransform
Transforming material3-release.aar (androidx.compose.material3:material3-android:1.3.1) with AarToClassTransform
Transforming foundation-layout-release.aar (androidx.compose.foundation:foundation-layout-android:1.7.6) with AarToClassTransform
Transforming material-ripple-release.aar (androidx.compose.material:material-ripple-android:1.7.6) with AarToClassTransform
Transforming foundation-release.aar (androidx.compose.foundation:foundation-android:1.7.6) with AarToClassTransform
Transforming animation-core-release.aar (androidx.compose.animation:animation-core-android:1.7.6) with AarToClassTransform
Transforming animation-release.aar (androidx.compose.animation:animation-android:1.7.6) with AarToClassTransform
Transforming ui-util-release.aar (androidx.compose.ui:ui-util-android:1.7.6) with AarToClassTransform
Transforming ui-unit-release.aar (androidx.compose.ui:ui-unit-android:1.7.6) with AarToClassTransform
Transforming ui-text-release.aar (androidx.compose.ui:ui-text-android:1.7.6) with AarToClassTransform
Transforming ui-geometry-release.aar (androidx.compose.ui:ui-geometry-android:1.7.6) with AarToClassTransform
Transforming ui-tooling-data-release.aar (androidx.compose.ui:ui-tooling-data-android:1.7.6) with AarToClassTransform
Transforming ui-tooling-preview-release.aar (androidx.compose.ui:ui-tooling-preview-android:1.7.6) with AarToClassTransform
Transforming ui-graphics-release.aar (androidx.compose.ui:ui-graphics-android:1.7.6) with AarToClassTransform
Transforming material-icons-extended-release.aar (androidx.compose.material:material-icons-extended-android:1.7.6) with AarToClassTransform
Transforming material-icons-core-release.aar (androidx.compose.material:material-icons-core-android:1.7.6) with AarToClassTransform
Transforming ui-release.aar (androidx.compose.ui:ui-android:1.7.6) with AarToClassTransform
Transforming ui-tooling-release.aar (androidx.compose.ui:ui-tooling-android:1.7.6) with AarToClassTransform
Transforming ui-test-manifest-1.7.6.aar (androidx.compose.ui:ui-test-manifest:1.7.6) with AarToClassTransform
Transforming activity-1.9.3.aar (androidx.activity:activity:1.9.3) with AarToClassTransform
Transforming activity-compose-1.9.3.aar (androidx.activity:activity-compose:1.9.3) with AarToClassTransform
Transforming activity-ktx-1.9.3.aar (androidx.activity:activity-ktx:1.9.3) with AarToClassTransform
Transforming core-ktx-1.15.0.aar (androidx.core:core-ktx:1.15.0) with AarToClassTransform
Transforming room-runtime-2.6.1.aar (androidx.room:room-runtime:2.6.1) with AarToClassTransform
Transforming room-ktx-2.6.1.aar (androidx.room:room-ktx:2.6.1) with AarToClassTransform
Transforming sqlite-framework-2.4.0.aar (androidx.sqlite:sqlite-framework:2.4.0) with AarToClassTransform
Transforming sqlite-2.4.0.aar (androidx.sqlite:sqlite:2.4.0) with AarToClassTransform
Transforming sqlite-ktx-2.4.0.aar (androidx.sqlite:sqlite-ktx:2.4.0) with AarToClassTransform
Transforming compose-m3-2.0.2.aar (com.patrykandpatrick.vico:compose-m3:2.0.2) with AarToClassTransform
Transforming runtime-saveable-release.aar (androidx.compose.runtime:runtime-saveable-android:1.7.6) with AarToClassTransform
Transforming runtime-release.aar (androidx.compose.runtime:runtime-android:1.7.6) with AarToClassTransform
Transforming lifecycle-common-jvm-2.8.7.jar (androidx.lifecycle:lifecycle-common-jvm:2.8.7) with IdentityTransform
Transforming annotation-experimental-1.4.1.aar (androidx.annotation:annotation-experimental:1.4.1) with AarToClassTransform
Transforming savedstate-ktx-1.2.1.aar (androidx.savedstate:savedstate-ktx:1.2.1) with AarToClassTransform
Transforming savedstate-1.2.1.aar (androidx.savedstate:savedstate:1.2.1) with AarToClassTransform
Transforming security-crypto-1.1.0-alpha06.aar (androidx.security:security-crypto:1.1.0-alpha06) with AarToClassTransform
Transforming versionedparcelable-1.1.1.aar (androidx.versionedparcelable:versionedparcelable:1.1.1) with AarToClassTransform
Transforming core-runtime-2.2.0.aar (androidx.arch.core:core-runtime:2.2.0) with AarToClassTransform
Transforming sqlcipher-android-4.6.1.aar (net.zetetic:sqlcipher-android:4.6.1) with AarToClassTransform
Transforming compose-2.0.2.aar (com.patrykandpatrick.vico:compose:2.0.2) with AarToClassTransform
Transforming core-2.0.2.aar (com.patrykandpatrick.vico:core:2.0.2) with AarToClassTransform
Transforming startup-runtime-1.1.1.aar (androidx.startup:startup-runtime:1.1.1) with AarToClassTransform
Transforming javax.inject-1.jar (javax.inject:javax.inject:1) with IdentityTransform
Transforming dagger-lint-aar-2.54.aar (com.google.dagger:dagger-lint-aar:2.54) with AarToClassTransform
Transforming jsr305-3.0.2.jar (com.google.code.findbugs:jsr305:3.0.2) with IdentityTransform
Transforming room-common-2.6.1.jar (androidx.room:room-common:2.6.1) with IdentityTransform
Transforming kotlinx-coroutines-core-jvm-1.10.1.jar (org.jetbrains.kotlinx:kotlinx-coroutines-core-jvm:1.10.1) with IdentityTransform
Transforming kotlinx-coroutines-android-1.10.1.jar (org.jetbrains.kotlinx:kotlinx-coroutines-android:1.10.1) with IdentityTransform
Transforming kotlinx-datetime-jvm-0.6.1.jar (org.jetbrains.kotlinx:kotlinx-datetime-jvm:0.6.1) with IdentityTransform
Transforming kotlinx-serialization-core-jvm-1.7.3.jar (org.jetbrains.kotlinx:kotlinx-serialization-core-jvm:1.7.3) with IdentityTransform
Transforming kotlinx-serialization-json-jvm-1.7.3.jar (org.jetbrains.kotlinx:kotlinx-serialization-json-jvm:1.7.3) with IdentityTransform
Transforming core-common-2.2.0.jar (androidx.arch.core:core-common:2.2.0) with IdentityTransform
Transforming collection-jvm-1.4.4.jar (androidx.collection:collection-jvm:1.4.4) with IdentityTransform
Transforming annotation-jvm-1.9.1.jar (androidx.annotation:annotation-jvm:1.9.1) with IdentityTransform
Transforming kotlin-stdlib-jdk8-1.8.22.jar (org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.8.22) with IdentityTransform
Transforming kotlin-stdlib-jdk7-1.8.22.jar (org.jetbrains.kotlin:kotlin-stdlib-jdk7:1.8.22) with IdentityTransform
Transforming kotlin-stdlib-2.1.20.jar (org.jetbrains.kotlin:kotlin-stdlib:2.1.20) with IdentityTransform
Transforming annotations-23.0.0.jar (org.jetbrains:annotations:23.0.0) with IdentityTransform
Transforming hilt-core-2.54.jar (com.google.dagger:hilt-core:2.54) with IdentityTransform
Transforming dagger-2.54.jar (com.google.dagger:dagger:2.54) with IdentityTransform
Transforming jakarta.inject-api-2.0.1.jar (jakarta.inject:jakarta.inject-api:2.0.1) with IdentityTransform
Transforming jspecify-1.0.0.jar (org.jspecify:jspecify:1.0.0) with IdentityTransform
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
Loaded cache entry for task ':app:kspDebugKotlin' with cache key 1298f43c4b469e3e5b5228f244e100e3
Resolve mutations for :app:compileDebugKotlin (Thread[Execution worker,5,main]) started.
:app:compileDebugKotlin (Thread[Execution worker,5,main]) started.

> Task :app:compileDebugKotlin FROM-CACHE
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
Loaded cache entry for task ':app:compileDebugKotlin' with cache key 64ce5cca5890a984ec52c2ec4574c70c
gradle/actions: Writing build results to /home/runner/work/_temp/.gradle-actions/build-results/compile-1777832859735.json
Build d732b798-dc76-4178-9569-b027cb28fc0b is closed

BUILD SUCCESSFUL in 13s
16 actionable tasks: 3 executed, 13 from cache
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
> Task :app:compileDebugJavaWithJavac FROM-CACHE
> Task :app:mergeDebugShaders
> Task :app:compileDebugShaders NO-SOURCE
> Task :app:generateDebugAssets UP-TO-DATE
> Task :app:mergeDebugAssets
> Task :app:compressDebugAssets FROM-CACHE
> Task :app:desugarDebugFileDependencies FROM-CACHE
> Task :app:hiltAggregateDepsDebug FROM-CACHE
> Task :app:hiltJavaCompileDebug FROM-CACHE
> Task :app:transformDebugClassesWithAsm FROM-CACHE
> Task :app:dexBuilderDebug FROM-CACHE
> Task :app:mergeDebugGlobalSynthetics FROM-CACHE
> Task :app:processDebugJavaRes
> Task :app:mergeDebugJniLibFolders
> Task :app:checkDebugDuplicateClasses
> Task :app:mergeDebugNativeLibs
> Task :app:mergeExtDexDebug FROM-CACHE
> Task :app:mergeLibDexDebug FROM-CACHE
> Task :app:mergeProjectDexDebug FROM-CACHE

> Task :app:stripDebugDebugSymbols
Unable to strip the following libraries, packaging them as they are: libandroidx.graphics.path.so, libsqlcipher.so.

> Task :app:mergeDebugJavaResource
> Task :app:validateSigningDebug
> Task :app:writeDebugAppMetadata
> Task :app:writeDebugSigningConfigVersions
> Task :app:packageDebug
> Task :app:createDebugApkListingFileRedirect
> Task :app:assembleDebug
gradle/actions: Writing build results to /home/runner/work/_temp/.gradle-actions/build-results/assemble-1777832872272.json

BUILD SUCCESSFUL in 15s
41 actionable tasks: 13 executed, 12 from cache, 16 up-to-date
```

## tests.log — error/warning lines

```
```

## tests.log — full log (94 lines)

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
> Task :app:compileDebugUnitTestKotlin
> Task :app:compileDebugUnitTestJavaWithJavac NO-SOURCE
> Task :app:hiltAggregateDepsDebugUnitTest FROM-CACHE
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

FlagSecureTest > FLAG_SECURE is set BEFORE setContent so no frame is ever rendered insecurely PASSED

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
gradle/actions: Writing build results to /home/runner/work/_temp/.gradle-actions/build-results/tests-1777832887944.json

BUILD SUCCESSFUL in 22s
35 actionable tasks: 9 executed, 2 from cache, 24 up-to-date
```

## detekt.log — error/warning lines

```
151:FAILURE: Build failed with an exception.
195:Caused by: org.gradle.api.GradleException: Analysis failed with 39 weighted issues.
323:Caused by: java.lang.reflect.InvocationTargetException
329:Caused by: io.github.detekt.tooling.api.MaxIssuesReached: Analysis failed with 39 weighted issues.
```

## detekt.log — full log (342 lines)

```
To honour the JVM settings for this build a single-use Daemon process will be forked. For more on this, please refer to https://docs.gradle.org/8.11.1/userguide/gradle_daemon.html#sec:disabling_the_daemon in the Gradle documentation.
Daemon will be stopped at the end of the build 

> Task :detekt
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/onboarding/CreateFirstProfileScreen.kt:73:5: The function CreateFirstProfileRoute is too long (91). The maximum length is 80. [LongMethod]
/home/runner/work/fintrack/fintrack/app/src/test/kotlin/com/fintrack/privacy/MoneyTypesTest.kt:23:9: Function noFloatingPointInMoneyLayers is nested too deeply. [NestedBlockDepth]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/MainActivity.kt:3:1: Imports must be ordered in lexicographic order without any empty lines in-between with "java", "javax", "kotlin" and aliases in the end [ImportOrdering]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/home/HomeScreen.kt:10:1: Unused import [NoUnusedImports]
/home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/home/HomeScreen.kt:3:1: Imports must be ordered in lexicographic order without any empty lines in-between with "java", "javax", "kotlin" and aliases in the end [ImportOrdering]
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
	[33mNoUnusedImports - [Unused import] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/home/HomeScreen.kt:10:1[0m
	[33mImportOrdering - [Imports must be ordered in lexicographic order without any empty lines in-betwee(...)] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/home/HomeScreen.kt:3:1[0m
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
	[33mNoUnusedImports - [Unused import] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/home/HomeScreen.kt:10:1[0m
	[33mImportOrdering - [Imports must be ordered in lexicographic order without any empty lines in-betwee(...)] at /home/runner/work/fintrack/fintrack/app/src/main/kotlin/com/fintrack/ui/home/HomeScreen.kt:3:1[0m
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


> Task :detekt FAILED
gradle/actions: Writing build results to /home/runner/work/_temp/.gradle-actions/build-results/detekt-1777832911031.json

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


BUILD FAILED in 12s
1 actionable task: 1 executed
```

