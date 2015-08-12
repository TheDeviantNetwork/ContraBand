package com.thedeviantnetwork.contraband.commands.subcommands;

import com.thedeviantnetwork.contraband.data.Record;
import com.thedeviantnetwork.contraband.data.storage.Database;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CommandCheck implements CommandExecutor {

    private Database database;

    public CommandCheck(Database database){
        this.database = database;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (strings.length > 1) {
            OfflinePlayer player = Bukkit.getServer().getOfflinePlayer(strings[1]);

            commandSender.sendMessage("Player " + player.getName() + " has following records: ");
            int i = 0;
            for (Record record: database.getRecords(player.getUniqueId())) {
                System.out.println(record);
                commandSender.sendMessage(i + " " + record.getMessage());
                i++;
            }

            return true;
        }
        return false;

    }
}
