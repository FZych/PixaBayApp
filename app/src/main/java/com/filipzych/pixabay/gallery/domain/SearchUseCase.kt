package com.filipzych.pixabay.gallery.domain

import com.filipzych.pixabay.BuildConfig
import com.filipzych.pixabay.foundation.BaseUseCase
import com.filipzych.pixabay.foundation.OpenForTesting
import com.filipzych.pixabay.foundation.flowSingle
import com.filipzych.pixabay.gallery.data.SearchApi
import com.filipzych.pixabay.gallery.data.entities.SearchApiResponse
import kotlinx.coroutines.flow.Flow

@OpenForTesting
class SearchUseCase(private val api: SearchApi) :
    BaseUseCase<SearchUseCase.Params, SearchApiResponse>() {

    override fun onBuild(params: Params): Flow<SearchApiResponse> = flowSingle {
        api.searchForImages(query = params.query, key = BuildConfig.KEY)
    }

    data class Params(val query: String)
}

