package com.mdcore.MysticalDiscoveries;

import com.mdcore.MysticalDiscoveries.init.ItemInit;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class MDTabAll extends CreativeTabs {
	
	public MDTabAll(String label) { 
		super(label);
	}
	
	public ItemStack getTabIconItem() {
		return new ItemStack(ItemInit.DUST_EVAPORIUM);
	}
}