package com.appledeath.swordandfire.capability;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;

import javax.annotation.Nullable;

public class WeaponTraitCapabilityStorage implements Capability.IStorage<IWeaponTraitCapability>{
    @Nullable
    @Override
    public INBT writeNBT(Capability<IWeaponTraitCapability> capability, IWeaponTraitCapability instance, Direction side) {
        return instance.serializeNBT();
    }

    @Override
    public void readNBT(Capability<IWeaponTraitCapability> capability, IWeaponTraitCapability instance, Direction side, INBT nbt) {
        instance.deserializeNBT((CompoundNBT) nbt);
    }
}
