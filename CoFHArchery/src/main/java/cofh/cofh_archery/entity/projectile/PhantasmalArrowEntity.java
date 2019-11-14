package cofh.cofh_archery.entity.projectile;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.IPacket;
import net.minecraft.network.play.server.SChangeGameStatePacket;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.*;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.fml.network.NetworkHooks;

import static cofh.cofh_archery.CoFHArchery.PHANTASMAL_ARROW_ENTITY;
import static cofh.cofh_archery.CoFHArchery.PHANTASMAL_ARROW_ITEM;

public class PhantasmalArrowEntity extends AbstractArrowEntity {

    private static boolean GLOWING = true;
    private static boolean NO_GRAVITY = true;

    private static int MAX_TICKS = 200;
    private static float MAX_VELOCITY = 3.0F;
    private static byte PIERCE = 16;

    public PhantasmalArrowEntity(EntityType<? extends PhantasmalArrowEntity> entityIn, World worldIn) {

        super(entityIn, worldIn);
        setGlowing(GLOWING);
        setNoGravity(NO_GRAVITY);
        setPierceLevel(PIERCE);
    }

    public PhantasmalArrowEntity(World worldIn, LivingEntity shooter) {

        super(PHANTASMAL_ARROW_ENTITY.get(), shooter, worldIn);
        setGlowing(GLOWING);
        setNoGravity(NO_GRAVITY);
        setPierceLevel(PIERCE);
    }

    public PhantasmalArrowEntity(World worldIn, double x, double y, double z) {

        super(PHANTASMAL_ARROW_ENTITY.get(), x, y, z, worldIn);
        setGlowing(GLOWING);
        setNoGravity(NO_GRAVITY);
        setPierceLevel(PIERCE);
    }

    @Override
    protected ItemStack getArrowStack() {

        return new ItemStack(PHANTASMAL_ARROW_ITEM.get());
    }

    @Override
    protected void func_213868_a(EntityRayTraceResult raytraceResultIn) {

        Entity entity = raytraceResultIn.getEntity();
        float f = (float) this.getMotion().length();
        int i = MathHelper.ceil(Math.max((double) f * this.damage, 0.0D));
//        if (this.getPierceLevel() > 0) {
//            if (this.field_213878_az == null) {
//                this.field_213878_az = new IntOpenHashSet(5);
//            }
//
//            if (this.field_213875_aA == null) {
//                this.field_213875_aA = Lists.newArrayListWithCapacity(5);
//            }
//
//            if (this.field_213878_az.size() >= this.getPierceLevel() + 1) {
//                this.remove();
//                return;
//            }
//
//            this.field_213878_az.add(entity.getEntityId());
//        }
//
//        if (this.getIsCritical()) {
//            i += this.rand.nextInt(i / 2 + 2);
//        }

        Entity entity1 = this.getShooter();
        DamageSource damagesource;
        if (entity1 == null) {
            damagesource = DamageSource.causeArrowDamage(this, this);
        } else {
            damagesource = DamageSource.causeArrowDamage(this, entity1);
            if (entity1 instanceof LivingEntity) {
                ((LivingEntity) entity1).setLastAttackedEntity(entity);
            }
        }
//        int j = entity.getFireTimer();
//        if (this.isBurning() && !(entity instanceof EndermanEntity)) {
//            entity.setFire(5);
//        }
        if (entity.attackEntityFrom(damagesource, (float) i)) {
            if (entity instanceof LivingEntity) {
                LivingEntity livingentity = (LivingEntity) entity;
//                if (!this.world.isRemote && this.getPierceLevel() <= 0) {
//                    livingentity.setArrowCountInEntity(livingentity.getArrowCountInEntity() + 1);
//                }
//                if (this.knockbackStrength > 0) {
//                    Vec3d vec3d = this.getMotion().mul(1.0D, 0.0D, 1.0D).normalize().scale((double) this.knockbackStrength * 0.6D);
//                    if (vec3d.lengthSquared() > 0.0D) {
//                        livingentity.addVelocity(vec3d.x, 0.1D, vec3d.z);
//                    }
//                }
                if (!this.world.isRemote && entity1 instanceof LivingEntity) {
                    EnchantmentHelper.applyThornEnchantments(livingentity, entity1);
                    EnchantmentHelper.applyArthropodEnchantments((LivingEntity) entity1, livingentity);
                }
                this.arrowHit(livingentity);
                if (entity1 != null && livingentity != entity1 && livingentity instanceof PlayerEntity && entity1 instanceof ServerPlayerEntity) {
                    ((ServerPlayerEntity) entity1).connection.sendPacket(new SChangeGameStatePacket(6, 0.0F));
                }
//                if (!entity.isAlive() && this.field_213875_aA != null) {
//                    this.field_213875_aA.add(livingentity);
//                }
//                if (!this.world.isRemote && entity1 instanceof ServerPlayerEntity) {
//                    ServerPlayerEntity serverplayerentity = (ServerPlayerEntity) entity1;
//                    if (this.field_213875_aA != null && this.func_213873_r()) {
//                        CriteriaTriggers.KILLED_BY_CROSSBOW.func_215105_a(serverplayerentity, this.field_213875_aA, this.field_213875_aA.size());
//                    } else if (!entity.isAlive() && this.func_213873_r()) {
//                        CriteriaTriggers.KILLED_BY_CROSSBOW.func_215105_a(serverplayerentity, Arrays.asList(entity), 0);
//                    }
//                }
            }
//            this.playSound(this.hitSound, 1.0F, 1.2F / (this.rand.nextFloat() * 0.2F + 0.9F));
//            if (this.getPierceLevel() <= 0 && !(entity instanceof EndermanEntity)) {
//                this.remove();
//            }
        }
//        else {
//            entity.setFireTimer(j);
//            this.setMotion(this.getMotion().scale(-0.1D));
//            this.rotationYaw += 180.0F;
//            this.prevRotationYaw += 180.0F;
//            this.ticksInAir = 0;
//            if (!this.world.isRemote && this.getMotion().lengthSquared() < 1.0E-7D) {
//                if (this.pickupStatus == AbstractArrowEntity.PickupStatus.ALLOWED) {
//                    this.entityDropItem(this.getArrowStack(), 0.1F);
//                }
//                this.remove();
//            }
//        }
    }

//    @Override
//    protected void func_213868_a(EntityRayTraceResult raytraceResultIn) {
//
//        Vec3d motion = getMotion();
//        super.func_213868_a(raytraceResultIn);
//
//        if (isAlive()) {
//            setMotion(motion);
//        }
//    }

    @Override
    public void setIsCritical(boolean critical) {

    }

    @Override
    public void setKnockbackStrength(int knockbackStrengthIn) {

    }

    @Override
    public void setPierceLevel(byte level) {

        super.setPierceLevel(PIERCE);
    }

    @Override
    public void shoot(double x, double y, double z, float velocity, float inaccuracy) {

        super.shoot(x, y, z, Math.min(velocity, MAX_VELOCITY), inaccuracy);
    }

    @Override
    public void tick() {

        if (!this.world.isRemote) {
            this.setFlag(6, this.isGlowing());
        }
        this.baseTick();

        boolean flag = this.func_203047_q();
        Vec3d vec3d = this.getMotion();
        if (this.prevRotationPitch == 0.0F && this.prevRotationYaw == 0.0F) {
            float f = MathHelper.sqrt(horizontalMag(vec3d));
            this.rotationYaw = (float) (MathHelper.atan2(vec3d.x, vec3d.z) * (double) (180F / (float) Math.PI));
            this.rotationPitch = (float) (MathHelper.atan2(vec3d.y, f) * (double) (180F / (float) Math.PI));
            this.prevRotationYaw = this.rotationYaw;
            this.prevRotationPitch = this.rotationPitch;
        }
//        BlockPos blockpos = new BlockPos(this.posX, this.posY, this.posZ);
//        BlockState blockstate = this.world.getBlockState(blockpos);
//        if (!blockstate.isAir(this.world, blockpos) && !flag) {
//            VoxelShape voxelshape = blockstate.getCollisionShape(this.world, blockpos);
//            if (!voxelshape.isEmpty()) {
//                for (AxisAlignedBB axisalignedbb : voxelshape.toBoundingBoxList()) {
//                    if (axisalignedbb.offset(blockpos).contains(new Vec3d(this.posX, this.posY, this.posZ))) {
//                        this.inGround = true;
//                        break;
//                    }
//                }
//            }
//        }
//        if (this.arrowShake > 0) {
//            --this.arrowShake;
//        }
//        if (this.isWet()) {
//            this.extinguish();
//        }
//        if (this.inGround && !flag) {
//            if (this.inBlockState != blockstate && this.world.areCollisionShapesEmpty(this.getBoundingBox().grow(0.06D))) {
//                this.inGround = false;
//                this.setMotion(vec3d.mul((this.rand.nextFloat() * 0.2F), (this.rand.nextFloat() * 0.2F), (this.rand.nextFloat() * 0.2F)));
//                this.ticksInGround = 0;
//                this.ticksInAir = 0;
//            } else if (!this.world.isRemote) {
//                this.tryDespawn();
//            }
//            ++this.timeInGround;
//        } else {
//        this.timeInGround = 0;
        ++this.ticksInAir;
        if (this.ticksInAir >= MAX_TICKS) {
            this.remove();
        }
        Vec3d vec3d1 = new Vec3d(this.posX, this.posY, this.posZ);
        Vec3d vec3d2 = vec3d1.add(vec3d);
        RayTraceResult raytraceresult = this.world.rayTraceBlocks(new RayTraceContext(vec3d1, vec3d2, RayTraceContext.BlockMode.COLLIDER, RayTraceContext.FluidMode.NONE, this));
        if (raytraceresult.getType() != RayTraceResult.Type.MISS) {
            vec3d2 = raytraceresult.getHitVec();
        }
        while (this.isAlive()) {
            EntityRayTraceResult entityraytraceresult = this.func_213866_a(vec3d1, vec3d2);
            if (entityraytraceresult != null) {
                raytraceresult = entityraytraceresult;
            }
            if (raytraceresult != null && raytraceresult.getType() == RayTraceResult.Type.ENTITY) {
                Entity entity = ((EntityRayTraceResult) raytraceresult).getEntity();
                Entity entity1 = this.getShooter();
                if (entity instanceof PlayerEntity && entity1 instanceof PlayerEntity && !((PlayerEntity) entity1).canAttackPlayer((PlayerEntity) entity)) {
                    raytraceresult = null;
                    entityraytraceresult = null;
                }
            }
            if (raytraceresult != null && raytraceresult.getType() == RayTraceResult.Type.ENTITY && !ForgeEventFactory.onProjectileImpact(this, raytraceresult)) {
                this.onHit(raytraceresult);
                this.isAirBorne = true;
            }
            if (entityraytraceresult == null || this.getPierceLevel() <= 0) {
                break;
            }
            raytraceresult = null;
        }
        vec3d = this.getMotion();
        double d1 = vec3d.x;
        double d2 = vec3d.y;
        double d0 = vec3d.z;
//            if (this.getIsCritical()) {
//                for (int i = 0; i < 4; ++i) {
//                    this.world.addParticle(ParticleTypes.CRIT, this.posX + d1 * (double) i / 4.0D, this.posY + d2 * (double) i / 4.0D, this.posZ + d0 * (double) i / 4.0D, -d1, -d2 + 0.2D, -d0);
//                }
//            }
        this.posX += d1;
        this.posY += d2;
        this.posZ += d0;
        float f4 = MathHelper.sqrt(horizontalMag(vec3d));
        if (flag) {
            this.rotationYaw = (float) (MathHelper.atan2(-d1, -d0) * (double) (180F / (float) Math.PI));
        } else {
            this.rotationYaw = (float) (MathHelper.atan2(d1, d0) * (double) (180F / (float) Math.PI));
        }
        this.rotationPitch = (float) (MathHelper.atan2(d2, f4) * (double) (180F / (float) Math.PI));
        while (this.rotationPitch - this.prevRotationPitch < -180.0F) {
            this.prevRotationPitch -= 360.0F;
        }
        while (this.rotationPitch - this.prevRotationPitch >= 180.0F) {
            this.prevRotationPitch += 360.0F;
        }
        while (this.rotationYaw - this.prevRotationYaw < -180.0F) {
            this.prevRotationYaw -= 360.0F;
        }
        while (this.rotationYaw - this.prevRotationYaw >= 180.0F) {
            this.prevRotationYaw += 360.0F;
        }
        this.rotationPitch = MathHelper.lerp(0.2F, this.prevRotationPitch, this.rotationPitch);
        this.rotationYaw = MathHelper.lerp(0.2F, this.prevRotationYaw, this.rotationYaw);
//            float f1 = 0.99F;
//            if (this.isInWater()) {
//                for (int j = 0; j < 4; ++j) {
//                    this.world.addParticle(ParticleTypes.BUBBLE, this.posX - d1 * 0.25D, this.posY - d2 * 0.25D, this.posZ - d0 * 0.25D, d1, d2, d0);
//                }
//                f1 = this.getWaterDrag();
//            }
//            this.setMotion(vec3d.scale(f1));
//            if (!this.hasNoGravity() && !flag) {
//                Vec3d vec3d3 = this.getMotion();
//                this.setMotion(vec3d3.x, vec3d3.y - 0.05D, vec3d3.z);
//            }
        this.setPosition(this.posX, this.posY, this.posZ);
//            this.doBlockCollisions();
//    }
    }

    @Override
    public IPacket<?> createSpawnPacket() {

        return NetworkHooks.getEntitySpawningPacket(this);
    }

}
