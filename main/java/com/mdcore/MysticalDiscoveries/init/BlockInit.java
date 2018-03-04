package com.mdcore.MysticalDiscoveries.init;

import java.util.ArrayList;
import java.util.List;

import com.mdcore.MysticalDiscoveries.Main;
import com.mdcore.MysticalDiscoveries.objects.blocks.CrafterEvaporating;
import com.mdcore.MysticalDiscoveries.objects.blocks.StoneEvaporium;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class BlockInit {

	public static final List<Block> BLOCKS = new ArrayList<Block>();
	
	//Machines
	public static final Block EVAPORATING_CRAFTER = new CrafterEvaporating("crafter_evaporating", Material.ROCK, SoundType.STONE, 2.5F, 10.0F);
	
	//Blocks
	
	//Ores
	public static final Block EVAPORIUMSTONE = new StoneEvaporium("stone_evaporium");
}
