package com.filipzych.pixabay

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import org.junit.Rule
import org.junit.Test


class GalleryComposeTest {

    @get: Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun shouldDisplayTyped() {
        composeTestRule.onNodeWithText("fruit").assertExists()
    }
}