package com.dfl.newsapi

import com.dfl.newsapi.di.Injector
import com.dfl.newsapi.model.ArticlesDto
import com.dfl.newsapi.model.SourcesDto
import com.dfl.newsapi.service.NewsApiService
import com.dfl.newsapi.enums.Category
import com.dfl.newsapi.enums.Country
import com.dfl.newsapi.enums.Language
import com.dfl.newsapi.enums.SortBy
import io.reactivex.Single

class NewsApiRepository(apiKey: String) {

    private val injector = Injector(apiKey)
    private val newsApiService: NewsApiService by injector.instance()

    fun getSources(category: Category? = null, language: Language? = null, country: Country? = null): Single<SourcesDto> {
        return newsApiService.getSources(category?.value, language?.value, country?.value)
    }

    fun getEverything(q: String? = null, sources: String? = null, domains: String? = null, from: String? = null,
                      to: String? = null, language: Language? = null, sortBy: SortBy? = null,
                      pageSize: Int = 20, page: Int = 1): Single<ArticlesDto> {
        return newsApiService.getEverything(q, sources, domains, from, to, language?.value, sortBy?.value, pageSize, page)
    }

    fun getTopHeadlines(category: Category? = null, country: Country? = null, q: String? = null, pageSize: Int = 20,
                        page: Int = 1): Single<ArticlesDto> {
        return newsApiService.getTopHeadlines(category?.value, country?.value, null, q, pageSize, page)
    }

    fun getTopHeadlines(sources: String? = null, q: String? = null, pageSize: Int = 20,
                        page: Int = 1): Single<ArticlesDto> {
        return newsApiService.getTopHeadlines(null, null, sources, q, pageSize, page)
    }
}