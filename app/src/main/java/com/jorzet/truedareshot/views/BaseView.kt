package com.jorzet.truedareshot.views

import android.app.Activity

interface BaseView {
    /**
     * This method gives current base context
     * @return Base Activity context
     */
    fun getBaseContext(): Activity
}