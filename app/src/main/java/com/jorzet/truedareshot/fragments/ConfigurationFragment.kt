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

package com.jorzet.truedareshot.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jorzet.truedareshot.R

/**
 * @author
 * Created by Jorge Zepeda Tinoco on 08/11/18.
 * jorzet.94@gmail.com
 */

class ConfigurationFragment: BaseFragment() {

    /*
     * Tags
     */
    private val TAG : String = "ConfigurationFragment"

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        if (container == null) {
            return null
        }

        val rootView = inflater.inflate(R.layout.configuration_fragment, container, false)

        return rootView
    }
}