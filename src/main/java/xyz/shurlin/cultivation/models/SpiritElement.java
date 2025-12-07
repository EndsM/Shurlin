package xyz.shurlin.cultivation.models;

import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Identifier;

import java.util.Objects;

public class SpiritElement {
    private final Identifier id;
    // Maybe later change into using Color type?
    private final int color;

    public SpiritElement(Identifier id, int color) {
        this.id = id;
        this.color = color;
    }

    public Identifier getId() {
        return id;
    }

    public int getColor() {
        return color;
    }

    public Text getName() {
        return new TranslatableText("spirit_element." + id.getNamespace() + "." + id.getPath());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        SpiritElement that = (SpiritElement) obj;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
