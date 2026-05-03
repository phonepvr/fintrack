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
