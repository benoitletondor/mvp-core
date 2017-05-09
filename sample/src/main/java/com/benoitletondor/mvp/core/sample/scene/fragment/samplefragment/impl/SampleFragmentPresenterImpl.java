package com.benoitletondor.mvp.core.sample.scene.fragment.samplefragment.impl;

import com.benoitletondor.mvp.core.presenter.impl.BasePresenterImpl;
import com.benoitletondor.mvp.core.sample.scene.fragment.samplefragment.SampleFragmentPresenter;
import com.benoitletondor.mvp.core.sample.scene.fragment.samplefragment.SampleFragmentView;

/**
 * Implementation of the {@link SampleFragmentPresenter}
 *
 * @author Benoit LETONDOR
 */
public class SampleFragmentPresenterImpl extends BasePresenterImpl<SampleFragmentView> implements SampleFragmentPresenter
{
    private int mCounter = 0;

    @Override
    public void onStart(boolean viewCreated)
    {
        super.onStart(viewCreated);
        assert mView != null;

        if( viewCreated )
        {
            upgradeFizzBuzzView();
        }
    }

    @Override
    public void onPlusButtonClicked()
    {
        mCounter++;

        upgradeFizzBuzzView();
    }

    private void upgradeFizzBuzzView()
    {
        if( mView == null )
        {
            return;
        }

        if (mCounter % 15 == 0)
        {
            mView.setFizzBuzzText("FizzBuzz");
        }
        else if (mCounter % 3 == 0)
        {
            mView.setFizzBuzzText("Fizz");
        }
        else if (mCounter % 5 == 0)
        {
            mView.setFizzBuzzText("Buzz");
        }
        else
        {
            mView.setFizzBuzzText(String.valueOf(mCounter));
        }
    }
}
