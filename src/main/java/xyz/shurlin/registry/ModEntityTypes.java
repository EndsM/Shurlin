package xyz.shurlin.registry;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import xyz.shurlin.Shurlin;
import xyz.shurlin.entity.passive.RoamingSpiritEntity;


public class ModEntityTypes {
    public static final EntityType<RoamingSpiritEntity> ROAMING_SPIRIT_ENTITY_TYPE =
            EntityType.Builder.create(RoamingSpiritEntity::new, SpawnGroup.CREATURE)
                    .setDimensions(0.6f, 0.6f)
                    .maxTrackingRange(6)
                    .build("roaming_spirit_entity_type");

    private static void registerEntityType(String id, EntityType entityType) {
        Registry.register(Registry.ENTITY_TYPE,new Identifier(Shurlin.MODID, id),entityType);
    }

    public static void Register() {
        registerEntityType("roaming_spirit_entity_type",ROAMING_SPIRIT_ENTITY_TYPE);
    }
}
