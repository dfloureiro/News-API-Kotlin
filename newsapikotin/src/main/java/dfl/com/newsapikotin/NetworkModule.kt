package dfl.com.newsapikotin

import okhttp3.Cache
import okhttp3.CacheControl
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit


/**
 * Created by loureiro on 13-02-2018.
 */
internal class NetworkModule {

    private val url = "https://newsapi.org/"

    fun create(apiKey: String, cacheDir: File, cacheMaxAgeSeconds: Int, cacheMaxSize: Long, readTimeOutSeconds: Long, writeTimeoutSeconds: Long): NewsApiService {

        val retrofit = Retrofit.Builder()
                .client(createOkHttpClient(apiKey, cacheDir, cacheMaxAgeSeconds, cacheMaxSize, readTimeOutSeconds, writeTimeoutSeconds))
                .addCallAdapterFactory(
                        RxJava2CallAdapterFactory.create())
                .addConverterFactory(
                        GsonConverterFactory.create())
                .baseUrl(url)
                .build()

        return retrofit.create(NewsApiService::class.java)
    }

    private fun createOkHttpClient(apiKey: String, cacheDir: File, cacheMaxAgeSeconds: Int, cacheMaxSize: Long, readTimeOutSeconds: Long, writeTimeoutSeconds: Long): OkHttpClient {
        val okHttpClient = OkHttpClient.Builder()
                .cache(Cache(cacheDir, cacheMaxSize))
                .addNetworkInterceptor({ chain ->
                    val cacheControl = CacheControl.Builder().maxAge(cacheMaxAgeSeconds, TimeUnit.SECONDS)
                            .build()
                    val request = chain.request().newBuilder()
                            .addHeader("Cache-Control", cacheControl.toString())
                            .build()
                    chain.proceed(request)
                })
                .addInterceptor({ chain ->
                    val request = chain.request().newBuilder().addHeader("X-Api-Key", apiKey).build()
                    chain.proceed(request)
                })
                .readTimeout(readTimeOutSeconds, TimeUnit.SECONDS)
                .writeTimeout(writeTimeoutSeconds, TimeUnit.SECONDS)

        return okHttpClient.build()
    }
}