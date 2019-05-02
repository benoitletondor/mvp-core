package com.benoitletondor.mvp.core.sample.scene.fragment.samplefragment.injection;

import com.benoitletondor.mvp.core.sample.scene.fragment.samplefragment.impl.SampleFragmentPresenterImpl;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import dagger.Module;
import dagger.Provides;

/**
 * Injection module for the fragment sample scene
 *
 * @author Benoit LETONDOR
 */
@Module
public final class FragmentSampleSceneModule
{
    @Provides
    protected ViewModelProvider.NewInstanceFactory getPresenterFactory()
    {
        return new SampleFragmentPresenterImplFactory();
    }

    private static final class SampleFragmentPresenterImplFactory extends ViewModelProvider.NewInstanceFactory
    {
        @NonNull
        @Override
        @SuppressWarnings("unchecked")
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass)
        {
            return (T) new SampleFragmentPresenterImpl();
        }
    }

}
