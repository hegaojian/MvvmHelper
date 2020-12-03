#所有GSON生成的对象类不能被混淆
-keep class com.zhixinhuixue.library.net.body.**{*;}
-keep class com.zhixinhuixue.library.net.entity.**{*;}

#okhttp
-dontwarn okhttp3.**
-keep class okhttp3.**{*;}

#okio
-dontwarn okio.**
-keep class okio.**{*;}

#retrofit2
-dontwarn retrofit2.**
-keep class retrofit2.** { *; }