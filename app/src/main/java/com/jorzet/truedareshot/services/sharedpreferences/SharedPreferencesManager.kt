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

package com.jorzet.truedareshot.services.sharedpreferences

import android.content.Context

/**
 * Created by Jorge Zepeda Tinoco on 08/11/18.
 * jorzet.94@gmail.com
 */

class SharedPreferencesManager(context: Context) {

    /*
     * Objects
     */
    private val mContext : Context = context

    /*
     * Tags to save data
     */
    private val SHARED_PREFERENCES_NAME : String = "shared_preferences_name"
    private val JSON_QUESTION : String = "json_question"
    private val FIRST_QUESTION_SHOWN = "first_question_shown"

    /**
     * This method removes all sharedPreferences session data
     */
    fun removeSessionData() {
        mContext.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE).edit().clear().apply()
    }

    /*
     * store json with JSON_QUESTION tag
     */
    fun storeJsonQuestion(json : String) {
        val editor = mContext.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE).edit()
        editor.putString(JSON_QUESTION, json)
        editor.apply()
    }

    /**
     * @return
     *      A json string that contains question object
     */
    fun getJsonQuestion() : String? {
        val prefs = mContext.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)
        return prefs.getString(JSON_QUESTION, null)
    }

    fun setFirstQuestionShown(isFirstQuestionShown: Boolean) {
        val editor = mContext.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE).edit()
        editor.putBoolean(FIRST_QUESTION_SHOWN, isFirstQuestionShown)
        editor.apply()
    }

    fun isFirstQuestionShown(): Boolean {
        val prefs = mContext.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)
        return prefs.getBoolean(FIRST_QUESTION_SHOWN, false)
    }

}