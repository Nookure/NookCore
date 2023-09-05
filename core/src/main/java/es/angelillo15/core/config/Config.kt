package es.angelillo15.core.config

import com.google.inject.Inject
import es.angelillo15.configmanager.ConfigManager
import es.angelillo15.core.NookInstance

import java.io.File

open class Config {
    @Inject
    lateinit var plugin: NookInstance

    fun loadFile(original: String, path: String): ConfigManager {
        ConfigMerge.merge(
            File("${plugin.getPluginDataFolder().toPath()}/$path"),
            plugin.getPluginResource(original)
        )

        val config = ConfigManager(plugin.getPluginDataFolder().toPath(), path, path)
        config.registerConfig()
        return config
    }
}