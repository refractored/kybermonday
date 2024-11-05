package net.refractored.kybermonday.grace

import net.refractored.kybermonday.Kybermonday
import org.bukkit.Bukkit
import org.bukkit.Sound
import org.bukkit.boss.BarColor
import org.bukkit.boss.BarStyle
import org.bukkit.boss.BossBar
import org.bukkit.scheduler.BukkitRunnable
import java.time.Duration

object Grace {
    @JvmStatic
    private var endtime: Long = 0

    private var totalDuration: Duration = Duration.ZERO

    /**
     * returns true if the grace period is active.
     */
    val isGracePeriod: Boolean
        get() = System.currentTimeMillis() < endtime

    fun setGracePeriod(duration: Duration) {
        if (isGracePeriod) throw IllegalStateException("Grace period is already active")
        endtime = System.currentTimeMillis() + duration.toMillis()
        totalDuration = duration
        startBossbar()
    }

    fun stopGracePeriod() {
        endtime = 0
        totalDuration = Duration.ZERO
        bossbar?.removeAll()
        runnable?.cancel()
        runnable = null
        bossbar = null
        var console = Bukkit.getConsoleSender()
        Bukkit.dispatchCommand(console, "rg flag -w world plains exit allow")
        Bukkit.dispatchCommand(console, "rg flag -w world desert exit allow")
        Bukkit.dispatchCommand(console, "rg flag -w world jungle exit allow")
        Bukkit.dispatchCommand(console, "rg flag -w world mountains exit allow")
        Bukkit.dispatchCommand(console, "plugman reload WorldGuard")
    }

    fun getRemainingTime(): Duration = Duration.ofMillis(getRemainingMillis())

    fun getRemainingMillis(): Long = (endtime - System.currentTimeMillis()).coerceAtLeast(0)

    var bossbar: BossBar? = null

    private var runnable: BukkitRunnable? = null

    private fun startBossbar() {
        val remainingTime = getRemainingTime()

        bossbar =
            Bukkit.createBossBar(
                "Grace Period: ${remainingTime.toMinutes()}m ${remainingTime.toSeconds()}s",
                BarColor.BLUE,
                BarStyle.SEGMENTED_10,
            )
        Bukkit.getOnlinePlayers().forEach { bossbar?.addPlayer(it) }
        bossbar?.isVisible = true
        runnable =
            object : BukkitRunnable() {
                override fun run() {
                    if (getRemainingMillis() == 0L) {
                        Bukkit.getOnlinePlayers().forEach { it.playSound(it.location, Sound.ENTITY_ENDER_DRAGON_GROWL, 1.0f, 1.0f) }
                        bossbar?.removeAll()
                        runnable?.cancel()
                        runnable = null
                        bossbar = null
                        val console = Bukkit.getConsoleSender()
                        Bukkit.dispatchCommand(console, "rg flag -w world plains exit allow")
                        Bukkit.dispatchCommand(console, "rg flag -w world desert exit allow")
                        Bukkit.dispatchCommand(console, "rg flag -w world jungle exit allow")
                        Bukkit.dispatchCommand(console, "rg flag -w world mountains exit allow")
                        Bukkit.dispatchCommand(console, "plugman reload WorldGuard")
                        return
                    }
                    val progress = (getRemainingMillis() / totalDuration.toMillis().toDouble()).coerceIn(0.0, 1.0)
                    bossbar?.progress = progress
                    val duration = getRemainingTime()
                    bossbar?.setTitle("Grace Period: ${duration.toMinutes()}m ${duration.toSecondsPart()}s")
                }
            }
        runnable!!.runTaskTimer(Kybermonday.instance, 0L, 3L)
    }
}
