package net.danygames2014.uniwrench.init;

import net.danygames2014.uniwrench.network.WrenchModeC2SPacket;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.modificationstation.stationapi.api.event.network.packet.PacketRegisterEvent;
import net.modificationstation.stationapi.api.mod.entrypoint.Entrypoint;
import net.modificationstation.stationapi.api.util.Namespace;
import net.modificationstation.stationapi.api.util.Null;

@SuppressWarnings("unused")
public class PacketListener {
    @EventListener
    public void registerPacket(PacketRegisterEvent event) {
        WrenchModeC2SPacket.register();
    }

}
