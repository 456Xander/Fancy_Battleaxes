package at.xander.battleaxes;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.common.config.Configuration;

@Mod(modid = "battleaxes", name = "Battleaxes", version = "1.0.1")
public class BAxe_Mod {
	// 1. Iron, 2. Gold, 3. Diamond
	boolean[] allows;
	public static BAxe_Mod instance;
	@SidedProxy(clientSide = "at.xander.battleaxes.ClientProxy", serverSide = "at.xander.battleaxes.ServerProxy")
	public static ServerProxy proxy;

	@EventHandler
	public void preInit(FMLPreInitializationEvent e) {
		// TODO Config
		allows = new boolean[3];
		Configuration config = new Configuration(e.getSuggestedConfigurationFile());
		allows[0] = config.getBoolean("EnableIron", "Tools", true, "");
		allows[1] = config.getBoolean("EnableGold", "Tools", true, "");
		allows[2] = config.getBoolean("EnableDiamond", "Tools", true, "");
	}

	@EventHandler
	public void init(FMLInitializationEvent e) {
		Items.initialise(allows);
		allows = null;
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent e) {

	}
}
