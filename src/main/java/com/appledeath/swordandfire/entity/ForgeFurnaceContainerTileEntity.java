package com.appledeath.swordandfire.entity;

import com.appledeath.swordandfire.TileEntityTypeRegistry;
import com.appledeath.swordandfire.Utils;
import com.appledeath.swordandfire.inventory.ForgeFurnaceContainer;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IntArray;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class ForgeFurnaceContainerTileEntity extends TileEntity implements ITickableTileEntity, INamedContainerProvider {
    private final Inventory inputInventory = new Inventory(6);
    private final Inventory fuelInventory = new Inventory(1);
    private final Inventory productInventory = new Inventory(1);
    ForgeFurnaceContainerCounter counter = new ForgeFurnaceContainerCounter();

    public ForgeFurnaceContainerTileEntity() {
        super(TileEntityTypeRegistry.FORGE_FURNACE);
    }

    @Override
    public void tick() {
        if (!world.isRemote) {
        }
    }

    @Override
    @Nonnull
    public ITextComponent getDisplayName() {
        return new TranslationTextComponent("gui." + Utils.MOD_ID + ".forge_furnace_container");
    }

    @Nullable
    @Override
    public Container createMenu(int id, PlayerInventory playerInventory, PlayerEntity player) {
        return new ForgeFurnaceContainer(id, this, playerInventory, new ForgeFurnaceContainerCounter());
    }

    @Override
    public void read(BlockState state, CompoundNBT nbt) {
        this.inputInventory.addItem(ItemStack.read(nbt.getCompound("item")));
        super.read(state, nbt);
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        ItemStack itemStack = this.inputInventory.getStackInSlot(0).copy();
        compound.put("item", itemStack.serializeNBT());
        return super.write(compound);
    }

    public Inventory getInputInventory() {
        return inputInventory;
    }

    public Inventory getFuelInventory() {
        return fuelInventory;
    }

    public Inventory getProductInventory() {
        return productInventory;
    }

}
