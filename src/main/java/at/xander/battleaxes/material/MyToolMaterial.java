package at.xander.battleaxes.material;

import java.util.Locale;
import java.util.Map;

import net.minecraft.item.Item.ToolMaterial;
import net.minecraftforge.common.util.EnumHelper;

public enum MyToolMaterial {
	NICKEL(2, 300, 7.0f, 2.25f, 12),
	SILVER(2, 280, 6.8f, 2.0f, 20),
	TITANIUM(3, 1300, 8.0f, 4.0f, 14),
	RUBY(3, 1280, 9.0f, 4.2f, 15),
	SAPPHIRE(3, 1850, 8.0f, 3.8f, 15),
	AMETHYST(3, 1550, 8.5f, 4.0f, 15);

	private ToolMaterial mat;
	// Defaults
	private final int harvestLevel, durability, enchantability;
	private final float efficiency, damage;

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

	private MyToolMaterial(int harvestLevel, int durability, float efficiency, float damage, int enchantability) {
		this.harvestLevel = harvestLevel;
		this.durability = durability;
		this.efficiency = efficiency;
		this.damage = damage;
		this.enchantability = enchantability;
	}

	public void initialise(boolean force_override, MaterialProperties props) {

		mat = this.shouldCreate(force_override)
				? EnumHelper.addToolMaterial(this.name(), props.getHarvestLevel(), props.getDurability(),
						props.getEfficiency(), props.getDamage(), props.getEnchantability())
				: ToolMaterial.valueOf(this.name());
	}

	public ToolMaterial getMat() {
		return mat;
	}

	private boolean shouldCreate(boolean force) {
		return ToolMaterial.valueOf(this.toString()) == null || force;
	}

	@Override
	public String toString() {
		return name().charAt(0) + name().toLowerCase(Locale.ROOT).substring(1);
	}
}
