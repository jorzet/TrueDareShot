package com.jorzet.truedareshot.views

import com.jorzet.truedareshot.models.Category

/**
 * @author Jorge Zepeda Tinoco
 * @email jorzet.94@gmail.com
 * @date 16/07/19
 */

interface ConfigurationView: BaseView {
    fun updateConfigurationData(categories: List<Category>)
}