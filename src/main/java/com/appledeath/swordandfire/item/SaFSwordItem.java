package com.appledeath.swordandfire.item;

import net.minecraft.item.*;
import net.minecraftforge.items.IItemHandler;

import javax.annotation.Nonnull;

public class SaFSwordItem extends SwordItem implements IItemHandler {
    public SaFSwordItem(IItemTier tier, int attackDamageIn, float attackSpeedIn, ItemGroup category){
        super(tier, attackDamageIn, attackSpeedIn, new Item.Properties().group(category).maxStackSize(1));
    }

    @Override
    public int getSlots() {
        return 0;
    }

    @Nonnull
    @Override
    public ItemStack getStackInSlot(int slot) {
        return null;
    }

    @Nonnull
    @Override
    public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
        return null;
    }

    @Nonnull
    @Override
    public ItemStack extractItem(int slot, int amount, boolean simulate) {
        return null;
    }

    @Override
    public int getSlotLimit(int slot) {
        return 0;
    }

    @Override
    public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
        return false;
    }
}
