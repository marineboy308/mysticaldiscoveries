package com.mdcore.MysticalDiscoveries.objects.blocks;

import com.mdcore.MysticalDiscoveries.init.BlockInit;
import com.mdcore.MysticalDiscoveries.init.ItemInit;
import com.mdcore.MysticalDiscoveries.util.interfaces.IHasModel;

import java.util.Random;

import com.mdcore.MysticalDiscoveries.Main;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class StoneEvaporium extends Block implements IHasModel {
	
	public StoneEvaporium(String name) {
		super(Material.ROCK);
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(Main.MDTab_Materials);
		
		setHardness(2.5F);
		setResistance(12.0F);

		BlockInit.BLOCKS.add(this);
		ItemInit.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
	}
	
	@Override
	public int getExpDrop(IBlockState state, IBlockAccess world, BlockPos pos, int fortune) {
		int xp = 3;
		if (fortune > 1) xp = xp * fortune;
		return xp;
	}
	
	@Override
	public int quantityDroppedWithBonus(int fortune, Random random) {
		int count = random.nextInt(5);
		if (count < 2) count = 2;
		if (fortune > 1) count = count * fortune;
		return count;
	}
	
	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return ItemInit.DUST_EVAPORIUM;
	}

	@Override
	public void registerModels() {
		Main.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
	}
}