package com.utsav.mvvm_clean_architecture.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.plcoding.cryptocurrencyappyt.common.Resource
import com.utsav.mvvm_clean_architecture.data.remote.ApiResources
import com.utsav.mvvm_clean_architecture.data.remote.dto.LoginDTO
import com.utsav.mvvm_clean_architecture.domain.use_cases.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginViewModel @Inject constructor(private val loginUseCase: LoginUseCase) : ViewModel() {
    private val state = MutableStateFlow<LoginActivityState>(LoginActivityState.Init)
    val mState: StateFlow<LoginActivityState> get() = state


    private fun setLoading() {
        state.value = LoginActivityState.IsLoading(true)
    }

    private fun hideLoading() {
        state.value = LoginActivityState.IsLoading(false)
    }

    private fun showToast(message: String) {
        state.value = LoginActivityState.ShowToast(message)
    }


    fun login() {
        loginUseCase(HashMap()).onEach { result ->
            when (result.status) {
                ApiResources.Status.SUCCESS -> {
                    state.value = result.data?.let { LoginActivityState.SuccessLogin(it) }!!

                }
                ApiResources.Status.ERROR -> {
                    state.value = result.message?.let { LoginActivityState.ErrorLogin(it) }!!
                }
                ApiResources.Status.LOADING -> {
                    state.value = LoginActivityState.IsLoading(true)
                }
                ApiResources.Status.NO_INTERNET_CONNECTION -> {

                }
                ApiResources.Status.UNKNOWN -> {

                }
                ApiResources.Status.SHIMMER_EFFECT -> {

                }
            }
        }.launchIn(viewModelScope)
    }


}

sealed class LoginActivityState {
    object Init : LoginActivityState()
    data class IsLoading(val isLoading: Boolean) : LoginActivityState()
    data class ShowToast(val message: String) : LoginActivityState()
    data class SuccessLogin(val loginEntity: LoginDTO) : LoginActivityState()
    data class ErrorLogin(val rawResponse: String) : LoginActivityState()
}