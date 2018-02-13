package dfl.com.newsapikotlin

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import dfl.com.newsapikotin.NewsApi
import dfl.com.newsapikotin.enums.Category
import dfl.com.newsapikotin.enums.Country
import dfl.com.newsapikotin.enums.Language
import dfl.com.newsapikotin.enums.SortBy
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val newsApi = NewsApi("")

        newsApi.getSources(category = Category.GENERAL, language = Language.EN, country = Country.US)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMapIterable { sources -> sources.sources }
                .subscribe({ source -> Log.d("source", source.name) },
                        { t -> Log.d("getSources error", t.message) })

        newsApi.getEverything(q = "bitcoin", sources = "bbc-news", domains = null, from =  "2018-01-01", to = "2018-02-13", language = Language.EN, sortBy = SortBy.POPULARITY, pageSize = 20, page = 1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMapIterable { articles -> articles.articles }
                .subscribe({ article -> Log.d("getEverything article", article.title) },
                        { t -> Log.d("getEverything error", t.message) })

        newsApi.getTopHeadlines(category = Category.GENERAL, country = Country.US, q = "trump", pageSize = 20, page = 1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMapIterable { articles -> articles.articles }
                .subscribe({ article -> Log.d("getTopHead CC article", article.title) },
                        { t -> Log.d("getTopHeadlines error", t.message) })

        newsApi.getTopHeadlines(sources = "bbc-news", q = "trump", pageSize = 20, page = 1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMapIterable { articles -> articles.articles }
                .subscribe({ article -> Log.d("getTopHead S article", article.title) },
                        { t -> Log.d("getTopHeadlines error", t.message) })
    }
}
