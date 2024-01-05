package net.danygames2014.uniwrench.init;

import net.danygames2014.uniwrench.network.WrenchModePacket;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.modificationstation.stationapi.api.event.network.packet.PacketRegisterEvent;
import net.modificationstation.stationapi.api.mod.entrypoint.Entrypoint;
import net.modificationstation.stationapi.api.util.Namespace;
import net.modificationstation.stationapi.api.util.Null;

public class PacketListener {
    @Entrypoint.Namespace
    public static final Namespace MOD_ID = Null.get();

    @EventListener
    public void registerPacket(PacketRegisterEvent event) {
        WrenchModePacket.register();
    }

}
