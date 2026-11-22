# APK analysis — MovieBox v4.0.04.1119.03

## Input and structure

- Uploaded APK: `moviebox-v-4.0.04.1119.03.apk`
- Size: 84,416,834 bytes.
- ZIP entries: 6,510.
- Primary DEX: `classes.dex`, 68,332,660 bytes.
- DEX header: version 037.
- DEX string IDs: 212.
- DEX type IDs: 58.
- DEX method IDs: 114.
- DEX class definitions: **4**.
- The four class descriptors are heavily obfuscated: `Lh7z/zd/bz/CN6SPr9Ww;`, `Lh7z/zd/bz/LMN6mQHv;`, `Lh7z/zd/bz/RyKTl9wlm;`, and `Lh7z/zd/bz/U0Ie6Ay;`.

The mismatch between a 68.3 MB DEX and only four class definitions/212 strings is a strong indication that this is not a conventional fully compiled app DEX. The APK also contains a 9.6 MB opaque asset at `assets/Qpq9X3WRH` (starts with `indl01`, not a ZIP or DEX header), consistent with a protected/packed payload. This finding blocks ordinary source-level reconstruction from the DEX alone.

## Android manifest facts

The binary manifest was parsed directly:
- Package/application ID: `com.community.oneroom`.
- Version name: `4.0.04.1119.03`.
- Version code: `50020137`.
- Minimum SDK: 24.
- Target SDK: 36.
- The manifest declares SDK components for Hisavana, Pangle/ByteDance OpenAds, Mintegral/MBridge, Vungle, and Transsion advertising. This is stronger evidence that ad SDK code is packaged, but it still does not establish which placements are actually requested at runtime.
- Manifest metadata includes `tran_hisavana_version=3.6.8.2` and `com.bytedance.sdk.pangle.version=7.1.1.4`.

Manifest/API-like host strings include `api.byte-app.com`, `api.eagllwin.com`, `api.sunnbird.com`, `h5.aoneroom.com`, and several MovieBox-related domains. These are **candidate hosts only**: their presence in the manifest string pool does not prove which one serves playback, account, or entitlement APIs. The manifest also includes `AD_SUBMIT_FORM=https://api.eagllwin.com`, which appears to be a mini-app/ad-form endpoint rather than a confirmed media API.

## Build metadata

- Android Gradle Plugin metadata: `8.13.2`.
- Version-control metadata records commit SHA `8514b619d304e02423a9823a69a02c124ca667be`.
- The metadata does not provide a repository remote URL.
- No original Gradle files, manifest source, Kotlin/Java source, or Git history are included as normal source files in the APK.

## Navigation recovered from `assets/appTab.json`

Bottom navigation entries:
- Home (`HOME`)
- NovelHub (`NOVEL`)
- ShortTV (`SHORTTV`)
- Downloads (`DOWNLOADS`)
- Me (`ME`)

Home sub-tabs include Football, Trending, Movie, Nollywood, TV, ShortTV, TV Channel, Anime, Kids, Education, Music, Free Novels, Game, and FightZone. The Anime entry is `tabId: 8`, type `OperateTab`, `tabCode: Animation`.

A sub-tab named `wajegame` is configured as `H5Tab`, `tabCode: AD`, and points to a promotional H5 landing page. This is a clear candidate for removal from the app's own navigation. Image assets hosted on `pbcdn.aoneroom.com` are also referenced by the same configuration; they are UI/CDN assets and must not be blanket-blocked as advertising.

## Advertising evidence

The APK contains advertising-related resources/metadata, including:
- `assets/hisavana_ab_data.json`
- `assets/mbridge_download_dialog_view.xml`
- Pangle-related verification/metadata strings observed in the package scan.
- Google advertising-ID integration metadata observed in the package scan.
- `assets/test_h5_ad.html`
- The explicit `wajegame` H5 tab described above.

These are integration/resource indicators, not proof that every SDK is initialized or every ad network is active at runtime. Before removing dependencies, call sites and manifest declarations must be identified from a usable decompilation or source project.

## Native media stack

The APK bundles native libraries for MPV, FFmpeg (including codec/format/filter/util libraries), and player/downloader components such as `libplayer.so`, `libsaasCorePlayer.so`, and `libsaasDownloader.so`. These are likely important to playback and must not be deleted as part of ad cleanup.

## API and entitlement status

The manifest exposes candidate API/domain strings (listed above), but static inspection still does not identify a confirmed playback API base URL or enough information to map all account/VIP checks. Anime category configuration is visible, but title/episode APIs and the Hindi playback source for any particular series cannot be established from this asset inventory.

The app's own UI/entitlement checks can only be safely removed after identifying their implementation and backend contract. Third-party provider authentication or access checks must remain intact; this recovery will not attempt to circumvent those controls.

## Current blockers and next required evidence

1. Obtain the original source repository/backup or a less-protected build, if available.
2. Otherwise obtain a valid unpack/decryption/decompilation path for the protected DEX/payload, plus runtime analysis on a test device.
3. Capture authorized app network traffic while navigating and playing a test title to identify API endpoints and request/response shapes. Redact cookies, bearer tokens, device identifiers, and account data before sharing.
4. Once source is reconstructed, restore Gradle configuration, build variants, signing setup, then add GitHub Actions and validate a debug APK build.

## Tooling note

At the time of this inspection, `java` is installed, but `jadx`, `apktool`, `aapt`, `aapt2`, `dexdump`, and `baksmali` are not installed. The environment cannot resolve GitHub hosts from the container, so downloading a decompiler from the internet is not currently possible. No decompilation or APK rebuild has been claimed.
