package es.angelillo15.core.database

import com.craftmend.storm.Storm
import com.craftmend.storm.StormOptions
import com.craftmend.storm.connection.sqlite.SqliteFileDriver
import com.craftmend.storm.logger.StormLogger
import com.google.inject.Inject
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import es.angelillo15.core.Logger
import es.angelillo15.core.utils.TextUtils
import java.io.File
import java.sql.Connection
import java.sql.DriverManager


class PluginConnection {
    @Inject
    private lateinit var logger: Logger

    var connection: Connection? = null
        private set
    var dataProvider: DataProvider? = null
        private set
    var hikariConfig: HikariConfig? = null
        private set
    var storm: Storm? = null
        private set
    var hikariDataSource: HikariDataSource? = null
        private set

    fun connect(host: String, port: Int, database: String, user: String, password: String) {
        dataProvider = DataProvider.MYSQL
        hikariConfig = HikariConfig()
        hikariConfig!!.username = user
        hikariConfig!!.password = password
        hikariConfig!!.jdbcUrl = "jdbc:mysql://$host:$port/$database?autoReconnect=true&useUnicode=true&characterEncoding=utf8"
        hikariConfig!!.maximumPoolSize = 20
        hikariConfig!!.connectionTimeout = 3000
        hikariConfig!!.leakDetectionThreshold = 0
        hikariDataSource = HikariDataSource(hikariConfig)

        storm = Storm(getDefaultStormOptions(), PluginDriver(hikariDataSource!!))

        try {
            connection = hikariDataSource!!.connection
        } catch (e: Exception) {
            logger.error("Error while connecting to the database: " + e.message)
            e.printStackTrace()
        }
    }

    fun connect(path: String, fileName: String) {
        dataProvider = DataProvider.SQLITE

        try {
            Class.forName("org.sqlite.JDBC")
        } catch (e: ClassNotFoundException) {
            logger.error(TextUtils.simpleColorize("&c┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓"))
            logger.error(TextUtils.simpleColorize("&c┃ The SQLite driver couldn't be found!                                     ┃"))
            logger.error(TextUtils.simpleColorize("&c┃                                                                          ┃"))
            logger.error(TextUtils.simpleColorize("&c┃ Please, join our Discord server to get support:                          ┃"))
            logger.error(TextUtils.simpleColorize("&c┃ https://discord.nookure.com                                              ┃"))
            logger.error(TextUtils.simpleColorize("&c┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛"))
        }

        try {
            val jdbc = "jdbc:sqlite:${path}/${fileName}.db"
            connection = DriverManager.getConnection(jdbc)
        } catch (e: Exception) {
            logger.error("Error while connecting to the database: " + e.message)
            e.printStackTrace()
        }

        storm = Storm(getDefaultStormOptions(), SqliteFileDriver(File(path, "$fileName.db")))
    }

    private fun getDefaultStormOptions(): StormOptions {
        val options = StormOptions()
        options.logger = object : StormLogger {
            override fun warning(string: String) {
                logger.warn(string)
            }

            override fun info(string: String) {
                logger.info(string)
            }
        }
        return options
    }
}