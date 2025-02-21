package com.example.marsphotos

import com.example.marsphotos.fake.FakeDataSource
import com.example.marsphotos.fake.FakeNetworkMarsPhotosRepository
import com.example.marsphotos.rules.TestDispatcherRule
import com.example.marsphotos.ui.screens.MarsUiState
import com.example.marsphotos.ui.screens.MarsViewModel
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class MarsViewModelTest {

    // To apply this rule to your tests, add the @get:Rule annotation to the testDispatcher property.
    @get:Rule
    val testDispatcher = TestDispatcherRule()

    /** You do not need to directly call MarsViewlModel.getMarsPhotos() to
     * trigger a call to MarsPhotosRepository.getMarsPhotos().
     * MarsViewModel.getMarsPhotos() is called when the ViewModel is initialized.*/
    @Test
    fun marsViewModel_getMarsPhotos_verifyMarsUiStateSuccess() {
        runTest {
            val marsViewModel = MarsViewModel(
                marsPhotosRepository = FakeNetworkMarsPhotosRepository()
            )
            assertEquals(
                MarsUiState.Success(
                    "Success: ${FakeDataSource.photosList.size} Mars " +
                            "photos retrieved"
                ),
                marsViewModel.marsUiState
            )

        }
    }
}