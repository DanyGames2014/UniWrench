package net.danygames2014.uniwrench.block;

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
        super(identifier, Material.WOOD);
        this.setHardness(0.2F);
        this.setResistance(1.0F);
    }

    @Override
    public void wrenchRightClick(ItemStack stack, PlayerEntity player, boolean isSneaking, World world, int x, int y, int z, int side, WrenchMode wrenchMode) {
        player.method_490("Stack : " + stack + " | Sneaking : " + isSneaking + " | X : " + x + " | Y : " + y + " | Z : " + z + " | Side : " + side + " | Wrench Mode : " + wrenchMode.getTranslatedName());
    }

    @Override
    public void wrenchLeftClick(ItemStack stack, PlayerEntity player, boolean isSneaking, World world, int x, int y, int z, int side, WrenchMode wrenchMode) {
        player.method_490("Stack : " + stack + " | Sneaking : " + isSneaking + " | X : " + x + " | Y : " + y + " | Z : " + z + " | Side : " + side + " | Wrench Mode : " + wrenchMode.getTranslatedName());
    }
}
