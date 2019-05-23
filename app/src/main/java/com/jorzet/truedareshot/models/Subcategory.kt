package com.jorzet.truedareshot.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * @author
 * Created by Jorge Zepeda Tinoco on 19/05/19.
 * jorzet.94@gmail.com
 */

data class Subcategory (
    @SerializedName("subcategory_name")
    @Expose
    var subcategoryName: String = "",
    @SerializedName("subcategory_active")
    @Expose
    var aubcategoryActive: Boolean = false,
    @SerializedName("emoji_text")
    @Expose
    var emojiText: String = "",

    var subcategoryId: String = ""
)