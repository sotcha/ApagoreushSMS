package com.sotcha.apagoreushsms.ui

import android.content.Context
import android.content.DialogInterface
import androidx.appcompat.app.AlertDialog


class SimpleDialog(private val mContext: Context) {

    fun show(title: Int, message: Int) {
        val titleAsString: String = mContext.resources.getString(title)
        val messageAsString: String = mContext.resources.getString(message)
        show(titleAsString, messageAsString, null)
    }

    fun show(title: Int, message: String?) {
        val titleAsString: String = mContext.resources.getString(title)
        show(titleAsString, message, null)
    }

    fun show(title: String?, message: Int) {
        val messageAsString: String = mContext.resources.getString(message)
        show(title, messageAsString, null)
    }

    fun show(title: Int, message: Int, listener: DialogInterface.OnClickListener?) {
        val titleAsString: String = mContext.resources.getString(title)
        val messageAsString: String = mContext.resources.getString(message)
        show(titleAsString, messageAsString, listener)
    }

    fun show(
        title: Int,
        message: String?,
        listener: DialogInterface.OnClickListener?
    ) {
        val titleAsString: String = mContext.resources.getString(title)
        show(titleAsString, message, listener)
    }

    fun show(
        title: String?,
        message: Int,
        listener: DialogInterface.OnClickListener?
    ) {
        val messageAsString: String = mContext.resources.getString(message)
        show(title, messageAsString, listener)
    }

    @JvmOverloads
    fun show(
        title: String?,
        message: String?,
        listener: DialogInterface.OnClickListener? = null
    ) {
        val builder: AlertDialog.Builder = AlertDialog.Builder(mContext)
        builder.setMessage(message)
            .setTitle(title)
            .setPositiveButton(android.R.string.ok, listener)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }


}