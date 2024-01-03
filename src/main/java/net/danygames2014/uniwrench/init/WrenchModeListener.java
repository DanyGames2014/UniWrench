package net.danygames2014.uniwrench.init;

import net.danygames2014.uniwrench.api.WrenchMode;
import net.danygames2014.uniwrench.api.event.WrenchModeRegistryEvent;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.modificationstation.stationapi.api.mod.entrypoint.Entrypoint;
import net.modificationstation.stationapi.api.util.Namespace;
import net.modificationstation.stationapi.api.util.Null;

public class WrenchModeListener {
    @Entrypoint.Namespace
    public static final Namespace MOD_ID = Null.get();

    @EventListener
    public void registerWrenchModes(WrenchModeRegistryEvent event){
        WrenchMode.MODE_WRENCH = new WrenchMode(MOD_ID.id("wrench"));
        WrenchMode.MODE_ROTATE = new WrenchMode(MOD_ID.id("rotate"));
    }
}
