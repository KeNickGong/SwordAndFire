package com.appledeath.swordandfire.item;

import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemGroup;

public class SaFAxeItem extends SaFWeaponGenericItem {

    public SaFAxeItem(IItemTier tier, int attackDamageIn, float attackSpeedIn, float baseWeight, String name, ItemGroup category){
        super(tier, attackDamageIn, attackSpeedIn, baseWeight, name, category);

        this.setRegistryName(name);
    }


//    @Override
//    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlotType equipmentSlot, ItemStack stack) {
//        return null;
//    }
}
