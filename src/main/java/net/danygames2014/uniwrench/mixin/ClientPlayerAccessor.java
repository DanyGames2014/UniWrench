package net.danygames2014.uniwrench.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.client.network.ClientNetworkHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(ClientNetworkHandler.class)
public interface ClientPlayerAccessor {
    @Accessor("minecraft")
    Minecraft getMinecraft();
}
