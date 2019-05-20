/*
 * Copyright [2019] [Jorge Zepeda Tinoco]
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.jorzet.truedareshot.utils

import com.jorzet.truedareshot.R

class ImagesUtil {
    companion object {
        /*
         * Define all icon resorces for bottom tab layout
         */
        val mBottomSelectedIcons = intArrayOf(
            R.drawable.ic_developers_orange,
            R.drawable.ic_players_orange,
            R.drawable.ic_add_question_orange,
            R.drawable.ic_configuration_orange,
            R.drawable.ic_add_dare_orange
        )

        val mBottomUnselectedIcons = intArrayOf(
            R.drawable.ic_developers_gray,
            R.drawable.ic_players_gray,
            R.drawable.ic_add_question_gray,
            R.drawable.ic_configuration_gray,
            R.drawable.ic_add_dare_gray
        )
    }
}