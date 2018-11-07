package com.dfl.newsapi.service

import com.dfl.newsapi.model.ArticlesDto
import com.dfl.newsapi.model.SourcesDto
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

internal interface NewsApiService {

    @GET("v2/sources")
    fun getSources(@Query("category") category: String?, @Query("language") language: String?, @Query("country") country: String?): Single<SourcesDto>

    @GET("/v2/everything")
    fun getEverything(@Query("q") q: String?, @Query("sources") sources: String?, @Query("domains") domains: String?, @Query("from") from: String?,
                      @Query("to") to: String?, @Query("language") language: String?, @Query("sortBy") sortBy: String?,
                      @Query("pageSize") pageSize: Int?, @Query("page") page: Int?): Single<ArticlesDto>

    @GET("/v2/top-headlines")
    fun getTopHeadlines(@Query("category") category: String?, @Query("country") country: String?,
                        @Query("sources") sources: String?, @Query("q") q: String?, @Query("pagesize") pageSize: Int?,
                        @Query("page") page: Int?): Single<ArticlesDto>
}