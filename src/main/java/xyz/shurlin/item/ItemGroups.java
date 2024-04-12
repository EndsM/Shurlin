package xyz.shurlin.item;

import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import xyz.shurlin.Shurlin;

public class ItemGroups {
    // main
    public static final ItemGroup SHURLIN = FabricItemGroupBuilder
            .create(new Identifier(Shurlin.MODID, Shurlin.MODID))
            .icon(() -> new ItemStack(Items.PLANT_ESSENCE))
            .build();
    // equipment

    // ore and material

    // decoration
}
