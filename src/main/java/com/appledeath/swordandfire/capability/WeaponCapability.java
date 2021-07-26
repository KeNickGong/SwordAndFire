package com.appledeath.swordandfire.capability;

import com.appledeath.swordandfire.Utils;
import net.minecraft.nbt.CompoundNBT;

import java.util.Random;

public class WeaponCapability implements IWeaponCapability {
    //private static final String MAX_DAMAGE_NBT_KEY = Utils.MOD_ID + "_max_damage";
    private static final String WEIGHT_NBT_KEY = Utils.MOD_ID + "_weight";
    private static final String FLEXIBILITY_NBT_KEY = Utils.MOD_ID + "_flexibility";
    private static final String RANGE_NBT_KEY = Utils.MOD_ID + "_range";

    private static final String MATERIAL_MODIFIER_NBT_KEY = Utils.MOD_ID + "_material_modifier";
    private static final String SKILL_MODIFIER_NBT_KEY = Utils.MOD_ID + "_skill_modifier";

    Random r = new Random();


    //private float max_damage;
    private float weight;
    private float flexibility;
    private float range;

    private float material_modifier;
    private float skill_modifier;

    public WeaponCapability() {
    }

    public WeaponCapability(float weight, float flexibility, float range, float average_material_level, float skill_level) {
        //this.max_damage = max_damage;
        this.weight = weight;
        this.flexibility = flexibility;
        this.range = range;

        this.material_modifier = average_material_level + r.nextFloat();
        this.skill_modifier = skill_level + ((r.nextFloat() - 0.5F)*20);
    }

//    @Override
//    public float getMaxDamage() {
//        return max_damage;
//    }

//    @Override
//    public void setMaxDamage(float value) {
//        max_damage = value;
//    }

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
    public float getMaterialModifier() {
        return material_modifier;
    }

    @Override
    public void setMaterialModifier(float value) {
        material_modifier = value;
    }

    @Override
    public float getSkillModifier() {
        return skill_modifier;
    }

    @Override
    public void setSkillModifier(float value) {
        skill_modifier = value;
    }

    @Override
    public CompoundNBT serializeNBT() {
        final CompoundNBT nbt = new CompoundNBT();
        //nbt.putFloat(MAX_DAMAGE_NBT_KEY, max_damage);
        nbt.putFloat(WEIGHT_NBT_KEY, weight);
        nbt.putFloat(FLEXIBILITY_NBT_KEY, flexibility);
        nbt.putFloat(RANGE_NBT_KEY, range);
        nbt.putFloat(MATERIAL_MODIFIER_NBT_KEY, material_modifier);
        nbt.putFloat(SKILL_MODIFIER_NBT_KEY, skill_modifier);
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundNBT nbt) {
        //this.setMaxDamage(nbt.getFloat(MAX_DAMAGE_NBT_KEY));
        this.setWeight(nbt.getFloat(WEIGHT_NBT_KEY));
        this.setFlexibility(nbt.getFloat(FLEXIBILITY_NBT_KEY));
        this.setRange(nbt.getFloat(RANGE_NBT_KEY));
        this.setMaterialModifier(nbt.getFloat(MATERIAL_MODIFIER_NBT_KEY));
        this.setSkillModifier(nbt.getFloat(SKILL_MODIFIER_NBT_KEY));
    }
}
