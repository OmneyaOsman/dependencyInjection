package com.omni.dependencyinjection.component;

import com.omni.dependencyinjection.interfaces.NewsServiceApi;
import com.omni.dependencyinjection.module.NewsModule;
import com.omni.dependencyinjection.module.PicassoModule;
import com.squareup.picasso.Picasso;

import dagger.Component;

@Component(modules = {NewsModule.class , PicassoModule.class })
public interface NewsComponent {

    NewsServiceApi getRandomUserApi();
    Picasso getPicasso();
}
