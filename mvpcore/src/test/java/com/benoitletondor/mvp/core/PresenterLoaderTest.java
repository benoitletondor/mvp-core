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

package com.benoitletondor.mvp.core;

import android.content.Context;
import android.support.annotation.NonNull;

import com.benoitletondor.mvp.core.presenter.Presenter;
import com.benoitletondor.mvp.core.presenter.impl.BasePresenterImpl;
import com.benoitletondor.mvp.core.presenter.loader.PresenterFactory;
import com.benoitletondor.mvp.core.presenter.loader.PresenterLoader;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

/**
 * Test the {@link PresenterLoader} to ensure runtime is ok
 *
 * @author Benoit LETONDOR
 */
@RunWith(MockitoJUnitRunner.class)
@SuppressWarnings("unchecked")
public final class PresenterLoaderTest
{
    @Test
    public void testForceLoadIsCallAtFirstStart()
    {
        final PresenterFactory factory = mockFactory();
        final LoaderTest presenterLoader = mockLoader(factory);

        presenterLoader.onStartLoading();

        // Ensure force load is called
        Mockito.verify(presenterLoader).forceLoad();
    }

    @Test
    public void testForceLoadCreatesPresenterAndDeliverIt()
    {
        final PresenterFactory factory = mockFactory();
        final LoaderTest presenterLoader = mockLoader(factory);

        presenterLoader.onForceLoad();

        // Ensure factory is called once
        Mockito.verify(factory).create();
        // Ensure presenter is delivered
        Mockito.verify(presenterLoader).deliverResult(Mockito.any(Presenter.class));
    }

    @Test
    public void testSecondStartDeliverPresenterDirectly()
    {
        final PresenterFactory factory = mockFactory();
        final LoaderTest presenterLoader = mockLoader(factory);

        presenterLoader.onStartLoading();
        presenterLoader.onStartLoading();

        // Ensure factory is called only once
        Mockito.verify(factory).create();
        // Ensure force load is called only once
        Mockito.verify(presenterLoader).forceLoad();
        // Ensure deliver result is called twice (one for first load, one for second)
        Mockito.verify(presenterLoader, Mockito.times(2)).deliverResult(Mockito.any(Presenter.class));
    }

    @Test
    public void testPresenterFinishMethodIsCalledOnLoaderReset()
    {
        final Presenter presenter = Mockito.spy(Presenter.class);
        final PresenterFactory factory = mockFactory(presenter);
        final LoaderTest presenterLoader = mockLoader(factory);

        presenterLoader.onStartLoading();

        // Ensure on finish is not called on start
        Mockito.verify(presenter, Mockito.never()).onFinish();

        presenterLoader.onReset();

        // Ensure on finish is called when loader resets
        Mockito.verify(presenter).onFinish();
    }

    @Test
    public void testPresenterIsNotKeptAfterLoaderReset()
    {
        final PresenterFactory factory = mockFactory();
        final LoaderTest presenterLoader = mockLoader(factory);

        presenterLoader.onStartLoading();
        presenterLoader.onReset();
        presenterLoader.onStartLoading();

        // Ensure factory is called twice
        Mockito.verify(factory, Mockito.times(2)).create();
        // Ensure force load is called twice
        Mockito.verify(presenterLoader, Mockito.times(2)).forceLoad();
        // Ensure deliver result is called twice (one for first load, one for second)
        Mockito.verify(presenterLoader, Mockito.times(2)).deliverResult(Mockito.any(Presenter.class));
    }

// ---------------------------------->

    private LoaderTest mockLoader(@NonNull PresenterFactory factory)
    {
        return Mockito.spy(new LoaderTest(Mockito.mock(Context.class), factory));
    }

    private PresenterFactory mockFactory(@NonNull Presenter presenter)
    {
        final PresenterFactory factory = Mockito.spy(PresenterFactory.class);

        Mockito.when(factory.create()).thenReturn(presenter);

        return factory;
    }

    private PresenterFactory mockFactory()
    {
        return mockFactory(new BasePresenterImpl() {});
    }

    private static class LoaderTest extends PresenterLoader
    {
        LoaderTest(Context context, @NonNull PresenterFactory presenterFactory)
        {
            super(context, presenterFactory);
        }

        @Override
        public void onStartLoading()
        {
            super.onStartLoading();
        }

        @Override
        public void onForceLoad()
        {
            super.onForceLoad();
        }

        @Override
        public void onReset()
        {
            super.onReset();
        }
    }
}
