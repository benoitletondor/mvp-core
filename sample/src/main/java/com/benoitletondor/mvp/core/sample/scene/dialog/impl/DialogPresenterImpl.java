package com.benoitletondor.mvp.core.sample.scene.dialog.impl;


import com.benoitletondor.mvp.core.presenter.impl.BasePresenterImpl;
import com.benoitletondor.mvp.core.sample.scene.dialog.DialogPresenter;
import com.benoitletondor.mvp.core.sample.scene.dialog.DialogView;

/**
 * Implementation of the {@link DialogPresenter}
 *
 * @author Benoit LETONDOR
 */
public final class DialogPresenterImpl extends BasePresenterImpl<DialogView> implements DialogPresenter
{
    @Override
    public void onCloseButtonClicked()
    {
        if( mView != null )
        {
            mView.close();
        }
    }
}
