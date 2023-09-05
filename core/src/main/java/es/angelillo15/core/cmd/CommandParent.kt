package es.angelillo15.core.cmd

import com.google.inject.Inject
import es.angelillo15.core.cmd.sender.CommandSender
import es.angelillo15.core.messages.CoreMessages
import es.angelillo15.core.utils.TextUtils

abstract class CommandParent : Command() {
    @Inject
    private lateinit var coreMessages: CoreMessages;

    private val subCommands: MutableMap<String?, SubCommand> = HashMap()
    override fun onCommand(sender: CommandSender, label: String, args: Array<String>) {
        if (subCommands.isEmpty())
            registerSubCommands()

        if (args.isEmpty()) {
            sendHelp(sender)
            return
        }
        val subCommand = getSubCommand(args[0])
        if (subCommand == null) {
            sendHelp(sender)
            return
        }

        if (sender.hasPermission(subCommand.permission)) {
            sender.sendMessage(TextUtils.simpleColorize(coreMessages.noPermission()))
            return
        }

        subCommand.onCommand(sender, label, args)
    }

    abstract fun registerSubCommands();

    abstract fun sendHelp(sender: CommandSender)

    fun registerSubCommand(subCommand: SubCommand) {
        subCommands[subCommand.name] = subCommand
    }

    fun unregisterSubCommand(subCommand: SubCommand) {
        subCommands.remove(subCommand.name)
    }

    fun unregisterSubCommand(name: String?) {
        subCommands.remove(name)
    }

    fun getSubCommand(name: String?): SubCommand? {
        return subCommands[name]
    }

    fun hasSubCommand(name: String?): Boolean {
        return subCommands.containsKey(name)
    }

    fun getCommands(): Collection<SubCommand> {
        return subCommands.values
    }

    fun registerHelpSubCommand(prefix: String) {
        registerSubCommand(object : SubCommand() {
            override val name: String
                get() = "help"
            override val description: String
                get() = "Shows this help message"
            override val syntax: String
                get() = "$prefix help"
            override val permission: String
                get() = ""

            override fun onCommand(sender: CommandSender, label: String, args: Array<String>) {
                sendHelp(sender)
            }
        })
    }
}
