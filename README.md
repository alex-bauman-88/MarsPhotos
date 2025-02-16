Mars Photos
==================================

Mars Photos app is a demo app that shows actual images of Mars' surface. These images are
real-life photos from Mars captured by NASA's Mars rovers. The data is stored on a Web server
as a REST web service.

This app demonstrated the use of [Retrofit](https://square.github.io/retrofit/) to make REST requests to the web service, [kotlinx.serialization](https://github.com/Kotlin/kotlinx.serialization) to
handle the deserialization of the returned JSON to Kotlin data objects, and [Coil](https://coil-kt.github.io/coil/) to load images by URL.

Pre-requisites
--------------

You need to know:
- How to create Composable functions.
- How to use architecture components including ViewModel.
- How to use coroutines for long-running tasks.
- Familiarity with lazy grid

Getting Started
---------------

1. [Install Android Studio](https://developer.android.com/studio/install.html), if you don't already
   have it.
2. Download the sample.
3. Import the sample into Android Studio.
4. Build and run the sample.

## [Build this app through hands-on codelabs in the Android Basics with Compose Course](https://developer.android.com/courses/android-basics-compose/course)

### [Get data from the internet](https://developer.android.com/codelabs/basic-android-kotlin-compose-getting-data-internet)
Learn how to use community-developed libraries to connect to a web service to retrieve and display data in your Android Kotlin compose app. 

### [Add repository and Manual DI](https://developer.android.com/codelabs/basic-android-kotlin-compose-add-repository)
Learn how to improve the architecture of the app by separating the network calls into a repository.

### [Load and display images from the internet](https://developer.android.com/codelabs/basic-android-kotlin-compose-load-images)
Use the Coil library to load and display photos from the internet in your Android Compose app. 

---------------
[The lab at developer.android.com, steps 1-6](https://developer.android.com/codelabs/basic-android-kotlin-compose-getting-data-internet?continue=https%3A%2F%2Fdeveloper.android.com%2Fcourses%2Fpathways%2Fandroid-basics-compose-unit-5-pathway-1%23codelab-https%3A%2F%2Fdeveloper.android.com%2Fcodelabs%2Fbasic-android-kotlin-compose-getting-data-internet#5)


[Steps 7-.. lab: ](https://developer.android.com/codelabs/basic-android-kotlin-compose-add-repository?continue=https%3A%2F%2Fdeveloper.android.com%2Fcourses%2Fpathways%2Fandroid-basics-compose-unit-5-pathway-2%23codelab-https%3A%2F%2Fdeveloper.android.com%2Fcodelabs%2Fbasic-android-kotlin-compose-add-repository#0)

Steps:

1. Retrofit dependencies in gradle.kts
2. Retrofit setup: `com/example/marsphotos/network/MarsApiService.kt`
3. Call the web service in MarsViewModel: `com.example.marsphotos.ui.screens.MarsViewModel.getMarsPhotos` (but the recommended approach is to call webservice from a repository)
4. Add Internet permission to `app/src/main/AndroidManifest.xml `
5. Add Exception (no internet connection) Handling.
6. Parse the JSON response with kotlinx.serialization:
6.1. Add kotlinx.serialization library dependencies.
6.2. Implement the Mars Photo data class `app/src/main/java/com/example/marsphotos/network/MarsPhoto.kt`
7. Attach application container to the app
8. Add repository and Factory to ViewModel