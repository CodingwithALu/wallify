package com.example.core_viewmodel.utils.exceptions

/**
 * Custom exception class to handle various format-related errors.
 */
class TFormatException(val messages: String = "An unexpected format error occurred. Please check your input.") : Exception(messages) {

    companion object {

        /**
         * Create a format exception from a specific error message.
         */
        fun fromMessage(message: String): TFormatException {
            return TFormatException(message)
        }

        /**
         * Create a format exception from a specific error code.
         */
        fun fromCode(code: String): TFormatException {
            return when (code) {
                "invalid-email-format" -> TFormatException("The email address format is invalid. Please enter a valid email.")
                "invalid-phone-number-format" -> TFormatException("The provided phone number format is invalid. Please enter a valid number.")
                "invalid-date-format" -> TFormatException("The date format is invalid. Please enter a valid date.")
                "invalid-url-format" -> TFormatException("The URL format is invalid. Please enter a valid URL.")
                "invalid-credit-card-format" -> TFormatException("The credit card format is invalid. Please enter a valid credit card number.")
                "invalid-numeric-format" -> TFormatException("The input should be a valid numeric format.")
                // Add more cases as needed...
                else -> TFormatException()
            }
        }
    }

    /**
     * Get the corresponding error message.
     */
    val formattedMessage: String
        get() = messages
}