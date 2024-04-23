package xyz.shurlin.item.Spiritual;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import xyz.shurlin.item.ItemGroups;

public class SpiritualArmorItem extends ArmorItem {
    public SpiritualArmorItem(ArmorMaterial material, EquipmentSlot slot) {
        super(material, slot, new Settings().group(ItemGroups.SHURLIN));
    }
}
