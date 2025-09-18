package net.danygames2014.uwtest.item;

import net.danygames2014.uniwrench.api.WrenchMode;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.block.States;
import net.modificationstation.stationapi.api.util.Identifier;

public class TestWrenchMode extends WrenchMode {
    public TestWrenchMode(Identifier identifier) {
        super(identifier);
    }

    @Override
    public boolean wrenchRightClick(ItemStack stack, PlayerEntity player, boolean isSneaking, World world, int x, int y, int z, int side, WrenchMode wrenchMode) {
        if (isSneaking) {
            world.setBlockStateWithNotify(x, y, z, States.AIR.get());
            return true;
        }
        
        return super.wrenchRightClick(stack, player, isSneaking, world, x, y, z, side, wrenchMode);
    }
}
