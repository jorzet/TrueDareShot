package com.jorzet.truedareshot.views

import android.app.Activity

/**
 * @author Jorge Zepeda Tinoco
 * @email jorzet.94@gmail.com
 * @date 16/07/19
 */

interface BaseView {
    /**
     * This method gives current base context
     * @return Base Activity context
     */
    fun getBaseContext(): Activity
}