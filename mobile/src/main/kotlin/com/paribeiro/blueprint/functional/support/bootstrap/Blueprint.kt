package com.paribeiro.blueprint.functional.support.bootstrap

import android.app.Application
import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import com.paribeiro.blueprint.BuildConfig
import com.paribeiro.blueprint.construction.injection.components.DaggerApplicationComponent
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

/**
 * Base class for the Blueprint application.
 */
open class Blueprint @Inject constructor(): Application(), HasAndroidInjector {

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    override fun androidInjector() = androidInjector

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        DaggerApplicationComponent.builder().application(this).build().inject(this)
    }

    override fun onCreate() {
        super.onCreate()
        initializeDevTools()
    }

    private fun initializeDevTools() {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true) // Vector Drawable for API < 23
        if (BuildConfig.DEBUG) {
            StrictModeAdmin.enableStrictModeThreadPolicy()
            StrictModeAdmin.enableStrictModeVMPolicy()
        }
    }

}
