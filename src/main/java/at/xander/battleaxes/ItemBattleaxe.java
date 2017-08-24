package at.xander.battleaxes;

import java.util.Set;

import com.google.common.collect.Multimap;
import com.google.common.collect.Sets;

import at.xander.battleaxes.material.MyToolMaterial;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemBattleaxe extends ItemSword {

	private Set<Block> effectiveBlocks;
	protected float efficiencyOnProperMaterial;
	/** Damage versus entities. */
	protected float damageVsEntity;
	protected float attackSpeed;
	/** The material this tool is made from. */
	protected Item.ToolMaterial toolMaterial;

	protected ItemBattleaxe(float attackDamageIn, float attackSpeedIn, Item.ToolMaterial materialIn,
			Set<Block> effectiveBlocksIn) {
		super(materialIn);
		this.efficiencyOnProperMaterial = 4.0F;
		this.toolMaterial = materialIn;
		this.effectiveBlocks = effectiveBlocksIn;
		this.maxStackSize = 1;
		this.setMaxDamage(materialIn.getMaxUses());
		this.efficiencyOnProperMaterial = materialIn.getEfficiencyOnProperMaterial();
		this.damageVsEntity = attackDamageIn + materialIn.getDamageVsEntity();
		this.attackSpeed = attackSpeedIn;
		this.setCreativeTab(CreativeTabs.TOOLS);
		toolClass = "axe";

	}

	protected ItemBattleaxe(Item.ToolMaterial materialIn, Set<Block> effectiveBlocksIn) {
		this(0.0F, 0.0F, materialIn, effectiveBlocksIn);
	}

	public float getStrVsBlock_(ItemStack stack, IBlockState state) {
		for (String type : getToolClasses(stack)) {
			if (state.getBlock().isToolEffective(type, state))
				return efficiencyOnProperMaterial;
		}
		return this.effectiveBlocks.contains(state.getBlock()) ? this.efficiencyOnProperMaterial : 1.0F;
	}

	/**
	 * Called when a Block is destroyed using this Item. Return true to trigger
	 * the "Use Item" statistic.
	 */
	public boolean onBlockDestroyed(ItemStack stack, World worldIn, IBlockState state, BlockPos pos,
			EntityLivingBase entityLiving) {
		if ((double) state.getBlockHardness(worldIn, pos) != 0.0D) {
			stack.damageItem(1, entityLiving);
		}

		return true;
	}

	/**
	 * Returns True is the item is renderer in full 3D when hold.
	 */
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
	public int getItemEnchantability() {
		return this.toolMaterial.getEnchantability();
	}

	/**
	 * Return the name for this tool's material.
	 */
	public String getToolMaterialName() {
		return this.toolMaterial.toString();
	}

	/**
	 * Return whether this item is repairable in an anvil.
	 */
	public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
		ItemStack mat = this.toolMaterial.getRepairItemStack();
		if (mat != null && net.minecraftforge.oredict.OreDictionary.itemMatches(mat, repair, false))
			return true;
		return super.getIsRepairable(toRepair, repair);
	}

	public Multimap<String, AttributeModifier> getItemAttributeModifiers(EntityEquipmentSlot equipmentSlot) {
		Multimap<String, AttributeModifier> multimap = super.getItemAttributeModifiers(equipmentSlot);

		if (equipmentSlot == EntityEquipmentSlot.MAINHAND) {
			// Remove the damage and attack speed of the sword:
			// Add the new ones
			multimap.removeAll(SharedMonsterAttributes.ATTACK_DAMAGE.getName());
			multimap.removeAll(SharedMonsterAttributes.ATTACK_SPEED.getName());

			multimap.put(SharedMonsterAttributes.ATTACK_DAMAGE.getName(),
					new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Tool modifier", (double) this.damageVsEntity, 0));
			multimap.put(SharedMonsterAttributes.ATTACK_SPEED.getName(),
					new AttributeModifier(ATTACK_SPEED_MODIFIER, "Tool modifier", (double) this.attackSpeed, 0));
		}

		return multimap;
	}

	private String toolClass;

	@Override
	public int getHarvestLevel(ItemStack stack, String toolClass, EntityPlayer player, IBlockState blockstate) {
		int level = super.getHarvestLevel(stack, toolClass, player, blockstate);
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

	private static final Set<Block> EFFECTIVE_ON = Sets.newHashSet(new Block[] { Blocks.PLANKS, Blocks.BOOKSHELF,
			Blocks.LOG, Blocks.LOG2, Blocks.CHEST, Blocks.PUMPKIN, Blocks.LIT_PUMPKIN, Blocks.MELON_BLOCK,
			Blocks.LADDER, Blocks.WOODEN_BUTTON, Blocks.WOODEN_PRESSURE_PLATE });
	private static final float[] ATTACK_DAMAGES = new float[] { 6.0F, 8.0F, 9.5F, 10.0F, 9.0F };
	private static final float[] ATTACK_SPEEDS = new float[] { -3.4F, -3.4F, -3.3F, -3.2F, -3.1F };

	public ItemBattleaxe(Item.ToolMaterial material, String unlocalizedName) {
		this(material, EFFECTIVE_ON);
		setUnlocalizedName(unlocalizedName);
		this.damageVsEntity = ATTACK_DAMAGES[material.ordinal()];
		this.attackSpeed = ATTACK_SPEEDS[material.ordinal()];
	}

	public ItemBattleaxe(MyToolMaterial material, String unlocalizedName) {
		this(material.getActualAxeDamage(), material.getActualAxeSpeed(), material.getMat(), EFFECTIVE_ON);
		setUnlocalizedName(unlocalizedName);
		this.damageVsEntity = material.getActualAxeDamage();
		this.attackSpeed = material.getActualAxeSpeed();
	}

	protected ItemBattleaxe(Item.ToolMaterial material, float damage, float speed) {
		this(damage, speed, material, EFFECTIVE_ON);
	}

	public float getStrVsBlock(ItemStack stack, IBlockState state) {
		Material material = state.getMaterial();
		return material != Material.WOOD && material != Material.PLANTS && material != Material.VINE
				? getStrVsBlock_(stack, state) : this.efficiencyOnProperMaterial - 1.0f;
	}

}