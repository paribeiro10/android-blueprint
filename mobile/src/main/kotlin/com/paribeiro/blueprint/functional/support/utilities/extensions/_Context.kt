package com.paribeiro.blueprint.functional.support.utilities.extensions

import android.content.Context

val Context.isTablet: Boolean
    get() = resources.isTablet
