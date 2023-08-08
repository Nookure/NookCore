package es.angelillo15.core.cmd.sender

import com.google.inject.Inject
import es.angelillo15.core.Logger
import net.kyori.adventure.audience.Audience

open class BungeeConsoleCommandSender : CommandSender {
    @Inject
    private val logger: Logger? = null
    override fun sendMessage(message: String) {
        logger!!.info(message)
    }

    override fun hasPermission(permission: String): Boolean {
        return true
    }

    override val name: String
        get() = "Console"
    override val uniqueId: String
        get() = "CONSOLE"
    override val isPlayer: Boolean
        get() = false
    override val isConsole: Boolean
        get() = true
    override val isProxy: Boolean
        get() = true
    override val isBungee: Boolean
        get() = true
    override val isSpigot: Boolean
        get() = false
    override val address: String
        get() = "0.0.0.0"
    override val audience: Audience?
        get() = null
    override val serverName: String
        get() = "Proxy"
}
