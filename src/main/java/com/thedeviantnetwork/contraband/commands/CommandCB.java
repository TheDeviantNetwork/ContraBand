package com.thedeviantnetwork.contraband.commands;

import com.thedeviantnetwork.contraband.commands.subcommands.CommandAdd;
import com.thedeviantnetwork.contraband.data.storage.Database;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.HashMap;
import java.util.Map;

public class CommandCB implements CommandExecutor {

    private Database database;
    private Map<String, CommandExecutor> subcommands = new HashMap<String, CommandExecutor>();

    public CommandCB(Database database){
        this.database = database;
        subcommands.put("add", new CommandAdd(database));
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (strings.length > 1)
        {
            String subcommand = strings[0].toLowerCase();
            if (subcommands.containsKey(subcommand))
                return subcommands.get(subcommand).onCommand(commandSender, command, s, strings);
        }
        return false;
    }
}
