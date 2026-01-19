plugins {
    alias(libs.plugins.subot.feature.convention)
}

kotlin {
    androidLibrary {
        namespace = "com.subot.features.profile"
        compileSdk = libs.versions.android.compileSdk.get().toInt()
    }
}