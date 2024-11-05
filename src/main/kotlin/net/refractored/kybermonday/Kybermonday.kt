package net.refractored.kybermonday

import net.refractored.kybermonday.commands.*
import net.refractored.kybermonday.listeners.OnEntityDamage
import net.refractored.kybermonday.listeners.OnPlayerDeath
import net.refractored.kybermonday.listeners.OnPlayerJoin
import net.refractored.kybermonday.placeholder.KybermondayExpansion
import net.refractored.kybermonday.teams.Team
import net.refractored.kybermonday.teams.Teams
import net.refractored.kybermonday.teams.TeamsResolver
import org.bukkit.plugin.java.JavaPlugin
import revxrsal.commands.bukkit.BukkitCommandHandler

class Kybermonday : JavaPlugin() {
    lateinit var handler: BukkitCommandHandler

    var eventStarted = false

    override fun onEnable() {
        instance = this

        saveDefaultConfig()

        Teams.refreshTeams()

        server.pluginManager.registerEvents(OnPlayerDeath(), this)
        server.pluginManager.registerEvents(OnPlayerJoin(), this)
        server.pluginManager.registerEvents(OnEntityDamage(), this)

        handler = BukkitCommandHandler.create(this)

        val teamResolver = TeamsResolver()
        handler.autoCompleter.registerParameterSuggestions(Team::class.java, teamResolver)
        handler.registerValueResolver(Team::class.java, teamResolver)

        handler.register(AddToTeam())
        handler.register(RemoveFromTeam())
        handler.register(SeparatePlayers())
        handler.register(SetGracePeriod())
        handler.register(StartEvent())
        handler.register(Reload())
        handler.register(SetTeleportLocation())
        handler.register(StopGracePeriod())

        KybermondayExpansion.register()
    }

    override fun onDisable() {
        // Plugin shutdown logic
    }

    fun reload() {
        reloadConfig()
        Teams.refreshTeams()
    }

    companion object {
        lateinit var instance: Kybermonday
            private set
    }
}
