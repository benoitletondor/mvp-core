package com.benoitletondor.mvp.core;

import android.support.annotation.Nullable;

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
    public void testViewIsNotNullAfterAttached() throws Exception
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
    public void testViewIsNullAfterDetached() throws Exception
    {
        final PresenterTest presenter = new PresenterTest();
        final View view = Mockito.mock(View.class);

        presenter.onViewAttached(view);
        presenter.onViewDetached();

        // Ensure view is not kept after detached
        assertNull(presenter.getView());
    }

// ----------------------------------->

    public static class PresenterTest extends BasePresenterImpl
    {
        @Nullable
        private View getView()
        {
            return mView;
        }
    }
}