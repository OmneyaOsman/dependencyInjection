package com.omni.dependencyinjection.component;

import com.omni.dependencyinjection.MainActivity;
import com.omni.dependencyinjection.interfaces.MainActivityScope;
import com.omni.dependencyinjection.module.MainActivityModule;

import dagger.Component;

@MainActivityScope
@Component(modules = MainActivityModule.class , dependencies = NewsComponent.class)
public interface MainActivityComponent {
    void injectMainActivity(MainActivity mainActivity);
}
