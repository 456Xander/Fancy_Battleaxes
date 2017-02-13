package at.xander.battleaxes;

import at.xander.battleaxes.material.MyToolMaterial;
import net.minecraft.creativetab.CreativeTabs;
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

		registerBattleaxe(iron_battleaxe, "ingotIron", "iron_battleaxe", ToolMaterial.IRON, allows[0]);
		registerBattleaxe(gold_battleaxe, "ingotGold", "gold_battleaxe", ToolMaterial.GOLD, allows[1]);
		registerBattleaxe(diamond_battleaxe, "gemDiamond", "diamond_battleaxe", ToolMaterial.DIAMOND, allows[2]);
		registerBattleaxe(nickel_battleaxe, "ingotNickel", "nickel_battleaxe", MyToolMaterial.NICKEL, allows[3]);
		registerBattleaxe(silver_battleaxe, "ingotSilver", "silver_battleaxe", MyToolMaterial.SILVER, allows[4]);
		registerBattleaxe(titanium_battleaxe, "ingotTitanium", "titanium_battleaxe", MyToolMaterial.TITANIUM,
				allows[5]);
		registerBattleaxe(ruby_battleaxe, "gemRuby", "ruby_battleaxe", MyToolMaterial.RUBY, allows[6]);
		registerBattleaxe(sapphire_battleaxe, "gemSapphire", "sapphire_battleaxe", MyToolMaterial.SAPPHIRE, allows[7]);
		registerBattleaxe(amethyst_battleaxe, "gemAmethyst", "amethyst_battleaxe", MyToolMaterial.AMETHYST, allows[8]);
	}

	private static void registerBattleaxe(Item axe, String crafting, String unlocName, ToolMaterial material,
			boolean allowed) {
		axe = new ItemBattleaxe(material, unlocName);
		modifyBattleaxe(axe, crafting, unlocName, allowed);
	}

	private static void registerBattleaxe(Item axe, String crafting, String unlocName, MyToolMaterial material,
			boolean allowed) {
		axe = new ItemBattleaxe(material, unlocName);
		modifyBattleaxe(axe, crafting, unlocName, allowed);
	}

	private static void modifyBattleaxe(Item axe, String crafting, String unlocName, boolean allowed) {
		BAxe_Mod.proxy.registerTexture(axe, "battleaxes:" + unlocName);
		axe.setRegistryName(unlocName);
		GameRegistry.register(axe);
		if (allowed) {
			GameRegistry.addRecipe(
					new ShapedOreRecipe(axe, "MSM", "MSM", " S ", 'M', crafting, 'S', net.minecraft.init.Items.STICK));
			axe.setCreativeTab(CreativeTabs.COMBAT);
		}
		else{
			axe.setCreativeTab(null);
		}
	}
}
