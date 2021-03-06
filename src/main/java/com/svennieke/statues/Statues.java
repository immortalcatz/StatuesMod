package com.svennieke.statues;

import java.time.LocalDateTime;
import java.time.Month;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.svennieke.statues.config.StatuesConfigGen;
import com.svennieke.statues.entity.EntityStatueBat;
import com.svennieke.statues.handler.DropHandler;
import com.svennieke.statues.init.StatuesBlocks;
import com.svennieke.statues.init.StatuesEntity;
import com.svennieke.statues.init.StatuesHoliday;
import com.svennieke.statues.init.StatuesItems;
import com.svennieke.statues.init.StatuesSounds;
import com.svennieke.statues.proxy.CommonProxy;

import net.minecraft.entity.EnumCreatureType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.SpawnListEntry;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Reference.MOD_ID, 
	name = Reference.MOD_NAME, 
	version = Reference.VERSION, 
	acceptedMinecraftVersions = Reference.ACCEPTED_VERSIONS,
	dependencies = Reference.DEPENDENCIES)

public class Statues {

	@Instance(Reference.MOD_ID)
	public static Statues instance;
	
	@SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
	public static CommonProxy proxy;
	
	public static final Logger logger = LogManager.getLogger(Reference.MOD_ID);
	public static boolean isBaublesEnabled = false;
	public static boolean isVeinminerInstalled = false;
		
	public static StatuesTab tabStatues = new StatuesTab();
	
	@EventHandler
	public void PreInit(FMLPreInitializationEvent event)
	{
		isVeinminerInstalled = Loader.isModLoaded("veinminer");
		
		isBaublesEnabled = Loader.isModLoaded("baubles");
		if(isBaublesEnabled)logger.info("Loading With Baubles Compat");
		else{logger.info("Loading Without Baubles Compat");}
		
		MinecraftForge.EVENT_BUS.register(new StatuesConfigGen());
		
		StatuesSounds.registerSounds();
		
		StatuesEntity.register();
		StatuesBlocks.init();
		StatuesBlocks.register();
		StatuesItems.init();
		StatuesItems.register();

		//StatuesCrafting.register();
		
		proxy.Preinit();
	}
	
	@EventHandler
    public void init(FMLInitializationEvent event)
	{
		if(StatuesConfigGen.general.Tier1Crafting == true)
		{
			for (Biome biome : Biome.REGISTRY) {
			    biome.getSpawnableList(EnumCreatureType.AMBIENT).add(new SpawnListEntry(EntityStatueBat.class, 4, 1, 2));
			}
			System.out.println("Registered Statues Bat Spawn");
		}
		StatuesHoliday.registerSpawning();
		
		proxy.Init();
		MinecraftForge.EVENT_BUS.register(new DropHandler());
    }
	
	@EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
		
    }
}
