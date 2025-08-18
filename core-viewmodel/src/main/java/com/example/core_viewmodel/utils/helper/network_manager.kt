package com.example.core_viewmodel.utils.helper

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import androidx.compose.material3.SnackbarHostState
import androidx.lifecycle.ViewModel
import com.example.core_viewmodel.utils.popups.TLoaders
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class NetworkManager(context: Context,
                     private val `snack-barHostState`: SnackbarHostState,
                     private val coroutineScope: CoroutineScope) : ViewModel() {
    private val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    // StateFlow để quan sát trạng thái mạng (Compose có thể collectAsState)
    private val _isConnected = MutableStateFlow(checkConnection())
    val isConnected: StateFlow<Boolean> get() = _isConnected

    private val networkCallback = object : ConnectivityManager.NetworkCallback() {
        override fun onAvailable(network: Network) {
            super.onAvailable(network)
            _isConnected.value = true
        }

        override fun onLost(network: Network) {
            super.onLost(network)
            _isConnected.value = checkConnection()
            if (!_isConnected.value) {
                // Tuỳ bạn muốn show loader/toast ra sao, ví dụ:
                TLoaders.customToast(
                    scope = coroutineScope,
                    snackbarHostState = `snack-barHostState`,
                    message = "No Internet Connection")
            }
        }
    }

    init {
        // Đăng ký callback để lắng nghe trạng thái mạng thay đổi
        val request = NetworkRequest.Builder()
            .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            .build()
        connectivityManager.registerNetworkCallback(request, networkCallback)
    }

    // Kiểm tra kết nối mạng hiện tại
    fun checkConnection(): Boolean {
        val activeNetwork = connectivityManager.activeNetwork
        val capabilities = connectivityManager.getNetworkCapabilities(activeNetwork)
        return capabilities?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) == true
    }

    // Có thể gọi hàm này từ ViewModel/Screen để check mạng thủ công
    suspend fun isConnectedNow(): Boolean {
        return _isConnected.value
    }

    // Bỏ đăng ký callback khi không cần nữa (nên gọi từ ViewModel.onCleared)
    override fun onCleared() {
        super.onCleared()
        connectivityManager.unregisterNetworkCallback(networkCallback)
    }
}