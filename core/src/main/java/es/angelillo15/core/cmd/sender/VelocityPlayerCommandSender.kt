package es.angelillo15.core.cmd.sender

import com.velocitypowered.api.proxy.Player
import es.angelillo15.core.utils.TextUtils.toComponent
import net.kyori.adventure.audience.Audience

class VelocityPlayerCommandSender(private val player: Player) : CommandSender {
    override fun sendMessage(message: String) {
        player.sendMessage(toComponent(message))
    }

    override fun hasPermission(permission: String): Boolean {
        return player.hasPermission(permission)
    }

    override val name: String
        get() = player.username
    override val uniqueId: String
        get() = player.uniqueId.toString()
    override val address: String
        get() = player.remoteAddress.address.hostAddress.split(":".toRegex()).dropLastWhile { it.isEmpty() }
            .toTypedArray()[0]

    override val isConsole: Boolean
        get() = false
    override val isProxy: Boolean
        get() = true
    override val isBungee: Boolean
        get() = true
    override val isSpigot: Boolean
        get() = false
    override val audience: Audience
        get() = player
    override val isPlayer: Boolean
        get() = true
    override val serverName: String
        get() = if (player.currentServer.isPresent) {
            player.currentServer.get().serverInfo.name
        } else "Proxy"
}
