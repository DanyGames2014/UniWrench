package net.danygames2014.uwtest.event;

import net.danygames2014.uniwrench.api.WrenchMode;
import net.danygames2014.uniwrench.api.event.UniversalWrenchModeEvent;
import net.danygames2014.uniwrench.api.event.WrenchModeRegistryEvent;
import net.danygames2014.uniwrench.api.event.WrenchableBlockRegisterEvent;
import net.danygames2014.uwtest.block.CheeseBlock;
import net.danygames2014.uwtest.block.SoftDependentBlock;
import net.danygames2014.uwtest.block.WrenchableBlock;
import net.danygames2014.uwtest.item.TestWrench;
import net.danygames2014.uwtest.item.TestWrenchMode;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.modificationstation.stationapi.api.event.registry.BlockRegistryEvent;
import net.modificationstation.stationapi.api.event.registry.ItemRegistryEvent;
import net.modificationstation.stationapi.api.mod.entrypoint.Entrypoint;
import net.modificationstation.stationapi.api.util.Namespace;
import net.modificationstation.stationapi.api.util.Null;

public class TestListener {
    @Entrypoint.Namespace
    public static Namespace NAMESPACE;

    public static Block wrenchableBlock;
    public static Block cheeseBlock;
    public static Block softDependentBlock;

    public static Item testWrench;

    @EventListener
    public void registerBlock(BlockRegistryEvent event){
        wrenchableBlock = new WrenchableBlock(NAMESPACE.id("wrenchable_block")).setTranslationKey(NAMESPACE,"wrenchable_block").setHardness(1.0F);
        cheeseBlock = new CheeseBlock(NAMESPACE.id("cheese_block"), Material.CLAY).setTranslationKey(NAMESPACE, "cheese_block").setHardness(1.0F);
        softDependentBlock = new SoftDependentBlock(NAMESPACE.id("soft_dependent_block"), Material.METAL).setTranslationKey(NAMESPACE, "soft_dependent_block").setHardness(1.0F);
    }

    @EventListener
    public void registerItems(ItemRegistryEvent event){
        testWrench = new TestWrench(NAMESPACE.id("test_wrench")).setTranslationKey(NAMESPACE, "test_wrench");
    }
}
