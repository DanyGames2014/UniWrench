package net.danygames2014.uwtest.block;

import net.danygames2014.uniwrench.api.WrenchFunction;
import net.danygames2014.uniwrench.api.WrenchMode;
import net.danygames2014.uniwrench.api.Wrenchable;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.template.block.TemplateBlock;
import net.modificationstation.stationapi.api.util.Identifier;
import org.lwjgl.input.Mouse;

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
    public boolean wrenchLeftClick(ItemStack stack, PlayerEntity player, boolean isSneaking, World world, int x, int y, int z, int side, WrenchMode wrenchMode) {
        System.out.println("LEFT");
        System.out.println("ISDOWN: " + Mouse.isButtonDown(0) + " | BUTTONSTATE: " + Mouse.getEventButtonState());
        return !isSneaking;
    }

    @Override
    public boolean wrenchRightClick(ItemStack stack, PlayerEntity player, boolean isSneaking, World world, int x, int y, int z, int side, WrenchMode wrenchMode) {
        System.out.println("RIGHT");
        return !isSneaking;
    }
}
