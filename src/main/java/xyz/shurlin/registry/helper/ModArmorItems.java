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
    public static Item PLANT_IRON_CHESTPLATE = new SpiritualArmorItem(ArmorMaterials.PLANT_IRON, EquipmentSlot.CHEST, 1.0f);
    public static Item PLANT_IRON_LEGGINGS = new SpiritualArmorItem(ArmorMaterials.PLANT_IRON, EquipmentSlot.LEGS, 1.0f);
    public static Item PLANT_IRON_BOOTS = new SpiritualArmorItem(ArmorMaterials.PLANT_IRON, EquipmentSlot.FEET, 1.0f);
    // Plant Gold
    public static Item PLANT_GOLDEN_HELMET = new SpiritualArmorItem(ArmorMaterials.PLANT_GOLD, EquipmentSlot.HEAD, 1.0f);
    public static Item PLANT_GOLDEN_CHESTPLATE = new SpiritualArmorItem(ArmorMaterials.PLANT_GOLD, EquipmentSlot.CHEST, 1.0f);
    public static Item PLANT_GOLDEN_LEGGINGS = new SpiritualArmorItem(ArmorMaterials.PLANT_GOLD, EquipmentSlot.LEGS, 1.0f);
    public static Item PLANT_GOLDEN_BOOTS = new SpiritualArmorItem(ArmorMaterials.PLANT_GOLD, EquipmentSlot.FEET, 1.0f);
    // Plant Jade

    private static void register(String name, Item item) {
        Registry.register(Registry.ITEM, new Identifier(Shurlin.MODID, name), item);
    }

    public static void Register() {
        register("plant_iron_helmet", PLANT_IRON_HELMET);
        register("plant_iron_chestplate", PLANT_IRON_CHESTPLATE);
        register("plant_iron_leggings", PLANT_IRON_LEGGINGS);
        register("plant_iron_boots", PLANT_IRON_BOOTS);

        register("plant_golden_helmet", PLANT_GOLDEN_HELMET);
        register("plant_golden_chestplate", PLANT_GOLDEN_CHESTPLATE);
        register("plant_golden_leggings", PLANT_GOLDEN_LEGGINGS);
        register("plant_golden_boots", PLANT_GOLDEN_BOOTS);
    }
}
