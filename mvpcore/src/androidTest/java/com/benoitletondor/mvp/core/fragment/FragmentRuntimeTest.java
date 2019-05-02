/*
 *   Copyright 2019 Benoit LETONDOR
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

package com.benoitletondor.mvp.core.fragment;

import com.benoitletondor.mvp.core.ActivityTestHelper;
import com.benoitletondor.mvp.core.SpyPresenter;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

/**
 * Instrumentation test that tests the MVP runtime with a Fragment.
 *
 * @author Benoit LETONDOR
 */
@RunWith(AndroidJUnit4.class)
public final class FragmentRuntimeTest
{
    @Rule
    public final ActivityScenarioRule<FragmentContainerActivity> mActivityRule
        = new ActivityScenarioRule<>(FragmentContainerActivity.class);

    @Test
    public void testPresenterAndViewAreBindAfterStartingActivity()
    {
        final FragmentContainerActivity activity = ActivityTestHelper.getActivity(mActivityRule.getScenario());

        assertNotNull(activity.getFragment().getPresenter());
        assertEquals(activity.getFragment(), activity.getFragment().getPresenter().getView());
    }

    @Test
    public void testViewIsReleasedAfterStoppingTheActivity()
    {
        final FragmentContainerActivity activity = ActivityTestHelper.getActivity(mActivityRule.getScenario());
        ActivityTestHelper.simulateActivityStop(activity);

        assertNotNull(activity.getFragment().getPresenter());
        assertNull(activity.getFragment().getPresenter().getView());
    }

    @Test
    public void testPresenterIsReleasedAfterFinishingTheActivity()
    {
        final FragmentContainerActivity activity = ActivityTestHelper.getActivity(mActivityRule.getScenario());

        final MVPFragment fragment = activity.getFragment();

        ActivityTestHelper.finishActivity(mActivityRule.getScenario());

        assertNull(fragment.getPresenter());
    }

    @Test
    public void testPresenterIsKeptOnRotation()
    {
        final FragmentContainerActivity activity = ActivityTestHelper.getActivity(mActivityRule.getScenario());

        final MVPFragment fragment = activity.getFragment();
        final SpyPresenter<MVPFragment> presenter = fragment.getPresenter();

        final FragmentContainerActivity newActivity = ActivityTestHelper.getActivity(mActivityRule.getScenario().recreate());

        assertNotSame(newActivity.getFragment(), fragment);

        assertNotNull(newActivity.getFragment().getPresenter());
        assertSame(newActivity.getFragment().getPresenter(), presenter);
    }

    @Test
    public void testViewIsReleasedWhenInBackstack()
    {
        final FragmentContainerActivity activity = ActivityTestHelper.getActivity(mActivityRule.getScenario());

        final MVPFragment fragment = activity.getFragment();
        final SpyPresenter<MVPFragment> presenter = fragment.getPresenter();
        assertNotNull(presenter);

        activity.addInstanceToBackstack();
        assertNotSame(activity.getFragment(), fragment);

        assertNull(presenter.getView());

        assertNotNull(activity.getFragment().getPresenter());
        assertNotSame(activity.getFragment().getPresenter(), presenter);
        assertNotSame(activity.getFragment().getPresenter().getView(), fragment);
    }

    @Test
    public void testPresenterIsKeptWhenPoppingBackstack()
    {
        final FragmentContainerActivity activity = ActivityTestHelper.getActivity(mActivityRule.getScenario());

        final MVPFragment fragment = activity.getFragment();
        final SpyPresenter<MVPFragment> presenter = fragment.getPresenter();
        assertNotNull(presenter);

        activity.addInstanceToBackstack();
        assertNotSame(activity.getFragment(), fragment);

        activity.popBackStack();

        assertSame(activity.getFragment(), fragment);
        assertSame(activity.getFragment().getPresenter(), presenter);
    }
}
