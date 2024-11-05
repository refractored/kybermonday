package net.refractored.kybermonday.commands

import net.refractored.kybermonday.Kybermonday
import net.refractored.kybermonday.grace.Grace
import net.refractored.kybermonday.teams.Teams
import org.bukkit.Bukkit
import revxrsal.commands.annotation.Command
import revxrsal.commands.annotation.Description
import revxrsal.commands.bukkit.BukkitCommandActor
import revxrsal.commands.bukkit.annotation.CommandPermission
import java.time.Duration

class StartEvent {
    @CommandPermission("kybermonday.start")
    @Description("Starts the event")
    @Command("event start")
    fun execute(actor: BukkitCommandActor) {
        Grace.setGracePeriod(Duration.ofMinutes(Kybermonday.instance.config.getLong("grace-period")))
        Teams.splitPlayers()
        for (team in Teams.getTeams()) {
            team.teleportMembers()
        }
        val console = Bukkit.getConsoleSender()
        Bukkit.dispatchCommand(console, "rg flag -w world plains exit deny")
        Bukkit.dispatchCommand(console, "rg flag -w world desert exit deny")
        Bukkit.dispatchCommand(console, "rg flag -w world jungle exit deny")
        Bukkit.dispatchCommand(console, "rg flag -w world mountains exit deny")
        Bukkit.dispatchCommand(console, "plugman reload WorldGuard")
        Kybermonday.instance.eventStarted = true
    }
}
