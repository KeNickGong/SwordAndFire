package com.appledeath.swordandfire.capability;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class WeaponCapabilityProvider implements ICapabilityProvider, INBTSerializable {
    final WeaponCapability data = new WeaponCapability();

    public WeaponCapabilityProvider() { }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if (cap == SaFCapabilityManager.WEAPON_CAPABILITY)
            return (LazyOptional<T>) LazyOptional.of(this::getImpl);

        return LazyOptional.empty();
    }

    @Override
    public INBT serializeNBT() {
        return data.serializeNBT();
    }

    @Override
    public void deserializeNBT(INBT nbt) {
        data.deserializeNBT((CompoundNBT) nbt);
    }

    private WeaponCapability getImpl() {
        if (data != null) {
            return data;
        }
        return new WeaponCapability();
    }
}
