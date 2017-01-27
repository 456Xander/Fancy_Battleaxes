package at.xander.battleaxes;

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
