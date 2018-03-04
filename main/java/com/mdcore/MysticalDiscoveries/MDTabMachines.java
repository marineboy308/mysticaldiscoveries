package com.mdcore.MysticalDiscoveries;

import com.mdcore.MysticalDiscoveries.init.BlockInit;
import com.mdcore.MysticalDiscoveries.init.ItemInit;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class MDTabMachines extends CreativeTabs {
	
	public MDTabMachines(String label) { 
		super(label);
	}
	
	public ItemStack getTabIconItem() {
		return new ItemStack(BlockInit.EVAPORATING_CRAFTER);
	}
}