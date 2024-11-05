package net.refractored.kybermonday.commands

import net.refractored.kybermonday.Kybermonday
import net.refractored.kybermonday.messages.MessageUtil.toComponent
import revxrsal.commands.annotation.Command
import revxrsal.commands.annotation.Description
import revxrsal.commands.annotation.Optional
import revxrsal.commands.bukkit.BukkitCommandActor
import revxrsal.commands.bukkit.annotation.CommandPermission

class Reload {
    @CommandPermission("kybermonday.teams.add")
    @Description("Adds a player to a team")
    @Command("event reload")
    fun execute(
        actor: BukkitCommandActor,
        @Optional confirm: Boolean = true,
    ) {
        Kybermonday.instance.reload()
        if (!confirm) {
            actor.reply(
                (
                    "<green>Reloading the plugin will reset all teams!" +
                        "<newline>Add true after the reload command to confirm."
                ).toComponent(),
            )
            return
        }
        actor.reply("<green>Reloaded Plugin".toComponent())
    }
}
