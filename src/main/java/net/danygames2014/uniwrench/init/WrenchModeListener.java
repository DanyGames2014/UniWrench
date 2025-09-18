package net.danygames2014.uniwrench.init;

import net.danygames2014.uniwrench.api.WrenchMode;
import net.danygames2014.uniwrench.api.event.WrenchModeRegistryEvent;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.mine_diver.unsafeevents.listener.ListenerPriority;
import net.modificationstation.stationapi.api.mod.entrypoint.Entrypoint;
import net.modificationstation.stationapi.api.util.Namespace;
import net.modificationstation.stationapi.api.util.Null;

@SuppressWarnings("unused")
public class WrenchModeListener {
    @Entrypoint.Namespace
    public static Namespace NAMESPACE;

    @EventListener(priority = ListenerPriority.HIGHEST)
    public void registerWrenchModes(WrenchModeRegistryEvent event) {
        WrenchMode.MODE_WRENCH = new WrenchMode(NAMESPACE.id("wrench"));
        WrenchMode.MODE_ROTATE = new WrenchMode(NAMESPACE.id("rotate"));
        WrenchMode.MODE_DEBUG = new WrenchMode(NAMESPACE.id("debug"));
    }
}
