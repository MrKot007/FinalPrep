package com.example.thirditeration

import android.content.Context
import androidx.appcompat.app.AlertDialog

class Utils {
    companion object {
        fun showAlertDialog(context: Context, reason: String) {
            AlertDialog.Builder(context)
                .setMessage(reason)
                .setTitle("Error occured!")
                .setPositiveButton("OK", null)
                .create().show()
        }
    }
}