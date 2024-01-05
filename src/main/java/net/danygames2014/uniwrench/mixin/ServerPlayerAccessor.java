package net.danygames2014.uniwrench.mixin;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(ServerPlayNetworkHandler.class)
public interface ServerPlayerAccessor {
    @Accessor("field_920")
    ServerPlayerEntity getServerPlayer();
}
