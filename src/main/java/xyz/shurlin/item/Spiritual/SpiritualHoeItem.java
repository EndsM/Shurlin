package xyz.shurlin.item.Spiritual;

import net.minecraft.item.HoeItem;
import net.minecraft.item.ToolMaterial;
import xyz.shurlin.util.ShurlinLevel;

public class SpiritualHoeItem extends HoeItem implements ShurlinLevel {
    private final float shurlinLevel;

    public SpiritualHoeItem(ToolMaterial material, int attackDamage, float attackSpeed, Settings settings, float shurlinLevel) {
        super(material, attackDamage, attackSpeed, settings);
        this.shurlinLevel = shurlinLevel;
    }

    @Override
    public float getShurlinLevel() {
        return shurlinLevel;
    }
}
