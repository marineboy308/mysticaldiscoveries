package com.mdcore.MysticalDiscoveries.tileentity.containers.slots;

import com.mdcore.MysticalDiscoveries.tileentity.TileEntityCrafterEvaporating;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotCrafterEvaporatingFuel extends Slot{

	public SlotCrafterEvaporatingFuel(IInventory inventory, int index, int x, int y) {
	
		super(inventory,index,x,y);
	}
	
	@Override
	public boolean isItemValid(ItemStack stack) {
		return TileEntityCrafterEvaporating.isItemFuel(stack);
	}
	
	@Override
	public int getItemStackLimit(ItemStack stack) {
		return super.getItemStackLimit(stack);
	}

}
