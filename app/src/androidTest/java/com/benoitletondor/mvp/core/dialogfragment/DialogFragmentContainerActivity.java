package com.benoitletondor.mvp.core.dialogfragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.benoitletondor.mvp.core.test.R;

import static android.support.test.InstrumentationRegistry.getInstrumentation;

/**
 * An activity that is just a container for {@link MVPDialogFragment}.
 *
 * @author Benoit LETONDOR
 */
public final class DialogFragmentContainerActivity extends AppCompatActivity
{
    private final static String TAG = "dialogfragment";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_container);

        final Fragment fragment = getSupportFragmentManager().findFragmentByTag(TAG);
        if( fragment == null )
        {
            final MVPDialogFragment dialogFragment = new MVPDialogFragment();
            dialogFragment.show(getSupportFragmentManager(), TAG);
        }
    }

    public MVPDialogFragment getFragment()
    {
        return (MVPDialogFragment) getSupportFragmentManager().findFragmentByTag(TAG);
    }

    public void addInstanceToBackstack()
    {
        getInstrumentation().runOnMainSync(new Runnable()
        {
            @Override
            public void run()
            {
                final FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.addToBackStack(System.nanoTime()+"");

                final MVPDialogFragment dialogFragment = new MVPDialogFragment();
                dialogFragment.show(transaction, TAG);

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
