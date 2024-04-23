package xyz.shurlin.registry.helper;

import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import xyz.shurlin.Shurlin;
import xyz.shurlin.item.Spiritual.*;
import xyz.shurlin.item.ToolMaterials;

public class ModToolItems {
    // Plant Iron
    public static final Item PLANT_IRON_SWORD = new SpiritualSwordItem(ToolMaterials.PLANT_IRON, 1.0f);
    public static final Item PLANT_IRON_PICKAXE = new SpiritualPickaxeItem(ToolMaterials.PLANT_IRON, 1.0f);
    public static final Item PLANT_IRON_AXE = new SpiritualAxeItem(ToolMaterials.PLANT_IRON, 1.0f);
    public static final Item PLANT_IRON_SHOVEL = new SpiritualShovelItem(ToolMaterials.PLANT_IRON, 1.0f);
    public static final Item PLANT_IRON_HOE = new SpiritualHoeItem(ToolMaterials.PLANT_IRON, 1.0f);
    // Plant Gold
    public static final Item PLANT_GOLDEN_SWORD = new SpiritualSwordItem(ToolMaterials.PLANT_GOLD, 1.0f);
    public static final Item PLANT_GOLDEN_PICKAXE = new SpiritualPickaxeItem(ToolMaterials.PLANT_GOLD, 1.0f);
    public static final Item PLANT_GOLDEN_AXE = new SpiritualAxeItem(ToolMaterials.PLANT_GOLD, 1.0f);
    public static final Item PLANT_GOLDEN_SHOVEL = new SpiritualShovelItem(ToolMaterials.PLANT_GOLD, 1.0f);
    public static final Item PLANT_GOLDEN_HOE = new SpiritualHoeItem(ToolMaterials.PLANT_GOLD, 1.0f);
    // Plant Jade
    public static final Item PLANT_JADE_SWORD = new SpiritualSwordItem(ToolMaterials.PLANT_JADE, 1.5f);
    public static final Item PLANT_JADE_PICKAXE = new SpiritualPickaxeItem(ToolMaterials.PLANT_JADE, 1.5f);
    public static final Item PLANT_JADE_AXE = new SpiritualAxeItem(ToolMaterials.PLANT_JADE, 1.5f);
    public static final Item PLANT_JADE_SHOVEL = new SpiritualShovelItem(ToolMaterials.PLANT_JADE, 1.5f);
    public static final Item PLANT_JADE_HOE = new SpiritualHoeItem(ToolMaterials.PLANT_JADE, 1.5f);

    private static void register(String name, Item item) {
        Registry.register(Registry.ITEM, new Identifier(Shurlin.MODID, name), item);
    }

    public static void Register() {
        register("plant_iron_sword", PLANT_IRON_SWORD);
        register("plant_iron_pickaxe", PLANT_IRON_PICKAXE);
        register("plant_iron_axe", PLANT_IRON_AXE);
        register("plant_iron_shovel", PLANT_IRON_SHOVEL);
        register("plant_iron_hoe", PLANT_IRON_HOE);

        register("plant_golden_sword", PLANT_GOLDEN_SWORD);
        register("plant_golden_pickaxe", PLANT_GOLDEN_PICKAXE);
        register("plant_golden_axe", PLANT_GOLDEN_AXE);
        register("plant_golden_shovel", PLANT_GOLDEN_SHOVEL);
        register("plant_golden_hoe", PLANT_GOLDEN_HOE);

        register("plant_jade_sword", PLANT_JADE_SWORD);
        register("plant_jade_pickaxe", PLANT_JADE_PICKAXE);
        register("plant_jade_axe", PLANT_JADE_AXE);
        register("plant_jade_shovel", PLANT_JADE_SHOVEL);
        register("plant_jade_hoe", PLANT_JADE_HOE);
    }
}
