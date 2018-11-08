package com.jorzet.truedareshot.services.firebase

import android.app.Activity
import com.jorzet.truedareshot.models.Question

private const val TAG : String = "RequestManager"

class RequesManager(activity : Activity) {
    private val mActivity = activity

    interface OnGetQuestionListener {
        fun onGetQuestionLoaded(question: Question)
        fun onGetQuestionError(throwable: Throwable)
    }

    fun requestGetQuestion(onGetQuestionListener: OnGetQuestionListener) {

    }

}