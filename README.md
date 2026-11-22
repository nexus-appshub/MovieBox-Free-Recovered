# MovieBox Free — APK Recovery

This repository is the recovery workspace for the user-provided MovieBox Android APK.

## Current status

- APK inspected: `moviebox-v-4.0.04.1119.03.apk` (version label from uploaded filename).
- APK package structure and selected assets inventoried.
- The APK does **not** currently yield a normal, recoverable Android application source tree.
- No Gradle project has been reconstructed yet, so an Android build workflow is intentionally not enabled. Adding a workflow before a valid Gradle project exists would only create a misleading failing build.

## Recovery objectives

1. Reconstruct the app's own UI and functionality as closely as the available evidence allows.
2. Remove the app's own advertising placements and ad SDK integrations where they can be identified safely.
3. Remove the app owner's own VIP/paywall restrictions, without bypassing authorization imposed by third-party content providers.
4. Preserve playback, downloads, navigation, and other existing features where technically possible.
5. Add a reproducible Gradle build and GitHub Actions APK artifact workflow after a valid project is recovered.

## Evidence and limitations

See [APK analysis](docs/APK_ANALYSIS.md) for the technical findings and blockers.

**Important:** Decompiled/reconstructed code is not the original source. Obfuscation, encrypted payloads, native libraries, and missing backend/API details may prevent exact recovery. No claim of a successful app build is made until a real Gradle project is present and CI passes.
