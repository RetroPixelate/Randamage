package me.retropixelate.randamage;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.entity.Player;
import java.util.*;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.util.Vector;

import static java.lang.Math.round;
import static org.bukkit.Sound.*;

public class PlayerEventListener implements Listener {

//    public static HashMap<Player, Integer> playerDamageCooldowns = new HashMap<Player, Integer>();
//    public static HashMap<Player, Integer> playerShockTime = new HashMap<Player, Integer>();

    private final Randamage plugin;

    public PlayerEventListener (Randamage plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerDamage(EntityDamageEvent event) {
        if (event.getEntityType() == EntityType.PLAYER) {
            Player p = (Player) event.getEntity();
            if (!plugin.playerDamageCooldowns.containsKey(p)) {
                plugin.playerDamageCooldowns.put(p, 0);
            }

            if (plugin.playerDamageCooldowns.get(p) == 0) {
                World w = p.getWorld();
                plugin.playerDamageCooldowns.put(p, 20); // 20 ticks until an effect can happen again
                int roll = ((int) Math.ceil(Math.random() * 6)); // random number from 1-given inclusive
//                roll = 6;
                this.plugin.getServer().broadcastMessage(p.getDisplayName() + ", " + roll); // debug message
                switch (roll) {

                    case 1:
                        w.createExplosion(p.getLocation().getX(), p.getLocation().getY() + 1.0, p.getLocation().getZ(), 1F); // explosion
                        break;

                    case 2:
                        if (!plugin.playerShockTime.containsKey(p)) {
                            plugin.playerShockTime.put(p, 0);
                        }
                        plugin.playerShockTime.put(p, plugin.playerShockTime.get(p) + 20); // screenshake
                        break;

                    case 3:
                        p.dropItem(true); // drop held item
                        p.playSound(p, ENTITY_SLIME_SQUISH_SMALL, 1.0f, 1.0f);
                        break;

                    case 4:
                        p.setVelocity(new Vector(p.getVelocity().getX(), 5.0, p.getVelocity().getZ()));
                        p.playSound(p, ENTITY_ENDER_DRAGON_FLAP, 1.0f, 1.5f);
                        break;

                    case 5:
                        Location l = new Location(p.getWorld(), p.getLocation().getX() + (round((Math.random() - 0.5) * 41)), p.getLocation().getY() + (round((Math.random() - 0.5) * 41)), p.getLocation().getZ() + (round((Math.random() - 0.5) * 41)));
                        p.teleport(l);
                        p.getWorld().setType(p.getLocation(), Material.AIR);
                        p.getWorld().setType(new Location(p.getWorld(), p.getLocation().getX(), p.getLocation().getY() + 1, p.getLocation().getZ()), Material.AIR);
                        Location belowFeet = new Location(p.getWorld(), p.getLocation().getX(), p.getLocation().getY() - 1, p.getLocation().getZ());
                        if (p.getWorld().getType(belowFeet) == Material.AIR) {
                            p.getWorld().setType(belowFeet, Material.STONE);
                        }
                        p.playSound(p, ENTITY_ENDERMAN_TELEPORT, 1.0f, 1.0f);
                        break;

                    case 6:
                        Vector v = new Vector(0.5, 0.5, 0.0);
                        for (double y = 1; y <= 8; y++) {
                            TNTPrimed tnt = (TNTPrimed) p.getWorld().spawnEntity(p.getLocation(), EntityType.PRIMED_TNT);
                            tnt.setFuseTicks(80);
                            tnt.setVelocity(v.rotateAroundY(Math.PI / 4.0));
                            p.playSound(p, ENTITY_TNT_PRIMED, 1.0f, 1.0f);
                        }
                        break;


                    default: // player got lucky, nothing happens

                }
            }
        }
    }

}
