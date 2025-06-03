# Shopping-App-Android-Dev
This Android application is a simple shopping cart demo built using Fragments, SharedPreferences, and Notifications, with multi-language support. It allows users to browse products, view details, and add items to their cart.

## Features

- **Modular UI** using three vertically-stacked Fragments:
  - **CartFragment:** Displays a list of cart items and calculates subtotal, shipping and total.
  - **ProductListFragment:** Shows all available products with image, title, and price.
  - **ProductDetailFragment:** Shows product details and allows quantity selection and cart addition.
- **Persistent Cart State** with SharedPreferences, cart is saved between app sessions.
- **Dynamic Notifications** when total price exceeds 200 units.
- **Multi-language Support**: Available in English and one Japanese.

## Technology Stack

- **Language:** Kotlin  
- **Min SDK:** 21  
- **Target SDK:** 33  
- **UI:** Fragments, RecyclerView, ConstraintLayout, Material Design  
- **Persistence:** SharedPreferences  
- **Notifications:** Android NotificationManager  
- **Localization:** Resource-based language switching
