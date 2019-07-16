package com.jorzet.truedareshot.views

import com.jorzet.truedareshot.models.Category

interface ConfigurationView: BaseView {
    fun updateConfigurationData(categories: List<Category>)
}