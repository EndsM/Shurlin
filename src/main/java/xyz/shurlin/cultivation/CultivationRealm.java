package xyz.shurlin.cultivation;

import it.unimi.dsi.fastutil.objects.Object2ObjectArrayMap;
import net.minecraft.text.TranslatableText;

import java.util.Random;

import static xyz.shurlin.cultivation.SpiritMeridians.getMaxExs;
import static xyz.shurlin.cultivation.SpiritMeridians.getMaxSpirits;

// Create a Cultivation API later, maybe five major versions later though
public class CultivationRealm {
    private final Object2ObjectArrayMap<SpiritPropertyType, SpiritMeridians> meridians = new Object2ObjectArrayMap<>();
    private CultivationRealms realm;
    private short rank;

    CultivationRealm() {
        this.realm = CultivationRealms.SOLDIER;
        this.rank = 1;
        for (SpiritPropertyType type : SpiritPropertyType.GROUPS) {
            this.meridians.put(type, new SpiritMeridians(type));
        }
    }

    public CultivationRealm(CultivationRealms realm, short rank) {
        this.realm = realm;
        this.rank = rank;
    }

    public static CultivationRealm of() {
        return new CultivationRealm();
    }

    public void healSpiritMeridians(int times) {
        for (SpiritPropertyType type : SpiritPropertyType.GROUPS) {
            this.meridians.get(type).heal(times);
        }
    }

    public CultivationRealm temp() {
        this.realm = CultivationRealms.KING;
        this.upgrade();
        Random random = new Random();
        int i = random.nextInt(6) + 1;
        for (SpiritPropertyType type : SpiritPropertyType.GROUPS) {
            this.putMeridians(type, new SpiritMeridians(type,
                    (short) i,
                    random.nextInt((int) getMaxSpirits(i)),
                    random.nextInt((int) getMaxExs(i))));
        }
        return this;
    }

    public void upgrade() {
        if (best())
            return;
        if (rank == realm.getMaxRank()) {
            this.realm = realm.getNextGradation();
            this.rank = 1;
        } else rank++;
    }

    public CultivationRealms getRealm() {
        return realm;
    }

    public short getRank() {
        return rank;
    }

    public String getDescribe() {
        return realm.getName() + ' ' + rank;
    }

    public TranslatableText getDescribeText() {
        return new TranslatableText("realm.shurlin." + this.realm.getName() + ".rank", this.rank);
    }

    public boolean best() {
        return this.realm.getGradation() == 12 && this.rank == 18;
    }

    public SpiritMeridians getMeridians(SpiritPropertyType type) {
        return meridians.get(type);
    }

    public Object2ObjectArrayMap<SpiritPropertyType, SpiritMeridians> getMeridians() {
        return meridians;
    }

    public void putMeridians(SpiritPropertyType type, SpiritMeridians meridians) {
        this.meridians.put(type, meridians);
    }
}
