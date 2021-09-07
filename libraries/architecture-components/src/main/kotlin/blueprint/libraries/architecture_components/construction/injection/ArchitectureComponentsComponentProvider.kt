package blueprint.libraries.architecture_components.construction.injection

import blueprint.libraries.architecture_components.construction.injection.components.ArchitectureComponentsComponent

interface ArchitectureComponentsComponentProvider {

    fun provideArchitectureComponentsComponent(): ArchitectureComponentsComponent

}
