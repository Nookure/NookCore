package es.angelillo15.core.cmd.sender

import es.angelillo15.core.utils.TextUtils.colorize
import es.angelillo15.core.utils.TextUtils.getAudience
import net.kyori.adventure.audience.Audience
import net.md_5.bungee.api.chat.TextComponent
import net.md_5.bungee.api.connection.ProxiedPlayer

class ProxiedPlayerCommandSender(private val player: ProxiedPlayer) : CommandSender {
    override val audience: Audience

    init {
        audience = getAudience(player)
    }

    override fun sendMessage(message: String) {
        player.sendMessage(TextComponent(colorize(message)))
    }

    override fun hasPermission(permission: String): Boolean {
        return player.hasPermission(permission)
    }

    override val name: String
        get() = player.name

    override val isConsole: Boolean
        get() = false
    override val isProxy: Boolean
        get() = true
    override val isBungee: Boolean
        get() = true
    override val isSpigot: Boolean
        get() = false
    override val uniqueId: String
        get() = player.uniqueId.toString()
    override val address: String
        get() = player.address.address.hostAddress.split(":".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()[0]
    override val isPlayer: Boolean
        get() = true
    override val serverName: String
        get() = player.server.info.name
}
