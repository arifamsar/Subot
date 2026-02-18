# Core UI Module

The `core:ui` module contains shared Compose UI components, themes, and navigation infrastructure.

## Purpose
This module ensures visual consistency across the application by providing a set of reusable UI components and a centralized theme. It also defines the shared navigation architecture.

## Contents
- **Components**: Reusable UI widgets (`AppButton`, `AppTextField`, `AppTopBar`, `LoadingIndicator`, etc.).
- **Icons**: A library of custom-drawn icons used throughout the app.
- **Navigation**: Infrastructure for multiplatform navigation (`Navigator`, `Route`, `RootNavigator`).
- **Theme**: Centralized design system definition (`Theme`, `Color`, `Typography`, `Ripple`).
- **Compose Resources**: Shared resources like strings and images managed by Compose Multiplatform.

## Key Components
- **AppScaffold**: A base scaffold with shared navigation and app bar support.
- **Navigator**: A type-safe navigation wrapper around `androidx-nav3`.
- **Theme.kt**: Configurable Material 3 theme with dynamic color support.

## Implementation Details
- Uses **Compose Multiplatform** for shared UI.
- Follows **Material Design 3** guidelines.
- Uses **Coil** for image loading within components.
- Integrated with **androidx-nav3** for navigation.

## Usage
Add this module to other modules' `build.gradle.kts`:
```kotlin
implementation(project(":core:ui"))
```
Import `SubotTheme` in your entry points to apply the global design system.
