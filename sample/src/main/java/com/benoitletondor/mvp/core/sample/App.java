package com.benoitletondor.mvp.core.sample;

import com.benoitletondor.mvp.core.sample.injection.AppModule;
import com.benoitletondor.mvp.core.sample.injection.DaggerAppComponent;

import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;

/**
 * Base application class
 *
 * @author
 */
public class App extends DaggerApplication
{
    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector()
    {
        return DaggerAppComponent
            .builder()
            .appModule(new AppModule(this))
            .build();
    }
}
