package at.xander.battleaxes;

import at.xander.battleaxes.material.MyToolMaterial;
import net.minecraft.block.state.IBlockState;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.init.Enchantments;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;

public class ItemBattleaxe extends ItemAxe {
	private static final float[] ATTACK_DAMAGES = new float[] { 6.0F, 8.0F, 9.5F, 10.0F, 9.0F };
	private static final float[] ATTACK_SPEEDS = new float[] { -3.4F, -3.4F, -3.3F, -3.2F, -3.1F };
	private static final float SharpnessImpact = 0.5f;

	public ItemBattleaxe(ToolMaterial material, String name) {
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
		BAxe_Mod.proxy.registerTexture(this, BAxe_Mod.MODID + ":" + name);
	}

	@Override
	public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
		return (enchantment.type == EnumEnchantmentType.BREAKABLE || enchantment.type == EnumEnchantmentType.WEAPON)
				&& (!enchantment.getName().equals("enchantment.sweeping"));
	}

	@Override
	public float getStrVsBlock(ItemStack stack, IBlockState state) {
		int i = EnchantmentHelper.getEnchantmentLevel(Enchantments.SHARPNESS, stack);
		return super.getStrVsBlock(stack, state) + (i * i + 1); // Add Sharpness to mining speed like Efficiency would
	}

}