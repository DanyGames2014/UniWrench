package net.danygames2014.uniwrench.mixin;

import net.danygames2014.uniwrench.init.ItemListener;
import net.danygames2014.uniwrench.item.WrenchBase;
import net.danygames2014.uniwrench.util.MathUtil;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerInventory.class)
public abstract class PlayerInventoryMixin {
    @Shadow
    public abstract ItemStack getSelectedItem();

    @Shadow
    public PlayerEntity player;

    @Inject(method = "method_692", at = @At("HEAD"), cancellable = true)
    public void wrenchScroll(int scrollDirection, CallbackInfo ci) {
        if (this.getSelectedItem() != null && this.getSelectedItem().getItem() instanceof WrenchBase wrench && player.method_1373()) {
            wrench.cycleWrenchMode(this.getSelectedItem(), MathUtil.clamp(scrollDirection, -1, 1), this.player);

            ci.cancel();
        }
    }
}
