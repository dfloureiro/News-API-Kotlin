package dfl.com.newsapikotlin;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import dfl.com.newsapikotin.NewsApi;
import dfl.com.newsapikotin.enums.Category;
import dfl.com.newsapikotin.enums.Country;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by loureiro on 16-02-2018.
 */

public class MainActivityJava extends AppCompatActivity {

    private NewsApi newsApi;
    private CompositeDisposable compositeDisposable;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_java);

        compositeDisposable = new CompositeDisposable();
        newsApi = new NewsApi("a9b9d5c92bc249ac976e796fb79d7a33", getCacheDir(), 60, 10 * 1024 * 1024L, 45L, 45L);
        findViewById(R.id.buttonjava).setOnClickListener(view -> doRequest());

    }

    private void doRequest() {
        compositeDisposable.add(newsApi.getSources(null, null, Country.BR)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(sources -> {
                        },
                        throwable -> Log.d("error", throwable.getMessage())));

        compositeDisposable.add(newsApi.getTopHeadlines(Category.GENERAL,null,null,20,1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        .subscribe(articles -> {},
                throwable -> Log.d("error", throwable.getMessage())));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeDisposable.clear();
    }
}