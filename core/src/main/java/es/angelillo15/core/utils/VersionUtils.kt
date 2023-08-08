package es.angelillo15.core.utils

import org.bukkit.Bukkit

object VersionUtils {
    @JvmStatic
    private var VERSION = -1

    @JvmStatic
    fun getVersion(): Int {
        if(VERSION != -1) return VERSION

        VERSION = try {
            Bukkit.getBukkitVersion().split("-".toRegex()).dropLastWhile { it.isEmpty() }
                .toTypedArray()[0].split("\\.".toRegex()).dropLastWhile { it.isEmpty() }
                .toTypedArray()[1].toInt()
        } catch (e: Exception) {
            20
        }

        return VERSION
    }
}