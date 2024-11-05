package net.refractored.kybermonday.listeners

import net.refractored.kybermonday.grace.Grace
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageByEntityEvent

class OnEntityDamage : Listener {
    @EventHandler
    fun execute(event: EntityDamageByEntityEvent) {
        if (!Grace.isGracePeriod) return
        if (event.entity !is Player) return
        if (event.damager !is Player) return
        event.isCancelled = true
        return
    }
}
