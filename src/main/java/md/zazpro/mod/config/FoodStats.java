package md.zazpro.mod.config;

public class FoodStats {
	private String foodName,exitName;
	private int heath;
	
	public FoodStats(String name, String foodName, int heath, String exitName)
	{
		this.foodName   = JsonConfig.get(name, "Food name", foodName).getString();
		this.heath 		= JsonConfig.get(name, "Root time", 48000).getInt();
		this.exitName   = JsonConfig.get(name, "Compost name", "minecraft:rottenflesh").getString();
	}

}
