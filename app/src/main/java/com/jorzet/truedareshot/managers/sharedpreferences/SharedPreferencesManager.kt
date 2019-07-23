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
import com.jorzet.truedareshot.models.Player
import com.jorzet.truedareshot.models.Question
import com.jorzet.truedareshot.models.enums.QuestionType

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
    protected val JSON_QUESTIONS_TRUE : String = "json_questions_true"
    protected val JSON_QUESTIONS_DARE : String = "json_questions_dare"
    protected val JSON_QUESTIONS_SHOT : String = "json_questions_shot"
    protected val FIRST_QUESTION_SHOWN = "first_question_shown"
    protected val JSON_CONFIG: String = "json_configuration"
    protected val JSON_PLAYERS: String = "json_players"

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
     * store json with JSON_QUESTIONS tag
     * @param questions object in object list [Question]
     */
    abstract fun saveQuestions(questions: List<Question>, questionType: QuestionType)

    /**
     * Return a json string that contains question object
     * @return Question in Json [String]
     */
    abstract fun getQuestions(questionType: QuestionType) : List<Question>?

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

    abstract fun saveConfiguration(config: HashMap<String, HashMap<String, Boolean>>?)

    abstract fun getConfiguration(): HashMap<String, HashMap<String, Boolean>>?

    abstract fun savePlayers(players: ArrayList<Player>?)

    abstract fun getPlayers(): ArrayList<Player>?
}