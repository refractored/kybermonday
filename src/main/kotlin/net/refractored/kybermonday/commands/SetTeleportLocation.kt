package net.refractored.kybermonday.commands

import net.refractored.kybermonday.messages.MessageUtil.toComponent
import net.refractored.kybermonday.teams.Team
import revxrsal.commands.annotation.Command
import revxrsal.commands.annotation.Description
import revxrsal.commands.bukkit.BukkitCommandActor
import revxrsal.commands.bukkit.annotation.CommandPermission
import revxrsal.commands.bukkit.player

class SetTeleportLocation {
    @CommandPermission("kybermonday.teams.add")
    @Description("Adds a player to a team")
    @Command("event tploc set")
    fun execute(
        actor: BukkitCommandActor,
        team: Team,
    ) {
        team.setSpawnLocation(actor.player.location)
        actor.reply("<green>Set spawn location.".toComponent())
    }
}
