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

package com.jorzet.truedareshot.ui.dialogs

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.jorzet.truedareshot.R
import com.jorzet.truedareshot.models.enums.DialogType

/**
 * @author Jorge Zepeda Tinoco
 * @email jorzet.94@gmail.com
 * @date 27/05/19
 */

open class BaseDialog: DialogFragment() {
    /*
     * Objects
     */
    protected var mOnDialogListener : OnDialogListener? = null
    protected lateinit var mDialogType: DialogType

    /*
     * interface to set listeners
     */
    interface OnDialogListener {
        fun onConfirmationCancel()
        fun onConfirmationNeutral(arguments: Bundle)
        fun onConfirmationAccept(arguments: Bundle)
    }

    /**
     * Constants
     */
    companion object {
        const val REQUEST_CODE: String = "request_code"
        const val DIALOG_TYPE: String = "dialog_type"
        const val ARG_IS_LISTENER_ACTIVITY: String = "is_listener_activity"
        const val NICK_NAME: String = "nick_name"
        const val PLAYER_ID: String = "player_id"
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val inflater = LayoutInflater.from(context)
        val rootView = onCreateView(inflater, null, savedInstanceState)

        val dialog = Dialog(context!!, R.style.TDSDialog)
        dialog.setContentView(rootView!!)

        return dialog
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (arguments!!.getBoolean(ARG_IS_LISTENER_ACTIVITY)) {
            mOnDialogListener = activity as OnDialogListener
        } else {
            mOnDialogListener = targetFragment as OnDialogListener
        }
    }

    override fun onDetach() {
        super.onDetach()
        mOnDialogListener = null
    }

    override fun show(fragmentManager : FragmentManager, tag : String) {
        try {
            super.show(fragmentManager, tag)
        } catch (e: java.lang.Exception) {
        } catch (e: Exception) { }
    }

    override fun onCancel(dialog: DialogInterface?) {
        super.onCancel(dialog)
        if (mOnDialogListener != null) {
            mOnDialogListener!!.onConfirmationCancel()
        }
        dismiss()
    }

}