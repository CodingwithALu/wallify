package com.example.core_viewmodel.reponsitory
import android.content.Context
import android.net.Uri
import android.util.Base64
import com.example.core_model.UserModel
import com.example.core_viewmodel.utils.exceptions.TFirebaseException
import com.example.core_viewmodel.utils.exceptions.TFormatException
import com.google.firebase.FirebaseException
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import okhttp3.FormBody
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.InputStream

class UserRepository(

) {
    private val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()

    private val cloudName = "dhl2sbjo5"
    private val uploadPreset = "t_stores"

    // Save user data to Firestore
    suspend fun saveUserRecord(user: UserModel) {
        try {
            firestore.collection("Users")
                .document(user.id)
                .set(user.toMap())
                .await()
        } catch (e: FirebaseException) {
            throw Exception(TFirebaseException(e.message ?: "unknown").message)
        } catch (e: IllegalArgumentException) {
            throw Exception(TFormatException().message)
        } catch (e: Exception) {
            throw Exception("Something went wrong. Please try again")
        }
    }

    // Fetch user details from Firestore
    suspend fun fetchUserDetails(userId: String): UserModel {
        try {
            val doc = firestore.collection("Users")
                .document(userId)
                .get().await()
            return if (doc.exists()) {
                UserModel.fromSnapshot(doc)
            } else {
                UserModel.empty()
            }
        } catch (e: FirebaseException) {
            throw Exception(TFirebaseException(e.message ?: "unknown").message)
        } catch (e: IllegalArgumentException) {
            throw Exception(TFormatException().message)
        } catch (e: Exception) {
            throw Exception("Something went wrong. Please try again")
        }
    }

    // Update user data in Firestore
    suspend fun updateUserRecord(userId: String, updateUser: UserModel) {
        try {
            firestore.collection("Users")
                .document(userId)
                .set(updateUser.toMap())
                .await()
        } catch (e: FirebaseException) {
            throw Exception(TFirebaseException(e.message ?: "unknown").message)
        } catch (e: IllegalArgumentException) {
            throw Exception(TFormatException().message)
        } catch (e: Exception) {
            throw Exception("Something went wrong. Please try again")
        }
    }

    // Remove user data from Firestore
    suspend fun removeUserRecord(userId: String) {
        try {
            firestore.collection("Users")
                .document(userId)
                .delete()
                .await()
        } catch (e: FirebaseException) {
            throw Exception(TFirebaseException(e.message ?: "unknown").message)
        } catch (e: IllegalArgumentException) {
            throw Exception(TFormatException().message)
        } catch (e: Exception) {
            throw Exception("Something went wrong. Please try again")
        }
    }

    // Update any field in Users collection
    suspend fun updateSingleField(userId: String, fields: Map<String, Any>) {
        try {
            firestore.collection("Users")
                .document(userId)
                .update(fields)
                .await()
        } catch (e: FirebaseException) {
            throw Exception(TFirebaseException(e.message ?: "unknown").message)
        } catch (e: IllegalArgumentException) {
            throw Exception(TFormatException().message)
        } catch (e: Exception) {
            throw Exception("Something went wrong. Please try again")
        }
    }

    // Upload image to Cloudinary (optionally delete old image first)
//    suspend fun uploadImage(
//        path: String,
//        imageUri: Uri,
//        oldImageUrl: String? = null
//    ): String {
//        try {
//            if (!oldImageUrl.isNullOrEmpty()) {
//                deleteImageFromCloudinary(oldImageUrl)
//            }
//            val bytes = readBytesFromUri(imageUri)
//            val url = uploadImageToCloudinary(
//                file = bytes,
//                path = path,
//                imageName = getFileNameFromUri(imageUri)
//            )
//            return url
//        } catch (e: Exception) {
//            throw Exception("Something went wrong. Please try again!")
//        }
//    }

    // Helper: read bytes from Uri
//    private fun readBytesFromUri(uri: Uri): ByteArray {
//        val inputStream: InputStream? = context.contentResolver.openInputStream(uri)
//        return inputStream?.readBytes() ?: throw Exception("Cannot read image data")
//    }

    // Helper: get file name from Uri
    private fun getFileNameFromUri(uri: Uri): String {
        // You may need to improve this for production
        val path = uri.path ?: ""
        return path.substringAfterLast("/")
    }

    // Delete image from Cloudinary (demo only, real implementation needs backend for security)
    suspend fun deleteImageFromCloudinary(imageUrl: String) {
        // For demo: just extract public_id and log it, real deletion requires API key/secret (should be on backend)
        val uri = Uri.parse(imageUrl)
        val segments = uri.pathSegments
        val uploadIndex = segments.indexOf("upload")
        if (uploadIndex == -1 || uploadIndex + 1 >= segments.size) return
        val publicId = segments.subList(uploadIndex + 1, segments.size)
            .joinToString("/")
            .replace(".jpg", "")
            .replace(".png", "")
        // For security, do not call Cloudinary delete API from client app!
        // Instead, call your backend/cloud function to perform the delete.
        // For now, just log:
        println("Delete Cloudinary public_id: $publicId")
    }

    // Upload image to Cloudinary, return url
    suspend fun uploadImageToCloudinary(
        file: ByteArray,
        path: String,
        imageName: String
    ): String {
        val url = "https://api.cloudinary.com/v1_1/$cloudName/image/upload"
        val encodedImage = Base64.encodeToString(file, Base64.DEFAULT)
        val client = OkHttpClient()
        val requestBody = FormBody.Builder()
            .add("file", "data:image/png;base64,$encodedImage")
            .add("upload_preset", uploadPreset)
            .add("public_id", "$path/$imageName".removePrefix("/"))
            .build()

        val request = Request.Builder()
            .url(url)
            .post(requestBody)
            .build()

        client.newCall(request).execute().use { response ->
            if (!response.isSuccessful) {
                throw Exception("Upload error: [${response.code}] ${response.body?.string()}")
            }
            val json = response.body?.string() ?: ""
            // Parse json to get secure_url
            val secureUrl = Regex("\"secure_url\"\\s*:\\s*\"([^\"]+)\"")
                .find(json)?.groupValues?.get(1)
            return secureUrl ?: throw Exception("No secure_url returned from Cloudinary")
        }
    }
}