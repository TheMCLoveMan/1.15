package cofh.archersparadox.item.projectile;

import cofh.archersparadox.entity.projectile.GlowstoneArrowEntity;
import cofh.lib.item.override.ArrowItemCoFH;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class GlowstoneArrowItem extends ArrowItemCoFH {

    public GlowstoneArrowItem(Properties builder) {

        super(builder);
    }

    @Override
    public AbstractArrowEntity createArrow(World worldIn, ItemStack stack, LivingEntity shooter) {

        return new GlowstoneArrowEntity(worldIn, shooter);
    }

}
