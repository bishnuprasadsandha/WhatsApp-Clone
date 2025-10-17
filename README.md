# 💬 WhatsApp Clone App (Android)

![Kotlin](https://img.shields.io/badge/Kotlin-7F52FF?style=for-the-badge&logo=kotlin&logoColor=white)
![Firebase](https://img.shields.io/badge/Firebase-FFCA28?style=for-the-badge&logo=firebase&logoColor=black)
![Android Studio](https://img.shields.io/badge/Android%20Studio-3DDC84?style=for-the-badge&logo=android-studio&logoColor=white)
![Jetpack Compose](https://img.shields.io/badge/Jetpack%20Compose-4285F4?style=for-the-badge&logo=jetpackcompose&logoColor=white)
![MVVM](https://img.shields.io/badge/Architecture-MVVM-blue?style=for-the-badge)

---

A fully functional **WhatsApp Clone App** built using **Kotlin**, **Firebase**, and **Android Studio**.  
This project is created for learning and demonstration purposes, showcasing real-time chat features, authentication, and modern UI with **Jetpack Compose / XML**.

---

## 🚀 Features

- 🔐 User Authentication (Firebase)
- 💬 Real-time Chat System
- 👤 Profile Management
- 📸 Image Upload using Firebase Storage
- 🕒 Message Time & Status (Sent/Delivered)
- 🔔 Notifications for New Messages
- 🌗 Dark/Light Mode Support *(optional)*
- 📱 Modern UI Design inspired by WhatsApp

---

## 🧠 Tech Stack

| Component | Technology Used |
|------------|----------------|
| **Language** | Kotlin |
| **Architecture** | MVVM |
| **UI** | Jetpack Compose |
| **Backend** | Firebase Authentication & Realtime Database |
| **Storage** | Firebase Storage |
| **IDE** | Android Studio |

---

## 📂 Project Structure

app/

┣ 📁 ui/ **All UI screens and components**

┣ 📁 data/ **Data models, repositories, Firebase integration**

┣ 📁 viewmodel/ **ViewModels for MVVM architecture**

┣ 📁 utils/ **Helper classes and constants**

┗ AndroidManifest.xml **App manifest file**



---

## 📅 Project Development Log

### **Day 1 — Project Setup & Initial Work**
✅ Created project structure and initialized Git repository  
🧭 Set up navigation and core dependencies  
🎨 Built initial layout components and design foundation  

---

### **Day 2 — Login & Home Screen UI**
🔐 Designed and implemented **Login Screen UI**  
🏠 Completed **Home Screen UI** layout  
🔗 Integrated navigation between Login → Home  

---

### **Day 3 — Update & Status UI**
📰 Created **Update Screen UI**  
💬 Developed **Status Screen UI**  
✨ Improved overall visual consistency and spacing  

---

### **Day 4 — Community & Call Screen UI**
👥 Completed **Community Screen UI**  
📞 Completed **Call Screen UI**  
🎯 Finalized all remaining UI components  
✅ UI Design Phase **Completed Successfully!**  

---

### **Day 5 — Implementing Navigation & Splash Screen Backend Logic**
🧭 Integrated **Navigation Component and set up app flow.**  
⚙️ Implemented **Splash Screen backend logic to check Firebase authentication state.**  
🔐 Redirected users automatically based on login status **(Login → Main Screen).**   
✅ **Navigation & Splash Screen Logic Completed Successfully!**  

---

### **Day 6 — Authentication Connection & OTP UI**
📱 **Connected Phone Input Screen to Authentication Flow**    
🔐 **Designed and Implemented OTP Verification UI**   
✅ **Authentication Module in progress**  

---

### **Day 7 — Base Features Implementation**
💬 Implemented **Chat Feature** (User-to-User Messaging)   
📜 Created **Chat List Screen** to display recent conversations   
⚙️ Integrated basic message sending & receiving logic       
✅ Progressing towards full chat functionality    

---


### **Day 8 — Home Screen Backend, User Search & Bottom Navigation**
🏠 **Implemented Home Screen Backend Logic** with real-time chat updates from Firestore   
🔍 **Added User Search Feature** – search users by name or phone number with debounce for smoother performance   
⬇️ **Integrated Bottom Navigation** using Navigation Component to switch between Chats, Status, Calls & Profile       
🧪 Added unit tests for **SearchUsersViewModel** (debounce + query validation)    
✅ Major step towards completing the core chat module! 🚀


---

## ❤️ Stay Connected
More updates coming soon!  
If you like this project, don’t forget to ⭐ **star** the repo on GitHub!
