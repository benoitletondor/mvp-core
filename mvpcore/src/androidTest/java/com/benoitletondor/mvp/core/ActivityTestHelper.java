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
