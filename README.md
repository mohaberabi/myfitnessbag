# My Fitnessbag

**My Fitnessbag** is a mobile application built using Compose Multiplatform, designed for both iOS
and Android platforms. The app integrates with WooCommerce

## Features

- **Authentication:** Secure user login and registration.
- **Product Listing:** Displays a list of products fetched from WooCommerce.
- **Product Details:** Detailed view of individual products, including variations (size, color,
  etc.).
- **Product Variations:** Handles different product variations like size, color, etc.
- **Cart Management:** Add, update, and remove items from the cart with real-time synchronization.
- **Custom Carousel Slider:** A custom-built carousel slider for showcasing products or promotions.
- **Custom Pull-to-Refresh:** Swipe down to refresh product listings and other content.
- **Custom Pagination:** Efficient loading of product lists and other paginated content.
- **Search:** Users can search for products across the catalog.
- **Native Localization:** Supports multiple languages, with native localization solutions for both
  iOS and Android.
- **Deep Links:** Supports deep linking for navigation across the app.
- **Secure Storage:** Custom secure storage implementation for iOS using `cinterop` and Android.
- **Compose Resources:** Management of assets, images, and strings using Compose, ensuring native
  handling of resources for iOS and Android.
- **App Icons & Splash Screens:** Custom app icons and splash screens for iOS and Android.

## Technologies Used

- **Compose Multiplatform:** Single codebase UI for both Android and iOS.
- **Clean Architecture (MVI):** Structured app architecture using Model-View-Intent.
- **Room:** Local database for storing cart items and user preferences.
- **Lifecycle ViewModel:** Manages UI-related data lifecycle-aware components.
- **Compose Navigation with Type Safety:** Safe navigation through the app using type-safe
  arguments.
- **Koin:** Dependency injection framework for managing app components.
- **Ktor:** HTTP client for communicating with WooCommerce API.
- **Coil:** Image loading library optimized for Compose Multiplatform.
- **Kotlinx Serialization:** For serializing and deserializing data models.
- **Kotlin DateTime:** For managing date and time data across both platforms.
- **Coroutines & Flows:** For managing asynchronous tasks and reactive streams.
- **Datastore:** For managing small amounts of user data like preferences.
- **Custom Secure Storage:** Secure storage implementation for iOS and Android using
  platform-specific tools.
- **Native Logging:** Separate logging implementations for both platforms.
- **Compose Resources:** Manages assets, images, and strings across platforms using Compose.
- **App Icons & Splash Screens:** Custom icons and splash screens tailored for both iOS and Android.

