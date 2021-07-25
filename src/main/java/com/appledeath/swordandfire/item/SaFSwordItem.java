package com.appledeath.swordandfire.item;

import com.appledeath.swordandfire.Utils;
import com.google.common.collect.Multimap;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.items.IItemHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class SaFSwordItem extends SwordItem {
    private static final String MAX_DAMAGE_NBT_KEY = Utils.MOD_ID + "_max_damage";
    private static final String WEIGHT_NBT_KEY = Utils.MOD_ID + "_weight";
    private static final String FLEXIBILITY_NBT_KEY = Utils.MOD_ID + "_flexibility";
    private static final String RANGE_NBT_KEY = Utils.MOD_ID + "_range";

    public SaFSwordItem(IItemTier tier, int attackDamageIn, float attackSpeedIn, String name, ItemGroup category){
        super(tier, attackDamageIn, attackSpeedIn, new Item.Properties().group(category).maxStackSize(1));
        this.setRegistryName(name);
    }

//    @Override
//    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlotType equipmentSlot, ItemStack stack) {
//        return null;
//    }


    @Override
    public int getMaxDamage(ItemStack stack) {
        int baseDurability = this.getTier().getMaxUses();
        CompoundNBT nbt = stack.getTag();
        float weight = nbt.getFloat(MAX_DAMAGE_NBT_KEY);
        return 100;
    }
}
