package net.refractored.kybermonday.placeholder

import me.clip.placeholderapi.expansion.PlaceholderExpansion
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer
import net.refractored.kybermonday.Kybermonday
import net.refractored.kybermonday.teams.Teams
import org.bukkit.OfflinePlayer

object KybermondayExpansion : PlaceholderExpansion() {
    override fun getIdentifier(): String = "kybermonday"

    override fun getAuthor(): String =
        Kybermonday.instance.pluginMeta.authors
            .joinToString(", ")

    override fun getVersion(): String = Kybermonday.instance.pluginMeta.version

    override fun onRequest(
        player: OfflinePlayer?,
        params: String,
    ): String? {
        val split = params.split("_")

        if (split[0] == "team") {
            if (player == null) return null
            val team = Teams.getPlayerTeam(player) ?: return ""
            if (split.getOrNull(1) == "count") {
                return team.members.count().toString()
            }
            return LegacyComponentSerializer.legacySection().serialize(team.displayName)
        }

        Teams.getTeam(split[0])?.let {
            if (split[1] == "displayname") {
                return LegacyComponentSerializer.legacySection().serialize(it.displayName)
            }

            if (split[1] == "count") {
                return it.members.count().toString()
            }
        }

        return null
    }
}
