package me.retropixelate.randamage;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;

public class PlayerDamageCountdown extends BukkitRunnable {

    private final JavaPlugin plugin;

    public PlayerDamageCountdown(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public void run() {
        String message = "";
        for (Map.Entry<Player, Integer> entry : PlayerEventListener.playerDamageCooldowns.entrySet()) {
            if (entry.getValue() > 0) {
                PlayerEventListener.playerDamageCooldowns.put(entry.getKey(), entry.getValue() - 1);
            }
            // message = message + entry.getKey().getDisplayName() + ", " + entry.getValue().toString() + "; ";
        }
        // plugin.getServer().broadcastMessage(PlayerEventListener.playerDamageCooldowns.toString());
    }

}
