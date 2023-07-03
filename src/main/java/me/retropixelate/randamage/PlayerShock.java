package me.retropixelate.randamage;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Map;

public class PlayerShock extends BukkitRunnable {

    private final JavaPlugin plugin;

    public PlayerShock(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public void run() {
        String message = "";
        for (Map.Entry<Player, Integer> entry : PlayerEventListener.playerShockTime.entrySet()) {
            if (entry.getValue() > 0) {
                PlayerEventListener.playerShockTime.put(entry.getKey(), entry.getValue() - 1);
                entry.getKey().sendHurtAnimation(0);
            }
            /* message = message + entry.getKey().getDisplayName() + ", " + entry.getValue().toString() + "; "; **/
        }
    }

}
