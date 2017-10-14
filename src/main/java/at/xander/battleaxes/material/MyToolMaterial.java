package at.xander.battleaxes.material;

import java.util.Locale;

import net.minecraft.item.Item.ToolMaterial;
import net.minecraftforge.common.util.EnumHelper;

public enum MyToolMaterial {
	NICKEL(2, 300, 7.0f, 2.25f, 12, 9.7f, -3.35f),
	SILVER(2, 280, 6.8f, 2.0f, 20, 9.5f, -3.15f),
	TITANIUM(3, 1300, 8.0f, 4.0f, 14, 11.0f, -3.1f),
	RUBY(3, 1280, 9.0f, 4.2f, 15, 11.5f, -3.2f),
	SAPPHIRE(3, 1850, 8.0f, 3.8f, 15, 11.0f, -3.15f),
	AMETHYST(3, 1550, 8.5f, 4.0f, 15, 10.5f, -3.1f);

	private ToolMaterial mat;
	// Defaults
	private final int harvestLevel, durability, enchantability;
	private final float efficiency, damage, axeDamage, axeSpeed;

	private float actualAxeSpeed, actualAxeDamage;

	public float getAxeDamage() {
		return axeDamage;
	}

	public float getAxeSpeed() {
		return axeSpeed;
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

	public float getActualAxeDamage() {
		return actualAxeDamage;
	}

	public float getActualAxeSpeed() {
		return actualAxeSpeed;
	}

	public int getActualHarvestLevel() {
		return mat.getHarvestLevel();
	}

	public int getActualDurability() {
		return mat.getMaxUses();
	}

	public int getActualEnchantability() {
		return mat.getEnchantability();
	}

	public float getActualEfficiency() {
		return mat.getEfficiencyOnProperMaterial();
	}

	public float getActualDamage() {
		return mat.getDamageVsEntity();
	}

	private MyToolMaterial(int harvestLevel, int durability, float efficiency, float damage, int enchantability,
			float axeDamage, float axeSpeed) {
		this.harvestLevel = harvestLevel;
		this.durability = durability;
		this.efficiency = efficiency;
		this.damage = damage;
		this.enchantability = enchantability;
		this.axeDamage = axeDamage;
		this.axeSpeed = axeSpeed;
	}

	public void initialise(boolean force_override, MaterialProperties props) {

		mat = this.shouldCreate(force_override)
				? EnumHelper.addToolMaterial(this.name(), props.getHarvestLevel(), props.getDurability(),
						props.getEfficiency(), props.getDamage(), props.getEnchantability())
				: ToolMaterial.valueOf(this.name());
		
		actualAxeDamage = props.getAxeDamage();
		actualAxeSpeed = props.getAxeSpeed();
	}
	
	public void initialise(MaterialProperties props){
		this.initialise(true, props);
	}

	public ToolMaterial getMat() {
		return mat;
	}

	private boolean shouldCreate(boolean force) {
		ToolMaterial[] mat = ToolMaterial.values();
		for (ToolMaterial m : mat) {
			if (m.name().equals(this.name())) {
				return force;
			}
		}
		return true;
	}

	@Override
	public String toString() {
		return name().charAt(0) + name().toLowerCase(Locale.ROOT).substring(1);
	}
}
