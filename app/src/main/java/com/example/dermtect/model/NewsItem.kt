package com.example.dermtect.model

data class NewsItem(
    val imageResId: Int? = null,           // for hardcoded drawables via ID
    val imageUrl: String? = null,       // for remote image URLs (optional future-proof)
    val title: String = "",
    val description: String = "",
    val source: String = "",
    val date: String = "",
    val body: String = ""
)
