package at.xander.battleaxes;

import java.util.Set;

import com.google.common.collect.Sets;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
<<<<<<< HEAD
import net.minecraft.block.state.IBlockState;
=======
import net.minecraft.creativetab.CreativeTabs;
>>>>>>> 6f0bec9b2396bb0c2ad18918f92fb4c640355b7f
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;

public class ItemBattleaxe extends ItemTool {
<<<<<<< HEAD
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
}
=======
	private static final Set<Block> effectiveMaterials = Sets.newHashSet(new Block[] { Blocks.planks, Blocks.bookshelf,
			Blocks.log, Blocks.log2, Blocks.chest, Blocks.pumpkin, Blocks.lit_pumpkin, Blocks.melon_block });

	protected ItemBattleaxe(Item.ToolMaterial material, String unlocName, float extraDamage) {
		super(4.5F + extraDamage, material, effectiveMaterials);
		// Is slower than normal Axe
		this.efficiencyOnProperMaterial -= 1;
		setCreativeTab(CreativeTabs.tabTools);
		this.setUnlocalizedName(unlocName);
	}
	//Same as in ItemAxe
	@Override
	public float func_150893_a(ItemStack stack, Block block) {
		return block.getMaterial() != Material.wood && block.getMaterial() != Material.plants
				&& block.getMaterial() != Material.vine ? super.func_150893_a(stack, block)
						: this.efficiencyOnProperMaterial;
	}
}
>>>>>>> 6f0bec9b2396bb0c2ad18918f92fb4c640355b7f
