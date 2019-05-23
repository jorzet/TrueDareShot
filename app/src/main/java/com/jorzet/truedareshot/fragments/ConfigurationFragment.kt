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

package com.jorzet.truedareshot.fragments

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jorzet.truedareshot.R
import com.jorzet.truedareshot.adapters.CategoryAdapter
import com.jorzet.truedareshot.models.Category
import com.jorzet.truedareshot.models.Subcategory

/**
 * @author
 * Created by Jorge Zepeda Tinoco on 08/11/18.
 * jorzet.94@gmail.com
 */

class ConfigurationFragment: BaseFragment() {

    /*
     * Tags
     */
    private val TAG : String = "ConfigurationFragment"

    /**
     * UI accessors
     */
    private lateinit var mConfigurationRecyclerView: RecyclerView

    /**
     * Model
     */
    private lateinit var mCategories: List<Category>

    /**
     * Adapter
     */
    private lateinit var mCategoryAdapter: CategoryAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        if (container == null) {
            return null
        }

        val rootView = inflater.inflate(R.layout.configuration_fragment, container, false)

        mConfigurationRecyclerView = rootView.findViewById(R.id.rv_configuration)

        requestCategories()
        return rootView
    }

    override fun onGetCategoriesSuccess(categories: List<Category>) {
        super.onGetCategoriesSuccess(categories)
        mCategories = categories
        requestSubcategories()

    }

    override fun onGetCategoriesFail(throwable: Throwable) {
        super.onGetCategoriesFail(throwable)
    }

    override fun onGetSubcategoriesSuccess(subcategories: List<Subcategory>) {
        super.onGetSubcategoriesSuccess(subcategories)
        if (mCategories.isNotEmpty()) {
            for (subcategory in subcategories) {
                for (category in mCategories) {
                    for (subcategoryId in category.subcategoryIdList){
                        if (subcategoryId.equals(subcategory.subcategoryId)) {
                            category.subcategoriesList.add(subcategory)
                            break;
                        }
                    }
                }
            }
        }
        Log.d(TAG, "")

        mCategoryAdapter = CategoryAdapter(context!!, mCategories)
        mConfigurationRecyclerView.adapter = mCategoryAdapter
        mConfigurationRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
    }

    override fun onGetSubcategoriesFail(throwable: Throwable) {
        super.onGetSubcategoriesFail(throwable)
    }


}