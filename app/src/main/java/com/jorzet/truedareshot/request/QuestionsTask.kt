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
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.Query
import com.google.gson.Gson
import com.jorzet.truedareshot.models.Question
import com.jorzet.truedareshot.models.error.ErrorType
import com.jorzet.truedareshot.models.error.GenericError
import org.json.JSONObject
import java.util.HashMap

/**
 * @author Jorge Zepeda Tinoco
 * @email jorzet.94@gmail.com
 * @date 17/07/19.
 */

/**
 * Constants
 */
private const val TAG: String = "QuestionsTask"
private const val QUESTIONS_REFERENCE: String = "questions"
private const val CATEGORY_REFERENCE: String = "category"

class QuestionsTask(category: String, subcategory: String): AbstractRequestTask<String, List<Question>>() {

    /**
     * Attributes
     */
    private val mCategory: String = category
    private val mSubcategory: String = subcategory

    override fun getReference(): String? {
        return QUESTIONS_REFERENCE
    }

    override fun keepSync(): Boolean {
        return true
    }

    override fun getQuery(): Query? {
        return getDatabaseInstance()
            .orderByChild(CATEGORY_REFERENCE).equalTo(mCategory)
    }

    override fun onGettingResponse(successResponse: DataSnapshot) {
        val post = successResponse.value

        if (post != null) {
            try {
                val list = (post as List<*>)
                val questions = arrayListOf<Question>()

                for (item in list) {
                    if (item != null) {
                        val itemMap = item as HashMap<*, *>
                        val question = Gson().fromJson(JSONObject(itemMap).toString(), Question::class.java)
                        if (question != null && question.subcategory == mSubcategory) {
                            questions.add(question)
                        }
                    }
                }

                if (questions.isNotEmpty()) {
                    Log.d(TAG, "categories success")
                    onRequestListenerSucces.onSuccess(questions)
                } else {
                    Log.d(TAG, "categories null response")
                    val error = GenericError(ErrorType.NULL_RESPONSE, "")
                    onRequestLietenerFailed.onFailed(error)
                }
            } catch (e: Exception) {
                Log.d(TAG, "questions null response")
                val error = GenericError(ErrorType.NULL_RESPONSE, "")
                onRequestLietenerFailed.onFailed(error)
            }
        } else {
            Log.d(TAG, "questions null response")
            val error = GenericError(ErrorType.NULL_RESPONSE, "")
            onRequestLietenerFailed.onFailed(error)
        }
    }

    override fun onGettingError(errorResponse: DatabaseError) {
        Log.d(TAG, errorResponse.toException().toString())
    }

}