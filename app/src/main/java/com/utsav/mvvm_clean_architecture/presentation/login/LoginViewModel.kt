package com.utsav.mvvm_clean_architecture.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.utsav.mvvm_clean_architecture.data.remote.ApiResources
import com.utsav.mvvm_clean_architecture.data.remote.dto.LoginDTO
import com.utsav.mvvm_clean_architecture.domain.use_cases.LoginUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class LoginViewModel @Inject constructor(private val loginUseCase: LoginUseCase) : ViewModel() {
    private val state = MutableStateFlow<ApiResources<LoginDTO>>(ApiResources.unknown())
    val mState: StateFlow<ApiResources<LoginDTO>> get() = state
    var email = ""
    var password = ""

    init {

    }

    fun login() {
        val requestMap: HashMap<String, String> = hashMapOf()
        requestMap["device_type"] = "123akjfdklasdjfk"
        requestMap["device_name"] = "android"
        requestMap["app_version"] = "app_version"
        requestMap["device_token"] = "123412341234321412341234"
        requestMap["email"] =
            email
        requestMap["password"] = password
        loginUseCase(requestMap).onEach { result ->
            state.value = result
        }.launchIn(viewModelScope)
    }


}

