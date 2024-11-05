package net.refractored.kybermonday.listeners

import net.refractored.kybermonday.Kybermonday
import net.refractored.kybermonday.grace.Grace
import net.refractored.kybermonday.teams.Teams
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent

class OnPlayerJoin : Listener {
    @EventHandler
    fun execute(event: PlayerJoinEvent) {
        if (!Kybermonday.instance.eventStarted) return
        if (Teams.getPlayerTeam(event.player) != null) return
        val team = Teams.getTeams().random()
        team.addMember(event.player)
        Grace.bossbar?.addPlayer(event.player)
    }
}
