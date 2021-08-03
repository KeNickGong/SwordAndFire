package com.appledeath.swordandfire.block;

import com.appledeath.swordandfire.SwordAndFire;
import com.appledeath.swordandfire.entity.ForgeFurnaceContainerTileEntity;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.network.PacketBuffer;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nullable;

public class ForgeFurnaceBlock extends Block {
    public static final DirectionProperty FACING = HorizontalBlock.HORIZONTAL_FACING;

    public ForgeFurnaceBlock(String name) {
        super(AbstractBlock.Properties
                .create(Material.ROCK)
                .sound(SoundType.STONE)
                .hardnessAndResistance(3.5F)
                .harvestTool(ToolType.get("pickaxe"))
                .harvestLevel(3)
                .setRequiresTool()
                .notSolid());
        this.setRegistryName(name);
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new ForgeFurnaceContainerTileEntity();
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if (!worldIn.isRemote && handIn == Hand.MAIN_HAND) {
            ForgeFurnaceContainerTileEntity forgeFurnaceContainerTileEntity = (ForgeFurnaceContainerTileEntity) worldIn.getTileEntity(pos);
            NetworkHooks.openGui((ServerPlayerEntity) player, forgeFurnaceContainerTileEntity, (PacketBuffer packerBuffer) -> {
                packerBuffer.writeBlockPos(forgeFurnaceContainerTileEntity.getPos());
            });
        }
        return ActionResultType.SUCCESS;
    }

    @Override
    public void onReplaced(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
        TileEntity tileentity = worldIn.getTileEntity(pos);
        if (tileentity instanceof ForgeFurnaceContainerTileEntity) {
            InventoryHelper.dropInventoryItems(worldIn, pos, ((ForgeFurnaceContainerTileEntity) tileentity).getInputInventory());
            InventoryHelper.dropInventoryItems(worldIn, pos, ((ForgeFurnaceContainerTileEntity) tileentity).getFuelInventory());
            InventoryHelper.dropInventoryItems(worldIn, pos, ((ForgeFurnaceContainerTileEntity) tileentity).getProductInventory());
            worldIn.updateComparatorOutputLevel(pos, this);
            worldIn.removeTileEntity(pos);
        }
    }

    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        return this.getDefaultState().with(FACING, context.getPlacementHorizontalFacing());
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }
}
