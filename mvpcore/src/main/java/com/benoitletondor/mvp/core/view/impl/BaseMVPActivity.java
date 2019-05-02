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

package com.benoitletondor.mvp.core.view.impl;

import android.os.Bundle;

import com.benoitletondor.mvp.core.presenter.Presenter;
import com.benoitletondor.mvp.core.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

public abstract class BaseMVPActivity<P extends Presenter<V>, V extends View> extends AppCompatActivity
{
    /**
     * The presenter for this view
     */
    @Nullable
    protected P mPresenter;
    /**
     * Is this the first start of the activity (after onCreate)
     */
    private boolean mFirstStart;

// ------------------------------------------->

    @Override
    @SuppressWarnings("unchecked")
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        mFirstStart = true;
        mPresenter = (P) ViewModelProviders.of(this, getPresenterFactory()).get(ViewModel.class);
    }

    @Override
    public void onStart()
    {
        super.onStart();

        if( mPresenter != null )
        {
            doStart();
        }
    }

    @Override
    protected void onStop()
    {
        if( mPresenter != null )
        {
            doStop();
        }

        super.onStop();
    }

    @Override
    protected void onDestroy()
    {
        mPresenter = null;

        super.onDestroy();
    }

// ------------------------------------------->

    /**
     * Call the presenter callbacks for onStart
     */
    @SuppressWarnings("unchecked")
    private void doStart()
    {
        assert mPresenter != null;

        mPresenter.onViewAttached((V) this);

        mPresenter.onStart(mFirstStart);

        mFirstStart = false;
    }

    /**
     * Call the presenter callbacks for onStop
     */
    private void doStop()
    {
        assert mPresenter != null;

        mPresenter.onStop();

        mPresenter.onViewDetached();
    }

    /**
     * Get the presenter factory implementation for this view
     *
     * @return the presenter factory
     */
    @NonNull
    protected abstract ViewModelProvider.NewInstanceFactory getPresenterFactory();
}
