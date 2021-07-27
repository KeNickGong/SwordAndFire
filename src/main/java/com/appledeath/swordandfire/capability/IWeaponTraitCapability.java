package com.appledeath.swordandfire.capability;

import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.common.util.INBTSerializable;

public interface IWeaponTraitCapability extends INBTSerializable<CompoundNBT>  {
    boolean isTwoHanded();
    void setTwoHanded(boolean isTwoHanded);

    boolean isOffhandEmpty();
    void setIsOffhandEmpty(boolean isOffhandEmpty);
}
