---
trigger: always_on
---

# Subot - AI Coding Instructions

## Project Overview

Subot is a **Kotlin Multiplatform (KMP)** application targeting Android and iOS using **Compose Multiplatform** for shared UI.

## Architecture

### Module Structure

```
:androidApp      → Android app shell (thin wrapper, only entry point)
:sharedUI        → Shared UI layer (Compose Multiplatform screens, themes, ViewModels)
:core            → Shared business logic (data/domain layers, platform abstractions)
:features:*      → Feature specific modules (home, schedule, transactions, etc)
```

**Dependency flow**: `androidApp → sharedUI → features → core`

### UI Package Structure

```
sharedUI/src/commonMain/kotlin/com/sukarobot/subot/
├── navigation/          → Type-safe Nav3 routes (AppRoute.kt, AppNavHost.kt)
├── theme/               → Material 3 theme with blue color scheme
└── ui/
    ├── components/      → Reusable UI components (buttons, text fields, etc.)
    └── screens/         → Feature screens organized by feature
        ├── splash/
        ├── onboarding/
        ├── login/
        ├── forgot_password/
        └── [feature_name]/
```

### Feature Module Structure
Feature modules (in `features/`) should follow a similar structure or expose their UI/Domain logic for `sharedUI` to consume.
Package convention: `com.subot.[feature_name]` (e.g. `com.subot.home`).

### Source Set Organization

Each KMP module follows this structure:

- `commonMain/` - Shared Kotlin code for all platforms
- `androidMain/` - Android-specific implementations
- `iosMain/` - iOS-specific implementations (Darwin/UIKit)

### Platform Abstractions (expect/actual)

Use `expect`/`actual` pattern for platform-specific code:

```kotlin
// commonMain: expect fun platform(): String
// androidMain: actual fun platform() = "Android"
// iosMain: actual fun platform() = "iOS"
```

See `core/src/commonMain/kotlin/com/subot/core/Platform.kt` for reference.

## Build & Run Commands

```bash
# Build Android APK
./gradlew :androidApp:assembleDebug
# Output: androidApp/build/outputs/apk/debug/androidApp-debug.apk

# Run all tests
./gradlew test

# iOS: Open iosApp/iosApp.xcodeproj in Xcode
```

## Key Technologies & Patterns

| Technology   | Purpose                                                            |
| ------------ | ------------------------------------------------------------------ |
| Koin         | Dependency injection (use `koin-compose-viewmodel` for ViewModels) |
| Ktor         | HTTP client (okhttp on Android, darwin on iOS)                     |
| Room         | Local database with KSP for schema generation                      |
| Coil         | Image loading with Ktor network integration                        |
| Navigation 3 | Type-safe Compose navigation (see `navigation/AppRoute.kt`)        |
| Kermit       | Multiplatform logging                                              |

## Navigation (Nav3)

Type-safe navigation using serializable route objects:

```kotlin
// Define routes in navigation/AppRoute.kt
@Serializable data object Splash : AppRoute
@Serializable data object Login : AppRoute
@Serializable data class Detail(val id: String) : AppRoute

// Navigate in AppNavHost.kt
backStack.add(AppRoute.Login)
backStack.clear() // Clear stack for auth flow changes
```

## Reusable Components

Located in `sharedUI/src/commonMain/kotlin/com/sukarobot/subot/ui/components/`:

- `AppPrimaryButton`, `AppOutlinedButton`, `AppTextButton` - Styled buttons
- `AppTextField`, `AppPasswordTextField` - Form inputs with validation
- `AppScaffold` - Standard screen layout with top bar
- `AppBottomNavigation` - Animated bottom nav bar
- `PageIndicator`, `DotIndicator` - Pager indicators with animations
- `ShimmerEffect`, `ShimmerList` - Loading skeleton animations
- `AppPullToRefresh` - Pull-to-refresh wrapper
- `AppNetworkImage`, `AppCircleImage` - Image loading with Coil

## UI Development

### Theme System

- Blue color palette in `theme/Color.kt`
- Theme in `Theme.kt` - supports light/dark with Material 3 color schemes
- Uses `LocalThemeIsDark` CompositionLocal for theme state

### Screen Pattern

Each screen follows this structure:

```kotlin
// FeatureScreen.kt - Composable UI
// FeatureViewModel.kt - State management with StateFlow
// FeatureUiState.kt - UI state data class
// FeatureEvent.kt - Sealed class for UI events (MVI pattern)
// FeatureValidator.kt - Form validation (if needed)
```

### MVI Architecture Pattern

Use the MVI (Model-View-Intent) pattern with `onEvent` callbacks:

- **Model**: `FeatureUiState` - immutable data class representing UI state
- **View**: `FeatureScreen` - composable that displays state and emits events
- **Intent**: `FeatureEvent` - sealed class representing all possible UI events

**Event handling in ViewModels**:
```kotlin
// In ViewModel
fun onEvent(event: FeatureEvent) {
    when (event) {
        is FeatureEvent.Action -> handleAction()
        is FeatureEvent.DataChanged -> handleDataChanged(event.data)
    }
}

// In Composable
FeatureScreen(
    onEvent = { viewModel.onEvent(it) }
)
```

**Event handling in Composables**:
```kotlin
// Instead of direct method calls, use onEvent pattern
AppTextField(
    value = uiState.email,
    onValueChange = { onEvent(FeatureEvent.EmailChanged(it)) }
)

AppPrimaryButton(
    onClick = { onEvent(FeatureEvent.Submit) }
)
```

### Compose Resources

Place shared resources in `sharedUI/src/commonMain/composeResources/`:

- `drawable/` - Vector assets (XML format)
- `values/strings.xml` - Localized strings
- `font/` - Custom fonts

Access via: `vectorResource(Res.drawable.*)`, `stringResource(Res.string.*)`

## Testing

- **Compose UI tests**: `sharedUI/src/commonTest/` using `runComposeUiTest`
- **Android unit tests**: `core/src/androidHostTest/`
- **Android instrumented tests**: `core/src/androidDeviceTest/`

## iOS Integration

The iOS app (`iosApp/`) uses SwiftUI wrapper around `ComposeUIViewController`:

```swift
// iosApp.swift calls MainKt.MainViewController()
// Defined in sharedUI/src/iosMain/kotlin/main.kt
```

## Conventions

- Package structure: `com.sukarobot.subot` (sharedUI), `com.subot.core` (core)
- JVM target: Java 17
- Compose preview: Use `@Preview` annotation in commonMain
