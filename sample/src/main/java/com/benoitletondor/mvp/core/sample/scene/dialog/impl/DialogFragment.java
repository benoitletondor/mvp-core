package com.benoitletondor.mvp.core.sample.scene.dialog.impl;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.benoitletondor.mvp.core.presenter.loader.PresenterFactory;
import com.benoitletondor.mvp.core.sample.R;
import com.benoitletondor.mvp.core.sample.scene.dialog.DialogPresenter;
import com.benoitletondor.mvp.core.sample.scene.dialog.DialogView;
import com.benoitletondor.mvp.core.view.impl.BaseMVPDialogFragment;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;

/**
 * Implementation of the {@link DialogView}
 *
 * @author Benoit LETONDOR
 */
public final class DialogFragment extends BaseMVPDialogFragment<DialogPresenter, DialogView> implements DialogView
{
    @Inject
    PresenterFactory<DialogPresenter> mPresenterFactory;

    public DialogFragment()
    {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context)
    {
        AndroidSupportInjection.inject(this);

        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment_dialog, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.fragment_dialog_close_button).setOnClickListener(view1 -> {
            if( mPresenter != null )
            {
                mPresenter.onCloseButtonClicked();
            }
        });
    }

    @Override
    protected PresenterFactory<DialogPresenter> getPresenterFactory()
    {
        return mPresenterFactory;
    }

    @Override
    public void close()
    {
        dismiss();
    }
}
