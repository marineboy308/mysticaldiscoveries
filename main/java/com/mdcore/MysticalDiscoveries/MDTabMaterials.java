package com.mdcore.MysticalDiscoveries;

import com.mdcore.MysticalDiscoveries.init.ItemInit;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class MDTabMaterials extends CreativeTabs {
	
	public MDTabMaterials(String label) { 
		super(label);
	}
	
	public ItemStack getTabIconItem() {
		return new ItemStack(ItemInit.DUST_EVAPORIUM);
	}
}