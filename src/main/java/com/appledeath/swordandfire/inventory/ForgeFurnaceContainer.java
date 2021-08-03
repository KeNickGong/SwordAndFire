package com.appledeath.swordandfire.inventory;

import com.appledeath.swordandfire.ContainerRegistry;
import com.appledeath.swordandfire.entity.ForgeFurnaceContainerCounter;
import com.appledeath.swordandfire.entity.ForgeFurnaceContainerTileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIntArray;
import net.minecraft.util.IntArray;
import net.minecraft.util.text.StringTextComponent;

public class ForgeFurnaceContainer extends Container {

    private ForgeFurnaceContainerCounter intArray;

    public ForgeFurnaceContainer(int i, PlayerInventory playerInventory) {
        this(i, new ForgeFurnaceContainerTileEntity(), playerInventory, new ForgeFurnaceContainerCounter());
    }

    public ForgeFurnaceContainer(int id, ForgeFurnaceContainerTileEntity forgeFurnace, PlayerInventory playerInventory, IIntArray intArray) {
        super(ContainerRegistry.FORGE_FURNACE_CONTAINER, id);
        this.intArray = (ForgeFurnaceContainerCounter) intArray;
        trackIntArray(this.intArray);

        addSlotBox(forgeFurnace.getInputInventory(), 0, 26, 10, 3, 18, 2, 18);

        this.addSlot(new Slot(forgeFurnace.getFuelInventory(), 0, 44, 63));

        this.addSlot(new Slot(forgeFurnace.getProductInventory(), 0, 118, 19));

        layoutPlayerInventorySlots(playerInventory, 8, 84);
    }

    @Override
    public boolean canInteractWith(PlayerEntity playerIn) {
        return true;
    }

    @Override
    public ItemStack transferStackInSlot(PlayerEntity playerIn, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(index);
        if (slot != null && slot.getHasStack()) {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();
            if (index < 8) {
                if (!this.mergeItemStack(itemstack1, 8, 44, true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.mergeItemStack(itemstack1, 0, 7, false)) {
                return ItemStack.EMPTY;
            }

            if (itemstack1.isEmpty()) {
                slot.putStack(ItemStack.EMPTY);
            } else {
                slot.onSlotChanged();
            }

            if (itemstack1.getCount() == itemstack.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTake(playerIn, itemstack1);
        }

        return itemstack;
    }

    private int addSlotRange(IInventory inventory, int index, int x, int y, int amount, int dx) {
        for (int i = 0; i < amount; i++) {
            addSlot(new Slot(inventory, index, x, y));
            x += dx;
            index++;
        }
        return index;
    }

    private int addSlotBox(IInventory inventory, int index, int x, int y, int horAmount, int dx, int verAmount, int dy) {
        for (int j = 0; j < verAmount; j++) {
            index = addSlotRange(inventory, index, x, y, horAmount, dx);
            y += dy;
        }
        return index;
    }

    private void layoutPlayerInventorySlots(IInventory inventory, int leftCol, int topRow) {
        // Player inventory
        addSlotBox(inventory, 9, leftCol, topRow, 9, 18, 3, 18);

        // Hotbar
        topRow += 58;
        addSlotRange(inventory, 0, leftCol, topRow, 9, 18);
    }

    public IIntArray getIntArray() {
        return intArray;
    }
}
