package com.example.marsphotos

import android.app.Application
import com.example.marsphotos.data.AppContainer
import com.example.marsphotos.data.DefaultAppContainer


// The container in MarsPhotosApplication is implementing what's known
// as a Dependency Container or Dependency Injection Container pattern.
// This pattern is used to manage dependencies between objects in an application.

// The container is responsible for creating and providing the dependencies 
// to the objects that need them.

class MarsPhotosApplication : Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}