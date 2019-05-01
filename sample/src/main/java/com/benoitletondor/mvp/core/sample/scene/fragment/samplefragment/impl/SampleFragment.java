package com.benoitletondor.mvp.core.sample.scene.fragment.samplefragment.impl;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.benoitletondor.mvp.core.presenter.loader.PresenterFactory;
import com.benoitletondor.mvp.core.sample.R;
import com.benoitletondor.mvp.core.sample.scene.fragment.samplefragment.SampleFragmentPresenter;
import com.benoitletondor.mvp.core.sample.scene.fragment.samplefragment.SampleFragmentView;
import com.benoitletondor.mvp.core.view.impl.BaseMVPFragment;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;

/**
 * Implementation of the {@link SampleFragmentView}
 *
 * @author Benoit LETONDOR
 */
public class SampleFragment extends BaseMVPFragment<SampleFragmentPresenter, SampleFragmentView> implements SampleFragmentView
{
    @Inject
    PresenterFactory<SampleFragmentPresenter> mPresenterFactory;

    private TextView mFizzBuzzTextView;

    public SampleFragment()
    {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context)
    {
        AndroidSupportInjection.inject(this);

        super.onAttach(context);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment_sample, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        mFizzBuzzTextView = view.findViewById(R.id.fragment_sample_fizzbuzz_textview);

        view.findViewById(R.id.fragment_sample_plus_button).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if( mPresenter != null )
                {
                    mPresenter.onPlusButtonClicked();
                }
            }
        });
    }

    @Override
    public void onDestroyView()
    {
        mFizzBuzzTextView = null;

        super.onDestroyView();
    }

    @Override
    protected PresenterFactory<SampleFragmentPresenter> getPresenterFactory()
    {
        return mPresenterFactory;
    }

    @Override
    public void setFizzBuzzText(@NonNull String text)
    {
        mFizzBuzzTextView.setText(text);
    }
}
