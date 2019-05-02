/*
 *   Copyright 2019 Benoit LETONDOR
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

package com.benoitletondor.mvp.core.fragment;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AppCompatActivity;
import androidx.test.platform.app.InstrumentationRegistry;

import com.benoitletondor.mvp.core.test.R;

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
        InstrumentationRegistry.getInstrumentation().runOnMainSync(() -> {
            getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, new MVPFragment())
                .addToBackStack(System.nanoTime()+"")
                .commit();

            getSupportFragmentManager().executePendingTransactions();
        });
    }

    public void popBackStack()
    {
        InstrumentationRegistry.getInstrumentation().runOnMainSync(() -> getSupportFragmentManager().popBackStackImmediate());
    }
}
