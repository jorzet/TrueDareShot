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

package com.jorzet.truedareshot.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.jorzet.truedareshot.R
import com.jorzet.truedareshot.models.Category

class ConfigurationAdapter(categories: List<Category>): RecyclerView.Adapter<ConfigurationViewHolder>() {

    /**
     * Models
     */
    private val mCategories = categories;

    /**
     * Adapter
     */
    //private val mQuestionTypeConfigAdapter = QuestionTypeConfigAdapter()

    override fun onCreateViewHolder(parent: ViewGroup, viewtype: Int): ConfigurationViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.custom_item_configuration, parent, false)
        return ConfigurationViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mCategories.size
    }

    override fun onBindViewHolder(holder: ConfigurationViewHolder, position: Int) {

    }

}

class ConfigurationViewHolder(view: View): RecyclerView.ViewHolder(view) {
    val mCategoryImageView = view.findViewById<ImageView>(R.id.iv_category_configuration)
    val mQuestionsTypeConfig = view.findViewById<RecyclerView>(R.id.rv_question_type_configuration)
}