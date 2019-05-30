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

package com.benoitletondor.mvp.core.presenter.impl;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModel;

import com.benoitletondor.mvp.core.presenter.Presenter;
import com.benoitletondor.mvp.core.view.View;

/**
 * Abstract presenter implementation that contains base implementation for other presenters.
 * Subclasses must call super for all {@link Presenter} method overriding.
 *
 * @author Benoit LETONDOR
 */
@SuppressWarnings("unused")
public abstract class BasePresenterImpl<V extends View> extends ViewModel implements Presenter<V>
{
    /**
     * The view, will be null if the presenter isn't attached to a view
     */
    @Nullable
    protected V mView;

// ------------------------------------------->

    @Override
    protected void onCleared()
    {
        onFinish();

        super.onCleared();
    }

// ------------------------------------------->

    @Override
    public void onViewAttached(@NonNull V view)
    {
        mView = view;
    }


    @Override
    public void onStart(boolean viewCreated)
    {
        // No-op but subclasses can override it
    }

    @Override
    public void onStop()
    {
        // No-op but subclasses can override it
    }

    @Override
    public void onViewDetached()
    {
        mView = null;
    }

    @Override
    public void onFinish()
    {
        // No-op but subclasses can override it
    }
}
