package com.veinminer.css.commands;

import com.veinminer.css.enums.HelpMenuEnum;
import com.veinminer.css.models.SubCommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class CommandHandler implements CommandExecutor {

    private final String DEFAULT_COMMAND = "help";
    private final Map<String, SubCommand> subcommands = new HashMap<>();

    public CommandHandler() {
        subcommands.put(DEFAULT_COMMAND, new HelpCommand(HelpMenuEnum.INDEX));
        subcommands.put("whitelist", new WhitelistCommand("veinminer.whitelist"));
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args == null) args = new String[]{};
        String arg = args.length == 0 ? DEFAULT_COMMAND : args[0];
        SubCommand subCommand = subcommands.get(arg);
        if (subCommand == null) subCommand = subcommands.get(DEFAULT_COMMAND);
        if (!subCommand.doesSenderHavePermission(sender)) {
            sender.sendMessage("You don't seem to have permission for this command");
            return false;
        }
        return subCommand.onCommand(sender, command, Arrays.copyOfRange(args, Math.min(args.length, 1), args.length));
    }
}
