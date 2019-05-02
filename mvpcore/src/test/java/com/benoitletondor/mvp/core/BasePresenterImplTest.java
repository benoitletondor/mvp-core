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

import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;

import com.benoitletondor.mvp.core.presenter.impl.BasePresenterImpl;
import com.benoitletondor.mvp.core.view.View;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;

/**
 * Test the {@link BasePresenterImpl} to ensure runtime is ok
 *
 * @author Benoit LETONDOR
 */
@RunWith(MockitoJUnitRunner.class)
@SuppressWarnings("unchecked")
public final class BasePresenterImplTest
{
    @Test
    public void testViewIsNotNullAfterAttached()
    {
        final PresenterTest presenter = new PresenterTest();
        final View view = Mockito.mock(View.class);

        presenter.onViewAttached(view);

        // Ensure view is kept by the presenter
        assertNotNull(presenter.getView());
        // Ensure it's the correct view
        assertEquals(view, presenter.getView());
    }

    @Test
    public void testViewIsNullAfterDetached()
    {
        final PresenterTest presenter = new PresenterTest();
        final View view = Mockito.mock(View.class);

        presenter.onViewAttached(view);
        presenter.onViewDetached();

        // Ensure view is not kept after detached
        assertNull(presenter.getView());
    }

    @Test
    public void testOnFinishIsCalledWhenViewModelOnClearedIsCalled() {
        final PresenterTest presenter = new PresenterTest();

        presenter.onCleared();

        // Ensure on finish is called correctly
        assertTrue(presenter.mOnFinishCalled);
    }

// ----------------------------------->

    public static class PresenterTest extends BasePresenterImpl
    {
        private boolean mOnFinishCalled = false;

        @Override
        @VisibleForTesting
        public void onCleared()
        {
            super.onCleared();
        }

        @Nullable
        private View getView()
        {
            return mView;
        }

        @Override
        public void onFinish()
        {
            mOnFinishCalled = true;

            super.onFinish();
        }
    }
}