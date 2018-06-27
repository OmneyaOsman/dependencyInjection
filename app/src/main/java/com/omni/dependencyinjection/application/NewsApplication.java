package com.omni.dependencyinjection.application;

import android.app.Activity;
import android.app.Application;

import com.omni.dependencyinjection.component.DaggerNewsComponent;
import com.omni.dependencyinjection.component.NewsComponent;
import com.omni.dependencyinjection.module.ContextModule;

import timber.log.Timber;

public class NewsApplication extends Application {

    NewsComponent newsComponent;

    public static NewsApplication get(Activity activity) {
        return (NewsApplication)  activity.getApplication();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Timber.plant(new Timber.DebugTree());
        newsComponent = DaggerNewsComponent.builder().contextModule(new ContextModule(this)).build();

    }

    public NewsComponent getRandomUserApplicationComponent(){
        return newsComponent;
    }
}
