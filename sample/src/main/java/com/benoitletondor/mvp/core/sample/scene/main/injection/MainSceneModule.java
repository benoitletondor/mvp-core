package com.benoitletondor.mvp.core.sample.scene.main.injection;

import androidx.annotation.NonNull;

import com.benoitletondor.mvp.core.presenter.loader.PresenterFactory;
import com.benoitletondor.mvp.core.sample.scene.main.MainPresenter;
import com.benoitletondor.mvp.core.sample.scene.main.impl.MainPresenterImpl;

import dagger.Module;
import dagger.Provides;

/**
 * Injection module for the main scene
 *
 * @author Benoit LETONDOR
 */
@Module
public final class MainSceneModule
{
    @Provides
    PresenterFactory<MainPresenter> provideMainPresenterFactory()
    {
        return new MainPresenterImplFactory();
    }

    private static final class MainPresenterImplFactory implements PresenterFactory<MainPresenter>
    {
        @NonNull
        @Override
        public MainPresenter create()
        {
            return new MainPresenterImpl();
        }
    }
}
