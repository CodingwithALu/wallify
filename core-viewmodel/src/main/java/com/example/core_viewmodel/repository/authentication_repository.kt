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
    // Logout
    fun logout() {
        try {
            firebaseAuth.signOut()
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