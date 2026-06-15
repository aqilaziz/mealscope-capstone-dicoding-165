# Public API is intentionally small and used by app and dynamic feature modules.
-keepattributes Signature, InnerClasses, EnclosingMethod, RuntimeVisibleAnnotations, RuntimeVisibleParameterAnnotations

-keep class id.aqil.mealscope.core.data.remote.response.** { *; }
-keep class id.aqil.mealscope.core.data.Resource { *; }
-keep class id.aqil.mealscope.core.data.Resource$* { *; }
-keep class id.aqil.mealscope.core.data.local.entity.** { *; }
-keep class id.aqil.mealscope.core.data.local.room.** { *; }
-keep class id.aqil.mealscope.core.di.** { *; }
-keep class id.aqil.mealscope.core.domain.model.** { *; }
-keep class id.aqil.mealscope.core.domain.usecase.** { *; }
-keep class * extends androidx.room.RoomDatabase { *; }
-keep @androidx.room.Dao class * { *; }
-keep class **_Impl { *; }
-dontwarn net.sqlcipher.**
-keep class net.sqlcipher.** { *; }
-keep class net.zetetic.** { *; }
-keepclasseswithmembernames class * {
    native <methods>;
}
-dontwarn javax.annotation.**
-dontwarn kotlin.Unit
-dontwarn org.koin.**
