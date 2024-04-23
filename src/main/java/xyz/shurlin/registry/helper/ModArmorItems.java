package xyz.shurlin.registry.helper;

import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import xyz.shurlin.Shurlin;

public class ModArmorItems {
    // Plant Iron

    // Plant Gold

    // Plant Jade

    private static void register(String name, Item item) {
        Registry.register(Registry.ITEM, new Identifier(Shurlin.MODID, name), item);
    }

    public static void Register() {
    }
}
