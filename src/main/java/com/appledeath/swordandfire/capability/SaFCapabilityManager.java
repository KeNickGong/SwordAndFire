package com.appledeath.swordandfire.capability;

import com.appledeath.swordandfire.Utils;
import com.appledeath.swordandfire.capability.IWeaponCapability;
import com.appledeath.swordandfire.capability.WeaponCapability;
import com.appledeath.swordandfire.item.SaFAxeItem;
import com.appledeath.swordandfire.item.SaFLargeAxeItem;
import com.appledeath.swordandfire.item.SaFSwordItem;
import com.appledeath.swordandfire.item.SaFWeaponGenericItem;
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
    @Nonnull
    public static Capability<IWeaponCapability> WEAPON_CAPABILITY = null;
    public static final ResourceLocation WEAPON_CAPABILITY_NAME = new ResourceLocation(Utils.MOD_ID, "weapon_capability");

    private SaFCapabilityManager() { }

    public static void registerCapabilities() {
        CapabilityManager.INSTANCE.register(
                IWeaponCapability.class,
                new WeaponCapabilityStorage(),
                WeaponCapability::new
        );
    }

    @SubscribeEvent
    public static void onStackAttachCapabilities(@Nonnull final AttachCapabilitiesEvent<ItemStack> e) {
        final ItemStack obj = e.getObject();
        final Item item = obj.getItem();

        final IWeaponCapability capability = new WeaponCapability(); // Default Storage could be used

        if (!(item instanceof SaFWeaponGenericItem)) {
            return;
        } else if (item instanceof SaFSwordItem) { //Attach basic cap for sword
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
                    new WeaponCapabilityProvider(70, 40, 3.5F)
            );
        } else {
            e.addCapability(
                    WEAPON_CAPABILITY_NAME,
                    new WeaponCapabilityProvider()
            );
        }
    }

}
