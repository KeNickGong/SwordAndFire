package com.appledeath.swordandfire.entity;

import net.minecraft.util.IIntArray;

public class ForgeFurnaceContainerCounter implements IIntArray {
    int i = 0;

    @Override
    public int get(int index) {
        return i;
    }

    @Override
    public void set(int index, int value) {
        i = value;
    }

    @Override
    public int size() {
        return 1;
    }
}
