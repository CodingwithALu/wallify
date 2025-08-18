package com.example.core_model

import com.google.firebase.firestore.DocumentSnapshot

data class UserModel(
    val id: String,
    val username: String,
    val email: String,
    val firstName: String,
    val lastName: String,
    val phoneNumber: String,
    val profilePicture: String
) {

    val fullName: String
        get() = "$firstName $lastName"

    // Định nghĩa hàm format phone nếu bạn có formatter riêng
    val formattedPhoneNumber: String
        get() = formatPhoneNumber(phoneNumber)

    companion object {
        fun empty() = UserModel(
            id = "",
            username = "",
            email = "",
            firstName = "",
            lastName = "",
            phoneNumber = "",
            profilePicture = ""
        )

        // Tạo UserModel từ Firestore snapshot
        fun fromSnapshot(document: DocumentSnapshot): UserModel {
            val data = document.data ?: return empty()
            return UserModel(
                id = document.id,
                firstName = data["FirstName"] as? String ?: "",
                lastName = data["LastName"] as? String ?: "",
                username = data["Username"] as? String ?: "",
                email = data["Email"] as? String ?: "",
                phoneNumber = data["PhoneNumber"] as? String ?: "",
                profilePicture = data["ProfilePicture"] as? String ?: ""
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
        "Username" to username,
        "Email" to email,
        "PhoneNumber" to phoneNumber,
        "ProfilePicture" to profilePicture
    )
}

// Giả lập formatter cho số điện thoại (bạn có thể thay thế bằng formatter thật)
fun formatPhoneNumber(phone: String): String {
    // Ví dụ: "+84 123 456 789"
    return phone
}