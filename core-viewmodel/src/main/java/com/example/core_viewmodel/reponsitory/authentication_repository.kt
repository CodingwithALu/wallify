package com.example.core_viewmodel.reponsitory

import android.content.Context
import com.example.core_viewmodel.utils.exceptions.TFirebaseAuthException
import com.example.core_viewmodel.utils.exceptions.TFirebaseException
import com.example.core_viewmodel.utils.exceptions.TFormatException
import com.google.firebase.auth.*
import com.google.firebase.FirebaseException
import kotlinx.coroutines.tasks.await

class AuthenticationRepository(
    private val context: Context,
    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
) {
    val authUser: FirebaseUser?
        get() = firebaseAuth.currentUser

    // Email/Password Sign In
    suspend fun loginWithEmailAndPassword(email: String, password: String): AuthResult {
        try {
            return firebaseAuth.signInWithEmailAndPassword(email, password).await()
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

    // Email/Password Register
    suspend fun registerWithEmailAndPassword(email: String, password: String): AuthResult {
        try {
            return firebaseAuth.createUserWithEmailAndPassword(email, password).await()
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

    // Send email verification
    suspend fun sendEmailVerification() {
        try {
            firebaseAuth.currentUser?.sendEmailVerification()?.await()
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

    // Forgot password
    suspend fun sendPasswordResetEmail(email: String) {
        try {
            firebaseAuth.sendPasswordResetEmail(email).await()
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

    // Google Authentication (truyền credential từ UI)
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
    suspend fun logout() {
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