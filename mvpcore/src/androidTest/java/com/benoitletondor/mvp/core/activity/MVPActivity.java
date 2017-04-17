package com.benoitletondor.mvp.core.activity;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;

import com.benoitletondor.mvp.core.SpyPresenter;
import com.benoitletondor.mvp.core.presenter.loader.PresenterFactory;
import com.benoitletondor.mvp.core.view.View;
import com.benoitletondor.mvp.core.view.impl.BaseMVPActivity;

/**
 * A simple activity that exposes its presenter that exposes its view.
 *
 * @author Benoit LETONDOR
 */
public class MVPActivity extends BaseMVPActivity<SpyPresenter<MVPActivity>, MVPActivity> implements View
{
    @Override
    protected PresenterFactory<SpyPresenter<MVPActivity>> getPresenterFactory()
    {
        return new PresenterFactory<SpyPresenter<MVPActivity>>()
        {
            @NonNull
            @Override
            public SpyPresenter<MVPActivity> create()
            {
                return new SpyPresenter<>();
            }
        };
    }

    @Nullable
    @VisibleForTesting
    public SpyPresenter<MVPActivity> getPresenter()
    {
        return mPresenter;
    }
}
