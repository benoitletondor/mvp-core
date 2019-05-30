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

package com.benoitletondor.mvp.core;

import android.app.Activity;

import java.util.concurrent.atomic.AtomicReference;

import androidx.annotation.NonNull;
import androidx.lifecycle.Lifecycle;
import androidx.test.core.app.ActivityScenario;
import androidx.test.platform.app.InstrumentationRegistry;

public class ActivityTestHelper
{
    public static void simulateActivityStop(@NonNull final Activity activity)
    {
        InstrumentationRegistry.getInstrumentation().runOnMainSync(() -> {
            InstrumentationRegistry.getInstrumentation().callActivityOnPause(activity);
            InstrumentationRegistry.getInstrumentation().callActivityOnStop(activity);
        });

        InstrumentationRegistry.getInstrumentation().waitForIdleSync();
    }

    public static void simulateActivityStart(@NonNull final Activity activity)
    {
        InstrumentationRegistry.getInstrumentation().runOnMainSync(() -> {
            InstrumentationRegistry.getInstrumentation().callActivityOnStart(activity);
            InstrumentationRegistry.getInstrumentation().callActivityOnResume(activity);
        });

        InstrumentationRegistry.getInstrumentation().waitForIdleSync();
    }

    public static void finishActivity(ActivityScenario scenario)
    {
        scenario.moveToState(Lifecycle.State.DESTROYED);
    }

    public static <A extends Activity> A getActivity(ActivityScenario<A> scenario) {
        final AtomicReference<A> activityRef = new AtomicReference<>();
        scenario.onActivity(activityRef::set);

        return activityRef.get();
    }
}
