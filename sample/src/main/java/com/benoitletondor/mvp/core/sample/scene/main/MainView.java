package com.benoitletondor.mvp.core.sample.scene.main;

import com.benoitletondor.mvp.core.view.View;

/**
 * Main view, starting point of the application
 *
 * @author Benoit LETONDOR
 */
public interface MainView extends View
{
    /**
     * Set the counter value to display
     *
     * @param value the value to display
     */
    void setCounterValue(int value);

    /**
     * Start the fragment activity
     */
    void startFragmentActivity();

    /**
     * Show the dialog fragment
     */
    void showDialogFragment();
}
