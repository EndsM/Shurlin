package xyz.shurlin.cultivation.gui;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.screen.ScreenHandler;
import xyz.shurlin.cultivation.interfaces.StorageAdapter;
import xyz.shurlin.cultivation.models.CultivationRealm;
import xyz.shurlin.cultivation.models.enums.CultivationType;
import xyz.shurlin.cultivation.models.enums.RealmStage;
import xyz.shurlin.registry.gui.ModScreenHandlerTypes;

import java.util.Map;

public class CultivationInfoScreenHandler extends ScreenHandler {
    private final StorageAdapter storageAdapter;
    private final CultivationType cultivationType;
  private   Map<Integer, CultivationRealm> cultivationStages;
   private int currentStage;
   private RealmStage realmStage;

    public CultivationInfoScreenHandler(int syncId, PlayerEntity player) {
        super(ModScreenHandlerTypes.CULTIVATION_INFO, syncId);
        // The mixin already put the MixinStorageAdapter into the class, so it can just find impl in there.
        storageAdapter = (StorageAdapter) player;
        cultivationType = storageAdapter.LoadCultivationType();
        cultivationStages = storageAdapter.LoadCultivationStages();
        currentStage = storageAdapter.LoadCurrentStage();
        realmStage = storageAdapter.LoadRealmStage();
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return true;
    }

    public CultivationType getCultivationType() {
        return cultivationType;
    }

    public int getCurrentStage() {
        return currentStage;
    }

    public Map<Integer, CultivationRealm> getCultivationStages() {
        return cultivationStages;
    }

    public RealmStage getRealmStage() {
        return realmStage;
    }
    public CultivationRealm getCurrentRealm(){
        if(currentStage>0){
            return  cultivationStages.get(currentStage);
        }
        CultivationRealm realm  = new CultivationRealm();
        realm.setNameKey("Not cultivated Yet");
        return realm;
    }
}
