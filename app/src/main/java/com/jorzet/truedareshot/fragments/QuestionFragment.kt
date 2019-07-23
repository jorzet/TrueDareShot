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

import android.app.Activity
import android.content.res.Resources
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.jorzet.truedareshot.R
import com.jorzet.truedareshot.models.enums.QuestionType
import com.jorzet.truedareshot.presenters.question.QuestionPresenter
import com.jorzet.truedareshot.presenters.question.QuestionPresenterImp
import com.jorzet.truedareshot.views.QuestionView

/**
 * @author Jorge Zepeda Tinoco
 * @email jorzet.94@gmail.com
 * @date 08/11/18.
 */

class QuestionFragment: BaseFragment(), QuestionView {

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

    /**
     * Presenter
     */
    private lateinit var mQuestionPresenter: QuestionPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mQuestionPresenter = QuestionPresenterImp()
    }

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

        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (::mQuestionPresenter.isInitialized)
            mQuestionPresenter.create(this)
    }

    private val mTrueButtonListener = View.OnClickListener {

        setFirstQuestionShown(true)
        mQuestionPresenter.updateQuestionView(QuestionType.TRUE)
    }

    private val mShotButtonListener = View.OnClickListener {
        setFirstQuestionShown(true)
        mQuestionPresenter.updateQuestionView(QuestionType.SHOT)
    }

    private val mDareButtonListener = View.OnClickListener {
        setFirstQuestionShown(true)
        mQuestionPresenter.updateQuestionView(QuestionType.DARE)
    }

    override fun getBaseContext(): Activity {
        return this.activity!!
    }

    override fun showQuestionText() {
        if (::mButtonWaitingContainer.isInitialized)
            mButtonWaitingContainer.visibility = View.GONE

        if (::mQuestionLevel.isInitialized)
            mQuestionLevel.visibility = View.GONE

        if (::mQuestionText.isInitialized)
            mQuestionText.visibility = View.VISIBLE
    }

    override fun showButtonIsWaiting() {
        if (::mButtonWaitingContainer.isInitialized)
            mButtonWaitingContainer.visibility = View.VISIBLE

        if (::mQuestionLevel.isInitialized)
            mQuestionLevel.visibility = View.GONE

        if (::mQuestionText.isInitialized)
            mQuestionText.visibility = View.GONE
    }

    override fun updateQuestionType(questionType: String) {
        if (::mQuestionType.isInitialized) {
            mQuestionType.text = questionType
        }
    }

    override fun setCurrentPlayerName(currentPlayer: String) {
        if (::mUserName.isInitialized) {
            mUserName.text = currentPlayer
        }
    }

    override fun setQuestionText(questionText: String) {
        if (::mQuestionText.isInitialized) {
            mQuestionText.text = questionText
        }
    }

    override fun getFragmentResources(): Resources {
        return resources
    }

}