package com.mdcore.MysticalDiscoveries.util.handlers;

import com.mdcore.MysticalDiscoveries.Main;
import com.mdcore.MysticalDiscoveries.init.BiomeInit;
import com.mdcore.MysticalDiscoveries.init.BlockInit;
import com.mdcore.MysticalDiscoveries.init.ItemInit;
import com.mdcore.MysticalDiscoveries.util.interfaces.IHasModel;
import com.mdcore.MysticalDiscoveries.world.gen.WorldGenCustomOres;
import com.mdcore.MysticalDiscoveries.world.types.WorldTypeGrassMesa;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.world.WorldType;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

@EventBusSubscriber
public class RegistryHandler {

	@SubscribeEvent
	public static void onItemRegister(RegistryEvent.Register<Item> event) {
		
		event.getRegistry().registerAll(ItemInit.ITEMS.toArray(new Item[0]));
	}
	
	@SubscribeEvent
	public static void onBlockRegister(RegistryEvent.Register<Block> event) {
		
		event.getRegistry().registerAll(BlockInit.BLOCKS.toArray(new Block[0]));
		TileEntityHandler.registerTileEntities();
	}
	
	@SubscribeEvent
	public static void onModelRegister(ModelRegistryEvent event) {
		
		for(Item item : ItemInit.ITEMS) {
			
			if(item instanceof IHasModel) {
				
				((IHasModel)item).registerModels();
			}
		}
		
		for(Block block : BlockInit.BLOCKS) {
			
			if(block instanceof IHasModel) {
				
				((IHasModel)block).registerModels();
			}
		}
	}
	
	public static void perInitRegistries() {
		GameRegistry.registerWorldGenerator(new WorldGenCustomOres(), 0);
		
		BiomeInit.registerBiomes();
	}
	
	public static void initRegistries() {
		NetworkRegistry.INSTANCE.registerGuiHandler(Main.instance, new GuiHandler());
	}
	
	public static void postInitRegistries() {
		WorldType GRASSYMESA = new WorldTypeGrassMesa();
	}
}
