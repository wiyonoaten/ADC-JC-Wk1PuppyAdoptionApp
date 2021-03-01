package com.wiyonoaten.composechallenge.wk1puppyadoptionapp.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.wiyonoaten.composechallenge.wk1puppyadoptionapp.models.Puppy
import com.wiyonoaten.composechallenge.wk1puppyadoptionapp.repositories.PuppyRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class ListViewModel(
    coroutineScope: CoroutineScope
) : ViewModel() {

    private val repository = PuppyRepository()

    // region States

    var puppies: List<Puppy> by mutableStateOf(emptyList())
        private set

    var selectedPuppy: Puppy? by mutableStateOf(null)
        private set

    // endregion

    // region Events

    fun onRefresh() {
        puppies = puppies.shuffled()
    }

    fun onPuppySelected(puppy: Puppy) {
        selectedPuppy = puppy
    }

    fun onPuppyUnselected(puppy: Puppy) {
        selectedPuppy = null
    }

    // endregion

    init {
        coroutineScope.launch {
            puppies = repository.loadPuppyList()
        }
    }
}
