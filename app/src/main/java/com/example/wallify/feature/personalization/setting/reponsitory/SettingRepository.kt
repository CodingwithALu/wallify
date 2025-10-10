package com.example.wallify.feature.personalization.setting.reponsitory

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri

class SettingRepository(
    val context: Context
) {
    @SuppressLint("UseKtx")
    fun sendFeedback(userEmail: String) {
        val intent = Intent(Intent.ACTION_SEND).apply {
            type = "message/rfc822"
            putExtra(Intent.EXTRA_EMAIL, arrayOf("laualu2k3@gmail.com"))
            putExtra(Intent.EXTRA_SUBJECT, "Feedback for Wallify")
            putExtra(Intent.EXTRA_TEXT, "Sender: $userEmail\n\n")
            setPackage("com.google.android.gm")
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        context.startActivity(intent)
    }
}