package md.zazpro.mod.handlers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.common.collect.ImmutableList;

import cpw.mods.fml.common.registry.GameRegistry;
import md.zazpro.mod.config.AddFoodStats;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.util.StatCollector;

public class AllFoodHandler 
{
	public void postInit(){
	
	}

	public static void addVanilaFood() {
		List<Item> list = ImmutableList.copyOf(Item.itemRegistry);
		for (int i=0; i<list.size(); i++)
		AddFoodStats.addFood(GameRegistry.findUniqueIdentifierFor(list.get(i)).toString(), 48000, GameRegistry.findUniqueIdentifierFor(Items.rotten_flesh).toString());
		
	}
}

