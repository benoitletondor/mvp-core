package com.benoitletondor.mvp.core.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.benoitletondor.mvp.core.test.R;

import static android.support.test.InstrumentationRegistry.getInstrumentation;

/**
 * An activity that is just a container for {@link MVPFragment}.
 *
 * @author Benoit LETONDOR
 */
public final class FragmentContainerActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_container);

        final Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        if( fragment == null )
        {
            getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, new MVPFragment())
                .commitNow();
        }
    }

    public MVPFragment getFragment()
    {
        return (MVPFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_container);
    }

    public void addInstanceToBackstack()
    {
        getInstrumentation().runOnMainSync(new Runnable()
        {
            @Override
            public void run()
            {
                getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new MVPFragment())
                    .addToBackStack(System.nanoTime()+"")
                    .commit();

                getSupportFragmentManager().executePendingTransactions();
            }
        });
    }

    public void popBackStack()
    {
        getInstrumentation().runOnMainSync(new Runnable()
        {
            @Override
            public void run()
            {
                getSupportFragmentManager().popBackStackImmediate();
            }
        });
    }
}
