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

package com.jorzet.truedareshot.managers.sharedpreferences

import android.content.Context

/**
 * @author Jorge Zepeda Tinoco
 * @email jorzet.94@gmail.com
 * @date 08/11/18.
 */

abstract class SharedPreferencesManager(context: Context) {

    /*
     * Objects
     */
    protected val mContext : Context = context

    /*
     * Tags to save data
     */
    protected val SHARED_PREFERENCES_NAME : String = "shared_preferences_name"
    protected val JSON_QUESTION : String = "json_question"
    protected val FIRST_QUESTION_SHOWN = "first_question_shown"

    companion object {
        fun getInstance(context: Context): SharedPreferencesManager {
            return SharedPreferencesManagerImp.getInstance(context)
        }
    }

    /**
     * Destroy [SharedPreferencesManager] instance
     */
    abstract fun destroy()

    /**
     * This method removes all session data
     */
    abstract fun removeSessionData()

    /**
     * store json with JSON_QUESTION tag
     * @param [Question] object in Json [Stirng]
     */
    abstract fun storeJsonQuestion(json : String)

    /**
     * Return a json string that contains question object
     * @return Question in Json [String]
     */
    abstract fun getJsonQuestion() : String?

    /**
     * Set flag to know if first question is shown
     * @param isFirstQuestionShown boolean flag
     */
    abstract fun setFirstQuestionShown(isFirstQuestionShown: Boolean)

    /**
     * Indicate true when first question is shown in app
     * @return true or false according first question
     */
    abstract fun isFirstQuestionShown(): Boolean

}