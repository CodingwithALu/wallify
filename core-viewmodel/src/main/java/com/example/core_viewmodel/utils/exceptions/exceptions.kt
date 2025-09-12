package com.example.core_viewmodel.utils.exceptions

/**
 * Exception class for handling various errors.
 */
class TExceptions(val messages: String = "An unexpected error occurred. Please try again.") : Exception(messages) {

    companion object {
        /**
         * Create an authentication exception from a Firebase authentication exception code.
         */
        fun fromCode(code: String?): TExceptions {
            return when (code) {
                "email-already-in-use" -> TExceptions("The email address is already registered. Please use a different email.")
                "invalid-email" -> TExceptions("The email address provided is invalid. Please enter a valid email.")
                "weak-password" -> TExceptions("The password is too weak. Please choose a stronger password.")
                "user-disabled" -> TExceptions("This user account has been disabled. Please contact support for assistance.")
                "user-not-found" -> TExceptions("Invalid login details. User not found.")
                "wrong-password" -> TExceptions("Incorrect password. Please check your password and try again.")
                "INVALID_LOGIN_CREDENTIALS" -> TExceptions("Invalid login credentials. Please double-check your information.")
                "too-many-requests" -> TExceptions("Too many requests. Please try again later.")
                "invalid-argument" -> TExceptions("Invalid argument provided to the authentication method.")
                "invalid-password" -> TExceptions("Incorrect password. Please try again.")
                "invalid-phone-number" -> TExceptions("The provided phone number is invalid.")
                "operation-not-allowed" -> TExceptions("The sign-in provider is disabled for your Firebase project.")
                "session-cookie-expired" -> TExceptions("The Firebase session cookie has expired. Please sign in again.")
                "uid-already-exists" -> TExceptions("The provided user ID is already in use by another user.")
                "sign_in_failed" -> TExceptions("Sign-in failed. Please try again.")
                "network-request-failed" -> TExceptions("Network request failed. Please check your internet connection.")
                "internal-error" -> TExceptions("Internal error. Please try again later.")
                "invalid-verification-code" -> TExceptions("Invalid verification code. Please enter a valid code.")
                "invalid-verification-id" -> TExceptions("Invalid verification ID. Please request a new verification code.")
                "quota-exceeded" -> TExceptions("Quota exceeded. Please try again later.")
                else -> TExceptions()
            }
        }
    }
}