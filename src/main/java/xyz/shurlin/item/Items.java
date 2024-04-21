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

    static {
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
