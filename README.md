# TV Shows App

A simple Android app that lists TV shows from the [TVMaze API](https://www.tvmaze.com/api), and lets you view details and share a show.

- Demo Project [YouTube Link](https://youtu.be/LPpVMHW4NZ0)

---

## Table of Contents
- [How to Run the Application](#how-to-run-the-application)
- [Architecture Decisions](#architecture-decisions)
- [What I Would Improve With More Time](#what-i-would-improve-with-more-time)

---

## How to Run the Application

### Prerequisites
- Android Studio (Koala or newer recommended)
- JDK 17
- An emulator or physical device running Android 7.0 (API 24) or above
- An active internet connection (the app fetches live data from the TVMaze API)

### Steps
1. Clone or download this repository.
2. Open the project in Android Studio.
3. Let Gradle sync automatically. If it doesn't, click **File → Sync Project with Gradle Files**.
4. Make sure the following permission is present in `AndroidManifest.xml`:
   ```xml
   <uses-permission android:name="android.permission.INTERNET" />
   ```
5. Select a device/emulator from the device dropdown.
6. Click **Run ▶** (or `Shift + F10`).

The app opens on the **List screen**, which fetches shows from the TVMaze `/shows` endpoint and displays them in a 2-column grid. Tapping a show opens the **Detail screen**, where you can also share the show via the share icon in the top bar.

### Running Unit Tests
Unit tests live under `src/test/java/com/example/tvshows/...` and can be run either:
- In Android Studio: right-click the `com.example.tvshows` test package → **Run 'Tests in ...'**
- From the command line:
  ```bash
  ./gradlew testDebugUnitTest
  ```
A readable report is generated at `app/build/reports/tests/testDebugUnitTest/index.html`.

---

## Architecture Decisions

This project follows a **three-layer architecture**, plus a dedicated dependency-injection layer:

### 1. Presentation Layer (`ui`)
Responsible for everything shown on screen: screens, reusable components, and the app's theme.
- `ui/screen/list` — the List screen and its `ListScreenViewModel`
- `ui/screen/detail` — the Detail screen
- `ui/components` — reusable, parameterized composables (poster image, rating badge, grid item) shared across screens
- `ui/theme` — a single light-mode `MaterialTheme` (colors, typography)

Each screen owns its corresponding ViewModel, which exposes UI state (via `LiveData<ResultState<T>>`) and the functions the screen can call (e.g. `loadShows()`). The screen itself stays declarative — it observes state and renders it, without containing business logic.

### 2. Domain Layer (`domain`)
Responsible for business logic, independent of both the UI and the data source.
- `domain/model` — plain models (`Show`, `Rating`, `ShowImage`) with no framework or DTO dependencies
- `domain/usecase` — use cases (e.g. `GetTvShowListUseCase`) that orchestrate a single piece of business logic and emit `ResultState` (Loading/Success/Error)
- `domain/repository` — repository *interfaces*, so the domain layer depends only on an abstraction, never on a concrete data source

This layer has no knowledge of Retrofit, DTOs, or Compose — it could be reused as-is if the UI framework or the data source changed.

### 3. Data Layer (`data`)
Responsible for retrieving and persisting data: API responses today, with room to add SharedPreferences or a Room database later without touching the domain or presentation layers.
- `data/api` — `ApiService` (Retrofit endpoints) and `ApiConfig` (Retrofit/OkHttp setup)
- `data/api/response` — DTOs that mirror the raw API response shape
- `data/mapper` — functions that convert DTOs into domain models, so DTOs never leak outside the data layer
- `data/repository` — repository *implementations* that fulfill the domain-layer interfaces, calling the API and returning domain models only

### Dependency Injection (`di`)
A lightweight, manual DI setup (no Hilt/Koin) to keep the project simple and dependency-free:
- `Injection` — wires concrete implementations together (`ApiConfig` → `TvShowRepositoryImpl` → `GetTvShowListUseCase`)
- `ViewModelFactory` — constructs ViewModels with their required use cases, since they don't have zero-arg constructors
- `ResultState` — a shared sealed class (`Loading` / `Success` / `Error`) used across every use case and ViewModel, so all asynchronous state is represented the same way throughout the app

### Why this structure
- **Separation of concerns**: UI, business rules, and data access can change independently. Swapping Retrofit for a different networking library, for instance, would only touch the `data` layer.
- **Testability**: each layer depends on abstractions (interfaces) rather than concrete implementations, so use cases and ViewModels can be tested with mocked repositories, and the repository can be tested with a mocked API service — with no Android framework or network calls required.
- **Predictable state handling**: `ResultState` gives every asynchronous operation the same three outcomes, so screens handle loading/success/error consistently instead of each screen inventing its own pattern.
- **No leaking implementation details**: DTOs never escape the data layer; the UI only ever works with domain models.

---

## What I Would Improve With More Time

- **Show more details per show**: cast, episodes, and seasons on the Detail screen.
- **Search feature**: let users search for shows by name instead of only browsing the full list.
- **Cast filmography**: allow users to tap a cast member and see which other TV shows they've appeared in.
- **Trailers**: enable playing a show's trailer directly within the app instead of linking out.