/*
 *   Copyright 2017 Benoit LETONDOR
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

package com.benoitletondor.mvp.core.fragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;

import com.benoitletondor.mvp.core.SpyPresenter;
import com.benoitletondor.mvp.core.presenter.loader.PresenterFactory;
import com.benoitletondor.mvp.core.view.View;
import com.benoitletondor.mvp.core.view.impl.BaseMVPFragment;

/**
 * A simple fragment that exposes its presenter that exposes its view.
 *
 * @author Benoit LETONDOR
 */
public final class MVPFragment extends BaseMVPFragment<SpyPresenter<MVPFragment>, MVPFragment> implements View
{
    @Override
    protected PresenterFactory<SpyPresenter<MVPFragment>> getPresenterFactory()
    {
        return new PresenterFactory<SpyPresenter<MVPFragment>>()
        {
            @NonNull
            @Override
            public SpyPresenter<MVPFragment> create()
            {
                return new SpyPresenter<>();
            }
        };
    }

    @Nullable
    @VisibleForTesting
    public SpyPresenter<MVPFragment> getPresenter()
    {
        return mPresenter;
    }
}
