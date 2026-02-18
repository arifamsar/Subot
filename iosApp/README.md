# iOS App Module

The `iosApp` module is the platform-specific entry point for the iOS application.

## Purpose
This module contains the Xcode project and Swift code required to launch the shared Compose Multiplatform application on iOS devices.

## Contents
- **iosApp.swift**: The SwiftUI application entry point.
- **ContentView.swift**: A SwiftUI view that wraps the shared Compose `UIViewController`.
- **Assets.xcassets**: iOS-specific assets like the App Icon.
- **Info.plist**: iOS configuration file.

## Implementation Details
- Uses a `ComposeUIViewController` to bridge the shared Compose UI into the iOS view hierarchy.
- Communicates with the `:sharedUI` module to render the app.
- Manages iOS-specific lifecycle events.

## How to Run
- Open `iosApp/iosApp.xcodeproj` in **Xcode**.
- Select a simulator or physical device.
- Click **Run**.
- Alternatively, use the **Kotlin Multiplatform Mobile** plugin in Android Studio to run directly.
