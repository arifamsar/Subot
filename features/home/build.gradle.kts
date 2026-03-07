plugins {
    alias(libs.plugins.subot.feature.convention)
}

kotlin {
    android {
        namespace = "com.subot.features.home"
        compileSdk = libs.versions.android.compileSdk.get().toInt()
    }
}
