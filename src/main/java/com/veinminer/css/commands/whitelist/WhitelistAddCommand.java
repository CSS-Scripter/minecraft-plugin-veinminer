package com.veinminer.css.commands.whitelist;

import com.veinminer.css.models.SubCommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class WhitelistAddCommand extends SubCommand {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String[] args) {
        sender.sendMessage("Adding to whitelist");
        return true;
    }
}
