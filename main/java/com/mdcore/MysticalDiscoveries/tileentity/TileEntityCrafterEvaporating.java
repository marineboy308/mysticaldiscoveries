package com.mdcore.MysticalDiscoveries.tileentity;

import java.util.ArrayList;
import java.util.List;

import com.mdcore.MysticalDiscoveries.init.ItemInit;
import com.mdcore.MysticalDiscoveries.objects.blocks.CrafterEvaporating;
import com.mdcore.MysticalDiscoveries.recipes.CrafterEvaporatingRecipes;

import net.minecraft.client.renderer.texture.ITickable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TileEntityCrafterEvaporating extends TileEntity implements IInventory,ITickable {

	private NonNullList<ItemStack> inventory = NonNullList.<ItemStack>withSize(11, ItemStack.EMPTY);
	private String customName;
	
	private int craftingTime;
	private int currentCraftingTime;
	private int craftTime;
	private int totalCraftTime;
	
	@Override
	public String getName() 
	{
		return this.hasCustomName() ? this.customName : "container.crafter_evaporating";
	}

	@Override
	public boolean hasCustomName() 
	{
		return this.customName != null && !this.customName.isEmpty();
	}
	
	public void setCustomName(String customName) 
	{
		this.customName = customName;
	}
	
	@Override
	public ITextComponent getDisplayName() 
	{
		return this.hasCustomName() ? new TextComponentString(this.getName()) : new TextComponentTranslation(this.getName());
	}

	@Override
	public int getSizeInventory() 
	{
		return this.inventory.size();
	}

	@Override
	public boolean isEmpty() 
	{
		for(ItemStack stack : this.inventory)
		{
			if(!stack.isEmpty()) return false;
		}
		return true;
	}

	@Override
	public ItemStack getStackInSlot(int index)
	{
		return (ItemStack)this.inventory.get(index);
	}

	@Override
	public ItemStack decrStackSize(int index, int count) 
	{
		return ItemStackHelper.getAndSplit(this.inventory, index, count);
	}

	@Override
	public ItemStack removeStackFromSlot(int index) 
	{
		return ItemStackHelper.getAndRemove(this.inventory, index);
	}

	@Override
	public void setInventorySlotContents(int index, ItemStack stack) 
	{
		ItemStack itemstack = (ItemStack)this.inventory.get(index);
		boolean flag = !stack.isEmpty() && stack.isItemEqual(itemstack) && ItemStack.areItemStackTagsEqual(stack, itemstack);
		this.inventory.set(index, stack);
		
		if(stack.getCount() > this.getInventoryStackLimit()) stack.setCount(this.getInventoryStackLimit());
		if(index == 1 && index + 1 == 2 && index + 2 == 3 && index + 3 == 4 && index + 4 == 5 && index + 5 == 6 && index + 6 == 7 && index + 7 == 8 && index + 8 == 9 && !flag)
		{
			System.out.println(this.inventory.get(index).getDisplayName() + ":" + index + ": x" + this.inventory.get(index).getCount());
			
			ItemStack stack1 = (ItemStack)this.inventory.get(index + 1);
			ItemStack stack2 = (ItemStack)this.inventory.get(index + 2);
			ItemStack stack3 = (ItemStack)this.inventory.get(index + 3);
			ItemStack stack4 = (ItemStack)this.inventory.get(index + 4);
			ItemStack stack5 = (ItemStack)this.inventory.get(index + 5);
			ItemStack stack6 = (ItemStack)this.inventory.get(index + 6);
			ItemStack stack7 = (ItemStack)this.inventory.get(index + 7);
			ItemStack stack8 = (ItemStack)this.inventory.get(index + 8);
			this.totalCraftTime = this.getCraftTime(stack, stack1, stack2, stack3, stack4, stack5, stack6, stack7, stack8);
			this.craftTime = 0;
			this.markDirty();
		}
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound)
	{
		super.readFromNBT(compound);
		this.inventory = NonNullList.<ItemStack>withSize(this.getSizeInventory(), ItemStack.EMPTY);
		ItemStackHelper.loadAllItems(compound, this.inventory);
		this.craftingTime = compound.getInteger("CraftingTime");
		this.craftTime = compound.getInteger("CraftTime");
		this.totalCraftTime = compound.getInteger("CraftTimeTotal");
		this.currentCraftingTime = getItemCraftingTime((ItemStack)this.inventory.get(0));
		
		if(compound.hasKey("CustomName", 8)) this.setCustomName(compound.getString("CustomName"));
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) 
	{
		super.writeToNBT(compound);
		compound.setInteger("CraftingTime", (short)this.craftingTime);
		compound.setInteger("CraftTime", (short)this.craftTime);
		compound.setInteger("CraftTimeTotal", (short)this.totalCraftTime);
		ItemStackHelper.saveAllItems(compound, this.inventory);
		
		if(this.hasCustomName()) compound.setString("CustomName", this.customName);
		return compound;
	}

	@Override
	public int getInventoryStackLimit() 
	{
		return 64;
	}
	
	public boolean isCrafting() 
	{
		return this.craftingTime > 0;
	}
	
	@SideOnly(Side.CLIENT)
	public static boolean isCrafting(IInventory inventory) 
	{
		return inventory.getField(0) > 0;
	}
	
	public void update() 
	{
		System.out.println("Updating");
		boolean flag = this.isCrafting();
		boolean flag1 = false;
		
		if(this.isCrafting()) --this.craftingTime;
		
		if(!this.world.isRemote) 
		{
			ItemStack stack = (ItemStack)this.inventory.get(0);
			
			if (this.isCrafting() || !stack.isEmpty() && !((((ItemStack)this.inventory.get(1)).isEmpty()) && (((ItemStack)this.inventory.get(2)).isEmpty()) && (((ItemStack)this.inventory.get(3)).isEmpty()) && (((ItemStack)this.inventory.get(4)).isEmpty()) && (((ItemStack)this.inventory.get(5)).isEmpty()) && (((ItemStack)this.inventory.get(6)).isEmpty()) && (((ItemStack)this.inventory.get(7)).isEmpty()) && (((ItemStack)this.inventory.get(8)).isEmpty()) && (((ItemStack)this.inventory.get(9)).isEmpty())))
			{
				if(!this.isCrafting() && this.canEvaporate()) 
				{
					this.craftingTime = getItemCraftingTime(stack);
					this.currentCraftingTime = this.craftingTime;
					
					if(this.isCrafting()) 
					{
						flag1 = true;
						
						if(!stack.isEmpty()) 
						{
							Item item = stack.getItem();
							stack.shrink(1);
							
							if(stack.isEmpty()) 
							{
								ItemStack item1 = item.getContainerItem(stack);
								this.inventory.set(0, item1);
							}
						}
					}
				} 
				if(this.isCrafting() && this.canEvaporate()) 
				{
					++this.craftTime;
					
					if(this.craftTime == this.totalCraftTime) 
					{
						this.craftTime = 0;
						this.totalCraftTime = this.getCraftTime((ItemStack)this.inventory.get(1), (ItemStack)this.inventory.get(2), (ItemStack)this.inventory.get(3), (ItemStack)this.inventory.get(4), (ItemStack)this.inventory.get(5), (ItemStack)this.inventory.get(6), (ItemStack)this.inventory.get(7), (ItemStack)this.inventory.get(8), (ItemStack)this.inventory.get(9));
						this.craftItem();
						flag1 = true;
					}
				} 
				else this.craftTime = 0;
			} 
			else if(!this.isCrafting() && this.craftTime > 0) 
			{
				this.craftTime = MathHelper.clamp(this.craftTime - 2, 0, this.totalCraftTime);
			}
			if(flag != this.isCrafting()) 
			{
				flag1 = true;
				CrafterEvaporating.setState(this.isCrafting(), this.world, this.pos);
			}
		} 
		if(flag1) this.markDirty();
	}
	
	public int getCraftTime(ItemStack input1, ItemStack input2, ItemStack input3, ItemStack input4, ItemStack input5, ItemStack input6, ItemStack input7, ItemStack input8, ItemStack input9) 
	{
		int amountOfFullSlots = 0;
		List<ItemStack> inputs = new ArrayList<ItemStack>();
		inputs.add(input1);
		inputs.add(input2);
		inputs.add(input3);
		inputs.add(input4);
		inputs.add(input5);
		inputs.add(input6);
		inputs.add(input7);
		inputs.add(input8);
		inputs.add(input9);
		for (int i = 0;  i < inputs.size(); i++) {
			if (!(inputs.get(i) == ItemStack.EMPTY || inputs.get(i) == new ItemStack(Items.AIR))) {
				System.out.println("'getCraftTime'--" + inputs.get(i).getDisplayName() + ":" + i + ": x" + inputs.get(i).getCount());;
				amountOfFullSlots = amountOfFullSlots + 1;		
			}
		}
		return amountOfFullSlots * 100;
	}
	
	private boolean canEvaporate() 
	{
		if (((((ItemStack)this.inventory.get(1)).isEmpty()) && (((ItemStack)this.inventory.get(2)).isEmpty()) && (((ItemStack)this.inventory.get(3)).isEmpty()) && (((ItemStack)this.inventory.get(4)).isEmpty()) && (((ItemStack)this.inventory.get(5)).isEmpty()) && (((ItemStack)this.inventory.get(6)).isEmpty()) && (((ItemStack)this.inventory.get(7)).isEmpty()) && (((ItemStack)this.inventory.get(8)).isEmpty()) && (((ItemStack)this.inventory.get(9)).isEmpty()))) return false;
		else 
		{
			ItemStack result = CrafterEvaporatingRecipes.getInstance().getCraftingResult((ItemStack)this.inventory.get(1), (ItemStack)this.inventory.get(2), (ItemStack)this.inventory.get(3), (ItemStack)this.inventory.get(4), (ItemStack)this.inventory.get(5), (ItemStack)this.inventory.get(6), (ItemStack)this.inventory.get(7), (ItemStack)this.inventory.get(8), (ItemStack)this.inventory.get(9));	
			if(result.isEmpty()) return false;
			else
			{
				ItemStack output = (ItemStack)this.inventory.get(10);
				if(output.isEmpty()) return true;
				if(!output.isItemEqual(result)) return false;
				int res = output.getCount() + result.getCount();
				return res <= getInventoryStackLimit() && res <= output.getMaxStackSize();
			}
		}
	}
	
	public void craftItem() 
	{
		System.out.println("Etempting To Craft Item.");
		if(this.canEvaporate()) 
		{
			System.out.println("Getting Crafting Result.");
			ItemStack input1 = (ItemStack)this.inventory.get(1);
			ItemStack input2 = (ItemStack)this.inventory.get(2);
			ItemStack input3 = (ItemStack)this.inventory.get(3);
			ItemStack input4 = (ItemStack)this.inventory.get(4);
			ItemStack input5 = (ItemStack)this.inventory.get(5);
			ItemStack input6 = (ItemStack)this.inventory.get(6);
			ItemStack input7 = (ItemStack)this.inventory.get(7);
			ItemStack input8 = (ItemStack)this.inventory.get(8);
			ItemStack input9 = (ItemStack)this.inventory.get(9);
			ItemStack result = CrafterEvaporatingRecipes.getInstance().getCraftingResult(input1, input2, input3, input4, input5, input6, input7, input8, input9);
			ItemStack output = (ItemStack)this.inventory.get(10);
			
			if(output.isEmpty()) this.inventory.set(3, result.copy());
			else if(output.getItem() == result.getItem()) output.grow(result.getCount());
			
			input1.shrink(1);
			input2.shrink(1);
			input3.shrink(1);
			input4.shrink(1);
			input5.shrink(1);
			input6.shrink(1);
			input7.shrink(1);
			input8.shrink(1);
			input9.shrink(1);
		}
	}
	
	public static int getItemCraftingTime(ItemStack fuel) 
	{
		if(fuel.isEmpty()) return 0;
		else 
		{
			Item item = fuel.getItem();

			if (item == ItemInit.DUST_EVAPORIUM) return 200;
		}
		return 0;
	}
		
	public static boolean isItemFuel(ItemStack fuel)
	{
		return getItemCraftingTime(fuel) > 0;
	}
	
	@Override
	public boolean isUsableByPlayer(EntityPlayer player) 
	{
		return this.world.getTileEntity(this.pos) != this ? false : player.getDistanceSq((double)this.pos.getX() + 0.5D, (double)this.pos.getY() + 0.5D, (double)this.pos.getZ() + 0.5D) <= 64.0D;
	}

	@Override
	public void openInventory(EntityPlayer player) {}

	@Override
	public void closeInventory(EntityPlayer player) {}

	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack) 
	{
		
		if(index == 10) return false;
		else if(index != 0) return true;
		else 
		{
			return isItemFuel(stack);
		}
	}
	
	public String getGuiID() 
	{
		return "mdcore:crafter_evaporating_gui";
	}

	@Override
	public int getField(int id) 
	{
		switch(id) 
		{
		case 0:
			return this.craftingTime;
		case 1:
			return this.currentCraftingTime;
		case 2:
			return this.craftTime;
		case 3:
			return this.totalCraftTime;
		default:
			return 0;
		}
	}

	@Override
	public void setField(int id, int value) 
	{
		switch(id) 
		{
		case 0:
			this.craftingTime = value;
			break;
		case 1:
			this.currentCraftingTime = value;
			break;
		case 2:
			this.craftTime = value;
			break;
		case 3:
			this.totalCraftTime = value;
		}
	}

	@Override
	public int getFieldCount() 
	{
		return 4;
	}

	@Override
	public void clear() 
	{
		this.inventory.clear();
	}

	@Override
	public void tick() {
		update();
	}

}
