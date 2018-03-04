package com.mdcore.MysticalDiscoveries.tileentity.containers;

import com.mdcore.MysticalDiscoveries.recipes.CrafterEvaporatingRecipes;
import com.mdcore.MysticalDiscoveries.tileentity.TileEntityCrafterEvaporating;
import com.mdcore.MysticalDiscoveries.tileentity.containers.slots.SlotCrafterEvaporatingFuel;
import com.mdcore.MysticalDiscoveries.tileentity.containers.slots.SlotCrafterEvaporatingOutput;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ContainerCrafterEvaporating extends Container{

	private final TileEntityCrafterEvaporating tileentity;
	private int craftingTime;
	private int currentCraftingTime;
	private int craftTime;
	private int totalCraftTime;
	
	public ContainerCrafterEvaporating(InventoryPlayer player, TileEntityCrafterEvaporating tileentity) {
		
		this.tileentity = tileentity;
		
		this.addSlotToContainer(new SlotCrafterEvaporatingFuel(tileentity, 0, 12, 43));
		this.addSlotToContainer(new SlotCrafterEvaporatingOutput(player.player, tileentity, 10, 139, 35));
		
		int index = 1;
		for (int y = 0; y < 3; y++) {
			for (int x = 0; x < 3; x++) {
				this.addSlotToContainer(new Slot(tileentity, index, 45 + x * 18, 17 + y * 18));
				index = index + 1;
			}
		}
		for (int y = 0; y < 3; y++) {
			for (int x = 0; x < 9; x++) {
				this.addSlotToContainer(new Slot(player, x + y * 9 + 9, 8 + x * 18, 84 + y * 18));
				index = index + 1;
			}
		}
		for (int x = 0; x < 9; x++) {
			this.addSlotToContainer(new Slot(player, x, 8 + x * 18, 142));
		}
	}
	
	@Override
	public void addListener(IContainerListener listener) 
	{
		super.addListener(listener);
		listener.sendAllWindowProperties(this, this.tileentity);
	}
	
	@Override
	public void detectAndSendChanges() 
	{
		super.detectAndSendChanges();
		
		for(int i = 0; i < this.listeners.size(); ++i) 
		{
			IContainerListener listener = (IContainerListener)this.listeners.get(i);
			
			if(this.craftTime != this.tileentity.getField(2)) listener.sendWindowProperty(this, 2, this.tileentity.getField(2));
			if(this.craftingTime != this.tileentity.getField(0)) listener.sendWindowProperty(this, 0, this.tileentity.getField(0));
			if(this.currentCraftingTime != this.tileentity.getField(1)) listener.sendWindowProperty(this, 1, this.tileentity.getField(1));
			if(this.totalCraftTime != this.tileentity.getField(3)) listener.sendWindowProperty(this, 3, this.tileentity.getField(3));
		}
		
		this.craftTime = this.tileentity.getField(2);
		this.craftingTime = this.tileentity.getField(0);
		this.currentCraftingTime = this.tileentity.getField(1);
		this.totalCraftTime = this.tileentity.getField(3);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void updateProgressBar(int id, int data) 
	{
		this.tileentity.setField(id, data);
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer playerIn) 
	{
		return this.tileentity.isUsableByPlayer(playerIn);
	}
	
	@Override
	public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) 
	{
		ItemStack stack = ItemStack.EMPTY;
		Slot slot = (Slot)this.inventorySlots.get(index);
		
		if(slot != null && slot.getHasStack()) {
			ItemStack stack1 = slot.getStack();
			stack = stack1.copy();
			
			if(index == 10) {
				if(!this.mergeItemStack(stack1, 4, 40, true)) return ItemStack.EMPTY;
				slot.onSlotChange(stack1, stack);
			}
			else if(index != 0 && index != 1 && index != 2 && index != 3 && index != 4 && index != 5 && index != 6 && index != 7 && index != 8 && index != 9) {		
				Slot slot1 = (Slot)this.inventorySlots.get(index + 1);
				Slot slot2 = (Slot)this.inventorySlots.get(index + 2);
				Slot slot3 = (Slot)this.inventorySlots.get(index + 3);
				Slot slot4 = (Slot)this.inventorySlots.get(index + 4);
				Slot slot5 = (Slot)this.inventorySlots.get(index + 5);
				Slot slot6 = (Slot)this.inventorySlots.get(index + 6);
				Slot slot7 = (Slot)this.inventorySlots.get(index + 7);
				Slot slot8 = (Slot)this.inventorySlots.get(index + 8);
				
				if(!CrafterEvaporatingRecipes.getInstance().getCraftingResult(stack1, slot1.getStack(), slot2.getStack(), slot3.getStack(), slot4.getStack(), slot5.getStack(), slot6.getStack(), slot7.getStack(), slot8.getStack()).isEmpty()) {
					if(!this.mergeItemStack(stack1, 0, 9, false)) {
						return ItemStack.EMPTY;
					}
					else if(TileEntityCrafterEvaporating.isItemFuel(stack1)) {
						if(!this.mergeItemStack(stack1, 0, 10, false)) return ItemStack.EMPTY;
					}
					else if(TileEntityCrafterEvaporating.isItemFuel(stack1))
					{
						if(!this.mergeItemStack(stack1, 0, 10, false)) return ItemStack.EMPTY;
					}
					else if(TileEntityCrafterEvaporating.isItemFuel(stack1))
					{
						if(!this.mergeItemStack(stack1, 0, 10, false)) return ItemStack.EMPTY;
					}
					else if(index >= 11 && index < 48)
					{
						if(!this.mergeItemStack(stack1, 48, 57, false)) return ItemStack.EMPTY;
					}
					else if(index >= 48 && index < 57 && !this.mergeItemStack(stack1, 11, 48, false))
					{
						return ItemStack.EMPTY;
					}
				}
			} 
			else if(!this.mergeItemStack(stack1, 11, 57, false)) 
			{
				return ItemStack.EMPTY;
			}
			if(stack1.isEmpty())
			{
				slot.putStack(ItemStack.EMPTY);
			}
			else
			{
				slot.onSlotChanged();

			}
			if(stack1.getCount() == stack.getCount()) return ItemStack.EMPTY;
			slot.onTake(playerIn, stack1);
		}
		return stack;
	}

}
