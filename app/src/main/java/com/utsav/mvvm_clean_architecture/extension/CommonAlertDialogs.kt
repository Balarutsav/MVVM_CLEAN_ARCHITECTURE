package com.utsav.mvvm_clean_architecture.common

import android.Manifest
import android.app.Activity
import android.app.AlertDialog

import android.content.Context

import androidx.core.content.ContextCompat

import com.utsav.mvvm_clean_architecture.R
import com.utsav.mvvm_clean_architecture.extension.LoadingDialog

import java.util.*


fun Context.showAlertDialog(
    title: String = resources.getString(R.string.app_name),
    message: String ="Okay",
    isCancelAble: Boolean = false,
    positiveBtnText: String ="Okay",
    negativeBtnText: String? = null,
    negativeClick: (() -> Unit)? = null,
    positiveClick: (() -> Unit)? = null,
    positiveButtonColor:Int=ContextCompat.getColor(this,R.color.purple_200)
) {
    val builder = AlertDialog.Builder(this)
    builder.setTitle(title)
    builder.setMessage(message)
    builder.setCancelable(isCancelAble)


    builder.setPositiveButton(positiveBtnText) { _, _ ->
        positiveClick?.invoke()
    }
    negativeBtnText?.let {
        builder.setNegativeButton(negativeBtnText) { _, _ ->
            negativeClick?.invoke()
        }
    }
    val alertDialog = builder.create()
    alertDialog.show()

    alertDialog.getButton(AlertDialog.BUTTON_POSITIVE)
        .setTextColor(positiveButtonColor)
    alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE)
        .setTextColor(ContextCompat.getColor(this,R.color.black))
}

fun Context.showLoader(){
    LoadingDialog.showLoadDialog(this)
}

fun Context.hideLoader(){
    LoadingDialog.hideLoadDialog()
}





