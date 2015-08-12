package com.thedeviantnetwork.contraband.material;

import com.thedeviantnetwork.contraband.Util;
import com.thedeviantnetwork.contraband.data.Material;
import org.bukkit.inventory.ItemStack;
import org.junit.Test;

import static org.junit.Assert.*;

public class MaterialResolverTest {
    @Test
    public void testItemStack(){
        Material material = Util.getMaterial(new ItemStack(org.bukkit.Material.ACACIA_STAIRS, 8, (short) 2));
        assertNotNull(material);
    }

}