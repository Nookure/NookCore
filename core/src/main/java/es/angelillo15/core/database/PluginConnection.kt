package es.angelillo15.core.database

import com.craftmend.storm.Storm
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import java.sql.Connection

class PluginConnection {
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
        
    }
}