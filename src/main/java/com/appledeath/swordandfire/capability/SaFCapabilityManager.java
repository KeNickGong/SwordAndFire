package com.appledeath.swordandfire.capability;

import com.appledeath.swordandfire.Utils;
import com.appledeath.swordandfire.capability.IWeaponCapability;
import com.appledeath.swordandfire.capability.WeaponCapability;
import com.appledeath.swordandfire.item.SaFSwordItem;
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
        if (!(item instanceof SaFSwordItem)) {
            return;
        }
        final IWeaponCapability capability = new WeaponCapability(); // Default Storage could be used
        e.addCapability(
                WEAPON_CAPABILITY_NAME,
                // Use SimplePersistentCapabilityProvider if in need of persisting data
                new WeaponCapabilityProvider()
        );
    }

}
