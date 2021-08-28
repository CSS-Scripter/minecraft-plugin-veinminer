package com.veinminer.css.models;

import org.bukkit.ChatColor;

public class HelpMenuCommandEntry {
    private String usage;
    private String description;

    public HelpMenuCommandEntry(String usage, String description) {
        this.usage = usage;
        this.description = description;
    }

    public String generateHelpEntry() {
        return ChatColor.GREEN + usage + ChatColor.GRAY + " - " + description;
    }
}
