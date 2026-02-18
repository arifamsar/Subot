# Android App Module

The `androidApp` module is the platform-specific entry point for the Android application.

## Purpose
This is a thin wrapper around the shared logic. It contains the Android `MainActivity`, `Application` class, and Android-specific configurations like `AndroidManifest.xml` and resources.

## Contents
- **MainActivity.kt**: The main activity that hosts the Compose content.
- **SubotApplication.kt**: (If present) Standard Android Application class for initializing Koin.
- **Resources**: Android-specific icons, values, and XML configurations.
- **build.gradle.kts**: Android-specific build configuration and dependencies.

## Implementation Details
- Simply calls the shared `App()` composable from the `:sharedUI` module.
- Initializes Koin with platform-specific modules if necessary.
- Manages the Android lifecycle and standard platform integrations.

## How to Run
- Open the project in **Android Studio**.
- Select the `androidApp` run configuration.
- Click **Run**.
- Alternatively, run `./gradlew :androidApp:installDebug` from the command line.
