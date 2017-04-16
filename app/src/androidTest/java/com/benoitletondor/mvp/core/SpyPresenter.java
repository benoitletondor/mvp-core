package com.benoitletondor.mvp.core;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.benoitletondor.mvp.core.presenter.impl.BasePresenterImpl;
import com.benoitletondor.mvp.core.view.View;

/**
 * A basic implementation of a presenter that spies method call and exposes the view.
 *
 * @author Benoit LETONDOR
 */
public final class SpyPresenter<V extends View> extends BasePresenterImpl<V>
{
    public int mViewAttachedCounter = 0;
    public int mOnStartCounter = 0;
    public int mOnStopCounter = 0;
    public int mOnViewDetachedCounter = 0;
    public int mOnFinishCounter = 0;

// ------------------------------->

    @Override
    public void onViewAttached(@NonNull V view)
    {
        super.onViewAttached(view);

        mViewAttachedCounter++;
    }

    @Override
    public void onStart(boolean viewCreated)
    {
        super.onStart(viewCreated);

        mOnStartCounter++;
    }

    @Override
    public void onStop()
    {
        super.onStop();

        mOnStopCounter++;
    }

    @Override
    public void onViewDetached()
    {
        super.onViewDetached();

        mOnViewDetachedCounter++;
    }

    @Override
    public void onFinish()
    {
        super.onFinish();

        mOnFinishCounter++;
    }

// ------------------------------->

    @Nullable
    public V getView()
    {
        return mView;
    }

    public void resetCounters()
    {
        mViewAttachedCounter = 0;
        mOnStartCounter = 0;
        mOnStopCounter = 0;
        mOnViewDetachedCounter = 0;
        mOnFinishCounter = 0;
    }
}
