package net.danygames2014.uniwrench.api;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * An interface to define a block which wrenches can interact with
 */
@SuppressWarnings("unused")
public interface Wrenchable {
    /**
     * This method will be fired when the block is right-clicked with a wrench
     * @param stack ItemStack of the wrench
     * @param player Player which right-clicked the block
     * @param isSneaking If the player is sneaking
     * @param world The world in which this happened
     * @param x x-coordinate of the right-clicked block
     * @param y y-coordinate of the right-clicked block
     * @param z z-coordinate of the right-clicked block
     * @param side Side of the block which was right-clicked
     * @param wrenchMode The current wrench mode of the wrench
     * @return If the action was susccesfull, returning true will cancel the onUse method on the block aswell as all further actions
     */
    default boolean wrenchRightClick(ItemStack stack, PlayerEntity player, boolean isSneaking, World world, int x, int y, int z, int side, WrenchMode wrenchMode) {
        return false;
    }

    /**
     * This method will be fired when the block is left-clicked with a wrench
     * @param stack ItemStack of the wrench
     * @param player Player which left-clicked the block
     * @param isSneaking If the player is sneaking
     * @param world The world in which this happened
     * @param x x-coordinate of the left-clicked block
     * @param y y-coordinate of the left-clicked block
     * @param z z-coordinate of the left-clicked block
     * @param side Side of the block which was left-clicked
     * @param wrenchMode The current wrench mode of the wrench
     * @return If the action was susccesfull, returning true will cancel all further actions
     */
    default boolean wrenchLeftClick(ItemStack stack, PlayerEntity player, boolean isSneaking, World world, int x, int y, int z, int side, WrenchMode wrenchMode) {
        return false;
    }
}
