package com.filipzych.pixabay

import com.filipzych.pixabay.gallery.GalleryViewModel
import com.filipzych.pixabay.gallery.domain.SearchUseCase
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.verify

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
    fun onInitSearchImagesFunctionIsCalled()  {
        verify(searchUseCase).build(SearchUseCase.Params("fruit"))
    }



}