package com.appledeath.swordandfire.item;

import com.appledeath.swordandfire.item.weapontrait.ISaFAntiHorse;
import com.appledeath.swordandfire.item.weapontrait.ISaFTwoHanded;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemGroup;

public class SaFLongSpearItem extends SaFPoleItem implements ISaFTwoHanded, ISaFAntiHorse {

    private final int antiHorseLevel;

    public SaFLongSpearItem(IItemTier tier, int attackDamageIn, float attackSpeedIn, float baseWeight, float baseFlex, float baseRange, String name, int antiHorseLevel, ItemGroup category) {
        super(tier, attackDamageIn, attackSpeedIn, baseWeight, baseFlex, baseRange, name, category);

        this.antiHorseLevel = antiHorseLevel;
    }

    @Override
    public boolean getAntiHorse() { return true; }

    @Override
    public int getAntiHorseLevel() { return antiHorseLevel; }

    @Override
    public boolean getTwoHanded() { return true; }
}
