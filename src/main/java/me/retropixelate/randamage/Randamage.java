package me.retropixelate.randamage;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

import java.util.*;

public final class Randamage extends JavaPlugin {

    public Randamage plugin;

    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(new PlayerEventListener(this), this);
        BukkitTask pDmgCdTicks = new PlayerDamageCountdown(this).runTaskTimer(this, 0, 1);
        BukkitTask pShockTicks = new PlayerShock(this).runTaskTimer(this, 0, 1);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
