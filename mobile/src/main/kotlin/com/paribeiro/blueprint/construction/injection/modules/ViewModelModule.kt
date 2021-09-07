package com.paribeiro.blueprint.construction.injection.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.paribeiro.blueprint.construction.creational.factories.ViewModelFactory
import com.paribeiro.blueprint.construction.injection.annotations.ViewModelKey
import com.paribeiro.blueprint.functional.section.activities.splash.SplashViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindsViewModelFactory(viewModelFactory: ViewModelFactory)
    : ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(SplashViewModel::class)
    abstract fun bindsSplashViewModel(splashViewModel: SplashViewModel): ViewModel

    // Add more ViewModels here if necessary

}
