package net.danygames2014.uniwrench.init;

import net.danygames2014.uniwrench.UniWrench;
import net.danygames2014.uniwrench.api.event.WrenchModeRegistryEvent;
import net.danygames2014.uniwrench.api.event.WrenchableBlockRegisterEvent;
import net.fabricmc.loader.api.FabricLoader;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.mine_diver.unsafeevents.listener.ListenerPriority;
import net.modificationstation.stationapi.api.StationAPI;
import net.modificationstation.stationapi.api.event.mod.InitEvent;
import net.modificationstation.stationapi.api.event.registry.AfterBlockAndItemRegisterEvent;
import net.modificationstation.stationapi.api.mod.entrypoint.EntrypointManager;

@SuppressWarnings("unused")
public class InitListener {
    @EventListener(priority = ListenerPriority.HIGHEST, phase = InitEvent.PRE_INIT_PHASE)
    public void initEventTest(InitEvent event) {
        FabricLoader.getInstance().getEntrypointContainers("uniwrench:event_bus", Object.class).forEach(EntrypointManager::setup);
        StationAPI.EVENT_BUS.post(new WrenchModeRegistryEvent());
        UniWrench.LOGGER.info("Posting Wrench Mode Registry Event");
    }
    
    @EventListener
    public void afterBlockAndItem(AfterBlockAndItemRegisterEvent event){
        StationAPI.EVENT_BUS.post(new WrenchableBlockRegisterEvent());
    }
}
