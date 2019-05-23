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
import android.os.Bundle
import android.support.v4.app.Fragment
import com.jorzet.truedareshot.models.Category
import com.jorzet.truedareshot.models.Subcategory
import com.jorzet.truedareshot.services.firebase.FirebaseRequestManager
import com.jorzet.truedareshot.services.sharedpreferences.SharedPreferencesManager

/**
 * @author
 * Created by Jorge Zepeda Tinoco on 08/11/18.
 * jorzet.94@gmail.com
 */

abstract class BaseFragment: Fragment() {

    private lateinit var mSharedPreferencesManager: SharedPreferencesManager
    private lateinit var mRequestManager : FirebaseRequestManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mSharedPreferencesManager = SharedPreferencesManager(context!!)
        mRequestManager = FirebaseRequestManager(activity as Activity)
    }


    fun isFirstQuestionShown(): Boolean {
        return mSharedPreferencesManager.isFirstQuestionShown();
    }

    fun setFirstQuestionShown(isFirstQuestionShown: Boolean) {
        mSharedPreferencesManager.setFirstQuestionShown(isFirstQuestionShown)
    }

    /**
     * Request categories method
     */
    fun requestCategories() {
        mRequestManager.requestGetCategories(object: FirebaseRequestManager.OnGetCategoriesListener {
            override fun onGetCategoriesLoaded(categories: List<Category>) {
                onGetCategoriesSuccess(categories)
            }
            override fun onGetCategoriesError(throwable: Throwable) {
                onGetCategoriesFail(throwable)
            }
        })
    }

    open fun onGetCategoriesSuccess(categories: List<Category>) {}
    open fun onGetCategoriesFail(throwable: Throwable) {}

    /**
     *
     */
    fun requestSubcategories() {
        mRequestManager.requestGetSubcategories(object: FirebaseRequestManager.OnGetSubcategoriesListener {
            override fun onGetSubcategoriesLoaded(subcategories: List<Subcategory>) {
                onGetSubcategoriesSuccess(subcategories)
            }
            override fun onGetSubcategoriesError(throwable: Throwable) {
                onGetSubcategoriesFail(throwable)
            }
        })
    }

    open fun onGetSubcategoriesSuccess(subcategories: List<Subcategory>) {}
    open fun onGetSubcategoriesFail(throwable: Throwable) {}
}