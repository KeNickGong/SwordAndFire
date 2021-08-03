package com.appledeath.swordandfire.item;

import com.appledeath.swordandfire.item.weapontrait.ISaFShieldPenetrable;
import com.appledeath.swordandfire.item.weapontrait.ISaFTwoHanded;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemGroup;

public class SaFLargeAxeItem extends SaFAxeItem implements ISaFTwoHanded, ISaFShieldPenetrable {

    private final int shieldPenetrableLevel;

    public SaFLargeAxeItem(IItemTier tier, int attackDamageIn, float attackSpeedIn, float baseWeight, float baseFlex, float baseRange, String name, int shieldPenetrableLevel, ItemGroup category) {
        super(tier, attackDamageIn, attackSpeedIn, baseWeight, baseFlex, baseRange, name, shieldPenetrableLevel, category);
        this.shieldPenetrableLevel = shieldPenetrableLevel;
    }

    @Override
    public boolean getTwoHanded() {
        return true;
    }

    @Override
    public boolean getShieldPenetrable() { return false; }

    @Override
    public int getShieldPenetrableLevel() {
        return shieldPenetrableLevel;
    }
}
