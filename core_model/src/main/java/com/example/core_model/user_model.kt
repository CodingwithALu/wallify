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
            email = "Sign in below to get started",
            firstName = "Save & Sync",
            lastName = "Your Favorites",
            urlProfile = "https://wallify-s3-01.s3.us-east-1.amazonaws.com/image/user/person_circle_outline.png",
            urlBackground = "https://wallify-s3-01.s3.us-east-1.amazonaws.com/image/user/photo-1729575846511-f499d2e17d79.jpg"
        )
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
        fun nameParts(fullName: String): List<String> = fullName.split(" ")
        fun generateUsername(fullName: String): String {
            val parts = fullName.trim().split(" ")
            val first = parts.getOrNull(0)?.lowercase() ?: ""
            val last = parts.getOrNull(1)?.lowercase() ?: ""
            return "cwt_${first}${last}"
        }
    }
    fun toMap(): Map<String, Any> = mapOf(
        "FirstName" to firstName,
        "LastName" to lastName,
        "Email" to email,
        "ProfilePicture" to urlProfile
    )
}
fun formatPhoneNumber(phone: String): String {
    return phone
}