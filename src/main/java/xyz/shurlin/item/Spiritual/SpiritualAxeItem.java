package xyz.shurlin.item.Spiritual;

import net.minecraft.item.AxeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ToolMaterial;
import xyz.shurlin.item.ItemGroups;
import xyz.shurlin.util.ShurlinLevel;

public class SpiritualAxeItem extends AxeItem implements ShurlinLevel {
    private final float shurlinLevel;

    public SpiritualAxeItem(ToolMaterial material, float shurlinLevel) {
        super(material, 6, -3.0f, new Item.Settings().group(ItemGroups.SHURLIN));
        this.shurlinLevel = shurlinLevel;
    }

    @Override
    public float getShurlinLevel() {
        return shurlinLevel;
    }
}
