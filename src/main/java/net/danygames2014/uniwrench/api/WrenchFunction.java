package net.danygames2014.uniwrench.api;

import com.mojang.datafixers.util.Function9;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * A function representation of the {@link Wrenchable} interface
 * If the interface is implemented, it takes a precedence
 * <p>The Signature is as follows <code>(stack, player, isSneaking, world, x, y, z, side, wrenchMode)</code>
 */
public interface WrenchFunction extends Function9<ItemStack, PlayerEntity, Boolean, World, Integer, Integer, Integer, Integer, WrenchMode, Boolean> {

    /**
     * @param stack      ItemStack of the wrench
     * @param player     Player which wrenched the block
     * @param isSneaking If the player is sneaking
     * @param world      The world in which this happened
     * @param x          x-coordinate of the block
     * @param y          y-coordinate of the block
     * @param z          z-coordinate of the block
     * @param side       Side of the block which was wrenched
     * @param wrenchMode The current wrench mode of the wrench
     * @return Whether the action was succesfull, this will cancel all further actions
     */
    @Override
    Boolean apply(ItemStack stack, PlayerEntity player, Boolean isSneaking, World world, Integer x, Integer y, Integer z, Integer side, WrenchMode wrenchMode);
}
