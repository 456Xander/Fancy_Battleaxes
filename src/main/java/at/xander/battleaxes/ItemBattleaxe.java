package at.xander.battleaxes;

import at.xander.battleaxes.material.MyToolMaterial;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ItemBattleaxe extends ItemAxe {

	private static final float[] ATTACK_DAMAGES = new float[] { 6.0F, 8.0F, 9.5F, 10.0F, 9.0F };
	private static final float[] ATTACK_SPEEDS = new float[] { -3.4F, -3.4F, -3.3F, -3.2F, -3.1F };

	protected ItemBattleaxe(ToolMaterial material, String name) {
		super(material, ATTACK_DAMAGES[material.ordinal()], ATTACK_SPEEDS[material.ordinal()]);
		doConstruction(name);
	}

	public ItemBattleaxe(MyToolMaterial material, String name) {
		super(material.getMat(), material.getActualAxeDamage(), material.getActualAxeSpeed());
		doConstruction(name);
	}

	private void doConstruction(String name) {
		setUnlocalizedName(name);
		setRegistryName(name);
		GameRegistry.register(this);
		BAxe_Mod.proxy.registerTexture(this, BAxe_Mod.MODID + ":" + name);
	}

	@Override
	public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
		return (enchantment.type == EnumEnchantmentType.BREAKABLE || enchantment.type == EnumEnchantmentType.WEAPON)
				&& (!enchantment.getName().equals("enchantment.sweeping"));
	}

}