package me.retropixelate.randamage;

import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.entity.Player;
import java.util.*;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

public class PlayerEventListener implements Listener {

    public static HashMap<Player, Integer> playerDamageCooldowns = new HashMap<Player, Integer>();
    public static HashMap<Player, Integer> playerShockTime = new HashMap<Player, Integer>();

    @EventHandler
    public void onPlayerDamage(EntityDamageEvent event) {
        if (event.getEntityType() == EntityType.PLAYER) {
            Player p = (Player) event.getEntity();
            if (!playerDamageCooldowns.containsKey(p)) {
                playerDamageCooldowns.put(p, 0);
            }

            if (playerDamageCooldowns.get(p) == 0) {
                World w = p.getWorld();
                playerDamageCooldowns.put(p, 20);
                int roll = ((int) Math.ceil(Math.random() * 2));
                switch (roll) {

                    case 1:
                        w.createExplosion(p.getLocation(), 2F);
                        break;

                    case 2:
                        if (!playerShockTime.containsKey(p)) {
                            playerShockTime.put(p, 0);
                        }
                        playerShockTime.put(p, playerShockTime.get(p) + 20);
                        break;

                    default:

                }
            }
        }
    }

}
