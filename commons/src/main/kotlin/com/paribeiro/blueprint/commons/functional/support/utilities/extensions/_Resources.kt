package com.paribeiro.blueprint.commons.functional.support.utilities.extensions

import android.content.res.Configuration.SCREENLAYOUT_SIZE_LARGE
import android.content.res.Configuration.SCREENLAYOUT_SIZE_MASK
import android.content.res.Resources

val Resources.isTablet: Boolean
    get() = configuration.screenLayout and SCREENLAYOUT_SIZE_MASK >= SCREENLAYOUT_SIZE_LARGE
