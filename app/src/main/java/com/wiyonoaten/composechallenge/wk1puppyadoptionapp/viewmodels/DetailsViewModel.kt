/*
 * Copyright 2021 Wiyono Aten
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

    var isLoading: Boolean by mutableStateOf(false)
        private set

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
            isLoading = true
            repository.loadPuppyDetails(puppyId).let {
                name = it.name
                breed = it.breed
                birthdate = it.birthdate
                photoUrl = it.photoUrl
                description = it.description
            }
            isLoading = false
        }
    }
}
