package shadows.apotheosis.deadly.loot.affix.impl.melee;

import net.minecraft.entity.ai.attributes.AttributeModifier.Operation;
import net.minecraft.entity.ai.attributes.Attributes;
import shadows.apotheosis.deadly.loot.EquipmentType;
import shadows.apotheosis.deadly.loot.affix.impl.AttributeAffix;

/**
 * Increases attack speed.
 */
public class AttackSpeedAffix extends AttributeAffix {

	public AttackSpeedAffix(int weight) {
		super(Attributes.ATTACK_SPEED, 0.05F, 1F, Operation.MULTIPLY_TOTAL, weight);
	}

	@Override
	public boolean canApply(EquipmentType type) {
		return type == EquipmentType.SWORD;
	}

	@Override
	public float getMax() {
		return 1.5F;
	}

}