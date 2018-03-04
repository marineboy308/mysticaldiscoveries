package com.mdcore.MysticalDiscoveries;

import org.apache.logging.log4j.Logger;

import com.mdcore.MysticalDiscoveries.proxy.CommonProxy;
import com.mdcore.MysticalDiscoveries.util.Reference;
import com.mdcore.MysticalDiscoveries.util.handlers.RegistryHandler;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Reference.MOD_ID, name = Reference.NAME, version = Reference.VERSION)
public class Main {

	@Instance
	public static Main instance;
	
	@SidedProxy(clientSide = Reference.CLIENT, serverSide = Reference.COMMON)
	public static CommonProxy proxy;
	
	public static final CreativeTabs MDTab_All = new MDTabMaterials("mystical_discoveries");
	public static final CreativeTabs MDTab_Materials = new MDTabMaterials("mystical_discoveries_materials");
	public static final CreativeTabs MDTab_Machines = new MDTabMachines("mystical_discoveries_machines");
	
	public static Logger logger;
	
	@EventHandler
	public static void preInit(FMLPreInitializationEvent event) {
		logger = event.getModLog();
		RegistryHandler.perInitRegistries();
	}
	
	@EventHandler
	public static void init(FMLInitializationEvent event) {
		logger.info("Mod Initalizing");
		RegistryHandler.initRegistries();
	}
	
	@EventHandler
	public static void postInit(FMLPostInitializationEvent event) {
		RegistryHandler.postInitRegistries();
		logger.info(Reference.NAME + " has finished initalizing. Enjoy the mod :)");
	}
	
}
