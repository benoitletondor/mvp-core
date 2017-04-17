package com.benoitletondor.mvp.core.presenter.loader;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.Loader;

import com.benoitletondor.mvp.core.presenter.Presenter;

/**
 * Loader that implements the loading of a Presenter, made to persist it on configuration changes.
 *
 * @author Benoit LETONDOR
 */
public class PresenterLoader<P extends Presenter> extends Loader<P>
{
    /**
     * The presenter, will be null if not created yet
     */
    @Nullable
    private P mPresenter;
    /**
     * Factory to create the presenter
     */
    @NonNull
    private final PresenterFactory<P> mPresenterFactory;

// ---------------------------------------->

    public PresenterLoader(Context context, @NonNull PresenterFactory<P> presenterFactory)
    {
        super(context);

        mPresenterFactory = presenterFactory;
    }

    @Override
    protected void onStartLoading()
    {
        // if we already own a presenter instance, simply deliver it.
        if (mPresenter != null)
        {
            deliverResult(mPresenter);
            return;
        }

        // Otherwise, force a load
        forceLoad();
    }

    @Override
    protected void onForceLoad()
    {
        // Create the Presenter using the Factory
        mPresenter = mPresenterFactory.create();

        // Deliver the result
        deliverResult(mPresenter);
    }

    @Override
    protected void onReset()
    {
        if (mPresenter != null)
        {
            mPresenter.onFinish();
            mPresenter = null;
        }
    }
}
