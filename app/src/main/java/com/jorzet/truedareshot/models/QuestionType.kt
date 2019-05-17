package com.jorzet.truedareshot.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class QuestionType (
    @SerializedName("questions_type_name")
    @Expose
    var questionTypeName: String = "",
    @SerializedName("questions_type_active")
    @Expose
    var questionTypeActive: Boolean = false
)