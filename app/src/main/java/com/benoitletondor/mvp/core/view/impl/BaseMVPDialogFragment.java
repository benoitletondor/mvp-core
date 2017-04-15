package com.benoitletondor.mvp.core.view.impl;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.util.Log;
import android.view.View;

import com.benoitletondor.mvp.core.presenter.Presenter;
import com.benoitletondor.mvp.core.presenter.loader.PresenterFactory;
import com.benoitletondor.mvp.core.presenter.loader.PresenterLoader;

import java.util.concurrent.atomic.AtomicBoolean;

@SuppressWarnings("unused")
public abstract class BaseMVPDialogFragment<P extends Presenter<V>, V extends com.benoitletondor.mvp.core.view.View> extends DialogFragment implements LoaderManager.LoaderCallbacks<P>
{
    private final static String TAG = BaseMVPFragment.class.getName();
    private final static int LOADER_ID = 17051992;

    /**
     * The presenter for this view
     */
    @SuppressWarnings("WeakerAccess")
    @Nullable
    protected P mPresenter;
    /**
     * Is this the first start of the fragment (after onCreate)
     */
    private boolean mFirstStart;
    /**
     * Do we need to call {@link #doStart()} from the {@link #onLoadFinished(Loader, P)} method.
     * Will be true if presenter wasn't loaded when {@link #onStart()} is reached
     */
    private final AtomicBoolean mNeedToCallStart = new AtomicBoolean(false);

// ------------------------------------------->

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        mFirstStart = true;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);

        getLoaderManager().initLoader(LOADER_ID, null, this);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
    {
        mFirstStart = true;

        super.onViewCreated(view, savedInstanceState);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        mFirstStart = true;

        return super.onCreateDialog(savedInstanceState);
    }

    @Override
    public void onStart()
    {
        super.onStart();

        if( mPresenter == null )
        {
            mNeedToCallStart.set(true);
            Log.d(TAG, "Start postponed, presenter not ready");
        }
        else
        {
            doStart();
        }
    }

    @Override
    public void onStop()
    {
        if( mPresenter != null )
        {
            mPresenter.onStop();

            mPresenter.onViewDetached();
        }

        super.onStop();
    }

    @Override
    public void onDismiss(DialogInterface dialog)
    {
        // Force destroy manually cause it's not called by the framework, not sure why yet
        if( getActivity() != null ) // If get activity == null, it's just a rotation
        {
            getLoaderManager().destroyLoader(LOADER_ID);
        }

        super.onDismiss(dialog);
    }

// ------------------------------------------->

    /**
     * Call the presenter callbacks for onStart
     */
    @SuppressWarnings("unchecked")
    private void doStart()
    {
        assert mPresenter != null;

        mPresenter.onViewAttached((V) this);

        mPresenter.onStart(mFirstStart);

        mFirstStart = false;
    }

    @Override
    public final Loader<P> onCreateLoader(int id, Bundle args)
    {
        return new PresenterLoader<>(getContext(), getPresenterFactory());
    }

    @Override
    public final void onLoadFinished(Loader<P> loader, P presenter)
    {
        mPresenter = presenter;

        if( mNeedToCallStart.compareAndSet(true, false) )
        {
            doStart();
            Log.d(TAG, "Postponed start called");
        }
    }

    @Override
    public final void onLoaderReset(Loader<P> loader)
    {
        mPresenter = null;
    }

    /**
     * Get the presenter factory implementation for this view
     *
     * @return the presenter factory
     */
    protected abstract PresenterFactory<P> getPresenterFactory();
}
