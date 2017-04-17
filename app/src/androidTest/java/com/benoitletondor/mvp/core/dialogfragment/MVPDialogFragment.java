package com.benoitletondor.mvp.core.dialogfragment;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;

import com.benoitletondor.mvp.core.SpyPresenter;
import com.benoitletondor.mvp.core.presenter.loader.PresenterFactory;
import com.benoitletondor.mvp.core.view.View;
import com.benoitletondor.mvp.core.view.impl.BaseMVPDialogFragment;

/**
 * A simple DialogFragment that exposes its presenter that exposes its view.
 *
 * @author Benoit LETONDOR
 */
public final class MVPDialogFragment extends BaseMVPDialogFragment<SpyPresenter<MVPDialogFragment>, MVPDialogFragment> implements View
{
    @Override
    protected PresenterFactory<SpyPresenter<MVPDialogFragment>> getPresenterFactory()
    {
        return new PresenterFactory<SpyPresenter<MVPDialogFragment>>()
        {
            @NonNull
            @Override
            public SpyPresenter<MVPDialogFragment> create()
            {
                return new SpyPresenter<>();
            }
        };
    }

    @Nullable
    @VisibleForTesting
    public SpyPresenter<MVPDialogFragment> getPresenter()
    {
        return mPresenter;
    }
}
