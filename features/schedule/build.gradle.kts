plugins {
    alias(libs.plugins.subot.feature.convention)
}

kotlin {
    android {
        namespace = "com.subot.features.schedule"
        compileSdk = libs.versions.android.compileSdk.get().toInt()
    }
}
