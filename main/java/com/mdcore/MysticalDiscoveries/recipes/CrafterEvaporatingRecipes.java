package com.mdcore.MysticalDiscoveries.recipes;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.google.common.collect.Maps;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import scala.actors.threadpool.Arrays;

public class CrafterEvaporatingRecipes {

	private static final CrafterEvaporatingRecipes INSTANCE = new CrafterEvaporatingRecipes();
	private final List<ItemStack[]> craftingList = new ArrayList<ItemStack[]> ();
	private final Map<ItemStack, Float> experienceList = Maps.<ItemStack, Float>newHashMap();
	
	public static CrafterEvaporatingRecipes getInstance()
	{
		return INSTANCE;
	}
	
	private CrafterEvaporatingRecipes() 
	{
		addCraftingRecipe(new ItemStack(Items.IRON_INGOT), new ItemStack(Items.IRON_INGOT), new ItemStack(Items.IRON_INGOT), ItemStack.EMPTY, new ItemStack(Items.STICK), ItemStack.EMPTY, ItemStack.EMPTY, new ItemStack(Items.STICK), ItemStack.EMPTY, new ItemStack(Items.IRON_PICKAXE), 2.0F);
	}

	
	public void addCraftingRecipe(ItemStack input1, ItemStack input2, ItemStack input3, ItemStack input4, ItemStack input5, ItemStack input6, ItemStack input7, ItemStack input8, ItemStack input9, ItemStack result, float experience) 
	{
		if(getCraftingResult(input1, input2, input3, input4, input5, input6, input7, input8, input9) != ItemStack.EMPTY) return;
		ItemStack[] recipe = new ItemStack[] {input1, input2, input3, input4, input5, input6, input7, input8, input9, result};
		this.craftingList.add(recipe);
		this.experienceList.put(result, Float.valueOf(experience));
	}
	
	public ItemStack getCraftingResult(ItemStack input1, ItemStack input2, ItemStack input3, ItemStack input4, ItemStack input5, ItemStack input6, ItemStack input7, ItemStack input8, ItemStack input9) 
	{
		for (int i = 0; i <= craftingList.size(); i++) {
			if (craftingList.get(i)[0].equals(input1) && craftingList.get(i)[1].equals(input2) && craftingList.get(i)[2].equals(input3) && craftingList.get(i)[3].equals(input4) && craftingList.get(i)[4].equals(input5) && craftingList.get(i)[5].equals(input6) && craftingList.get(i)[6].equals(input7) && craftingList.get(i)[7].equals(input8) && craftingList.get(i)[8].equals(input9)) {
				return craftingList.get(i)[9];
			}
		}
		return ItemStack.EMPTY;
	}
	
	private boolean compareItemStacks(ItemStack stack1, ItemStack stack2)
	{
		return stack2.getItem() == stack1.getItem() && (stack2.getMetadata() == 32767 || stack2.getMetadata() == stack1.getMetadata());
	}
	
	public List<ItemStack[]> getCraftingList() 
	{
		return this.craftingList;
	}
	
	public float getCraftingExperience(ItemStack stack)
	{
		for (Entry<ItemStack, Float> entry : this.experienceList.entrySet()) 
		{
			if(this.compareItemStacks(stack, (ItemStack)entry.getKey())) 
			{
				return ((Float)entry.getValue()).floatValue();
			}
		}
		return 0.0F;
	}

}
