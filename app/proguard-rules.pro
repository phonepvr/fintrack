# Strip android.util.Log calls in release builds.
# Acts as belt-and-braces with the existing rule that no Log.* calls reference
# sensitive values; even if a future contributor adds one, R8 elides it.
-assumenosideeffects class android.util.Log {
    public static *** v(...);
    public static *** d(...);
    public static *** i(...);
    public static *** w(...);
    public static *** e(...);
    public static *** wtf(...);
}

# Keep SQLCipher native bindings.
-keep class net.zetetic.database.sqlcipher.** { *; }
-keep class net.sqlcipher.** { *; }

# Keep Room generated classes.
-keep class androidx.room.RoomDatabase { *; }
-keep class * extends androidx.room.RoomDatabase

# Keep Hilt generated classes.
-keep class dagger.hilt.** { *; }
-keep class * extends dagger.hilt.android.internal.GeneratedComponent { *; }

# Keep kotlinx.serialization metadata.
-keepattributes *Annotation*, InnerClasses
-dontnote kotlinx.serialization.AnnotationsKt
-keepclassmembers class kotlinx.serialization.json.** {
    *** Companion;
}
-keepclasseswithmembers class kotlinx.serialization.json.** {
    kotlinx.serialization.KSerializer serializer(...);
}

# Keep our serializable backup payloads.
-keep,includedescriptorclasses class com.fintrack.backup.**$$serializer { *; }
-keepclassmembers class com.fintrack.backup.** {
    *** Companion;
    kotlinx.serialization.KSerializer serializer(...);
}

# -----------------------------------------------------------------------------
# Apache POI (XLSX template + import).
#
# POI is reflection-heavy via XmlBeans; without these rules R8 strips the
# schema classes / service-loader registrations and the workbook factory
# fails at runtime. Keep the schema-bearing packages and silence the warns
# on host-only classes (java.awt, javax.swing, etc.) that POI references
# but never invokes on Android.
# -----------------------------------------------------------------------------
-keep class org.apache.poi.** { *; }
-keep class org.apache.xmlbeans.** { *; }
-keep class org.openxmlformats.** { *; }
-keep class schemaorg_apache_xmlbeans.** { *; }
-keep class com.microsoft.schemas.** { *; }
-keep class org.etsi.uri.** { *; }
-keep class org.w3.x2000.** { *; }
-keep class javax.xml.** { *; }

-keepattributes Signature, InnerClasses, EnclosingMethod
-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}

-dontwarn org.apache.poi.**
-dontwarn org.apache.xmlbeans.**
-dontwarn org.openxmlformats.**
-dontwarn schemaorg_apache_xmlbeans.**
-dontwarn com.microsoft.schemas.**
-dontwarn org.etsi.uri.**
-dontwarn org.w3.x2000.**
-dontwarn java.awt.**
-dontwarn javax.swing.**
-dontwarn javax.xml.stream.**
-dontwarn org.apache.commons.compress.**
-dontwarn org.apache.commons.collections4.**
-dontwarn org.apache.logging.log4j.**

# POI ships ServiceLoader resources; keep them.
-keep class org.apache.poi.ooxml.POIXMLDocumentPart { *; }
-keep class org.apache.poi.openxml4j.opc.PackageRelationship { *; }
