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

class CategoryAdapter(context: Context, categories: List<Category>): RecyclerView.Adapter<CategoryViewHolder>() {

    /**
     * Attributes
     */
    private val mContext: Context = context

    /**
     * Model
     */
    private val mCategories: List<Category> = categories

    override fun onCreateViewHolder(patern: ViewGroup, viewType: Int): CategoryViewHolder {
        val view = LayoutInflater.from(patern.context).inflate(R.layout.custom_category_item, patern, false)
        return CategoryViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mCategories.size
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val category = mCategories[position]

        val subcategoryAdapter = SubcategoryAdapter(category.subcategoriesList)
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