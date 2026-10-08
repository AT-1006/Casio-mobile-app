# Casio Mobile — Android E-Commerce & Product Catalog App

[![Android](https://img.shields.io/badge/Platform-Android-3DDC84?style=flat-square&logo=android&logoColor=white)](https://developer.android.com)
[![Java](https://img.shields.io/badge/Language-Java-ED8B00?style=flat-square&logo=openjdk&logoColor=white)](https://oracle.com/java)
[![Gradle](https://img.shields.io/badge/Build-Gradle-02303A?style=flat-square&logo=gradle&logoColor=white)](https://gradle.org)
[![Material Design](https://img.shields.io/badge/UI-Material%20Design-757575?style=flat-square&logo=material-design&logoColor=white)](https://material.io)
[![License: MIT](https://img.shields.io/badge/License-MIT-green.svg?style=flat-square)](LICENSE)

A native Android mobile application showcasing an immersive e-commerce catalog for Casio products. Features comprehensive category exploration across iconic watch series (G-Shock, Vintage), scientific calculators, and musical instruments, complete with user authentication and checkout simulation.

---

## ⚡ Key Highlights

- 🛍️ **Multi-Category Product Catalog**: Dedicated browsing interfaces for iconic Casio lines:
  - **G-Shock**: Rugged sports and tactical timepieces (`gshockPage.java`).
  - **Vintage**: Retro digital collection (`vintagePage.java`).
  - **Standard Watches**: Analog & digital everyday watches (`watchesPage.java`).
  - **Calculators**: Scientific, graphing, and desktop office devices (`calculatorPage.java`).
  - **Musical Instruments**: Electronic keyboards and synthesizers (`musicPage.java`).
- 📱 **Modern Mobile UI/UX**:
  - Custom floating navigation bar (`floating_nav_bar.xml`) for swift screen transitions.
  - Interactive banner carousels and promotional cards (`item_banner.xml`).
  - High-performance RecyclerView adapters (`HomeAdapter.java`, `WatchesAdapter.java`) for smooth list scrolling.
- 🔐 **Authentication System**: User sign-up and login interface with input validation (`loginpage.java`, `signup.java`).
- 💳 **Product Detail & Payment Flow**: In-depth product view (`productDetail.java`) with specifications and integrated purchase/payment simulation (`PaymentActivity.java`, `purchasePage.java`).

---

## 🏗️ Architecture & Project Structure

```
app/src/main/
├── java/com/example/casio/
│   ├── MainActivity.java        # Application launch and splash gateway
│   ├── CasioHome.java           # Primary storefront dashboard & category selector
│   ├── HomeAdapter.java         # RecyclerView adapter for featured banners & categories
│   ├── WatchesAdapter.java      # Dynamic product card grid adapter
│   ├── Product.java             # Immutable product entity model
│   ├── productDetail.java       # Product specifications and purchase intent
│   ├── gshockPage.java          # G-Shock product collection
│   ├── vintagePage.java         # Vintage collection showcase
│   ├── watchesPage.java         # Standard watch catalog
│   ├── calculatorPage.java      # Calculator product catalog
│   ├── musicPage.java           # Electronic instruments & keyboards
│   ├── loginpage.java           # User authentication login
│   ├── signup.java              # User registration
│   ├── purchasePage.java        # Order summary & confirmation
│   └── PaymentActivity.java     # Secure payment processing simulation
│
└── res/
    ├── layout/                  # Responsive XML screen layouts & item views
    ├── drawable/                # Product assets, brand iconography, and custom selectors
    └── values/                  # Colors, typography, and styling themes
```

---

## 🚀 Getting Started

### Prerequisites

- **Android Studio**: Ladybug / Jellyfish or newer
- **JDK**: Version 17 or higher
- **Android SDK**: API Level 26+ (target SDK 34)

### Installation & Build

1. **Clone the repository**:
   ```bash
   git clone https://github.com/AT-1006/Casio-mobile-app.git
   cd Casio-mobile-app
   ```

2. **Open in Android Studio**:
   - Select **Open Project** and navigate to the cloned directory.
   - Allow Gradle to sync dependencies.

3. **Run on Device or Emulator**:
   - Connect an Android device (with USB debugging enabled) or start an AVD emulator.
   - Click **Run 'app'** (`Shift + F10`) or execute via Gradle wrapper:
     ```bash
     ./gradlew assembleDebug
     ```

---

## 📄 License

MIT License © 2026 Atharv Gawand
