package com.appledeath.swordandfire.item;

import com.appledeath.swordandfire.item.weapontrait.ISaFShieldPenetrable;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemGroup;

public class SaFAxeItem extends SaFWeaponGenericItem implements ISaFShieldPenetrable {

    private int shieldPenetrableLevel;

    public SaFAxeItem(IItemTier tier, int attackDamageIn, float attackSpeedIn, float baseWeight, float baseFlex, float baseRange, String name, int shieldPenetrableLevel, ItemGroup category){
        super(tier, attackDamageIn, attackSpeedIn, baseWeight, baseFlex, baseRange, name, category);
        this.shieldPenetrableLevel = shieldPenetrableLevel;
    }

    @Override
    public boolean getShieldPenetrable() { return true; }

    @Override
    public int getShieldPenetrableLevel() {
        return shieldPenetrableLevel;
    }

//    @Override
//    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlotType equipmentSlot, ItemStack stack) {
//        return null;
//    }
}
