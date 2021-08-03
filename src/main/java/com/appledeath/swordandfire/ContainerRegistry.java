package com.appledeath.swordandfire;

import com.appledeath.swordandfire.gui.forgefurnace.ForgeFurnaceContainerGui;
import com.appledeath.swordandfire.inventory.ForgeFurnaceContainer;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

import java.lang.reflect.Field;

@Mod.EventBusSubscriber(modid = Utils.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ContainerRegistry {

    public static final ContainerType FORGE_FURNACE_CONTAINER = register(new ContainerType(ForgeFurnaceContainer::new), "forge_furnace");

    public static ContainerType register(ContainerType type, String name) {
        type.setRegistryName(name);
        return type;
    }

    @SubscribeEvent
    public static void registerContainers(final RegistryEvent.Register<ContainerType<?>> event) {
        try {
            for (Field f : ContainerRegistry.class.getDeclaredFields()) {
                Object obj = f.get(null);
                if (obj instanceof ContainerType) {
                    event.getRegistry().register((ContainerType) obj);
                } else if (obj instanceof ContainerType[]) {
                    for (ContainerType container : (ContainerType[]) obj) {
                        event.getRegistry().register(container);
                    }
                }
            }
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @SubscribeEvent
    public static void onClientSetupEvent(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            ScreenManager.registerFactory(FORGE_FURNACE_CONTAINER, ForgeFurnaceContainerGui::new);
        });
    }
}
