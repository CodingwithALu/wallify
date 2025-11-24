package com.example.wallify.feature.personalization.setting.reponsitory

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import com.example.wallify.feature.wallify.network.ApiClient
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Response

class SettingRepository(
    private val settingApi: SettingApi = ApiClient.settingApi,
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
    // Changed: accept file Uri, read bytes and send as binary body with proper content-type header
    suspend fun updateUrlBackGround(userId: String, fileUri: Uri): Response<String> {
        val cr = context.contentResolver
        val mime = cr.getType(fileUri) ?: "application/octet-stream"
        val fileName = getFileName(context, fileUri) ?: "upload.jpg"
        val input = cr.openInputStream(fileUri) ?: throw IllegalArgumentException("Cannot open uri: $fileUri")
        val bytes = input.use { it.readBytes() }
        val mediaType = mime.toMediaType()
        val body = bytes.toRequestBody(mediaType)
        return settingApi.updateUserBackground(userId, mime, fileName, body)
    }
    private fun getFileName(context: Context, uri: Uri): String? {
        var result: String? = null
        if (uri.scheme == "content") {
            val cursor = context.contentResolver.query(uri, null, null, null, null)
            cursor?.use {
                if (it.moveToFirst()) {
                    result =
                        it.getString(it.getColumnIndexOrThrow(android.provider.OpenableColumns.DISPLAY_NAME))
                }
            }
        }
        if (result == null) {
            result = uri.path?.substringAfterLast('/')
        }
        return result
    }
}