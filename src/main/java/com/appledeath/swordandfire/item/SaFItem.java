package com.appledeath.swordandfire.item;

import com.appledeath.swordandfire.SwordAndFire;
import com.appledeath.swordandfire.Utils;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

public class SaFItem extends Item {
    public SaFItem(String name) {
        super(new Item.Properties().group(ItemGroup.MISC));
        this.setRegistryName(Utils.MOD_ID, name);
    }
    public SaFItem(String name, ItemGroup category) {
        super(new Item.Properties().group(category));
        this.setRegistryName(Utils.MOD_ID, name);
    }
}
