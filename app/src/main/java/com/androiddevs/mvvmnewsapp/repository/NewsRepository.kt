package com.androiddevs.mvvmnewsapp.repository

import com.androiddevs.mvvmnewsapp.api.RetrofitInstance
import com.androiddevs.mvvmnewsapp.db.ArticleDatabase
import com.androiddevs.mvvmnewsapp.models.Article
import com.androiddevs.mvvmnewsapp.models.NewsResponse
import retrofit2.Response
import java.util.Collections.copy

class NewsRepository(
    val db: ArticleDatabase
) {
//    suspend fun getBreakingNews(countryCode: String, pageNumber: Int) =
//        RetrofitInstance().api.getBreakingNews(countryCode,pageNumber)

    suspend fun getBreakingNews(countryCode: String, pageNumber: Int): Response<NewsResponse> {
        var newsResponse = RetrofitInstance().api.getBreakingNews(countryCode,pageNumber)

//         Filter News Source
        var articles = newsResponse.body()!!.articles
        var filteredArticles = articles.filter {
            it.source?.id != "google-news"
        }

        newsResponse.body()!!.articles = filteredArticles as MutableList<Article>

        return newsResponse
    }
    suspend fun searchNews(searchQuery: String, pageNumber: Int) =
        RetrofitInstance().api.searchForNews(searchQuery,pageNumber)


    suspend fun upsert(article:Article) = db.getArticleDao().upsert(article)

    fun getSavedNews() = db.getArticleDao().getAllArticles()

    suspend fun deleteArticle(article:Article) = db.getArticleDao().deleteArticle(article)
}