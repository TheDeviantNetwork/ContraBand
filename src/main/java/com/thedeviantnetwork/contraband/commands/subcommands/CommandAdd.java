package com.thedeviantnetwork.contraband.commands.subcommands;

import com.thedeviantnetwork.contraband.Util;
import com.thedeviantnetwork.contraband.data.ContraBand;
import com.thedeviantnetwork.contraband.data.Level;
import com.thedeviantnetwork.contraband.data.storage.Database;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.awt.*;

public class CommandAdd implements CommandExecutor {

    private Database database;

    public CommandAdd(Database database) {
        this.database = database;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        try {
            if (strings.length > 1) {
                if (!(commandSender instanceof Player)) {
                    commandSender.sendMessage("You have to be a player to perform this command");
                    return false;
                }else if (((Player) commandSender).getItemInHand().getType() == Material.AIR) {
                    commandSender.sendMessage("You need to hold the item that you wand to add");
                    return false;
                }
                Level level = Level.valueOf(strings[1].toUpperCase());
                int time = strings.length > 2 ? Integer.valueOf(strings[2]): 0;
                ContraBand contraBand = Util.getContraBand(((Player)commandSender).getItemInHand(), level, time);
                database.addContraBand(contraBand);
                commandSender.sendMessage(contraBand + " is now " + level + " for players under " + time + " hours");
                return true;
            }
        }catch (IllegalArgumentException e){
            commandSender.sendMessage("Not a valid warning level. Valid warning levels are: SUSPICIOUS, WARNING or BAN");
        }
        commandSender.sendMessage("/cb add <SUSPICIOUS|WARNING|BAN> [time in hours]");
        return false;
    }

}
