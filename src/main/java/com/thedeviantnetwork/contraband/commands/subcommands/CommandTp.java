package com.thedeviantnetwork.contraband.commands.subcommands;

import com.thedeviantnetwork.contraband.data.storage.Database;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/**
 * Created by thomas15v on 13/08/15.
 */
public class CommandTp implements CommandExecutor{

    private Database database;

    public CommandTp(Database database){
        this.database = database;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (strings.length > 1){
            return true;
        }
        commandSender.sendMessage("/cb tp <reportid>");
        return false;
    }
}
