package com.dfl.newsapi.model

import com.google.gson.annotations.SerializedName

data class SourcesDto(@SerializedName("status") var status: String,
                      @SerializedName("code") var code: String,
                      @SerializedName("message")
                      var message: String,
                      @SerializedName("sources")
                      var sources: List<SourceDto>)

data class SourceDto(
        @SerializedName("id") var id: String,
        @SerializedName("name") var name: String,
        @SerializedName("description") var description: String,
        @SerializedName("url") var url: String,
        @SerializedName("category") var category: String,
        @SerializedName("language") var language: String,
        @SerializedName("country") var country: String
)