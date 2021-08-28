package com.veinminer.css;

import com.veinminer.css.commands.CommandHandler;
import com.veinminer.css.config.Configuration;
import com.veinminer.css.listeners.BlockBreakListener;
import org.bukkit.plugin.java.JavaPlugin;
import java.util.logging.Level;


public class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info("Starting Veinminer!");
        this.saveDefaultConfig();

        Configuration.getInstance().initializeConfig(this);
        registerCommands();
        registerEventListeners();
    }

    private void registerCommands() {
        if (this.getCommand("veinminer") == null) {
            getLogger().log(Level.SEVERE, "Something went wrong while registering commands");
            return;
        }
        this.getCommand("veinminer").setExecutor(new CommandHandler());
    }

    private void registerEventListeners() {
        getServer().getPluginManager().registerEvents(new BlockBreakListener(), this);
    }
}
