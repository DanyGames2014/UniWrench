package net.danygames2014.uniwrench.item;

import net.danygames2014.uniwrench.api.WrenchMode;
import net.danygames2014.uniwrench.api.Wrenchable;
import net.danygames2014.uniwrench.network.WrenchModePacket;
import net.danygames2014.uniwrench.util.MathUtil;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
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

    @Environment(EnvType.CLIENT)
    public static int updateCounter;
    @Environment(EnvType.CLIENT)
    public static final int updateDelay = 50;
    private final ArrayList<WrenchMode> wrenchModes;

    public WrenchBase(Identifier identifier) {
        super(identifier);
        this.setMaxCount(1);
        wrenchModes = new ArrayList<>();
    }

    // Wrench Modes
    @Environment(EnvType.CLIENT)
    public void cycleWrenchMode(ItemStack itemStack, int direction, PlayerEntity player) {
        this.setWrenchMode(itemStack, MathUtil.clamp(this.readMode(itemStack) + direction, 0, this.wrenchModes.size() - 1));
        player.method_490("Wrench Mode changed : " + this.getWrenchMode(itemStack).getTranslatedName());
        if (player.world.isRemote) {
            PacketHelper.send(new WrenchModePacket(readMode(itemStack)));
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
            System.out.println("WRENCH MODE IS NULL! The game will now crash because fuck you (:");
        }

        // I know this can be null, and if it is i want it to crash because that means i can cry over race conditions again :)))
        //noinspection DataFlowIssue
        System.out.println("Adding Wrench Mode " + wrenchMode.name + " to " + this.getTranslatedName());

        if (!this.wrenchModes.contains(wrenchMode)) {
            this.wrenchModes.add(wrenchMode);
        }
    }

    // Wrench Actions
    @Override
    public boolean useOnBlock(ItemStack stack, PlayerEntity player, World world, int x, int y, int z, int side) {
        if (world.getBlockState(x, y, z).getBlock() instanceof Wrenchable) {
            ((Wrenchable) world.getBlockState(x, y, z).getBlock()).wrenchRightClick(stack, player, player.method_1373(), world, x, y, z, side, this.getWrenchMode(stack));
            return true;
        }
        return false;
    }

    @Override
    public boolean preMine(ItemStack stack, BlockState blockState, int x, int y, int z, int side, PlayerEntity player) {
        if (player.world.getBlockState(x, y, z).getBlock() instanceof Wrenchable) {
            ((Wrenchable) player.world.getBlockState(x, y, z).getBlock()).wrenchLeftClick(stack, player, player.method_1373(), player.world, x, y, z, side, this.getWrenchMode(stack));
        }
        return false;
    }

    @Environment(EnvType.CLIENT)
    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        updateCounter++;
        if(updateCounter >= updateDelay){
            updateCounter = 0;
            if(selected){
                PacketHelper.send(new WrenchModePacket(-7000));
            }
        }
    }

    // Tooltip
    public String[] getTooltip(ItemStack stack, String originalTooltip) {
        if (this.wrenchModes.get(0) == null) {
            return new String[]{
                    "ERROR"
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
}
