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

package com.jorzet.truedareshot.services.firebase

import android.app.Activity
import com.jorzet.truedareshot.models.Category
import com.jorzet.truedareshot.models.Question
import com.jorzet.truedareshot.models.Subcategory
import com.jorzet.truedareshot.request.AbstractRequestTask
import com.jorzet.truedareshot.request.CategoriesTask

/**
 * @author
 * Created by Jorge Zepeda Tinoco on 19/05/19.
 * jorzet.94@gmail.com
 */

private const val TAG : String = "RequestManager"

class FirebaseRequestManager(activity : Activity) {
    private val mActivity = activity

    interface OnGetQuestionListener {
        fun onGetQuestionLoaded(question: Question)
        fun onGetQuestionError(throwable: Throwable)
    }

    interface OnGetCategoriesListener {
        fun onGetCategoriesLoaded(categories: List<Category>)
        fun onGetCategoriesError(throwable: Throwable)
    }

    interface OnGetSubcategoriesListener {
        fun onGetSubcategoriesLoaded(subcategories: List<Subcategory>)
        fun onGetSubcategoriesError(throwable: Throwable)
    }

    fun requestGetQuestion(onGetQuestionListener: OnGetQuestionListener) {

    }

    fun requestGetCategories(onGetCategoriesListener: OnGetCategoriesListener) {
        val categoryTask = CategoriesTask()

        categoryTask.setOnRequestSuccess(object: AbstractRequestTask.OnRequestListenerSuccess {
            override fun onSuccess(result: Any) {
                onGetCategoriesListener.onGetCategoriesLoaded(result as List<Category>)
            }
        })

        categoryTask.setOnRequestFailed(object: AbstractRequestTask.OnRequestListenerFailed {
            override fun onFailed(throwable: Throwable) {
                onGetCategoriesListener.onGetCategoriesError(throwable)
            }
        })

        categoryTask.requestGetCategories()
    }

    fun requestGetSubcategories(onGetSubcategoriesListener: OnGetSubcategoriesListener) {
        val categoryTask = CategoriesTask()

        categoryTask.setOnRequestSuccess(object: AbstractRequestTask.OnRequestListenerSuccess {
            override fun onSuccess(result: Any) {
                onGetSubcategoriesListener.onGetSubcategoriesLoaded(result as List<Subcategory>)
            }
        })

        categoryTask.setOnRequestFailed(object: AbstractRequestTask.OnRequestListenerFailed {
            override fun onFailed(throwable: Throwable) {
                onGetSubcategoriesListener.onGetSubcategoriesError(throwable)
            }
        })

        categoryTask.requestGetSubcategories()
    }

}