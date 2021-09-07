package blueprint.libraries.architecture_components.functional.support.utilities.extensions

import android.content.Context

val Context.isTablet: Boolean
    get() = resources.isTablet
