package com.thedeviantnetwork.contraband.commands.subcommands;

import com.thedeviantnetwork.contraband.data.Record;
import com.thedeviantnetwork.contraband.data.storage.Database;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/**
 * Created by thomas15v on 13/08/15.
 */
public class CommandList implements CommandExecutor{

    private Database database;

    public CommandList(Database database){
        this.database = database;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (strings.length > 1){
            OfflinePlayer player = Bukkit.getOfflinePlayer(strings[1]);
            if (player == null) {
                commandSender.sendMessage("Could not find player " + strings[1]);
            } else {
                commandSender.sendMessage("List of contraband:");
                for (Record record : database.getRecords(player.getUniqueId())){
                    commandSender.sendMessage(record.getMessage());
                }
                commandSender.sendMessage("");
            }
            return true;
        }
        commandSender.sendMessage("/cb list <player>");
        return false;
    }
}
