/*
 * Copyright (C) 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.marsphotos.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.marsphotos.MarsPhotosApplication
import com.example.marsphotos.data.MarsPhotosRepository
import kotlinx.coroutines.launch
import java.io.IOException

/*
A sealed interface makes it easy to manage state by limiting the possible values.

A sealed interface (or sealed class, which is very similar) restricts the possible subtypes
(classes or objects that implement the interface) to only those defined within the same file.
It's like an enum on steroids, but with much more flexibility.

Main Function:  The primary purpose of a sealed interface is to represent a limited set of states
in a type-safe way. Because you know all possible subtypes at compile time, you can use when
expressions to handle each state exhaustively.

The compiler will even warn you if you forget to handle one of the states in your when expression,
ensuring you've considered all possibilities.

Here, MarsUiState can only be one of three things: Success, Error, or Loading.
No other types can implement MarsUiState from outside this file.
*/

/* Data Class, Object, and Data Object

Data Class:

A data class is designed to hold data.  The compiler automatically generates useful methods
like equals(), hashCode(), and toString(), making them convenient for representing data structures.
They are ideal when you primarily care about the data itself.

Object:

An object declaration defines a singleton.  There is only one instance of that object ever created.
Objects are useful for things like utility classes, managers, or representing states where you
don't need to hold specific data.

Data Object:

A data object is a combination of a data class and an object.
It's a singleton (like an object) but also has the compiler-generated methods of a data class
(equals, hashCode, toString etc.). They are useful when you need a single instance of a class
and also need the data class functionality.

*/

sealed interface MarsUiState {
    data class Success(val photos: String) : MarsUiState
    data object Error : MarsUiState
    data object Loading : MarsUiState
}

class MarsViewModel(
    private val marsPhotosRepository: MarsPhotosRepository
) : ViewModel() {
    /** The mutable State that stores the status of the most recent request */
    var marsUiState: MarsUiState by mutableStateOf(MarsUiState.Loading) // Loading is default value
        private set

    /**
     * Call getMarsPhotos() on init so we can display status immediately.
     */
    init {
        getMarsPhotos()
    }

    /**
     * Gets Mars photos information from the Mars API Retrofit service and updates the
     * [MarsPhoto] [List] [MutableList].      */
    fun getMarsPhotos() {
        viewModelScope.launch {
            try {
                val listResult = marsPhotosRepository.getMarsPhotos()
                marsUiState =
                    MarsUiState.Success("Success: ${listResult.size} Mars photos retrieved")
            } catch (e: IOException) {
                marsUiState = MarsUiState.Error
//                MarsUiState.Error // You can lift the 'marsUiState =' assignment
            }
        }
    }

    companion object {
        // 1. Declaring a property named Factory that implements ViewModelProvider.Factory interface
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            // 2. Using the viewModelFactory DSL to create a factory
            initializer {
                // 3. Getting the Application instance using a special key.
                // `this` refers to the current context
                // `APPLICATION_KEY` is a special key provided by Android to access the Application instance
                // `as MarsPhotosApplication` casts the Application instance to a MarsPhotosApplication instance
                val application = (this[APPLICATION_KEY] as MarsPhotosApplication)

                // 4. Getting the repository from the container (the container was created when the app started)
                // The repository is created lazily (only when first accessed)
                val marsPhotosRepository = application.container.marsPhotosRepository

                // 5. Creating and returning a new ViewModel instance
                // Passes the repository it needs as a dependency
                MarsViewModel(marsPhotosRepository = marsPhotosRepository)

                // When used in the UI:
                // val marsViewModel: MarsViewModel = viewModel(factory = MarsViewModel.Factory)
            }
        }
    }
}

/* This factory pattern is crucial because:
- ViewModels can't have constructor parameters without a factory
- It provides a clean way to inject dependencies
- It integrates with Android's ViewModel lifecycle management
*/

/*Following dependency injection's best practices, ViewModels can take dependencies as parameters
in their constructor. These are mostly of types from the domain or data layers. Because the framework 
provides the ViewModels, a special mechanism is required to create instances of them. 

That mechanism is the ViewModelProvider.Factory interface. Only implementations of this interface 
can instantiate ViewModels in the right scope.

Source: https://developer.android.com/topic/libraries/architecture/viewmodel/viewmodel-factories
 */
