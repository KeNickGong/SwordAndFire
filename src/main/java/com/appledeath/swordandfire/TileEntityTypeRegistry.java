package com.appledeath.swordandfire;

import com.appledeath.swordandfire.entity.ForgeFurnaceTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.lang.reflect.Field;

@Mod.EventBusSubscriber(modid = Utils.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class TileEntityTypeRegistry {
    public static final TileEntityType<ForgeFurnaceTileEntity> FORGE_FURNACE = registerTileEntity(TileEntityType.Builder.create(ForgeFurnaceTileEntity::new, BlockRegistry.FORGE_FURNACE), "forge_furnace");

    public static TileEntityType registerTileEntity(TileEntityType.Builder builder, String entityName) {
        ResourceLocation nameLoc = new ResourceLocation(Utils.MOD_ID, entityName);
        return (TileEntityType) builder.build(null).setRegistryName(nameLoc);
    }

    @SubscribeEvent
    public static void registerTileEntities(final RegistryEvent.Register<TileEntityType<?>> event) {
        try {
            for (Field f : TileEntityTypeRegistry.class.getDeclaredFields()) {
                Object obj = f.get(null);
                if (obj instanceof TileEntityType) {
                    event.getRegistry().register((TileEntityType) obj);
                } else if (obj instanceof TileEntityType[]) {
                    for (TileEntityType te : (TileEntityType[]) obj) {
                        event.getRegistry().register(te);
                    }
                }
            }
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
