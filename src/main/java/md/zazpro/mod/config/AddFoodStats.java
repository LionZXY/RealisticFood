package md.zazpro.mod.config;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Loader;
import net.minecraft.crash.CrashReport;
import net.minecraftforge.common.config.Property;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

import cpw.mods.fml.common.Loader;
import md.zazpro.mod.handlers.AllFoodHandler;

public class AddFoodStats {

	static File jsonFile = new File(Loader.instance().getConfigDir() + "/RealisticFood", "FoodStats.json");
    	public static JsonArray mainJson = new JsonArray();

    static public void addFoodStats() {
	load();
        AllFoodHandler.addVanilaFood();
        save();
    }
    
    static public void addFood(String foodName, int heath, String exitName	) {
        JsonObject obj = new JsonObject();
        obj.addProperty("Food name", foodName);
        obj.addProperty("Root time", heath);
        obj.addProperty("Compost name", exitName);
        mainJson.add(obj);
    }

    public static void save() {

        if (!jsonFile.canWrite()) {
            try {
                jsonFile.getParentFile().mkdirs();
                jsonFile.createNewFile();
            } catch (Exception e) {
                FMLLog.bigWarning("Can't create json mod config!");
                e.printStackTrace();
            }
        }

        try {
            FileOutputStream os = new FileOutputStream(jsonFile);
            os.write(getFormatedText(mainJson.toString()).getBytes());
            os.close();
        } catch (Exception e) {
            FMLLog.bigWarning("Can't save json mod config!");
            e.printStackTrace();
        }
    }

    public static void load() {

        if (!jsonFile.canWrite()) {
            try {
                jsonFile.getParentFile().mkdirs();
                jsonFile.createNewFile();
            } catch (Exception e) {
                FMLLog.bigWarning("Can't create json mod config!");
                e.printStackTrace();
            }
        }
        System.out.println(jsonFile.getAbsoluteFile());
        try {
            mainJson = new JsonParser().parse(new FileReader(jsonFile)).getAsJsonObject();
        } catch (Exception e) {
            FMLLog.bigWarning("Can't load json mod config!");
            e.printStackTrace();
        }
    }

}
