package mischa.lifeStealSword;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.checkerframework.common.returnsreceiver.qual.This;

public final class LifeStealSword extends JavaPlugin implements Listener {
    public static LifeStealSword getPlugin() {
        return plugin;
    }

    private static LifeStealSword plugin;


    @Override
    public void onEnable() {
        plugin = this;
        getServer().getPluginManager().registerEvents(new Events(getConfig()), this);
        this.getCommand("givelifesword").setExecutor(new GiveSword());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }



}

