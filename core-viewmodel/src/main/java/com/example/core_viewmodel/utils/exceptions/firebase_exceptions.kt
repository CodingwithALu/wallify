package com.example.core_viewmodel.utils.exceptions

/**
 * Custom exception class to handle various Firebase-related errors.
 */
class TFirebaseException(val code: String) : Exception() {

    /**
     * Get the corresponding error message based on the error code.
     */
    val messages: String
        get() = when (code) {
            "unknown" -> "An unknown Firebase error occurred. Please try again."
            "invalid-custom-token" -> "The custom token format is incorrect. Please check your custom token."
            "custom-token-mismatch" -> "The custom token corresponds to a different audience."
            "user-disabled" -> "The user account has been disabled."
            "user-not-found" -> "No user found for the given email or UID."
            "invalid-email" -> "The email address provided is invalid. Please enter a valid email."
            "email-already-in-use" -> "The email address is already registered. Please use a different email."
            "wrong-password" -> "Incorrect password. Please check your password and try again."
            "weak-password" -> "The password is too weak. Please choose a stronger password."
            "provider-already-linked" -> "The account is already linked with another provider."
            "operation-not-allowed" -> "This operation is not allowed. Contact support for assistance."
            "invalid-credential" -> "The supplied credential is malformed or has expired."
            "invalid-verification-code" -> "Invalid verification code. Please enter a valid code."
            "invalid-verification-id" -> "Invalid verification ID. Please request a new verification code."
            "captcha-check-failed" -> "The reCAPTCHA response is invalid. Please try again."
            "app-not-authorized" -> "The app is not authorized to use Firebase Authentication with the provided API key."
            "keychain-error" -> "A keychain error occurred. Please check the keychain and try again."
            "internal-error" -> "An internal authentication error occurred. Please try again later."
            "invalid-app-credential" -> "The app credential is invalid. Please provide a valid app credential."
            "user-mismatch" -> "The supplied credentials do not correspond to the previously signed-in user."
            "requires-recent-login" -> "This operation is sensitive and requires recent authentication. Please log in again."
            "quota-exceeded" -> "Quota exceeded. Please try again later."
            "account-exists-with-different-credential" -> "An account already exists with the same email but different sign-in credentials."
            "missing-iframe-start" -> "The email template is missing the iframe start tag."
            "missing-iframe-end" -> "The email template is missing the iframe end tag."
            "missing-iframe-src" -> "The email template is missing the iframe src attribute."
            "auth-domain-config-required" -> "The authDomain configuration is required for the action code verification link."
            "missing-app-credential" -> "The app credential is missing. Please provide valid app credentials."
            "session-cookie-expired" -> "The Firebase session cookie has expired. Please sign in again."
            "uid-already-exists" -> "The provided user ID is already in use by another user."
            "web-storage-unsupported" -> "Web storage is not supported or is disabled."
            "app-deleted" -> "This instance of FirebaseApp has been deleted."
            "user-token-mismatch" -> "The provided user's token has a mismatch with the authenticated user's user ID."
            "invalid-message-payload" -> "The email template verification message payload is invalid."
            "invalid-sender" -> "The email template sender is invalid. Please verify the sender's email."
            "invalid-recipient-email" -> "The recipient email address is invalid. Please provide a valid recipient email."
            "missing-action-code" -> "The action code is missing. Please provide a valid action code."
            "user-token-expired" -> "The user's token has expired, and authentication is required. Please sign in again."
            "INVALID_LOGIN_CREDENTIALS" -> "Invalid login credentials."
            "expired-action-code" -> "The action code has expired. Please request a new action code."
            "invalid-action-code" -> "The action code is invalid. Please check the code and try again."
            "credential-already-in-use" -> "This credential is already associated with a different user account."
            else -> "An unexpected Firebase error occurred. Please try again."
        }
}