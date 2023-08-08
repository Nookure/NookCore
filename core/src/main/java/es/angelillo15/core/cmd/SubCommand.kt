package es.angelillo15.core.cmd

import es.angelillo15.core.cmd.sender.CommandSender

abstract class SubCommand {
    abstract val name: String?
    abstract val description: String?
    abstract val syntax: String?
    abstract val permission: String?
    abstract fun onCommand(sender: CommandSender, label: String, args: Array<String>)
}