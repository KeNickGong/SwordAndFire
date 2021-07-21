package com.appledeath.swordandfire;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Utils.MOD_ID)
@Mod.EventBusSubscriber(modid = Utils.MOD_ID)
public class SwordAndFire {
    public static ItemGroup TAB_MATERIALS = new ItemGroup(Utils.MOD_ID + "materials") {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(ItemRegistry.STEEL_INGOT);
        }
    };
    public SwordAndFire() {
    }
}
