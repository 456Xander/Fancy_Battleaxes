package at.xander.battleaxes;

import java.util.Set;

import com.google.common.collect.Multimap;
import com.google.common.collect.Sets;

import at.xander.battleaxes.material.MyToolMaterial;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.world.World;

public class ItemBattleaxe extends ItemSword {

	private Set<Block> effectiveBlocks;
	protected float efficiencyOnProperMaterial;
	/** Damage versus entities. */
	protected float damageVsEntity;
	protected float attackSpeed;
	/** The material this tool is made from. */
	protected Item.ToolMaterial toolMaterial;

	protected ItemBattleaxe(float attackDamageIn, Item.ToolMaterial materialIn, Set<Block> effectiveBlocksIn) {
		super(materialIn);
		this.efficiencyOnProperMaterial = 3.0F;
		this.toolMaterial = materialIn;
		this.effectiveBlocks = effectiveBlocksIn;
		this.maxStackSize = 1;
		this.setMaxDamage(materialIn.getMaxUses());
		this.efficiencyOnProperMaterial = materialIn.getEfficiencyOnProperMaterial() - 1;
		this.damageVsEntity = attackDamageIn + materialIn.getDamageVsEntity();
		this.setCreativeTab(CreativeTabs.tabTools);
		toolClass = "axe";

	}

	protected ItemBattleaxe(Item.ToolMaterial materialIn, Set<Block> effectiveBlocksIn) {
		this(4.5f, materialIn, effectiveBlocksIn);
	}

	@Override
	public boolean onBlockDestroyed(ItemStack stack, World world, Block block, int x, int y, int z,
			EntityLivingBase entity) {
		if (block.getBlockHardness(world, x, y, z) != 0.0D) {
			stack.damageItem(1, entity);
		}

		return true;
	}

	/**
	 * Returns True is the item is renderer in full 3D when hold.
	 */
	@Override
	@SideOnly(Side.CLIENT)
	public boolean isFull3D() {
		return true;
	}

	public Item.ToolMaterial getToolMaterial() {
		return this.toolMaterial;
	}

	/**
	 * Return the enchantability factor of the item, most of the time is based
	 * on material.
	 */
	@Override
	public int getItemEnchantability() {
		return this.toolMaterial.getEnchantability();
	}

	/**
	 * Return the name for this tool's material.
	 */
	@Override
	public String getToolMaterialName() {
		return this.toolMaterial.toString();
	}

	/**
	 * Return whether this item is repairable in an anvil.
	 */
	@Override
	public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
		ItemStack mat = this.toolMaterial.getRepairItemStack();
		if (mat != null && net.minecraftforge.oredict.OreDictionary.itemMatches(mat, repair, false))
			return true;
		return super.getIsRepairable(toRepair, repair);
	}

	@Override
	public Multimap<String, AttributeModifier> getItemAttributeModifiers() {
		Multimap<String, AttributeModifier> multimap = super.getItemAttributeModifiers();

		// Remove the damage and attack speed of the sword:
		// Add the new ones
		multimap.removeAll(SharedMonsterAttributes.attackDamage.getAttributeUnlocalizedName());

		multimap.put(SharedMonsterAttributes.attackDamage.getAttributeUnlocalizedName(),
				new AttributeModifier(field_111210_e, "Tool modifier", this.damageVsEntity, 0));

		return multimap;
	}

	private String toolClass;

	@Override
	public int getHarvestLevel(ItemStack stack, String toolClass) {
		int level = super.getHarvestLevel(stack, toolClass);
		if (level == -1 && toolClass != null && toolClass.equals(this.toolClass)) {
			return this.toolMaterial.getHarvestLevel();
		} else {
			return level;
		}
	}

	@Override
	public Set<String> getToolClasses(ItemStack stack) {
		return toolClass != null ? com.google.common.collect.ImmutableSet.of(toolClass) : super.getToolClasses(stack);
	}

	private static final Set<Block> EFFECTIVE_ON = Sets.newHashSet(new Block[] { Blocks.planks, Blocks.bookshelf,
			Blocks.log, Blocks.log2, Blocks.chest, Blocks.pumpkin, Blocks.lit_pumpkin, Blocks.melon_block,
			Blocks.ladder, Blocks.wooden_button, Blocks.wooden_pressure_plate });

	public ItemBattleaxe(Item.ToolMaterial material, String unlocalizedName) {
		this(material, EFFECTIVE_ON);
		setUnlocalizedName(unlocalizedName);
	}

	public ItemBattleaxe(MyToolMaterial material, String unlocalizedName) {
		this(4.5f, material.getMat(), EFFECTIVE_ON);
		setUnlocalizedName(unlocalizedName);
		this.damageVsEntity = material.getActualAxeDamage();
	}

	protected ItemBattleaxe(Item.ToolMaterial material, float damage) {
		this(damage, material, EFFECTIVE_ON);
	}

	public float getStrVsBlock(ItemStack stack, Block block) {
		for (String type : getToolClasses(stack)) {
			if (block.isToolEffective(type, 0)) {
				return efficiencyOnProperMaterial;
			}
		}

		if (this.effectiveBlocks.contains(block)) {
			return this.efficiencyOnProperMaterial;
		}
		return 1.0f;

	}

	@Override
	public float func_150893_a(ItemStack stack, Block block) {
		for (String type : getToolClasses(stack)) {
			if (block.isToolEffective(type, 0)) {
				return efficiencyOnProperMaterial;
			}
		}

		if (this.effectiveBlocks.contains(block)) {
			return this.efficiencyOnProperMaterial;
		}
		return 1.0f;
	}

}