package com.veinminer.css.config;

import com.veinminer.css.enums.ConfigPathEnum;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class VeinMinerConfiguration {
    private JavaPlugin plugin;
    private List<Material> veinMinerWhitelist;
    private int veinMineLimit;

    public VeinMinerConfiguration(JavaPlugin plugin) {
        this.plugin = plugin;
        this.initializeVeinMiner();
        this.logInitialization();
    }
    private void initializeVeinMiner() {
        FileConfiguration config = plugin.getConfig();
        this.veinMinerWhitelist = parseStringListToMaterialList(config.getStringList(ConfigPathEnum.BLOCK_WHITELIST.toString()));
        this.veinMineLimit = config.getInt(ConfigPathEnum.BLOCK_LIMIT.toString());
    }

    private void logInitialization() {
        Logger logger = plugin.getLogger();
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

    public boolean addMaterialToWhitelist(Material material) {
        FileConfiguration config = plugin.getConfig();
        List<String> currentWhitelist = config.getStringList("blockWhitelist");
        if (currentWhitelist.contains(material.toString())) return false;
        currentWhitelist.add(material.toString());
        config.set(ConfigPathEnum.BLOCK_WHITELIST.toString(), currentWhitelist);
        plugin.saveConfig();
        this.veinMinerWhitelist = parseStringListToMaterialList(currentWhitelist);
        return true;
    }
}
