package net.danygames2014.uniwrench.compat;

import net.danygames2014.uniwrench.api.WrenchMode;
import net.minecraft.block.*;
import net.minecraft.block.entity.PistonBlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class VanillaCompat {
    public static boolean rotateStairs(ItemStack stack, PlayerEntity player, boolean isSneaking, World world, int x, int y, int z, int side, WrenchMode wrenchMode) {
        if (wrenchMode == WrenchMode.MODE_ROTATE && world.getBlockState(x, y, z).getBlock() instanceof StairsBlock) {
            world.setBlockMeta(x, y, z, (world.getBlockMeta(x, y, z) + 1) & 3);
            return true;
        }
        return false;
    }

    public static boolean rotatePumpkin(ItemStack stack, PlayerEntity player, boolean isSneaking, World world, int x, int y, int z, int side, WrenchMode wrenchMode) {
        if (wrenchMode == WrenchMode.MODE_ROTATE && world.getBlockState(x, y, z).getBlock() instanceof PumpkinBlock) {
            world.setBlockMeta(x, y, z, (world.getBlockMeta(x, y, z) + 1) & 3);
            return true;
        }
        return false;
    }

    public static boolean rotateRail(ItemStack stack, PlayerEntity player, boolean isSneaking, World world, int x, int y, int z, int side, WrenchMode wrenchMode) {
        if (wrenchMode == WrenchMode.MODE_ROTATE && world.getBlockState(x, y, z).getBlock() instanceof RailBlock rail) {
            world.setBlockMeta(x, y, z, (world.getBlockMeta(x, y, z) + 1) & 7);

            int meta = world.getBlockMeta(x, y, z);
            int straightMeta = meta;
            if (rail.isAlwaysStraight()) {
                straightMeta = meta & 7;
            }

            boolean isPowered =
                    world.isEmittingRedstonePower(x, y, z) ||
                            world.isEmittingRedstonePower(x, y + 1, z) ||
                            rail.isPoweredByConnectedRails(world, x, y, z, meta, true, 0) ||
                            rail.isPoweredByConnectedRails(world, x, y, z, meta, false, 0);

            if (isPowered && (meta & 8) == 0) {
                world.setBlockMeta(x, y, z, straightMeta | 8);
            }


            return true;
        }
        return false;
    }

    public static boolean rotateRepeater(ItemStack stack, PlayerEntity player, boolean isSneaking, World world, int x, int y, int z, int side, WrenchMode wrenchMode) {
        if (wrenchMode == WrenchMode.MODE_ROTATE && world.getBlockState(x, y, z).getBlock() instanceof RepeaterBlock repeater) {
            world.setBlockMeta(x, y, z, (world.getBlockMeta(x, y, z) + 1) & 3);
            repeater.onTick(world, x, y, z, world.random);
            return true;
        }
        return false;
    }

    public static boolean rotateFurnace(ItemStack stack, PlayerEntity player, boolean isSneaking, World world, int x, int y, int z, int side, WrenchMode wrenchMode) {
        if (wrenchMode == WrenchMode.MODE_ROTATE && world.getBlockState(x, y, z).getBlock() instanceof FurnaceBlock) {
            world.setBlockMeta(x, y, z, 2 + ((world.getBlockMeta(x, y, z) - 1) & 3));
            return true;
        }
        return false;
    }

    public static boolean rotatePiston(ItemStack stack, PlayerEntity player, boolean isSneaking, World world, int x, int y, int z, int side, WrenchMode wrenchMode) {
        if (wrenchMode == WrenchMode.MODE_ROTATE && world.getBlockState(x, y, z).getBlock() instanceof PistonBlock) {
            if(world.getBlockMeta(x,y,z) < 6){
                world.setBlockMeta(x, y, z, (world.getBlockMeta(x, y, z) + 1) % 6);
                return true;
            }
        }
        return false;
    }

    public static boolean rotateDispenser(ItemStack stack, PlayerEntity player, boolean isSneaking, World world, int x, int y, int z, int side, WrenchMode wrenchMode) {
        if (wrenchMode == WrenchMode.MODE_ROTATE && world.getBlockState(x, y, z).getBlock() instanceof DispenserBlock) {
            world.setBlockMeta(x, y, z, 2 + ((world.getBlockMeta(x, y, z) - 1) & 3));
            return true;
        }
        return false;
    }
}
