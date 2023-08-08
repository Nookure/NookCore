package es.angelillo15.core.config

import ru.vyarus.yaml.updater.YamlUpdater

import java.io.File
import java.io.InputStream


object ConfigMerge {
    /**
     * Merges the InputStream into the File
     * @param file The file to merge
     * @param Input The InputStream to merge
     */
    fun merge(current: File, update: InputStream) {
        YamlUpdater.create(current, update)
            .update()
    }
}