package at.xander.battleaxes;

import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.ShapedOreRecipe;

public class Items {
	public static Item iron_battleaxe, gold_battleaxe, diamond_battleaxe;

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
	}

	private static void registerBattleaxe(Item axe, String crafting, String unlocName, ToolMaterial material) {
		axe = new ItemBattleaxe(material, unlocName);
		BAxe_Mod.proxy.registerTexture(axe, "battleaxes:" + unlocName);
		axe.setRegistryName(unlocName);
		GameRegistry.register(axe);
		GameRegistry.addRecipe(
				new ShapedOreRecipe(axe, "MSM", "MSM", " S ", 'M', crafting, 'S', net.minecraft.init.Items.STICK));
	}
}
