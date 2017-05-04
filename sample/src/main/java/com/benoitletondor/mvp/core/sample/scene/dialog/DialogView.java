package com.benoitletondor.mvp.core.sample.scene.dialog;

import com.benoitletondor.mvp.core.view.View;

/**
 * View that displays a dialog to the user
 *
 * @author Benoit LETONDOR
 */
public interface DialogView extends View
{
    /**
     * Close the dialog
     */
    void close();
}
