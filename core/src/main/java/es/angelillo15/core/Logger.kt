package es.angelillo15.core

abstract class Logger {
    abstract fun info(message: String?)
    abstract fun warn(message: String?)
    abstract fun error(message: String?)
    abstract fun debug(message: String?)

    fun info(message: String, vararg args: Any) {
        info(format(message, *args))
    }

    fun warn(message: String, vararg args: Any) {
        warn(format(message, *args))
    }

    fun error(message: String, vararg args: Any) {
        error(format(message, *args))
    }

    fun debug(message: String, vararg args: Any) {
        debug(format(message, *args))
    }

    fun format(message: String, vararg args: Any): String {
        var msg = message
        for (arg in args) {
            msg = msg.replaceFirst("\\{\\}".toRegex(), arg.toString())
        }
        return msg
    }

    companion object {
        var instance: Logger? = null
    }
}
