package com.mdcore.MysticalDiscoveries.objects.blocks;

import java.util.Random;

import com.mdcore.MysticalDiscoveries.Main;
import com.mdcore.MysticalDiscoveries.init.BlockInit;
import com.mdcore.MysticalDiscoveries.tileentity.TileEntityCrafterEvaporating;
import com.mdcore.MysticalDiscoveries.util.Reference;

import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class CrafterEvaporating extends BlockBase implements ITileEntityProvider{
	
	public static final PropertyDirection FACING = BlockHorizontal.FACING;
	public static final PropertyBool CRAFTING = PropertyBool.create("crafting");

	public CrafterEvaporating(String name, Material material, SoundType soundtype, Float hardness, Float resistance) {
		super(name, material);
		setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(CRAFTING, false));
		setCreativeTab(Main.MDTab_Machines);
		setSoundType(soundtype);
		setHardness(hardness);
		setResistance(resistance);
	}
	
	@Override
	public boolean isFullBlock(IBlockState state) {
		return false;
	}
	
	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}
	
	@Override
	public boolean canPlaceTorchOnTop(IBlockState state, IBlockAccess world, BlockPos pos) {
		return true;
	}
	
	@Override
	public boolean doesSideBlockRendering(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing face) {
		return false;
	}
	
	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return Item.getItemFromBlock(BlockInit.EVAPORATING_CRAFTER);
	}
	
	@Override
	public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state) {
		return new ItemStack(BlockInit.EVAPORATING_CRAFTER);
	}
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (!worldIn.isRemote) {
			playerIn.openGui(Main.instance, Reference.GUI_ID_EVAPORATING_CRAFTER, worldIn, pos.getX(), pos.getY(), pos.getZ());
		}
		return true;
	}
	
	public static void setState(boolean isActive, World worldIn, BlockPos pos) {
		IBlockState state = worldIn.getBlockState(pos);
		TileEntity tileentity = worldIn.getTileEntity(pos);
		if (isActive) {
			worldIn.setBlockState(pos, BlockInit.EVAPORATING_CRAFTER.getDefaultState().withProperty(FACING, state.getValue(FACING)).withProperty(CRAFTING, true),3);
		} else {
			worldIn.setBlockState(pos, BlockInit.EVAPORATING_CRAFTER.getDefaultState().withProperty(FACING, state.getValue(FACING)).withProperty(CRAFTING, false),3);
		}
		if (tileentity != null) {
			tileentity.validate();
			worldIn.setTileEntity(pos, tileentity);
		}
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityCrafterEvaporating();
	}
	
	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] {CRAFTING,FACING});
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return ((EnumFacing)state.getValue(FACING)).getHorizontalIndex();
	}

	 @Override
	 public IBlockState getStateFromMeta(int meta) {
	 	EnumFacing enumfacing = EnumFacing.getHorizontal(meta);

	 	if (enumfacing.getAxis() == EnumFacing.Axis.Y) {
	    	enumfacing = EnumFacing.NORTH;
	    }

	    return this.getDefaultState().withProperty(FACING, enumfacing);
	 }

	 @Override
	 public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
		 worldIn.setBlockState(pos, state.withProperty(FACING, placer.getHorizontalFacing().getOpposite()), 2);
	 }
	 
	 @Override
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
		 TileEntityCrafterEvaporating tileentity = (TileEntityCrafterEvaporating)worldIn.getTileEntity(pos);
		 InventoryHelper.dropInventoryItems(worldIn, pos, tileentity);
		 super.breakBlock(worldIn, pos, state);
	}

	 @Override
	 public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
		 return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite());
	 }
	 
	 @Override
	 public EnumBlockRenderType getRenderType(IBlockState state) {
		 return EnumBlockRenderType.MODEL;
	 }
	 
	 @Override
	 public IBlockState withRotation(IBlockState state, Rotation rot) {
		 return state.withProperty(FACING, rot.rotate((EnumFacing)state.getValue(FACING)));
	 }
	 
	 @Override
	 public IBlockState withMirror(IBlockState state, Mirror mirrorIn) {
		 return state.withRotation(mirrorIn.toRotation((EnumFacing)state.getValue(FACING)));
	 }
}
