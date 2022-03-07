package com.utsav.mvvm_clean_architecture.presentation.base

import android.app.Activity
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

abstract class BaseActivity<T : ViewDataBinding, V : ViewModel> : AppCompatActivity(),
    CoroutineScope {
    abstract val layoutId: Int
   lateinit var activity:Activity
    abstract val bindingVariable: Int
    val RQ_CODE_CAMERA = 100
    val RQ_CODE_LOCATION=101
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO
    abstract fun setUpObserver()


    @Inject
    lateinit var gson: Gson


    @Inject
    lateinit var mViewModel: V

    lateinit var binding: T


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        performDataBinding()
    }

    private fun performDataBinding() {
        activity=this
        binding = DataBindingUtil.setContentView(activity, layoutId)
        binding.setVariable(bindingVariable, mViewModel)
        binding.executePendingBindings()
        setUpObserver()
    }


    override fun onBackPressed() {
        super.onBackPressed()
    }




}