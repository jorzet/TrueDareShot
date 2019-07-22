package com.jorzet.truedareshot.managers.sharedpreferences

import android.annotation.SuppressLint
import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.jorzet.truedareshot.models.Question
import com.jorzet.truedareshot.models.enums.QuestionType


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

    override fun saveQuestions(questions: List<Question>, questionType: QuestionType) {
        val editor = mContext.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE).edit()

        val questionListType = object : TypeToken<List<Question>>() {}.type
        val json = Gson().toJson(questions, questionListType)
        when (questionType) {
            QuestionType.TRUE -> editor.putString(JSON_QUESTIONS_TRUE, json)
            QuestionType.DARE -> editor.putString(JSON_QUESTIONS_DARE, json)
            QuestionType.SHOT -> editor.putString(JSON_QUESTIONS_SHOT, json)
        }

        editor.apply()
    }

    override fun getQuestions(questionType: QuestionType) : List<Question>? {
        val prefs = mContext.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)

        val json = when(questionType) {
            QuestionType.TRUE -> prefs.getString(JSON_QUESTIONS_TRUE, null)
            QuestionType.DARE -> prefs.getString(JSON_QUESTIONS_DARE, null)
            QuestionType.SHOT -> prefs.getString(JSON_QUESTIONS_SHOT, null)
        }

        val questionListType = object : TypeToken<List<Question>>() {}.type
        val questions = Gson().fromJson<List<Question>>(json, questionListType)
        return questions
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

    override fun saveConfiguration(config: HashMap<String, HashMap<String, Boolean>>?) {
        val editor = mContext.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE).edit()
        if (config != null) {
            val configString = JsonParser.parseObjectToJson(config)
            editor.putString(JSON_CONFIG, configString)
        } else {
            editor.putString(JSON_CONFIG, null)
        }
        editor.apply()
    }

    override fun getConfiguration(): HashMap<String, HashMap<String, Boolean>>? {
        val prefs = mContext.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)
        val configJson = prefs.getString(JSON_CONFIG, "")
        if (configJson == "") return null

        val empMapType = object : TypeToken<HashMap<String, HashMap<String, Boolean>>>() {}.type

        return Gson().fromJson(configJson, empMapType)
    }
}