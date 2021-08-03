package com.appledeath.swordandfire.proxy;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class ClientProxy extends CommonProxy{
    @OnlyIn(Dist.CLIENT)
    @Override
    @SuppressWarnings("deprecation")
    public void init() {
    }

    @OnlyIn(Dist.CLIENT)
    public void postInit() {
    }

    public void setupClient() {
    }
}
