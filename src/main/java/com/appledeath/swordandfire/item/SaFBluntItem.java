package com.appledeath.swordandfire.item;

import com.appledeath.swordandfire.item.weapontrait.ISaFArmorPenetrable;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemGroup;

public class SaFBluntItem extends SaFWeaponGenericItem implements ISaFArmorPenetrable {

    private final int armorPenetrableLevel;

    public SaFBluntItem(IItemTier tier, int attackDamageIn, float attackSpeedIn, float baseWeight, float baseFlex, float baseRange, String name, int armorPenetrableLevel, ItemGroup category){
        super(tier, attackDamageIn, attackSpeedIn, baseWeight, baseFlex, baseRange, name, category);

        this.armorPenetrableLevel = armorPenetrableLevel;
    }

    @Override
    public boolean getArmorPenetrable() {
        return true;
    }

    @Override
    public int getArmorPenetrableLevel() {
        return armorPenetrableLevel;
    }
}
