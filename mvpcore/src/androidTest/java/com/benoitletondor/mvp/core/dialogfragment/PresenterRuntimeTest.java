package com.benoitletondor.mvp.core.dialogfragment;

import android.support.test.runner.AndroidJUnit4;

import com.benoitletondor.mvp.core.ActivityLifecycleTestRule;
import com.benoitletondor.mvp.core.SpyPresenter;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Instrumentation test that tests the presenter runtime with an Fragment.
 *
 * @author Benoit LETONDOR
 */
@RunWith(AndroidJUnit4.class)
public class PresenterRuntimeTest
{
    @Rule
    public final ActivityLifecycleTestRule<DialogFragmentContainerActivity> mActivityRule
        = new ActivityLifecycleTestRule<>(DialogFragmentContainerActivity.class);

    @Test
    public void testPresenterRuntime()
    {
        final SpyPresenter<MVPDialogFragment> presenter = mActivityRule.getActivity().getFragment().getPresenter();
        assertNotNull(presenter);

        // Test first launch
        assertEquals(1, presenter.mOnStartCounter);
        assertEquals(1, presenter.mViewAttachedCounter);
        assertEquals(0, presenter.mOnStopCounter);
        assertEquals(0, presenter.mOnViewDetachedCounter);
        assertEquals(0, presenter.mOnFinishCounter);

        // Test stop
        mActivityRule.simulateActivityStop();
        assertEquals(1, presenter.mOnStartCounter);
        assertEquals(1, presenter.mViewAttachedCounter);
        assertEquals(1, presenter.mOnStopCounter);
        assertEquals(1, presenter.mOnViewDetachedCounter);
        assertEquals(0, presenter.mOnFinishCounter);

        // Test restart
        mActivityRule.simulateActivityStart();
        assertEquals(2, presenter.mOnStartCounter);
        assertEquals(2, presenter.mViewAttachedCounter);
        assertEquals(1, presenter.mOnStopCounter);
        assertEquals(1, presenter.mOnViewDetachedCounter);
        assertEquals(0, presenter.mOnFinishCounter);

        // Test added to backstack (doesn't trigger onStop)
        mActivityRule.getActivity().addInstanceToBackstack();
        assertEquals(2, presenter.mOnStartCounter);
        assertEquals(2, presenter.mViewAttachedCounter);
        assertEquals(1, presenter.mOnStopCounter);
        assertEquals(1, presenter.mOnViewDetachedCounter);
        assertEquals(0, presenter.mOnFinishCounter);

        // Test back from backstack (doesn't trigger onStart)
        mActivityRule.getActivity().popBackStack();
        assertEquals(2, presenter.mOnStartCounter);
        assertEquals(2, presenter.mViewAttachedCounter);
        assertEquals(1, presenter.mOnStopCounter);
        assertEquals(1, presenter.mOnViewDetachedCounter);
        assertEquals(0, presenter.mOnFinishCounter);

        // Test recreate
        mActivityRule.recreateCurrentActivity();
        assertEquals(presenter, mActivityRule.getActivity().getFragment().getPresenter());

        assertEquals(3, presenter.mOnStartCounter);
        assertEquals(3, presenter.mViewAttachedCounter);
        assertEquals(2, presenter.mOnStopCounter);
        assertEquals(2, presenter.mOnViewDetachedCounter);
        assertEquals(0, presenter.mOnFinishCounter);

        // Test finish
        mActivityRule.finishCurrentActivity();
        assertEquals(3, presenter.mOnStartCounter);
        assertEquals(3, presenter.mViewAttachedCounter);
        assertEquals(3, presenter.mOnStopCounter);
        assertEquals(3, presenter.mOnViewDetachedCounter);
        assertEquals(1, presenter.mOnFinishCounter);
    }

}