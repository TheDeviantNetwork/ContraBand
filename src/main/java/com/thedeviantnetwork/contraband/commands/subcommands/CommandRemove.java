package com.thedeviantnetwork.contraband.commands.subcommands;

import com.thedeviantnetwork.contraband.Util;
import com.thedeviantnetwork.contraband.data.Material;
import com.thedeviantnetwork.contraband.data.storage.Database;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandRemove implements CommandExecutor {

    private Database database;

    public CommandRemove(Database database){
        this.database = database;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage("You have to be a player to perform this command");
            return false;
        }else if (((Player) commandSender).getItemInHand().getType() == org.bukkit.Material.AIR) {
            commandSender.sendMessage("You need to hold the item that you wand to add");
            return false;
        }
        Material material = Util.getMaterial(((Player) commandSender).getItemInHand());
        database.removeContraBand(material);
        commandSender.sendMessage("Removed " + material);
        return true;
    }
}
