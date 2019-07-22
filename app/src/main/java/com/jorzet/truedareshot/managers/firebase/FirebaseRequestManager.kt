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

package com.jorzet.truedareshot.managers.firebase

import android.app.Activity
import android.content.Context
import com.jorzet.truedareshot.models.Category
import com.jorzet.truedareshot.models.Player
import com.jorzet.truedareshot.models.Question
import com.jorzet.truedareshot.models.Subcategory
import com.jorzet.truedareshot.models.enums.QuestionType

/**
 * @author Jorge Zepeda Tinoco
 * @email jorzet.94@gmail.com
 * @date 20/05/19.
 */

abstract class FirebaseRequestManager(activity: Activity) {

    protected val TAG : String = "FirebaseRequestManager"
    protected val mActivity: Activity = activity

    companion object {
        /**
         * Manager constructor
         * @param activity Base Activity or Fragment [Context]
         */
        fun getInstance(activity: Activity): FirebaseRequestManager {
            return FirebaseRequestManagerImp.getInstance(activity)
        }
    }

    interface OnGetQuestionsListener {
        fun onGetQuestionsLoaded(questions: List<Question>, questionType: String)
        fun onGetQuestionsError(throwable: Throwable)
    }

    interface OnGetCategoriesListener {
        fun onGetCategoriesLoaded(categories: List<Category>)
        fun onGetCategoriesError(throwable: Throwable)
    }

    interface OnGetSubcategoriesListener {
        fun onGetSubcategoriesLoaded(subcategories: List<Subcategory>)
        fun onGetSubcategoriesError(throwable: Throwable)
    }

    interface OnGetPlayersListener {
        fun onGetPlayersLoaded(players: List<Player>)
        fun onGetPlayersError(throwable: Throwable)
    }

    abstract fun requestGetQuestions(category: String, subcategory: String, onGetQuestionsListener: OnGetQuestionsListener)

    /**
     * @param onGetCategoriesListener categories response listener
     */
    abstract fun requestGetCategories(onGetCategoriesListener: OnGetCategoriesListener)

    /**
     * @param onGetSubcategoriesListener subcategories response listener [OnGetSubcategoriesListener]
     */
    abstract fun requestGetSubcategories(onGetSubcategoriesListener: OnGetSubcategoriesListener)

    /**
     * @param onGetPlayersListener players response listener [OnGetQuestionListener]
     */
    abstract fun requestGetPlayers(onGetPlayersListener: OnGetPlayersListener)

    /**
     * Destroy [FirebaseRequestManager] instance
     */
    abstract fun destroy()

}