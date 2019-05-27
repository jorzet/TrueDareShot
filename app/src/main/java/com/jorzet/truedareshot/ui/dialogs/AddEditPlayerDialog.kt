package com.jorzet.truedareshot.ui.dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jorzet.truedareshot.R

class AddEditPlayerDialog: BaseDialog() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView : View?

        rootView = inflater.inflate(R.layout.custom_add_edit_dialog, container, false)


        return rootView
    }
}