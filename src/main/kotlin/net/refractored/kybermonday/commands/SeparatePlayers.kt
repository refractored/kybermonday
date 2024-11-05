package net.refractored.kybermonday.commands

import net.refractored.kybermonday.messages.MessageUtil.toComponent
import net.refractored.kybermonday.teams.Teams
import revxrsal.commands.annotation.Command
import revxrsal.commands.annotation.Description
import revxrsal.commands.bukkit.BukkitCommandActor
import revxrsal.commands.bukkit.annotation.CommandPermission

class SeparatePlayers {
    @CommandPermission("kybermonday.teams.separate")
    @Description("Separates players into multiple teams")
    @Command("event teams separate")
    fun execute(actor: BukkitCommandActor) {
        Teams.splitPlayers()
        actor.reply("<green>Separated players into teams".toComponent())
    }
}
