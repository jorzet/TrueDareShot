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

import android.app.Activity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import com.jorzet.truedareshot.R
import com.jorzet.truedareshot.models.enums.DialogType
import com.jorzet.truedareshot.utils.Utils

/**
 * @author Jorge Zepeda Tinoco
 * @email jorzet.94@gmail.com
 * @date 27/05/19
 */

class AddEditPlayerDialog: BaseDialog() {

    /**
     * UI accessors
     */
    private lateinit var mNickNameEditText: EditText
    private lateinit var mColorProgressBar: ProgressBar
    private lateinit var mCancelButton: Button
    private lateinit var mAcceptButton: Button

    companion object {
        /**
         * Creates an instance of [AddEditPlayerDialog]
         * @param requestCode identifier request code [Int]
         * @param playerNickName player nick name in [String]
         * @param playerId player identifier [String]
         * @param dialogType dialog type [DialogType]
         * @param onDialogListener dialog action listener [OnDialogListener]
         * @return An instance of [AddEditPlayerDialog]
         */
        fun newInstance(requestCode: Int, playerNickName: String?, playerId: String?, dialogType: DialogType,
                        onDialogListener: OnDialogListener) : AddEditPlayerDialog {
            val addEditPlayerDialog = AddEditPlayerDialog()
            val bundle = Bundle()

            bundle.putInt(REQUEST_CODE, requestCode)
            bundle.putString(PLAYER_ID, playerId)
            bundle.putString(NICK_NAME, playerNickName)

            if (onDialogListener is Activity) {
                bundle.putBoolean(ARG_IS_LISTENER_ACTIVITY, true)
            } else {
                bundle.putBoolean(ARG_IS_LISTENER_ACTIVITY, false)
                addEditPlayerDialog.setTargetFragment(onDialogListener as Fragment, 0)
            }

            bundle.putSerializable(DIALOG_TYPE, dialogType)
            addEditPlayerDialog.arguments = bundle
            return addEditPlayerDialog
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val rootView = inflater.inflate(R.layout.custom_add_edit_dialog, container, false)

        mNickNameEditText = rootView.findViewById(R.id.et_player_nick_name)
        mColorProgressBar = rootView.findViewById(R.id.pb_choose_color)
        mCancelButton = rootView.findViewById(R.id.btn_cancel)
        mAcceptButton = rootView.findViewById(R.id.btn_save_player)

        mCancelButton.setOnClickListener(mCancelButtonListener)
        mAcceptButton.setOnClickListener(mAcceptButtonListener)

        if (arguments != null) {
            mDialogType = (arguments as Bundle).getSerializable(DIALOG_TYPE) as DialogType
            val nickName = (arguments as Bundle).getString(NICK_NAME)

            if (nickName != null) {
                val playerNickName = Utils.unicodeToEmoji(nickName)
                mNickNameEditText.setText(playerNickName)
            }
        }

        return rootView
    }

    private val mCancelButtonListener = View.OnClickListener {
        onCancel(dialog)
    }

    private val mAcceptButtonListener = View.OnClickListener {
        val bundle = Bundle()
        if (arguments != null) {
            bundle.putInt(REQUEST_CODE, arguments!!.getInt(REQUEST_CODE))
            bundle.putString(PLAYER_ID, arguments!!.getString(PLAYER_ID))
        }

        bundle.putString(NICK_NAME, mNickNameEditText.text.toString())

        mOnDialogListener?.onConfirmationAccept(bundle)
        dismiss()
    }

}