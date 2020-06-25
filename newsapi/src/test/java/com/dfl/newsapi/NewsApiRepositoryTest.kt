package com.dfl.newsapi

import com.dfl.newsapi.builder.NewsQuery
import com.dfl.newsapi.enums.SortBy
import com.dfl.newsapi.model.ArticlesDto
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeAll
import java.util.*

internal class NewsApiRepositoryTest {
    val TAG="News API"
    val repo= NewsApiRepository("---YOUR_NEWS_API_KEY---")

    @Test
    fun getEverything() {
        val single=repo.getEverything(
                q = NewsQuery.NewsQueryBuilder().with("business").build(),
                sortBy = SortBy.POPULARITY,
                pageSize = 10
        ).doOnError { println(TAG+ it.message) }
                .doOnSuccess {  println(TAG+ it.articles.size.toString()) }
        single.subscribe()
        val observable=single.test()
        observable.awaitTerminalEvent()
        observable.assertOf {
                    it.assertSubscribed()
                    it.assertComplete()
                    it.assertNoErrors()
                }
    }

    @Test
    fun getEverythingByTitle() {
        val single=repo.getEverythingByTitle(
                q = NewsQuery.NewsQueryBuilder().with("business").build(),
                sortBy = SortBy.POPULARITY,
                pageSize = 10
        ).doOnError { println(TAG+ it.message) }
                .doOnSuccess {  println(TAG+ it.articles.size.toString()) }
        single.subscribe()
        val observable=single.test()
        observable.awaitTerminalEvent()
        observable.assertOf {
            it.assertSubscribed()
            it.assertComplete()
            it.assertNoErrors()
        }
    }
}