package net.danygames2014.uniwrench.init;

import net.danygames2014.uniwrench.block.WrenchableBlock;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.modificationstation.stationapi.api.event.registry.BlockRegistryEvent;
import net.modificationstation.stationapi.api.mod.entrypoint.Entrypoint;
import net.modificationstation.stationapi.api.template.block.TemplateBlock;
import net.modificationstation.stationapi.api.util.Namespace;
import net.modificationstation.stationapi.api.util.Null;

public class BlockListener {
    @Entrypoint.Namespace
    public static final Namespace MOD_ID = Null.get();

    public static Block wrenchableBlock;

    @EventListener
    public void registerTestBlock(BlockRegistryEvent event){
        wrenchableBlock = new WrenchableBlock(MOD_ID.id("test_block")).setTranslationKey(MOD_ID, "wrenchable_block");
    }
}
