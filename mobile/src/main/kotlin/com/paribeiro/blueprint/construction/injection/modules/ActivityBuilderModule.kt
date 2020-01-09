package com.paribeiro.blueprint.construction.injection.modules

import com.paribeiro.blueprint.functional.section.activities.splash.Splash
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule {

    @ContributesAndroidInjector
    abstract fun contributesSplashActivity(): Splash

    // Add more Activities here ...

}
