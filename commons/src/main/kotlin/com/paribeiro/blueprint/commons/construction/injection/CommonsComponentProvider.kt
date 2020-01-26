package com.paribeiro.blueprint.commons.construction.injection

import com.paribeiro.blueprint.commons.construction.injection.components.CommonsComponent

interface CommonsComponentProvider {

    fun provideCommonsComponent(): CommonsComponent

}
