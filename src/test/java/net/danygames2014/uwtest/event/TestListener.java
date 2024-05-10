package net.danygames2014.uwtest.event;

import net.danygames2014.uwtest.block.CheeseBlock;
import net.danygames2014.uwtest.block.WrenchableBlock;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.modificationstation.stationapi.api.event.registry.BlockRegistryEvent;
import net.modificationstation.stationapi.api.mod.entrypoint.Entrypoint;
import net.modificationstation.stationapi.api.util.Namespace;
import net.modificationstation.stationapi.api.util.Null;

public class TestListener {
    @Entrypoint.Namespace
    public static final Namespace MOD_ID = Null.get();

    public static Block wrenchableBlock;
    public static Block cheeseBlock;

    @EventListener
    public void registerBlock(BlockRegistryEvent event){
        wrenchableBlock = new WrenchableBlock(MOD_ID.id("wrenchable_block")).setTranslationKey(MOD_ID,"wrenchable_block").setHardness(1.0F);
        cheeseBlock = new CheeseBlock(MOD_ID.id("cheese_block"), Material.CLAY).setTranslationKey(MOD_ID, "cheese_block").setHardness(1.0F);
    }
}
