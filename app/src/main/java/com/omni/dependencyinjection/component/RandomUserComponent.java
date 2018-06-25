package com.omni.dependencyinjection.component;

import com.omni.dependencyinjection.interfaces.RandomUsersApi;
import com.omni.dependencyinjection.module.PicassoModule;
import com.omni.dependencyinjection.module.RandomUserModule;
import com.squareup.picasso.Picasso;

import dagger.Component;

@Component(modules = {RandomUserModule.class , PicassoModule.class })
public interface RandomUserComponent {

    RandomUsersApi getRandomUserApi();
    Picasso getPicasso();
}
