package xyz.shurlin.registry.helper;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import xyz.shurlin.Shurlin;
import xyz.shurlin.item.ArmorMaterials;
import xyz.shurlin.item.Spiritual.SpiritualArmorItem;

public class ModArmorItems {
    // Plant Iron
    public static Item PLANT_IRON_HELMET = new SpiritualArmorItem(ArmorMaterials.PLANT_IRON, EquipmentSlot.HEAD, 1.0f);
    // Plant Gold

    // Plant Jade

    private static void register(String name, Item item) {
        Registry.register(Registry.ITEM, new Identifier(Shurlin.MODID, name), item);
    }

    public static void Register() {
    }
}
