# Core Domain Module

The `core:domain` module contains the central business logic of the application, including models and repository interfaces.

## Purpose
This module is independent of any platform-specific implementations and other architectural layers (except `core:common`). It defines *what* the application does, not *how* it's implemented.

## Contents
- **Models**: Platform-agnostic data structures (`ListItem`, `PaginatedResult`, etc.) representing domain entities.
- **Repository Interfaces**: Abstract definitions for data operations (`ListItemRepository`), ensuring a decoupled architecture.
- **Use Cases**: (When applicable) Encompassing specific pieces of business logic.

## Implementation Details
- Highly decoupled, focusing only on business rules.
- Depends only on `core:common`.
- Defines interfaces that are implemented in the `:core:data` layer.

## Usage
Add this module to other modules' `build.gradle.kts`:
```kotlin
implementation(project(":core:domain"))
```
For features, it's recommended to depend on domain for repository access.
