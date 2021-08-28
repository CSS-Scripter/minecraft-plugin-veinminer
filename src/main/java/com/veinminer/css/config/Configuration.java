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

    private List<Material> veinMinerWhitelist;
    private int veinMineLimit;
    private String pluginVersion;

    public void initializeConfig(JavaPlugin plugin) {
        initializeVeinMiner(plugin.getConfig());
        logInitialization(plugin.getLogger());
        this.pluginVersion = plugin.getDescription().getVersion();
    }

    private void initializeVeinMiner(FileConfiguration config) {
        this.veinMinerWhitelist = parseStringListToMaterialList(config.getStringList("blockWhitelist"));
        this.veinMineLimit = config.getInt("veinMineLimit");
    }

    private void logInitialization(Logger logger) {
        logger.info(String.format(
                "List of whitelisted materials has been initialized \n%s",
                veinMinerWhitelist.stream()
                        .map(Enum::toString)
                        .collect(Collectors.joining("\n- ", "- ", ""))
        ));
        logger.info(String.format(
                "VeinMine limit has been initialized at a value of %s",
                veinMineLimit
        ));
    }

    private List<Material> parseStringListToMaterialList(List<String> stringList) {
        return stringList.stream().map(Material::valueOf).collect(Collectors.toList());
    }

    public List<Material> getVeinMinerWhitelist() {
        return veinMinerWhitelist;
    }

    public int getVeinMineLimit() {
        return veinMineLimit;
    }

    public String getPluginVersion() {
        return pluginVersion;
    }
}
