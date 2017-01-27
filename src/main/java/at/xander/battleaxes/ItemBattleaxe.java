package at.xander.battleaxes;

import java.util.Set;

import com.google.common.collect.Sets;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;

public class ItemBattleaxe extends ItemTool {
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
	public float getStrVsBlock(ItemStack stack, Block block) {
		return block.getMaterial() != Material.wood && block.getMaterial() != Material.plants
				&& block.getMaterial() != Material.vine ? super.getStrVsBlock(stack, block)
						: this.efficiencyOnProperMaterial;
	}
}
