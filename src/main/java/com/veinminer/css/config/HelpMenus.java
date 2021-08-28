package com.veinminer.css.config;

import com.veinminer.css.enums.HelpMenuEnum;
import com.veinminer.css.models.HelpMenuCommandEntry;
import org.bukkit.ChatColor;

import java.util.*;
import java.util.stream.Collectors;

public class HelpMenus {
    private final HelpMenuEnum DEFAULT_KEY = HelpMenuEnum.INDEX;
    private final Map<HelpMenuEnum, HelpMenuCommandEntry[]> helpMenus = new HashMap<>();

    public HelpMenus() {
        helpMenus.put(DEFAULT_KEY, new HelpMenuCommandEntry[]{
                new HelpMenuCommandEntry("/veinminer help", "Shows this menu"),
                new HelpMenuCommandEntry("/veinminer whitelist help", "Shows commands for managing whitelist")
        });
        helpMenus.put(HelpMenuEnum.WHITELIST, new HelpMenuCommandEntry[]{
                new HelpMenuCommandEntry("/veinminer whitelist help", "Shows this menu"),
                new HelpMenuCommandEntry("/veinminer whitelist list", "Displays all whitelisted blocks"),
                new HelpMenuCommandEntry("/veinminer whitelist add [blocktype]", "Adds a block to the whitelist"),
                new HelpMenuCommandEntry("/veinminer whitelist remove [blocktype]", "Removes a block to the whitelist")
        });
    }

    public List<String> getMenu(HelpMenuEnum menuKey) {
        HelpMenuCommandEntry[] menu = helpMenus.get(menuKey);
        if (menu == null) menu = helpMenus.get(DEFAULT_KEY);
        List<String> helpMenu = new ArrayList<>();
        helpMenu.add(createPrefix());
        helpMenu.addAll(Arrays.stream(menu).map(HelpMenuCommandEntry::generateHelpEntry).collect(Collectors.toList()));
        helpMenu.add(createSuffix());
        return helpMenu;
    }

    private String createPrefix() {
        return String.format(ChatColor.DARK_GREEN + "=-- Veinminer v%s --=", Configuration.getInstance().getPluginVersion());
    }

    private String createSuffix() {
        return ChatColor.DARK_GRAY + "=-- By CSS-Scripter --=";
    }
}
