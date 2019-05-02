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

package com.benoitletondor.mvp.core.activity;

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
 * Instrumentation test that tests the MVP runtime with an Activity.
 *
 * @author Benoit LETONDOR
 */
@RunWith(AndroidJUnit4.class)
public final class ActivityRuntimeTest
{
    @Rule
    public final ActivityScenarioRule<MVPActivity> mActivityRule = new ActivityScenarioRule<>(MVPActivity.class);

    @Test
    public void testPresenterAndViewAreBindAfterStartingActivity()
    {
        final MVPActivity activity = ActivityTestHelper.getActivity(mActivityRule.getScenario());

        assertNotNull(activity.getPresenter());
        assertEquals(activity, activity.getPresenter().getView());
    }

    @Test
    public void testViewIsReleasedAfterStoppingTheActivity()
    {
        final MVPActivity activity = ActivityTestHelper.getActivity(mActivityRule.getScenario());

        ActivityTestHelper.simulateActivityStop(activity);

        assertNotNull(activity.getPresenter());
        assertNull(activity.getPresenter().getView());
    }

    @Test
    public void testPresenterIsReleasedAfterFinishingTheActivity()
    {
        final MVPActivity activity = ActivityTestHelper.getActivity(mActivityRule.getScenario());
        ActivityTestHelper.finishActivity(mActivityRule.getScenario());

        assertNull(activity.getPresenter());
    }

    @Test
    public void testPresenterIsKeptOnRotation()
    {
        final MVPActivity activity = ActivityTestHelper.getActivity(mActivityRule.getScenario());
        final SpyPresenter<MVPActivity> presenter = activity.getPresenter();

        final MVPActivity newActivity =  ActivityTestHelper.getActivity(mActivityRule.getScenario().recreate());

        assertNotSame(newActivity, activity);

        assertNotNull(newActivity.getPresenter());
        assertSame(newActivity.getPresenter(), presenter);
    }
}
