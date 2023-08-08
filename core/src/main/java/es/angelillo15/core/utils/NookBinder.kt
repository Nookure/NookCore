package es.angelillo15.core.utils

import com.google.inject.AbstractModule
import es.angelillo15.core.Logger
import es.angelillo15.core.Logger.Companion.instance

class NookBinder : AbstractModule() {
    override fun configure() {
        bind(Logger::class.java).toInstance(instance)
    }
}
