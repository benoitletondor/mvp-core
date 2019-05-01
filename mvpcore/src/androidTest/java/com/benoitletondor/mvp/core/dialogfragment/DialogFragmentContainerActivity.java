/*
 *   Copyright 2017 Benoit LETONDOR
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

package com.benoitletondor.mvp.core.dialogfragment;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.appcompat.app.AppCompatActivity;
import androidx.test.platform.app.InstrumentationRegistry;

import com.benoitletondor.mvp.core.test.R;

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
        InstrumentationRegistry.getInstrumentation().runOnMainSync(new Runnable()
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
        InstrumentationRegistry.getInstrumentation().runOnMainSync(new Runnable()
        {
            @Override
            public void run()
            {
                getSupportFragmentManager().popBackStackImmediate();
            }
        });
    }
}
