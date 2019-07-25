/*
 * Copyright [2019] [Jorge Zepeda Tinoco]
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

package com.jorzet.truedareshot.request

import android.util.Log
import com.google.firebase.database.*
import com.google.gson.Gson
import com.jorzet.truedareshot.models.Subcategory
import com.jorzet.truedareshot.models.error.ErrorType
import com.jorzet.truedareshot.models.error.GenericError
import org.json.JSONObject
import java.lang.Exception
import java.util.ArrayList
import java.util.HashMap

/**
 * @author Jorge Zepeda Tinoco
 * @email jorzet.94@gmail.com
 * @date 16/07/19
 */

/**
 * Tags
 */
private const val TAG: String = "CategoriesTask"
private const val SUBCATEGORIES_REFERENCE: String = "subcategory"

class SubcategoryTask: AbstractRequestTask<Void, List<Subcategory>>() {

    override fun getReference(): String? {
        return SUBCATEGORIES_REFERENCE
    }

    override fun keepSync(): Boolean {
        return true
    }

    override fun onGettingResponse(successResponse: DataSnapshot) {
        val post = successResponse.value
        if (post != null) {
            try {
                val map = (post as HashMap<*, *>)
                Log.d(TAG, "subcategories size: " + map.size)
                val subcategories = ArrayList<Subcategory>()
                map.keys.forEach {
                    val subcategoryMap = map[it] as kotlin.collections.HashMap<*, *>
                    val subcategory = Gson().fromJson(JSONObject(subcategoryMap).toString(), Subcategory::class.java)
                    subcategory.subcategoryId = it.toString()
                    subcategories.add(subcategory)
                }

                if (subcategories.isNotEmpty()) {
                    Log.d(TAG, "subcategories success")
                    onRequestListenerSucces.onSuccess(subcategories)
                } else {
                    Log.d(TAG, "subcategories null response")
                    val error = GenericError(ErrorType.NULL_RESPONSE, "")
                    onRequestLietenerFailed.onFailed(error)
                }
            } catch (e: Exception) {
                Log.d(TAG, "subcategories null response")
                val error = GenericError(ErrorType.NULL_RESPONSE, "")
                onRequestLietenerFailed.onFailed(error)
            }
        } else {
            Log.d(TAG, "subcategories null response")
            val error = GenericError(ErrorType.NULL_RESPONSE, "")
            onRequestLietenerFailed.onFailed(error)
        }
    }

    override fun onGettingError(errorResponse: DatabaseError) {
        println("The read failed: " + errorResponse.code)
        onRequestLietenerFailed.onFailed(errorResponse.toException())
    }

}