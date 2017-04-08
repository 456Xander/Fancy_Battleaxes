package at.xander.battleaxes;

import at.xander.battleaxes.material.MaterialProperties;
import at.xander.battleaxes.material.MyToolMaterial;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.common.config.Configuration;

@Mod(modid = "battleaxes", name = "Fancy Battleaxes", version = "1.1.0")
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
		allows = new boolean[10];
		Configuration config = new Configuration(e.getSuggestedConfigurationFile());
		config.load();
		loadEnabled(config);
		loadMaterials(config);
		config.save();
	}

	private void loadEnabled(Configuration config) {
		allows[0] = config.getBoolean("EnableIron", "1 - Tools", true, "");
		allows[1] = config.getBoolean("EnableGold", "1 - Tools", true, "");
		allows[2] = config.getBoolean("EnableDiamond", "1 - Tools", true, "");
		allows[3] = config.getBoolean("EnableNickel", "1 - Tools", true, "");
		allows[4] = config.getBoolean("EnableSilver", "1 - Tools", true, "");
		allows[5] = config.getBoolean("EnableTitanium", "1 - Tools", true, "");
		allows[6] = config.getBoolean("EnableRuby", "1 - Tools", true, "");
		allows[7] = config.getBoolean("EnableSapphire", "1 - Tools", true, "");
		allows[8] = config.getBoolean("EnableAmethyst", "1 - Tools", true, "");
		allows[9] = config.getBoolean("EnableStone", "1 - Tools", true, "");
	}

	private void loadMaterials(Configuration config) {
		// For testing
		// OreDictionary.registerOre("gemRuby",net.minecraft.init.Items.EGG);

		// ConfigCategory force = config.getCategory("Force_Overrides");
		// force.setComment(
		// "Forces Override of existing ToolMaterials, if it is false, existing
		// ToolMaterials with the same name will be used if there are some at
		// config load time");

		MyToolMaterial.NICKEL.initialise(MaterialProperties.getMaterialProperties(config, MyToolMaterial.NICKEL));

		MyToolMaterial.SILVER.initialise(MaterialProperties.getMaterialProperties(config, MyToolMaterial.SILVER));

		MyToolMaterial.TITANIUM.initialise(MaterialProperties.getMaterialProperties(config, MyToolMaterial.TITANIUM));

		MyToolMaterial.RUBY.initialise(MaterialProperties.getMaterialProperties(config, MyToolMaterial.RUBY));

		MyToolMaterial.SAPPHIRE.initialise(MaterialProperties.getMaterialProperties(config, MyToolMaterial.SAPPHIRE));

		MyToolMaterial.AMETHYST.initialise(MaterialProperties.getMaterialProperties(config, MyToolMaterial.AMETHYST));

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
