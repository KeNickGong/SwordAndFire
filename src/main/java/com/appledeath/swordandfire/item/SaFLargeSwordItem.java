package com.appledeath.swordandfire.item;

import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemGroup;

public class SaFLargeSwordItem extends SaFWeaponGenericItem implements ISaFTwoHanded {
    public SaFLargeSwordItem(IItemTier tier, int attackDamageIn, float attackSpeedIn, float baseWeight, String name, ItemGroup category) {
        super(tier, attackDamageIn, attackSpeedIn, baseWeight, name, category);

        this.setRegistryName(name);
    }

    @Override
    public boolean getTwoHanded() {
        return true;
    }
}
