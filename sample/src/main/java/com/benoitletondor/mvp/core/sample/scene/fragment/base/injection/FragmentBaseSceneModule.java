package com.benoitletondor.mvp.core.sample.scene.fragment.base.injection;

import com.benoitletondor.mvp.core.sample.scene.fragment.base.impl.FragmentPresenterImpl;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import dagger.Module;
import dagger.Provides;

/**
 * Injection module for the base fragment scene
 *
 * @author Benoit LETONDOR
 */
@Module
public final class FragmentBaseSceneModule
{
    @Provides
    protected ViewModelProvider.NewInstanceFactory getPresenterFactory()
    {
        return new FragmentPresenterImplFactory();
    }

    private static final class FragmentPresenterImplFactory extends ViewModelProvider.NewInstanceFactory
    {
        @NonNull
        @Override
        @SuppressWarnings("unchecked")
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass)
        {
            return (T) new FragmentPresenterImpl();
        }
    }

}
