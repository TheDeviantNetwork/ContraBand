package com.thedeviantnetwork.contraband;

import com.thedeviantnetwork.contraband.data.Material;
import org.bukkit.inventory.ItemStack;


public class Util {

    public static Material getMaterial(ItemStack stack){
        return new Material(stack.getType().name(), stack.getData().getData());
    }
}
