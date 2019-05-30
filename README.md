# MVP Core

[![Build Status](https://travis-ci.org/benoitletondor/mvp-core.svg?branch=develop)](https://travis-ci.org/benoitletondor/mvp-core) [ ![Download](https://api.bintray.com/packages/benoitletondor/maven/mvp-core/images/download.svg) ](https://bintray.com/benoitletondor/maven/mvp-core/_latestVersion)

MVP Core is an Android library that contains base classes for implementing MVP into your application.

## Concept

This is a library I built essentially for my own use since I needed the base classes of my MVP implementation in multiple projects.

It provides base classes for `Presenter` and different kind of views:

- `Activity`
- `Fragment`
- `DialogFragment`

The `Presenter` is instantiated and kept across configuration changes using a [`Loader`](https://developer.android.com/guide/components/loaders.html).

**The implementation follows the one of my [Android Studio MVP Template](https://github.com/benoitletondor/Android-Studio-MVP-template) and everything is explained in details in the README, so you should definitely check it.**

## Dependencies

This library provides implementation for views that use the [androidx appcompat](https://developer.android.com/jetpack/androidx/releases/appcompat), so it has a strong dependency on it. 

Presenter implementation is also based on [ViewModel](https://developer.android.com/reference/androidx/lifecycle/ViewModel.html), it also has a strong dependency on it.

## How to use

**Add this line to your gradle file:**

```
implementation 'com.benoitletondor:mvp-core:2.0'
```

To use it, every `Presenter` of your app should extends `BasePresenterImpl` and every view should extend either `BaseMVPActivity`, `BaseMVPFragment` or `BaseMVPDialogFragment`.

Every view should provide a `ViewModelProvider.NewInstanceFactory` that will be called to get a new instance of the presenter when needed.

Here's an example of the implementation within an `Activity`:

```java
public class MainActivity extends BaseMVPActivity<MainViewPresenter, MainView> 
{
	PresenterFactory<MainViewPresenter> mPresenterFactory; // You can inject this (see sample about how to do it)

	@Override
    @NonNull
    protected PresenterFactory<MainViewPresenter> getPresenterFactory()
    {
        return mPresenterFactory;
    }
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
	{
		onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		// You can bind UI events to the presenter (but beware, presenter is still null at this point)
		findViewById(R.id.button).setOnClickListener(new View.OnClickListener()
		{
			public void onClick(View v) 
			{
				if( mPresenter != null )
				{
					mPresenter.onButtonClicked();
				} 
			}
		});
	}
}
```

And here's how the `Presenter` looks like:

```java
public class MainViewPresenterImpl extends BasePresenterImpl<MainView> implements MainViewPresenter
{
	@Override
	public void onStart(boolean viewCreated)
	{
		super.onStart(viewCreated);
		assert mView != null;

		// View is accessible starting from this method until onStop is called
	}

	@Override
	public void onStop()
	{
		assert mView != null;

		// View is still accessible here but won't before next time onStart is called

		super.onStop();
	}

	@Override
	public void onFinish()
	{
		assert mView == null;

		// The presenter will be destroyed now, finish any work started. View is NOT available here

		super.onFinish();
	}
}
```

##### Again, if you're not sure about anything here, a good idea would be to check the [README of the MVP Template that explains everything in details](https://github.com/benoitletondor/Android-Studio-MVP-template).

## Sample code

A sample application is provided (see _sample_ subfolder) implementing the MVP core library into an application. 

You'll find:

- A sample MVP `Activity`: the _main_ scene
- A sample MVP `Fragment` with its containing Activity: the _fragment_ scene
- A sample MVP `DialogFragment`: the `dialog` scene

This sample app also shows how to use [Dagger 2](https://github.com/google/dagger) to inject your presenters into views.

## License

    Copyright 2019 Benoit LETONDOR

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
