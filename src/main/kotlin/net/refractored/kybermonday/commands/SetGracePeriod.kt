package net.refractored.kybermonday.commands

import net.refractored.kybermonday.Kybermonday
import net.refractored.kybermonday.grace.Grace
import net.refractored.kybermonday.messages.MessageUtil.toComponent
import revxrsal.commands.annotation.Command
import revxrsal.commands.annotation.Description
import revxrsal.commands.bukkit.BukkitCommandActor
import revxrsal.commands.bukkit.annotation.CommandPermission
import java.time.Duration

class SetGracePeriod {
    @CommandPermission("kybermonday.teams.add")
    @Description("Adds a player to a team")
    @Command("event graceperiod start")
    fun execute(actor: BukkitCommandActor) {
        Grace.setGracePeriod(Duration.ofMinutes(Kybermonday.instance.config.getLong("grace-period")))
        actor.reply("<green>Grace period started".toComponent())
    }
}
