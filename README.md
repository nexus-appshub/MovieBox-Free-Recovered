# MovieBox Free — APK Recovery

This repository contains an initial, buildable Android recovery shell plus the APK analysis notes.

## Build status

A minimal Android application scaffold and GitHub Actions workflow are now present. Every push affecting the Android project or workflow triggers a debug APK build; a manual run is also available under **Actions → Android APK Build → Run workflow**.

The generated APK is a **placeholder recovery shell**, not the recovered MovieBox application. It shows the navigation/category names recovered from the APK configuration, but does not yet implement MovieBox's original screens, media APIs, account system, downloads, or playback.

## Downloading the build

After the workflow succeeds:
1. Open the repository's **Actions** tab.
2. Select the latest **Android APK Build** run.
3. Download the `moviebox-recovery-debug-apk` artifact.

## Recovery objectives

1. Reconstruct the app's own UI and functionality as closely as the available evidence allows.
2. Remove the app owner's own advertising placements and VIP/paywall restrictions where their implementation can be identified and safely changed.
3. Preserve playback, downloads, navigation, and other features where technically possible.
4. Keep third-party provider authorization and access controls intact.

## Important limitations

- The original APK is protected/packed; its real implementation has not been recovered.
- The current shell is only a build-pipeline proof and must not be represented as an exact MovieBox clone.
- No decrypted DEX, original source tree, ad-free production build, or VIP-free production build has been produced.
- Decompiled/reconstructed code is not the original source.
- The app uses a separate package ID (`com.moviebox.recovered`) so it can be installed alongside the original app for shell testing.
