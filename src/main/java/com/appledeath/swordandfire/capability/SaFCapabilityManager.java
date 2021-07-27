package com.appledeath.swordandfire.capability;

import com.appledeath.swordandfire.Utils;
import com.appledeath.swordandfire.capability.IWeaponCapability;
import com.appledeath.swordandfire.capability.WeaponCapability;
import com.appledeath.swordandfire.item.*;
import com.google.common.eventbus.Subscribe;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.common.util.Lazy;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import javax.annotation.Nonnull;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Supplier;

@Mod.EventBusSubscriber(modid = Utils.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class SaFCapabilityManager {
    @CapabilityInject(IWeaponCapability.class)
    public static Capability<IWeaponCapability> WEAPON_CAPABILITY = null;
    public static final ResourceLocation WEAPON_CAPABILITY_NAME = new ResourceLocation(Utils.MOD_ID, "weapon_capability");


    @CapabilityInject(IWeaponTraitCapability.class)
    public static Capability<IWeaponTraitCapability> WEAPON_TRAIT_CAPABILITY = null;
    public static final ResourceLocation WEAPON_TRAIT_CAPABILITY_NAME = new ResourceLocation(Utils.MOD_ID, "weapon_trait_capability");

    private SaFCapabilityManager() { }

    @SubscribeEvent
    public static void onStackAttachCapabilities(@Nonnull final AttachCapabilitiesEvent<ItemStack> e) {
        final ItemStack obj = e.getObject();
        final Item item = obj.getItem();


        //Weapon Capability
        if (!(item instanceof SaFWeaponGenericItem)) {
            return;
        }
        if (item instanceof SaFSwordItem) { //Attach basic cap for sword
            e.addCapability(
                    WEAPON_CAPABILITY_NAME,
                    new WeaponCapabilityProvider(50, 50, 3.5F)
            );
        } else if (item instanceof SaFAxeItem) {
            e.addCapability(
                    WEAPON_CAPABILITY_NAME,
                    new WeaponCapabilityProvider(50, 50, 3.5F)
            );
        } else if (item instanceof SaFLargeAxeItem) {
            e.addCapability(
                    WEAPON_CAPABILITY_NAME,
                    new WeaponCapabilityProvider(70, 40, 4.5F)
            );
        } else if (item instanceof SaFLargeSwordItem) {
            e.addCapability(
                    WEAPON_CAPABILITY_NAME,
                    new WeaponCapabilityProvider(60, 40, 4.0F)
            );
        } else {
            e.addCapability(
                    WEAPON_CAPABILITY_NAME,
                    new WeaponCapabilityProvider()
            );
        }

        //Weapon Trait Capability
        if (item instanceof ISaFTwoHanded) {
            e.addCapability(
                    WEAPON_TRAIT_CAPABILITY_NAME,
                    new WeaponTraitCapabilityProvider(true)
            );
        } else {
            e.addCapability(
                    WEAPON_TRAIT_CAPABILITY_NAME,
                    new WeaponTraitCapabilityProvider()
            );
        }
    }

}
