package xyz.shurlin.item.spiritual;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import xyz.shurlin.item.ItemGroups;
import xyz.shurlin.util.ShurlinLevel;

public class SpiritualArmorItem extends ArmorItem implements ShurlinLevel {
    private final float shurlinLevel;

    public SpiritualArmorItem(ArmorMaterial material, EquipmentSlot slot, float shurlinLevel) {
        super(material, slot, new Settings().group(ItemGroups.SHURLIN));
        this.shurlinLevel = shurlinLevel;
    }

    @Override
    public float getShurlinLevel() {
        return shurlinLevel;
    }
}
