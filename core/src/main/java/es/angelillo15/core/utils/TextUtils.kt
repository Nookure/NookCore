package es.angelillo15.core.utils

import com.google.inject.Inject
import es.angelillo15.core.chat.api.ChatColor
import net.kyori.adventure.audience.Audience
import net.kyori.adventure.platform.AudienceProvider
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.minimessage.MiniMessage
import net.md_5.bungee.api.connection.ProxiedPlayer
import org.bukkit.entity.Player
import java.text.SimpleDateFormat
import java.util.*
import java.util.regex.Matcher
import java.util.regex.Pattern

object TextUtils {
    @Inject
    private lateinit var audienceProvider: AudienceProvider

    private const val WITH_DELIMITER = "((?<=%1\$s)|(?=%1\$s))"
    private val HEX_PATTERN = Pattern.compile("#[a-fA-F0-9]{6}}")
    private const val SECOND: Long = 1000
    private const val MINUTE = 60 * SECOND
    private const val HOUR = 60 * MINUTE
    private const val DAY = 24 * HOUR

    @JvmStatic
    fun colorize(text: String): String {
        var text = text
        if (VersionUtils.getVersion() < 16) {
            return ChatColor.translateAlternateColorCodes('&', text)
        }
        val texts: Array<String> =
            text.split(String.format(WITH_DELIMITER, "&").toRegex())
                .dropLastWhile { it.isEmpty() }
                .toTypedArray()


        val finalText = StringBuilder()
        var match: Matcher = HEX_PATTERN.matcher(text)
        if (text.contains("&#")) {
            var i = 0
            while (i < texts.size) {
                if (texts[i].equals("&", ignoreCase = true)) {
                    i++
                    if (texts[i][0] == '#') {
                        finalText.append(ChatColor.of(texts[i].substring(0, 7))).append(texts[i].substring(7))
                    } else {
                        finalText.append(ChatColor.translateAlternateColorCodes('&', "&" + texts[i]))
                    }
                } else {
                    while (match.find()) {
                        val color = texts[i].substring(match.start(), match.end())
                        texts[i] = texts[i].replace(color, ChatColor.of(color).toString())
                        match = HEX_PATTERN.matcher(text)
                    }
                    finalText.append(texts[i])
                }

                i++
            }
        } else {
            while (match.find()) {
                val color = text.substring(match.start(), match.end())
                text = text.replace(color, ChatColor.of(color).toString())
                match = HEX_PATTERN.matcher(text)
            }
            finalText.append(text)
        }
        return ChatColor.translateAlternateColorCodes('&', finalText.toString())
    }

    @JvmStatic
    fun formatUptime(uptime: Long): String {
        var uptimeVar = uptime

        val buf = java.lang.StringBuilder()
        if (uptimeVar > DAY) {
            val days: Long =
                (uptimeVar - uptimeVar % DAY) / DAY
            buf.append(days)
            buf.append("d")
            uptimeVar %= DAY
        }
        if (uptimeVar > HOUR) {
            val hours: Long = (uptimeVar - uptimeVar % HOUR) / HOUR
            if (buf.isNotEmpty()) {
                buf.append(", ")
            }
            buf.append(hours)
            buf.append("h")
            uptimeVar %= HOUR
        }
        if (uptimeVar > MINUTE) {
            val minutes: Long = (uptimeVar - uptimeVar % MINUTE) / MINUTE
            if (buf.isNotEmpty()) {
                buf.append(", ")
            }
            buf.append(minutes)
            buf.append("m")
            uptimeVar = MINUTE
        }
        if (uptimeVar > SECOND) {
            val seconds: Long = (uptimeVar - uptimeVar % SECOND) / SECOND

            if (buf.isNotEmpty()) {
                buf.append(", ")
            }

            buf.append(seconds)
            buf.append("s")
        }
        return buf.toString()
    }

    @JvmStatic
    fun toMM(str: String): String {
        val sb = java.lang.StringBuilder(str)
        val m = ChatColor.STRIP_COLOR_PATTERN.matcher(sb)
        while (m.find()) {
            sb.replace(m.start(), m.end(), sb.substring(m.start(), m.end()).lowercase(Locale.getDefault()))
        }
        return sb.toString()
            .replace("&0", "<reset><black>")
            .replace("&1", "<reset><dark_blue>")
            .replace("&2", "<reset><dark_green>")
            .replace("&3", "<reset><dark_aqua>")
            .replace("&4", "<reset><dark_red>")
            .replace("&5", "<reset><dark_purple>")
            .replace("&6", "<reset><gold>")
            .replace("&7", "<reset><grey>")
            .replace("&8", "<reset><dark_grey>")
            .replace("&9", "<reset><blue>")
            .replace("&a", "<reset><green>")
            .replace("&b", "<reset><aqua>")
            .replace("&c", "<reset><red>")
            .replace("&d", "<reset><light_purple>")
            .replace("&e", "<reset><yellow>")
            .replace("&f", "<reset><white>")
            .replace("&k", "<obf>")
            .replace("&l", "<b>")
            .replace("&m", "<st>")
            .replace("&n", "<u>")
            .replace("&r", "<reset>")
            .replace("&o", "<i>")
            .replace("§0", "<reset><black>")
            .replace("§1", "<reset><dark_blue>")
            .replace("§2", "<reset><dark_green>")
            .replace("§3", "<reset><dark_aqua>")
            .replace("§4", "<reset><dark_red>")
            .replace("§5", "<reset><dark_purple>")
            .replace("§6", "<reset><gold>")
            .replace("§7", "<reset><grey>")
            .replace("§8", "<reset><dark_grey>")
            .replace("§9", "<reset><blue>")
            .replace("§a", "<reset><green>")
            .replace("§b", "<reset><aqua>")
            .replace("§c", "<reset><red>")
            .replace("§d", "<reset><light_purple>")
            .replace("§e", "<reset><yellow>")
            .replace("§f", "<reset><white>")
            .replace("§k", "<obf>")
            .replace("§l", "<b>")
            .replace("§m", "<st>")
            .replace("§n", "<u>")
            .replace("§r", "<reset>")
            .replace("§o", "<i>")
    }

    @JvmStatic
    fun toComponent(str: String): Component {
        return MiniMessage.miniMessage().deserialize(toMM(str))
    }

    @JvmStatic
    fun simpleColorize(text: String): String {
        return text.replace("&", "§")
    }

    @JvmStatic
    fun formatDate(date: Long, format: String): String {
        return SimpleDateFormat(format).format(Date(date))
    }

    @JvmStatic
    fun getAudience(uuid: UUID): Audience {
        return audienceProvider.player(uuid)
    }

    @JvmStatic
    fun getAudience(player: Player): Audience {
        return getAudience(player.uniqueId)
    }

    @JvmStatic
    fun getAudience(player: ProxiedPlayer): Audience {
        return getAudience(player.uniqueId)
    }
}