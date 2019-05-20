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
import android.widget.Button
import android.widget.TextView
import com.jorzet.truedareshot.R

/**
 * @author
 * Created by Jorge Zepeda Tinoco on 08/11/18.
 * jorzet.94@gmail.com
 */

class QuestionFragment: BaseFragment() {

    /**
     * Tags
     */
    private val TAG : String = "QuestionFragment"

    /**
     * UI accessors
     */
    private lateinit var mQuestionType: TextView
    private lateinit var mUserName: TextView
    private lateinit var mQuestionLevel: TextView
    private lateinit var mQuestionText: TextView
    private lateinit var mButtonWaitingContainer: View
    private lateinit var mTrueButton: Button
    private lateinit var mShotButton: Button
    private lateinit var mDareButton: Button

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        if (container == null) {
            return null
        }

        val rootView = inflater.inflate(R.layout.questions_fragment, container, false)

        mQuestionType = rootView.findViewById(R.id.tv_question_type)
        mUserName = rootView.findViewById(R.id.tv_user)
        mQuestionLevel = rootView.findViewById(R.id.tv_question_level)
        mQuestionText = rootView.findViewById(R.id.tv_question_text)
        mButtonWaitingContainer = rootView.findViewById(R.id.rl_button_is_waitin_container)
        mTrueButton = rootView.findViewById(R.id.btn_true_image)
        mShotButton = rootView.findViewById(R.id.btn_shot_image)
        mDareButton = rootView.findViewById(R.id.btn_dare_image)

        mTrueButton.setOnClickListener(mTrueButtonListener)
        mShotButton.setOnClickListener(mShotButtonListener)
        mDareButton.setOnClickListener(mDareButtonListener)

        if (isFirstQuestionShown()) {
            showQuestionText()
        } else {
            showButtonIsWaiting()
            mQuestionType.text = resources.getString(R.string.play_text)
        }

        return rootView
    }

    private val mTrueButtonListener = View.OnClickListener {
        showQuestionText()
        setFirstQuestionShown(true)

        mQuestionType.text = resources.getString(R.string.true_text)
    }

    private val mShotButtonListener = View.OnClickListener {
        showQuestionText()
        setFirstQuestionShown(true)

        mQuestionType.text = resources.getString(R.string.shot_text)
    }

    private val mDareButtonListener = View.OnClickListener {
        showQuestionText()
        setFirstQuestionShown(true)

        mQuestionType.text = resources.getString(R.string.dare_text)
    }

    private fun showQuestionText() {
        mButtonWaitingContainer.visibility = View.GONE
        mQuestionLevel.visibility = View.VISIBLE
        mQuestionText.visibility = View.VISIBLE
    }

    private fun showButtonIsWaiting() {
        mButtonWaitingContainer.visibility = View.VISIBLE
        mQuestionLevel.visibility = View.GONE
        mQuestionText.visibility = View.GONE
    }

}