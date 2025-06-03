# Shopping App
This Android application is a simple shopping cart demo built using Fragments, SharedPreferences, and Notifications, with multi-language support. It allows users to browse products, view details, and add items to their cart.

## Screenshots

<img src="https://github.com/user-attachments/assets/c085a3cc-ca35-4d3e-ba8d-5678b72a988e" width="200"/>
<img src="https://github.com/user-attachments/assets/55f5e2a7-b97e-4e9f-881f-fb0a0597d99b" width="200"/>
<img src="https://github.com/user-attachments/assets/b582f26d-9518-495d-bcd9-c9f176371ad0" width="200"/>
<img src="https://github.com/user-attachments/assets/a16c6657-6671-46c3-90b6-d6c696026e70" width="200"/>
<img src="https://github.com/user-attachments/assets/ed7999b1-ba57-450e-974a-26fd0ac24db8" width="200"/>
<img src="https://github.com/user-attachments/assets/90ec0b7b-0024-4a70-ad74-1e1e2808f390" width="200"/>
<img src="https://github.com/user-attachments/assets/56c79a06-2548-4376-bdd7-6a37e909f042" width="200"/>
<img src="https://github.com/user-attachments/assets/a1a6beca-91f7-478a-a10c-a66b1a6d765c" width="200"/>

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
