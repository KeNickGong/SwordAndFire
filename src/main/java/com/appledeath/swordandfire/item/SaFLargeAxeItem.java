package com.appledeath.swordandfire.item;

import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemGroup;

public class SaFLargeAxeItem extends SaFWeaponGenericItem{
    public SaFLargeAxeItem(IItemTier tier, int attackDamageIn, float attackSpeedIn, float baseWeight, String name, ItemGroup category) {
        super(tier, attackDamageIn, attackSpeedIn, baseWeight, name, category);

        this.setRegistryName(name);
    }
}
