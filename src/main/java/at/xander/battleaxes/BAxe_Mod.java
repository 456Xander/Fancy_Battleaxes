package at.xander.battleaxes;

import java.io.File;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import at.xander.battleaxes.material.MaterialProperties;
import at.xander.battleaxes.material.MyToolMaterial;
import gnu.trove.map.hash.THashMap;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.oredict.OreDictionary;

@Mod(modid = BAxe_Mod.MODID, name = "Fancy Battleaxes", version = "1.2")
public class BAxe_Mod {

	public static final String MODID = "battleaxes";

	public THashMap<String, Boolean> allows = new THashMap<>();
	@Instance
	public static BAxe_Mod instance;
	private File configFile;
	@SidedProxy(clientSide = "at.xander.battleaxes.ClientProxy", serverSide = "at.xander.battleaxes.ServerProxy")
	public static ServerProxy proxy;

	@EventHandler
	public void preInit(FMLPreInitializationEvent e) {
		// TODO Config
		configFile = e.getSuggestedConfigurationFile();
		MinecraftForge.EVENT_BUS.register(RegisterHandler.instance);
		proxy.preInit(e);
	}

	public void onInitItems(List<Item> registryList) {
		Configuration config = new Configuration(configFile);
		config.load();
		loadEnabled(config);
		loadMaterials(config);
		config.save();
		ItemHandler.initialise(allows, registryList);

		configFile = null;
	}

	private void loadEnabled(Configuration config) {

		allows.put("iron", config.getBoolean("EnableIron", "1 - Tools", true, ""));
		allows.put("gold", config.getBoolean("EnableGold", "1 - Tools", true, ""));
		allows.put("diamond", config.getBoolean("EnableDiamond", "1 - Tools", true, ""));
		allows.put("nickel", config.getBoolean("EnableNickel", "1 - Tools", true, ""));
		allows.put("silver", config.getBoolean("EnableSilver", "1 - Tools", true, ""));
		allows.put("titanium", config.getBoolean("EnableTitanium", "1 - Tools", true, ""));
		allows.put("ruby", config.getBoolean("EnableRuby", "1 - Tools", true, ""));
		allows.put("sapphire", config.getBoolean("EnableSapphire", "1 - Tools", true, ""));
		allows.put("amethyst", config.getBoolean("EnableAmethyst", "1 - Tools", true, ""));
		allows.put("stone", config.getBoolean("EnableStone", "1 - Tools", true, ""));
	}

	private void loadMaterials(Configuration config) {
		// For testing
		// OreDictionary.registerOre("gemRuby",net.minecraft.init.Items.EGG);

		// ConfigCategory force = config.getCategory("Force_Overrides");
		// force.setComment(
		// "Forces Override of existing ToolMaterials, if it is false, existing
		// ToolMaterials with the same name will be used if there are some at
		// config load time");

		// add cobble to listAllStone which is used for stone battleaxe, so that
		// compatibility with unified stone tools exists
		OreDictionary.registerOre("listAllStone", Blocks.COBBLESTONE);

		MyToolMaterial.NICKEL.initialise(MaterialProperties.getMaterialProperties(config, MyToolMaterial.NICKEL));
		final Pattern itemPattern = Pattern.compile("(\\w+):(\\w+)#?(\\d*)");
		
		registerAdditionalWhitelist(config, itemPattern, MyToolMaterial.NICKEL);

		MyToolMaterial.SILVER.initialise(MaterialProperties.getMaterialProperties(config, MyToolMaterial.SILVER));
		registerAdditionalWhitelist(config, itemPattern, MyToolMaterial.SILVER);

		MyToolMaterial.TITANIUM.initialise(MaterialProperties.getMaterialProperties(config, MyToolMaterial.TITANIUM));
		registerAdditionalWhitelist(config, itemPattern, MyToolMaterial.TITANIUM);

		MyToolMaterial.RUBY.initialise(MaterialProperties.getMaterialProperties(config, MyToolMaterial.RUBY));
		registerAdditionalWhitelist(config, itemPattern, MyToolMaterial.RUBY);

		MyToolMaterial.SAPPHIRE.initialise(MaterialProperties.getMaterialProperties(config, MyToolMaterial.SAPPHIRE));
		registerAdditionalWhitelist(config, itemPattern, MyToolMaterial.SAPPHIRE);

		MyToolMaterial.AMETHYST.initialise(MaterialProperties.getMaterialProperties(config, MyToolMaterial.AMETHYST));
		registerAdditionalWhitelist(config, itemPattern, MyToolMaterial.AMETHYST);

	}

	private void registerAdditionalWhitelist(Configuration config, final Pattern itemPattern, MyToolMaterial mat) {
		String[] whitelist = config.getStringList(mat.toString(), "whitelist", new String[] {},
				"Additional Items, which are valid for this material can be registered here, items should be entered in the following way:"
						+ "\n modid:itemname#meta");
		for (String s : whitelist) {
			Matcher m = itemPattern.matcher(s);
			if (m.matches()) {
				String modid = m.group(1);
				String itemName = m.group(2);
				int meta = 0;
				if (m.groupCount() >= 3) {
					String tmp = m.group(3);
					meta = tmp.equals("") ? 0 : Integer.parseInt(m.group(3));
				}
				String matName = mat.toString().toLowerCase();
				OreDictionary.registerOre("ingot" + matName.substring(0, 1).toUpperCase() + matName.substring(1),
						new ItemStack(Item.REGISTRY.getObject(new ResourceLocation(modid, itemName)), 1, meta));
				System.out.printf("Found whitelist: %s:%s with meta %d\n", modid, itemName, meta);
			}
			
		}
	}

	@EventHandler
	public void init(FMLInitializationEvent e) {
		proxy.init(e);
	}

	public void postInit(FMLPostInitializationEvent e) {
		allows = null;
		proxy.postInit(e);
	}
}
