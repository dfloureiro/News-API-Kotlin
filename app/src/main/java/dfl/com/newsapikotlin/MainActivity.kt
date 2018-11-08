package dfl.com.newsapikotlin

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.dfl.newsapi.NewsApiRepository
import com.dfl.newsapi.enums.Category
import com.dfl.newsapi.enums.Country
import com.dfl.newsapi.enums.Language
import com.dfl.newsapi.enums.SortBy
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

internal class MainActivity : AppCompatActivity() {

    private val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val newsApiRepository = NewsApiRepository("")

        compositeDisposable.add(newsApiRepository.getSources(category = Category.GENERAL, language = Language.EN, country = Country.US)
                .subscribeOn(Schedulers.io())
                .toFlowable()
                .flatMapIterable { sources -> sources.sources }
                .subscribe({ source -> Log.d("source", source.name) },
                        { t -> Log.d("getSources error", t.message) }))

        compositeDisposable.add(newsApiRepository.getEverything(q = "bitcoin", sources = "bbc-news", domains = null, from = "2018-01-01", to = "2018-02-13", language = Language.EN, sortBy = SortBy.POPULARITY, pageSize = 20, page = 1)
                .subscribeOn(Schedulers.io())
                .toFlowable()
                .flatMapIterable { articles -> articles.articles }
                .subscribe({ article -> Log.d("getEverything article", article.title) },
                        { t -> Log.d("getEverything error", t.message) }))

        compositeDisposable.add(newsApiRepository.getTopHeadlines(category = Category.GENERAL, country = Country.US, q = "trump", pageSize = 20, page = 1)
                .subscribeOn(Schedulers.io())
                .toFlowable()
                .flatMapIterable { articles -> articles.articles }
                .subscribe({ article -> Log.d("getTopHead CC article", article.title) },
                        { t -> Log.d("getTopHeadlines error", t.message) }))

        compositeDisposable.add(newsApiRepository.getTopHeadlines(sources = "bbc-news", q = "trump", pageSize = 20, page = 1)
                .subscribeOn(Schedulers.io())
                .toFlowable()
                .flatMapIterable { articles -> articles.articles }
                .subscribe({ article -> Log.d("getTopHead S article", article.title) },
                        { t -> Log.d("getTopHeadlines error", t.message) }))
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }
}
