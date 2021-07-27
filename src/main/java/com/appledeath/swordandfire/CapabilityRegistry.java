package com.appledeath.swordandfire;

import com.appledeath.swordandfire.capability.*;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod.EventBusSubscriber(modid = Utils.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CapabilityRegistry {

    @SubscribeEvent
    public static void registerCapabilities(FMLCommonSetupEvent e) {
        CapabilityManager.INSTANCE.register(
                IWeaponCapability.class,
                new WeaponCapabilityStorage(),
                WeaponCapability::new
        );
        CapabilityManager.INSTANCE.register(
                IWeaponTraitCapability.class,
                new WeaponTraitCapabilityStorage(),
                WeaponTraitCapability::new
        );
    }
}
