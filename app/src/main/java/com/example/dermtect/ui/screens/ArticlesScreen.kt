package com.example.dermtect.ui.screens

import androidx.compose.runtime.Composable
import com.example.dermtect.model.NewsItem
import com.example.dermtect.ui.components.ArticleTemplate

@Composable
fun ArticleDetailScreen(newsItem: NewsItem, onBackClick: () -> Unit) {
    ArticleTemplate(newsItem = newsItem, onBackClick = onBackClick)
}

@Composable
fun HighlightArticle(newsItem: NewsItem, onBackClick: () -> Unit) {
    ArticleTemplate(newsItem = newsItem, onBackClick = onBackClick)
}
