package com.benoitletondor.mvp.core.activity;

import android.support.test.runner.AndroidJUnit4;

import com.benoitletondor.mvp.core.ActivityLifecycleTestRule;
import com.benoitletondor.mvp.core.SpyPresenter;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static org.junit.Assert.*;

/**
 * Instrumentation test that tests the MVP runtime with an Activity.
 *
 * @author Benoit LETONDOR
 */
@RunWith(AndroidJUnit4.class)
public final class ActivityRuntimeTest
{
    @Rule
    public ActivityLifecycleTestRule<MVPActivity> mActivityRule = new ActivityLifecycleTestRule<>(MVPActivity.class);

    @Test
    public void testPresenterAndViewAreBindAfterStartingActivity()
    {
        assertNotNull(mActivityRule.getActivity().getPresenter());
        assertEquals(mActivityRule.getActivity(), mActivityRule.getActivity().getPresenter().getView());
    }

    @Test
    public void testViewIsReleasedAfterStoppingTheActivity()
    {
        getInstrumentation().callActivityOnPause(mActivityRule.getActivity());
        getInstrumentation().callActivityOnStop(mActivityRule.getActivity());

        assertNotNull(mActivityRule.getActivity().getPresenter());
        assertNull(mActivityRule.getActivity().getPresenter().getView());
    }

    @Test
    public void testPresenterIsReleasedAfterFinishingTheActivity() throws Exception
    {
        final MVPActivity activity = mActivityRule.getActivity();
        mActivityRule.finishCurrentActivity();

        assertNull(activity.getPresenter());
    }

    @Test
    public void testPresenterIsKeptOnRotation() throws Exception
    {
        final MVPActivity activity = mActivityRule.getActivity();
        final SpyPresenter<MVPActivity> presenter = activity.getPresenter();

        mActivityRule.recreateCurrentActivity();

        assertTrue(mActivityRule.getActivity() != activity);

        assertNotNull(mActivityRule.getActivity().getPresenter());
        assertTrue(mActivityRule.getActivity().getPresenter() == presenter);
    }
}
