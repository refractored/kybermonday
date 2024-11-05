package net.refractored.kybermonday.listeners

import com.destroystokyo.paper.profile.PlayerProfile
import net.refractored.kybermonday.Kybermonday
import net.refractored.kybermonday.messages.MessageUtil.replace
import net.refractored.kybermonday.messages.MessageUtil.toComponent
import org.bukkit.BanEntry
import org.bukkit.Bukkit
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.PlayerDeathEvent
import java.time.Duration

class OnPlayerDeath : Listener {
    @EventHandler
    fun execute(event: PlayerDeathEvent) {
        if (!Kybermonday.instance.eventStarted) return
        // aids workaround wtf
        event.player.ban<BanEntry<in PlayerProfile>>("You died!", null as Duration?, null as String?)
        event.player.kick()
        event.player.world.strikeLightningEffect(event.player.location)
        val message = Kybermonday.instance.config.getString("death-announce") ?: "Message not found"
        Bukkit.broadcast(
            message.replace("%player%", event.player.displayName()).toComponent(),
        )
    }
}
