package com.wiyonoaten.composechallenge.wk1puppyadoptionapp.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.wiyonoaten.composechallenge.wk1puppyadoptionapp.repositories.PuppyRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.threeten.bp.OffsetDateTime

class DetailsViewModel(
    coroutineScope: CoroutineScope,
    private val puppyId: String
) : ViewModel() {

    private val repository = PuppyRepository()

    // region States

    var name: String by mutableStateOf("Name")
        private set

    var breed: String by mutableStateOf("Breed")
        private set

    var birthdate: OffsetDateTime by mutableStateOf(OffsetDateTime.MIN)
        private set

    var photoUrl: String? by mutableStateOf(null)
        private set

    var description: String? by mutableStateOf(null)
        private set

    // endregion

    // region Events

    // endregion

    init {
        coroutineScope.launch {
            repository.loadPuppyDetails(puppyId).let {
                name = it.name
                breed = it.breed
                birthdate = it.birthdate
                photoUrl = it.photoUrl
                description = it.description
            }
        }
    }
}
