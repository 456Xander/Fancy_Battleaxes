package at.xander.battleaxes;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = "battleaxes", name = "Battleaxes", version = "1.0.0")
public class BAxe_Mod {

	public static BAxe_Mod instance;

	@EventHandler
	public void preInit(FMLPreInitializationEvent e) {
		//TODO Config
	}

	@EventHandler
	public void init(FMLInitializationEvent e) {
		Items.initialise();
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent e) {

	}
}
