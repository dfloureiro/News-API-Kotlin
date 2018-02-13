package dfl.com.newsapikotin

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by loureiro on 13-02-2018.
 */
class Model {

    class Sources {
        @SerializedName("status")
        @Expose
        var status: String? = null
        @SerializedName("code")
        @Expose
        var code: String? = null
        @SerializedName("message")
        @Expose
        var message: String? = null
        @SerializedName("sources")
        @Expose
        var sources: List<Source>? = null
    }

    class Source {
        @SerializedName("id")
        @Expose
        var id: String? = null
        @SerializedName("name")
        @Expose
        var name: String? = null
        @SerializedName("description")
        @Expose
        var description: String? = null
        @SerializedName("url")
        @Expose
        var url: String? = null
        @SerializedName("category")
        @Expose
        var category: String? = null
        @SerializedName("language")
        @Expose
        var language: String? = null
        @SerializedName("country")
        @Expose
        var country: String? = null
    }


    class Articles {
        @SerializedName("status")
        @Expose
        var status: String? = null
        @SerializedName("code")
        @Expose
        var code: String? = null
        @SerializedName("message")
        @Expose
        var message: String? = null
        @SerializedName("totalResults")
        @Expose
        var totalResults: Int? = null
        @SerializedName("articles")
        @Expose
        var articles: List<Article>? = null
    }

    class Article {
        @SerializedName("source")
        @Expose
        var source: Source? = null
        @SerializedName("author")
        @Expose
        var author: String? = null
        @SerializedName("title")
        @Expose
        var title: String? = null
        @SerializedName("description")
        @Expose
        var description: String? = null
        @SerializedName("url")
        @Expose
        var url: String? = null
        @SerializedName("urlToImage")
        @Expose
        var urlToImage: String? = null
        @SerializedName("publishedAt")
        @Expose
        var publishedAt: String? = null
    }


}