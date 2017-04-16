package com.benoitletondor.mvp.core.fragment;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;

import com.benoitletondor.mvp.core.SpyPresenter;
import com.benoitletondor.mvp.core.presenter.loader.PresenterFactory;
import com.benoitletondor.mvp.core.view.View;
import com.benoitletondor.mvp.core.view.impl.BaseMVPFragment;

/**
 * A simple fragment that exposes its presenter that exposes its view.
 *
 * @author Benoit LETONDOR
 */
public final class MVPFragment extends BaseMVPFragment<SpyPresenter<MVPFragment>, MVPFragment> implements View
{
    @Override
    protected PresenterFactory<SpyPresenter<MVPFragment>> getPresenterFactory()
    {
        return new PresenterFactory<SpyPresenter<MVPFragment>>()
        {
            @NonNull
            @Override
            public SpyPresenter<MVPFragment> create()
            {
                return new SpyPresenter<>();
            }
        };
    }

    @Nullable
    @VisibleForTesting
    public SpyPresenter<MVPFragment> getPresenter()
    {
        return mPresenter;
    }
}
