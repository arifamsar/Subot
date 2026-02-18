# Shared UI Module

The `sharedUI` module is the central UI layer of the application, containing the main entry points, authentication flows, and common app screens.

## Purpose
This module ties together the specific `features/*` and `core/*` modules. It contains the application's root `App.kt`, manages the overall `AppState`, and implements common screens like Splash, Onboarding, and Login.

## Contents
- **App.kt**: The root Composable entry point for both Android and iOS.
- **AppState.kt**: Manages the global UI state across the application.
- **Navigation**: `AppNavigation` - The main navigation graph that coordinates between login, splash, and feature screens.
- **Screens**:
  - `Splash`: Initial loading screen.
  - `Onboarding`: First-time user introduction screens.
  - `Login`: Authentication flow UI.
  - `ForgotPassword`: Password recovery flow.
  - `MainScreen`: The main application container after login (hosting the bottom navigation).
- **ViewModels**: ViewModels for each of the core app screens using the **MVI** pattern.
- **Localization**: Application-wide localization utilities.

## Implementation Details
- Uses **Compose Multiplatform** for a single source of truth for the UI.
- Implements **MVI (Model-View-Intent)** with `onEvent` handlers in all ViewModels.
- Integrated with **Koin** for dependency injection of ViewModels.
- Uses **androidx-nav3** for type-safe navigation.
- Depends on all `:features` and `:core:ui`, `:core:data`, `:core:domain`.

## Usage
The `sharedUI` module is the primary dependency for both `androidApp` and `iosApp`.
It is recommended to implement any global or authentication-related UI here.
