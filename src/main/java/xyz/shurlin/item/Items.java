package xyz.shurlin.item;

import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import xyz.shurlin.Shurlin;
import xyz.shurlin.cultivation.WeaponLevels;

public class Items {
    public static void load() {
    }

    public static final BasicArmors PLANT_JADE_ARMORS;

    //weapon
    public static final Item DARK_IRON_SWORD;

    // These are disaster in terms of reading...
    private static Item register(String registryName, Item item) {
        return Registry.register(Registry.ITEM, new Identifier(Shurlin.MODID, registryName), item);
    }


    static {
        PLANT_JADE_ARMORS = new BasicArmors(ArmorMaterials.PLANT_JADE, "plant_jade");

        //weapon
        DARK_IRON_SWORD = register("dark_iron_sword", new SwordWeaponItem(WeaponLevels.INFERIOR_WEAPON, WeaponProperties.DARK_IRON));
    }
}
