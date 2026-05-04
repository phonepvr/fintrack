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
