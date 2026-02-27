import com.subot.convention.utils.coreDataDependencies
import org.jetbrains.compose.internal.utils.localPropertiesFile
import java.util.Properties

plugins {
    alias(libs.plugins.subot.kotlin.multiplatform)
    alias(libs.plugins.subot.room)
    alias(libs.plugins.kotlinx.serialization)
    alias(libs.plugins.buildConfig)
}

buildConfig {
    val localPropertiesFile = rootProject.localPropertiesFile
    val properties = Properties().apply {
        localPropertiesFile.inputStream().use { load(it) }
    }
    val  baseUrl: String = properties.getProperty("BASE_URL")
    buildConfigField("BASE_URL", baseUrl )
}

kotlin {
    androidLibrary {
        namespace = "com.subot.core.data"
        compileSdk = libs.versions.android.compileSdk.get().toInt()
    }

    sourceSets {
        commonMain.dependencies {
            api(project(":core:domain"))
            implementation(project(":core:common"))
            coreDataDependencies(project)
        }
        
        androidMain.dependencies {
            implementation(libs.ktor.client.okhttp)
        }

        iosMain.dependencies {
            implementation(libs.ktor.client.darwin)
        }
    }
}
