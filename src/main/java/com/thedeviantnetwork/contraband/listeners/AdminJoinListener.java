package com.thedeviantnetwork.contraband.listeners;

import com.thedeviantnetwork.contraband.data.Record;
import com.thedeviantnetwork.contraband.data.storage.Database;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;

public class AdminJoinListener {

    private Database database;

    public AdminJoinListener(Database database){
        this.database = database;
    }

    @EventHandler
    public void onPlayerJoinEvent(PlayerJoinEvent event){
        if (event.getPlayer().isOp() || event.getPlayer().hasPermission("contraband.notice")){
            for (Record record : database.getUnSolvedRecords())
                event.getPlayer().sendMessage(record.getMessage());
        }
    }

}
