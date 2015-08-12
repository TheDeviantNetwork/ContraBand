package com.thedeviantnetwork.contraband.commands;

import com.thedeviantnetwork.contraband.commands.subcommands.CommandAdd;
import com.thedeviantnetwork.contraband.commands.subcommands.CommandCheck;
import com.thedeviantnetwork.contraband.commands.subcommands.CommandRemove;
import com.thedeviantnetwork.contraband.commands.subcommands.Commandlistcontraband;
import com.thedeviantnetwork.contraband.data.storage.Database;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.HashMap;
import java.util.Map;

public class CommandCB implements CommandExecutor {

    private Map<String, CommandExecutor> subcommands = new HashMap<String, CommandExecutor>();

    public CommandCB(Database database){
        subcommands.put("add", new CommandAdd(database));
        subcommands.put("remove", new CommandRemove(database));
        subcommands.put("listcontraband", new Commandlistcontraband(database));
        subcommands.put("check", new CommandCheck(database));
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (strings.length > 0)
        {
            String subcommand = strings[0].toLowerCase();
            if (subcommands.containsKey(subcommand))
                return subcommands.get(subcommand).onCommand(commandSender, command, s, strings);
        }
        commandSender.sendMessage("/cb add <SUSPICIOUS|WARNING|BAN> [time in hours]");
        commandSender.sendMessage("/cb remove");
        commandSender.sendMessage("/cb listcontraband");
        commandSender.sendMessage("/cb check <playername>");
        return false;
    }
}
