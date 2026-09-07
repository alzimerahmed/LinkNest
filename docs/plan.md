# LinkNest — Phased Implementation Plan

Derived from `docs/idea.md`. Each phase ends with a code review (per `phase.md` step 7) before proceeding.

## Phase A — Quality of Life (highest impact, lowest risk)
1. **Bulk duplicate finder**: DAO query grouping by normalized URL → `DuplicateFinder` in repository → Settings entry with preview dialog and bulk "keep newest / keep oldest" resolution.
2. **Pocket HTML import**: extend `ImportExportManager` with Pocket export (`Pocket.html` / Netscape format) detection — reads `#unread`/`#read` list items with tags from `#readwise`-style attributes; wire into existing import flow.
3. **Reading statistics**: repository aggregate queries (saved per day, read ratio, top domains) → stats card in Settings + empty-state polish.

## Phase B — Reading Experience
1. **Reader mode**: Jsoup readability extraction in `InAppBrowser` with a toggle, font-size control, and cached article text stored per link.
2. **Offline text cache**: store extracted article text at save time (opt-in setting).

## Phase C — Organization Power Features
1. **Smart collections**: saved filter queries rendered alongside folders.
2. **Broken-link checker**: WorkManager job doing batched HEAD checks, badge + bulk cleanup UI.

## Phase D — AI Enhancements
1. **AI auto-tagging** per link using existing `AiOrganizerService` plumbing.
2. **AI summaries** stored in the note field with provenance label.

## Phase E — Surfaces & Polish
1. **Quick-save home-screen widget** (Glance).
2. **Swipe gestures** on link cards (open / archive), haptics, motion polish.
3. **Testing**: unit tests for import/export, URL normalization, duplicate finder; CI workflow extension.

## Out of Scope (for now)
- Cloud sync / accounts (local-first is a feature, revisit later).
- MCP server / desktop apps.
