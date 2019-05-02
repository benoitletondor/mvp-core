package com.benoitletondor.mvp.core.sample.scene.dialog.injection;

import com.benoitletondor.mvp.core.sample.scene.dialog.impl.DialogPresenterImpl;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import dagger.Module;
import dagger.Provides;

/**
 * Injection module for the dialog scene
 *
 * @author Benoit LETONDOR
 */
@Module
public final class DialogSceneModule
{
    @Provides
    protected ViewModelProvider.NewInstanceFactory getPresenterFactory()
    {
        return new DialogPresenterImplFactory();
    }

    private static final class DialogPresenterImplFactory extends ViewModelProvider.NewInstanceFactory
    {
        @NonNull
        @Override
        @SuppressWarnings("unchecked")
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass)
        {
            return (T) new DialogPresenterImpl();
        }
    }
}
