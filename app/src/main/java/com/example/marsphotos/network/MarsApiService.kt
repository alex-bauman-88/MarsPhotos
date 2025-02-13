package com.example.marsphotos.network

import com.example.marsphotos.network.MarsPhoto
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.GET

private const val BASE_URL = "https://android-kotlin-fun-mars-server.appspot.com"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
    .baseUrl(BASE_URL)
    .build()  // Call build() to create the Retrofit object.

// interface that defines how Retrofit talks to the web server using HTTP requests
interface MarsApiService {
    @GET("photos") // GET request with /photos endpoint
    suspend fun getPhotos(): List<MarsPhoto>
    // When the getPhotos() method is invoked, Retrofit appends the endpoint
    // photos to the base URL used to start the request.
}

/*Object declaration

In Kotlin, object declarations are used to declare singleton objects.
Singleton pattern ensures that one, and only one, instance of an object is
created and has one global point of access to that object.
Object initialization is thread-safe and done at first access.

Following is an example of an object declaration and its access.
Object declaration always has a name following the object keyword.

Warning: Singleton pattern is not a recommended practice.
Singletons represent global states that are hard to predict, particularly
in tests. Objects should define which dependencies they need, instead of
describing how to create them.

Use Dependency injection over singleton pattern, you will learn how to
implement Dependency injection in a later codelab.
*/

/* Lazy initialization

"Lazy initialization" is when object creation is purposely delayed, until
you actually need that object, to avoid unnecessary computation or use of
other computing resources.

Kotlin has first-class support for lazy instantiation.
https://kotlinlang.org/docs/reference/delegated-properties.html#lazy
*/

object MarsApi {
    val retrofitService : MarsApiService by lazy {
        retrofit.create(MarsApiService::class.java)
    }
}
/* The Retrofit setup is done!

Each time your app calls MarsApi.retrofitService, the caller accesses
the same singleton Retrofit object that implements MarsApiService,
which is created on the first access.
*/

