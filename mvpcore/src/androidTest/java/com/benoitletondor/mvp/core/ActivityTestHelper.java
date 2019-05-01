package com.benoitletondor.mvp.core;

import android.app.Activity;

import java.util.concurrent.atomic.AtomicReference;

import androidx.annotation.NonNull;
import androidx.lifecycle.Lifecycle;
import androidx.test.core.app.ActivityScenario;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.platform.app.InstrumentationRegistry;

public class ActivityTestHelper
{
    public static void simulateActivityStop(@NonNull final Activity activity)
    {
        InstrumentationRegistry.getInstrumentation().runOnMainSync(new Runnable()
        {
            @Override
            public void run()
            {
                InstrumentationRegistry.getInstrumentation().callActivityOnPause(activity);
                InstrumentationRegistry.getInstrumentation().callActivityOnStop(activity);
            }
        });

        InstrumentationRegistry.getInstrumentation().waitForIdleSync();
    }

    public static void simulateActivityStart(@NonNull final Activity activity) {
        InstrumentationRegistry.getInstrumentation().runOnMainSync(new Runnable()
        {
            @Override
            public void run()
            {
                InstrumentationRegistry.getInstrumentation().callActivityOnStart(activity);
                InstrumentationRegistry.getInstrumentation().callActivityOnResume(activity);
            }
        });

        InstrumentationRegistry.getInstrumentation().waitForIdleSync();
    }

    public static void finishActivity(ActivityScenario scenario)
    {
        scenario.moveToState(Lifecycle.State.DESTROYED);
    }

    public static <A extends Activity> A getActivity(ActivityScenario<A> scenario) {
        final AtomicReference<A> activityRef = new AtomicReference<>();
        scenario.onActivity(new ActivityScenario.ActivityAction<A>()
        {
            @Override
            public void perform(A activity)
            {
                activityRef.set(activity);
            }
        });

        return activityRef.get();
    }
}
