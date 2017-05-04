package com.benoitletondor.mvp.core.sample.scene.dialog.injection;

import android.support.annotation.NonNull;

import com.benoitletondor.mvp.core.presenter.loader.PresenterFactory;
import com.benoitletondor.mvp.core.sample.scene.dialog.DialogPresenter;
import com.benoitletondor.mvp.core.sample.scene.dialog.impl.DialogPresenterImpl;

import dagger.Module;
import dagger.Provides;

/**
 * Injection module for the dialog scene
 *
 * @author Benoit LETONDOR
 */
@Module
public final class DialogSceneModule
{
    @Provides
    PresenterFactory<DialogPresenter> provideDialogPresenterFactory()
    {
        return new DialogPresenterImplFactory();
    }

    private static final class DialogPresenterImplFactory implements PresenterFactory<DialogPresenter>
    {
        @NonNull
        @Override
        public DialogPresenter create()
        {
            return new DialogPresenterImpl();
        }
    }
}
