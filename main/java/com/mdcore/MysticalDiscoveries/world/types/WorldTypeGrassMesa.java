package com.mdcore.MysticalDiscoveries.world.types;

import com.mdcore.MysticalDiscoveries.init.BiomeInit;

import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraft.world.biome.BiomeProviderSingle;

public class WorldTypeGrassMesa extends WorldType{

	public WorldTypeGrassMesa() {
		super("grassy_mesa");
	}
	
	@Override
	public BiomeProvider getBiomeProvider(World world) {
		return new BiomeProviderSingle(BiomeInit.GRASSMESA);
	}
}
