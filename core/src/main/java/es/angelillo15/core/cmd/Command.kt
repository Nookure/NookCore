package es.angelillo15.core.cmd

import es.angelillo15.core.cmd.sender.CommandSender

abstract class Command {
    abstract fun onCommand(sender: CommandSender, label: String, args: Array<String>)

    open fun onTabComplete(sender: CommandSender, args: Array<String>): List<String> {
        return ArrayList()
    }
}
