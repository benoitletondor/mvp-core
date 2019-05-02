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
