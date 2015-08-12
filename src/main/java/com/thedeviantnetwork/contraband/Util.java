package com.thedeviantnetwork.contraband;

import com.thedeviantnetwork.contraband.data.ContraBand;
import com.thedeviantnetwork.contraband.data.Level;
import com.thedeviantnetwork.contraband.data.Material;
import org.bukkit.inventory.ItemStack;


public class Util {

    public static Material getMaterial(ItemStack stack){
        return new Material(stack.getType().name(), stack.getData().getData());
    }

    public static ContraBand getContraBand(ItemStack stack, Level level, int time){
        return new ContraBand(stack.getType().name(), stack.getData().getData(), level, time);
    }
}
