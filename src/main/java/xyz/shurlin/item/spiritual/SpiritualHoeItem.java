package xyz.shurlin.item.spiritual;

import net.minecraft.item.HoeItem;
import net.minecraft.item.ToolMaterial;
import xyz.shurlin.item.ItemGroups;
import xyz.shurlin.util.ShurlinLevel;

public class SpiritualHoeItem extends HoeItem implements ShurlinLevel {
    private final float shurlinLevel;

    public SpiritualHoeItem(ToolMaterial material, float shurlinLevel) {
        super(material, (int) (0 - material.getAttackDamage()), material.getAttackDamage() - 3, new Settings().group(ItemGroups.SHURLIN));
        this.shurlinLevel = shurlinLevel;
    }

    @Override
    public float getShurlinLevel() {
        return shurlinLevel;
    }
}
