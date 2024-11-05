package net.refractored.kybermonday.commands

import net.refractored.kybermonday.grace.Grace
import net.refractored.kybermonday.messages.MessageUtil.toComponent
import revxrsal.commands.annotation.Command
import revxrsal.commands.annotation.Description
import revxrsal.commands.bukkit.BukkitCommandActor
import revxrsal.commands.bukkit.annotation.CommandPermission

class StopGracePeriod {
    @CommandPermission("kybermonday.teams.add")
    @Description("Adds a player to a team")
    @Command("event graceperiod stop")
    fun execute(actor: BukkitCommandActor) {
        Grace.stopGracePeriod()
        actor.reply("<green>Grace period stopped".toComponent())
    }
}
