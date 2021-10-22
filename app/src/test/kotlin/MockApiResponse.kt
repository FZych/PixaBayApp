package com.filipzych.pixabay

import com.filipzych.pixabay.gallery.data.entities.Hit
import com.filipzych.pixabay.gallery.data.entities.SearchApiResponse

fun mockSearchResponse() =
    SearchApiResponse(listOf(mockPhotoResponse()), 1, 1)

fun mockPhotoResponse() =
    Hit(
        collections = 2071,
        comments = 188,
        downloads = 175225,
        id = 2305192,
        imageHeight = 2248,
        imageSize = 2944985,
        imageWidth = 4000,
        largeImageURL = "https://pixabay.com/get/g33cf76343e8eeb5cc77e532fc7315a8f444c22bc983507e020242fbb7133e2cf6f0960bed11da0116c8c81558c3b60209a996fe3d716984e15c33b99c0cf5f5d_1280.jpg",
        likes = 1012,
        pageURL = "https://pixabay.com/photos/fresh-fruits-bowls-fruit-bowls-2305192/",
        previewHeight = 84,
        previewURL = "https://cdn.pixabay.com/photo/2017/05/11/19/44/fresh-fruits-2305192_150.jpg",
        previewWidth = 150,
        tags = "fresh fruits, bowls, fruit bowls",
        type = "photo",
        user = "silviarita",
        userImageURL = "https://cdn.pixabay.com/user/2021/05/07/16-52-19-814_250x250.jpg",
        user_id = 3142410,
        views = 330623,
        webformatHeight = 359,
        webformatURL = "https://pixabay.com/get/g78d01344e263569df1085023e087edec32441c0181dd32661561138f1a0893456bfb9e64f896f6d7989bb19fd9260cea5180f758f8d246e4421d9218dc1e8a9f_640.jpg",
        webformatWidth = 640
    )