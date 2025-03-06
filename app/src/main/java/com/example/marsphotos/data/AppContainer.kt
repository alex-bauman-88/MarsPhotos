package com.example.marsphotos.data

import com.example.marsphotos.network.MarsApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

/**
* A container is an object that contains the dependencies that the app requires.
 * These dependencies are used across the whole application, so they need to be
 * in a common place that all activities can use.
 *
 * You can create a subclass of the Application class and store a reference to the container.
*/

interface AppContainer {
    val marsPhotosRepository: MarsPhotosRepository
}

class DefaultAppContainer: AppContainer {
    private val baseUrl = "https://android-kotlin-fun-mars-server.appspot.com"


    /**
     * Use the Retrofit builder to build a retrofit object using a kotlinx.serialization converter
     */
    private val retrofit = Retrofit.Builder()
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseUrl)
        .build()  // Call build() to create the Retrofit object.

    private val retrofitService: MarsApiService by lazy {
        // ↓ dynamically creates an implementation of the MarsApiService interface at runtime
        retrofit.create(MarsApiService::class.java) 
    }

    override val marsPhotosRepository: MarsPhotosRepository by lazy {
        NetworkMarsPhotosRepository(retrofitService)
    }

}
/* Lazy initialization

"Lazy initialization" is when object creation is purposely delayed, until
you actually need that object, to avoid unnecessary computation or use of
other computing resources.

Kotlin has first-class support for lazy instantiation.
https://kotlinlang.org/docs/reference/delegated-properties.html#lazy
*/