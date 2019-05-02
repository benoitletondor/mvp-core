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

package com.benoitletondor.mvp.core;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.benoitletondor.mvp.core.presenter.impl.BasePresenterImpl;
import com.benoitletondor.mvp.core.view.View;

/**
 * A basic implementation of a presenter that spies method call and exposes the view.
 *
 * @author Benoit LETONDOR
 */
public final class SpyPresenter<V extends View> extends BasePresenterImpl<V>
{
    public int mViewAttachedCounter = 0;
    public int mOnStartCounter = 0;
    public int mOnStopCounter = 0;
    public int mOnViewDetachedCounter = 0;
    public int mOnFinishCounter = 0;

// ------------------------------->

    @Override
    public void onViewAttached(@NonNull V view)
    {
        super.onViewAttached(view);

        mViewAttachedCounter++;
    }

    @Override
    public void onStart(boolean viewCreated)
    {
        super.onStart(viewCreated);

        mOnStartCounter++;
    }

    @Override
    public void onStop()
    {
        super.onStop();

        mOnStopCounter++;
    }

    @Override
    public void onViewDetached()
    {
        super.onViewDetached();

        mOnViewDetachedCounter++;
    }

    @Override
    public void onFinish()
    {
        super.onFinish();

        mOnFinishCounter++;
    }

// ------------------------------->

    @Nullable
    public V getView()
    {
        return mView;
    }
}
