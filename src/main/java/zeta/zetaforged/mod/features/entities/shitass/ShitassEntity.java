package zeta.zetaforged.mod.features.entities.shitass;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.world.World;

@SuppressWarnings("EntityConstructor")
public class ShitassEntity extends HostileEntity {
    public ShitassEntity(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(1000, new MeleeAttackGoal(this, 2, true));
    }

    public static DefaultAttributeContainer.Builder createShitassAttributes() {
        return ShitassEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 10)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 2);
    }

}
