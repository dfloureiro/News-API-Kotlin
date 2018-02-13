package dfl.com.newsapikotin

import dfl.com.newsapikotin.enums.Category
import dfl.com.newsapikotin.enums.Country
import dfl.com.newsapikotin.enums.Language
import dfl.com.newsapikotin.enums.SortBy
import io.reactivex.Flowable

/**
 * Created by loureiro on 13-02-2018.
 */
class NewsApi(apiKey : String, cacheMaxAgeSeconds : Int? = 120, cacheMaxSize : Long? = 10*1024*1024,
              readTimeOutSeconds : Long? = 45, writeTimeoutSeconds : Long? = 45) {

    private val networkModule = NetworkModule().create(apiKey, cacheMaxAgeSeconds!!, cacheMaxSize!!, readTimeOutSeconds!!, writeTimeoutSeconds!!)

    fun getSources(category: Category? = null, language: Language? = null, country: Country? = null):Flowable<Model.Sources>{
        return networkModule.getSources(category.toString().toLowerCase(), language.toString().toLowerCase(), country.toString().toLowerCase())
    }

    fun getEverything(q: String? = null, sources: String? = null, domains: String? = null, from: String? = null,
                      to: String? = null, language: Language? = null, sortBy: SortBy? = null,
                      pageSize: Int? = null, page: Int? = null):Flowable<Model.Articles>{
        return networkModule.getEverything(q,sources,domains,from,to,language.toString().toLowerCase(),sortBy.toString().toLowerCase(),pageSize,page)
    }

    fun getTopHeadlines(category: Category? = null, country: Country?= null, q: String?= null, pageSize: Int?= null,
                        page: Int?= null):Flowable<Model.Articles>{
        return networkModule.getTopHeadlines(category.toString().toLowerCase(),country.toString().toLowerCase(),null,q,pageSize,page)
    }

    fun getTopHeadlines(sources: String?= null, q: String?= null, pageSize: Int?= null,
                        page: Int?= null):Flowable<Model.Articles>{
        return networkModule.getTopHeadlines(null,null,sources,q,pageSize,page)
    }
}