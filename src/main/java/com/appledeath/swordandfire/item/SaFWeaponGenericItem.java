package com.appledeath.swordandfire.item;

import com.appledeath.swordandfire.Utils;
import com.appledeath.swordandfire.capability.IWeaponCapability;
import com.appledeath.swordandfire.capability.SaFCapabilityManager;
import com.appledeath.swordandfire.capability.WeaponCapability;
import com.appledeath.swordandfire.capability.WeaponCapabilityProvider;
import com.appledeath.swordandfire.item.weapontrait.ISaFArmorPenetrable;
import com.appledeath.swordandfire.item.weapontrait.ISaFCavalryBonus;
import com.appledeath.swordandfire.item.weapontrait.ISaFShieldPenetrable;
import com.appledeath.swordandfire.item.weapontrait.ISaFTwoHanded;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nullable;
import java.util.List;
import java.util.UUID;

public class SaFWeaponGenericItem extends SwordItem {
    //private static final String MAX_DAMAGE_NBT_KEY = Utils.MOD_ID + "_max_damage";
    private static final String WEIGHT_NBT_KEY = Utils.MOD_ID + "_weight";
    private static final String FLEXIBILITY_NBT_KEY = Utils.MOD_ID + "_flexibility";
    private static final String RANGE_NBT_KEY = Utils.MOD_ID + "_range";
    private static final String MATERIAL_MODIFIER_NBT_KEY = Utils.MOD_ID + "_material_modifier";
    private static final String SKILL_MODIFIER_NBT_KEY = Utils.MOD_ID + "_skill_modifier";

    private static final String TWO_HANDED_NBT_KEY = Utils.MOD_ID + "_two_handed";
    private static final String OFFHAND_EMPTY_NBT_KEY = Utils.MOD_ID + "_offhand_empty";

    private final float attackDamage;
    private final float attackSpeed;

    private final float baseWeight;
    private final float baseFlex;
    private final float baseRange;

    protected static final UUID MOVEMENT_SPEED_MODIFIER = UUID.randomUUID();
    protected static final UUID ATTACK_REACH_MODIFIER = UUID.randomUUID();

    public static final TextFormatting[] TRAIT_COLOR = {TextFormatting.DARK_RED};

    public SaFWeaponGenericItem(IItemTier tier, int attackDamageIn, float attackSpeedIn, float baseWeight, float baseFlex, float baseRange, String name, ItemGroup category) {
        super(tier, attackDamageIn, attackSpeedIn, new Item.Properties().group(category).maxStackSize(1));
        this.attackDamage = (float)attackDamageIn + tier.getAttackDamage();
        this.attackSpeed = (float)attackSpeedIn;
        this.baseWeight = baseWeight;
        this.baseFlex = baseFlex;
        this.baseRange = baseRange;

        this.setRegistryName(name);
    }

    public float getAttackSpeed() {
        return attackSpeed;
    }

    public float getBaseWeight() { return baseWeight; }

    public float getBaseFlex() { return baseFlex; }

    public float getBaseRange() { return baseRange; }

    @Override
    public int getMaxDamage(ItemStack stack) {
        CompoundNBT WeaponCap = stack.serializeNBT().getCompound("ForgeCaps").getCompound("swordandfire:weapon_capability");

        int baseDurability = this.getTier().getMaxUses();
        float weight = WeaponCap.getFloat(WEIGHT_NBT_KEY);
        float material_modifier = WeaponCap.getFloat(MATERIAL_MODIFIER_NBT_KEY);
        return (int) (baseDurability + (weight - baseWeight) * 5 + material_modifier * 100);
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlotType equipmentSlot, ItemStack stack) {

        return equipmentSlot == EquipmentSlotType.MAINHAND ? calculateOutputAttribute(equipmentSlot, stack) : super.getAttributeModifiers(equipmentSlot);

    }


    /*
        Helper Method for Attack Damage, Attack Speed, Movement Speed, Attach Range calculation
     */
    public Multimap<Attribute, AttributeModifier> calculateOutputAttribute(EquipmentSlotType equipmentSlot, ItemStack stack) {
        Multimap<Attribute, AttributeModifier> attributeModifiers;

        CompoundNBT WeaponCap = stack.serializeNBT().getCompound("ForgeCaps").getCompound("swordandfire:weapon_capability");
        CompoundNBT WeaponTrait = stack.serializeNBT().getCompound("ForgeCaps").getCompound("swordandfire:weapon_trait_capability");

        float weight = WeaponCap.getFloat(WEIGHT_NBT_KEY);
        float flexibility = WeaponCap.getFloat(FLEXIBILITY_NBT_KEY);
        float skill_modifier = WeaponCap.getFloat(SKILL_MODIFIER_NBT_KEY);
        float material_modifier = WeaponCap.getFloat(MATERIAL_MODIFIER_NBT_KEY);
        float range = WeaponCap.getFloat(RANGE_NBT_KEY) - 3.5F;

        float finalAttackDamage = this.attackDamage + (weight - baseWeight) * 0.25F + skill_modifier * 0.025F + material_modifier;
        float finalAttackSpeed = this.attackSpeed + (-1 * (weight - baseWeight) * 0.1F) + (flexibility - 50) * 0.01F;
        float finalMovementSpeed = weight < 50 ? 0 : -1 * ((weight - 50) * 0.004F) ;



        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();

        builder.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Weapon modifier", finalAttackDamage, AttributeModifier.Operation.ADDITION));
        builder.put(Attributes.ATTACK_SPEED, new AttributeModifier(ATTACK_SPEED_MODIFIER, "Weapon modifier", finalAttackSpeed, AttributeModifier.Operation.ADDITION));
        builder.put(Attributes.MOVEMENT_SPEED, new AttributeModifier(MOVEMENT_SPEED_MODIFIER, "Weapon modifier", finalMovementSpeed, AttributeModifier.Operation.MULTIPLY_BASE));
        builder.put(ForgeMod.REACH_DISTANCE.get(), new AttributeModifier(ATTACK_REACH_MODIFIER,"Weapon modifier", range, AttributeModifier.Operation.ADDITION));

        /*
            Two Handed Trait Check
        */
        if (WeaponTrait.getBoolean(TWO_HANDED_NBT_KEY) && !WeaponTrait.getBoolean(OFFHAND_EMPTY_NBT_KEY)) {
            builder.put(Attributes.ATTACK_SPEED, new AttributeModifier(ATTACK_REACH_MODIFIER, "Weapon modifier", -0.5, AttributeModifier.Operation.MULTIPLY_TOTAL));
        }

        attributeModifiers = builder.build();

        return attributeModifiers;
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        CompoundNBT WeaponTrait = stack.serializeNBT().getCompound("ForgeCaps").getCompound("swordandfire:weapon_trait_capability");

        if (stack.getItem() instanceof ISaFTwoHanded) {
            tooltip.add(new TranslationTextComponent("trait." + Utils.MOD_ID + ".two_handed").mergeStyle(TRAIT_COLOR));
        }
        if (stack.getItem() instanceof ISaFArmorPenetrable) {
            tooltip.add(new TranslationTextComponent("trait." + Utils.MOD_ID + ".armor_penetrable_" + ((ISaFArmorPenetrable) stack.getItem()).getArmorPenetrableLevel()).mergeStyle(TRAIT_COLOR));
        }
        if (stack.getItem() instanceof ISaFShieldPenetrable) {
            tooltip.add(new TranslationTextComponent("trait." + Utils.MOD_ID + ".shield_penetrable_" + ((ISaFShieldPenetrable) stack.getItem()).getShieldPenetrableLevel()).mergeStyle(TRAIT_COLOR));
        }
        if (stack.getItem() instanceof ISaFCavalryBonus) {
            tooltip.add(new TranslationTextComponent("trait." + Utils.MOD_ID + ".cavalry_bonus_" + ((ISaFCavalryBonus) stack.getItem()).getCavalryBonusLevel()).mergeStyle(TRAIT_COLOR));
        }
        //tooltip.add(new StringTextComponent("SHIT"));
    }
}