package com.appledeath.swordandfire.gui.forgefurnace;

import com.appledeath.swordandfire.ItemRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.List;

@OnlyIn(Dist.CLIENT)
public enum ForgeFurnaceProductCategories {
    MATERIALS(new ItemStack(ItemRegistry.STEEL_INGOT), ItemRegistry.MATERIALS),
    SWORDS(new ItemStack(ItemRegistry.VIKING_SWORD), ItemRegistry.SWORDS),
    AXES(new ItemStack(ItemRegistry.VIKING_AXE), ItemRegistry.AXES),
    BLUNTS(new ItemStack(ItemRegistry.NAILED_MACE), ItemRegistry.BLUNTS),
    POLES(new ItemStack(ItemRegistry.GAULISH_LANCE), ItemRegistry.POLES);


    private final ItemStack icon;
    private final List<ItemStack> items;

    private ForgeFurnaceProductCategories(ItemStack icon, List<ItemStack> items) {
        this.icon = icon;
        this.items = items;
    }

    public ItemStack getIcon() {
        return icon;
    }

    public List<ItemStack> getItemStackList() { return items; }
}
