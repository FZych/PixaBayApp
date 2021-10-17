package com.filipzych.pixabay.di

import com.filipzych.pixabay.gallery.GalleryViewModel
import com.filipzych.pixabay.gallery.data.SearchApi
import com.filipzych.pixabay.gallery.domain.SearchUseCase
import com.filipzych.pixabay.network.*
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val appModule = module {
    single<Gson> { GsonBuilder().create() }
    single<OkHttpClient> { OkHttpClient.Builder().build() }
    single<Retrofit> { RetrofitProviderImpl(okHttpClient = get(), gson = get()).provideRetrofit() }

    single<SearchApi> { get<Retrofit>().create(SearchApi::class.java) as SearchApi }
    single<SearchUseCase> { SearchUseCase(api = get()) as SearchUseCase }

    viewModel { GalleryViewModel(get()) }
}