package com.omni.dependencyinjection.application;

import android.app.Activity;
import android.app.Application;

import com.omni.dependencyinjection.component.DaggerRandomUserComponent;
import com.omni.dependencyinjection.component.RandomUserComponent;
import com.omni.dependencyinjection.module.ContextModule;

import timber.log.Timber;

public class RandomUserApplication extends Application {

    RandomUserComponent randomUserComponent ;

    public static RandomUserApplication get(Activity activity) {
        return (RandomUserApplication)  activity.getApplication();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Timber.plant(new Timber.DebugTree());
        randomUserComponent = DaggerRandomUserComponent.builder().contextModule(new ContextModule(this)).build();

    }

    public RandomUserComponent getRandomUserApplicationComponent(){
        return randomUserComponent;
    }
}
