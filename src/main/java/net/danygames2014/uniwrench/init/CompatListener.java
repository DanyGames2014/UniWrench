package net.danygames2014.uniwrench.init;

import net.danygames2014.uniwrench.api.event.WrenchableBlockRegisterEvent;
import net.danygames2014.uniwrench.compat.VanillaCompat;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.block.Block;

public class CompatListener {
    @EventListener
    public void registerCompat(WrenchableBlockRegisterEvent event){
        event.registerRightClickAction(Block.WOODEN_STAIRS, VanillaCompat::rotateStairs);
        event.registerRightClickAction(Block.COBBLESTONE_STAIRS, VanillaCompat::rotateStairs);
        event.registerRightClickAction(Block.PUMPKIN, VanillaCompat::rotatePumpkin);
        event.registerRightClickAction(Block.JACK_O_LANTERN, VanillaCompat::rotatePumpkin);
        event.registerRightClickAction(Block.RAIL, VanillaCompat::rotateRail);
        event.registerRightClickAction(Block.POWERED_RAIL, VanillaCompat::rotateRail);
        event.registerRightClickAction(Block.DETECTOR_RAIL, VanillaCompat::rotateRail);
        event.registerRightClickAction(Block.REPEATER, VanillaCompat::rotateRepeater);
        event.registerRightClickAction(Block.POWERED_REPEATER, VanillaCompat::rotateRepeater);
        event.registerRightClickAction(Block.FURNACE, VanillaCompat::rotateFurnace);
        event.registerRightClickAction(Block.LIT_FURNACE, VanillaCompat::rotateFurnace);
        event.registerRightClickAction(Block.PISTON, VanillaCompat::rotatePiston);
        event.registerRightClickAction(Block.STICKY_PISTON, VanillaCompat::rotatePiston);
        event.registerRightClickAction(Block.DISPENSER, VanillaCompat::rotateDispenser);
    }
}
