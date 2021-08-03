package com.appledeath.swordandfire.gui.forgefurnace;

import com.appledeath.swordandfire.Utils;
import com.appledeath.swordandfire.inventory.ForgeFurnaceContainer;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.recipebook.RecipeBookGui;
import net.minecraft.client.gui.widget.ToggleWidget;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import java.util.List;

public class ForgeFurnaceProductTabToggleWidget extends ToggleWidget {
    private final ForgeFurnaceProductCategories category;

    public ForgeFurnaceProductTabToggleWidget(ForgeFurnaceProductCategories category) {
        super(0, 0, 35, 26, false);

        this.category = category;
        this.initTextureValues(0, 0, 35, 0, ForgeFurnaceContainerGui.FORGE_FURNACE_WIDGET);
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
        }

        if (this.isHovered()) {
            j += this.yDiffTex;
        }

        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);

        this.blit(matrixStack, this.x, this.y, i, j, this.width, this.height);
        RenderSystem.enableDepthTest();
        this.renderIcon(minecraft.getItemRenderer());
    }

    public ForgeFurnaceProductCategories getCategory() {
        return category;
    }

    private void renderIcon(ItemRenderer render) {
        ItemStack icon = this.category.getIcon();
        int i = this.stateTriggered ? -2 : 0;

        render.renderItemAndEffectIntoGuiWithoutEntity(icon, this.x + 9 + i, this.y + 5);
    }
}
