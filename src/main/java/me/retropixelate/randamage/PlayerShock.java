package me.retropixelate.randamage;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Map;

import static org.bukkit.Sound.ENTITY_PLAYER_HURT;

public class PlayerShock extends BukkitRunnable {

    private final Randamage plugin;

    public PlayerShock(Randamage plugin) {
        this.plugin = plugin;
    }

    @Override
    public void run() {
        String message = "";
        for (Map.Entry<Player, Integer> entry : plugin.playerShockTime.entrySet()) {
            if (entry.getValue() > 0) {
                Player p = entry.getKey();
                plugin.playerShockTime.put(p, entry.getValue() - 1);
                p.sendHurtAnimation(0);
                p.playSound(p, ENTITY_PLAYER_HURT, 1.0f, 1.0f);
            }
            // message = message + entry.getKey().getDisplayName() + ", " + entry.getValue().toString() + "; ";
        }
    }

}
