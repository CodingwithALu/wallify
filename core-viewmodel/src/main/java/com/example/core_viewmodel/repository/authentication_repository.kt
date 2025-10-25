package com.example.core_viewmodel.repository

import com.example.core_viewmodel.utils.exceptions.TFirebaseAuthException
import com.example.core_viewmodel.utils.exceptions.TFirebaseException
import com.example.core_viewmodel.utils.exceptions.TFormatException
import com.google.firebase.auth.*
import com.google.firebase.FirebaseException
import kotlinx.coroutines.tasks.await
class AuthenticationRepository() {
    private val firebaseAuth = FirebaseAuth.getInstance()
    suspend fun signInWithGoogle(idToken: String, accessToken: String): AuthResult {
        try {
            val credential = GoogleAuthProvider.getCredential(idToken, accessToken)
            return firebaseAuth.signInWithCredential(credential).await()
        } catch (e: FirebaseAuthException) {
            throw Exception(TFirebaseAuthException(e.errorCode).message)
        } catch (e: FirebaseException) {
            throw Exception(TFirebaseException(e.message ?: "unknown").message)
        } catch (e: IllegalArgumentException) {
            throw Exception(TFormatException().message)
        } catch (e: Exception) {
            throw Exception("Something went wrong. Please try again.")
        }
    }

    // Delete Account (Auth + Firestore)
    suspend fun deleteAccount(userRepository: UserRepository) {
        try {
            val userId = firebaseAuth.currentUser?.uid ?: throw Exception("No user logged in")
            userRepository.removeUserRecord(userId)
            firebaseAuth.currentUser?.delete()?.await()
        } catch (e: FirebaseAuthException) {
            throw Exception(TFirebaseAuthException(e.errorCode).message)
        } catch (e: FirebaseException) {
            throw Exception(TFirebaseException(e.message ?: "unknown").message)
        } catch (e: IllegalArgumentException) {
            throw Exception(TFormatException().message)
        } catch (e: Exception) {
            throw Exception("Something went wrong. Please try again.")
        }
    }

    // Logout
    fun logout() {
        try {
            firebaseAuth.signOut()
            // GoogleSignIn signOut nếu cần, thực hiện ở UI layer
        } catch (e: FirebaseAuthException) {
            throw Exception(TFirebaseAuthException(e.errorCode).message)
        } catch (e: FirebaseException) {
            throw Exception(TFirebaseException(e.message ?: "unknown").message)
        } catch (e: IllegalArgumentException) {
            throw Exception(TFormatException().message)
        } catch (e: Exception) {
            throw Exception("Something went wrong. Please try again.")
        }
    }

    // Re-authenticate
    suspend fun reAuthenticationWithEmailAndPassword(email: String, password: String) {
        try {
            val credential = EmailAuthProvider.getCredential(email, password)
            firebaseAuth.currentUser?.reauthenticate(credential)?.await()
        } catch (e: FirebaseAuthException) {
            throw Exception(TFirebaseAuthException(e.errorCode).message)
        } catch (e: FirebaseException) {
            throw Exception(TFirebaseException(e.message ?: "unknown").message)
        } catch (e: IllegalArgumentException) {
            throw Exception(TFormatException().message)
        } catch (e: Exception) {
            throw Exception("Something went wrong. Please try again.")
        }
    }
}