package com.mdcore.MysticalDiscoveries.util.handlers;

import com.mdcore.MysticalDiscoveries.tileentity.TileEntityCrafterEvaporating;

import net.minecraftforge.fml.common.registry.GameRegistry;

public class TileEntityHandler {

	public static void registerTileEntities() {
		GameRegistry.registerTileEntity(TileEntityCrafterEvaporating.class, "crafter_evaporating");
	}
}
