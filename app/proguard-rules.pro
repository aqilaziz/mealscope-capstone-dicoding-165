-keepattributes Signature,*Annotation*,InnerClasses,EnclosingMethod

-keep class id.aqil.mealscope.core.data.remote.response.** { *; }
-keep class id.aqil.mealscope.core.data.local.entity.** { *; }
-keep class id.aqil.mealscope.core.data.remote.network.** { *; }

-dontwarn okhttp3.**
-dontwarn okio.**
-dontwarn javax.annotation.**
-dontwarn net.sqlcipher.**
