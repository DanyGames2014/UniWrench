package net.danygames2014.uwtest.item;

import net.danygames2014.uniwrench.api.WrenchMode;
import net.danygames2014.uniwrench.item.WrenchBase;
import net.danygames2014.uwtest.event.TestListener;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.util.Identifier;
import org.checkerframework.framework.qual.IgnoreInWholeProgramInference;

public class TestWrench extends WrenchBase {
    public TestWrench(Identifier identifier) {
        super(identifier);
        this.addWrenchMode(WrenchMode.MODE_WRENCH);
        this.addWrenchMode(WrenchMode.MODE_ROTATE);
        this.addWrenchMode(TestListener.testMode1);
        this.addWrenchMode(TestListener.testMode2);
        this.addWrenchMode(TestListener.testMode3);
        this.addWrenchMode(TestListener.testMode4);
        this.addWrenchMode(TestListener.testMode5);
        this.usageDelay = 10;
    }

    @Override
    public boolean wrenchLeftClick(ItemStack stack, PlayerEntity player, boolean isSneaking, World world, int x, int y, int z, int side, WrenchMode wrenchMode) {
        if (this.isModeSwitchLocked(stack)) {
            this.unlockModeSwitch(stack);
            player.sendMessage("Unlocked");
        } else {
            this.lockModeSwitch(stack);
            player.sendMessage("Locked");
        }
        
        System.out.println("Wrench Wand Left Click | Block : " + world.getBlockState(x,y,z).getBlock().getTranslatedName() + " | WrenchMode : " + wrenchMode.identifier);
        return !isSneaking;
    }

    @Override
    public boolean wrenchRightClick(ItemStack stack, PlayerEntity player, boolean isSneaking, World world, int x, int y, int z, int side, WrenchMode wrenchMode) {
        System.out.println("Wrench Wand Right Click | Block : " + world.getBlockState(x,y,z).getBlock().getTranslatedName() + " | WrenchMode : " + wrenchMode.identifier);
        return !isSneaking;
    }
}
