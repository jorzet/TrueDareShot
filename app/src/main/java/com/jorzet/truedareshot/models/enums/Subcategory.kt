package com.jorzet.truedareshot.models.enums

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

enum class Subcategory constructor(val value : String) {
    NONE(""),
    @SerializedName("Inocente")
    @Expose
    INOCENT("Inocente"),
    @SerializedName("Amigable")
    @Expose
    FRIENDLY("Amigable"),
    @SerializedName("Sexual")
    @Expose
    SEXUAL("Sexual"),
    @SerializedName("Prohibida")
    @Expose
    FORBIDDEN("Prohibida"),
    @SerializedName("Prendas")
    @Expose
    CLOTHES("Prendas"),
    @SerializedName("Ñero")
    @Expose
    NERO("Ñero"),
    @SerializedName("Algo_tranqui")
    @Expose
    SOMETHING_RELAX("Algo tranqui"),
    @SerializedName("Normal")
    @Expose
    NORMAL("Normal"),
    @SerializedName("Extremo")
    @Expose
    EXTREAM("Extremo"),
    @SerializedName("A_morir")
    @Expose
    TO_DIE("A morir")
}