-keepattributes Signature,*Annotation*,InnerClasses,EnclosingMethod

-keep class id.aqil.mealscope.core.data.remote.response.** { *; }
-keep class id.aqil.mealscope.core.data.local.entity.** { *; }
-keep class id.aqil.mealscope.core.data.remote.network.** { *; }

-dontwarn okhttp3.**
-dontwarn okio.**
-dontwarn javax.annotation.**
-dontwarn net.sqlcipher.**
-keepattributes Signature, InnerClasses, EnclosingMethod, RuntimeVisibleAnnotations, RuntimeVisibleParameterAnnotations

-keep class id.aqil.mealscope.core.data.remote.response.** { *; }
-keep class id.aqil.mealscope.core.data.Resource { *; }
-keep class id.aqil.mealscope.core.data.Resource$* { *; }
-keep class id.aqil.mealscope.core.data.local.entity.** { *; }
-keep class id.aqil.mealscope.core.di.** { *; }
-keep class id.aqil.mealscope.core.domain.model.** { *; }
-keep class id.aqil.mealscope.core.domain.usecase.** { *; }
-keep class * extends androidx.room.RoomDatabase { *; }
-keep @androidx.room.Dao class * { *; }
-keep class **_Impl { *; }
-dontwarn javax.annotation.**
-dontwarn kotlin.Unit
-dontwarn org.koin.**
