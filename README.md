# KeyGuard - Privacy-First Android Keyboard 🔐

<div align="center">

![KeyGuard Logo](app/src/main/res/drawable/ic_launcher_foreground.png)

[![Build Status](https://github.com/kedarnathdev/keyguard/workflows/Build%20and%20Release%20APK/badge.svg)](https://github.com/kedarnathdev/keyguard/actions)
[![Release](https://img.shields.io/github/v/release/kedarnathdev/keyguard)](https://github.com/kedarnathdev/keyguard/releases/latest)
[![License](https://img.shields.io/badge/license-GPL--3.0-blue.svg)](LICENSE)
[![Android](https://img.shields.io/badge/Android-8.0%2B-green.svg)](https://www.android.com/)

**A privacy-focused Android keyboard that keeps your typing safe. No telemetry. No data collection. No internet required.**

[Download APK](https://github.com/kedarnathdev/keyguard/releases) • [Features](#features) • [Installation](#installation) • [Contributing](#contributing)

</div>

---

## 🛡️ Privacy First

**Your keyboard sees everything you type.** That's why KeyGuard was built from the ground up to protect your privacy:

- ✅ **No Internet Access** - Zero network permissions
- ✅ **No Telemetry** - No analytics, tracking, or usage data
- ✅ **No Cloud Storage** - Everything stays on your device
- ✅ **No Keylogging** - Your data never leaves your phone
- ✅ **Open Source** - Full transparency, audit the code yourself

**Not a promise. An architectural constraint.**

---

## ✨ Features

### Core Functionality
- 🎹 **Full QWERTY Keyboard** - Standard layout you're familiar with
- 🔤 **Word Suggestions** - Smart suggestions powered by local dictionary
- ✏️ **Autocorrect** - Intelligent error correction
- 📋 **Clipboard Support** - Easy paste functionality
- 🌐 **Multilingual** - English support with more languages coming soon

### User Experience
- 🎨 **Beautiful Themes** - Light, Dark, and System Default modes
- ✨ **Smooth Animations** - Fluid transitions and key popups
- 📱 **Haptic Feedback** - Responsive key vibration (no extra permissions!)
- ⚡ **Fast & Lightweight** - Optimized for performance
- 📐 **44x44px Keys** - Material Design standard tap targets

### Customization
- 🎚️ **Adjustable Settings** - Fine-tune your typing experience
- 🔊 **Key Sounds** - Optional audio feedback
- 💡 **Key Popups** - Visual feedback on key press
- 📝 **Learn New Words** - Expand your personal dictionary

---

## 📥 Installation

### From Release APK

1. **Download** the latest APK from [Releases](https://github.com/kedarnathdev/keyguard/releases)
2. **Enable** "Install from Unknown Sources" in Android settings
3. **Install** the APK
4. **Configure** your keyboard:
   - Go to Settings → System → Languages & input → Virtual keyboard
   - Enable **KeyGuard**
   - Select it as your default keyboard

### From Source

```bash
# Clone the repository
git clone https://github.com/kedarnathdev/keyguard.git
cd keyguard

# Build with Gradle
./gradlew assembleDebug

# Install on connected device
./gradlew installDebug
```

**Requirements:**
- Android Studio Hedgehog (2023.1.1) or later
- JDK 17
- Android SDK 34
- Gradle 8.2

---

## 🏗️ Architecture

KeyGuard follows clean architecture principles:

```
app/
├── keyboard/           # Core keyboard implementation
│   ├── KeyGuardInputMethodService.kt    # Main IME service
│   ├── KeyGuardKeyboard.kt              # Keyboard layout engine
│   └── KeyGuardKeyboardView.kt          # UI rendering
├── data/               # Local data storage
├── ui/                 # UI components
└── util/               # Utilities
```

### Key Components

- **InputMethodService** - Android's input method framework
- **Keyboard Layout Engine** - Handles key mapping and modes
- **Custom View** - Renders keyboard with Canvas
- **Settings** - SharedPreferences for user preferences
- **Dictionary** - Local word frequency database

---

## 🔒 Security

### Permissions
**KeyGuard requires ZERO dangerous permissions:**
- ❌ No Internet
- ❌ No Storage Access
- ❌ No Location
- ❌ No Contacts
- ✅ Only `BIND_INPUT_METHOD` (required for keyboard)

### Haptic Feedback
Uses `View.performHapticFeedback()` instead of `VIBRATE` permission - respects system settings and feels natural.

---

## 🤝 Contributing

Contributions are welcome! Please read our [Contributing Guidelines](CONTRIBUTING.md) first.

### Development Setup

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

### Code Style
- Kotlin coding conventions
- 120 character line limit
- Meaningful variable names
- Comprehensive comments

---

## 📋 Roadmap

- [ ] Swipe typing (gesture input)
- [ ] Additional languages (Spanish, French, German, etc.)
- [ ] Emoji keyboard
- [ ] Custom theme editor
- [ ] Backup/restore settings
- [ ] Tablet optimizations
- [ ] One-handed mode
- [ ] Clipboard history

---

## 📄 License

This project is licensed under the GNU General Public License v3.0 - see the [LICENSE](LICENSE) file for details.

```
KeyGuard - Privacy-First Android Keyboard
Copyright (C) 2024 Kedarnath

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.
```

---

## 🙏 Acknowledgments

- [Android InputMethod Framework](https://developer.android.com/develop/ui/views/touch-and-input/creating-input-method)
- [Material Design Guidelines](https://material.io/design)
- [Open Source Keyboards](https://github.com/topics/android-keyboard) for inspiration

---

## 📞 Support

- **Issues:** [GitHub Issues](https://github.com/kedarnathdev/keyguard/issues)
- **Discussions:** [GitHub Discussions](https://github.com/kedarnathdev/keyguard/discussions)
- **Security:** See [SECURITY.md](SECURITY.md)

---

<div align="center">

**Made with ❤️ for privacy**

[⬆ Back to Top](#keyguard---privacy-first-android-keyboard-)

</div>
