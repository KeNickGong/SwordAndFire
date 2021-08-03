package com.appledeath.swordandfire;

import com.appledeath.swordandfire.item.*;
import com.google.common.collect.Lists;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.lang.reflect.Field;
import java.util.List;

@Mod.EventBusSubscriber(modid = Utils.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ItemRegistry {
    //Tabs
    private static final ItemGroup MATERIAL = SwordAndFire.TAB_MATERIALS;
    private static final ItemGroup WEAPON = SwordAndFire.TAB_WEAPONS;

    public static final Item STEEL_INGOT = new SaFItem("steel_ingot", MATERIAL);
    public static final Item STEEL_NUGGET = new SaFItem("steel_nugget", MATERIAL);

    public static final Item VIKING_SWORD = new SaFSwordItem(SaFWeaponTier.TIER_ZERO, 4, -2.4F, 50, 50, 3.5F,"viking_sword", WEAPON);
    public static final Item ARMING_SWORD = new SaFSwordItem(SaFWeaponTier.TIER_ONE, 5, -2.4F, 50, 50, 3.5F, "arming_sword", WEAPON);
    public static final Item SHAMSHIR_SWORD = new SaFCavalrySwordItem(SaFWeaponTier.TIER_ONE, 3, -2.4F, 40, 60, 3.2F, "shamshir_sword", 1, WEAPON);
    public static final Item ENGLISH_CLAYMORE = new SaFSwordItem(SaFWeaponTier.TIER_TWO, 6, -2.4F, 50, 50, 3.5F, "english_claymore", WEAPON);
    public static final Item ATAKAN_MACHETE = new SaFSwordItem(SaFWeaponTier.TIER_TWO, 6, -2.4F, 50, 50, 3.5F, "atakan_machete", WEAPON);
    public static final Item LONG_SWORD = new SaFLargeSwordItem(SaFWeaponTier.TIER_ONE, 7, -3.0F, 60, 50, 4F, "long_sword", WEAPON);
    public static final Item SCOTTISH_CLAYMORE = new SaFLargeSwordItem(SaFWeaponTier.TIER_TWO, 8, -3.1F, 70, 50, 4, "scottish_claymore", WEAPON);
    public static final Item ZWEIHANDER = new SaFLargeSwordItem(SaFWeaponTier.TIER_THREE, 11, -3.3F, 90, 40, 4.5F, "zweihander", WEAPON);

    public static final Item VIKING_AXE = new SaFAxeItem(SaFWeaponTier.TIER_ZERO, 4, -2.4F, 50, 50, 3.5F, "viking_axe", 1, WEAPON);
    public static final Item CRESCENT_AXE = new SaFLargeAxeItem(SaFWeaponTier.TIER_ONE, 7, -3.2F, 70, 40, 4.5F,"crescent_axe", 2, WEAPON);

    public static final Item NAILED_MACE = new SaFBluntItem(SaFWeaponTier.TIER_TWO, 5, -2.6F, 50, 40, 3.5F, "nailed_mace", 1, WEAPON);

    public static final Item GAULISH_LANCE = new SaFLanceItem(SaFWeaponTier.TIER_ONE, 4, -3.6F, 80, 50, 5F, "gaulish_lance", 2, WEAPON);

    public static final Item LOWLANDS_SPEAR = new SaFLongSpearItem(SaFWeaponTier.TIER_ONE, 7, -3.2F, 80, 50, 6F, "lowlands_spear", 1, WEAPON);

    public static List<ItemStack> MATERIALS = Lists.newArrayList();
    public static List<ItemStack> SWORDS = Lists.newArrayList();
    public static List<ItemStack> AXES = Lists.newArrayList();
    public static List<ItemStack> BLUNTS = Lists.newArrayList();
    public static List<ItemStack> POLES = Lists.newArrayList();


    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        try {
            for (Field f : ItemRegistry.class.getDeclaredFields()) {
                Object obj = f.get(null);
                if (obj instanceof Item) {
                    if(((Item) obj).getRegistryName() != null){
                        event.getRegistry().register((Item) obj);
                    }

                    if (obj instanceof SaFSwordItem) {
                        SWORDS.add(new ItemStack((Item) obj));
                    }
                    if (obj instanceof SaFAxeItem) {
                        AXES.add(new ItemStack((Item) obj));
                    }
                    if (obj instanceof SaFBluntItem) {
                        BLUNTS.add(new ItemStack((Item) obj));
                    }
                    if (obj instanceof SaFPoleItem) {
                        POLES.add(new ItemStack((Item) obj));
                    }

                } else if (obj instanceof Item[]) {
                    for (Item item : (Item[]) obj) {
                        if(item.getRegistryName() != null){
                            event.getRegistry().register(item);
                        }
                    }
                }
            }
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

}
