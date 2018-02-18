# News-API-Kotlin
This is a Kotlin API Wrapper SDK for the https://newsapi.org. Go to the website to get your apiKey.

## How to import:
### Gradle:
Add it in your root build.gradle at the end of repositories:

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
  
Add the dependency:

	dependencies {
	        compile 'com.github.dfloureiro:news-api-kotlin:v1.0'
	}

### Maven:

	<repositories>
		<repository>
		    <id>jitpack.io</id>
		    <url>https://jitpack.io</url>
		</repository>
	</repositories>
  
Add the dependency:

	<dependency>
	    <groupId>com.github.dfloureiro</groupId>
	    <artifactId>news-api-kotlin</artifactId>
	    <version>v1.0</version>
	</dependency>


### How to use examples:
Create an instance of NewsApi:

        val newsApi = NewsApi("apikey")
        /*optional customizable parameters
        cacheMaxAgeSeconds, cacheMaxSize, readTimeOutSeconds, writeTimeoutSeconds
        /*

Get top headlines by category/country/query:

        newsApi.getTopHeadlines(category = Category.GENERAL, country = Country.US, q = "trump", pageSize = 20, page = 1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMapIterable { articles -> articles.articles }
                .subscribe({ article -> Log.d("getTopHead CC article", article.title) },
                        { t -> Log.d("getTopHeadlines error", t.message) })
		
Get top headlines by sources/query:

        newsApi.getTopHeadlines(sources = "bbc-news", q = "trump", pageSize = 20, page = 1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMapIterable { articles -> articles.articles }
                .subscribe({ article -> Log.d("getTopHead S article", article.title) },
                        { t -> Log.d("getTopHeadlines error", t.message) })

Search articles from the full repo with everything:

        newsApi.getEverything(q = "bitcoin", sources = "bbc-news", domains = null, from =  "2018-01-01", to = "2018-02-13", language = Language.EN, sortBy = SortBy.POPULARITY, pageSize = 20, page = 1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMapIterable { articles -> articles.articles }
                .subscribe({ article -> Log.d("getEverything article", article.title) },
                        { t -> Log.d("getEverything error", t.message) })

Get available sources:

        newsApi.getSources(category = Category.GENERAL, language = Language.EN, country = Country.US)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMapIterable { sources -> sources.sources }
                .subscribe({ source -> Log.d("source", source.name) },
                        { t -> Log.d("getSources error", t.message) })
			

### Used technologies:
#### KOTLIN
https://github.com/JetBrains/kotlin
#### RETROFIT 2
https://github.com/square/retrofit/tree/master/retrofit-adapters/rxjava2
#### GSON
https://github.com/square/retrofit/tree/master/retrofit-converters/gson
#### RXJAVA 2
https://github.com/ReactiveX/RxJava
