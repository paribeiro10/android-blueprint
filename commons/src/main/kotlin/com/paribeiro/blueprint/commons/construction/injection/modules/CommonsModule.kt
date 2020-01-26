package com.paribeiro.blueprint.commons.construction.injection.modules

import com.paribeiro.blueprint.commons.functional.TestObject
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CommonsModule {

    @Provides
    @Singleton
    fun provideTestObject(): TestObject = TestObject()

    // TODO PR: Add provisions here ...

}
