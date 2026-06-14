# MealScope Capstone

MealScope adalah aplikasi katalog makanan berbasis TheMealDB API untuk submission Capstone Android Developer Expert.

## Fitur

- List makanan seafood dari network API.
- Detail makanan berisi gambar, kategori, asal, dan instruksi.
- Favorite makanan memakai Room database.
- Dynamic feature module `favorite`.

## Arsitektur

- Modul `app`: presentation layer untuk list dan detail.
- Modul `core`: data, domain, repository, Room, Retrofit, mapper, dan use case.
- Modul `favorite`: dynamic feature untuk daftar favorite.
- Clean Architecture dengan model berbeda untuk data, domain, dan presentation.
- Dependency Injection memakai Koin.
- Reactive Programming memakai Kotlin Flow untuk network dan database.
- Continuous Integration memakai GitHub Actions pada `.github/workflows/android-ci.yml` dan CircleCI pada `.circleci/config.yml`.
- Performance tooling memakai LeakCanary pada debug build dan lint task di CI.
- Security memakai ProGuard obfuscation, SQLCipher database encryption, dan OkHttp certificate pinning.

## Build

```bash
./gradlew assembleDebug
```

## CI

GitHub Actions workflow:

https://github.com/aqilaziz/mealscope-capstone-dicoding-165/actions/workflows/android-ci.yml

Commands:

```bash
./gradlew lintDebug testDebugUnitTest assembleDebug assembleRelease
```
