package com.appledeath.swordandfire.event;

import com.appledeath.swordandfire.Utils;
import com.appledeath.swordandfire.capability.IWeaponCapability;
import com.appledeath.swordandfire.capability.IWeaponTraitCapability;
import com.appledeath.swordandfire.capability.SaFCapabilityManager;
import com.appledeath.swordandfire.capability.WeaponCapability;
import com.appledeath.swordandfire.item.ISaFTwoHanded;
import com.google.common.collect.Multimap;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

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
}
