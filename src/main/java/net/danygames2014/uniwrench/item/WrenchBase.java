package net.danygames2014.uniwrench.item;

import net.danygames2014.uniwrench.api.WrenchMode;
import net.danygames2014.uniwrench.api.Wrenchable;
import net.danygames2014.uniwrench.util.MathUtil;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.block.BlockState;
import net.modificationstation.stationapi.api.client.item.CustomTooltipProvider;
import net.modificationstation.stationapi.api.item.StationItemNbt;
import net.modificationstation.stationapi.api.template.item.TemplateItem;
import net.modificationstation.stationapi.api.util.Identifier;

import java.util.ArrayList;

public class WrenchBase extends TemplateItem implements CustomTooltipProvider {

    protected ArrayList<WrenchMode> wrenchModes;

    public WrenchBase(Identifier identifier) {
        super(identifier);
        this.setMaxCount(1);
        wrenchModes = new ArrayList<>();
    }

    // Wrench Modes
    public void cycleWrenchMode(ItemStack itemStack, int direction){
        setWrenchMode(itemStack, MathUtil.clamp(readMode(itemStack)+direction, 0, ((WrenchBase)itemStack.getItem()).wrenchModes.size()-1));
    }

    public void cycleWrenchMode(ItemStack itemStack, int direction, PlayerEntity player){
        cycleWrenchMode(itemStack, direction);
        player.method_490("Wrench Mode changed : " + getWrenchMode(itemStack).getTranslatedName());
    }

    public WrenchMode getWrenchMode(ItemStack stack){
        return ((WrenchBase)stack.getItem()).wrenchModes.get(readMode(stack));
    }

    public void setWrenchMode(ItemStack stack, int mode){
        writeMode(stack, mode);
    }

    public void addWrenchMode(WrenchMode wrenchMode){
        if(!wrenchModes.contains(wrenchMode)){
            wrenchModes.add(wrenchMode);
        }
    }

    // Wrench Actions
    @Override
    public boolean useOnBlock(ItemStack stack, PlayerEntity player, World world, int x, int y, int z, int side) {
        if(world.getBlockState(x,y,z).getBlock() instanceof Wrenchable){
            ((Wrenchable) world.getBlockState(x,y,z).getBlock()).wrenchRightClick(stack, player, player.method_1373(), world, x, y, z, side, this.getWrenchMode(stack));
            return true;
        }
        return false;
    }

    @Override
    public boolean preMine(ItemStack stack, BlockState blockState, int x, int y, int z, int side, PlayerEntity player) {
        if(player.world.getBlockState(x,y,z).getBlock() instanceof Wrenchable){
            ((Wrenchable) player.world.getBlockState(x,y,z).getBlock()).wrenchLeftClick(stack, player, player.method_1373(), player.world, x, y, z, side, this.getWrenchMode(stack));
        }
        return false;
    }

    // Tooltip
    @Override
    public String[] getTooltip(ItemStack stack, String originalTooltip) {
        return new String[]{
          originalTooltip,
          "Mode : " + getWrenchMode(stack).getTranslatedName()
        };
    }

    // NBT
    public int readMode(ItemStack itemStack) {
        NbtCompound nbt = ((StationItemNbt) itemStack).getStationNbt();
        return MathUtil.clamp(nbt.getInt("wrench_mode"), 0, ((WrenchBase)itemStack.getItem()).wrenchModes.size()-1);
    }

    public void writeMode(ItemStack itemStack, int mode) {
        NbtCompound nbt = ((StationItemNbt) itemStack).getStationNbt();
        nbt.putInt("wrench_mode", mode);
    }
}
