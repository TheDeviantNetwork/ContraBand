package com.thedeviantnetwork.contraband.commands.subcommands;

import com.thedeviantnetwork.contraband.data.ContraBand;
import com.thedeviantnetwork.contraband.data.storage.Database;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Commandlistcontraband implements CommandExecutor {

    private Database database;

    public Commandlistcontraband(Database database){
        this.database = database;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        commandSender.sendMessage("List of contraband:");
        for (ContraBand contraBand : database.getContraBandList())
            commandSender.sendMessage(contraBand + " " + contraBand.getLevel() + " " + contraBand.getTime() + "h");
        commandSender.sendMessage("");
        return true;
    }
}
