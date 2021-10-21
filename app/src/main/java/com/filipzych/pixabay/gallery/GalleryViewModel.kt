package com.filipzych.pixabay.gallery

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.filipzych.pixabay.foundation.OpenForTesting
import com.filipzych.pixabay.gallery.data.entities.Hit
import com.filipzych.pixabay.gallery.domain.SearchUseCase
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@OpenForTesting
class GalleryViewModel(private val searchUseCase: SearchUseCase) : ViewModel() {

    val images = MutableLiveData<List<Hit>>()
    val searchText = MutableLiveData<String>()
    val selectedPhoto = MutableLiveData<Hit>()
    val showConfirmationDialog = MutableLiveData<Boolean>()
    val navigateToDetails = MutableLiveData<Boolean>()


    init {
        fetchImages("fruit")
    }


    fun fetchImages(query: String) {
        searchText.postValue(query)
        searchUseCase
            .build(SearchUseCase.Params(query = query)).onEach {
                images.value = it.hits
            }
            .launchIn(viewModelScope)
    }

    fun selectedPhoto(photo: Hit) {
        selectedPhoto.postValue(photo)
        showConfirmationDialog.postValue(true)
    }

    fun navigateToDetails() {
        showConfirmationDialog.postValue(true)
    }

}