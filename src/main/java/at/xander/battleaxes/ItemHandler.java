package at.xander.battleaxes;

import java.util.Collection;
import java.util.List;

import at.xander.battleaxes.material.MyToolMaterial;
import gnu.trove.map.TMap;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;

public class ItemHandler {
	public static Item stone_battleaxe, iron_battleaxe, gold_battleaxe, diamond_battleaxe, nickel_battleaxe,
			silver_battleaxe, titanium_battleaxe, ruby_battleaxe, sapphire_battleaxe, amethyst_battleaxe;

	/**
	 * 
	 * @param allows
	 *            a Map containing all allows for the different battleaxes
	 */
	public static void initialise(TMap<String, Boolean> allows, List<Item> registry) {

		System.out.println(allows);

		stone_battleaxe = new ItemBattleaxe(ToolMaterial.STONE, "stone_battleaxe");

		addToCreativeTab(stone_battleaxe, allows.get("stone"));

		iron_battleaxe = new ItemBattleaxe(ToolMaterial.IRON, "iron_battleaxe");
		addToCreativeTab(iron_battleaxe, allows.get("iron"));

		gold_battleaxe = new ItemBattleaxe(ToolMaterial.GOLD, "gold_battleaxe");
		addToCreativeTab(gold_battleaxe, allows.get("gold"));

		diamond_battleaxe = new ItemBattleaxe(ToolMaterial.DIAMOND, "diamond_battleaxe");
		addToCreativeTab(diamond_battleaxe, allows.get("diamond"));

		// Custom Materials ↓

		nickel_battleaxe = new ItemBattleaxe(MyToolMaterial.NICKEL, "nickel_battleaxe");
		addToCreativeTab(nickel_battleaxe, allows.get("nickel"));

		silver_battleaxe = new ItemBattleaxe(MyToolMaterial.SILVER, "silver_battleaxe");
		addToCreativeTab(silver_battleaxe, allows.get("silver"));

		titanium_battleaxe = new ItemBattleaxe(MyToolMaterial.TITANIUM, "titanium_battleaxe");
		addToCreativeTab(titanium_battleaxe, allows.get("titanium"));

		ruby_battleaxe = new ItemBattleaxe(MyToolMaterial.RUBY, "ruby_battleaxe");
		addToCreativeTab(ruby_battleaxe, allows.get("ruby"));

		sapphire_battleaxe = new ItemBattleaxe(MyToolMaterial.SAPPHIRE, "sapphire_battleaxe");
		addToCreativeTab(sapphire_battleaxe, allows.get("sapphire"));

		amethyst_battleaxe = new ItemBattleaxe(MyToolMaterial.AMETHYST, "amethyst_battleaxe");
		addToCreativeTab(amethyst_battleaxe, allows.get("amethyst"));

		addMultipleToCollection(registry, stone_battleaxe, iron_battleaxe, gold_battleaxe, diamond_battleaxe,
				nickel_battleaxe, silver_battleaxe, titanium_battleaxe, ruby_battleaxe, sapphire_battleaxe,
				amethyst_battleaxe);
	}

	private static void addToCreativeTab(Item item, boolean b) {
		if (b) {
			item.setCreativeTab(CreativeTabs.COMBAT);
		}
	}
	
	@SafeVarargs
	private static <T> void addMultipleToCollection(Collection<T> collection, T... objs) {
		for (T t : objs) {
			collection.add(t);
		}
	}

}
