package com.jinyeob.nathanks

import android.app.AlertDialog
import android.content.Context
import androidx.annotation.StringRes
import com.jinyeob.nathanks.MainApplication.Companion.isOnline

object BasicDialog {
    private var noInternetAlertDialog: AlertDialog? = null

    fun checkOnlineAndShowDialog(context: Context?): Boolean {
        if (context == null) return false

        if (isOnline()) {
            return true
        }

        if (noInternetAlertDialog == null || noInternetAlertDialog?.context != context) {
            noInternetAlertDialog = AlertDialog.Builder(context).apply {
                setTitle(R.string.load_fail_title)
                setMessage(R.string.load_fail_message)
                setPositiveButton(R.string.load_fail_confirm) { _, _ -> }
            }.create()
        }

        if (noInternetAlertDialog?.isShowing == true) {
            noInternetAlertDialog?.dismiss()
        }

        noInternetAlertDialog?.show()

        return false
    }

    fun showAlertDialog(
        context: Context?,
        @StringRes title: Int,
        @StringRes message: Int,
        @StringRes positiveButton: Int? = null,
        @StringRes negativeButton: Int? = null,
        positiveListener: (() -> Unit)? = null,
        negativeListener: (() -> Unit)? = null
    ) {
        if (context == null) return

        AlertDialog.Builder(context).apply {
            setTitle(context.getString(title))
            setMessage(context.getString(message))
            setPositiveButton(positiveButton?.let { context.getString(it) }) { _, _ -> positiveListener?.let { it() } }
            setNegativeButton(negativeButton?.let { context.getString(it) }) { _, _ -> negativeListener?.let { it() } }
        }.create().show()
    }

    fun showAlertDialog(
        context: Context?,
        title: String,
        message: String,
        positiveButton: String? = null,
        negativeButton: String? = null,
        positiveListener: (() -> Unit)? = null,
        negativeListener: (() -> Unit)? = null
    ) {
        if (context == null) return

        AlertDialog.Builder(context).apply {
            setTitle(title)
            setMessage(message)
            setPositiveButton(positiveButton) { _, _ -> positiveListener?.let { it() } }
            setNegativeButton(negativeButton) { _, _ -> negativeListener?.let { it() } }
        }.create().show()
    }
}
