package com.appledeath.swordandfire.item;

import com.appledeath.swordandfire.item.weapontrait.ISaFTwoHanded;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemGroup;

public class SaFPoleItem extends SaFWeaponGenericItem {
    public SaFPoleItem(IItemTier tier, int attackDamageIn, float attackSpeedIn, float baseWeight, float baseFlex, float baseRange, String name, ItemGroup category) {
        super(tier, attackDamageIn, attackSpeedIn, baseWeight, baseFlex, baseRange, name, category);
    }
}
