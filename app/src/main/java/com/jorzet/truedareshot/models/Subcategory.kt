package com.jorzet.truedareshot.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * @author Jorge Zepeda Tinoco
 * @email jorzet.94@gmail.com
 * @date 20/05/19
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