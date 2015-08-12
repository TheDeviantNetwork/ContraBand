package com.thedeviantnetwork.contraband.listeners;

import com.google.common.base.Optional;
import com.thedeviantnetwork.contraband.data.Level;
import com.thedeviantnetwork.contraband.data.Record;
import com.thedeviantnetwork.contraband.scanner.ContraBandScanner;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class ContraBandListener implements Listener {

    private ContraBandScanner contraBandScanner;
    private int CHECKRATE = 5;
    private Map<UUID, Integer> rateList = new HashMap<UUID, Integer>();

    public ContraBandListener(ContraBandScanner contraBandScanner){
        this.contraBandScanner = contraBandScanner;
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event){
        if (event.getPlayer().getGameMode() == GameMode.CREATIVE)
            return;
        if (shouldCheck(event.getPlayer().getUniqueId()))
            if (event.getAction() == Action.RIGHT_CLICK_AIR && event.hasItem() || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
                Optional<List<Record>> records = contraBandScanner.scan(event.getPlayer());
                if (records.isPresent())
                    for (Record record : records.get()) {
                        for (Player player : Bukkit.getOnlinePlayers())
                            if (player.isOp() || player.hasPermission("contraband.notice")) {
                                player.playSound(player.getLocation(), Sound.EXPLODE, 100, 1);
                                player.sendMessage(record.getMessage());
                            }
                        if (record.getLevel() == Level.BAN) {
                            String message = ChatColor.RED +  "Their was no way you could have: " + record.getMaterial();
                            Bukkit.getBanList(BanList.Type.NAME).addBan(event.getPlayer().getName(), message, null, null);
                            event.getPlayer().kickPlayer(message);
                        }

                    }
            }
    }

    private boolean shouldCheck(UUID uuid) {
        if (rateList.containsKey(uuid)) {
            int rate = rateList.get(uuid) - 1;
            rateList.put(uuid, rate);
            if (rate < 0) {
                rateList.put(uuid, CHECKRATE);
            } else {
                return false;
            }
        } else
            rateList.put(uuid, CHECKRATE);
        return true;
    }
}
