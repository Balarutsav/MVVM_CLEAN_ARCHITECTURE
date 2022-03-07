package com.utsav.mvvm_clean_architecture.presentation.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.utsav.mvvm_clean_architecture.BR
import com.utsav.mvvm_clean_architecture.R
import com.utsav.mvvm_clean_architecture.presentation.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity(
    override val layoutId: Int = R.layout.activity_login,
    override val bindingVariable: Int = BR.viewModel
) : BaseActivity<com.utsav.mvvm_clean_architecture.databinding.ActivityLoginBinding, LoginViewModel>() {


    override fun setUpObserver() {

    }
}