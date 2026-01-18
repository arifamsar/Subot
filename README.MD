# Subot

Subot is a Kotlin Multiplatform (KMP) application targeting Android and iOS using Compose Multiplatform for shared UI. The project follows MVI (Model-View-Intent) architecture with `onEvent` patterns for clean, maintainable code.

## Architecture Overview

### Module Structure
```
:androidApp    → Android app shell (thin wrapper, only entry point)
:sharedUI      → Shared UI layer (Compose Multiplatform screens, themes, ViewModels)
:core          → Shared business logic (data/domain layers, platform abstractions)
```

**Dependency flow**: `androidApp → sharedUI → core`

### MVI Pattern with onEvent
The application uses MVI (Model-View-Intent) architecture:
- **Model**: `FeatureUiState` - immutable data class representing UI state
- **View**: `FeatureScreen` - composable that displays state and emits events
- **Intent**: `FeatureEvent` - sealed class representing all possible UI events

Example:
```kotlin
// In ViewModel
fun onEvent(event: FeatureEvent) {
    when (event) {
        is FeatureEvent.EmailChanged -> handleEmailChanged(event.email)
        is FeatureEvent.Submit -> handleSubmit()
    }
}

// In Composable
AppTextField(
    value = uiState.email,
    onValueChange = { onEvent(FeatureEvent.EmailChanged(it)) }
)
```

## Getting Started

### Prerequisites
- Android Studio (latest version recommended)
- Xcode (for iOS development)
- Kotlin Multiplatform Mobile plugin for Android Studio (optional)

### Setup
1. Clone the repository
2. Open the project in Android Studio
3. Sync the Gradle project
4. Build and run on your preferred platform

## Development

### Android
To run the application on android device/emulator:
 - open project in Android Studio and run imported android run configuration

To build the application bundle:
 - run `./gradlew :androidApp:assembleDebug`
 - find `.apk` file in `androidApp/build/outputs/apk/debug/androidApp-debug.apk`

### iOS
To run the application on iPhone device/simulator:
 - Open `iosApp/iosApp.xcproject` in Xcode and run standard configuration
 - Or use [Kotlin Multiplatform Mobile plugin](https://plugins.jetbrains.com/plugin/14936-kotlin-multiplatform-mobile) for Android Studio

### Adding New Features
When creating new screens/features, follow this structure:
```
sharedUI/src/commonMain/kotlin/com/sukarobot/subot/ui/screens/feature/
├── FeatureScreen.kt      # Composable UI with onEvent callback
├── FeatureViewModel.kt   # State management with onEvent method
├── FeatureUiState.kt     # Immutable UI state data class
├── FeatureEvent.kt       # Sealed class for all UI events
└── FeatureValidator.kt   # Form validation (if needed)
```

## Key Technologies
- **Koin**: Dependency injection
- **Ktor**: HTTP client
- **Room**: Local database
- **Coil**: Image loading
- **Navigation 3**: Type-safe Compose navigation
- **Kermit**: Multiplatform logging

## Build Commands
```bash
# Build Android APK
./gradlew :androidApp:assembleDebug

# Run all tests
./gradlew test

# Build for iOS (requires Xcode setup)
./gradlew :sharedUI:build
```

## Contributing
1. Follow the MVI architecture pattern with `onEvent` callbacks
2. Maintain consistent naming conventions
3. Write unit tests for business logic
4. Use proper error handling and loading states
5. Follow Material Design 3 guidelines for UI components

