package com.mdcore.MysticalDiscoveries.init;

import com.mdcore.MysticalDiscoveries.world.biomes.BiomeGrassMesa;

import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.BiomeManager.BiomeEntry;
import net.minecraftforge.common.BiomeManager.BiomeType;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class BiomeInit {

	public static final Biome GRASSMESA = new BiomeGrassMesa();
	
	public static void registerBiomes() {
		initBiome(GRASSMESA, "Grass Mesa", BiomeType.WARM, Type.DENSE, Type.DRY, Type.MESA, Type.LUSH, Type.WET, Type.RIVER, Type.SAVANNA, Type.SANDY);
	}
	
	public static Biome initBiome(Biome biome, String name, BiomeType biometype, Type... types) {
		biome.setRegistryName(name);
		ForgeRegistries.BIOMES.register(biome);
		System.out.println("Biome " + name + " Has Been Registered.");
		BiomeDictionary.addTypes(biome, types);
		BiomeManager.addBiome(biometype, new BiomeEntry(biome, 10));
		BiomeManager.addSpawnBiome(biome);
		System.out.println("Biome " + name + " Has Been Added.");
		return biome;
	}
}
