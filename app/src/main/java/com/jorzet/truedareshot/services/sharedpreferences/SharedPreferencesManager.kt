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