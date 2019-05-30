package com.benoitletondor.mvp.core.sample.scene.fragment.samplefragment;

import androidx.annotation.NonNull;

import com.benoitletondor.mvp.core.view.View;

/**
 * View that displays a simple fizz buzz implementation
 *
 * @author Benoit LETONDOR
 */
public interface SampleFragmentView extends View
{
    /**
     * Set the text to display into the fizz buzz text view
     *
     * @param text the text to display
     */
    void setFizzBuzzText(@NonNull String text);
}
