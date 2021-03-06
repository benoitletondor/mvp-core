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

/**
 * Instrumentation test that tests the presenter runtime with an Activity.
 *
 * @author Benoit LETONDOR
 */
@RunWith(AndroidJUnit4.class)
public class PresenterRuntimeTest
{
    @Rule
    public final ActivityScenarioRule<MVPActivity> mActivityRule = new ActivityScenarioRule<>(MVPActivity.class);

    @Test
    public void testPresenterRuntime()
    {
        final MVPActivity activity = ActivityTestHelper.getActivity(mActivityRule.getScenario());
        final SpyPresenter<MVPActivity> presenter = activity.getPresenter();

        assertNotNull(presenter);

        // Test first launch
        assertEquals(1, presenter.mOnStartCounter);
        assertEquals(1, presenter.mViewAttachedCounter);
        assertEquals(0, presenter.mOnStopCounter);
        assertEquals(0, presenter.mOnViewDetachedCounter);
        assertEquals(0, presenter.mOnFinishCounter);

        // Test stop
        ActivityTestHelper.simulateActivityStop(activity);
        assertEquals(1, presenter.mOnStartCounter);
        assertEquals(1, presenter.mViewAttachedCounter);
        assertEquals(1, presenter.mOnStopCounter);
        assertEquals(1, presenter.mOnViewDetachedCounter);
        assertEquals(0, presenter.mOnFinishCounter);

        // Test restart
        ActivityTestHelper.simulateActivityStart(activity);
        assertEquals(2, presenter.mOnStartCounter);
        assertEquals(2, presenter.mViewAttachedCounter);
        assertEquals(1, presenter.mOnStopCounter);
        assertEquals(1, presenter.mOnViewDetachedCounter);
        assertEquals(0, presenter.mOnFinishCounter);

        // Test recreate
        final MVPActivity newActivity = ActivityTestHelper.getActivity(mActivityRule.getScenario().recreate());
        assertEquals(presenter, newActivity.getPresenter());

        assertEquals(3, presenter.mOnStartCounter);
        assertEquals(3, presenter.mViewAttachedCounter);
        assertEquals(2, presenter.mOnStopCounter);
        assertEquals(2, presenter.mOnViewDetachedCounter);
        assertEquals(0, presenter.mOnFinishCounter);

        // Test finish
        ActivityTestHelper.finishActivity(mActivityRule.getScenario());
        assertEquals(3, presenter.mOnStartCounter);
        assertEquals(3, presenter.mViewAttachedCounter);
        assertEquals(3, presenter.mOnStopCounter);
        assertEquals(3, presenter.mOnViewDetachedCounter);
        assertEquals(1, presenter.mOnFinishCounter);
    }

}
