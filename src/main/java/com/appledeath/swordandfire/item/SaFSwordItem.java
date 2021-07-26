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

public class SaFSwordItem extends SaFWeaponGenericItem {

    public SaFSwordItem(IItemTier tier, int attackDamageIn, float attackSpeedIn, float baseWeight, String name, ItemGroup category){
        super(tier, attackDamageIn, attackSpeedIn, baseWeight, name, category);

        this.setRegistryName(name);
    }


//    @Override
//    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlotType equipmentSlot, ItemStack stack) {
//        return null;
//    }
}
