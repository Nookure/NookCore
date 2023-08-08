package es.angelillo15.core

import com.google.inject.Injector
import java.io.File
import java.io.InputStream


interface NookInstance {
    /**
     * Gets the plugin's data folder
     */
    fun getPluginDataFolder(): File

    /**
     * Get a plugin resource
     */
    fun getPluginResource(s: String): InputStream

    /**
     * Gets google guice injector
     */
    fun getInjector(): Injector

    /**
     * Sets the debug mode
     */
    fun setDebug(debug: Boolean)

    /**
     * Gets the debug mode
     */
    fun getDebug(): Boolean
}