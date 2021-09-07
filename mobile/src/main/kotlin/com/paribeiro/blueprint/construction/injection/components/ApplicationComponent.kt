package com.paribeiro.blueprint.construction.injection.components

import android.app.Application
import blueprint.libraries.architecture_components.construction.injection.components.ArchitectureComponentsComponent
import com.paribeiro.blueprint.construction.injection.modules.ActivityBuilderModule
import com.paribeiro.blueprint.construction.injection.modules.ViewModelModule
import com.paribeiro.blueprint.construction.injection.scopes.AppScope
import com.paribeiro.blueprint.functional.support.bootstrap.Blueprint
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector

@Component(
    modules = [
        AndroidInjectionModule::class,
        ActivityBuilderModule::class,
        ViewModelModule::class
    ],
    dependencies = [
        ArchitectureComponentsComponent::class
    ]
)
@AppScope
interface ApplicationComponent : AndroidInjector<Blueprint> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun architectureComponentsComponent(
            architectureComponentsComponent: ArchitectureComponentsComponent
        ): Builder

        fun build(): ApplicationComponent

    }

    override fun inject(app: Blueprint)

}
