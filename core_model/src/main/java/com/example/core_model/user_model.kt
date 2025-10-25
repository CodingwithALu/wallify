package com.example.core_model

import com.google.firebase.firestore.DocumentSnapshot

data class UserModel(
    val idToken: String,
    val email: String,
    val firstName: String,
    val lastName: String,
    val urlProfile: String,
    val urlBackground: String? = ""
) {

    val fullName: String
        get() = "$firstName $lastName"

    companion object {
        fun empty() = UserModel(
            idToken = "",
            email = "",
            firstName = "",
            lastName = "",
            urlProfile = "",
            urlBackground = ""
        )

        // Tạo UserModel từ Firestore snapshot
        fun fromSnapshot(document: DocumentSnapshot): UserModel {
            val data = document.data ?: return empty()
            return UserModel(
                idToken = document.id,
                firstName = data["FirstName"] as? String ?: "",
                lastName = data["LastName"] as? String ?: "",
                email = data["Email"] as? String ?: "",
                urlProfile = data["ProfilePicture"] as? String ?: ""
            )
        }

        // Tách tên
        fun nameParts(fullName: String): List<String> = fullName.split(" ")

        // Sinh username từ fullName
        fun generateUsername(fullName: String): String {
            val parts = fullName.trim().split(" ")
            val first = parts.getOrNull(0)?.lowercase() ?: ""
            val last = parts.getOrNull(1)?.lowercase() ?: ""
            return "cwt_${first}${last}"
        }
    }

    // Đưa về map để lưu Firestore
    fun toMap(): Map<String, Any> = mapOf(
        "FirstName" to firstName,
        "LastName" to lastName,
        "Email" to email,
        "ProfilePicture" to urlProfile
    )
}

// Giả lập formatter cho số điện thoại (bạn có thể thay thế bằng formatter thật)
fun formatPhoneNumber(phone: String): String {
    // Ví dụ: "+84 123 456 789"
    return phone
}