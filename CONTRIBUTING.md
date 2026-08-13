# Contributing to KeyGuard

First off, thank you for considering contributing to KeyGuard! It's people like you that make KeyGuard a great privacy-focused keyboard.

## Code of Conduct

This project and everyone participating in it is governed by our Code of Conduct. By participating, you are expected to uphold this code. Please report unacceptable behavior to the project maintainers.

## How Can I Contribute?

### Reporting Bugs

Before creating bug reports, please check the issue list as you might find out that you don't need to create one. When you are creating a bug report, please include as many details as possible:

- **Use a clear and descriptive title**
- **Describe the exact steps to reproduce the problem**
- **Provide specific examples to demonstrate the steps**
- **Describe the behavior you observed and what you expected**
- **Include screenshots or animated GIFs if helpful**
- **Include your device info** (Android version, device model)

### Suggesting Enhancements

Enhancement suggestions are tracked as GitHub issues. When creating an enhancement suggestion, include:

- **Use a clear and descriptive title**
- **Provide a step-by-step description of the suggested enhancement**
- **Provide specific examples to demonstrate the steps**
- **Describe the current behavior and explain the expected behavior**
- **Explain why this enhancement would be useful**
- **List some other keyboards where this enhancement exists**

### Pull Requests

- Fill in the required template
- Do not include issue numbers in the PR title
- Include screenshots and animated GIFs in your pull request whenever possible
- Follow the Kotlin style guide
- Include tests when adding new functionality
- End all files with a newline

## Development Process

### Setup

1. Fork the repo
2. Clone your fork:
   ```bash
   git clone https://github.com/your-username/keyguard.git
   ```
3. Create a branch:
   ```bash
   git checkout -b feature/my-new-feature
   ```
4. Make your changes
5. Test thoroughly
6. Commit your changes:
   ```bash
   git commit -am 'Add some feature'
   ```
7. Push to the branch:
   ```bash
   git push origin feature/my-new-feature
   ```
8. Create a Pull Request

### Coding Standards

#### Kotlin Style Guide
We follow the official [Kotlin coding conventions](https://kotlinlang.org/docs/coding-conventions.html):

- Use 4 spaces for indentation
- Maximum line length of 120 characters
- Use meaningful variable names
- Write comprehensive comments
- Follow Material Design guidelines

#### Git Commit Messages
- Use the present tense ("Add feature" not "Added feature")
- Use the imperative mood ("Move cursor to..." not "Moves cursor to...")
- Limit the first line to 72 characters or less
- Reference issues and pull requests liberally after the first line

### Testing

- Write unit tests for new functionality
- Test on multiple Android versions (minimum: API 26 and 34)
- Test on different screen sizes
- Test in both light and dark themes

### Building

```bash
# Debug build
./gradlew assembleDebug

# Release build
./gradlew assembleRelease

# Run tests
./gradlew test

# Install on device
./gradlew installDebug
```

## Project Structure

```
keyguard/
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/keyguard/keyboard/
│   │   │   │   ├── keyboard/     # Core keyboard logic
│   │   │   │   ├── data/         # Data layer
│   │   │   │   ├── ui/           # UI components
│   │   │   │   └── util/         # Utilities
│   │   │   ├── res/              # Resources
│   │   │   └── AndroidManifest.xml
│   │   └── test/                 # Unit tests
│   └── build.gradle.kts
├── gradle/
├── build.gradle.kts
└── settings.gradle.kts
```

## Additional Notes

### Issue and Pull Request Labels

- `bug` - Something isn't working
- `enhancement` - New feature or request
- `documentation` - Improvements or additions to documentation
- `good first issue` - Good for newcomers
- `help wanted` - Extra attention is needed
- `priority: high` - Needs immediate attention
- `priority: low` - Nice to have

### Recognition

Contributors will be recognized in:
- The README.md file
- Release notes
- Our gratitude! 🙏

## Questions?

Feel free to open an issue with the `question` label or start a discussion in GitHub Discussions.

---

Thank you for contributing to KeyGuard! 🔐

*Together, we're building a privacy-respecting keyboard for everyone.*
