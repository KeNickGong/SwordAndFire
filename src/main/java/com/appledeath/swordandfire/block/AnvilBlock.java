package com.appledeath.swordandfire.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;

import java.util.Optional;
import java.util.stream.Stream;

public class AnvilBlock extends SaFBlock{
    public static final DirectionProperty FACING = HorizontalBlock.HORIZONTAL_FACING;
    private static final Optional<VoxelShape> shape_north, shape_east, shape_south, shape_west;

    static {
        shape_north = Stream.of(
                Block.makeCuboidShape(3, 0, 2, 13, 4, 15),
                Block.makeCuboidShape(3.5, 4, 2, 12.5, 7, 15),
                Block.makeCuboidShape(3.5, 7, 0, 12.5, 12, 16),
                Block.makeCuboidShape(3.5, 12, 6, 12.5, 13, 16)
        ).reduce((v1, v2) -> {return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);});

        shape_east = Stream.of(
                Block.makeCuboidShape(1, 0, 3, 14, 4, 13),
                Block.makeCuboidShape(1, 4, 3.5, 14, 7, 12.5),
                Block.makeCuboidShape(0, 7, 3.5, 16, 12, 12.5),
                Block.makeCuboidShape(0, 12, 3.5, 10, 13, 12.5)
        ).reduce((v1, v2) -> {return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);});

        shape_south = Stream.of(
                Block.makeCuboidShape(3, 0, 1, 13, 4, 14),
                Block.makeCuboidShape(3.5, 4, 1, 12.5, 7, 14),
                Block.makeCuboidShape(3.5, 7, 0, 12.5, 12, 16),
                Block.makeCuboidShape(3.5, 12, 0, 12.5, 13, 10)
        ).reduce((v1, v2) -> {return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);});

        shape_west = Stream.of(
                Block.makeCuboidShape(2, 0, 3, 15, 4, 13),
                Block.makeCuboidShape(2, 4, 3.5, 15, 7, 12.5),
                Block.makeCuboidShape(0, 7, 3.5, 16, 12, 12.5),
                Block.makeCuboidShape(6, 12, 3.5, 16, 13, 12.5)
        ).reduce((v1, v2) -> {return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);});
    }

    public AnvilBlock(String name) {
        super(Material.ANVIL, name, "pickaxe", 3, 5.0F, 1200.0F, SoundType.ANVIL);
    }

    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        return this.getDefaultState().with(FACING, context.getPlacementHorizontalFacing().getOpposite());
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        Direction direction = state.get(FACING).getOpposite();
        switch (direction) {
            case NORTH:
                return shape_north.get();
            case SOUTH:
                return shape_south.get();
            case EAST:
                return shape_east.get();
            case WEST:
                return shape_west.get();
        }
        return shape_north.get();
    }
}
