package com.veinminer.css.commands.whitelist;

import com.veinminer.css.config.Configuration;
import com.veinminer.css.models.SubCommand;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class WhitelistRemoveCommand extends SubCommand {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String[] args) {
        args = args == null ? new String[]{} : args;
        if (args.length == 0) {
            sender.sendMessage(ChatColor.RED + "Missing arguments");
            sender.sendMessage(ChatColor.GREEN + "Usage /veinminer whitelist remove [block type]");
            return true;
        }
        Material material = Material.matchMaterial(args[0]);
        if (material == null) {
            sender.sendMessage(ChatColor.RED + "Unknown block");
            return true;
        }
        boolean success = Configuration.getInstance().getVeinMinerConfiguration().removeMaterialFromWhitelist(material);
        if (success) {
            sender.sendMessage(String.format(ChatColor.GREEN + "Succesfully removed %s from the veinminer whitelist", material.toString()));
            return true;
        }
        sender.sendMessage(ChatColor.RED + "Looks like this material isn't in the whitelist");
        return true;
    }
}
