package com.paribeiro.blueprint.functional.support.bootstrap

import androidx.appcompat.app.AppCompatDelegate
import com.google.android.play.core.splitcompat.SplitCompatApplication
import com.paribeiro.blueprint.BuildConfig
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

/** Base class for the Blueprint application. */
@HiltAndroidApp open class Blueprint
@Inject constructor(): SplitCompatApplication() {

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
