# Build Logic Module

The `build-logic` module contains the centralized build configuration and convention plugins for the Subots project.

## Purpose
This module avoids code duplication in `build.gradle.kts` files across the project. It defines a set of convention plugins that can be applied to other modules to configure common settings like Kotlin Multiplatform, Compose, Android targets, Room, and more.

## Contents
- **Convention**: The main implementation of the Gradle convention plugins using Kotlin.
- **Convention Plugins**:
  - `subot.kotlin.multiplatform`: Standard KMP configuration.
  - `subot.compose.multiplatform`: Compose Multiplatform settings.
  - `subot.room`: Configuration for Room with KSP.
  - `subot.feature.convention`: Shared configuration for feature modules.
  - `subot.navigation.convention`: Navigation-specific build setup.

## Implementation Details
- Uses **Kotlin DSL** for build scripts.
- Integrates with `libs.versions.toml` for version management.
- Provides a modular and scalable way to manage build configurations.

## Usage
Apply a convention plugin in a module's `build.gradle.kts`:
```kotlin
plugins {
    alias(libs.plugins.subot.feature.convention)
}
```
This will automatically apply all the necessary plugins and configuration for a feature module.
