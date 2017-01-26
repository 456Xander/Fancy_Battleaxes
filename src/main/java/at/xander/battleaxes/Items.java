package at.xander.battleaxes;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraftforge.oredict.ShapedOreRecipe;

public class Items {
	public static Item iron_battleaxe, gold_battleaxe, diamond_battleaxe;

	public static void initialise() {
		// Create Items
		constructTools();
		// Register Item Textures
		registerTextures();
		// Register Items in the Registry
		registerItems();
		// Register Crafing
		registerCrafting();
	}

	private static void registerCrafting() {
		craftBattleaxe("ingotIron", iron_battleaxe);
		craftBattleaxe("ingotGold", gold_battleaxe);
		craftBattleaxe("gemDiamond", diamond_battleaxe);
	}

	private static void craftBattleaxe(String material, Item result) {
		GameRegistry.addRecipe(
				new ShapedOreRecipe(result, "MMM", "MSM", " S ", 'M', material, 'S', net.minecraft.init.Items.stick));
	}

	private static void registerItems() {
		GameRegistry.registerItem(iron_battleaxe, "iron_battleaxe");
		GameRegistry.registerItem(gold_battleaxe, "gold_battleaxe");
		GameRegistry.registerItem(diamond_battleaxe, "diamond_battleaxe");
	}

	private static void constructTools() {
		iron_battleaxe = new ItemBattleaxe(ToolMaterial.IRON, "iron_battleaxe", 0.0f);
		diamond_battleaxe = new ItemBattleaxe(ToolMaterial.EMERALD, "gold_battleaxe", 0.0f);
		// Make gold stronger than normal
		gold_battleaxe = new ItemBattleaxe(ToolMaterial.GOLD, "diamond_battleaxe", 1.0f);
	}

	private static void registerTextures() {
		iron_battleaxe.setTextureName("battleaxes:iron_battleaxe");
		gold_battleaxe.setTextureName("battleaxes:gold_battleaxe");
		diamond_battleaxe.setTextureName("battleaxes:diamond_battleaxe");
	}
}
