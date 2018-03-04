package com.mdcore.MysticalDiscoveries.init;

import java.util.ArrayList;
import java.util.List;

import com.mdcore.MysticalDiscoveries.objects.items.DustEvaporium;

import net.minecraft.item.Item;

public class ItemInit {

	public static final List<Item> ITEMS = new ArrayList<Item>();
	
	public static final Item DUST_EVAPORIUM = new DustEvaporium("dust_evaporium");
}
