package xyz.shurlin.cultivation.models;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import xyz.shurlin.cultivation.dynamic.TechniqueGrade;
import xyz.shurlin.cultivation.dynamic.TechniqueQuality;

import java.util.UUID;

public class GeneratedTechnique {
    private UUID id;
    private String name;
    private TechniqueGrade grade;
    private TechniqueQuality quality;
    private Identifier elementId;

    // The stats of the Technique
    private double efficiency;
    private double capacityModifier;

    public GeneratedTechnique(UUID id, String name, TechniqueGrade grade, TechniqueQuality quality, Identifier elementId, double efficiency, double capacityModifier) {
        this.id = id;
        this.name = name;
        this.grade = grade;
        this.quality = quality;
        this.elementId = elementId;
        this.efficiency = efficiency;
        this.capacityModifier = capacityModifier;
    }

    // Read from NBT
    public static GeneratedTechnique fromNbt(NbtCompound tag) {
        UUID id = tag.getUuid("Id");
        String name = tag.getString("Name");
        TechniqueGrade grade = TechniqueGrade.valueOf(tag.getString("Grade"));
        TechniqueQuality quality = TechniqueQuality.valueOf(tag.getString("Quality"));
        Identifier element = new Identifier(tag.getString("Element"));
        double eff = tag.getDouble("Efficiency");
        double cap = tag.getDouble("Capacity");
        return new GeneratedTechnique(id, name, grade, quality, element, eff, cap);
    }

    // Write to NBT
    public NbtCompound toNbt() {
        NbtCompound tag = new NbtCompound();
        tag.putUuid("Id", id);
        tag.putString("Name", name);
        tag.putString("Grade", grade.name());
        tag.putString("Quality", quality.name());
        tag.putString("Element", elementId.toString());
        tag.putDouble("Efficiency", efficiency);
        tag.putDouble("Capacity", capacityModifier);
        return tag;
    }

    public Text getDisplayName() {
        return new LiteralText(String.format("§7[%s·%s] §r%s", grade.getName(), quality.getName(), name))
                .formatted(grade.getColor());
    }

    public UUID getId() {
        return id;
    }

    public TechniqueGrade getGrade() {
        return grade;
    }

    public double getEfficiency() {
        return efficiency;
    }

    public double getCapacityModifier() {
        return capacityModifier;
    }

    public Identifier getElementId() {
        return elementId;
    }
}
