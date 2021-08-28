package com.veinminer.css.commands.whitelist;

import com.veinminer.css.config.Configuration;
import com.veinminer.css.models.SubCommand;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.stream.Collectors;


public class WhitelistListCommand extends SubCommand {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String[] args) {
        sender.sendMessage(ChatColor.GREEN + "Blocks currently on the whitelist are: "+ ChatColor.DARK_GREEN +
                Configuration.getInstance().getVeinMinerConfiguration()
                        .getVeinMinerWhitelist().stream()
                        .map(Enum::toString)
                        .collect(Collectors.joining(ChatColor.GRAY + ", " + ChatColor.DARK_GREEN)));
        return true;
    }
}
