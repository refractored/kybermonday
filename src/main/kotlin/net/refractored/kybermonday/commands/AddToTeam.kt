package net.refractored.kybermonday.commands

import net.refractored.kybermonday.messages.MessageUtil.toComponent
import net.refractored.kybermonday.teams.Team
import org.bukkit.entity.Player
import revxrsal.commands.annotation.Command
import revxrsal.commands.annotation.Description
import revxrsal.commands.bukkit.BukkitCommandActor
import revxrsal.commands.bukkit.annotation.CommandPermission

class AddToTeam {
    @CommandPermission("kybermonday.teams.add")
    @Description("Adds a player to a team")
    @Command("event teams add")
    fun execute(
        actor: BukkitCommandActor,
        player: Player,
        team: Team,
    ) {
        team.addMember(player)
        actor.reply("<green>Added ${player.name} to ${team.id}".toComponent())
    }
}
