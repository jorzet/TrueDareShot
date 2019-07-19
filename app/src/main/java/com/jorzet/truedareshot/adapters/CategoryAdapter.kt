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

package com.jorzet.truedareshot.adapters

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView

import com.jorzet.truedareshot.R
import com.jorzet.truedareshot.models.Category

/**
 * @author Jorge Zepeda Tinoco
 * @email jorzet.94@gmail.com
 * @date 23/05/19.
 */

class CategoryAdapter(context: Context, categories: List<Category>, configuration: HashMap<String, HashMap<String, Boolean>>?):
    RecyclerView.Adapter<CategoryViewHolder>() {

    /**
     * Attributes
     */
    private val mContext: Context = context

    /**
     * Model
     */
    private val mCategories: List<Category> = categories
    private val mConfiguration: HashMap<String, HashMap<String, Boolean>>? = configuration

    /**
     * Listener
     */
    private lateinit var mOnSubcategorySelectedListener: SubcategoryAdapter.OnSubcategorySelectedListener

    fun setOnSubcategorySelectedListener(onSubcategorySelectedListener: SubcategoryAdapter.OnSubcategorySelectedListener) {
        this.mOnSubcategorySelectedListener = onSubcategorySelectedListener
    }

    override fun onCreateViewHolder(patern: ViewGroup, viewType: Int): CategoryViewHolder {
        val view = LayoutInflater.from(patern.context).inflate(R.layout.custom_category_item, patern, false)
        return CategoryViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mCategories.size
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val category = mCategories[position]

        val subcategoryAdapter = SubcategoryAdapter(category.subcategoriesList, category.categoryId, mConfiguration)
        subcategoryAdapter.setOnSubcategorySelectedListener(mOnSubcategorySelectedListener)
        holder.mSubcategoryConfig.adapter = subcategoryAdapter
        holder.mSubcategoryConfig.layoutManager = LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false)

        when(category.categoryId) {
            "true" -> {
                holder.mCategoryImageView.setImageResource(R.drawable.botton_true)
            }
            "dare" -> {
                holder.mCategoryImageView.setImageResource(R.drawable.botton_dare)
            }

            "shot" -> {
                holder.mCategoryImageView.setImageResource(R.drawable.botton_shot)
            }
        }

    }

}

class CategoryViewHolder(view: View): RecyclerView.ViewHolder(view) {
    val mCategoryImageView = view.findViewById<ImageView>(R.id.iv_category_configuration)
    val mSubcategoryConfig = view.findViewById<RecyclerView>(R.id.rv_subcategory_configuration)
}