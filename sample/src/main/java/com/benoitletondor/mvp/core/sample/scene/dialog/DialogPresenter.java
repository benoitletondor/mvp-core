package com.benoitletondor.mvp.core.sample.scene.dialog;

import com.benoitletondor.mvp.core.presenter.Presenter;

/**
 * Presenter for the {@link DialogView}
 *
 * @author Benoit LETONDOR
 */
public interface DialogPresenter extends Presenter<DialogView>
{
    /**
     * Called when the user clicks the close button
     */
    void onCloseButtonClicked();
}
