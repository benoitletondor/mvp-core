package com.benoitletondor.mvp.core.sample.scene.fragment.base.injection;

import android.support.annotation.NonNull;

import com.benoitletondor.mvp.core.presenter.loader.PresenterFactory;
import com.benoitletondor.mvp.core.sample.scene.fragment.base.FragmentPresenter;
import com.benoitletondor.mvp.core.sample.scene.fragment.base.impl.FragmentPresenterImpl;

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
    PresenterFactory<FragmentPresenter> provideFragmentPresenterFactory()
    {
        return new FragmentPresenterImplFactory();
    }

    private static final class FragmentPresenterImplFactory implements PresenterFactory<FragmentPresenter>
    {
        @NonNull
        @Override
        public FragmentPresenter create()
        {
            return new FragmentPresenterImpl();
        }
    }
}
