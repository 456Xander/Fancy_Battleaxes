package at.xander.battleaxes;

<<<<<<< HEAD
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraftforge.fml.common.registry.GameRegistry;
=======
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
>>>>>>> 6f0bec9b2396bb0c2ad18918f92fb4c640355b7f
import net.minecraftforge.oredict.ShapedOreRecipe;

public class Items {
	public static Item iron_battleaxe, gold_battleaxe, diamond_battleaxe;

<<<<<<< HEAD
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
=======
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
>>>>>>> 6f0bec9b2396bb0c2ad18918f92fb4c640355b7f
	}
}
