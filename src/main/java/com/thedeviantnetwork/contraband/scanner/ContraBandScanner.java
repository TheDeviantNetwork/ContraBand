package com.thedeviantnetwork.contraband.scanner;

import com.google.common.base.Optional;
import com.thedeviantnetwork.contraband.data.storage.Database;
import com.thedeviantnetwork.contraband.data.Record;
import org.bukkit.Chunk;
import org.bukkit.block.Block;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class ContraBandScanner {

    private Database database;

    public ContraBandScanner(Database database){
        this.database = database;
    }

    public Optional<List<Record>> scan(Inventory inventory){
        return Optional.absent();
    }

    public Optional<Record> scan(ItemStack itemStack){
        return Optional.absent();
    }

    public Optional<Record> scan(Block block){
        return Optional.absent();
    }

    public Optional<List<Record>> scan(Chunk chunk){
        return Optional.absent();
    }

}
