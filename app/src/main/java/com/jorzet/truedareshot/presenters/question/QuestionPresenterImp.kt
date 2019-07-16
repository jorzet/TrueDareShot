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
import com.jorzet.truedareshot.services.firebase.FirebaseRequestManager
import com.jorzet.truedareshot.services.sharedpreferences.SharedPreferencesManager
import com.jorzet.truedareshot.views.QuestionView

/**
 * @author
 * Created by Jorge Zepeda Tinoco on 16/07/19.
 * jorzet.94@gmail.com
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

    override fun create(view: QuestionView) {
        mQuestionView = view

        // init manager
        mSharedPreferencesManager = SharedPreferencesManager.getInstance(view.getBaseContext())
        mRequestManager = FirebaseRequestManager.getInstance(view.getBaseContext())

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

    override fun requestQuestion() {
        mRequestManager?.requestGetQuestion(object : FirebaseRequestManager.OnGetQuestionListener {
            override fun onGetQuestionLoaded(question: Question) {

            }

            override fun onGetQuestionError(throwable: Throwable) {

            }

        })
    }


}