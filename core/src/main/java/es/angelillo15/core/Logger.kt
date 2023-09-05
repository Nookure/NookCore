package es.angelillo15.core

abstract class Logger {
    abstract fun info(message: String?)
    abstract fun warn(message: String?)
    abstract fun error(message: String?)
    abstract fun debug(message: String?)
}
