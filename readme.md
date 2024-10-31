# Movie Search App

This is a movie search application that uses the OMDB API to fetch and display movie data. The project follows a Clean Architecture with MVVM (Model-View-ViewModel) and is built using modern Android development tools.

## Features

- **OMDB API Integration**: Fetch movie information by making calls to the OMDB API.
- **Clean Architecture + MVVM**: Structured code for improved maintainability and scalability.
- **Jetpack Compose**: Builds a smooth and dynamic UI with declarative UI elements.
- **Paging 3**: Handles pagination seamlessly for smooth scrolling through movie results.
- **Retrofit**: Used for network requests to the OMDB API.
- **Room Database**: Caches movie data for offline access and efficient data management.
- **Hilt**: Manages dependency injection, simplifying code and reducing boilerplate.

## Getting Started

### 1. API Key Configuration

To start, create an `API_KEY` in the `local.properties` file of the project root. This will enable the app to connect to the OMDB API.

API_KEY="your_omdb_api_key"

Replace `"your_omdb_api_key"` with the actual API key obtained from [OMDB API](http://www.omdbapi.com/apikey.aspx).

### 2. Project Setup

This app uses several Android libraries and tools, including:
- **Compose** for UI
- **Paging 3** for data pagination
- **Retrofit** for API calls
- **Room** for local data caching
- **Hilt** for dependency injection

Ensure you have the latest Android Studio and SDKs installed for compatibility.

### How to Run

1. Clone this repository.
2. Open the project in Android Studio.
3. Add your OMDB API key in `local.properties` as mentioned above.
4. Build and run the app on an emulator or physical device.

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.
