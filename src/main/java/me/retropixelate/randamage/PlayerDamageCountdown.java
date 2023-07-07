package me.retropixelate.randamage;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;

public class PlayerDamageCountdown extends BukkitRunnable {

    private final Randamage plugin;

    public PlayerDamageCountdown(Randamage plugin) {
        this.plugin = plugin;
    }

    @Override
    public void run() {
        String message = "";
        for (Map.Entry<Player, Integer> entry : plugin.playerDamageCooldowns.entrySet()) {
            if (entry.getValue() > 0) {
                plugin.playerDamageCooldowns.put(entry.getKey(), entry.getValue() - 1);
            }
            // message = message + entry.getKey().getDisplayName() + ", " + entry.getValue().toString() + "; ";
        }
        // plugin.getServer().broadcastMessage(PlayerEventListener.playerDamageCooldowns.toString());
    }

}
