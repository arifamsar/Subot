import com.subot.convention.utils.coreDataDependencies
import org.jetbrains.compose.internal.utils.localPropertiesFile
import java.util.Properties

plugins {
    alias(libs.plugins.subot.kotlin.multiplatform)
    alias(libs.plugins.subot.compose.multiplatform)
    alias(libs.plugins.subot.room)
    alias(libs.plugins.kotlinx.serialization)
    alias(libs.plugins.buildConfig)
}

buildConfig {
    val localPropertiesFile = rootProject.localPropertiesFile
    val properties = Properties().apply {
        localPropertiesFile.inputStream().use { load(it) }
    }
    val baseUrl: String = properties.getProperty("BASE_URL")
    val url: String = properties.getProperty("URL")?.takeIf { it.isNotBlank() } ?: "https://staging-subot.sukarobot.id/"
    buildConfigField("BASE_URL", baseUrl)
    buildConfigField("URL", url)
}

kotlin {
    android {
        namespace = "com.subot.core.data"
        compileSdk = libs.versions.android.compileSdk.get().toInt()
    }

    sourceSets {
        commonMain.dependencies {
            api(project(":core:domain"))
            implementation(project(":core:common"))
            implementation(libs.alarmee)
            api(libs.compose.ui)
            coreDataDependencies(project)
        }
        
        androidMain.dependencies {
            implementation(libs.ktor.client.okhttp)
        }

        iosMain {
            kotlin.srcDir("build/generated/ksp/metadata/commonMain/kotlin")
        }
        iosMain.dependencies {
            implementation(libs.ktor.client.darwin)
        }
    }
}
