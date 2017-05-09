/*
 *   Copyright 2017 Benoit LETONDOR
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */

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
    public final ActivityLifecycleTestRule<MVPActivity> mActivityRule = new ActivityLifecycleTestRule<>(MVPActivity.class);

    @Test
    public void testPresenterAndViewAreBindAfterStartingActivity()
    {
        assertNotNull(mActivityRule.getActivity().getPresenter());
        assertEquals(mActivityRule.getActivity(), mActivityRule.getActivity().getPresenter().getView());
    }

    @Test
    public void testViewIsReleasedAfterStoppingTheActivity()
    {
        mActivityRule.simulateActivityStop();

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
