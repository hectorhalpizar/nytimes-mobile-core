-keep public class me.hectorhalpizar.core.nytimes.Greeting
-keepclassmembers class me.hectorhalpizar.core.nytimes.Greeting{
    public *** greeting(...);
}

-keep public final class me.hectorhalpizar.core.nytimes.domain.*
-keepclassmembers public final class me.hectorhalpizar.core.nytimes.domain.* { *; }

-dontshrink
-dontoptimize
-dontwarn com.somelibrary.annotations.*
