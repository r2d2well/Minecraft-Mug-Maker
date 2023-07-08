package net.r2d2well.mugmakermod.Screen;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.r2d2well.mugmakermod.MugMakerMod;

public class MixerScreen extends AbstractContainerScreen<MixerMenu> {
    public MixerScreen(MixerMenu menu, Inventory inventory, Component component){
        super(menu, inventory, component);
    }

    private static final ResourceLocation TEXTURE = new ResourceLocation(MugMakerMod.MOD_ID, "textures/gui/mixer_gui.png");

    @Override
    protected void init(){
        super.init();
    }

    @Override
    protected void renderBg(GuiGraphics stack, float pPartialTick, int pMouseX, int pMouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        stack.blit(TEXTURE, x, y, 0, 0, imageWidth, imageHeight);
    }

    @Override
    public void render(GuiGraphics stack, int mouseX, int mouseY, float delta) {
        renderBackground(stack);
        super.render(stack, mouseX, mouseY, delta);
        renderTooltip(stack, mouseX, mouseY);
    }
}
