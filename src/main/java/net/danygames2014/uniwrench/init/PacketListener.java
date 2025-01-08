package net.danygames2014.uniwrench.init;

import net.danygames2014.uniwrench.network.WrenchModeC2SPacket;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.modificationstation.stationapi.api.event.network.packet.PacketRegisterEvent;
import net.modificationstation.stationapi.api.mod.entrypoint.Entrypoint;
import net.modificationstation.stationapi.api.registry.PacketTypeRegistry;
import net.modificationstation.stationapi.api.registry.Registry;
import net.modificationstation.stationapi.api.util.Namespace;

@SuppressWarnings("unused")
public class PacketListener {
    @Entrypoint.Namespace
    public static Namespace NAMESPACE;
    
    @EventListener
    public void registerPacket(PacketRegisterEvent event) {
        Registry.register(PacketTypeRegistry.INSTANCE, NAMESPACE.id("wrench_mode"), WrenchModeC2SPacket.TYPE);
    }

}
