package xyz.shurlin.cultivation;

import it.unimi.dsi.fastutil.shorts.Short2ObjectOpenHashMap;
import org.jetbrains.annotations.Nullable;
import xyz.shurlin.cultivation.CultivationRealms.Map;

public enum CultivationRealms {
    SOLDIER("soldier", 1, 3),
    EMISSARY("emissary", 2, 3),
    TEACHER("teacher", 3, 3),
    OVERLORD("overlord", 4, 3),
    GENERAL("general", 5, 6),
    MARQUIS("marquis", 6, 6),
    SENIOR("senior", 7, 6),
    MASTER("master", 8, 6),
    SOVEREIGN("sovereign", 9, 9),
    KING("king", 10, 9),
    EMPEROR("emperor", 11, 9),
    SAGE("sage", 12, 18);

    private final String name;
    private final short gradation;
    private final short maxRank;

    CultivationRealms(String name, int gradation, int maxRank) {
        this.name = name;
        this.gradation = (short) gradation;
        this.maxRank = (short) maxRank;

        Map.INSTANCE.put(this.gradation, this);
    }

    public String getName() {
        return name;
    }

    public short getMaxRank() {
        return maxRank;
    }

    public CultivationRealms getNextGradation() {
        return getRealmByGradation((short) (gradation + 1));
    }

    public short getGradation() {
        return gradation;
    }

    /**
     * Simplified this method by putting a map.
     *
     * @author Garay Shurlin
     * @author teddyxlandlee
     * @see xyz.shurlin.cultivation.CultivationRealms.Map
     * @since 0.1.2-beta
     */
    @Nullable
    public static CultivationRealms getRealmByGradation(short gradation) {
        return Map.INSTANCE.getOrDefault(gradation, null);
    }

    /**
     * Simplified {@link #getRealmByGradation} method by putting a map.
     *
     * @author Garay Shurlin
     * @author teddyxlandlee
     * @see it.unimi.dsi.fastutil.shorts.Short2ObjectMap
     * @see it.unimi.dsi.fastutil.shorts.Short2ObjectOpenHashMap
     * @see #getRealmByGradation(short)
     * @since 0.1.2-beta
     */
    static final class Map extends Short2ObjectOpenHashMap<CultivationRealms> {
        static final Map INSTANCE = new Map();

        private Map() {
        }
    }
}
