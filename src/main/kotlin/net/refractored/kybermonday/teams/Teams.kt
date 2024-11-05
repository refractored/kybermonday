package net.refractored.kybermonday.teams

import net.refractored.kybermonday.Kybermonday
import org.bukkit.Bukkit
import org.bukkit.OfflinePlayer
import java.util.*

object Teams {
    @JvmStatic
    private var teams: MutableList<Team> = mutableListOf()

    @JvmStatic
    fun refreshTeams() {
        teams.clear()
        val config = Kybermonday.instance.config
        val section = config.getConfigurationSection("teams")!!
        val keys = section.getKeys(false)
        for (key in keys) {
            val team = Team(key)
            teams.add(team)
        }
    }

    fun splitPlayers() {
        val shuffledPlayers = Bukkit.getOnlinePlayers().shuffled()
        val teamsCount = teams.size

        for ((index, player) in shuffledPlayers.withIndex()) {
            val team = teams[index % teamsCount]
            team.addMember(player)
        }
    }

    /**
     * Returns a list of all teams
     */
    @JvmStatic
    fun getTeams(): List<Team> = teams.toList()

    fun getPlayerTeam(player: OfflinePlayer): Team? = getPlayerTeam(player.uniqueId)

    fun getPlayerTeam(player: UUID): Team? {
        for (team in teams) {
            if (team.members.contains(player)) {
                return team
            }
        }
        return null
    }

    fun getTeam(id: String): Team? = teams.find { it.id == id }
}
