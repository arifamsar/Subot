# Home Feature Module

The `features:home` module implements the main dashboard/home screen of the application.

## Purpose
This module handles the UI and logic for the Home section of the app, typically the first screen users see after logging in.

## Contents
- **Screens**: `HomeScreen` - The main UI implementation using Compose Multiplatform.
- **Navigation**: `HomeNavigation` - Defines routes and integration into the app's navigation graph.
- **Platform**: Platform-specific logic (if any) for the home screen.

## Implementation Details
- Follows the **MVI (Model-View-Intent)** architecture pattern.
- Integrates with `core:ui` for consistent design.
- Uses `core:domain` for fetching home-related data.

## Usage
The home feature is typically navigated to from the `sharedUI` main screen or splash screen.
It is part of the `:features` group of modules.
