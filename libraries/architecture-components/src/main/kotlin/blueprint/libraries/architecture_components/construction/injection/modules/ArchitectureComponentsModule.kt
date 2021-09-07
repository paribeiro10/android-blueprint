package blueprint.libraries.architecture_components.construction.injection.modules

import blueprint.libraries.architecture_components.functional.TestObject
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ArchitectureComponentsModule {

    @Provides
    @Singleton
    fun provideTestObject(): TestObject = TestObject()

    // TODO PR: Add provisions here ...

}
