package com.jorzet.truedareshot.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.jorzet.truedareshot.R
import com.jorzet.truedareshot.models.Subcategory


class SubcategoryAdapter(subcategories: List<Subcategory>): RecyclerView.Adapter<SubcategoryViewHolder>() {

    /**
     * Model
     */
    private val mSubcategories: List<Subcategory> = subcategories

    override fun onCreateViewHolder(patern: ViewGroup, p1: Int): SubcategoryViewHolder {
        val view = LayoutInflater.from(patern.context).inflate(R.layout.custom_subcategory_item,
            patern, false)
        return SubcategoryViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mSubcategories.size
    }

    override fun onBindViewHolder(holder: SubcategoryViewHolder, position: Int) {
        val subcategory = mSubcategories[position]

        holder.mSubcategoryTextView.text = subcategory.subcategoryName

        if (subcategory.emojiText.isNotEmpty()) {
            val hexString = subcategory.emojiText.replace("U+", "")
            val emoji = String(Character.toChars(Integer.parseInt(hexString, 16)))
            holder.mEmoji.text.append(emoji);
        }
    }

}

class SubcategoryViewHolder(view: View): RecyclerView.ViewHolder(view) {
    val mSubcategoryTextView = view.findViewById<TextView>(R.id.tv_subcategory)
    val mEmoji= view.findViewById<EditText>(R.id.iv_subcategory_emoji)
    val mConfigSwitch = view.findViewById<Switch>(R.id.sw_subcategory_config)
}