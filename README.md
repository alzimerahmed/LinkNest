# LinkNest — Link Saver for Android

<div align="center">

<img src="asset/LinkNest%20Icon.png" alt="LinkNest" width="128"/>

[![Android API 26+](https://img.shields.io/badge/Android-API%2026%2B-green?logo=android)](https://www.android.com)
[![Kotlin](https://img.shields.io/badge/Kotlin-100%25-blue?logo=kotlin)](https://kotlinlang.org)
[![Jetpack Compose](https://img.shields.io/badge/Jetpack-Compose-4285F4?logo=android)](https://developer.android.com/jetpack/compose)
[![Material Design 3](https://img.shields.io/badge/Material%20Design-3-6200EA?logo=materialdesign)](https://m3.material.io)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](LICENSE)

*Organize your digital life, one link at a time*

[Download](https://github.com/alzimerahmed/LinkNest/releases/latest) • [Features](#features) • [Building](#building)

</div>

---

## Overview

**LinkNest** is a local-first link saver for Android. Save links from any app through the native share sheet, organize them into folders, and rediscover them with full-text search — all stored privately on your device.

Built with 100% Kotlin and Jetpack Compose, following Material Design 3.

---

## Features

- **Save from anywhere** — share sheet integration with auto-fetched title, description, and favicon
- **Folders & favorites** — custom folders with icons and colors, favorites, read/unread tracking
- **Full-text search** — search by title, domain, and description
- **Reader mode** — distraction-free reading in the built-in browser, with adjustable text size
- **Duplicate finder** — scan your library and keep one copy of each link
- **Link health checker** — find broken links and move them to trash in bulk
- **AI organizer** — automatically categorize links into folders using your own AI API key (OpenAI, Anthropic, Gemini, DeepSeek, Grok)
- **Import/Export** — JSON, CSV, and HTML; import browser bookmarks and Pocket exports (with tags)
- **Trash bin** — 30-day recovery before permanent deletion
- **Security** — app lock and folder lock with PIN or biometrics
- **Material You** — dynamic color, dark mode, and AMOLED support
- **Reminders** — notifications to revisit saved links

---

## Tech Stack

| Layer | Technology |
|-------|------------|
| UI | Jetpack Compose, Material Design 3 |
| Architecture | MVVM + StateFlow |
| Database | Room |
| Dependency Injection | Hilt |
| Image Loading | Coil |
| Web Scraping | Jsoup |
| Async | Kotlin Coroutines + Flow |
| Language | 100% Kotlin |
| SDK | Min API 26 · Target API 34 |

---

## Getting Started

### Download

Grab the latest signed APK from the [Releases](https://github.com/alzimerahmed/LinkNest/releases/latest) page and install it on any device running Android 8.0+.

### Build from source

Requirements: Android Studio (Hedgehog or newer), JDK 17, Android SDK 34.

```bash
git clone https://github.com/alzimerahmed/LinkNest.git
cd LinkNest
./gradlew assembleDebug
```

Output: `app/build/outputs/apk/debug/app-debug.apk`

For a signed release build, configure your signing key in `app/build.gradle` or via GitHub Actions secrets (the release workflow builds and publishes an APK on every `v*` tag).

---

## License

This project is licensed under the MIT License — see the [LICENSE](LICENSE) file for details.

---

<div align="center">

Made with ❤️ by [Alzimer Ahmed](https://github.com/alzimerahmed/)

</div>
