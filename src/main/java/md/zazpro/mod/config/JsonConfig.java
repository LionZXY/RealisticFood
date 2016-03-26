package md.zazpro.mod.config;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Loader;
import net.minecraft.crash.CrashReport;
import net.minecraftforge.common.config.Property;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;

/**
 * Created by nikit_000 on 06.10.2015.
 */
public class JsonConfig {

    private static JsonObject mainJson = new JsonObject();
    static File jsonFile = new File(Loader.instance().getConfigDir() + "/RealisticFood", "FoodStats.json");




    public static Property get(String category, String name, boolean defaultValue) {
        Property prop = new Property(category, name, Property.Type.BOOLEAN);

        try {
            if (mainJson.get(category) == null)
                mainJson.add(category, new JsonObject());
            JsonObject obj = mainJson.get(category).getAsJsonObject();

            if (obj.get(name) == null)
                obj.addProperty(name, defaultValue);

            prop.set(obj.get(name).getAsBoolean());
        } catch (Exception e) {
            CrashReport.makeCrashReport(e, "Not read/write/parse config file RealisticFood");
            e.printStackTrace();
            return null;
        }

        return prop;
    }

    public static Property get(String category, String name, int defaultValue) {
        Property prop = new Property(category, name, Property.Type.INTEGER);

        try {
            if (mainJson.get(category) == null)
                mainJson.add(category, new JsonObject());
            JsonObject obj = mainJson.get(category).getAsJsonObject();

            if (obj.get(name) == null)
                obj.addProperty(name, defaultValue);

            prop.set(obj.get(name).getAsInt());
        } catch (Exception e) {
            CrashReport.makeCrashReport(e, "Not read/write/parse config file RealisticFood");
            e.printStackTrace();
            return null;
        }

        return prop;
    }

    public static Property get(String category, String name, double defaultValue) {
        Property prop = new Property(category, name, Property.Type.DOUBLE);

        try {
            if (mainJson.get(category) == null)
                mainJson.add(category, new JsonObject());
            JsonObject obj = mainJson.get(category).getAsJsonObject();

            if (obj.get(name) == null)
                obj.addProperty(name, defaultValue);

            prop.set(obj.get(name).getAsDouble());
        } catch (Exception e) {
            CrashReport.makeCrashReport(e, "Not read/write/parse config file RealisticFood");
            e.printStackTrace();
            return null;
        }

        return prop;
    }

    public static Property get(String category, String name, String defaultValue) {
        Property prop = new Property(category, name, Property.Type.STRING);

        try {
            if (mainJson.get(category) == null)
                mainJson.add(category, new JsonObject());
            JsonObject obj = mainJson.get(category).getAsJsonObject();

            if (obj.get(name) == null)
                obj.addProperty(name, defaultValue);

            prop.set(obj.get(name).getAsString());
        } catch (Exception e) {
            CrashReport.makeCrashReport(e, "Not read/write/parse config file RealisticFood");
            e.printStackTrace();
            return null;
        }

        return prop;
    }


}
