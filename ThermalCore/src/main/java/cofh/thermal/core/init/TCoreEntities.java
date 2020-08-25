package cofh.thermal.core.init;

import cofh.thermal.core.entity.item.*;
import cofh.thermal.core.entity.monster.BasalzEntity;
import cofh.thermal.core.entity.monster.BlitzEntity;
import cofh.thermal.core.entity.monster.BlizzEntity;
import cofh.thermal.core.entity.projectile.*;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;

import static cofh.thermal.core.ThermalCore.ENTITIES;
import static cofh.thermal.core.init.TCoreReferences.*;

public class TCoreEntities {

    private TCoreEntities() {

    }

    public static void register() {

        ENTITIES.register(ID_BASALZ, () -> EntityType.Builder.create(BasalzEntity::new, EntityClassification.MONSTER).size(0.6F, 1.8F).immuneToFire().build(ID_BASALZ));
        ENTITIES.register(ID_BLIZZ, () -> EntityType.Builder.create(BlizzEntity::new, EntityClassification.MONSTER).size(0.6F, 1.8F).build(ID_BLIZZ));
        ENTITIES.register(ID_BLITZ, () -> EntityType.Builder.create(BlitzEntity::new, EntityClassification.MONSTER).size(0.6F, 1.8F).build(ID_BLITZ));

        ENTITIES.register(ID_BASALZ_PROJECTILE, () -> EntityType.Builder.<BasalzProjectileEntity>create(BasalzProjectileEntity::new, EntityClassification.MISC).size(0.3125F, 0.3125F).build(ID_BASALZ_PROJECTILE));
        ENTITIES.register(ID_BLIZZ_PROJECTILE, () -> EntityType.Builder.<BlizzProjectileEntity>create(BlizzProjectileEntity::new, EntityClassification.MISC).size(0.3125F, 0.3125F).build(ID_BLIZZ_PROJECTILE));
        ENTITIES.register(ID_BLITZ_PROJECTILE, () -> EntityType.Builder.<BlitzProjectileEntity>create(BlitzProjectileEntity::new, EntityClassification.MISC).size(0.3125F, 0.3125F).build(ID_BLITZ_PROJECTILE));

        ENTITIES.register(ID_EXPLOSIVE_GRENADE, () -> EntityType.Builder.<ExplosiveGrenadeEntity>create(ExplosiveGrenadeEntity::new, EntityClassification.MISC).size(0.25F, 0.25F).build(ID_EXPLOSIVE_GRENADE));
        ENTITIES.register(ID_PHYTO_GRENADE, () -> EntityType.Builder.<PhytoGrenadeEntity>create(PhytoGrenadeEntity::new, EntityClassification.MISC).size(0.25F, 0.25F).build(ID_PHYTO_GRENADE));

        ENTITIES.register(ID_FIRE_GRENADE, () -> EntityType.Builder.<FireGrenadeEntity>create(FireGrenadeEntity::new, EntityClassification.MISC).size(0.25F, 0.25F).build(ID_FIRE_GRENADE));
        ENTITIES.register(ID_EARTH_GRENADE, () -> EntityType.Builder.<EarthGrenadeEntity>create(EarthGrenadeEntity::new, EntityClassification.MISC).size(0.25F, 0.25F).build(ID_EARTH_GRENADE));
        ENTITIES.register(ID_ICE_GRENADE, () -> EntityType.Builder.<IceGrenadeEntity>create(IceGrenadeEntity::new, EntityClassification.MISC).size(0.25F, 0.25F).build(ID_ICE_GRENADE));
        ENTITIES.register(ID_LIGHTNING_GRENADE, () -> EntityType.Builder.<LightningGrenadeEntity>create(LightningGrenadeEntity::new, EntityClassification.MISC).size(0.25F, 0.25F).build(ID_LIGHTNING_GRENADE));

        ENTITIES.register(ID_NUKE_GRENADE, () -> EntityType.Builder.<NukeGrenadeEntity>create(NukeGrenadeEntity::new, EntityClassification.MISC).size(0.25F, 0.25F).build(ID_NUKE_GRENADE));

        ENTITIES.register(ID_PHYTO_TNT, () -> EntityType.Builder.<PhytoTNTEntity>create(PhytoTNTEntity::new, EntityClassification.MISC).immuneToFire().size(0.98F, 0.98F).build(ID_PHYTO_TNT));

        ENTITIES.register(ID_FIRE_TNT, () -> EntityType.Builder.<FireTNTEntity>create(FireTNTEntity::new, EntityClassification.MISC).immuneToFire().size(0.98F, 0.98F).build(ID_FIRE_TNT));
        ENTITIES.register(ID_EARTH_TNT, () -> EntityType.Builder.<EarthTNTEntity>create(EarthTNTEntity::new, EntityClassification.MISC).immuneToFire().size(0.98F, 0.98F).build(ID_EARTH_TNT));
        ENTITIES.register(ID_ICE_TNT, () -> EntityType.Builder.<IceTNTEntity>create(IceTNTEntity::new, EntityClassification.MISC).immuneToFire().size(0.98F, 0.98F).build(ID_ICE_TNT));
        ENTITIES.register(ID_LIGHTNING_TNT, () -> EntityType.Builder.<LightningTNTEntity>create(LightningTNTEntity::new, EntityClassification.MISC).immuneToFire().size(0.98F, 0.98F).build(ID_LIGHTNING_TNT));

        ENTITIES.register(ID_NUKE_TNT, () -> EntityType.Builder.<NukeTNTEntity>create(NukeTNTEntity::new, EntityClassification.MISC).immuneToFire().size(0.98F, 0.98F).build(ID_NUKE_TNT));
    }

}
