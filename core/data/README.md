# Core Data Module

The `core:data` module handles data persistence, network operations, and the concrete implementation of repository interfaces.

## Purpose
This module is responsible for fetching data from remote APIs (Ktor), local databases (Room), or preferences (DataStore), and providing that data to the `core:domain` layer through repository implementations.

## Contents
- **DI (Dependency Injection)**: Koin modules (`DataModule`, `HttpModule`, `PreferencesModule`) for data source and repository injection.
- **DTOs (Data Transfer Objects)**: Models (`ListItemDto`, `ListResponseDto`) that match the data structure from remote APIs.
- **Mappers**: Logic (`ListItemMapper`) to transform DTOs into domain models and vice versa.
- **Repository Implementations**: Concrete classes (`ListItemRepositoryImpl`) that implement domain repository interfaces.
- **Services**: Networking with **Ktor** (`ApiService`) and local storage with **DataStore** (`UserPreferences`).
- **Database**: Local persistence using **Room** (configured via convention plugins).

## Implementation Details
- Uses **Ktor** for HTTP requests with `ContentNegotiation` and `Serialization`.
- Implements **Room** for local database support.
- Uses **DataStore** for persistent key-value storage.
- Mapping logic ensures the data layer's DTOs don't leak into the domain layer.

## Usage
Add this module to other modules' `build.gradle.kts`:
```kotlin
implementation(project(":core:data"))
```
Repositories are injected into ViewModels using Koin.
