package net.refractored.kybermonday.commands

import net.refractored.kybermonday.exceptions.CommandErrorException
import net.refractored.kybermonday.messages.MessageUtil.toComponent
import net.refractored.kybermonday.teams.Teams
import org.bukkit.entity.Player
import revxrsal.commands.annotation.Command
import revxrsal.commands.annotation.Description
import revxrsal.commands.bukkit.BukkitCommandActor
import revxrsal.commands.bukkit.annotation.CommandPermission

class RemoveFromTeam {
    @CommandPermission("kybermonday.teams.remove")
    @Description("Removes a player to a team.")
    @Command("event teams remove")
    fun execute(
        actor: BukkitCommandActor,
        player: Player,
    ) {
        Teams.getPlayerTeam(player)?.removeMember(player) ?: throw CommandErrorException(
            "<red>Player is not in a team.".toComponent(),
        )
        actor.reply("<green>Removed ${player.name} from a team".toComponent())
    }
}
