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

package com.benoitletondor.mvp.core.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;

import com.benoitletondor.mvp.core.SpyPresenter;
import com.benoitletondor.mvp.core.presenter.PresenterFactory;
import com.benoitletondor.mvp.core.view.View;
import com.benoitletondor.mvp.core.view.impl.BaseMVPActivity;

/**
 * A simple activity that exposes its presenter that exposes its view.
 *
 * @author Benoit LETONDOR
 */
public class MVPActivity extends BaseMVPActivity<SpyPresenter<MVPActivity>, MVPActivity> implements View
{
    @Nullable
    @VisibleForTesting
    public SpyPresenter<MVPActivity> getPresenter()
    {
        return mPresenter;
    }

    @Override
    @NonNull
    protected PresenterFactory<SpyPresenter<MVPActivity>> getPresenterFactory()
    {
        return SpyPresenter::new;
    }
}
