package com.androiddevs.mvvmnewsapp.models

import com.androiddevs.mvvmnewsapp.models.Article

data class NewsResponse(
    var articles: MutableList<Article>,
    val status: String,
    val totalResults: Int
)