package com.paribeiro.blueprint.commons.functional.base

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
import android.content.pm.ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.paribeiro.blueprint.commons.functional.support.utilities.extensions.isTablet
import dagger.android.AndroidInjection
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

abstract class BaseActivity<T : ViewDataBinding, VM : ViewModel> : AppCompatActivity(),
    HasAndroidInjector {

    @Inject lateinit var androidInjector: DispatchingAndroidInjector<Any>
    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory

    protected val dataBinding: T by lazy {
        DataBindingUtil.setContentView<T>(this, layoutToInflate())
    }

    protected val viewModel: VM by lazy {
        ViewModelProvider(this, viewModelFactory).get(getViewModelClass())
    }

    @LayoutRes abstract fun layoutToInflate(): Int

    abstract fun getViewModelClass(): Class<VM>

    override fun androidInjector() = androidInjector

    // TODO PR: Explain what this procedure is meant to do on each Activity
    abstract fun doOnCreated()

    override fun onCreate(savedInstanceState: Bundle?) {
        enableTransitionsIfPossible()
        AndroidInjection.inject(this)

        super.onCreate(savedInstanceState)

        runOrientationConfiguration()
        runContentWindowConfiguration()
        runBasicSoftKeyboardConfiguration()
        initDataBinding()

        doOnCreated()
    }

    override fun onStop() {
        // TODO PR: This should be in a SoftKeyboard Util class or Extension class
        val inputManager =
            getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.hideSoftInputFromWindow(
            currentFocus?.windowToken,
            InputMethodManager.HIDE_NOT_ALWAYS
        )
        super.onStop()
    }

    override fun onDestroy() {
        dataBinding.unbind()
        super.onDestroy()
    }

    private fun initDataBinding() { dataBinding.lifecycleOwner = this }

    private fun runOrientationConfiguration() {
        requestedOrientation =
            if (isTablet) SCREEN_ORIENTATION_SENSOR_LANDSCAPE else SCREEN_ORIENTATION_PORTRAIT
    }

    private fun runContentWindowConfiguration() {
        window.apply {
            clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
                statusBarColor = Color.TRANSPARENT
            }
            decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        }
    }

    private fun runBasicSoftKeyboardConfiguration() {
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN)
    }

    @SuppressLint("ObsoleteSdkInt")
    private fun enableTransitionsIfPossible() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            requestWindowFeature(Window.FEATURE_ACTIVITY_TRANSITIONS)
            requestWindowFeature(Window.FEATURE_CONTENT_TRANSITIONS)
        }
    }

}
