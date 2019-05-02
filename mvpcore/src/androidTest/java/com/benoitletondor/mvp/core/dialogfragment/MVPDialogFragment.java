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

package com.benoitletondor.mvp.core.dialogfragment;

import com.benoitletondor.mvp.core.SpyPresenter;
import com.benoitletondor.mvp.core.view.View;
import com.benoitletondor.mvp.core.view.impl.BaseMVPDialogFragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

/**
 * A simple DialogFragment that exposes its presenter that exposes its view.
 *
 * @author Benoit LETONDOR
 */
public final class MVPDialogFragment extends BaseMVPDialogFragment<SpyPresenter<MVPDialogFragment>, MVPDialogFragment> implements View
{
    @Override
    @SuppressWarnings("unchecked")
    @NonNull
    protected ViewModelProvider.NewInstanceFactory getPresenterFactory()
    {
        return new ViewModelProvider.NewInstanceFactory()
        {
            @NonNull
            @Override
            public <T extends ViewModel> T create(@NonNull Class<T> modelClass)
            {
                return (T) new SpyPresenter();
            }
        };
    }

    @Nullable
    @VisibleForTesting
    public SpyPresenter<MVPDialogFragment> getPresenter()
    {
        return mPresenter;
    }
}
