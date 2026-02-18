# Core Common Module

The `core:common` module provides shared utilities, base classes, and platform-agnostic logic that are used across the entire application.

## Purpose
This module serves as the foundation for other modules, containing code that doesn't belong to a specific feature or layer (like data or domain) but is needed globally.

## Contents
- **AppDispatchers**: Provides a central point for accessing Coroutine Dispatchers (Main, IO, Default), facilitating easier testing and consistency.
- **DateTimeExtensions**: Shared utilities for handling date and time across platforms using `kotlinx-datetime`.
- **CommonModule**: Koin module for dependency injection of common components.

## Implementation Details
- Uses `kotlinx-coroutines` for asynchronous operations.
- Uses `kotlinx-datetime` for multiplatform date/time handling.
- Integrated with **Koin** for dependency injection.

## Usage
Add this module to other modules' `build.gradle.kts`:
```kotlin
implementation(project(":core:common"))
```
