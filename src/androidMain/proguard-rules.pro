-keep public class me.hectorhalpizar.core.nytimes.Greeting
-keepclassmembers class me.hectorhalpizar.core.nytimes.Greeting{
    public *** greeting(...);
}

-dontshrink
-dontoptimize
-dontwarn com.somelibrary.annotations.*
