package com.veinminer.css.commands;

import com.veinminer.css.commands.whitelist.WhitelistAddCommand;
import com.veinminer.css.enums.HelpMenuEnum;
import com.veinminer.css.models.SubCommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.HashMap;
import java.util.Map;

public class WhitelistCommand extends SubCommand {

    private final String DEFAULT_COMMAND = "help";
    private final Map<String, SubCommand> subCommands = new HashMap<>();

    public WhitelistCommand(String permission) {
        super(permission);
        subCommands.put(DEFAULT_COMMAND, new HelpCommand(HelpMenuEnum.WHITELIST));
        subCommands.put("add", new WhitelistAddCommand());
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String[] args) {
        if (args == null) args = new String[]{};
        String arg = args.length == 0 ? DEFAULT_COMMAND : args[0];
        SubCommand subCommand = subCommands.get(arg);
        if (subCommand == null) subCommand = subCommands.get(DEFAULT_COMMAND);
        return subCommand.onCommand(sender, command, args);
    }
}
