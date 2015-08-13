package com.thedeviantnetwork.contraband.commands.subcommands;

import com.google.common.base.Optional;
import com.thedeviantnetwork.contraband.data.Record;
import com.thedeviantnetwork.contraband.data.storage.Database;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by thomas15v on 13/08/15.
 */
public class CommandOke implements CommandExecutor {

    private Database database;

    public CommandOke(Database database){
        this.database = database;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (strings.length > 1){
            try {
                int reportid = Integer.parseInt(strings[1]);
                Optional<Record> record = database.getRecord(reportid);
                if (record.isPresent()) {
                    record.get().setSolved();
                    commandSender.sendMessage("Report " + reportid + " is now solved!");
                }
                else
                    commandSender.sendMessage("Could not find record for id " + reportid);
                return true;
            }
            catch (NumberFormatException e){
                OfflinePlayer player = Bukkit.getOfflinePlayer(strings[1]);
                if (player == null) {
                    commandSender.sendMessage("Could not find player " + strings[1]);
                }else {
                    for (Record record : database.getRecords(player.getUniqueId()))
                        record.setSolved();
                    commandSender.sendMessage("All records for player " + player.getName() + " are now solved");
                }
                return true;
            }
        }
        commandSender.sendMessage("/cb oke <reportid|playername>");
        return false;
    }
}
