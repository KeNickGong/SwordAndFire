package com.appledeath.swordandfire.capability;

import com.appledeath.swordandfire.Utils;
import net.minecraft.nbt.CompoundNBT;

public class WeaponCapability implements IWeaponCapability {
    private static final String MAX_DAMAGE_NBT_KEY = Utils.MOD_ID + "_max_damage";
    private static final String WEIGHT_NBT_KEY = Utils.MOD_ID + "_weight";
    private static final String FLEXIBILITY_NBT_KEY = Utils.MOD_ID + "_flexibility";
    private static final String RANGE_NBT_KEY = Utils.MOD_ID + "_range";


    private float max_damage;
    private float weight;
    private float flexibility;
    private float range;

    public WeaponCapability() {
        this(0, 0, 0, 0);
    }

    public WeaponCapability(float max_damage, float weight, float flexibility, float range) {
        this.max_damage = max_damage;
        this.weight = weight;
        this.flexibility = flexibility;
        this.range = range;
    }

    @Override
    public float getMaxDamage() {
        return max_damage;
    }

    @Override
    public void setMaxDamage(float value) {
        max_damage = value;
    }

    @Override
    public float getWeight() {
        return weight;
    }

    @Override
    public void setWeight(float value) {
        weight = value;
    }

    @Override
    public float getFlexibility() {
        return flexibility;
    }

    @Override
    public void setFlexibility(float value) {
        flexibility = value;
    }

    @Override
    public float getRange() {
        return range;
    }

    @Override
    public void setRange(float value) {
        range = value;
    }

    @Override
    public CompoundNBT serializeNBT() {
        final CompoundNBT nbt = new CompoundNBT();
        nbt.putFloat(MAX_DAMAGE_NBT_KEY, max_damage);
        nbt.putFloat(WEIGHT_NBT_KEY, weight);
        nbt.putFloat(FLEXIBILITY_NBT_KEY, flexibility);
        nbt.putFloat(RANGE_NBT_KEY, range);
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundNBT nbt) {
        this.setMaxDamage(nbt.getFloat(MAX_DAMAGE_NBT_KEY));
        this.setWeight(nbt.getFloat(WEIGHT_NBT_KEY));
        this.setFlexibility(nbt.getFloat(FLEXIBILITY_NBT_KEY));
        this.setRange(nbt.getFloat(RANGE_NBT_KEY));
    }
}
