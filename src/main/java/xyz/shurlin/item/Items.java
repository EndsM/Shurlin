package xyz.shurlin.item;

import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import xyz.shurlin.Shurlin;
import xyz.shurlin.cultivation.SpiritConstants;
import xyz.shurlin.cultivation.SpiritPropertyType;
import xyz.shurlin.cultivation.WeaponLevels;

public class Items {
    public static void load() {
    }

    public static final Item TENUOUS_LIGHTNING_SPIRIT;
    public static final Item TENUOUS_ICE_SPIRIT;
    public static final Item TENUOUS_TIME_SPACE_SPIRIT;
    public static final Item COMMON_METAL_SPIRIT;
    public static final Item COMMON_WOOD_SPIRIT;
    public static final Item COMMON_WATER_SPIRIT;
    public static final Item COMMON_FIRE_SPIRIT;
    public static final Item COMMON_EARTH_SPIRIT;
    public static final Item COMMON_WIND_SPIRIT;
    public static final Item COMMON_LIGHT_SPIRIT;
    public static final Item COMMON_DARKNESS_SPIRIT;
    public static final Item COMMON_POISON_SPIRIT;
    public static final Item COMMON_LIGHTNING_SPIRIT;
    public static final Item COMMON_ICE_SPIRIT;
    public static final Item COMMON_TIME_SPACE_SPIRIT;
    public static final Item DENSE_METAL_SPIRIT;
    public static final Item DENSE_WOOD_SPIRIT;
    public static final Item DENSE_WATER_SPIRIT;
    public static final Item DENSE_FIRE_SPIRIT;
    public static final Item DENSE_EARTH_SPIRIT;
    public static final Item DENSE_WIND_SPIRIT;
    public static final Item DENSE_LIGHT_SPIRIT;
    public static final Item DENSE_DARKNESS_SPIRIT;
    public static final Item DENSE_POISON_SPIRIT;
    public static final Item DENSE_LIGHTNING_SPIRIT;
    public static final Item DENSE_ICE_SPIRIT;
    public static final Item DENSE_TIME_SPACE_SPIRIT;
    public static final Item SHURLIN_INGOT;
    public static final Item SHURLIN_POWERFUL_AXE;

    public static final BasicToolsItem PLANT_IRON_TOOLS;
    public static final BasicToolsItem PLANT_GOLDEN_TOOLS;
    public static final BasicToolsItem PLANT_JADE_TOOLS;

    public static final BasicArmors PLANT_IRON_ARMORS;
    public static final BasicArmors PLANT_GOLDEN_ARMORS;
    public static final BasicArmors PLANT_JADE_ARMORS;

    //weapon
    public static final Item DARK_IRON_SWORD;

    // These are disaster in terms of reading...
    private static Item register(String registryName, Item item) {
        return Registry.register(Registry.ITEM, new Identifier(Shurlin.MODID, registryName), item);
    }

    private static Item register(String registryName, Item.Settings settings) {
        return register(registryName, new Item(settings));
    }

    private static Item registerSpirit(String registryName, SpiritPropertyType type, double consistence) {
        return register(registryName, new SpiritItem(type, consistence));
    }

    static {
        TENUOUS_LIGHTNING_SPIRIT = registerSpirit("tenuous_lightning_spirit", SpiritPropertyType.LIGHTNING, SpiritConstants.TENUOUS);
        TENUOUS_ICE_SPIRIT = registerSpirit("tenuous_ice_spirit", SpiritPropertyType.ICE, SpiritConstants.TENUOUS);
        TENUOUS_TIME_SPACE_SPIRIT = registerSpirit("tenuous_time_space_spirit", SpiritPropertyType.TIME_SPACE, SpiritConstants.TENUOUS);
        COMMON_METAL_SPIRIT = registerSpirit("common_metal_spirit", SpiritPropertyType.METAL, SpiritConstants.COMMON);
        COMMON_WOOD_SPIRIT = registerSpirit("common_wood_spirit", SpiritPropertyType.WOOD, SpiritConstants.COMMON);
        COMMON_WATER_SPIRIT = registerSpirit("common_water_spirit", SpiritPropertyType.WATER, SpiritConstants.COMMON);
        COMMON_FIRE_SPIRIT = registerSpirit("common_fire_spirit", SpiritPropertyType.FIRE, SpiritConstants.COMMON);
        COMMON_EARTH_SPIRIT = registerSpirit("common_earth_spirit", SpiritPropertyType.EARTH, SpiritConstants.COMMON);
        COMMON_WIND_SPIRIT = registerSpirit("common_wind_spirit", SpiritPropertyType.WIND, SpiritConstants.COMMON);
        COMMON_LIGHT_SPIRIT = registerSpirit("common_light_spirit", SpiritPropertyType.LIGHT, SpiritConstants.COMMON);
        COMMON_DARKNESS_SPIRIT = registerSpirit("common_darkness_spirit", SpiritPropertyType.DARKNESS, SpiritConstants.COMMON);
        COMMON_POISON_SPIRIT = registerSpirit("common_poison_spirit", SpiritPropertyType.POISON, SpiritConstants.COMMON);
        COMMON_LIGHTNING_SPIRIT = registerSpirit("common_lightning_spirit", SpiritPropertyType.LIGHTNING, SpiritConstants.COMMON);
        COMMON_ICE_SPIRIT = registerSpirit("common_ice_spirit", SpiritPropertyType.ICE, SpiritConstants.COMMON);
        COMMON_TIME_SPACE_SPIRIT = registerSpirit("common_time_space_spirit", SpiritPropertyType.TIME_SPACE, SpiritConstants.COMMON);
        DENSE_METAL_SPIRIT = registerSpirit("dense_metal_spirit", SpiritPropertyType.METAL, SpiritConstants.DENSE);
        DENSE_WOOD_SPIRIT = registerSpirit("dense_wood_spirit", SpiritPropertyType.WOOD, SpiritConstants.DENSE);
        DENSE_WATER_SPIRIT = registerSpirit("dense_water_spirit", SpiritPropertyType.WATER, SpiritConstants.DENSE);
        DENSE_FIRE_SPIRIT = registerSpirit("dense_fire_spirit", SpiritPropertyType.FIRE, SpiritConstants.DENSE);
        DENSE_EARTH_SPIRIT = registerSpirit("dense_earth_spirit", SpiritPropertyType.EARTH, SpiritConstants.DENSE);
        DENSE_WIND_SPIRIT = registerSpirit("dense_wind_spirit", SpiritPropertyType.WIND, SpiritConstants.DENSE);
        DENSE_LIGHT_SPIRIT = registerSpirit("dense_light_spirit", SpiritPropertyType.LIGHT, SpiritConstants.DENSE);
        DENSE_DARKNESS_SPIRIT = registerSpirit("dense_darkness_spirit", SpiritPropertyType.DARKNESS, SpiritConstants.DENSE);
        DENSE_POISON_SPIRIT = registerSpirit("dense_poison_spirit", SpiritPropertyType.POISON, SpiritConstants.DENSE);
        DENSE_LIGHTNING_SPIRIT = registerSpirit("dense_lightning_spirit", SpiritPropertyType.LIGHTNING, SpiritConstants.DENSE);
        DENSE_ICE_SPIRIT = registerSpirit("dense_ice_spirit", SpiritPropertyType.ICE, SpiritConstants.DENSE);
        DENSE_TIME_SPACE_SPIRIT = registerSpirit("dense_time_space_spirit", SpiritPropertyType.TIME_SPACE, SpiritConstants.DENSE);

        SHURLIN_INGOT = register("shurlin_ingot", new Item.Settings().group(ItemGroups.SHURLIN).fireproof());
        SHURLIN_POWERFUL_AXE = register("shurlin_powerful_axe", new ShurlinPowerfulAxeItem());

        PLANT_IRON_TOOLS = new BasicToolsItem(ToolMaterials.PLANT_IRON, "plant_iron", 1.0f);
        PLANT_GOLDEN_TOOLS = new BasicToolsItem(ToolMaterials.PLANT_GOLD, "plant_golden", 1.0f);
        PLANT_JADE_TOOLS = new BasicToolsItem(ToolMaterials.PLANT_JADE, "plant_jade", 1.5f);

        PLANT_IRON_ARMORS = new BasicArmors(ArmorMaterials.PLANT_IRON, "plant_iron");
        PLANT_GOLDEN_ARMORS = new BasicArmors(ArmorMaterials.PLANT_GOLD, "plant_golden");
        PLANT_JADE_ARMORS = new BasicArmors(ArmorMaterials.PLANT_JADE, "plant_jade");

        //weapon
        DARK_IRON_SWORD = register("dark_iron_sword", new SwordWeaponItem(WeaponLevels.INFERIOR_WEAPON, WeaponProperties.DARK_IRON));
    }
}
