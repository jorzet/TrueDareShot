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

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.util.Log
import com.jorzet.truedareshot.models.Category
import com.jorzet.truedareshot.models.Player
import com.jorzet.truedareshot.models.Question
import com.jorzet.truedareshot.models.Subcategory
import com.jorzet.truedareshot.request.*

/**
 * @author Jorge Zepeda Tinoco
 * @email jorzet.94@gmail.com
 * @date 16/07/19.
 */

class FirebaseRequestManagerImp(activity: Activity): FirebaseRequestManager(activity) {


    companion object {
        /**
         * Manager static instance
         */
        @SuppressLint("StaticFieldLeak")
        private var sInstance: FirebaseRequestManagerImp? = null

        /**
         * Creates a [FirebaseRequestManager] implementation instance
         *
         * @param activity Base Activity or Fragment [Context]
         * @return A [FirebaseRequestManager] instance
         */
        fun getInstance(activity: Activity): FirebaseRequestManager {
            if (sInstance == null) {
                synchronized(FirebaseRequestManager::class.java) {
                    if (sInstance == null) {
                        sInstance = FirebaseRequestManagerImp(activity)
                    }
                }
            }
            return sInstance!!
        }
    }

    override fun destroy() {
        sInstance = null
    }

    override fun requestGetQuestions(onGetQuestionsListener: OnGetQuestionsListener) {
        val questionsTask = QuestionsTask()

        questionsTask.setOnRequestSuccess(object: AbstractRequestTask.OnRequestListenerSuccess<List<Question>> {
            override fun onSuccess(result: List<Question>) {
                onGetQuestionsListener.onGetQuestionsLoaded(result)
            }
        })

        questionsTask.setOnRequestFailed(object: AbstractRequestTask.OnRequestListenerFailed {
            override fun onFailed(throwable: Throwable) {
                onGetQuestionsListener.onGetQuestionsError(throwable)
            }
        })

        questionsTask.request()
    }

    override fun requestGetCategories(onGetCategoriesListener: OnGetCategoriesListener) {
        val categoryTask = CategoriesTask()

        categoryTask.setOnRequestSuccess(object: AbstractRequestTask.OnRequestListenerSuccess<List<Category>> {
            override fun onSuccess(result: List<Category>) {
                Log.d(TAG, "requestGetCategories success")
                onGetCategoriesListener.onGetCategoriesLoaded(result)
            }
        })

        categoryTask.setOnRequestFailed(object: AbstractRequestTask.OnRequestListenerFailed {
            override fun onFailed(throwable: Throwable) {
                Log.d(TAG, "requestGetCategories fail")
                onGetCategoriesListener.onGetCategoriesError(throwable)
            }
        })

        categoryTask.request()
    }

    override fun requestGetSubcategories(onGetSubcategoriesListener: OnGetSubcategoriesListener) {
        val subcategoryTask = SubcategoryTask()

        subcategoryTask.setOnRequestSuccess(object: AbstractRequestTask.OnRequestListenerSuccess<List<Subcategory>> {
            override fun onSuccess(result: List<Subcategory>) {
                Log.d(TAG, "requestGetSubcategories success")
                onGetSubcategoriesListener.onGetSubcategoriesLoaded(result )
            }
        })

        subcategoryTask.setOnRequestFailed(object: AbstractRequestTask.OnRequestListenerFailed {
            override fun onFailed(throwable: Throwable) {
                Log.d(TAG, "requestGetSubcategories fail")
                onGetSubcategoriesListener.onGetSubcategoriesError(throwable)
            }
        })

        subcategoryTask.request()
    }

    override fun requestGetPlayers(onGetPlayersListener: OnGetPlayersListener) {
        val playersTask = PlayersTask()

        playersTask.setOnRequestSuccess(object: AbstractRequestTask.OnRequestListenerSuccess<List<Player>> {
            override fun onSuccess(result: List<Player>) {
                Log.d(TAG, "requestGetPlayers success")
                onGetPlayersListener.onGetPlayersLoaded(result)
            }
        })

        playersTask.setOnRequestFailed(object: AbstractRequestTask.OnRequestListenerFailed {
            override fun onFailed(throwable: Throwable) {
                Log.d(TAG, "requestGetPlayers fail")
                onGetPlayersListener.onGetPlayersError(throwable)
            }
        })

        playersTask.request()
    }

}