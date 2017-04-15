package com.benoitletondor.mvp.core.interactor;

/**
 * An interactor is in charge of providing the data to a presenter. It's meant to be called by the
 * presenter to get any kind of data (persisted, or accessed via network calls). The interactor is
 * also in charge of persisting data to disk or in memory.
 * <p>
 * It must not contain any app logic and must be only data getter and setter.
 * <p>
 * This interface is currently empty (on purpose) but this can change in the future.
 *
 * @author Benoit LETONDOR
 */
@SuppressWarnings("unused")
public interface Interactor
{

}
