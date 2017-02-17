package at.xander.battleaxes.material;

import net.minecraftforge.common.config.Configuration;

public class MaterialProperties {
	private final int harvestLevel, durability, enchantability;
	private final float efficiency, damage, axeDamage;

	public MaterialProperties(int harvestLevel, int durability, float efficiency, float damage, int enchantability) {
		this.harvestLevel = harvestLevel;
		this.durability = durability;
		this.efficiency = efficiency;
		this.damage = damage;
		this.enchantability = enchantability;
		this.axeDamage = 0;
	}

	public MaterialProperties(int harvestLevel, int durability, float efficiency, float damage, int enchantability,
			float axeDamage) {
		this.harvestLevel = harvestLevel;
		this.durability = durability;
		this.efficiency = efficiency;
		this.damage = damage;
		this.enchantability = enchantability;
		this.axeDamage = axeDamage;
	}

	public static MaterialProperties getMaterialProperties(Configuration config, MyToolMaterial mat) {
		int harvestLevel = config.getInt("Harvest Level", mat.toString(), mat.getHarvestLevel(), 0, Integer.MAX_VALUE,
				"");
		int durability = config.getInt("Durability", mat.toString(), mat.getDurability(), 1, Integer.MAX_VALUE, "");
		float efficiency = config.getFloat("Efficiency", mat.toString(), mat.getEfficiency(), 0.0f, Float.MAX_VALUE,
				"");
		float damage = config.getFloat("Damage", mat.toString(), mat.getDamage(), 0, Float.MAX_VALUE, "");
		int enchantability = config.getInt("Enchantability", mat.toString(), mat.getEnchantability(), 0,
				Integer.MAX_VALUE, "");
		float axeDamage = config.getFloat("Axe_Damage", mat.toString(), mat.getAxeDamage(), 0.0f, Float.MAX_VALUE, "");

		return new MaterialProperties(harvestLevel, durability, efficiency, damage, enchantability, axeDamage);

	}

	public int getHarvestLevel() {
		return harvestLevel;
	}

	public int getDurability() {
		return durability;
	}

	public int getEnchantability() {
		return enchantability;
	}

	public float getEfficiency() {
		return efficiency;
	}

	public float getDamage() {
		return damage;
	}

	public float getAxeDamage() {
		return axeDamage;
	}


}
