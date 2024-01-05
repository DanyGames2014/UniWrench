package net.danygames2014.uniwrench.init;

import net.danygames2014.uniwrench.api.WrenchMode;
import net.danygames2014.uniwrench.api.Wrenchable;
import net.minecraft.block.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.template.block.TemplateBlock;
import net.modificationstation.stationapi.api.util.Identifier;

public class WrenchableBlock extends TemplateBlock implements Wrenchable {
    public WrenchableBlock(Identifier identifier) {
        super(identifier, Material.STONE);
    }

    @Override
    public void wrenchLeftClick(ItemStack stack, PlayerEntity player, boolean isSneaking, World world, int x, int y, int z, int side, WrenchMode wrenchMode) {
        System.out.println("Wrench Left Click | isRemote : " + world.isRemote + " | WrenchMode : " + wrenchMode.identifier);
        player.method_490("Wrench Left Click | isRemote : " + world.isRemote + " | WrenchMode : " + wrenchMode.identifier);
    }

    @Override
    public void wrenchRightClick(ItemStack stack, PlayerEntity player, boolean isSneaking, World world, int x, int y, int z, int side, WrenchMode wrenchMode) {
        System.out.println("Wrench Right Click | isRemote : " + world.isRemote + " | WrenchMode : " + wrenchMode.identifier);
        player.method_490("Wrench Right Click | isRemote : " + world.isRemote + " | WrenchMode : " + wrenchMode.identifier);
    }
}
