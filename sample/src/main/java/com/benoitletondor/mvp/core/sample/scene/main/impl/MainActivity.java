package com.benoitletondor.mvp.core.sample.scene.main.impl;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.benoitletondor.mvp.core.presenter.loader.PresenterFactory;
import com.benoitletondor.mvp.core.sample.R;
import com.benoitletondor.mvp.core.sample.scene.dialog.impl.DialogFragment;
import com.benoitletondor.mvp.core.sample.scene.fragment.base.impl.FragmentActivity;
import com.benoitletondor.mvp.core.sample.scene.main.MainPresenter;
import com.benoitletondor.mvp.core.sample.scene.main.MainView;
import com.benoitletondor.mvp.core.view.impl.BaseMVPActivity;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

/**
 * Implementation of {@link MainView}
 *
 * @author Benoit LETONDOR
 */
public final class MainActivity extends BaseMVPActivity<MainPresenter, MainView> implements MainView
{
    @Inject
    PresenterFactory<MainPresenter> mPresenterFactory;

    private TextView mCounterTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        AndroidInjection.inject(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mCounterTextView = (TextView) findViewById(R.id.activity_main_counter_textview);

        findViewById(R.id.activity_main_minus_button).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if( mPresenter != null )
                {
                    mPresenter.onMinusButtonClick();
                }
            }
        });

        findViewById(R.id.activity_main_plus_button).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if( mPresenter != null )
                {
                    mPresenter.onPlusButtonClick();
                }
            }
        });

        findViewById(R.id.activity_main_start_fragment_activity_button).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if( mPresenter != null )
                {
                    mPresenter.onStartFragmentActivityButtonClicked();
                }
            }
        });

        findViewById(R.id.activity_main_start_dialog_fragment_button).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if( mPresenter != null )
                {
                    mPresenter.onShowDialogFragmentButtonClicked();
                }
            }
        });
    }

    @Override
    protected PresenterFactory<MainPresenter> getPresenterFactory()
    {
        return mPresenterFactory;
    }

    @Override
    public void setCounterValue(int value)
    {
        mCounterTextView.setText(getString(R.string.activity_main_counter, value));
    }

    @Override
    public void startFragmentActivity()
    {
        final Intent intent = new Intent(this, FragmentActivity.class);
        startActivity(intent);
    }

    @Override
    public void showDialogFragment()
    {
        new DialogFragment().show(getSupportFragmentManager(), null);
    }
}
