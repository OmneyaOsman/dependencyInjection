package com.omni.dependencyinjection;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.picasso.OkHttp3Downloader;
import com.omni.dependencyinjection.adapter.NewsAdapter;
import com.omni.dependencyinjection.application.NewsApplication;
import com.omni.dependencyinjection.component.DaggerMainActivityComponent;
import com.omni.dependencyinjection.component.MainActivityComponent;
import com.omni.dependencyinjection.interfaces.NewsServiceApi;
import com.omni.dependencyinjection.model.NewsResponse;
import com.omni.dependencyinjection.module.MainActivityModule;
import com.squareup.picasso.Picasso;

import java.io.File;

import javax.inject.Inject;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import timber.log.Timber;

public class MainActivity extends AppCompatActivity {

    Retrofit retrofit;
    RecyclerView recyclerView;

    Picasso picasso;

    @Inject
    NewsServiceApi newsServiceApi;

    @Inject
    NewsAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        //beforeDagger2();
        //afterDagger();
        afterActivityLevelComponent();
        populateUsers();

    }

    private void afterActivityLevelComponent() {
        MainActivityComponent mainActivityComponent = DaggerMainActivityComponent.builder()
                .mainActivityModule(new MainActivityModule(this))
                .newsComponent(NewsApplication.get(this).getRandomUserApplicationComponent())
                .build();
        mainActivityComponent.injectMainActivity(this);
    }

//    public void afterDagger() {
//        NewsComponent daggerRandomUserComponent = DaggerNewsComponent.builder()
//                .contextModule(new ContextModule(this))
//                .build();
//        picasso = daggerRandomUserComponent.getPicasso();
//        newsServiceApi = daggerRandomUserComponent.getRandomUserService();
//    }

    private void beforeDagger2() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();

        Timber.plant(new Timber.DebugTree());

        File cacheFile = new File(this.getCacheDir(), "HttpCache");
        cacheFile.mkdirs();

        Cache cache = new Cache(cacheFile, 10 * 1000 * 1000); //10 MB

        HttpLoggingInterceptor httpLoggingInterceptor = new
                HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(@NonNull String message) {
                Timber.i(message);
            }
        });

        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);


        OkHttpClient okHttpClient = new OkHttpClient()
                .newBuilder()
                .cache(cache)
                .addInterceptor(httpLoggingInterceptor)
                .build();

        OkHttp3Downloader okHttpDownloader = new OkHttp3Downloader(okHttpClient);

        picasso = new Picasso.Builder(this).downloader(okHttpDownloader).build();

        retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("https://randomuser.me/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    private void initViews() {
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void populateUsers() {
        Call<NewsResponse> randomUsersCall = getRandomUserService().getRandomUsers("bitcoin" ,"publishedAt",BuildConfig.NEWS_API_KEY);
        randomUsersCall.enqueue(new Callback<NewsResponse>() {
            @Override
            public void onResponse(@NonNull Call<NewsResponse> call, @NonNull Response<NewsResponse> response) {
                if(response.isSuccessful()) {
                    Log.d("", "onResponse: "+response.body().getArticles().get(0));
                    mAdapter.setItems(response.body().getArticles());
                    recyclerView.setAdapter(mAdapter);
                }
            }

            @Override
            public void onFailure(@NonNull Call<NewsResponse> call, @NonNull Throwable t) {
                Timber.i(t.getMessage());
            }
        });
    }

    public NewsServiceApi getRandomUserService(){
        return newsServiceApi;
    }


}