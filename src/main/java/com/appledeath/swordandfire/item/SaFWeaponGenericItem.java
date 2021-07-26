package com.appledeath.swordandfire.item;

import com.appledeath.swordandfire.Utils;
import com.appledeath.swordandfire.capability.IWeaponCapability;
import com.appledeath.swordandfire.capability.SaFCapabilityManager;
import com.appledeath.swordandfire.capability.WeaponCapability;
import com.appledeath.swordandfire.capability.WeaponCapabilityProvider;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;

import java.util.UUID;

public class SaFWeaponGenericItem extends SwordItem {
    //private static final String MAX_DAMAGE_NBT_KEY = Utils.MOD_ID + "_max_damage";
    private static final String WEIGHT_NBT_KEY = Utils.MOD_ID + "_weight";
    private static final String FLEXIBILITY_NBT_KEY = Utils.MOD_ID + "_flexibility";
    private static final String RANGE_NBT_KEY = Utils.MOD_ID + "_range";

    private static final String MATERIAL_MODIFIER_NBT_KEY = Utils.MOD_ID + "_material_modifier";
    private static final String SKILL_MODIFIER_NBT_KEY = Utils.MOD_ID + "_skill_modifier";

    private final float attackDamage;
    private final float attackSpeed;
    private final float baseWeight;

    Multimap<Attribute, AttributeModifier> attributeModifiers;
    protected static final UUID MOVEMENT_SPEED_MODIFIER = UUID.randomUUID();

    public SaFWeaponGenericItem(IItemTier tier, int attackDamageIn, float attackSpeedIn, float baseWeight, String name, ItemGroup category) {
        super(tier, attackDamageIn, attackSpeedIn, new Item.Properties().group(category).maxStackSize(1));
        this.attackDamage = (float)attackDamageIn + tier.getAttackDamage();
        this.attackSpeed = (float)attackSpeedIn;
        this.baseWeight = baseWeight;
    }

    public float getAttackSpeed() {
        return attackSpeed;
    }

    @Override
    public int getMaxDamage(ItemStack stack) {
        CompoundNBT WeaponCap = stack.serializeNBT().getCompound("ForgeCaps").getCompound("swordandfire:weapon_capability");

        int baseDurability = this.getTier().getMaxUses();
        float weight = WeaponCap.getFloat(WEIGHT_NBT_KEY);
        float material_modifier = WeaponCap.getFloat(MATERIAL_MODIFIER_NBT_KEY);
        return (int) (baseDurability + (weight - baseWeight) * 5 + material_modifier * 100);
    }

    @Override
    public void inventoryTick(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
        if (!isSelected && attributeModifiers != null) {
        }
    }

    /*
        Attack Damage and Attack Speed calculation
     */
    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlotType equipmentSlot, ItemStack stack) {
        Multimap<Attribute, AttributeModifier> attributeModifiers;

        CompoundNBT WeaponCap = stack.serializeNBT().getCompound("ForgeCaps").getCompound("swordandfire:weapon_capability");

        float weight = WeaponCap.getFloat(WEIGHT_NBT_KEY);
        float flexibility = WeaponCap.getFloat(FLEXIBILITY_NBT_KEY);
        float skill_modifier = WeaponCap.getFloat(SKILL_MODIFIER_NBT_KEY);
        float material_modifier = WeaponCap.getFloat(MATERIAL_MODIFIER_NBT_KEY);

        float finalAttackDamage = this.attackDamage + (weight - baseWeight) * 0.25F + skill_modifier * 0.025F + material_modifier;
        float finalAttackSpeed = this.attackSpeed + (-1 * (weight - baseWeight) * 0.1F) + (flexibility - 50) * 0.01F;
        float finalMovementSpeed = weight < 50 ? 0 : -1 * ((weight - 50) * 0.004F) ;

        attributeModifiers = ArrayListMultimap.create();
        attributeModifiers.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Weapon modifier", (double) finalAttackDamage, AttributeModifier.Operation.ADDITION));
        attributeModifiers.put(Attributes.ATTACK_SPEED, new AttributeModifier(ATTACK_SPEED_MODIFIER, "Weapon modifier", (double) finalAttackSpeed, AttributeModifier.Operation.ADDITION));
        attributeModifiers.put(Attributes.MOVEMENT_SPEED, new AttributeModifier(MOVEMENT_SPEED_MODIFIER, "Weapon modifier",(double) finalMovementSpeed, AttributeModifier.Operation.MULTIPLY_BASE));


        return equipmentSlot == EquipmentSlotType.MAINHAND ? attributeModifiers : super.getAttributeModifiers(equipmentSlot);
    }
}