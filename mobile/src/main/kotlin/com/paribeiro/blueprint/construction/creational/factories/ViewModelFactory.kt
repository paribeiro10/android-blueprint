package com.paribeiro.blueprint.construction.creational.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.paribeiro.blueprint.construction.injection.scopes.AppScope
import javax.inject.Inject
import javax.inject.Provider

@AppScope
class ViewModelFactory @Inject constructor(private val creators: Map<Class <out ViewModel>,
        @JvmSuppressWildcards Provider<ViewModel>>) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val creator = creators[modelClass] ?: creators.asIterable().firstOrNull {
            modelClass.isAssignableFrom(it.key)
        }?.value ?: throw IllegalArgumentException()

        return try { creator.get() as T }
        catch (exception: Exception) { throw RuntimeException(exception) }
    }

}
