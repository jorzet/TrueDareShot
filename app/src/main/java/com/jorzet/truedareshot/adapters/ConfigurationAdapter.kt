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