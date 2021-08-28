package com.veinminer.css.config;

import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class Configuration {

    // Singleton stuff ===========================
    private static Configuration INSTANCE;

    private Configuration() {
    }

    public static Configuration getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new Configuration();
        }

        return INSTANCE;
    }
    // Singleton stuff ===========================

    private FileConfiguration config;
    private Logger logger;

    private String pluginVersion;
    private VeinMinerConfiguration veinMinerConfiguration;

    public void initializeConfig(JavaPlugin plugin) {
        veinMinerConfiguration = new VeinMinerConfiguration(plugin);
        this.pluginVersion = plugin.getDescription().getVersion();
    }

    public VeinMinerConfiguration getVeinMinerConfiguration() {
        return this.veinMinerConfiguration;
    }

    public String getPluginVersion() {
        return pluginVersion;
    }
}
