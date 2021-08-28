package com.veinminer.css;

import com.veinminer.css.config.Configuration;
import com.veinminer.css.listeners.BlockBreakListener;
import org.bukkit.Material;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info("Starting Veinminer!");
        this.saveDefaultConfig();

        Configuration.getInstance().initializeConfig(this);

        getServer().getPluginManager().registerEvents(new BlockBreakListener(this), this);
    }
}
