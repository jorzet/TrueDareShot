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
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jorzet.truedareshot.R
import com.jorzet.truedareshot.models.enums.QuestionType
import com.jorzet.truedareshot.presenters.question.QuestionPresenter
import com.jorzet.truedareshot.presenters.question.QuestionPresenterImp
import com.jorzet.truedareshot.views.QuestionView

import kotlinx.android.synthetic.main.questions_fragment.*

/**
 * @author Jorge Zepeda Tinoco
 * @email jorzet.94@gmail.com
 * @date 08/11/18.
 */

/**
 * Constants
 */
private const val TAG: String = "QuestionFragment"

class QuestionFragment: BaseFragment(), QuestionView {

    /**
     * Presenter
     */
    private lateinit var mQuestionPresenter: QuestionPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mQuestionPresenter = QuestionPresenterImp()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.questions_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (::mQuestionPresenter.isInitialized)
            mQuestionPresenter.create(this)

        mTrueButton.setOnClickListener(mTrueButtonListener)
        mShotButton.setOnClickListener(mShotButtonListener)
        mDareButton.setOnClickListener(mDareButtonListener)
    }

    private val mTrueButtonListener = View.OnClickListener {
        Log.d(TAG, "true button")
        setFirstQuestionShown(true)
        mQuestionPresenter.updateQuestionView(QuestionType.TRUE)
    }

    private val mShotButtonListener = View.OnClickListener {
        Log.d(TAG, "shot button")
        setFirstQuestionShown(true)
        mQuestionPresenter.updateQuestionView(QuestionType.SHOT)
    }

    private val mDareButtonListener = View.OnClickListener {
        Log.d(TAG, "dare button")
        setFirstQuestionShown(true)
        mQuestionPresenter.updateQuestionView(QuestionType.DARE)
    }

    override fun getBaseContext(): Activity {
        return this.activity!!
    }

    override fun showQuestionText() {
        if (mButtonWaitingContainer != null)
            mButtonWaitingContainer.visibility = View.GONE

        if (mQuestionLevel != null)
            mQuestionLevel.visibility = View.GONE

        if (mQuestionText != null)
            mQuestionText.visibility = View.VISIBLE
    }

    override fun showButtonIsWaiting() {
        if (mButtonWaitingContainer != null)
            mButtonWaitingContainer.visibility = View.VISIBLE

        if (mQuestionLevel != null)
            mQuestionLevel.visibility = View.GONE

        if (mQuestionText != null)
            mQuestionText.visibility = View.GONE
    }

    override fun updateQuestionType(questionType: String) {
        if (mQuestionType != null) {
            mQuestionType.text = questionType
        }
    }

    override fun setCurrentPlayerName(currentPlayer: String) {
        if (mUserName != null) {
            mUserName.text = currentPlayer
        }
    }

    override fun setQuestionText(questionText: String) {
        if (mQuestionText != null) {
            mQuestionText.text = questionText
        }
    }

    override fun getFragmentResources(): Resources {
        return resources
    }

}