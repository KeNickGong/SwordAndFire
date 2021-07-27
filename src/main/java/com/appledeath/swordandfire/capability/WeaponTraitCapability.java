package com.appledeath.swordandfire.capability;

import com.appledeath.swordandfire.Utils;
import net.minecraft.nbt.CompoundNBT;

public class WeaponTraitCapability implements IWeaponTraitCapability {
    private static final String TWO_HANDED_NBT_KEY = Utils.MOD_ID + "_two_handed";
    private static final String OFFHAND_EMPTY_NBT_KEY = Utils.MOD_ID + "_offhand_empty";

    private boolean isTwoHanded = false;
    private boolean isOffhandEmpty = true;

    public WeaponTraitCapability() {
    }

    public WeaponTraitCapability(boolean isTwoHanded) {
        this.isTwoHanded = isTwoHanded;
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
    public CompoundNBT serializeNBT() {
        final CompoundNBT nbt = new CompoundNBT();
        nbt.putBoolean(TWO_HANDED_NBT_KEY, isTwoHanded);
        nbt.putBoolean(OFFHAND_EMPTY_NBT_KEY, isOffhandEmpty);
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundNBT nbt) {
        this.setTwoHanded(nbt.getBoolean(TWO_HANDED_NBT_KEY));
        this.setIsOffhandEmpty(nbt.getBoolean(OFFHAND_EMPTY_NBT_KEY));
    }
}
