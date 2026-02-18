# Profile Feature Module

The `features:profile` module implements the user profile and settings screens.

## Purpose
This module allows users to view and edit their profile information, as well as configure application settings.

## Contents
- **Screens**: `ProfileScreen`, `SettingsScreen` - UI implementations for user data and settings.
- **ViewModels**: `ProfileViewModel` - State management for user profile interactions.
- **Navigation**: `ProfileNavigation` - Routes and navigation graph for profile and settings screens.

## Implementation Details
- Uses **MVI (Model-View-Intent)** with a reactive `ProfileUiState`.
- Implements settings persistence (via `core:data`'s DataStore).
- Uses **Koin** for ViewModel injection.

## Usage
The profile feature is typically accessible from the main bottom navigation bar defined in `sharedUI`.
It depends on `:core:ui` and `:core:domain`.
