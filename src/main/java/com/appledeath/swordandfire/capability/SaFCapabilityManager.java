package com.appledeath.swordandfire.capability;

import com.appledeath.swordandfire.Utils;
import com.appledeath.swordandfire.item.*;
import com.appledeath.swordandfire.item.weapontrait.ISaFArmorPenetrable;
import com.appledeath.swordandfire.item.weapontrait.ISaFCavalryBonus;
import com.appledeath.swordandfire.item.weapontrait.ISaFShieldPenetrable;
import com.appledeath.swordandfire.item.weapontrait.ISaFTwoHanded;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import javax.annotation.Nonnull;

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

        e.addCapability(
                WEAPON_CAPABILITY_NAME,
                new WeaponCapabilityProvider(((SaFWeaponGenericItem) item).getBaseWeight(), ((SaFWeaponGenericItem) item).getBaseFlex(), ((SaFWeaponGenericItem) item).getBaseRange())
        );

        //Weapon Trait Capability
        boolean[] traitProps = new boolean[] {false, false, false, false};
        if (item instanceof ISaFTwoHanded) {
            traitProps[0] = true;
        }
        if (item instanceof ISaFArmorPenetrable) {
            traitProps[1] = true;
        }
        if (item instanceof ISaFShieldPenetrable) {
            traitProps[2] = true;
        }
        if (item instanceof ISaFCavalryBonus) {
            traitProps[3] = true;
        }
        e.addCapability(
                WEAPON_TRAIT_CAPABILITY_NAME,
                new WeaponTraitCapabilityProvider(traitProps)
        );
    }

}
