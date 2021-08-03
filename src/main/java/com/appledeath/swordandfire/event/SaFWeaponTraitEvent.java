package com.appledeath.swordandfire.event;

import com.appledeath.swordandfire.Utils;
import com.appledeath.swordandfire.capability.IWeaponTraitCapability;
import com.appledeath.swordandfire.capability.SaFCapabilityManager;
import com.appledeath.swordandfire.item.weapontrait.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.passive.horse.AbstractHorseEntity;
import net.minecraft.entity.passive.horse.HorseEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Random;
import java.util.UUID;

@Mod.EventBusSubscriber(modid = Utils.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class SaFWeaponTraitEvent {
    protected static final UUID ATTACK_SPEED_MODIFIER = UUID.randomUUID();

    @SubscribeEvent
    public static void twoHandTraitModifier(TickEvent.PlayerTickEvent e) {
        if (e == null || e.player == null) {
            return;
        }

        PlayerEntity player = e.player;
        if (!(player.getHeldItemMainhand().getItem() instanceof ISaFTwoHanded)) {
            return;
        }

        ItemStack stack = player.getHeldItemMainhand();

        LazyOptional<IWeaponTraitCapability> lazyOptional = stack.getCapability(SaFCapabilityManager.WEAPON_TRAIT_CAPABILITY);

        lazyOptional.ifPresent((s) -> {
            if (player.getHeldItemOffhand().getItem() != Items.AIR) {
                s.setIsOffhandEmpty(false);
            } else {
                s.setIsOffhandEmpty(true);
            }
        });

    }

    @SubscribeEvent
    public static void armorPenetrationModifier(LivingHurtEvent e) {
        float baseDamage = e.getAmount();
        int victimArmorValue = 0;

        if (!(e.getSource().getTrueSource() instanceof PlayerEntity)) {
            return;
        }
        PlayerEntity attacker = (PlayerEntity) e.getSource().getTrueSource();
        LivingEntity victim = e.getEntityLiving();

        ItemStack weapon = attacker.getHeldItemMainhand();

        if (!(weapon.getItem() instanceof ISaFArmorPenetrable)) {
            return;
        }

        Iterable<ItemStack> armors = victim.getArmorInventoryList();
        ISaFArmorPenetrable weaponItem = (ISaFArmorPenetrable) weapon.getItem();

        for (ItemStack armor : armors) {
            if (!armor.isEmpty() && (armor.getItem() instanceof ArmorItem)) {
                victimArmorValue += ((ArmorItem) armor.getItem()).getDamageReduceAmount();
            }
        }
        e.setAmount((victimArmorValue >= 10 ? (victimArmorValue - 10) * (0.25F + (weaponItem.getArmorPenetrableLevel() * 0.25F)) : 0) + baseDamage);
    }

    @SubscribeEvent
    public static void shieldPenetrationModifier(LivingAttackEvent e) {

        float damage = e.getAmount();

        if (!(e.getSource().getTrueSource() instanceof PlayerEntity)) {
            return;
        }
        PlayerEntity attacker = (PlayerEntity) e.getSource().getTrueSource();
        LivingEntity victim = e.getEntityLiving();

        ItemStack weapon = attacker.getHeldItemMainhand();
        if (!(weapon.getItem() instanceof ISaFShieldPenetrable)) {
            return;
        }

        ISaFShieldPenetrable weaponItem = (ISaFShieldPenetrable) weapon.getItem();
        ItemStack shield = victim.getActiveItemStack();

        if (victim.isActiveItemStackBlocking() && shield.getItem() instanceof ShieldItem) {
            shield.damageItem((int) damage * (1 + weaponItem.getShieldPenetrableLevel()), victim, player -> {});
        }
    }

    @SubscribeEvent
    public static void cavalryBonusModifier(LivingHurtEvent e) {
        float baseDamage = e.getAmount();

        if (!(e.getSource().getTrueSource() instanceof PlayerEntity)) {
            return;
        }

        PlayerEntity attacker = (PlayerEntity) e.getSource().getTrueSource();
        LivingEntity victim = e.getEntityLiving();

        ItemStack weapon = attacker.getHeldItemMainhand();
        if (!(weapon.getItem() instanceof ISaFCavalryBonus)) {
            return;
        }

        ISaFCavalryBonus weaponItem = (ISaFCavalryBonus) weapon.getItem();

        if (attacker.getRidingEntity() instanceof AbstractHorseEntity) {
            if (weaponItem.getCavalryBonusLevel() == 1) {
                baseDamage = baseDamage * 2;
            } else {
                baseDamage = baseDamage * (1 + weaponItem.getCavalryBonusLevel());
                victim.addPotionEffect(new EffectInstance(Effects.MINING_FATIGUE, 60, weaponItem.getCavalryBonusLevel() - 1));
            }
        }

        e.setAmount(baseDamage);
    }

    @SubscribeEvent
    public static void antiHorseModifier(LivingHurtEvent e) {
        if (!(e.getSource().getTrueSource() instanceof PlayerEntity)) {
            return;
        }

        PlayerEntity attacker = (PlayerEntity) e.getSource().getTrueSource();
        LivingEntity victim = e.getEntityLiving();
        ItemStack weapon = attacker.getHeldItemMainhand();

        if (attacker.getRidingEntity() != null) {
            return;
        }
        if (!(weapon.getItem() instanceof ISaFAntiHorse)) {
            return;
        }

        if (victim instanceof AbstractHorseEntity) {
            victim.setAIMoveSpeed(0);
            victim.setMotion(0, 0, 0);
        }

        if (victim instanceof PlayerEntity && victim.getRidingEntity() instanceof AbstractHorseEntity) {
            Random r = new Random();
            if (r.nextFloat() > 0.9) {
                victim.stopRiding();
                if (!victim.getEntityWorld().isRemote) {
                    attacker.sendStatusMessage(new TranslationTextComponent("trait." + Utils.MOD_ID + ".anti_horse_fall"), false);
                }
            }
        }
    }
}
