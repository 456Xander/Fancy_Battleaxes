package at.xander.battleaxes;

import java.util.Set;

import com.google.common.collect.Sets;

import at.xander.battleaxes.material.MyToolMaterial;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;

public class ItemBattleaxe extends ItemTool {
	private static final Set<Block> EFFECTIVE_ON = Sets.newHashSet(new Block[] { Blocks.PLANKS, Blocks.BOOKSHELF,
			Blocks.LOG, Blocks.LOG2, Blocks.CHEST, Blocks.PUMPKIN, Blocks.LIT_PUMPKIN, Blocks.MELON_BLOCK,
			Blocks.LADDER, Blocks.WOODEN_BUTTON, Blocks.WOODEN_PRESSURE_PLATE });
	private static final float[] ATTACK_DAMAGES = new float[] { 6.0F, 8.0F, 9.5F, 10.0F, 9.0F };
	private static final float[] ATTACK_SPEEDS = new float[] { -3.4F, -3.4F, -3.3F, -3.2F, -3.1F };

	public ItemBattleaxe(Item.ToolMaterial material, String unlocalizedName) {
		super(material, EFFECTIVE_ON);
		setUnlocalizedName(unlocalizedName);
		this.damageVsEntity = ATTACK_DAMAGES[material.ordinal()];
		this.attackSpeed = ATTACK_SPEEDS[material.ordinal()];
	}

	public ItemBattleaxe(MyToolMaterial material, String unlocalizedName) {
		super(material.getMat(), EFFECTIVE_ON);
		setUnlocalizedName(unlocalizedName);
		this.damageVsEntity = material.getAxeDamage();
		this.attackSpeed = material.getAxeSpeed();
	}

	protected ItemBattleaxe(Item.ToolMaterial material, float damage, float speed) {
		super(material, EFFECTIVE_ON);
		this.damageVsEntity = damage;
		this.attackSpeed = speed;
	}

	public float getStrVsBlock(ItemStack stack, IBlockState state) {
		Material material = state.getMaterial();
		return material != Material.WOOD && material != Material.PLANTS && material != Material.VINE
				? super.getStrVsBlock(stack, state) : this.efficiencyOnProperMaterial - 1.0f;
	}

	@Override
	public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
		return enchantment.type.canEnchantItem(net.minecraft.init.Items.DIAMOND_SWORD);
	}
}