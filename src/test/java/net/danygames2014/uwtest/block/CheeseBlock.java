package net.danygames2014.uwtest.block;

import net.danygames2014.uniwrench.api.WrenchMode;
import net.danygames2014.uniwrench.api.Wrenchable;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.template.block.TemplateBlock;
import net.modificationstation.stationapi.api.util.Identifier;

public class CheeseBlock extends TemplateBlock implements Wrenchable {
    public CheeseBlock(Identifier identifier, Material material) {
        super(identifier, material);
    }

    @Override
    public boolean onUse(World world, int x, int y, int z, PlayerEntity player) {
        System.out.println("PABLO");
        return true;
    }

    @Override
    public void wrenchLeftClick(ItemStack stack, PlayerEntity player, boolean isSneaking, World world, int x, int y, int z, int side, WrenchMode wrenchMode) {
        System.out.println("LEFT");
    }

    @Override
    public void wrenchRightClick(ItemStack stack, PlayerEntity player, boolean isSneaking, World world, int x, int y, int z, int side, WrenchMode wrenchMode) {
        System.out.println("RIGHT");
    }
}
