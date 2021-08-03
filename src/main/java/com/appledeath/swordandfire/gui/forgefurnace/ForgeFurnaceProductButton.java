package com.appledeath.swordandfire.gui.forgefurnace;

import com.appledeath.swordandfire.Utils;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.widget.ToggleWidget;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TranslationTextComponent;

public class ForgeFurnaceProductButton extends ToggleWidget {
    private final ItemStack stack;
    TranslationTextComponent content = new TranslationTextComponent("gui." + Utils.MOD_ID + ".first");

    public ForgeFurnaceProductButton(ItemStack icon) {
        super(0, 0, 109, 19, false);

        this.stack = icon;
        this.initTextureValues(0, 26, 109, 19, ForgeFurnaceContainerGui.FORGE_FURNACE_WIDGET);
    }

    public void setStateTriggered(boolean triggered) {
        this.stateTriggered = triggered;
    }

    public boolean isStateTriggered() {
        return this.stateTriggered;
    }

    @Override
    public void renderWidget(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        Minecraft minecraft = Minecraft.getInstance();
        minecraft.getTextureManager().bindTexture(ForgeFurnaceContainerGui.FORGE_FURNACE_WIDGET);
        RenderSystem.disableDepthTest();
        int i = this.xTexStart;
        int j = this.yTexStart;
        if (this.stateTriggered) {
            i += this.xDiffTex;
            j += this.yDiffTex;
        }

        if (this.isHovered() && !this.stateTriggered) {
            i += this.xDiffTex;
        }

        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.blit(matrixStack, this.x, this.y, i, j, this.width, this.height);
        RenderSystem.enableDepthTest();
        this.renderIcon(minecraft.getItemRenderer());
    }

    private void renderIcon(ItemRenderer render) {
        render.renderItemAndEffectIntoGuiWithoutEntity(this.stack, this.x + 1, this.y + 1);
    }

    public ItemStack getIcon() {
        return stack;
    }
}
