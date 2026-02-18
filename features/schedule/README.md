# Schedule Feature Module

The `features:schedule` module implements the schedule management functionality.

## Purpose
This module handles the display and interaction for user schedules or calendar-related features.

## Contents
- **Screens**: `ScheduleScreen` - Main UI for viewing and managing schedules.
- **Navigation**: `ScheduleNavigation` - Route definitions for the schedule feature.

## Implementation Details
- Uses **Compose Multiplatform** for a shared calendar/list UI.
- Follows the project-wide **MVI** pattern for consistency.
- Integrates with `core:ui` for shared components and icons.

## Usage
Accessible via the main navigation structure of the app.
Depends on `:core:ui` and `:core:domain`.
