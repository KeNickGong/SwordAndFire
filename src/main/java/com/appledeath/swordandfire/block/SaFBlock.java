package com.appledeath.swordandfire.block;

import com.appledeath.swordandfire.Utils;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;

public class SaFBlock extends Block {
    public SaFBlock(Material materialIn, String name, String toolUsed, int toolStrength, float hardness, float resistance, SoundType sound) {
        super(
                AbstractBlock.Properties
                        .create(materialIn)
                        .sound(sound)
                        .hardnessAndResistance(hardness, resistance)
                        .harvestTool(ToolType.get(toolUsed))
                        .harvestLevel(toolStrength)
                        .setRequiresTool()
        );
        this.setRegistryName(Utils.MOD_ID, name);
    }
}
