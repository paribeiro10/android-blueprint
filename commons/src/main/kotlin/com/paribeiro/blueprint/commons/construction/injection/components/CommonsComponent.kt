package com.paribeiro.blueprint.commons.construction.injection.components

import com.paribeiro.blueprint.commons.construction.injection.modules.CommonsModule
import com.paribeiro.blueprint.commons.functional.TestObject
import dagger.Component
import javax.inject.Singleton

@Component(modules = [
    CommonsModule::class
])
@Singleton
interface CommonsComponent {

    fun getTestObject(): TestObject

}
