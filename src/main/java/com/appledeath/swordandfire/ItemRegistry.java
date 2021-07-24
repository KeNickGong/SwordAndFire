package com.appledeath.swordandfire;

import com.appledeath.swordandfire.item.SaFItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.lang.reflect.Field;

@Mod.EventBusSubscriber(modid = Utils.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ItemRegistry {
    //Tabs
    private static final ItemGroup MATERIAL = SwordAndFire.TAB_MATERIALS;

    public static final Item STEEL_INGOT = new SaFItem("steel_ingot", MATERIAL);
    public static final Item STEEL_NUGGET = new SaFItem("steel_nugget", MATERIAL);

    public static final Item VIKING_SWORD_BODY = new SaFItem("viking_sword_body", MATERIAL);

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        try {
            for (Field f : ItemRegistry.class.getDeclaredFields()) {
                Object obj = f.get(null);
                if (obj instanceof Item) {
                    if(((Item) obj).getRegistryName() != null){
                        event.getRegistry().register((Item) obj);
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
