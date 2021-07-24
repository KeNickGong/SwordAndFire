package com.appledeath.swordandfire;

import com.appledeath.swordandfire.block.AnvilBlock;
import com.appledeath.swordandfire.block.ForgeFurnaceBlock;
import com.appledeath.swordandfire.block.SaFBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.WallTorchBlock;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.lang.reflect.Field;

@Mod.EventBusSubscriber(modid = Utils.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class BlockRegistry {
    //Tabs
    private static final ItemGroup MATERIAL = SwordAndFire.TAB_MATERIALS;

    public static final Block STEEL_BLOCK = new SaFBlock(Material.IRON, "steel_block", "pickaxe", 2, 3.0F, 10.0F, SoundType.METAL);

    public static final Block ANVIL = new AnvilBlock("anvil");
    public static final Block FORGE_FURNACE = new ForgeFurnaceBlock("forge_furnace");
    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        try {
            for (Field f : BlockRegistry.class.getDeclaredFields()) {
                Object obj = f.get(null);
                if (obj instanceof Block) {
                    event.getRegistry().register((Block) obj);
                } else if (obj instanceof Block[]) {
                    for (Block block : (Block[]) obj) {
                        event.getRegistry().register(block);
                    }
                }
            }
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
    @SubscribeEvent
    public static void registerBlockItems(RegistryEvent.Register<Item> event) {
        try {
            for (Field f : BlockRegistry.class.getDeclaredFields()) {
                Object obj = f.get(null);
                if (obj instanceof Block && !(obj instanceof WallTorchBlock)) {
                    Item.Properties props = new Item.Properties();
                    props.group(SwordAndFire.TAB_BLOCKS);
                    BlockItem itemBlock;
                    itemBlock = new BlockItem((Block) obj, props);
                    itemBlock.setRegistryName(((Block) obj).getRegistryName());
                    event.getRegistry().register(itemBlock);
                } else if (obj instanceof Block[]) {
                    for (Block block : (Block[]) obj) {
                        Item.Properties props = new Item.Properties();
                        if (block.getRegistryName() != null) {
                            props.group(SwordAndFire.TAB_BLOCKS);
                            BlockItem itemBlock = new BlockItem(block, props);
                            itemBlock.setRegistryName(block.getRegistryName());
                            event.getRegistry().register(itemBlock);
                        }
                    }
                }
            }
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
