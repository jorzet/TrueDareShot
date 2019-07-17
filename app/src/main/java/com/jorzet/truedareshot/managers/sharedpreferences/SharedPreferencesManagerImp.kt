package com.jorzet.truedareshot.managers.sharedpreferences

import android.annotation.SuppressLint
import android.content.Context

/**
 * @author Jorge Zepeda Tinoco
 * @email jorzet.94@gmail.com
 * @date 16/07/19.
 */

class SharedPreferencesManagerImp(context: Context): SharedPreferencesManager(context) {

    companion object {
        /**
         * Manager static instance
         */
        @SuppressLint("StaticFieldLeak")
        private var sInstance: SharedPreferencesManager? = null

        /**
         * Creates a [SharedPreferencesManagerImp] implementation instance
         *
         * @param context Base Activity or Fragment [Context]
         * @return A [SharedPreferencesManagerImp] instance
         */
        fun getInstance(context: Context): SharedPreferencesManager {
            if (sInstance == null) {
                synchronized(SharedPreferencesManager::class.java) {
                    if (sInstance == null) {
                        sInstance = SharedPreferencesManagerImp(context)
                    }
                }
            }
            return sInstance!!
        }
    }

    override fun destroy() {
        sInstance = null
    }

    override fun removeSessionData() {
        mContext.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE).edit().clear().apply()
    }

    override fun storeJsonQuestion(json : String) {
        val editor = mContext.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE).edit()
        editor.putString(JSON_QUESTION, json)
        editor.apply()
    }

    override fun getJsonQuestion() : String? {
        val prefs = mContext.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)
        return prefs.getString(JSON_QUESTION, null)
    }

    override fun setFirstQuestionShown(isFirstQuestionShown: Boolean) {
        val editor = mContext.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE).edit()
        editor.putBoolean(FIRST_QUESTION_SHOWN, isFirstQuestionShown)
        editor.apply()
    }

    override fun isFirstQuestionShown(): Boolean {
        val prefs = mContext.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)
        return prefs.getBoolean(FIRST_QUESTION_SHOWN, false)
    }
}