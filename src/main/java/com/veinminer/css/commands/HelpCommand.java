package com.veinminer.css.commands;

import com.veinminer.css.config.HelpMenus;
import com.veinminer.css.enums.HelpMenuEnum;
import com.veinminer.css.models.SubCommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.List;

public class HelpCommand extends SubCommand {
    private List<String> helpMenu;

    public HelpCommand(HelpMenuEnum helpMenuKey) {
        this.helpMenu = new HelpMenus().getMenu(helpMenuKey);
    }

    public boolean onCommand(CommandSender sender, Command command, String[] args) {
        for(String line : helpMenu) {
            sender.sendMessage(line);
        }
        return true;
    }
}
