package xyz.shurlin.item;

import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import xyz.shurlin.Shurlin;
import xyz.shurlin.cultivation.WeaponLevels;

public class Items {
    public static void load() {
    }

    //weapon

    // These are disaster in terms of reading...
    private static Item register(String registryName, Item item) {
        return Registry.register(Registry.ITEM, new Identifier(Shurlin.MODID, registryName), item);
    }

    static {
    }
}
