package com.utsav.mvvm_clean_architecture.presentation.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.utsav.mvvm_clean_architecture.BR
import com.utsav.mvvm_clean_architecture.R
import com.utsav.mvvm_clean_architecture.common.hideLoader
import com.utsav.mvvm_clean_architecture.common.showAlertDialog
import com.utsav.mvvm_clean_architecture.common.showLoader
import com.utsav.mvvm_clean_architecture.data.remote.ApiResources
import com.utsav.mvvm_clean_architecture.presentation.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class LoginActivity(
    override val layoutId: Int = R.layout.activity_login,
    override val bindingVariable: Int = BR.viewModel
) : BaseActivity<com.utsav.mvvm_clean_architecture.databinding.ActivityLoginBinding, LoginViewModel>() {

companion object{
     val TAG=LoginActivity::class.java.name
}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }
    fun init(){

    }
    override fun setUpObserver() {
        lifecycleScope.launchWhenCreated {
            mViewModel.mState.collect{
            when(it.status){
                ApiResources.Status.SUCCESS -> {
                    hideLoader()
                    it.message?.let { it1 -> showAlertDialog(message = it1) }
                    Log.e(TAG," Status : Success")

                }
                ApiResources.Status.ERROR -> {
                    hideLoader()
                    it.message?.let { it1 -> showAlertDialog(message = it1) }
                    Log.e(TAG," Status : Error")
                }
                ApiResources.Status.LOADING ->{
                    showLoader()
                    Log.e(TAG," Status : Loading")
                }
                ApiResources.Status.NO_INTERNET_CONNECTION -> {}
                ApiResources.Status.UNKNOWN -> {}
                ApiResources.Status.SHIMMER_EFFECT -> {}
            }
            }
        }
    }


}