package com.example.marsphotos.fake

import com.example.marsphotos.model.MarsPhoto

object FakeDataSource {

    const val idOne = "img1"
    const val idTwo = "img2"
    const val imgOne = "https://upload.wikimedia.org/wikipedia/commons/thumb/9/97/The_Earth_seen_from_Apollo_17.jpg/480px-The_Earth_seen_from_Apollo_17.jpg"
    const val imgTwo = "https://upload.wikimedia.org/wikipedia/commons/thumb/d/da/Ruler_image.jpg/480px-Ruler_image.jpg"

    val photosList = listOf(
        MarsPhoto(id = idOne, imgSrc = imgOne),
        MarsPhoto(id = idTwo, imgSrc = imgTwo)
    )
}
