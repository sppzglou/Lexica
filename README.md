# 📘 Lexica

**Lexica** is a simple Android project written in **Kotlin**, demonstrating modern app development with clean architecture, reactive state management, and Material3 design.

---

## 🔧 Tech Stack & Libraries

- 📐 **Clean Architecture** with 3 modules:
  - `data` – handles API and local database
  - `domain` – holds business logic
  - `presentation` – UI, ViewModels, navigation
- 🧠 **MVI Pattern** – Model-View-Intent for predictable state management
- 🛠 **Dagger Hilt** – dependency injection
- 🗄 **Room (SQLite)** – local persistence
- 🔁 **Kotlin Flows** & **Coroutines** – reactive and async programming
- 🎨 **Jetpack Compose** – declarative UI toolkit
- 🎨 **Material3** – modern Material You design components
- 🧭 **Compose Destinations** – type-safe navigation
- 📦 **Gson** – JSON parsing

---

## 📱 Screens

1. **🔍 Search Screen**
  - Type a word and search using an API
  - Navigate to the word's details

2. **📖 Details Screen**
  - Shows full definitions, phonetics, examples
  - Allows saving/removing from favorites

3. **⭐ Favorites Screen**
  - Lists all saved favorite words
  - Navigate to full details

---

## ▶️ Getting Started

1. Clone the repository:
   ```bash
   git clone https://github.com/sppzglou/Lexica.git
   ```
2.	Open the project in Android Studio Giraffe or newer
3.	Make sure you’re using JDK 17
4.	Run the app on a device or emulator

---

## 📂 Project Structure

```
Lexica/
├── data/                # Data layer (Room DB, API, DTOs, Mappers)
│   ├── local/           # Room database
│   │   ├── entities/    # Room entities (e.g. WordEntity)
│   │   ├── AppDao.kt
│   │   └── AppDatabase.kt
│   ├── remote/          # Network layer (Retrofit)
│   │   ├── dto/         # Data transfer objects from API
│   │   └── ApiService.kt
│   └── mappers/         # Mappers: DTO <-> Entity <-> Domain
├── domain/              # Domain layer (business logic)
│   ├── cases/           # Use cases (e.g. GetWordUseCase)
│   └── models/          # Domain models (e.g. WordDomain)
├── presentation/        # UI layer (Jetpack Compose, MVI)
│   ├── base/            # Base state/effect/viewmodel classes
│   ├── screens/         # All screens (Search, Details, Favs)
│   └── components/      # Reusable composables
```

---

## ✨ Features
-	Reactive UI with StateFlow
-	Full screen navigation with Compose Destinations
-	Material3 components for modern look & feel
-	Offline support using Room
-	Architecture ready for scaling

---

## 📃 License

Copyright (c) 2025 Symeon Papazoglou

All rights reserved.
This software is proprietary and confidential. No part of this codebase may be copied, modified, distributed, published, sublicensed, or sold in any form without the prior written permission of the author.
The code is provided for **personal and non-commercial use only**.
Unauthorized use or duplication is strictly prohibited.
