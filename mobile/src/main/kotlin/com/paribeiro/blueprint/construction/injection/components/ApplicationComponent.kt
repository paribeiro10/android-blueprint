package com.paribeiro.blueprint.construction.injection.components

import android.app.Application
import com.paribeiro.blueprint.construction.injection.modules.ActivityBuilderModule
import com.paribeiro.blueprint.construction.injection.modules.ViewModelModule
import com.paribeiro.blueprint.functional.support.bootstrap.Blueprint
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidInjectionModule::class,
    ActivityBuilderModule::class,
    ViewModelModule::class
])
interface ApplicationComponent : AndroidInjector<Blueprint> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): ApplicationComponent

    }

    override fun inject(app: Blueprint)

}
