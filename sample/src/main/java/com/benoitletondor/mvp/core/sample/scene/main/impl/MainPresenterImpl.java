package com.benoitletondor.mvp.core.sample.scene.main.impl;

import com.benoitletondor.mvp.core.presenter.impl.BasePresenterImpl;
import com.benoitletondor.mvp.core.sample.scene.main.MainPresenter;
import com.benoitletondor.mvp.core.sample.scene.main.MainView;

/**
 * Implementation of {@link MainPresenter}
 *
 * @author Benoit LETONDOR
 */
public final class MainPresenterImpl extends BasePresenterImpl<MainView> implements MainPresenter
{
    /**
     * Current counter value
     */
    private int mCounter = 0;

    @Override
    public void onStart(boolean viewCreated)
    {
        super.onStart(viewCreated);
        assert mView != null;

        // You want to update the view to the current value when created
        if( viewCreated )
        {
            mView.setCounterValue(mCounter);
        }
    }

    @Override
    public void onMinusButtonClick()
    {
        mCounter--;

        if( mView != null )
        {
            mView.setCounterValue(mCounter);
        }
    }

    @Override
    public void onPlusButtonClick()
    {
        mCounter++;

        if( mView != null )
        {
            mView.setCounterValue(mCounter);
        }
    }

    @Override
    public void onStartFragmentActivityButtonClicked()
    {
        if( mView != null )
        {
            mView.startFragmentActivity();
        }
    }

    @Override
    public void onShowDialogFragmentButtonClicked()
    {
        if( mView != null )
        {
            mView.showDialogFragment();
        }
    }
}