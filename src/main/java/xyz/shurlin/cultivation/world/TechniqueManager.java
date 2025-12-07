package xyz.shurlin.cultivation.world;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import net.minecraft.world.PersistentState;
import xyz.shurlin.cultivation.models.GeneratedTechnique;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class TechniqueManager extends PersistentState {
    private static final String ID = "shurlin_techniques";

    // In memory cached techniques
    private final Map<UUID, GeneratedTechnique> techniques = new HashMap<>();

    public TechniqueManager() {
        super(ID);
    }

    @Override
    public void fromTag(NbtCompound tag) {
        techniques.clear();
        NbtList list = tag.getList("Techniques", 10);
        for (int i = 0; i < list.size(); i++) {
            NbtCompound techTag = list.getCompound(i);
            GeneratedTechnique tech = GeneratedTechnique.fromNbt(techTag);
            techniques.put(tech.getId(), tech);
        }
    }

    @Override
    public NbtCompound writeNbt(NbtCompound nbt) {
        NbtList list = new NbtList();
        for (GeneratedTechnique tech : techniques.values()) {
            list.add(tech.toNbt());
        }
        nbt.put("Techniques", list);
        return nbt;
    }
}
