package com.appledeath.swordandfire.item;

import net.minecraft.entity.Entity;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class SaFLargeAxeItem extends SaFWeaponGenericItem implements ISaFTwoHanded {
    public SaFLargeAxeItem(IItemTier tier, int attackDamageIn, float attackSpeedIn, float baseWeight, String name, ItemGroup category) {
        super(tier, attackDamageIn, attackSpeedIn, baseWeight, name, category);

        this.setRegistryName(name);
    }

    @Override
    public boolean getTwoHanded() {
        return true;
    }
}
