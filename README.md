# Mobile UI Automation Framework (Appium + Java + Cucumber + TestNG)

This project automates the Sauce Labs sample mobile apps for Android and iOS using Appium, Java, Cucumber, and TestNG.

---

## Required Tools

Before running the tests, make sure the following are installed and configured:

1. Java 17 or newer
   - Set `JAVA_HOME`
   - Confirm with: `java -version`

2. Apache Maven
   - Install Maven and add it to `PATH`
   - Confirm with: `mvn -version`

3. Node.js and npm
   - Required for Appium installation
   - Confirm with: `node -v` and `npm -v`

4. Appium Server v2
   ```bash
   npm install -g appium
   appium driver install uiautomator2
   appium driver install xcuitest
   ```

5. Android setup (for Android execution)
   - Android Studio / Android SDK
   - Ensure `ANDROID_HOME` and `PATH` include:
     - `%ANDROID_HOME%\platform-tools`
     - `%ANDROID_HOME%\emulator`
   - Confirm with: `adb devices`

6. iOS setup (for iOS execution)
   - Xcode installed on macOS
   - iOS simulator or physical iPhone connected

---

## Project Structure

- `src/main/java` – page objects, driver setup, utilities
- `src/test/java` – step definitions and Cucumber tests
- `src/test/resources/config` – platform-specific config properties
- `apps/` – APK and IPA app files
- `target/` – build output and test reports

---

## Platform Configuration

The project loads configuration based on the selected platform:

- Android config: `src/test/resources/config/android.properties`
- iOS config: `src/test/resources/config/ios.properties`

Example Android settings:

```properties
platform=android
device.name=33555e227d29
device.udid=33555e227d29
platform.version=9
app.path=apps/sample-app-android.apk
appium.server.url=http://127.0.0.1:4723
new.command.timeout=240
autoGrantPermissions=true
```

Update the values for your real device/emulator, especially:
- `device.udid`
- `platform.version`
- `app.path`

---

## Start Appium Server

Open a terminal and run:

```bash
appium
```

The default Appium URL used by the framework is:

```text
http://127.0.0.1:4723
```

---

## Run Tests

### Android

```bash
mvn test -Dplatform=android
```

### iOS

```bash
mvn test -Dplatform=ios
```

### Run a specific Cucumber scenario

```bash
mvn test '-Dplatform=android' '-Dcucumber.filter.name=Successful login with valid credentials'
```

---

## Sample Apps

The framework expects app binaries in the `apps/` folder:

- Android: `apps/sample-app-android.apk`
- iOS: `apps/sample-app-ios.ipa`

Make sure the correct app file exists before running the tests.

---

## Notes

- The framework uses Appium capabilities defined in the driver factory.
- Android uses `uiautomator2` driver.
- iOS uses `xcuitest` driver.
- If a device is not available, use an emulator or simulator that matches the config values.

---

## Troubleshooting

Common issues:

- Appium server not running: start it with `appium`
- No device detected: verify `adb devices` or simulator state
- Wrong app path: confirm the APK/IPA exists in `apps/`
- Java/Maven missing: verify `java -version` and `mvn -version`
- Driver missing: reinstall Appium drivers with `appium driver install ...`
