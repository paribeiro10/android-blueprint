package blueprint.libraries.architecture_components.construction

import blueprint.libraries.architecture_components.functional.TestObject
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
object ArchitectureComponentsModule {

    @Provides
    fun providesAString(): String = "This is a String to be injected!"

    @Provides
    fun providesAnObject(): TestObject = TestObject()

}
