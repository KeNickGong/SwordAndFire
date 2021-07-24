package com.appledeath.swordandfire.Proxy;

import com.appledeath.swordandfire.Utils;
import com.appledeath.swordandfire.gui.ForgeFurnaceGui;
import net.minecraft.client.Minecraft;
import net.minecraft.util.text.TranslationTextComponent;
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

    @OnlyIn(Dist.CLIENT)
    @Override
    public void openForgeFurnaceGui() {
        Minecraft.getInstance().displayGuiScreen(new ForgeFurnaceGui(new TranslationTextComponent(Utils.MOD_ID + ".test")));
    }
}
