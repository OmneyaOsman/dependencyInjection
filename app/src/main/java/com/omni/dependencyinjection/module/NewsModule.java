package com.omni.dependencyinjection.module;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.omni.dependencyinjection.interfaces.NewsServiceApi;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module(includes = OkhttpClientModule.class)
public class NewsModule {

    @Provides
    public NewsServiceApi randomUsersApi(Retrofit retrofit){
        return retrofit.create(NewsServiceApi.class);
    }

    @Provides
    public Retrofit retrofit(OkHttpClient okHttpClient,
                             GsonConverterFactory gsonConverterFactory, Gson gson){
        return new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("https://newsapi.org/v2/")
                .addConverterFactory(gsonConverterFactory)
                .build();
    }

    @Provides
    public Gson gson(){
        GsonBuilder gsonBuilder = new GsonBuilder();
        return gsonBuilder.create();
    }

    @Provides
    public GsonConverterFactory gsonConverterFactory(Gson gson){
        return GsonConverterFactory.create(gson);
    }



}
