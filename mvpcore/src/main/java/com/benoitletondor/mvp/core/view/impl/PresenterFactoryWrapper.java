package com.benoitletondor.mvp.core.view.impl;

import com.benoitletondor.mvp.core.presenter.PresenterFactory;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

class PresenterFactoryWrapper extends ViewModelProvider.NewInstanceFactory
{
    @NonNull
    private final PresenterFactory<?> mPresenterFactory;

    PresenterFactoryWrapper(@NonNull PresenterFactory<?> presenterFactory)
    {
        mPresenterFactory = presenterFactory;
    }

    @NonNull
    @Override
    @SuppressWarnings("unchecked")
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass)
    {
        return (T) mPresenterFactory.create();
    }
}
