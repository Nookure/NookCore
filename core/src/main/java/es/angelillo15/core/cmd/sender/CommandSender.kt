package es.angelillo15.core.cmd.sender

import net.kyori.adventure.audience.Audience

interface CommandSender {
    fun sendMessage(message: String)
    fun hasPermission(permission: String): Boolean
    val name: String
    val uniqueId: String
    val address: String
    val isPlayer: Boolean
    val isConsole: Boolean
    val isProxy: Boolean
    val isBungee: Boolean
    val isSpigot: Boolean
    val audience: Audience?
    val serverName: String
}
