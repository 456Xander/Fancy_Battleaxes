package at.xander.battleaxes;

import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.ShapedOreRecipe;

public class Items {
	public static Item iron_battleaxe, gold_battleaxe, diamond_battleaxe;

	public static void initialise(boolean[] allows) {
		if (allows[0]) {
			registerBattleaxe(iron_battleaxe, "ingotIron", "iron_battleaxe", ToolMaterial.IRON, 0.0f);
		}
		if (allows[1]) {
			registerBattleaxe(gold_battleaxe, "ingotGold", "gold_battleaxe", ToolMaterial.GOLD, 1.0f);
		}
		if (allows[2]) {
			registerBattleaxe(diamond_battleaxe, "gemDiamond", "diamond_battleaxe", ToolMaterial.EMERALD, 0.0f);
		}
	}

	private static void registerBattleaxe(Item axe, String crafting, String unlocName, ToolMaterial material,
			float extraDamage) {
		axe = new ItemBattleaxe(material, unlocName, extraDamage);
		BAxe_Mod.proxy.registerTexture(axe, "battleaxes:" + unlocName);
		GameRegistry.registerItem(axe, unlocName);
		GameRegistry.addRecipe(
				new ShapedOreRecipe(axe, "MSM", "MSM", " S ", 'M', crafting, 'S', net.minecraft.init.Items.stick));
	}
}
