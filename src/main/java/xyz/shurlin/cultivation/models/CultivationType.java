package xyz.shurlin.cultivation.models;

import xyz.shurlin.cultivation.interfaces.CultivationLogic;

import java.util.ArrayList;
import java.util.List;


public class CultivationType {
    private final List<CultivationRealm> majorRealms;
    private final CultivationLogic logic;

    public CultivationType(List<CultivationRealm> majorRealms, CultivationLogic logic) {
        this.majorRealms = majorRealms;
        this.logic = logic;
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

    public CultivationLogic getLogic() {
        return logic;
    }

    public static class Builder {
        private final List<CultivationRealm> realms = new ArrayList<>();
        private CultivationLogic logic;

        public Builder addRealm(CultivationRealm realm) {
            this.realms.add(realm);
            return this;
        }

        public Builder setLogic(CultivationLogic logic) {
            this.logic = logic;
            return this;
        }

        public CultivationType build() {
            if (logic == null) {
                throw new IllegalStateException("There are not logic set for this Cultivation Type!");
            }
            return new CultivationType(realms, logic);
        }
    }
}
