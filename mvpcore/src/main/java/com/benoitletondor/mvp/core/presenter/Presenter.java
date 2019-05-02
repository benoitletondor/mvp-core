/*
 *   Copyright 2019 Benoit LETONDOR
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */

package com.benoitletondor.mvp.core.presenter;

import androidx.annotation.NonNull;

import com.benoitletondor.mvp.core.view.View;

/**
 * A presenter is in charge of managing the view, getting the data via one or many interactors and
 * handling runtime events. It's the central piece of any scene of the app.
 * <p>
 * <b>Important note:</b> The presenter is persisted across configuration changes, so it's important
 * that it doesn't contain any link to the view.
 *
 * @author Benoit LETONDOR
 */
public interface Presenter<V extends View>
{
    /**
     * Called when the view is attached to the presenter. Presenters should normally not use this
     * method since it's only used to link the view to the presenter which is done by the base impl.
     *
     * @param view the view
     */
    void onViewAttached(@NonNull V view);

    /**
     * Called every time the view starts, the view is guarantee to be not null starting at this
     * method, until {@link #onStop()} is called.
     *
     * @param viewCreated true if it's the has been just created, false if its just restarting after a stop
     */
    @SuppressWarnings({"EmptyMethod", "UnusedParameters"})
    void onStart(boolean viewCreated);

    /**
     * Called every time the view stops. After this method, the view will be null.
     */
    @SuppressWarnings("EmptyMethod")
    void onStop();

    /**
     * Called when the view is detached from the presenter. Presenters should normally not use this
     * method since it's only used to unlink the view from the presenter which is done by the base impl.
     */
    void onViewDetached();

    /**
     * Called when the presenter is definitely destroyed, you should use this method only to release
     * any resource used by the presenter (cancel HTTP requests, close database connection...)
     */
    @SuppressWarnings("EmptyMethod")
    void onFinish();
}
