package com.benoitletondor.mvp.core.sample.scene.main.impl;

import android.os.Bundle;

import com.benoitletondor.mvp.core.presenter.loader.PresenterFactory;
import com.benoitletondor.mvp.core.sample.R;
import com.benoitletondor.mvp.core.sample.scene.main.MainPresenter;
import com.benoitletondor.mvp.core.sample.scene.main.MainView;
import com.benoitletondor.mvp.core.view.impl.BaseMVPActivity;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

/**
 * Implementation of {@link MainView}
 *
 * @author Benoit LETONDOR
 */
public final class MainActivity extends BaseMVPActivity<MainPresenter, MainView> implements MainView
{
    @Inject
    PresenterFactory<MainPresenter> mPresenterFactory;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        AndroidInjection.inject(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected PresenterFactory<MainPresenter> getPresenterFactory()
    {
        return mPresenterFactory;
    }
}
