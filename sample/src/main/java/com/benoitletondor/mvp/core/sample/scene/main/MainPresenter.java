package com.benoitletondor.mvp.core.sample.scene.main;

import com.benoitletondor.mvp.core.presenter.Presenter;

/**
 * Presenter for the {@link MainView}
 *
 * @author Benoit LETONDOR
 */
public interface MainPresenter extends Presenter<MainView>
{
    /**
     * Called when the user clicks on the minus button
     */
    void onMinusButtonClick();

    /**
     * Called when the user clicks on the plus button
     */
    void onPlusButtonClick();

    /**
     * Called when the user clicks on the start fragment activity button
     */
    void onStartFragmentActivityButtonClicked();

    /**
     * Called when the user clicks on the show dialog fragment button
     */
    void onShowDialogFragmentButtonClicked();
}
