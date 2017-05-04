package com.benoitletondor.mvp.core.sample.injection;

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


}
