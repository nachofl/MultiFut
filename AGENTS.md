# AGENTS.md

## Project Overview
- Kotlin Multiplatform project with three Gradle modules:
  - `:shared` – common business logic and Firebase integration.
  - `:shared-ui` – multiplatform Compose UI used by Android and iOS.
  - `:AndroidApp` – Android application that bundles `shared` and `shared-ui`.
  - `iOSApp` – Xcode project that imports the static framework generated from `:shared`.
- Kotlin 2.0.0, Gradle 8.9.2, Android API 35, iOS 15+.
- Uses Firebase (Firestore, Auth, Analytics) – `google-services.json` must be present.

## Gradle Wrapper
- Use the `gradlew` (or `gradlew.bat` on Windows) script in the repo root.
- Example: `./gradlew :AndroidApp:assembleDebug`.
- The wrapper is configured to download Gradle 8.9.2 via `gradle-wrapper.properties`.

## Building the Project
| Module | Command | Purpose |
|--------|---------|---------|
| All modules | `./gradlew build` | Compile code, run tests, generate artifacts for all subprojects. |
| Android app | `./gradlew :AndroidApp:assembleDebug` | Build the debug APK. |
| Android app (install) | `./gradlew :AndroidApp:installDebug` | Install the APK on a connected device/emulator. |
| iOS frameworks | `./gradlew :shared:build` | Generates the static framework for iOS (`build/iosX64/...`, `build/iosArm64/...`, `build/iosSimulatorArm64/...`). |
| iOS app | Open `iOSApp/iosFutApp.xcodeproj` in Xcode and build, or run `xcodebuild -workspace iOSApp/iosFutApp.xcodeproj -scheme iOSFutApp -configuration Debug`. |

## Running Tests
| Module | Test Command | Notes |
|--------|--------------|-------|
| `:shared` | `./gradlew :shared:test` | Executes JVM tests in `commonTest`. |
| `:shared-ui` | `./gradlew :shared-ui:test` | Executes JVM tests in `androidHostTest` and any shared UI tests. |
| `:AndroidApp` | `./gradlew :AndroidApp:test` | Executes local unit tests (`androidTest` directories if present). |
| Android Instrumentation | `./gradlew :AndroidApp:connectedAndroidTest` | Requires an emulator or device connected via ADB. |

## Linting & Formatting
- **Check**: `./gradlew ktlintCheck` – runs ktlint across all subprojects.
- **Format**: `./gradlew ktlintFormat` – automatically formats source files.

## Common Gotchas
- **Gradle Version**: The wrapper uses Gradle 8.9.2; using a different Gradle version may break builds.
- **Java JDK**: Project compiles against Java 8 for Android, but you need at least JDK 11 to run the wrapper on Windows.
- **Firebase**: The Android module applies the `com.google.gms.google-services` plugin, so `google-services.json` must be present in the module root (it is already included).
- **iOS Framework Generation**: After building `:shared`, the static framework must be added to the Xcode project. If it is missing, Xcode will fail to compile.
- **Android SDK**: The build requires Android SDK 35. Ensure `ANDROID_HOME` points to a valid SDK installation.

## Quick Commands
- `./gradlew clean` – clean all outputs.
- `./gradlew :AndroidApp:assembleRelease` – build release APK.
- `./gradlew :shared-ui:build` – build only the UI module (for preview generation).
- `./gradlew :AndroidApp:debugAndroidTest` – run debug unit tests.

---

Feel free to add additional notes if you encounter any project‑specific quirks.
