package com.jorzet.truedareshot.models.enums

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

enum class Category constructor(val value : String) {
    NONE(""),
    @SerializedName("Verdad")
    @Expose
    TRUE("True"),
    @SerializedName("Reto")
    @Expose
    DARE("Dare"),
    @SerializedName("Shot")
    @Expose
    SHOT("Shot")
}