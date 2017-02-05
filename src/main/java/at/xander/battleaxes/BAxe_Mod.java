package at.xander.battleaxes;

import at.xander.battleaxes.material.MaterialProperties;
import at.xander.battleaxes.material.MyToolMaterial;
import net.minecraftforge.common.config.ConfigCategory;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = "battleaxes", name = "Fancy Battleaxes", version = "1.0.0")
public class BAxe_Mod {
	/**
	 * 0: iron; 1: gold; 2: diamond; 3: Nickel; 4: Silver; 5: Titanium; 6: Ruby;
	 * 7: Sappire; 8: Amethyst
	 */
	private boolean[] allows;
	public static BAxe_Mod instance;
	@SidedProxy(clientSide = "at.xander.battleaxes.ClientProxy", serverSide = "at.xander.battleaxes.ServerProxy")
	public static ServerProxy proxy;

	@EventHandler
	public void preInit(FMLPreInitializationEvent e) {
		// TODO Config
		allows = new boolean[9];
		Configuration config = new Configuration(e.getSuggestedConfigurationFile());
		config.load();
		loadEnabled(config);
		loadMaterials(config);
		config.save();
	}

	private void loadEnabled(Configuration config) {
		allows[0] = config.getBoolean("EnableIron", "Tools", true, "");
		allows[1] = config.getBoolean("EnableGold", "Tools", true, "");
		allows[2] = config.getBoolean("EnableDiamond", "Tools", true, "");
		allows[3] = config.getBoolean("EnableNickel", "Tools", true, "");
		allows[4] = config.getBoolean("EnableSilver", "Tools", true, "");
		allows[5] = config.getBoolean("EnableTitanium", "Tools", true, "");
		allows[6] = config.getBoolean("EnableRuby", "Tools", true, "");
		allows[7] = config.getBoolean("EnableSapphire", "Tools", true, "");
		allows[8] = config.getBoolean("EnableAmethyst", "Tools", true, "");
	}

	private void loadMaterials(Configuration config) {
		ConfigCategory force = config.getCategory("Force_Overrides");
		force.setComment(
				"Forces Override of existing ToolMaterials, if it is false, existing ToolMaterials with the same name will be used if there are some at config load time");
		if (allows[3]) {
			MyToolMaterial.NICKEL.initialise(config.getBoolean("Nickel", force.getName(), false, ""),
					MaterialProperties.getMaterialProperties(config, MyToolMaterial.NICKEL));
		}
		if (allows[4]) {
			MyToolMaterial.SILVER.initialise(config.getBoolean("Silver", force.getName(), false, ""),
					MaterialProperties.getMaterialProperties(config, MyToolMaterial.SILVER));
		}
		if (allows[5]) {
			MyToolMaterial.TITANIUM.initialise(config.getBoolean("Titanium", force.getName(), false, ""),
					MaterialProperties.getMaterialProperties(config, MyToolMaterial.TITANIUM));
		}
		if (allows[6]) {
			MyToolMaterial.RUBY.initialise(config.getBoolean("Ruby", force.getName(), false, ""),
					MaterialProperties.getMaterialProperties(config, MyToolMaterial.RUBY));
		}
		if (allows[7]) {
			MyToolMaterial.SAPPHIRE.initialise(config.getBoolean("Nickel", force.getName(), false, ""),
					MaterialProperties.getMaterialProperties(config, MyToolMaterial.SAPPHIRE));
		}
		if (allows[8]) {
			MyToolMaterial.AMETHYST.initialise(config.getBoolean("Amethyst", force.getName(), false, ""),
					MaterialProperties.getMaterialProperties(config, MyToolMaterial.AMETHYST));
		}
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
