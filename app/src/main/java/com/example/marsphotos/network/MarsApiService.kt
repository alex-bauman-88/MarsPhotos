package com.example.marsphotos.network

import com.example.marsphotos.model.MarsPhoto
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.GET



// interface that defines how Retrofit talks to the web server using HTTP requests
interface MarsApiService {
    @GET("photos") // GET request with /photos endpoint
    suspend fun getPhotos(): List<MarsPhoto>
    // When the getPhotos() method is invoked, Retrofit appends the endpoint
    // photos to the base URL used to start the request.
}