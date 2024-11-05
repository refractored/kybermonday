package net.refractored.kybermonday.teams

import net.refractored.kybermonday.Kybermonday
import net.refractored.kybermonday.messages.MessageUtil.toComponent
import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.OfflinePlayer
import java.util.UUID

class Team(
    val id: String,
) {
    val members = mutableListOf<UUID>()

    val displayName =
        Kybermonday.instance.config
            .getString("teams.$id.display-name")!!
            .toComponent()

    fun addMember(player: OfflinePlayer) {
        if (members.contains(player.uniqueId)) return
        members.add(player.uniqueId)
    }

    fun removeMember(player: OfflinePlayer) {
        members.remove(player.uniqueId)
    }

    var location: Location

    init {
        val split =
            Kybermonday.instance.config
                .getString("teams.$id.spawn-location")!!
                .split(",")

        val world = Bukkit.getWorld(split[0])

        location =
            Location(
                world,
                split[1].toDouble(),
                split[2].toDouble(),
                split[3].toDouble(),
            )
    }

    fun setSpawnLocation(location: Location) {
        this.location = location
        val split =
            location.run {
                "${world.name},$x,$y,$z"
            }
        Kybermonday.instance.config.set("teams.$id.spawn-location", split)
        Kybermonday.instance.saveConfig()
    }

    fun teleportMembers() {
        for (member in members) {
            val player = Bukkit.getPlayer(member) ?: return
            player.teleport(location)
        }
    }
}
