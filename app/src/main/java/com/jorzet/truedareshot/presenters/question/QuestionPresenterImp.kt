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

package com.jorzet.truedareshot.presenters.question

import com.jorzet.truedareshot.R
import com.jorzet.truedareshot.models.Player
import com.jorzet.truedareshot.models.Question
import com.jorzet.truedareshot.managers.firebase.FirebaseRequestManager
import com.jorzet.truedareshot.managers.sharedpreferences.SharedPreferencesManager
import com.jorzet.truedareshot.models.enums.QuestionType
import com.jorzet.truedareshot.views.QuestionView
import kotlin.random.Random

/**
 * @author Jorge Zepeda Tinoco
 * @email jorzet.94@gmail.com
 * @date 16/07/19
 */

class QuestionPresenterImp: QuestionPresenter {

    /**
     * View
     */
    private lateinit var mQuestionView: QuestionView

    /**
     * Manager
     */
    private var mSharedPreferencesManager: SharedPreferencesManager? = null
    private var mRequestManager : FirebaseRequestManager? = null

    /**
     * Model
     */
    private var mConfiguration: HashMap<String, HashMap<String, Boolean>>? = null
    private var mQuestions : List<Question>? = arrayListOf()

    override fun create(view: QuestionView) {
        mQuestionView = view

        // init manager
        mSharedPreferencesManager = SharedPreferencesManager.getInstance(view.getBaseContext())
        mRequestManager = FirebaseRequestManager.getInstance(view.getBaseContext())

        mConfiguration = mSharedPreferencesManager?.getConfiguration()

        // init
        initializeView()
    }

    private fun initializeView() {
        if (mSharedPreferencesManager!!.isFirstQuestionShown()) {
            mQuestionView.showQuestionText()
        } else {
            mQuestionView.showButtonIsWaiting()
            mQuestionView.updateQuestionType(mQuestionView.getBaseContext().resources.getString(R.string.play_text))
        }

    }

    override fun destroy() {
        mSharedPreferencesManager?.destroy()
        mRequestManager?.destroy()
    }

    override fun getCurrentPlayer(): Player? {
        if (mSharedPreferencesManager != null) {

        }
        return null
    }

    override fun requestGroup() {

    }

    override fun updateQuestionView(questionType: QuestionType) {
        mQuestionView.showQuestionText()

        mQuestions = mSharedPreferencesManager?.getQuestions(questionType)
        getRandomQuestion()

        when(questionType) {
            QuestionType.TRUE -> {
                mQuestionView.updateQuestionType(mQuestionView.getFragmentResources().getString(R.string.true_text))
            }
            QuestionType.DARE -> {
                mQuestionView.updateQuestionType(mQuestionView.getFragmentResources().getString(R.string.dare_text))
            }
            QuestionType.SHOT -> {
                mQuestionView.updateQuestionType(mQuestionView.getFragmentResources().getString(R.string.shot_text))
            }
        }
    }

    override fun getRandomQuestion() {
        if (!mQuestions.isNullOrEmpty()) {
            val randomIndex = (0 until ((mQuestions?.size )?: 0)).random()

            val question: Question? = mQuestions!![randomIndex]

            if (question != null) {
                mQuestionView.setQuestionText(question.text)
            }
        }
    }

}