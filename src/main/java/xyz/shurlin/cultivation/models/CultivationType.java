package xyz.shurlin.cultivation.models;

import java.util.ArrayList;
import java.util.List;


public class CultivationType {
    private final List<CultivationRealm> majorRealms;

    public CultivationType(List<CultivationRealm> majorRealms) {
        this.majorRealms = majorRealms;
    }

    public CultivationRealm getRealm(int index) {
        if (index >= 0 && index < majorRealms.size()) {
            return majorRealms.get(index);
        }
        return null;
    }

    public int getRealmCount() {
        return majorRealms.size();
    }

    public static class Builder {
        private final List<CultivationRealm> realms = new ArrayList<>();

        public Builder addRealm(CultivationRealm realm) {
            this.realms.add(realm);
            return this;
        }

        public CultivationType build() {
            return new CultivationType(realms);
        }
    }
}
