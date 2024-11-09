package net.danygames2014.uwtest.block;

import net.danygames2014.uniwrench.api.WrenchMode;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.template.block.TemplateBlock;
import net.modificationstation.stationapi.api.util.Identifier;

public class SoftDependentBlock extends TemplateBlock {
    public SoftDependentBlock(Identifier identifier, Material material) {
        super(identifier, material);
    }

    public boolean wrenchRightClick(ItemStack stack, PlayerEntity player, boolean isSneaking, World world, int x, int y, int z, int side, WrenchMode wrenchMode) {
        player.sendMessage("softRight");
        return true;
    }

    public boolean wrenchLeftClick(ItemStack stack, PlayerEntity player, boolean isSneaking, World world, int x, int y, int z, int side, WrenchMode wrenchMode) {
        player.sendMessage("softLeft");
        return true;
    }
}
