package md.zazpro.mod.config;

import com.google.gson.JsonArray;
import cpw.mods.fml.common.Loader;

import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by LionZXY on 16.10.2015.
 * SimplyHammer v0.9
 */

public class ConfigFoodStats {
	
	public static JsonArray jsonArray = new JsonArray();
    public static File modJson = new File(Loader.instance().getConfigDir() + "/RealisticFood", "FoodStats.json");
    public static void save(){
        if(!modJson.canWrite()){
            try {
                modJson.mkdirs();
                modJson.createNewFile();
                FileOutputStream os = new FileOutputStream(modJson);
                os.write(JsonConfig.getFormatedText(jsonArray.toString()).getBytes());
                os.close();
            }catch (Exception e){

            }}
    }
    public static void addModHammer(String name){

    }

    public static FoodStats getFoodStats(String name){
        //public FoodStats(String name, String foodName, int heath, String exitName) {

        if(!modJson.canWrite()){

        } return null;
    }

}
