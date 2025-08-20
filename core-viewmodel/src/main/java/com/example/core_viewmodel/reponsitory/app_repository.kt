package com.example.core_viewmodel.reponsitory

import android.content.Context
import com.example.core_viewmodel.utils.data_store.getFirstTime
import com.example.core_viewmodel.utils.data_store.setFirstTime
import com.example.core_viewmodel.utils.exceptions.TFirebaseAuthException
import com.example.core_viewmodel.utils.exceptions.TFirebaseException
import com.example.core_viewmodel.utils.exceptions.TFormatException
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.first

class AppRepository(
    private val context: Context,
    private val onCallBack: () -> Unit = {},
    private  val onBoardingClick: () -> Unit = {}
) {
    private val firebaseAuth = FirebaseAuth.getInstance()
    val authUser: FirebaseUser? get() = firebaseAuth.currentUser

    suspend fun onReady() {
        screenRedirect()
    }

  suspend fun screenRedirect() {
        try {
            // Check if user is logged in
            val currentUser = authUser
            if (currentUser != null) {
                val isFirstTime = getFirstTime(context).first()
                if (isFirstTime) {
                    setFirstTime(context, false)
                    onBoardingClick()
                } else {
                    onCallBack()
                }
            }
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