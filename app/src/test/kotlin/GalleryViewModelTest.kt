package com.filipzych.pixabay

import com.filipzych.pixabay.gallery.GalleryViewModel
import com.filipzych.pixabay.gallery.domain.SearchUseCase
import com.filipzych.pixabay.gallery.domain.SearchUseCase.Params
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class GalleryViewModelTest : BaseViewModelTest() {

    private lateinit var viewModel: GalleryViewModel

    @Mock
    private lateinit var searchUseCase: SearchUseCase

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        viewModel = GalleryViewModel(searchUseCase)
    }

    @Test
    fun `should trigger fruit search on initialization`()  {
        verify(searchUseCase).build(Params("fruit"))
    }

    @Test
    fun `should update search text on query entered`(){
        viewModel.fetchImages("people")
        assertThat(viewModel.searchText.value).isEqualTo("people")
    }

    @Test
    fun `should fetch data on query entered`(){
        viewModel.fetchImages("people")
        whenever(searchUseCase.build(Params("people"))).thenReturnFlow(mockSearchResponse())
        verify(searchUseCase).build(Params("people"))
    }

    @Test
    fun `should update selected photo on gallery photo click`(){
        viewModel.selectedPhoto(mockPhotoResponse())
        assertThat(viewModel.selectedPhoto.value).isEqualTo(mockPhotoResponse())
    }

    @Test
    fun `should show confirmation dialog on photo selected`(){
        viewModel.selectedPhoto(mockPhotoResponse())
        assertThat(viewModel.showConfirmationDialog.value).isTrue()
    }
}