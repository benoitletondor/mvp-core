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

package com.benoitletondor.mvp.core.view.impl;

import android.annotation.SuppressLint;
import android.support.annotation.Nullable;

import com.benoitletondor.mvp.core.presenter.Presenter;
import com.benoitletondor.mvp.core.presenter.loader.PresenterFactory;
import com.benoitletondor.mvp.core.view.View;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * Test the {@link BaseMVPFragment} to ensure runtime is ok
 *
 * @author Benoit LETONDOR
 */
@RunWith(MockitoJUnitRunner.class)
@SuppressWarnings("unchecked")
public final class BaseMVPFragmentTest
{
    @Test
    public void testPresenterIsKeptAfterLoadFinished()
    {
        final Presenter presenter = Mockito.spy(Presenter.class);
        final FragmentTest activity = Mockito.spy(new FragmentTest());

        activity.onLoadFinished(null, presenter);

        // Verify presenter is not null after load finish
        assertNotNull(activity.getPresenter());
    }

    @Test
    public void testPresenterIsReleasedWhenLoaderResets()
    {
        final Presenter presenter = Mockito.spy(Presenter.class);
        final FragmentTest activity = Mockito.spy(new FragmentTest());

        activity.onLoadFinished(null, presenter);
        activity.onLoaderReset(null);

        // Verify presenter is null after reset
        assertNull(activity.getPresenter());
    }

    @Test
    public void testPresenterLifeCycleIsRespectedOnStart()
    {
        final Presenter presenter = Mockito.spy(Presenter.class);
        final FragmentTest activity = Mockito.spy(new FragmentTest());

        activity.onLoadFinished(null, presenter);
        activity.onStart();

        // Verify onViewAttached and onStart are called in right order
        final InOrder inOrder = Mockito.inOrder(presenter);
        inOrder.verify(presenter).onViewAttached(Mockito.eq(activity));
        inOrder.verify(presenter).onStart(Mockito.anyBoolean());
    }

    @Test
    public void testPresenterLifeCycleIsRespectedOnStop()
    {
        final Presenter presenter = Mockito.spy(Presenter.class);
        final FragmentTest activity = Mockito.spy(new FragmentTest());

        activity.onLoadFinished(null, presenter);
        activity.onStart();
        activity.onStop();

        // Verify onStop and onViewDetached are called in right order
        final InOrder inOrder = Mockito.inOrder(presenter);
        inOrder.verify(presenter).onStop();
        inOrder.verify(presenter).onViewDetached();
    }

    @Test
    public void testPresenterStartLifecycleIsCalledWhenLoaderIsNotReadyOnFragmentStart()
    {
        final Presenter presenter = Mockito.spy(Presenter.class);
        final FragmentTest activity = Mockito.spy(new FragmentTest());

        activity.mNeedToCallStart.set(true);
        activity.onLoadFinished(null, presenter);

        // Verify doStart is called on load finish when mNeedToCallStart is true
        Mockito.verify(activity).doStart();
    }

    @Test
    public void testPresenterStartLifecycleIsNotCalledWhenLoaderIsReadyOnFragmentStart()
    {
        final Presenter presenter = Mockito.spy(Presenter.class);
        final FragmentTest activity = Mockito.spy(new FragmentTest());

        activity.mNeedToCallStart.set(false);
        activity.onLoadFinished(null, presenter);

        // Verify doStart is not called on load finish when mNeedToCallStart is false
        Mockito.verify(activity, Mockito.never()).doStart();
    }

// ---------------------------------->

    @SuppressLint("ValidFragment")
    private static class FragmentTest extends BaseMVPFragment implements View
    {
        @Override
        protected PresenterFactory getPresenterFactory()
        {
            return null;
        }

        @Nullable
        private Presenter getPresenter()
        {
            return mPresenter;
        }

        @SuppressLint("MissingSuperCall")
        @Override
        public void onStart()
        {
            if( mPresenter != null )
            {
                doStart();
            }
        }

        @SuppressLint("MissingSuperCall")
        @Override
        public void onStop()
        {
            if( mPresenter != null )
            {
                doStop();
            }
        }
    }
}
