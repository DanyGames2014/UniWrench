package net.danygames2014.uniwrench.api.event;

import net.danygames2014.uniwrench.api.WrenchFunction;
import net.danygames2014.uniwrench.api.WrenchableBlockRegistry;
import net.mine_diver.unsafeevents.Event;
import net.mine_diver.unsafeevents.event.EventPhases;
import net.minecraft.block.Block;
import net.modificationstation.stationapi.api.StationAPI;

@SuppressWarnings("UnstableApiUsage")
@EventPhases(StationAPI.INTERNAL_PHASE)
public class WrenchableBlockRegisterEvent extends Event {
    /**
     * Registers a function to be called when the specified block is left clicked with a wrench
     * @param block The block that the function will be called for
     * @param wrenchable The function with a signature of <code>(stack, player, isSneaking, world, x, y, z, side, wrenchMode)</code>
     */
    public void registerLeftClickAction(Block block, WrenchFunction wrenchable){
        WrenchableBlockRegistry.registerLeftClickAction(block, wrenchable);
    }

    /**
     * Registers a function to be called when the specified block is right clicked with a wrench
     * @param block The block that the function will be called for
     * @param wrenchable The function with a signature of <code>(stack, player, isSneaking, world, x, y, z, side, wrenchMode)</code>
     */
    public void registerRightClickAction(Block block, WrenchFunction wrenchable){
        WrenchableBlockRegistry.registerRightClickAction(block, wrenchable);
    }
}
