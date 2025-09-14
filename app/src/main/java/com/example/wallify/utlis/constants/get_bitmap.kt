package com.example.wallify.utlis.constants

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import coil.imageLoader
import coil.request.ImageRequest
import coil.request.SuccessResult

suspend fun getBitmapFromUrl(context: Context, url: String): Bitmap? {
    val request = ImageRequest.Builder(context)
        .data(url)
        .allowHardware(false)
        .build()
    val result = context.imageLoader.execute(request)
    return if (result is SuccessResult) {
        (result.drawable as? BitmapDrawable)?.bitmap
    } else null
}