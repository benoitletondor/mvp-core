package com.benoitletondor.mvp.core.view;

import android.support.annotation.UiThread;

/**
 * A view is in charge of displaying the content to the user. The view is called by the presenter
 * and is responsible of sending user events (like touch, keyboard input etc...) back to the presenter.
 * <p>
 * It must not contain any app logic and must strictly contain UI related stuff.
 * <p>
 * This interface is currently empty (on purpose) but this can change in the future.
 *
 * @author Benoit LETONDOR
 */
@SuppressWarnings("unused")
@UiThread
public interface View
{
}
