/*
 * Copyright [2018] [Jorge Zepeda Tinoco]
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
import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import com.jorzet.truedareshot.managers.firebase.FirebaseRequestManager
import com.jorzet.truedareshot.managers.sharedpreferences.SharedPreferencesManager
import com.jorzet.truedareshot.models.Question
import com.jorzet.truedareshot.models.enums.QuestionType


/**
 * @author Jorge Zepeda Tinoco
 * @email jorzet.94@gmail.com
 * @date 08/11/18
 */

abstract class BaseFragment: Fragment() {

    /**
     * Managers
     */
    private lateinit var mSharedPreferencesManager: SharedPreferencesManager
    private lateinit var mRequestManager: FirebaseRequestManager

    /**
     * Model
     */
    private var mQuestionsTrue = arrayListOf<Question>()
    private var mQuestionsDare = arrayListOf<Question>()
    private var mQuestionsShot = arrayListOf<Question>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mSharedPreferencesManager = SharedPreferencesManager.getInstance(context!!)
        mRequestManager = FirebaseRequestManager.getInstance(activity!!)
        requestQuestions()
    }

    fun Context.hideKeyboard(view: View?) {
        if (view != null) {
            val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    protected open fun requestQuestions() {
        val mConfiguration = mSharedPreferencesManager.getConfiguration()

        if (mConfiguration != null) {
            mQuestionsTrue.clear()
            mQuestionsDare.clear()
            mQuestionsShot.clear()

            for (category in mConfiguration.keys) {
                for (subcategory in mConfiguration.get(category)?.keys!!) {
                    val subcategoryHashMap = mConfiguration.get(category) as HashMap<String, Boolean>
                    val selected = subcategoryHashMap.get(subcategory) == true
                    if (selected) {
                        mRequestManager.requestGetQuestions(category, subcategory, mOnGetQuestionsListener)
                    }
                }
            }
        }
    }

    fun isFirstQuestionShown(): Boolean {
        return mSharedPreferencesManager.isFirstQuestionShown();
    }

    fun setFirstQuestionShown(isFirstQuestionShown: Boolean) {
        mSharedPreferencesManager.setFirstQuestionShown(isFirstQuestionShown)
    }

    private val mOnGetQuestionsListener =
        object: FirebaseRequestManager.OnGetQuestionsListener {
        override fun onGetQuestionsLoaded(questions: List<Question>, questionType: String) {

            when(questionType) {
                "true" -> {
                    mQuestionsTrue.addAll(questions)
                    mSharedPreferencesManager.saveQuestions(mQuestionsTrue, QuestionType.TRUE)
                }
                "dare" ->  {
                    mQuestionsDare.addAll(questions)
                    mSharedPreferencesManager.saveQuestions(mQuestionsDare, QuestionType.DARE)
                }
                "shot" -> {
                    mQuestionsShot.addAll(questions)
                    mSharedPreferencesManager.saveQuestions(mQuestionsShot, QuestionType.SHOT)
                }
            }
        }

        override fun onGetQuestionsError(throwable: Throwable) {

        }
    }

}