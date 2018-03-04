package com.mdcore.MysticalDiscoveries.world.biomes.biomegenerator;

import java.util.Random;

import com.mdcore.MysticalDiscoveries.Main;

import net.minecraft.block.BlockDirt;
import net.minecraft.block.BlockSand;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;

public class CustomBiomeTerrain extends Biome {
	
	public CustomBiomeTerrain(BiomeProperties properties) {
		super(properties);
	}
	
	public IBlockState usualTopBlock = Blocks.GRASS.getDefaultState();
	public IBlockState alternativeTopBlock = Blocks.DIRT.getDefaultState().withProperty(BlockDirt.VARIANT, BlockDirt.DirtType.COARSE_DIRT);
	
	public IBlockState normalFillerBlock = Blocks.DIRT.getDefaultState();
	public IBlockState scatteredFillerBlock = Blocks.STONE.getDefaultState();
	
	public IBlockState underBlock = Blocks.STONE.getDefaultState();
	
	@Override
	public void genTerrainBlocks(World worldIn, Random rand, ChunkPrimer chunkPrimerIn, int x, int z, double noiseVal)
    {
		int topType = rand.nextInt(5);
    	if (topType <= 3) {
    		topBlock = this.usualTopBlock;
    	} else {
    		topBlock = this.alternativeTopBlock;
    	}
    	
    	int fillerType = rand.nextInt(7);
    	if (fillerType <= 4) {
    		fillerBlock = this.normalFillerBlock;
    	} else {
    		fillerBlock = this.scatteredFillerBlock;
    	}
    	
    	generateBiomeTerrain(worldIn, rand, chunkPrimerIn, x, z, noiseVal);
    }
	
}
