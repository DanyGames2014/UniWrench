package net.danygames2014.uniwrench.util;

import net.danygames2014.uniwrench.mixin.InGameHudAccessor;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.Minecraft;

@Environment(EnvType.CLIENT)
public class HotbarTooltipHelper {
    private static Minecraft client;

    public static void setTooltip(String message, int remaining){
        if(client == null){
            client = (Minecraft) FabricLoader.getInstance().getGameInstance();
        }

        ((InGameHudAccessor)client.inGameHud).setOverlayMessage(message);
        ((InGameHudAccessor)client.inGameHud).setOverlayRemaining(remaining);

    }
}
