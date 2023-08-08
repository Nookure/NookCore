package es.angelillo15.core.cmd.sender

import es.angelillo15.core.utils.TextUtils.getAudience
import es.angelillo15.core.utils.TextUtils.toComponent
import net.kyori.adventure.audience.Audience
import org.bukkit.entity.Player

class PlayerCommandSender(private val player: Player) : CommandSender {
    override val audience: Audience

    init {
        audience = getAudience(player)
    }

    override fun sendMessage(message: String) {
        audience.sendMessage(toComponent(message))
    }

    override fun hasPermission(permission: String): Boolean {
        return player.hasPermission(permission)
    }

    override val name: String
        get() = player.name
    override val isConsole: Boolean
        get() = false
    override val isProxy: Boolean
        get() = false
    override val isBungee: Boolean
        get() = false
    override val isSpigot: Boolean
        get() = true
    override val uniqueId: String
        get() = player.uniqueId.toString()
    override val address: String
        get() = player.address!!.address.hostAddress.split(":".toRegex()).dropLastWhile { it.isEmpty() }
            .toTypedArray()[0]
    override val isPlayer: Boolean
        get() = true
    override val serverName: String
        get() = player.server.name
}
