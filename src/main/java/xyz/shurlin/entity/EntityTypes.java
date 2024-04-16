package xyz.shurlin.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import xyz.shurlin.Shurlin;
import xyz.shurlin.entity.passive.RoamingSpiritEntity;

public class EntityTypes {
    // Before registering mobs, the data fixer warn can be ignored.
    public static final EntityType<RoamingSpiritEntity> ROAMING_SPIRIT_ENTITY_TYPE =
            register("roaming_spirit_entity_type",
                    EntityType.Builder.create(RoamingSpiritEntity::new, SpawnGroup.CREATURE)
                            .setDimensions(0.6f, 0.6f)
                            .maxTrackingRange(8));

    private static <T extends Entity> EntityType<T> register(String name, EntityType.Builder<T> type) {
        return Registry.register(Registry.ENTITY_TYPE, new Identifier(Shurlin.MODID, name), type.build(name));
    }
}
