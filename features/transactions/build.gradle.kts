plugins {
    alias(libs.plugins.subot.feature.convention)
}

kotlin {
    android {
        namespace = "com.subot.features.transactions"
        compileSdk = libs.versions.android.compileSdk.get().toInt()
    }
}
