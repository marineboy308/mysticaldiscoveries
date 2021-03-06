package com.mdcore.MysticalDiscoveries.util.handlers;

import com.mdcore.MysticalDiscoveries.tileentity.TileEntityCrafterEvaporating;
import com.mdcore.MysticalDiscoveries.tileentity.containers.ContainerCrafterEvaporating;
import com.mdcore.MysticalDiscoveries.tileentity.guis.GuiCrafterEvaporating;
import com.mdcore.MysticalDiscoveries.util.Reference;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler {

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if (ID == Reference.GUI_ID_EVAPORATING_CRAFTER) return new ContainerCrafterEvaporating(player.inventory, (TileEntityCrafterEvaporating)world.getTileEntity(new BlockPos(x,y,z)));
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if (ID == Reference.GUI_ID_EVAPORATING_CRAFTER) return new GuiCrafterEvaporating(player.inventory, (TileEntityCrafterEvaporating)world.getTileEntity(new BlockPos(x,y,z)));
		return null;
	}

}
