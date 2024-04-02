package net.danygames2014.uwtest.event;

import net.danygames2014.uwtest.block.WrenchableBlock;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.block.Block;
import net.modificationstation.stationapi.api.event.registry.BlockRegistryEvent;
import net.modificationstation.stationapi.api.mod.entrypoint.Entrypoint;
import net.modificationstation.stationapi.api.util.Namespace;
import net.modificationstation.stationapi.api.util.Null;

public class TestListener {
    @Entrypoint.Namespace
    public static final Namespace MOD_ID = Null.get();

    public static Block wrenchableBlock;

    @EventListener
    public void registerBlock(BlockRegistryEvent event){
        wrenchableBlock = new WrenchableBlock(MOD_ID.id("wrenchable_block")).setTranslationKey(MOD_ID,"wrenchable_block");
    }
}
