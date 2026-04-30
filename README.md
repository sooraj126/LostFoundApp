# Lost and Found App – Android App

## Overview

Lost and Found App is an Android application where users can create, view, filter and remove lost or found item adverts. The app allows users to upload images and store item details locally using SQLite database.

The app is designed to help users manage lost and found items in a simple and easy way.

---

## Features
- Create lost or found advert
- Upload image from gallery
- Select category (Electronics, Pets, Wallets, etc.)
- Pick date using DatePicker
- Save data in SQLite database
- View all items using RecyclerView
- Filter items by category
- View full item details
- Delete/remove advert

---

## Technologies Used
- Java
- Android Studio
- XML Layouts
- SQLite Database
- RecyclerView
- Intents

---

## App Flow
1. User opens the app
2. User can choose:
   - Create Advert
   - Show All Items
3. Create Advert Flow:
   - Enter item details
   - Select date
   - Upload image
   - Click Save
   - Data is stored in SQLite
4. View Items Flow:
   - RecyclerView displays all items
   - User can filter items using category spinner
   - User clicks an item
5. Item Detail Flow:
   - Full details are shown
   - Image is displayed
   - User can remove advert
  
---
   
## Database Structure

SQLite is used for local storage

### Table: Items
- id (Primary Key)
- type (Lost / Found)
- name
- phone
- description
- date
- location
- category
- image URI
- timestamp
---
## Key Concepts Used
- SQLite database for local data storage
- Cursor to read database rows
- RecyclerView for displaying list
- Adapter pattern to bind data to UI
- Intent for navigation between screens
- Spinner for filtering data
- ActivityResultLauncher for image selection

---
  
## Project Structure (Important Files)
- MainActivity.java → Home screen navigation
- CreateAdvertActivity.java → Create advert form and save data
- AllItemsActivity.java → Display and filter items
- ItemDetailActivity.java → Show full details and delete item
- DatabaseHelper.java → SQLite database operations
- ItemModel.java → Data model class
- ItemAdapter.java → RecyclerView adapter
- item_row.xml → Layout for each item
---
## Setup
1. Open project in Android Studio
2. Sync Gradle
3. Run app on emulator or device
