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

class AddEditPlayerDialog: BaseDialog() {

    private lateinit var mNickNameEditText: EditText
    private lateinit var mColorProgressBar: ProgressBar
    private lateinit var mCancelButton: Button
    private lateinit var mAcceptButton: Button

    companion object {
        fun newInstance(requestCode: Int, playerId: String?, dialogType: DialogType, onDialogListener: OnDialogListener) : AddEditPlayerDialog {
            val addEditPlayerDialog = AddEditPlayerDialog()
            val bundle = Bundle()

            bundle.putInt(REQUEST_CODE, requestCode)
            bundle.putString(PLAYER_ID, playerId)

            if (onDialogListener is Activity) {
                bundle.putBoolean(ARG_IS_LISTENER_ACTIVITY, true)
            } else {
                bundle.putBoolean(ARG_IS_LISTENER_ACTIVITY, false)
                addEditPlayerDialog.setTargetFragment(onDialogListener as Fragment, 0)
            }

            bundle.putSerializable(DIALOG_TYPE, dialogType)
            addEditPlayerDialog.arguments = bundle
            return addEditPlayerDialog;
        }
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView : View?

        rootView = inflater.inflate(R.layout.custom_add_edit_dialog, container, false)

        mNickNameEditText = rootView.findViewById(R.id.et_player_nick_name)
        mColorProgressBar = rootView.findViewById(R.id.pb_choose_color)
        mCancelButton = rootView.findViewById(R.id.btn_cancel)
        mAcceptButton = rootView.findViewById(R.id.btn_save_player)

        mCancelButton.setOnClickListener(mCancelButtonListener)
        mAcceptButton.setOnClickListener(mAcceptButtonListener)

        if (arguments != null) {
            val dialogType = (arguments as Bundle).getSerializable(DIALOG_TYPE)
        }

        return rootView
    }

    override fun onResume() {
        super.onResume()
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