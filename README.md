# Books App 📚

An Android application developed in Kotlin to manage a personal library. The app allows users to list books, view details, mark favorites, and remove items from their collection.

## 🚀 Features

- **Home Screen**: Lists all available books in a modern `RecyclerView`.
- **Book Details**: View specific information about a book, including title, author, and genre.
- **Favorites**: Mark books as favorites to highlight them.
- **Dynamic UI**: Genres are color-coded with custom badges (e.g., Red for Terror, Custom Purple for Fantasy, Teal for others).
- **Management**: Ability to remove books from the library with a confirmation dialog.

## 🛠️ Technical Stack

- **Language**: [Kotlin](https://kotlinlang.org/)
- **Architecture**: MVVM (Model-ViewModel-Intent)
- **UI Components**:
    - `ViewBinding` for safe view access.
    - `RecyclerView` with `ListAdapter` and `DiffUtil` for efficient list updates.
    - `Material Design` components for a modern look and feel.
    - `ConstraintLayout` for responsive UI.
- **Navigation**: Fragment-based navigation using `SupportFragmentManager`.
- **Data Management**: 
    - Repository Pattern with a Singleton implementation.
    - `LiveData` for reactive UI updates.
    - `Room Database` (Infrastructure ready for persistence).

## 📁 Project Structure

```text
com.example.books
├── adapters      # RecyclerView adapters (BookAdapter)
├── entity        # Data models (BookEntity)
├── fragments     # UI Fragments (Home, Details, Favorites)
├── listeners     # Interface definitions for click events
├── repository    # Data source management (BookRepository, BookDatabase)
├── viewholders   # RecyclerView ViewHolders
└── viewmodels    # Architecture ViewModels
```

## 🔧 Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/your-username/books-app.git
   ```
2. Open the project in **Android Studio**.
3. Sync Gradle and run the app on an emulator or physical device.

## 📝 License

This project is for educational purposes. Feel free to use and modify it as you wish.
