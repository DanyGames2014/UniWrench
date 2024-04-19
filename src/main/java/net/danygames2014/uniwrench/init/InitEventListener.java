package net.danygames2014.uniwrench.init;

import net.danygames2014.uniwrench.api.event.WrenchModeRegistryEvent;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.mine_diver.unsafeevents.listener.ListenerPriority;
import net.modificationstation.stationapi.api.StationAPI;
import net.modificationstation.stationapi.api.event.mod.InitEvent;
import net.modificationstation.stationapi.api.mod.entrypoint.Entrypoint;
import net.modificationstation.stationapi.api.util.Null;
import org.apache.logging.log4j.Logger;

@SuppressWarnings("unused")
public class InitEventListener {
    @Entrypoint.Logger
    public static final Logger logger = Null.get();

    @EventListener(priority = ListenerPriority.HIGHEST, phase = InitEvent.PRE_INIT_PHASE)
    public void initEventTest(InitEvent event) {
        StationAPI.EVENT_BUS.post(new WrenchModeRegistryEvent());
        logger.info("Posting Wrench Mode Registry Event");
    }
}
