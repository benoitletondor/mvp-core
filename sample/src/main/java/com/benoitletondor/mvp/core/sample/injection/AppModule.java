package com.benoitletondor.mvp.core.sample.injection;

import android.content.Context;
import android.support.annotation.NonNull;

import com.benoitletondor.mvp.core.sample.App;

import dagger.Module;
import dagger.Provides;

/**
 * Application module containing common base injection app-wide
 *
 * @author Benoit LETONDOR
 */
@Module
public final class AppModule
{
    private final App mApp;

    public AppModule(@NonNull App app)
    {
        mApp = app;
    }

    @Provides
    App provideApplication()
    {
        return mApp;
    }

    @Provides
    Context provideApplicationContext(@NonNull App app)
    {
        return app.getApplicationContext();
    }
}
