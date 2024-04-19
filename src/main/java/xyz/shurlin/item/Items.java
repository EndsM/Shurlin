package xyz.shurlin.item;

import net.minecraft.block.Block;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import xyz.shurlin.Shurlin;
import xyz.shurlin.cultivation.SpiritConsistences;
import xyz.shurlin.cultivation.SpiritPropertyType;
import xyz.shurlin.cultivation.WeaponLevels;

public class Items {
    public static void load() {
    }

    public static final Item PLANT_GOLD_INGOT;
    public static final Item PLANT_GOLD_NUGGET;
    public static final Item PLANT_JADE;
    public static final Item PEAR;
    public static final Item HOLY_PEAR;
    public static final Item HOLY_PEAR_SWORD;
    public static final Item HOLT_PEAR_BOW;
    //    public static final Item HOLY_PEAR_ARROW;
    public static final Item HOLT_PEAR_WAND;
    public static final Item BEAN;
    public static final Item CHAIN_MINER;
    public static final Item LIFE_AMULET;
    public static final Item CORAL_AMULET;
    public static final Item PLANT_WAND;
    public static final Item MYSTERIOUS_SPIRIT_OF_PLANT;
    public static final Item PLANT_EXTRACTANT;

    public static final Item WORKER_SHELL;
    public static final Item BREAKER_CORE;
    public static final Item COLLECTOR_CORE;
    public static final Item CONCENTRATOR_CORE;
    public static final Item EXTRACTOR_CORE;

    public static final Item TENUOUS_METAL_SPIRIT;
    public static final Item TENUOUS_WOOD_SPIRIT;
    public static final Item TENUOUS_WATER_SPIRIT;
    public static final Item TENUOUS_FIRE_SPIRIT;
    public static final Item TENUOUS_EARTH_SPIRIT;
    public static final Item TENUOUS_WIND_SPIRIT;
    public static final Item TENUOUS_LIGHT_SPIRIT;
    public static final Item TENUOUS_DARKNESS_SPIRIT;
    public static final Item TENUOUS_POISON_SPIRIT;
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

    public static final Item HOLY_PEAR_BAG;

    private static String getBlockId(Block block) {
        return Registry.BLOCK.getId(block).getPath();
    }

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

    private static Item register(Block block) {
        return register(getBlockId(block), new BasicBlockItem(block));
    }

    private static Item register(String registryName, FoodComponent component) {
        return register(registryName, new BasicFoodItem(component));
    }

    private static Item register(String registryName) {
        return register(registryName, new BasicItem());
    }

    static {
        PLANT_GOLD_INGOT = register("plant_gold_ingot");
        PLANT_GOLD_NUGGET = register("plant_gold_nugget");
        PLANT_JADE = register("plant_jade");
        PEAR = register("pear", new FoodComponent.Builder().hunger(3).snack().saturationModifier(0.3f).build());
        HOLY_PEAR = register("holy_pear");
        HOLY_PEAR_SWORD = register("holy_pear_sword", new HolyPearSwordItem());
        HOLT_PEAR_BOW = register("holy_pear_bow", new HolyPearBowItem());
//        HOLY_PEAR_ARROW = register("holy_pear_arrow");
        HOLT_PEAR_WAND = register("holy_pear_wand", new HolyPearWandItem());
        BEAN = register("bean");
        CHAIN_MINER = register("chain_miner", new ChainMinerItem());
        LIFE_AMULET = register("life_amulet", new LifeAmuletItem());
        CORAL_AMULET = register("coral_amulet", new CoralAmuletItem());
        HOLY_PEAR_BAG = register("holy_pear_bag", new HolyPearBagItem());
        PLANT_WAND = register("plant_wand", new PlantWandItem());
        MYSTERIOUS_SPIRIT_OF_PLANT = register("mysterious_spirit_of_plant");
        PLANT_EXTRACTANT = register("plant_extractant", new PlantExtractantItem(64));

        WORKER_SHELL = register("worker_shell");
        BREAKER_CORE = register("breaker_core");
        COLLECTOR_CORE = register("collector_core");
        CONCENTRATOR_CORE = register("concentrator_core");
        EXTRACTOR_CORE = register("extractor_core");


        TENUOUS_METAL_SPIRIT = registerSpirit("tenuous_metal_spirit", SpiritPropertyType.METAL, SpiritConsistences.TENUOUS);
        TENUOUS_WOOD_SPIRIT = registerSpirit("tenuous_wood_spirit", SpiritPropertyType.WOOD, SpiritConsistences.TENUOUS);
        TENUOUS_WATER_SPIRIT = registerSpirit("tenuous_water_spirit", SpiritPropertyType.WATER, SpiritConsistences.TENUOUS);
        TENUOUS_FIRE_SPIRIT = registerSpirit("tenuous_fire_spirit", SpiritPropertyType.FIRE, SpiritConsistences.TENUOUS);
        TENUOUS_EARTH_SPIRIT = registerSpirit("tenuous_earth_spirit", SpiritPropertyType.EARTH, SpiritConsistences.TENUOUS);
        TENUOUS_WIND_SPIRIT = registerSpirit("tenuous_wind_spirit", SpiritPropertyType.WIND, SpiritConsistences.TENUOUS);
        TENUOUS_LIGHT_SPIRIT = registerSpirit("tenuous_light_spirit", SpiritPropertyType.LIGHT, SpiritConsistences.TENUOUS);
        TENUOUS_DARKNESS_SPIRIT = registerSpirit("tenuous_darkness_spirit", SpiritPropertyType.DARKNESS, SpiritConsistences.TENUOUS);
        TENUOUS_POISON_SPIRIT = registerSpirit("tenuous_poison_spirit", SpiritPropertyType.POISON, SpiritConsistences.TENUOUS);
        TENUOUS_LIGHTNING_SPIRIT = registerSpirit("tenuous_lightning_spirit", SpiritPropertyType.LIGHTNING, SpiritConsistences.TENUOUS);
        TENUOUS_ICE_SPIRIT = registerSpirit("tenuous_ice_spirit", SpiritPropertyType.ICE, SpiritConsistences.TENUOUS);
        TENUOUS_TIME_SPACE_SPIRIT = registerSpirit("tenuous_time_space_spirit", SpiritPropertyType.TIME_SPACE, SpiritConsistences.TENUOUS);
        COMMON_METAL_SPIRIT = registerSpirit("common_metal_spirit", SpiritPropertyType.METAL, SpiritConsistences.COMMON);
        COMMON_WOOD_SPIRIT = registerSpirit("common_wood_spirit", SpiritPropertyType.WOOD, SpiritConsistences.COMMON);
        COMMON_WATER_SPIRIT = registerSpirit("common_water_spirit", SpiritPropertyType.WATER, SpiritConsistences.COMMON);
        COMMON_FIRE_SPIRIT = registerSpirit("common_fire_spirit", SpiritPropertyType.FIRE, SpiritConsistences.COMMON);
        COMMON_EARTH_SPIRIT = registerSpirit("common_earth_spirit", SpiritPropertyType.EARTH, SpiritConsistences.COMMON);
        COMMON_WIND_SPIRIT = registerSpirit("common_wind_spirit", SpiritPropertyType.WIND, SpiritConsistences.COMMON);
        COMMON_LIGHT_SPIRIT = registerSpirit("common_light_spirit", SpiritPropertyType.LIGHT, SpiritConsistences.COMMON);
        COMMON_DARKNESS_SPIRIT = registerSpirit("common_darkness_spirit", SpiritPropertyType.DARKNESS, SpiritConsistences.COMMON);
        COMMON_POISON_SPIRIT = registerSpirit("common_poison_spirit", SpiritPropertyType.POISON, SpiritConsistences.COMMON);
        COMMON_LIGHTNING_SPIRIT = registerSpirit("common_lightning_spirit", SpiritPropertyType.LIGHTNING, SpiritConsistences.COMMON);
        COMMON_ICE_SPIRIT = registerSpirit("common_ice_spirit", SpiritPropertyType.ICE, SpiritConsistences.COMMON);
        COMMON_TIME_SPACE_SPIRIT = registerSpirit("common_time_space_spirit", SpiritPropertyType.TIME_SPACE, SpiritConsistences.COMMON);
        DENSE_METAL_SPIRIT = registerSpirit("dense_metal_spirit", SpiritPropertyType.METAL, SpiritConsistences.DENSE);
        DENSE_WOOD_SPIRIT = registerSpirit("dense_wood_spirit", SpiritPropertyType.WOOD, SpiritConsistences.DENSE);
        DENSE_WATER_SPIRIT = registerSpirit("dense_water_spirit", SpiritPropertyType.WATER, SpiritConsistences.DENSE);
        DENSE_FIRE_SPIRIT = registerSpirit("dense_fire_spirit", SpiritPropertyType.FIRE, SpiritConsistences.DENSE);
        DENSE_EARTH_SPIRIT = registerSpirit("dense_earth_spirit", SpiritPropertyType.EARTH, SpiritConsistences.DENSE);
        DENSE_WIND_SPIRIT = registerSpirit("dense_wind_spirit", SpiritPropertyType.WIND, SpiritConsistences.DENSE);
        DENSE_LIGHT_SPIRIT = registerSpirit("dense_light_spirit", SpiritPropertyType.LIGHT, SpiritConsistences.DENSE);
        DENSE_DARKNESS_SPIRIT = registerSpirit("dense_darkness_spirit", SpiritPropertyType.DARKNESS, SpiritConsistences.DENSE);
        DENSE_POISON_SPIRIT = registerSpirit("dense_poison_spirit", SpiritPropertyType.POISON, SpiritConsistences.DENSE);
        DENSE_LIGHTNING_SPIRIT = registerSpirit("dense_lightning_spirit", SpiritPropertyType.LIGHTNING, SpiritConsistences.DENSE);
        DENSE_ICE_SPIRIT = registerSpirit("dense_ice_spirit", SpiritPropertyType.ICE, SpiritConsistences.DENSE);
        DENSE_TIME_SPACE_SPIRIT = registerSpirit("dense_time_space_spirit", SpiritPropertyType.TIME_SPACE, SpiritConsistences.DENSE);

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
