package xyz.shurlin.item.Spiritual;

import net.minecraft.item.Item;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import xyz.shurlin.item.ItemGroups;
import xyz.shurlin.util.ShurlinLevel;

public class SpiritualSwordItem extends SwordItem implements ShurlinLevel {
    private final float shurlinLevel;

    public SpiritualSwordItem(ToolMaterial material,  float shurlinLevel) {
        super(material, 3, -2.4f, new Item.Settings().group(ItemGroups.SHURLIN));
        this.shurlinLevel = shurlinLevel;
    }

    @Override
    public float getShurlinLevel() {
        return this.shurlinLevel;
    }
}
