package md.zazpro.mod;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import md.zazpro.mod.config.AddFoodStats;
import md.zazpro.mod.config.JsonConfig;
import md.zazpro.mod.handlers.ModEventHandler;
import md.zazpro.mod.references.ModInfo;
import net.minecraft.init.Items;
import net.minecraftforge.common.MinecraftForge;

/**
 * Created by zazpro on 3/21/2016.
 */
@Mod(modid = ModInfo.MODID, name = ModInfo.MODNAME, version = ModInfo.VERSION) //Tell forge "Oh hey, there's a new mod here to load."

public class Realistic_Food //Start the class Declaration
{

    @Instance(value = ModInfo.MODID) //Tell Forge what instance to use.
    public static Realistic_Food instance;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
    }

    @EventHandler
    public void load(FMLInitializationEvent event)
    {
    	FMLCommonHandler.instance().bus().register(new ModEventHandler());
    	MinecraftForge.EVENT_BUS.register(new ModEventHandler());
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
    	System.out.println(GameRegistry.findUniqueIdentifierFor(Items.rotten_flesh));
    	System.out.println(GameRegistry.findItem("minecraft", "apple"));
    	AddFoodStats.addFoodStats();
    	JsonConfig.save();
    }
}
