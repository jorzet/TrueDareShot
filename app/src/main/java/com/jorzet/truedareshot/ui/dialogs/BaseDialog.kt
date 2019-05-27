package com.jorzet.truedareshot.ui.dialogs

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v4.app.FragmentActivity
import android.support.v4.app.FragmentManager
import android.view.LayoutInflater
import com.jorzet.truedareshot.R
import com.jorzet.truedareshot.models.enums.DialogType

open class BaseDialog: DialogFragment() {
    /*
     * Objects
     */
    private var mOnDialogListener : OnDialogListener? = null
    private lateinit var mDialogType: DialogType

    /*
     * interface to set listeners
     */
    interface OnDialogListener {
        fun onConfirmationCancel()
        fun onConfirmationNeutral()
        fun onConfirmationAccept()
    }

    companion object {
        fun newInstance() {

        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val inflater = LayoutInflater.from(activity)
        val rootView = onCreateView(inflater, null, savedInstanceState)

        val dialog = Dialog(activity, R.style.TDSDialog)
        dialog.setContentView(rootView)

        return dialog
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is FragmentActivity) {
            mOnDialogListener = activity as OnDialogListener;
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
    }

}