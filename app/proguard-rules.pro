# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in Android SDK tools.

# Keep keyboard related classes
-keep public class * extends android.inputmethodservice.InputMethodService

# Keep all native methods
-keepclasseswithmembernames class * {
    native <methods>;
}

# Keep custom views
-keep public class * extends android.view.View

# Optimization settings
-optimizationpasses 5
-dontusemixedcaseclassnames
-dontskipnonpubliclibraryclasses
-verbose

# Optimization passes
-optimizations !code/simplification/arithmetic,!field/*,!class/merging/*,!code/allocation/variable
