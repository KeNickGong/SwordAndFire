package com.appledeath.swordandfire.gui.forgefurnace;

import com.appledeath.swordandfire.ItemRegistry;
import com.appledeath.swordandfire.Utils;
import com.appledeath.swordandfire.inventory.ForgeFurnaceContainer;
import com.google.common.collect.Lists;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.recipebook.RecipeTabToggleWidget;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.util.RecipeBookCategories;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.lwjgl.opengl.GL11;

import java.util.List;

@OnlyIn(Dist.CLIENT)
public class ForgeFurnaceContainerGui extends ContainerScreen<ForgeFurnaceContainer> {

    protected static final ResourceLocation FORGE_FURNACE_CONTAINER = new ResourceLocation(Utils.MOD_ID, "textures/gui/forge_furnace_gui.png");
    protected static final ResourceLocation FORGE_FURNACE_WIDGET = new ResourceLocation(Utils.MOD_ID, "textures/gui/forge_furnace_widget.png");

    private final int textureWidth = 512;
    private final int textureHeight = 256;

    private int currentPage;
    private ForgeFurnaceProductButton currentProduct;
    private List<ForgeFurnaceProductButton> displayButtons = Lists.newArrayList();

    private final List<ForgeFurnaceProductTabToggleWidget> productTabs = Lists.newArrayList();
    private ForgeFurnaceProductTabToggleWidget currentTab;

    public ForgeFurnaceContainerGui(ForgeFurnaceContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
        super(screenContainer, inv, titleIn);
        this.xSize = 299;
        this.ySize = 166;

        for (ForgeFurnaceProductCategories category : ForgeFurnaceProductCategories.values()) {
            productTabs.add(new ForgeFurnaceProductTabToggleWidget(category));
        }

        this.currentTab = this.productTabs.get(0);

        currentPage = 0;

        this.currentTab.setStateTriggered(true);

        this.populateButtons(currentPage, currentTab);
    }

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        renderBackground(matrixStack);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        renderHoveredTooltip(matrixStack, mouseX, mouseY);

        for(ForgeFurnaceProductTabToggleWidget forgeFurnaceProductTabToggleWidget : this.productTabs) {
            forgeFurnaceProductTabToggleWidget.render(matrixStack, mouseX, mouseY, partialTicks);
        }
        this.updateTabs();

        for (ForgeFurnaceProductButton forgeFurnaceProductButton : this.displayButtons) {
            forgeFurnaceProductButton.render(matrixStack, mouseX, mouseY, partialTicks);
        }
        this.updateProducts(matrixStack);
    }

    private void updateProducts(MatrixStack matrixStack) {
        int i = (this.width + 3) / 2 + 30;
        int j = this.height / 2 - 74;
        int m = 0, n = 0;

        for (ForgeFurnaceProductButton forgeFurnaceProductButton : displayButtons) {
            forgeFurnaceProductButton.setPosition(i, j + 19 * m++);
            forgeFurnaceProductButton.visible = true;
            this.font.drawString(matrixStack, new TranslationTextComponent(forgeFurnaceProductButton.getIcon().getTranslationKey()).getString(), i + 22, j + 3 + 19 * n++, currentProduct == forgeFurnaceProductButton ? 0xffffff : 0x000000);
        }
        //Weird bug that won't render last item
    }

    public void populateButtons(int currentPage, ForgeFurnaceProductTabToggleWidget currentTab) {
        displayButtons.clear();
        List<ItemStack> itemStacks = currentTab.getCategory().getItemStackList();
        List<ItemStack> displayItems = (itemStacks.size() > currentPage * 6 + 6) ? itemStacks.subList(currentPage, currentPage + 6) : itemStacks.subList(currentPage, itemStacks.size());
        for (ItemStack stack: displayItems) {
            ForgeFurnaceProductButton btn = new ForgeFurnaceProductButton(stack);
            displayButtons.add(btn);
            btn.visible = false;
        }
    }

    private void updateTabs() {
        int i = (this.width - 147) / 2 + 220;
        int j = (this.height - 166) / 2 + 8;
        int l = 0;

        for(ForgeFurnaceProductTabToggleWidget forgeFurnaceProductTabToggleWidget : this.productTabs) {
            forgeFurnaceProductTabToggleWidget.visible = true;
            forgeFurnaceProductTabToggleWidget.setPosition(i, j + 27 * l++);
        }

    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        super.mouseClicked(mouseX, mouseY, button);

        for (ForgeFurnaceProductTabToggleWidget forgeFurnaceProductTabToggleWidget : this.productTabs) {
            if (forgeFurnaceProductTabToggleWidget.mouseClicked(mouseX, mouseY, button)) {
                if (this.currentTab != forgeFurnaceProductTabToggleWidget) {

                    this.currentTab.setStateTriggered(false);
                    this.currentTab = forgeFurnaceProductTabToggleWidget;
                    this.currentTab.setStateTriggered(true);
                    this.currentPage = 0;

                    populateButtons(this.currentPage, this.currentTab);
                    //this.updateCollections(true);
                }

                return true;
            }
        }

        for (ForgeFurnaceProductButton forgeFurnaceProductButton : this.displayButtons) {
            if (forgeFurnaceProductButton.mouseClicked(mouseX, mouseY, button)) {
                if (this.currentProduct == null) {
                    this.currentProduct = forgeFurnaceProductButton;
                    this.currentProduct.setStateTriggered(true);
                } else if (this.currentProduct != forgeFurnaceProductButton) {
                    this.currentProduct.setStateTriggered(false);
                    this.currentProduct = forgeFurnaceProductButton;
                    this.currentProduct.setStateTriggered(true);
                } else {
                    this.currentProduct.setStateTriggered(false);
                    this.currentProduct = null;
                }

                return true;
            }
        }

        return false;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(MatrixStack matrixStack, int x, int y) {

    }

    @Override
    protected void drawGuiContainerBackgroundLayer(MatrixStack matrixStack, float partialTicks, int x, int y) {
        this.renderBackground(matrixStack);
        this.minecraft.getTextureManager().bindTexture(FORGE_FURNACE_CONTAINER);
        int i = (this.width - this.xSize) / 2;
        int j = (this.height - this.ySize) / 2;
        blit(matrixStack, i, j, 0, 0, xSize, ySize, this.textureWidth, this.textureHeight);
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }
}
