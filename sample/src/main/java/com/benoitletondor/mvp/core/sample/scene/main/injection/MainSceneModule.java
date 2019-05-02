package com.benoitletondor.mvp.core.sample.scene.main.injection;

import com.benoitletondor.mvp.core.sample.scene.main.impl.MainPresenterImpl;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
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
    protected ViewModelProvider.NewInstanceFactory getPresenterFactory()
    {
        return new MainPresenterImplFactory();
    }

    private static final class MainPresenterImplFactory extends ViewModelProvider.NewInstanceFactory
    {
        @NonNull
        @Override
        @SuppressWarnings("unchecked")
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass)
        {
            return (T) new MainPresenterImpl();
        }
    }

}
