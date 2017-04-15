package com.benoitletondor.mvp.core.presenter.impl;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.benoitletondor.mvp.core.presenter.Presenter;
import com.benoitletondor.mvp.core.view.View;

/**
 * Abstract presenter implementation that contains base implementation for other presenters.
 * Subclasses must call super for all {@link Presenter} method overriding.
 *
 * @author Benoit LETONDOR
 */
@SuppressWarnings("unused")
public abstract class BasePresenterImpl<V extends View> implements Presenter<V>
{
    /**
     * The view, will be null if the presenter isn't attached to a view
     */
    @SuppressWarnings("WeakerAccess")
    @Nullable
    protected V mView;

// ------------------------------------------->

    @Override
    public void onViewAttached(@NonNull V view)
    {
        mView = view;
    }


    @Override
    public void onStart(boolean viewCreated)
    {
        // No-op but subclasses can override it
    }

    @Override
    public void onStop()
    {
        // No-op but subclasses can override it
    }

    @Override
    public void onViewDetached()
    {
        mView = null;
    }

    @Override
    public void onFinish()
    {
        // No-op but subclasses can override it
    }
}
