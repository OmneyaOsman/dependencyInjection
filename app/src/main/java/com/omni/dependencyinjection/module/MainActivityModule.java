package com.omni.dependencyinjection.module;

import com.omni.dependencyinjection.MainActivity;
import com.omni.dependencyinjection.adapter.NewsAdapter;
import com.omni.dependencyinjection.interfaces.MainActivityScope;
import com.squareup.picasso.Picasso;

import dagger.Module;
import dagger.Provides;

@Module

public class MainActivityModule {
    private final MainActivity mainActivity ;

    public MainActivityModule(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Provides
    @MainActivityScope
    public NewsAdapter randomUserAdapter(Picasso picasso){
        return new NewsAdapter(mainActivity , picasso);

    }
}
