package blueprint.libraries.architecture_components.construction.injection.components

import blueprint.libraries.architecture_components.construction.injection.modules.ArchitectureComponentsModule
import blueprint.libraries.architecture_components.functional.TestObject
import dagger.Component
import javax.inject.Singleton

@Component(modules = [
    ArchitectureComponentsModule::class
])
@Singleton
interface ArchitectureComponentsComponent {

    fun getTestObject(): TestObject

}
