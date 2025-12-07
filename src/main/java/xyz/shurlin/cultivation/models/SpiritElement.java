package xyz.shurlin.cultivation.models;

import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Identifier;

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
        return new TranslatableText("element." + id.getNamespace() + "." + id.getPath());
    }
}
