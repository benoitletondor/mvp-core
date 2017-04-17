# MVP Core

[![Build Status](https://travis-ci.org/benoitletondor/mvp-core.svg?branch=develop)](https://travis-ci.org/benoitletondor/mvp-core)

MVP Core is an Android library that contains base classes for implementing MVP into your application.

## Concept

This is a library I built essentially for my own use since I needed the base classes of my MVP implementation in multiple projects.

It provides base classes for `Presenter` and different kind of views:

- `Activity`
- `Fragment`
- `DialogFragment`

The `Presenter` is instanciated and kept across configuration changes using a [`Loader`](https://developer.android.com/guide/components/loaders.html). 

**The implementation follows the one of my [Android Studio MVP Template](https://github.com/benoitletondor/Android-Studio-MVP-template) and everything is explained in details in the README, so you should definitely check it.**

## Dependencies

This library provides implementation for views that use the [appcompat-v7 support library](https://developer.android.com/topic/libraries/support-library/features.html), so it has a strong dependency on it. 

## How to use

To use it, every `Presenter` of your app should extends `BasePresenterImpl` and every view should extend either `BaseMVPActivity`, `BaseMVPFragment` or `BaseMVPDialogFragment`.

Every view should provide a `PresenterFactory` that will be called by the `Loader` to get a new instance of the presenter when needed.

Here's an exemple of the implementation within an `Activity`:

```java
public class MainActivity extends BaseMVPActivity<MainViewPresenter, MainView> 
{
	PresenterFactory<MainViewPresenter> mPresenterFactory; // You can inject this

	@Override
	protected PresenterFactory<MainViewPresenter> getPresenterFactory()
	{
		return mPresenterFactory;
	}
	
	@Override
	public void onStart()
	{
		super.onStart();
		assert mPresenter != null;
		
		// Presenter is accessible starting from this method until the activity gets destroyed
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

## License

Sources are available under the Apache 2 licence (See LICENSE for details).
