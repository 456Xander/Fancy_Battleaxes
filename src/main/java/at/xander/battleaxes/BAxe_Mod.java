package at.xander.battleaxes;

<<<<<<< HEAD

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = "battleaxes", name = "Fancy Battleaxes", version = "1.0.0")
public class BAxe_Mod {
	// 1. Iron, 2. Gold, 3. Diamond
	boolean[] allows;
	public static BAxe_Mod instance;
	@SidedProxy(clientSide = "at.xander.battleaxes.ClientProxy", serverSide = "at.xander.battleaxes.ServerProxy")
	public static Server_Proxy proxy;

	@EventHandler
	public void preInit(FMLPreInitializationEvent e) {
		// TODO Config
		allows = new boolean[3];
		Configuration config = new Configuration(e.getSuggestedConfigurationFile());
		allows[0] = config.getBoolean("EnableIron", "Tools", true, "");
		allows[1] = config.getBoolean("EnableGold", "Tools", true, "");
		allows[2] = config.getBoolean("EnableDiamond", "Tools", true, "");
=======
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
>>>>>>> 6f0bec9b2396bb0c2ad18918f92fb4c640355b7f
	}

	@EventHandler
	public void init(FMLInitializationEvent e) {
<<<<<<< HEAD
		Items.initialise(allows);
		allows = null;
=======
		Items.initialise();
>>>>>>> 6f0bec9b2396bb0c2ad18918f92fb4c640355b7f
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent e) {

	}
}
