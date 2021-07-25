package com.appledeath.swordandfire;

import com.appledeath.swordandfire.Proxy.ClientProxy;
import com.appledeath.swordandfire.Proxy.CommonProxy;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
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
    public static ItemGroup TAB_WEAPONS = new ItemGroup(Utils.MOD_ID + "weapons") {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(ItemRegistry.VIKING_SWORD);
        }
    };
    public static ItemGroup TAB_BLOCKS = new ItemGroup(Utils.MOD_ID + "blocks") {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(BlockRegistry.STEEL_BLOCK);
        }
    };

    public static CommonProxy PROXY = DistExecutor.safeRunForDist(() -> ClientProxy::new, () -> CommonProxy::new);

    public SwordAndFire() {
        PROXY.init();
    }

    private void setupComplete(final FMLLoadCompleteEvent event) {
        PROXY.postInit();
    }

    private void setupClient(FMLClientSetupEvent event) {
        PROXY.setupClient();
    }
}
