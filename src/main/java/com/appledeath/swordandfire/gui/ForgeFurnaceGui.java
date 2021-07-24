package com.appledeath.swordandfire.gui;

import com.appledeath.swordandfire.Utils;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import org.lwjgl.opengl.GL11;

public class ForgeFurnaceGui extends Screen {

    ResourceLocation FORGE_FURNACE_TEXTURE = new ResourceLocation(Utils.MOD_ID, "textures/gui/gui.png");

    public ForgeFurnaceGui(ITextComponent titleIn) {
        super(titleIn);
    }

    @Override
    protected void init() {

        super.init();
    }

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.minecraft.getTextureManager().bindTexture(FORGE_FURNACE_TEXTURE);
        int textureWidth = 208;
        int textureHeight = 156;
        this.blit(matrixStack, this.width / 2 - 150, 10, 0, 0, 300, 200, textureWidth, textureHeight);
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }
}
