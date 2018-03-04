package com.mdcore.MysticalDiscoveries.objects.items;

import java.util.List;
import java.util.Random;

import com.mdcore.MysticalDiscoveries.Main;
import com.mdcore.MysticalDiscoveries.init.ItemInit;
import com.mdcore.MysticalDiscoveries.util.interfaces.IHasModel;
import com.mojang.realmsclient.gui.ChatFormatting;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.ITickable;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;

public class DustEvaporium extends Item implements IHasModel,ITickable {
	
	private String name;
	private int evaporation = 0;

	public DustEvaporium(String name) {
		
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(Main.MDTab_Materials);
		
		this.maxStackSize = 16;
		
		ItemInit.ITEMS.add(this);
	}
	
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return ItemInit.DUST_EVAPORIUM;
    }
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add("Rarity: " + ChatFormatting.GREEN + "Uncommon");
		tooltip.add("");
		tooltip.add(ChatFormatting.AQUA + "A Core Ingredient In All Recipes.");
		tooltip.add("");
		tooltip.add("Description: A Material That Slowly Evaporates Over Time.");
		flagIn.isAdvanced();
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}
	
	public static boolean eatDebounce = false;
	public static int eatCooldown = 0;
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		if (playerIn != null) {
			if (playerIn.canEat(true) && eatDebounce == false && eatCooldown == 0) {
				eatDebounce = true;
				playerIn.sendMessage(new TextComponentString("You Taste This Strange Material And Start To Feel Dizzy."));
				playerIn.addPotionEffect(new PotionEffect(Potion.getPotionById(9), 200, 1));
				playerIn.addPotionEffect(new PotionEffect(Potion.getPotionById(17), 300, 0));
				playerIn.inventory.getCurrentItem().setCount(playerIn.inventory.getCurrentItem().getCount() - 1);
				evaporation = 0;
				eatCooldown = 100;
				eatDebounce = false;
			}
		}
		return super.onItemRightClick(worldIn, playerIn, handIn);
	}
	
	@Override
	public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
		EntityPlayer playerIn = Minecraft.getMinecraft().player;
		if (playerIn != null) {
			if (!playerIn.capabilities.isCreativeMode) {
				evaporation = evaporation + 1;
				eatCooldown = eatCooldown - 1;
				if (eatCooldown <= 0) eatCooldown = 0;
				if (evaporation >= 1000) {
					stack.setCount(stack.getCount() - 1);
					evaporation = 0;
				}
			}
		}
	}

	@Override
	public void tick() {}
	
	@Override
	public void registerModels() {
		
		Main.proxy.registerItemRenderer(this, 0, "inventory");
	}
	
}
