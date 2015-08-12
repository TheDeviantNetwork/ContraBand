package com.thedeviantnetwork.contraband.scanner;

import com.google.common.base.Optional;
import com.thedeviantnetwork.contraband.data.ContraBand;
import com.thedeviantnetwork.contraband.data.storage.Database;
import com.thedeviantnetwork.contraband.data.Record;
import org.bukkit.Chunk;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class ContraBandScanner {

    private Database database;

    public ContraBandScanner(Database database){
        this.database = database;
    }

    public Optional<List<Record>> scan(Player player){
        List<Record> recordList = null;
        for (ItemStack itemStack : player.getInventory())
            if (itemStack != null)
                for (ContraBand contraBand : database.getContraBandList())
                    if (contraBand.equals(itemStack.getType(), itemStack.getData().getData())) {
                        if (recordList == null)
                            recordList = new ArrayList<Record>();
                        Record record = new Record(
                                player.getUniqueId(),
                                contraBand,
                                player.getLocation().getBlockX(),
                                player.getLocation().getBlockY(),
                                player.getLocation().getBlockZ(),
                                player.getWorld().getName()
                        );
                        recordList.add(record);
                        database.addRecord(record);
                    }
        return Optional.fromNullable(recordList);
    }

    public Optional<Record> scan(Player player, ItemStack itemStack){
        return Optional.absent();
    }

}
