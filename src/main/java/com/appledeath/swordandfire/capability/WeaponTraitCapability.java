package com.appledeath.swordandfire.capability;

import com.appledeath.swordandfire.Utils;
import net.minecraft.nbt.CompoundNBT;

public class WeaponTraitCapability implements IWeaponTraitCapability {
    private static final String TWO_HANDED_NBT_KEY = Utils.MOD_ID + "_two_handed";
    private static final String OFFHAND_EMPTY_NBT_KEY = Utils.MOD_ID + "_offhand_empty";
    private static final String ARMOR_PENETRABLE_NBT_KEY = Utils.MOD_ID + "_armor_penetrable";
    private static final String SHIELD_PENETRABLE_NBT_KEY = Utils.MOD_ID + "_shield_penetrable";
    private static final String CAVALRY_BONUS_NBT_KEY = Utils.MOD_ID + "_cavalry_bonus";
    private static final String ANTI_HORSE_NBT_KEY = Utils.MOD_ID + "_anti_horse";

    private boolean isTwoHanded = false;
    private boolean isOffhandEmpty = true;
    private boolean isArmorPenetrable = false;
    private boolean isShieldPenetrable = false;
    private boolean isCavalryBonus = false;
    private boolean isAntiHorse = false;

    public WeaponTraitCapability() {
    }

    public WeaponTraitCapability(boolean[] traitProps) {
        this.isTwoHanded = traitProps[0];
        this.isArmorPenetrable = traitProps[1];
        this.isShieldPenetrable = traitProps[2];
        this.isCavalryBonus = traitProps[3];
        this.isAntiHorse = traitProps[4];
    }


    @Override
    public boolean isTwoHanded() {
        return isTwoHanded;
    }

    @Override
    public void setTwoHanded(boolean isTwoHanded) { this.isTwoHanded = isTwoHanded; }

    @Override
    public boolean isOffhandEmpty() { return isOffhandEmpty; }

    @Override
    public void setIsOffhandEmpty(boolean isOffhandEmpty) { this.isOffhandEmpty = isOffhandEmpty; }

    @Override
    public boolean isArmorPenetrable() { return isArmorPenetrable; }

    @Override
    public void setIsArmorPenetrable(boolean isArmorPenetrable) { this.isArmorPenetrable = isArmorPenetrable; }

    @Override
    public boolean isShieldPenetrable() { return isShieldPenetrable; }

    @Override
    public void setIsShieldPenetrable(boolean isShieldPenetrable) { this.isShieldPenetrable = isShieldPenetrable; }

    @Override
    public boolean isCavalryBonus() { return isCavalryBonus; }

    @Override
    public void setIsCavarlyBonus(boolean isCavarlyBonus) { this.isCavalryBonus = isCavarlyBonus; }

    @Override
    public boolean isAntiHorse() { return isAntiHorse; }

    @Override
    public void setIsAntiHorse(boolean isAntiHorse) { this.isAntiHorse = isAntiHorse; }

    @Override
    public CompoundNBT serializeNBT() {
        final CompoundNBT nbt = new CompoundNBT();
        nbt.putBoolean(TWO_HANDED_NBT_KEY, isTwoHanded);
        nbt.putBoolean(OFFHAND_EMPTY_NBT_KEY, isOffhandEmpty);
        nbt.putBoolean(ARMOR_PENETRABLE_NBT_KEY, isArmorPenetrable);
        nbt.putBoolean(SHIELD_PENETRABLE_NBT_KEY, isShieldPenetrable);
        nbt.putBoolean(CAVALRY_BONUS_NBT_KEY, isCavalryBonus);
        nbt.putBoolean(ANTI_HORSE_NBT_KEY, isAntiHorse);
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundNBT nbt) {
        this.setTwoHanded(nbt.getBoolean(TWO_HANDED_NBT_KEY));
        this.setIsOffhandEmpty(nbt.getBoolean(OFFHAND_EMPTY_NBT_KEY));
        this.setIsArmorPenetrable(nbt.getBoolean(ARMOR_PENETRABLE_NBT_KEY));
        this.setIsShieldPenetrable(nbt.getBoolean(SHIELD_PENETRABLE_NBT_KEY));
        this.setIsCavarlyBonus(nbt.getBoolean(CAVALRY_BONUS_NBT_KEY));
        this.setIsAntiHorse(nbt.getBoolean(ANTI_HORSE_NBT_KEY));
    }
}
