package com.utsav.mvvm_clean_architecture.extension

import android.app.Dialog
import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.view.Window

import com.utsav.mvvm_clean_architecture.R


object LoadingDialog {
    var dialog: Dialog? = null

    fun showLoadDialog(context: Context) {

        dialog?.let {
            if (it.isShowing) {
                try {
                    it.dismiss()
                } catch (e: Exception) {
                }
            }
        }
        dialog = Dialog(context)

        dialog?.apply {
            window!!.setBackgroundDrawable(ColorDrawable(android.graphics.Color.TRANSPARENT))
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            setCancelable(false)
            setContentView(R.layout.dialog_loading)
            show()
        }
    }

    fun hideLoadDialog() {
        try {
            dialog?.dismiss()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}