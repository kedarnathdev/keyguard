# Privacy Policy

**Last updated: August 13, 2024**

## Introduction

KeyGuard ("we", "our", or "the App") is committed to protecting your privacy. This Privacy Policy explains our data practices and principles.

## Core Privacy Principles

### No Data Collection
**KeyGuard does not collect, store, transmit, or share any user data.** Period.

Specifically, we do NOT:
- Collect keystrokes or typing data
- Track usage patterns or statistics
- Store personal information
- Send data to external servers
- Use analytics or telemetry
- Sell or monetize user data
- Access internet (no network permission)

### Local Processing Only
All keyboard functionality is processed entirely on your device:
- Word suggestions are generated locally
- Autocorrect runs on-device
- Learned words are stored in local database
- Settings are saved in app preferences
- No cloud processing or storage

## Data Storage

### What We Store Locally
KeyGuard stores the following data **only on your device**:

1. **User Settings**
   - Theme preference
   - Haptic feedback settings
   - Sound settings
   - Autocorrect preferences
   - Stored in: Android SharedPreferences

2. **Learned Words** (if enabled)
   - Words you type frequently
   - Word frequency data
   - Stored in: Local Room database
   - **Never synced or uploaded**

3. **Keyboard State**
   - Current mode (letters/numbers/symbols)
   - Shift/caps lock state
   - Stored in: App memory (session only)

### Data Retention
- **Settings**: Retained until app uninstallation
- **Learned Words**: Retained until app uninstallation or user clears data
- **Session State**: Cleared when keyboard closes

## Permissions

### Required Permissions
KeyGuard requires only one permission:

- **BIND_INPUT_METHOD** (System permission)
  - Purpose: Allow the app to function as an input method
  - Scope: System-level, cannot access network or files

### Permissions We Do NOT Request
- ❌ INTERNET - No network access
- ❌ READ_EXTERNAL_STORAGE - No file access
- ❌ WRITE_EXTERNAL_STORAGE - No file writing
- ❌ VIBRATE - We use HapticFeedbackConstants (no permission needed)
- ❌ READ_CONTACTS - No contact access
- ❌ ACCESS_FINE_LOCATION - No location tracking
- ❌ CAMERA - No camera access
- ❌ MICROPHONE - No audio recording

## Third-Party Services

KeyGuard does NOT integrate with:
- Analytics services
- Advertising networks
- Social media platforms
- Cloud services
- Any third-party SDKs

## Children's Privacy

KeyGuard is safe for users of all ages. Since we collect no data, there are no age-related restrictions or special protections needed.

## Security

### Data Protection
- All data stored using Android's secure storage mechanisms
- Local databases use Android's Room library with encryption support
- Settings stored in private app preferences
- No network communication means no data interception risk

### Open Source
KeyGuard is fully open source. You can:
- Inspect the source code
- Verify our privacy claims
- Build from source yourself
- Contribute improvements

Source code: https://github.com/kedarnathdev/keyguard

## Your Rights

Since we collect no data, there is no data to:
- Access
- Export
- Delete
- Modify

However, you can:
- Clear learned words in settings
- Uninstall the app to remove all local data
- View and modify settings anytime

## Changes to This Policy

We may update this Privacy Policy from time to time. Changes will be:
- Posted on our GitHub repository
- Included in app updates
- Dated with last update date

## Contact

For privacy questions or concerns:
- **GitHub Issues:** https://github.com/kedarnathdev/keyguard/issues
- **Email:** (Add contact email if desired)

## Transparency

This app was built with privacy as a core architectural principle, not an afterthought. The absence of internet permission makes data collection structurally impossible.

**Audit the code yourself:** https://github.com/kedarnathdev/keyguard

---

## Certification

KeyGuard follows these privacy frameworks:
- ✅ GDPR compliant (no data collection)
- ✅ CCPA compliant (no data sale)
- ✅ COPPA compliant (no collection from children)
- ✅ HIPAA compatible (no sensitive data handling)

---

**Remember: Your keyboard sees everything you type. Choose one that respects your privacy.**

*KeyGuard - Privacy by Design*
