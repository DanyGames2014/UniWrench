package net.danygames2014.uniwrench.mixin;

import net.danygames2014.uniwrench.item.WrenchBase;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerInteractionManager;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.block.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ServerPlayerInteractionManager.class)
public class ServerInteractionManagerMixin {

    @Inject(method = "interactBlock", at = @At(value = "HEAD"), cancellable = true)
    public void flipActions(PlayerEntity player, World world, ItemStack stack, int x, int y, int z, int side, CallbackInfoReturnable<Boolean> cir) {
        BlockState state = world.getBlockState(x, y, z);

        if (state.isAir() || stack == null) {
            return;
        }

        if (stack.getItem() instanceof WrenchBase wrench) {
            if (!wrench.useOnBlock(stack, player, world, x, y, z, side)) {
                state.getBlock().onUse(world, x, y, z, player);
            }
            cir.cancel();
        }
    }

}
