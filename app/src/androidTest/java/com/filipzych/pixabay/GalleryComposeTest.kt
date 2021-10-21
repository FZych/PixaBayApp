package com.filipzych.pixabay

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.ui.test.isDialog
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.filipzych.pixabay.gallery.GalleryFragment
import com.filipzych.pixabay.gallery.GalleryViewModel
import com.filipzych.pixabay.gallery.domain.SearchUseCase
import kotlinx.coroutines.flow.flowOf
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock


@ExperimentalFoundationApi
@ExperimentalMaterialApi
class GalleryComposeTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun should_display_fruits_text_at_start() {
        composeTestRule.onNodeWithText("fruits").assertExists()
    }

    @Before
    fun setUp() {
        val fragment = GalleryFragment()
        val useCase = mock<SearchUseCase> {
            on { build(SearchUseCase.Params("fruits")) } doReturn flowOf(
                mockSearchResponseAndroidTest()
            )
        }
        val viewModel = GalleryViewModel(useCase)

        composeTestRule.setContent {
            fragment.GalleryHandler(viewModel = viewModel)
            fragment.ConfirmationDialog(viewModel = viewModel)
            fragment.DetailsNavigation(viewModel = viewModel)

        }
    }

    @Test
    fun on_photo_click_dialog_is_displayed() {
        composeTestRule.onNodeWithContentDescription("image tags fresh fruits, bowls, fruit bowls")
            .performClick()
        composeTestRule.onNodeWithContentDescription("image tags fresh fruits, bowls, fruit bowls")
            .performClick()

        composeTestRule.onNode(isDialog()).assertExists()
    }
}