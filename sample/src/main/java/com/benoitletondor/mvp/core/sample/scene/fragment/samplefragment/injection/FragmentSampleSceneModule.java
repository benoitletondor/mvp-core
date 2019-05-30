package com.benoitletondor.mvp.core.sample.scene.fragment.samplefragment.injection;

import com.benoitletondor.mvp.core.presenter.PresenterFactory;
import com.benoitletondor.mvp.core.sample.scene.fragment.samplefragment.SampleFragmentPresenter;
import com.benoitletondor.mvp.core.sample.scene.fragment.samplefragment.impl.SampleFragmentPresenterImpl;

import androidx.annotation.NonNull;
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
    protected PresenterFactory<SampleFragmentPresenter> getPresenterFactory()
    {
        return new SampleFragmentPresenterImplFactory();
    }

    private static final class SampleFragmentPresenterImplFactory implements PresenterFactory<SampleFragmentPresenter>
    {
        @NonNull
        @Override
        public SampleFragmentPresenter create()
        {
            return new SampleFragmentPresenterImpl();
        }
    }

}
