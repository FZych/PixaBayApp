package com.filipzych.pixabay.gallery

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.filipzych.pixabay.gallery.data.entities.Hit
import com.filipzych.pixabay.gallery.domain.SearchUseCase
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class GalleryViewModel(private val searchUseCase: SearchUseCase) : ViewModel() {

    val images = MutableLiveData<List<Hit>>()
    val searchText = MutableLiveData<String>()


    init {
        fetchImages("fruit")
    }


    fun fetchImages(query: String) {
        searchText.value = query
        searchUseCase
            .build(SearchUseCase.Params(query = query)).onEach {
                images.value = it.hits
            }
            .launchIn(viewModelScope)
    }

}