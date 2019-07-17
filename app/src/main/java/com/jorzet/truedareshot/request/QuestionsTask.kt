package com.jorzet.truedareshot.request

import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.Query
import com.jorzet.truedareshot.models.Question


/**
 * Constants
 */
private const val TAG: String = "QuestionsTask"
private const val QUESTIONS_REFERENCE: String = "questions"

class QuestionsTask: AbstractRequestTask<String, List<Question>>() {

    override fun getReference(): String? {
        return QUESTIONS_REFERENCE
    }

    override fun keepSync(): Boolean {
        return true
    }

    override fun getQuery(): Query? {
        return getDatabaseInstance().orderByChild("category").equalTo("true")
    }

    override fun onGettingResponse(successResponse: DataSnapshot) {
        Log.d(TAG, successResponse.value.toString())

        val value = successResponse.value
        for (child in successResponse.children) {
            val childval = child.value
            Log.d(TAG, child.key)
        }
    }

    override fun onGettingError(errorResponse: DatabaseError) {
        Log.d(TAG, errorResponse.toException().toString())
    }

}