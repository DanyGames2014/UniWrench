package net.danygames2014.uniwrench.mixin;

import net.minecraft.client.gui.hud.InGameHud;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(InGameHud.class)
public interface InGameHudAccessor {
    @Accessor("overlayRemaining")
    void setOverlayRemaining(int value);

    @Accessor("overlayMessage")
    void setOverlayMessage(String value);
}
