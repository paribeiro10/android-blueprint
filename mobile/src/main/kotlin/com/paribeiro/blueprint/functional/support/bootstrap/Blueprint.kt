package com.paribeiro.blueprint.functional.support.bootstrap

import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import blueprint.libraries.architecture_components.construction.injection.ArchitectureComponentsComponentProvider
import blueprint.libraries.architecture_components.construction.injection.components.ArchitectureComponentsComponent
import blueprint.libraries.architecture_components.construction.injection.components.DaggerArchitectureComponentsComponent
import com.google.android.play.core.splitcompat.SplitCompatApplication
import com.paribeiro.blueprint.BuildConfig
import com.paribeiro.blueprint.construction.injection.components.DaggerApplicationComponent
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

/**
 * Base class for the Blueprint application.
 */
open class Blueprint @Inject constructor(): SplitCompatApplication(),
    ArchitectureComponentsComponentProvider, HasAndroidInjector {

    @Inject lateinit var androidInjector: DispatchingAndroidInjector<Any>

    private lateinit var architectureComponentsComponent: ArchitectureComponentsComponent

    override fun androidInjector() = androidInjector

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        DaggerApplicationComponent.builder()
            .application(this)
            .architectureComponentsComponent(provideArchitectureComponentsComponent())
        .build().inject(this)
    }

    override fun onCreate() {
        super.onCreate()
        initializeDevTools()
    }

    override fun provideArchitectureComponentsComponent(): ArchitectureComponentsComponent {
        if (!this::architectureComponentsComponent.isInitialized) {
            architectureComponentsComponent = DaggerArchitectureComponentsComponent.builder().build()
        }
        return architectureComponentsComponent
    }

    private fun initializeDevTools() {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true) // Vector Drawable for API < 23
        if (BuildConfig.DEBUG) {
            StrictModeAdmin.enableStrictModeThreadPolicy()
            StrictModeAdmin.enableStrictModeVMPolicy()
        }
    }

}
