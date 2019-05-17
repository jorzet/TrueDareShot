package com.jorzet.truedareshot.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Category (
    @SerializedName("category_name")
    @Expose
    var categoryName: String = "",
    @SerializedName("category_active")
    @Expose
    var categoryActive: Boolean = false,
    @SerializedName("questions_type")
    @Expose
    var questionsType: List<QuestionType> = arrayListOf()
)