package at.xander.battleaxes;

import at.xander.battleaxes.material.MyToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;

public class Items {
	public static Item iron_battleaxe, gold_battleaxe, diamond_battleaxe, nickel_battleaxe, silver_battleaxe,
			titanium_battleaxe, ruby_battleaxe, sapphire_battleaxe, amethyst_battleaxe;

	/**
	 * 
	 * @param allows
	 *            0: iron; 1: gold; 2: diamond; 3: Nickel; 4: Silver; 5:
	 *            Titanium; 6: Ruby; 7: Sappire; 8: Amethyst
	 */
	public static void initialise(boolean[] allows) {
		if (allows[0]) {
			registerBattleaxe(iron_battleaxe, "ingotIron", "iron_battleaxe", ToolMaterial.IRON);
		}
		if (allows[1]) {
			registerBattleaxe(gold_battleaxe, "ingotGold", "gold_battleaxe", ToolMaterial.GOLD);
		}
		if (allows[2]) {
			registerBattleaxe(diamond_battleaxe, "gemDiamond", "diamond_battleaxe", ToolMaterial.DIAMOND);
		}
		if (allows[3]) {
			registerBattleaxe(nickel_battleaxe, "ingotNickel", "nickel_battleaxe", MyToolMaterial.NICKEL);
		}
		if (allows[4]) {
			registerBattleaxe(silver_battleaxe, "ingotSilver", "silver_battleaxe", MyToolMaterial.SILVER);
		}
		if (allows[5]) {
			registerBattleaxe(titanium_battleaxe, "ingotTitanium", "titanium_battleaxe", MyToolMaterial.TITANIUM);
		}
		if (allows[6]) {
			registerBattleaxe(ruby_battleaxe, "gemRuby", "ruby_battleaxe", MyToolMaterial.RUBY);
		}
		if (allows[7]) {
			registerBattleaxe(sapphire_battleaxe, "gemSapphire", "sapphire_battleaxe", MyToolMaterial.SAPPHIRE);
		}
		if (allows[8]) {
			registerBattleaxe(amethyst_battleaxe, "gemAmethyst", "amethyst_battleaxe", MyToolMaterial.AMETHYST);
		}
	}

	private static void registerBattleaxe(Item axe, String crafting, String unlocName, ToolMaterial material) {
		axe = new ItemBattleaxe(material, unlocName);
		modifyBattleaxe(axe, crafting, unlocName);
	}

	private static void registerBattleaxe(Item axe, String crafting, String unlocName, MyToolMaterial material) {
		axe = new ItemBattleaxe(material, unlocName);
		modifyBattleaxe(axe, crafting, unlocName);
	}

	private static void modifyBattleaxe(Item axe, String crafting, String unlocName) {
		BAxe_Mod.proxy.registerTexture(axe, "battleaxes:" + unlocName);
		axe.setRegistryName(unlocName);
		GameRegistry.register(axe);
		GameRegistry.addRecipe(
				new ShapedOreRecipe(axe, "MSM", "MSM", " S ", 'M', crafting, 'S', net.minecraft.init.Items.STICK));
	}
}
