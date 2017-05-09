package com.benoitletondor.mvp.core.sample.injection;

import com.benoitletondor.mvp.core.sample.scene.dialog.impl.DialogFragment;
import com.benoitletondor.mvp.core.sample.scene.dialog.injection.DialogSceneModule;
import com.benoitletondor.mvp.core.sample.scene.fragment.base.impl.FragmentActivity;
import com.benoitletondor.mvp.core.sample.scene.fragment.base.injection.FragmentBaseSceneModule;
import com.benoitletondor.mvp.core.sample.scene.fragment.samplefragment.impl.SampleFragment;
import com.benoitletondor.mvp.core.sample.scene.fragment.samplefragment.injection.FragmentSampleSceneModule;
import com.benoitletondor.mvp.core.sample.scene.main.impl.MainActivity;
import com.benoitletondor.mvp.core.sample.scene.main.injection.MainSceneModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Module in charge of injecting all scenes of the app
 *
 * @author Benoit LETONDOR
 */
@Module
abstract class SceneInjectorsModule
{
    // Main scene
    @ActivityScope
    @ContributesAndroidInjector(modules = {
        MainSceneModule.class
    })
    abstract MainActivity MainActivityInjector();

    // Base fragment scene
    @ActivityScope
    @ContributesAndroidInjector(modules = {
        FragmentBaseSceneModule.class
    })
    abstract FragmentActivity FragmentActivityInjector();

    // Sample fragment scene
    @FragmentScope
    @ContributesAndroidInjector(modules = {
        FragmentSampleSceneModule.class
    })
    abstract SampleFragment SampleFragmentInjector();

    // Dialog scene
    @FragmentScope
    @ContributesAndroidInjector(modules = {
        DialogSceneModule.class
    })
    abstract DialogFragment DialogFragmentInjector();
}
