package com.paribeiro.blueprint.functional.support.bootstrap

import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import com.google.android.play.core.splitcompat.SplitCompatApplication
import com.paribeiro.blueprint.BuildConfig
import com.paribeiro.blueprint.commons.construction.injection.CommonsComponentProvider
import com.paribeiro.blueprint.commons.construction.injection.components.CommonsComponent
import com.paribeiro.blueprint.commons.construction.injection.components.DaggerCommonsComponent
import com.paribeiro.blueprint.construction.injection.components.DaggerApplicationComponent
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

/**
 * Base class for the Blueprint application.
 */
open class Blueprint @Inject constructor(): SplitCompatApplication(),
    CommonsComponentProvider, HasAndroidInjector {

    @Inject lateinit var androidInjector: DispatchingAndroidInjector<Any>

    private lateinit var commonsComponent: CommonsComponent

    override fun androidInjector() = androidInjector

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        DaggerApplicationComponent.builder()
            .application(this)
            .commonsComponent(provideCommonsComponent())
        .build().inject(this)
    }

    override fun onCreate() {
        super.onCreate()
        initializeDevTools()
    }

    override fun provideCommonsComponent(): CommonsComponent {
        if (!this::commonsComponent.isInitialized) {
            commonsComponent = DaggerCommonsComponent.builder().build()
        }
        return commonsComponent
    }

    private fun initializeDevTools() {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true) // Vector Drawable for API < 23
        if (BuildConfig.DEBUG) {
            StrictModeAdmin.enableStrictModeThreadPolicy()
            StrictModeAdmin.enableStrictModeVMPolicy()
        }
    }

}
