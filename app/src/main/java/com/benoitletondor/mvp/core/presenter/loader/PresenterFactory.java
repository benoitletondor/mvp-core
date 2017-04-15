package com.benoitletondor.mvp.core.presenter.loader;

import android.support.annotation.NonNull;

import com.benoitletondor.mvp.core.presenter.Presenter;

/**
 * Factory to create a presenter.
 *
 * @author Benoit LETONDOR
 */
@SuppressWarnings("unused")
public interface PresenterFactory<T extends Presenter>
{
    @NonNull
    T create();
}
