package net.danygames2014.uniwrench.mixin;

import net.danygames2014.uniwrench.item.WrenchBase;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.render.Tessellator;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.texture.TextureManager;
import net.minecraft.item.ItemStack;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemRenderer.class)
public class ItemRendererMixin {
    @Unique
    private void fillRect(Tessellator tessellator, int x, int y, int width, int height, int color, int alpha) {
        tessellator.startQuads();
        tessellator.color(color, alpha);
        tessellator.vertex(x, y, 0.0F);
        tessellator.vertex(x, (y + height), 0.0F);
        tessellator.vertex((x + width), (y + height), 0.0F);
        tessellator.vertex((x + width), y, 0.0F);
        tessellator.draw();
    }

    @Inject(method = "renderGuiItemDecoration", at = @At("HEAD"))
    void render(TextRenderer textRenderer, TextureManager textureManager, ItemStack stack, int x, int y, CallbackInfo ci){
        if(stack != null && stack.getItem() instanceof WrenchBase wrenchBase && wrenchBase.usageDelay > 0){
            int delay = wrenchBase.getDelay(stack);
            if(delay == 0) return;
            int offset = (int) (16 * (1.0 - (double) delay / wrenchBase.usageDelay));

            GL11.glDisable(GL11.GL_LIGHTING);
            GL11.glDisable(GL11.GL_DEPTH_TEST);
            GL11.glDisable(GL11.GL_TEXTURE_2D);
            GL11.glEnable(GL11.GL_BLEND);
            GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

            fillRect(Tessellator.INSTANCE, x, y + offset, 16, 16 - offset, 0xFFFFFF, 90);

            GL11.glEnable(GL11.GL_LIGHTING);
            GL11.glEnable(GL11.GL_DEPTH_TEST);
            GL11.glEnable(GL11.GL_TEXTURE_2D);
            GL11.glDisable(GL11.GL_BLEND);

        }
    }
}
