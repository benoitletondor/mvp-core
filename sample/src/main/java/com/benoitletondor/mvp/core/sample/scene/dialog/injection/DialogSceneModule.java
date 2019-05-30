package com.benoitletondor.mvp.core.sample.scene.dialog.injection;

import com.benoitletondor.mvp.core.presenter.PresenterFactory;
import com.benoitletondor.mvp.core.sample.scene.dialog.DialogPresenter;
import com.benoitletondor.mvp.core.sample.scene.dialog.impl.DialogPresenterImpl;

import androidx.annotation.NonNull;
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
    protected PresenterFactory<DialogPresenter> getPresenterFactory()
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
