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

package com.benoitletondor.mvp.core.view;

import androidx.annotation.UiThread;

/**
 * A view is in charge of displaying the content to the user. The view is called by the presenter
 * and is responsible of sending user events (like touch, keyboard input etc...) back to the presenter.
 * <p>
 * It must not contain any app logic and must strictly contain UI related stuff.
 * <p>
 * This interface is currently empty (on purpose) but this can change in the future.
 *
 * @author Benoit LETONDOR
 */
@SuppressWarnings("unused")
@UiThread
public interface View
{
}
