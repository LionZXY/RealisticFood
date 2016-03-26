package md.zazpro.mod.config;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

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
        try {
            try {
                if (!jsonFile.canWrite()) {
                    jsonFile.getParentFile().mkdirs();
                    jsonFile.createNewFile();
                    AllFoodHandler.addVanilaFood();
                    FileOutputStream os = new FileOutputStream(jsonFile);
                    os.write(JsonConfig.getFormatedText(mainJson.toString()).getBytes());
                    os.close();

                } else {
                    mainJson = new JsonParser().parse(new JsonReader(new FileReader(jsonFile))).getAsJsonArray();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            for (JsonElement obj2 : mainJson) {
              //  SimplyHammer.hammers.add(addHammerFromJsonObject(obj2.getAsJsonObject()));
            }
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
    }
    
    static public void addFood(String foodName, int heath, String exitName	) {
        JsonObject obj = new JsonObject();
        obj.addProperty("Food name", foodName);
        obj.addProperty("Root time", heath);
        obj.addProperty("Compost name", exitName);
        mainJson.add(obj);
    }



}
