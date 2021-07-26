package com.appledeath.swordandfire.capability;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.common.util.INBTSerializable;

public interface IWeaponCapability extends INBTSerializable<CompoundNBT> {
    //float getMaxDamage();
    //void setMaxDamage(float value);

    float getWeight();
    void setWeight(float value);

    float getFlexibility();
    void setFlexibility(float value);

    float getRange();
    void setRange(float value);

    float getMaterialModifier();
    void setMaterialModifier(float value);

    float getSkillModifier();
    void setSkillModifier(float value);
}
