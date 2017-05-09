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

package com.benoitletondor.mvp.core.fragment;

import android.support.test.runner.AndroidJUnit4;

import com.benoitletondor.mvp.core.ActivityLifecycleTestRule;
import com.benoitletondor.mvp.core.SpyPresenter;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * Instrumentation test that tests the MVP runtime with a Fragment.
 *
 * @author Benoit LETONDOR
 */
@RunWith(AndroidJUnit4.class)
public final class FragmentRuntimeTest
{
    @Rule
    public final ActivityLifecycleTestRule<FragmentContainerActivity> mActivityRule
        = new ActivityLifecycleTestRule<>(FragmentContainerActivity.class);

    @Test
    public void testPresenterAndViewAreBindAfterStartingActivity()
    {
        assertNotNull(mActivityRule.getActivity().getFragment().getPresenter());
        assertEquals(mActivityRule.getActivity().getFragment(), mActivityRule.getActivity().getFragment().getPresenter().getView());
    }

    @Test
    public void testViewIsReleasedAfterStoppingTheActivity()
    {
        mActivityRule.simulateActivityStop();

        assertNotNull(mActivityRule.getActivity().getFragment().getPresenter());
        assertNull(mActivityRule.getActivity().getFragment().getPresenter().getView());
    }

    @Test
    public void testPresenterIsReleasedAfterFinishingTheActivity() throws Exception
    {
        final MVPFragment fragment = mActivityRule.getActivity().getFragment();
        mActivityRule.finishCurrentActivity();

        assertNull(fragment.getPresenter());
    }

    @Test
    public void testPresenterIsKeptOnRotation() throws Exception
    {
        final MVPFragment fragment = mActivityRule.getActivity().getFragment();
        final SpyPresenter<MVPFragment> presenter = fragment.getPresenter();

        mActivityRule.recreateCurrentActivity();

        assertTrue(mActivityRule.getActivity().getFragment() != fragment);

        assertNotNull(mActivityRule.getActivity().getFragment().getPresenter());
        assertTrue(mActivityRule.getActivity().getFragment().getPresenter() == presenter);
    }

    @Test
    public void testViewIsReleasedWhenInBackstack()
    {
        final MVPFragment fragment = mActivityRule.getActivity().getFragment();
        final SpyPresenter<MVPFragment> presenter = fragment.getPresenter();
        assertNotNull(presenter);

        mActivityRule.getActivity().addInstanceToBackstack();
        assertTrue(mActivityRule.getActivity().getFragment() != fragment);

        assertNull(presenter.getView());

        assertNotNull(mActivityRule.getActivity().getFragment().getPresenter());
        assertTrue(mActivityRule.getActivity().getFragment().getPresenter() != presenter);
        assertTrue(mActivityRule.getActivity().getFragment().getPresenter().getView() != fragment);
    }

    @Test
    public void testPresenterIsKeptWhenPoppingBackstack()
    {
        final MVPFragment fragment = mActivityRule.getActivity().getFragment();
        final SpyPresenter<MVPFragment> presenter = fragment.getPresenter();
        assertNotNull(presenter);

        mActivityRule.getActivity().addInstanceToBackstack();
        assertTrue(mActivityRule.getActivity().getFragment() != fragment);

        mActivityRule.getActivity().popBackStack();

        assertTrue(mActivityRule.getActivity().getFragment() == fragment);
        assertTrue(mActivityRule.getActivity().getFragment().getPresenter() == presenter);
    }
}
