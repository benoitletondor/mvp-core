package com.benoitletondor.mvp.core.sample.scene.fragment.samplefragment;

import com.benoitletondor.mvp.core.presenter.Presenter;

/**
 * Presenter of the {@link SampleFragmentView}
 *
 * @author Benoit LETONDOR
 */
public interface SampleFragmentPresenter extends Presenter<SampleFragmentView>
{
    /**
     * Called when the user presses the plus button
     */
    void onPlusButtonClicked();
}
