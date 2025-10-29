package net.danygames2014.uniwrench.item;

import net.danygames2014.uniwrench.UniWrench;
import net.danygames2014.uniwrench.api.WrenchFunction;
import net.danygames2014.uniwrench.api.WrenchMode;
import net.danygames2014.uniwrench.api.Wrenchable;
import net.danygames2014.uniwrench.api.WrenchableBlockRegistry;
import net.danygames2014.uniwrench.network.WrenchModeC2SPacket;
import net.danygames2014.uniwrench.util.HotbarTooltipHelper;
import net.danygames2014.uniwrench.util.MathUtil;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.block.BlockState;
import net.modificationstation.stationapi.api.client.item.CustomTooltipProvider;
import net.modificationstation.stationapi.api.item.StationItemNbt;
import net.modificationstation.stationapi.api.network.packet.PacketHelper;
import net.modificationstation.stationapi.api.template.item.TemplateItem;
import net.modificationstation.stationapi.api.util.Identifier;

import java.util.ArrayList;

public class WrenchBase extends TemplateItem implements CustomTooltipProvider {
    private final ArrayList<WrenchMode> wrenchModes;
    protected int usageDelay;

    public WrenchBase(Identifier identifier) {
        super(identifier);
        this.setMaxCount(1);
        this.wrenchModes = new ArrayList<>();
        this.usageDelay = 0;
    }

    // Wrench Modes
    @Environment(EnvType.CLIENT)
    public void cycleWrenchMode(ItemStack itemStack, int direction, PlayerEntity player) {
        if (this.wrenchModes == null || this.wrenchModes.isEmpty()) {
            return;
        }

        this.setWrenchMode(itemStack, MathUtil.clamp(this.readMode(itemStack) + direction, 0, this.wrenchModes.size() - 1));
        //player.method_490("Wrench Mode changed : " + this.getWrenchMode(itemStack).getTranslatedName());
        HotbarTooltipHelper.setTooltip("Wrench Mode: " + this.getWrenchMode(itemStack).getTranslatedName(), 40);
        if (player.world.isRemote) {
            for (int i = 0; i < player.inventory.main.length; i++) {
                if (player.inventory.main[i] == itemStack) {
                    PacketHelper.send(new WrenchModeC2SPacket(readMode(itemStack), i));
                }
            }
        }
    }

    public WrenchMode getWrenchMode(ItemStack stack) {
        return this.wrenchModes.get(this.readMode(stack));
    }

    public void setWrenchMode(ItemStack stack, int mode) {
        this.writeMode(stack, mode);
    }

    public void addWrenchMode(WrenchMode wrenchMode) {
        if (wrenchMode == null) {
            UniWrench.LOGGER.error("Tried to add a null wrench mode to wrench {}", this.toString());
            return;
        }

        UniWrench.LOGGER.info("Adding Wrench Mode {} to {}", wrenchMode.name, this.toString());

        if (this.wrenchModes.contains(wrenchMode)) {
            UniWrench.LOGGER.error("Tried to add a duplicate Wrench mode {}", wrenchMode.name);
            return;
        }
        
        this.wrenchModes.add(wrenchMode);
    }

    // Wrench Actions
    @Override
    public boolean useOnBlock(ItemStack stack, PlayerEntity player, World world, int x, int y, int z, int side) {
        if (getDelay(stack) > 0) {
            return false;
        }
        
        Block block = world.getBlockState(x, y, z).getBlock();
        WrenchMode wrenchMode = this.getWrenchMode(stack);

        // First see if the block has the Wrenchable interface
        if (block instanceof Wrenchable wrenchable) {
            // If its Wrenchable, try to wrench it
            boolean wrenched = wrenchable.wrenchRightClick(stack, player, player.isSneaking(), world, x, y, z, side, wrenchMode);

            // If this returns true, ignore all further actions, if not, continue
            if (wrenched) {
                setDelay(stack, usageDelay);
                return true;
            }

            // Check if any Right Click actions exist for this block
        } else if (WrenchableBlockRegistry.doRightClickActionsExist(block)) {
            // If they exist, loop thru them
            for (WrenchFunction action : WrenchableBlockRegistry.getRightClickActions(block)) {
                // If any of them returns true, ignore all futher actions
                if (action.apply(stack, player, player.isSneaking(), world, x, y, z, side, wrenchMode)) {
                    setDelay(stack, usageDelay);
                    return true;
                }
            }
        }

        // If no actions existed, or they all returned false, trigger the wrench action
        if (wrenchRightClick(stack, player, player.isSneaking(), world, x, y, z, side, wrenchMode)) {
            setDelay(stack, usageDelay);
            return true;
        }

        // If no previus actions returned true, try the wrench mode action
        if (wrenchMode.wrenchRightClick(stack, player, player.isSneaking(), world, x, y, z, side, wrenchMode)) {
            setDelay(stack, usageDelay);
            return true;
        }
        
        return false;
    }

    @Override
    public boolean preMine(ItemStack stack, BlockState state, int x, int y, int z, int side, PlayerEntity player) {
        if (getDelay(stack) > 0) {
            return false;
        }
        
        Block block = state.getBlock();
        WrenchMode wrenchMode = this.getWrenchMode(stack);

        // First see if the block has the Wrenchable interface
        if (block instanceof Wrenchable wrenchable) {
            // If its Wrenchable, try to wrench it
            boolean wrenched = wrenchable.wrenchLeftClick(stack, player, player.isSneaking(), player.world, x, y, z, side, wrenchMode);

            // If this returns true, ignore all further actions, if not, continue
            if (wrenched) {
                setDelay(stack, usageDelay);
                return false;
            }

            // Check if any Left Click actions exist for this block
        } else if (WrenchableBlockRegistry.doRightClickActionsExist(state.getBlock())) {
            // If they exist, loop thru them
            for (WrenchFunction action : WrenchableBlockRegistry.getLeftClickActions(block)) {
                // If any of them returns true, ignore all futher actions
                if (action.apply(stack, player, player.isSneaking(), player.world, x, y, z, side, wrenchMode)) {
                    setDelay(stack, usageDelay);
                    return false;
                }
            }

        }

        // If no actions existed, or they all returned false, trigger the wrench action
        if (wrenchLeftClick(stack, player, player.isSneaking(), player.world, x, y, z, side, wrenchMode)) {
            setDelay(stack, usageDelay);
            return false;
        }

        // If no previus actions returned true, try the wrench mode action
        if(wrenchMode.wrenchLeftClick(stack, player, player.isSneaking(), player.world, x, y, z, side, wrenchMode)) {
            setDelay(stack, usageDelay);
            return false;
        }
        
        return true;
    }

    // API Methods
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
    public boolean wrenchRightClick(ItemStack stack, PlayerEntity player, boolean isSneaking, World world, int x, int y, int z, int side, WrenchMode wrenchMode) {
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
    public boolean wrenchLeftClick(ItemStack stack, PlayerEntity player, boolean isSneaking, World world, int x, int y, int z, int side, WrenchMode wrenchMode) {
        return false;
    }

    // Tooltip
    public String[] getTooltip(ItemStack stack, String originalTooltip) {
        if (this.wrenchModes == null || this.wrenchModes.isEmpty()) {
            return new String[]{
                    originalTooltip
            };
        }
        return new String[]{
                originalTooltip,
                "Mode : " + this.getWrenchMode(stack).getTranslatedName()
        };
    }

    // NBT
    public int readMode(ItemStack itemStack) {
        NbtCompound nbt = ((StationItemNbt) itemStack).getStationNbt();
        if (!nbt.contains("wrench_mode")) {
            this.writeMode(itemStack, 0);
        }
        return MathUtil.clamp(nbt.getInt("wrench_mode"), 0, this.wrenchModes.size() - 1);
    }

    public void writeMode(ItemStack itemStack, int mode) {
        NbtCompound nbt = ((StationItemNbt) itemStack).getStationNbt();
        nbt.putInt("wrench_mode", MathUtil.clamp(mode, 0, this.wrenchModes.size() - 1));
    }

    // Usage Delay
    public WrenchBase setUsageDelay(int usageDelay) {
        this.usageDelay = usageDelay;
        return this;
    }
    
    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        super.inventoryTick(stack, world, entity, slot, selected);
        
        int delay = getDelay(stack); 
        if (delay > 0) {
            setDelay(stack, delay - 1);
        }
    }

    public int getDelay(ItemStack itemStack) {
        return itemStack.getStationNbt().getInt("delay");
    }

    public void setDelay(ItemStack itemStack, int delay) {
        itemStack.getStationNbt().putInt("delay", delay);
    }
}
