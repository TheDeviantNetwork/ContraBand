package com.thedeviantnetwork.contraband.commands.subcommands;

import com.thedeviantnetwork.contraband.data.storage.Database;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CommandAdd implements CommandExecutor {

    private Database database;

    public CommandAdd(Database database) {
        this.database = database;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        return false;
    }

}
