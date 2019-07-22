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

package com.jorzet.truedareshot.presenters.configuration

import com.jorzet.truedareshot.adapters.SubcategoryAdapter
import com.jorzet.truedareshot.models.Category
import com.jorzet.truedareshot.models.Subcategory
import com.jorzet.truedareshot.managers.firebase.FirebaseRequestManager
import com.jorzet.truedareshot.managers.sharedpreferences.SharedPreferencesManager
import com.jorzet.truedareshot.views.ConfigurationView

/**
 * @author Jorge Zepeda Tinoco
 * @email jorzet.94@gmail.com
 * @date 16/07/19
 */

class ConfigurationPresenterImp: ConfigurationPresenter {

    /**
     * View
     */
    private lateinit var mConfigurationView: ConfigurationView

    /**
     * Manager
     */
    private var mSharedPreferencesManager: SharedPreferencesManager? = null
    private var mRequestManager : FirebaseRequestManager? = null

    /**
     * Model
     */
    private lateinit var mCategories: List<Category>
    private var mConfiguration: HashMap<String, HashMap<String, Boolean>>? = null

    /**
     * Listener
     */
    override fun updateNewConfiguration(category: String, subcategoryId: String, selected: Boolean) {
            if (mConfiguration == null || (mConfiguration != null && mConfiguration?.get(category) == null)) {

                if (mConfiguration == null) {
                    mConfiguration = HashMap()
                }

                val subcategoryHashMap = HashMap<String, Boolean>()
                subcategoryHashMap.put(subcategoryId, selected)
                mConfiguration?.put(category, subcategoryHashMap)
            } else {
                val subcategory = mConfiguration?.get(category)
                subcategory?.put(subcategoryId, selected)
            }
            saveUserConfiguration()

    }

    override fun create(view: ConfigurationView) {
        mConfigurationView = view

        // init manager
        mSharedPreferencesManager = SharedPreferencesManager.getInstance(view.getBaseContext())
        mRequestManager = FirebaseRequestManager.getInstance(view.getBaseContext())

        requestCategories()
    }

    override fun destroy() {
        mSharedPreferencesManager?.destroy()
        mRequestManager?.destroy()
    }

    override fun getUserConfiguration(): HashMap<String, HashMap<String, Boolean>>? {
        return mSharedPreferencesManager?.getConfiguration()
    }

    override fun saveUserConfiguration() {
        mSharedPreferencesManager?.saveConfiguration(mConfiguration)
    }

    override fun requestCategories() {
        mRequestManager?.requestGetCategories(object: FirebaseRequestManager.OnGetCategoriesListener {
            override fun onGetCategoriesLoaded(categories: List<Category>) {
                mCategories = categories
                requestSubcategories()
            }

            override fun onGetCategoriesError(throwable: Throwable) {

            }

        })
    }

    override fun requestSubcategories() {
        mRequestManager?.requestGetSubcategories(object: FirebaseRequestManager.OnGetSubcategoriesListener {
            override fun onGetSubcategoriesLoaded(subcategories: List<Subcategory>) {

                mConfiguration = getUserConfiguration()
                var configuration = mConfiguration
                if (mConfiguration == null) {
                    configuration = HashMap()
                }

                if (mCategories.isNotEmpty()) {
                    for (subcategory in subcategories) {
                        for (category in mCategories) {
                            for (subcategoryId in category.subcategoryIdList){
                                if (subcategoryId == subcategory.subcategoryId) {

                                    if (mConfiguration == null) {

                                        if (configuration?.get(category.categoryId) != null) {
                                            val subcategoryHashMap = configuration.get(category.categoryId)
                                            subcategoryHashMap?.put(subcategoryId, true)
                                        } else {
                                            val subcategoryHashMap = HashMap<String, Boolean>()
                                            subcategoryHashMap.put(subcategoryId, true)
                                            configuration?.put(category.categoryId, subcategoryHashMap)
                                        }
                                    }

                                    category.subcategoriesList.add(subcategory)
                                    break
                                }
                            }
                        }
                    }
                    mConfiguration = configuration
                    saveUserConfiguration()
                    mConfigurationView.updateConfigurationData(mCategories, mConfiguration)
                }
            }

            override fun onGetSubcategoriesError(throwable: Throwable) {

            }

        })
    }

}