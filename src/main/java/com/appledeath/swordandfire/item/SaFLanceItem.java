package com.appledeath.swordandfire.item;

import com.appledeath.swordandfire.item.weapontrait.ISaFCavalryBonus;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemGroup;

public class SaFLanceItem extends SaFPoleItem implements ISaFCavalryBonus {

    private final int cavarlyBonusLevel;

    public SaFLanceItem(IItemTier tier, int attackDamageIn, float attackSpeedIn, float baseWeight, float baseFlex, float baseRange, String name, int cavarlyBonusLevel, ItemGroup category) {
        super(tier, attackDamageIn, attackSpeedIn, baseWeight, baseFlex, baseRange, name, category);

        this.cavarlyBonusLevel = cavarlyBonusLevel;
    }

    @Override
    public boolean getCavalryBonus() {
        return true;
    }

    @Override
    public int getCavalryBonusLevel() {
        return cavarlyBonusLevel;
    }
}
