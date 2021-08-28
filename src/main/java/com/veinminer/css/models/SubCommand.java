package com.veinminer.css.models;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public abstract class SubCommand {
    private String permission = "";
    public SubCommand() {}
    public SubCommand(String permission) {
        this.permission = permission;
    }

    public abstract boolean onCommand(CommandSender sender, Command command, String[] args);

    public boolean doesSenderHavePermission(CommandSender sender) {
        return permission.equals("") || sender.hasPermission(this.permission);
    }
}
