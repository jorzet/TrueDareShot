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

import android.app.Activity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jorzet.truedareshot.R
import com.jorzet.truedareshot.adapters.CategoryAdapter
import com.jorzet.truedareshot.adapters.SubcategoryAdapter
import com.jorzet.truedareshot.models.Category
import com.jorzet.truedareshot.presenters.configuration.ConfigurationPresenter
import com.jorzet.truedareshot.presenters.configuration.ConfigurationPresenterImp
import com.jorzet.truedareshot.views.ConfigurationView

/**
 * @author Jorge Zepeda Tinoco
 * @email jorzet.94@gmail.com
 * @date 08/11/18.
 */

/**
 * Constants
 */
private const val TAG : String = "ConfigurationFragment"

class ConfigurationFragment: BaseFragment(), ConfigurationView {

    /**
     * UI accessors
     */
    private lateinit var mConfigurationRecyclerView: RecyclerView

    /**
     * Adapter
     */
    private lateinit var mCategoryAdapter: CategoryAdapter

    /**
     * Presenter
     */
    private lateinit var mConfigurationPresenter: ConfigurationPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mConfigurationPresenter = ConfigurationPresenterImp()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        if (container == null) {
            return null
        }

        val rootView = inflater.inflate(R.layout.configuration_fragment, container, false)

        mConfigurationRecyclerView = rootView.findViewById(R.id.rv_configuration)

        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (::mConfigurationPresenter.isInitialized)
            mConfigurationPresenter.create(this)
    }

    private val mOnSubcategorySelectedListener =
        object: SubcategoryAdapter.OnSubcategorySelectedListener {
            override fun onSubcategorySelected(category: String, subcategoryId: String, selected: Boolean) {
                if (::mConfigurationPresenter.isInitialized) {
                    mConfigurationPresenter.updateNewConfiguration(category, subcategoryId, selected)
                }
                requestQuestions()
            }
        }

    override fun getBaseContext(): Activity {
        return this.activity!!
    }

    override fun updateConfigurationData(categories: List<Category>, config: HashMap<String, HashMap<String, Boolean>>?) {
        mCategoryAdapter = CategoryAdapter(context!!, categories, config)
        mCategoryAdapter.setOnSubcategorySelectedListener(mOnSubcategorySelectedListener)
        mConfigurationRecyclerView.adapter = mCategoryAdapter
        mConfigurationRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
    }
}