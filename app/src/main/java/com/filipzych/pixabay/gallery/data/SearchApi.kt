package com.filipzych.pixabay.gallery.data

import com.filipzych.pixabay.gallery.data.entities.SearchApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchApi {
    @GET("api")
    suspend fun searchForImages(@Query("key") key: String, @Query("q") query: String) : SearchApiResponse
}