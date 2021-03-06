package com.svennieke.statues.init;

import com.svennieke.statues.items.ItemCup;
import com.svennieke.statues.items.ItemMooshroomSoup;
import com.svennieke.statues.items.ItemRoyalNugget;
import com.svennieke.statues.items.ItemStatueCore;
import com.svennieke.statues.items.ItemTea;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class StatuesItems {
	
	public static ItemFood nugget, soup, tea, cup;
	public static Item core;
	
	public static void init(){
		nugget = new ItemRoyalNugget(4, 0.1f, false);
		soup = new ItemMooshroomSoup(6, 0.3F, false);
		core = new ItemStatueCore();
		tea = new ItemTea();
		cup = new ItemCup(1, 0.2F, false);
		}
	
	public static void register()
	{
		ForgeRegistries.ITEMS.register(nugget);
		ForgeRegistries.ITEMS.register(soup);
		ForgeRegistries.ITEMS.register(core);
		ForgeRegistries.ITEMS.register(tea);
		ForgeRegistries.ITEMS.register(cup);
	}
	
	public static void registerRenders()
	{
		registerRender(nugget);
		registerRender(soup);
		registerRender(core);
		registerRender(tea);
		registerRender(cup);
	}
	
	public static void registerRender(Item item)
	{
		ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
	}
}
