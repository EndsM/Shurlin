package xyz.shurlin.registry;

import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import xyz.shurlin.Shurlin;
import xyz.shurlin.cultivation.CultivationRegistry;
import xyz.shurlin.cultivation.models.SpiritElement;

public class ModElements {
    public static final SpiritElement METAL = new SpiritElement(new Identifier(Shurlin.MODID, "metal"), 0xFFD700);
    public static final SpiritElement WOOD = new SpiritElement(new Identifier(Shurlin.MODID, "wood"), 0x00FF00);
    public static final SpiritElement WATER = new SpiritElement(new Identifier(Shurlin.MODID, "water"), 0x0000FF);
    public static final SpiritElement FIRE = new SpiritElement(new Identifier(Shurlin.MODID, "fire"), 0xFF0000);
    public static final SpiritElement EARTH = new SpiritElement(new Identifier(Shurlin.MODID, "earth"), 0x8B4513);
    public static final SpiritElement WIND = new SpiritElement(new Identifier(Shurlin.MODID, "wind"), 0xADD8E6);
    public static final SpiritElement LIGHT = new SpiritElement(new Identifier(Shurlin.MODID, "light"), 0xFFFFFF);
    public static final SpiritElement DARKNESS = new SpiritElement(new Identifier(Shurlin.MODID, "darkness"), 0x000000);
    public static final SpiritElement POISON = new SpiritElement(new Identifier(Shurlin.MODID, "poison"), 0x800080);
    public static final SpiritElement LIGHTNING = new SpiritElement(new Identifier(Shurlin.MODID, "lightning"), 0xFFFF00);
    public static final SpiritElement ICE = new SpiritElement(new Identifier(Shurlin.MODID, "ice"), 0xE0FFFF);
    public static final SpiritElement TIME_SPACE = new SpiritElement(new Identifier(Shurlin.MODID, "time_space"), 0x808080);

    public static final SpiritElement[] SHURLIN_ELEMENTS = {
            METAL, WOOD, WATER, FIRE, EARTH, WIND, LIGHT, DARKNESS, POISON, LIGHTNING, ICE, TIME_SPACE
    };

    public static void Register() {
        register(METAL);
        register(WOOD);
        register(WATER);
        register(FIRE);
        register(EARTH);
        register(WIND);
        register(LIGHT);
        register(DARKNESS);
        register(POISON);
        register(LIGHTNING);
        register(ICE);
        register(TIME_SPACE);
    }

    private static void register(SpiritElement element) {
        Registry.register(CultivationRegistry.ELEMENT_REGISTRY, element.getId(), element);
    }
}
