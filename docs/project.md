# LinkNest — Project Overview

*Owner: Alzimer Ahmed <alzimerahmed84@gmail.com>*

## What It Is
LinkNest (formerly Linksi) is a local-first Android link-saver built with 100% Kotlin, Jetpack Compose, Material 3, Room, Hilt, MVVM + StateFlow. Min SDK 26, target 34.

## Architecture
- `app/src/main/java/com/linksi/app/`
  - `data/db/` — Room entities (`LinkEntity`, `FolderEntity`, `MetadataCacheEntity`), DAOs, database with 11 migrations
  - `data/repository/LinkRepository.kt` — single repository over DAOs, entity↔domain mappers
  - `di/AppModule.kt` — Hilt singleton providers
  - `domain/model/` — `Link`, `Folder`, AI models registry
  - `ui/screens/` — Home, Folder, Settings (+ViewModels), ImportExport, InAppBrowser, AI Organizer, Security, TrashBin, Onboarding, ShareReceiverActivity
  - `ui/components/` — link cards, dialogs, sheets, progress components
  - `utils/` — MetadataFetcher (Jsoup + hosted scraper API + WebView fallback), ImportExportManager (JSON/CSV/HTML, browser & Pocket HTML import), SecurityManager (PIN/biometric), BackgroundImportManager, AiOrganizerService, ReminderUtils
  - `worker/BinCleanupWorker.kt` — 30-day trash retention
- Persistence: Room (`linksi_db`) + DataStore preferences (theme, security, AI keys, feature toggles)

## Key Flows
- Save: share sheet → `ShareReceiverActivity` → metadata fetch → insert (URL normalized, duplicates skipped)
- Import: file picker → `BackgroundImportManager` (progress UI, duplicate detection)
- AI: BYO API key (OpenAI/Anthropic/Gemini/DeepSeek/Grok) → batched organize plans with preview/revert

## History
- Cloned from `AsukaAzure/Linksi`, fully detached from origin, all commit metadata and in-code attribution rewritten to Alzimer Ahmed (2026-09).
- Tags v3.1.0/v3.1.1 preserved under rewritten history.

## Implemented Improvements

### Phase A (docs/plan.md)
1. **Bulk duplicate finder** — `LinkDao.getDuplicateLinks()`, `LinkRepository.getDuplicateLinks()`, Settings entry + `DuplicatesDialog` (keep-one, rest to trash).
2. **Pocket import tags** — `importFromBrowserHtml` now reads `TAGS` attribute from Pocket HTML exports.
3. **Reading stats** — unread count + top-3 domain chips in Settings stats card.

### Phase B
4. **Reader mode** — `utils/ReaderExtractor.kt` (Jsoup readability extraction) + `ReaderView` composable in `InAppBrowser` with font-size controls and a bottom-bar toggle. Offline text cache deferred (needs DB migration).

### Phase C
5. **Broken-link checker** — `utils/LinkHealthChecker.kt` (batched HEAD/GET checks, concurrency 8) + Settings entry with "move dead links to trash" dialog. Smart collections deferred to a later phase.

## Remaining Roadmap
See `docs/plan.md` Phases D–E (AI tagging/summaries, widget, swipe gestures, tests).
