package xyz.shurlin.registry.helper;

import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import xyz.shurlin.Shurlin;
import xyz.shurlin.item.Spiritual.SpiritualSwordItem;
import xyz.shurlin.item.ToolMaterials;

public class ModToolItems {
    // Plant Iron
    public static final Item PLANT_IRON_SWORD = new SpiritualSwordItem(ToolMaterials.PLANT_IRON, 1.0f);
    // Plant Gold

    // Plant Jade

    private static void register(String name, Item item) {
        Registry.register(Registry.ITEM, new Identifier(Shurlin.MODID, name), item);
    }

    public static void Register() {
    }
}
