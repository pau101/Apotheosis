package shadows.apotheosis.deadly.loot.affix.impl.melee;

import java.util.List;
import java.util.Random;
import java.util.function.Predicate;

import javax.annotation.Nullable;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.text.TranslationTextComponent;
import shadows.apotheosis.Apotheosis;
import shadows.apotheosis.deadly.loot.EquipmentType;
import shadows.apotheosis.deadly.loot.affix.AffixHelper;
import shadows.apotheosis.deadly.loot.affix.impl.RangedAffix;
import shadows.apotheosis.deadly.loot.modifiers.AffixModifier;

/**
 * Full strength attacks will zap nearby enemies.
 */
public class DamageChainAffix extends RangedAffix {

	public DamageChainAffix(int weight) {
		super(2, 8, weight);
	}

	@Override
	public boolean canApply(EquipmentType type) {
		return type == EquipmentType.SWORD;
	}

	@Override
	public void onEntityDamaged(LivingEntity user, Entity target, float level) {
		if (Apotheosis.localAtkStrength >= 0.98) {
			Predicate<Entity> pred = e -> !(e instanceof PlayerEntity) && e instanceof LivingEntity && ((LivingEntity) e).canAttack(EntityType.PLAYER);
			List<Entity> nearby = target.world.getEntitiesInAABBexcluding(target, new AxisAlignedBB(target.getPosition()).grow(6), pred);
			if (!user.world.isRemote) for (Entity e : nearby) {
				e.attackEntityFrom(DamageSource.LIGHTNING_BOLT, level);
			}
		}
	}

	@Override
	public float apply(ItemStack stack, Random rand, @Nullable AffixModifier modifier) {
		float lvl = range.generateFloat(rand);
		if (modifier != null) lvl = modifier.editLevel(this, lvl);
		AffixHelper.addLore(stack, new TranslationTextComponent("affix." + this.getRegistryName() + ".desc", String.format("%.2f", lvl)));
		return lvl;
	}

	@Override
	public float getMin() {
		return 1;
	}

	@Override
	public float getMax() {
		return 10;
	}

}