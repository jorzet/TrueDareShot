/*
 * Copyright [2018] [Jorge Zepeda Tinoco]
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

package com.jorzet.truedareshot.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * @author Jorge Zepeda Tinoco
 * @email jorzet.94@gmail.com
 * @date 08/11/18
 */

data class Question(
    @SerializedName("status")
    @Expose
    var status : Boolean = false,
    @SerializedName("subcategoria")
    @Expose
    var subcategoria : Subcategory,
    @SerializedName("question")
    @Expose
    var question : String,

    var questionId : Int = 0
)