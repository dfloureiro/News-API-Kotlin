package com.dfl.newsapi.model

import com.google.gson.annotations.SerializedName

data class ArticlesDto(
        @SerializedName("status") var status: String,
        @SerializedName("code") var code: String,
        @SerializedName("message") var message: String,
        @SerializedName("totalResults") var totalResults: Int,
        @SerializedName("articles") var articles: List<ArticleDto>
)

data class ArticleDto(
        @SerializedName("source") var source: SourceDto,
        @SerializedName("author") var author: String,
        @SerializedName("title") var title: String,
        @SerializedName("description") var description: String,
        @SerializedName("url") var url: String,
        @SerializedName("urlToImage") var urlToImage: String,
        @SerializedName("publishedAt") var publishedAt: String
)