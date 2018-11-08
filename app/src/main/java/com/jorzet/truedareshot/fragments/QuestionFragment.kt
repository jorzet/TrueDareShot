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

class QuestionFragment: BaseFragment() {

    /*
     * Tags
     */
    private val TAG : String = "QuestionFragment"

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        if (container == null) {
            return null
        }

        val rootView = inflater.inflate(R.layout.questions_fragment, container, false)

        return rootView
    }
}