package net.danygames2014.uniwrench.network;

import net.danygames2014.uniwrench.UniWrench;
import net.danygames2014.uniwrench.init.ItemListener;
import net.danygames2014.uniwrench.item.WrenchBase;
import net.danygames2014.uniwrench.mixin.ServerPlayerAccessor;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.NetworkHandler;
import net.minecraft.network.packet.Packet;
import net.modificationstation.stationapi.api.network.packet.ManagedPacket;
import net.modificationstation.stationapi.api.network.packet.PacketType;
import net.modificationstation.stationapi.api.util.Identifier;
import org.apache.commons.io.filefilter.IOFileFilter;
import org.jetbrains.annotations.NotNull;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class WrenchModeC2SPacket extends Packet implements ManagedPacket<WrenchModeC2SPacket> {
    private static final Identifier identifier = ItemListener.NAMESPACE.id("wrench_mode");
    public static PacketType<WrenchModeC2SPacket> TYPE = PacketType.<WrenchModeC2SPacket>builder(false, true, WrenchModeC2SPacket::new).build();
    
    private int wrenchMode;
    private int slot;

    public WrenchModeC2SPacket() {
    }

    public WrenchModeC2SPacket(int wrenchMode, int slot) {
        this.wrenchMode = wrenchMode;
        this.slot = slot;
    }

    @Override
    public void read(DataInputStream stream) {
        try {
            this.wrenchMode = stream.readInt();
            this.slot = stream.readInt();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void write(DataOutputStream stream) {
        try {
            stream.writeInt(this.wrenchMode);
            stream.writeInt(this.slot);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void apply(NetworkHandler networkHandler) {
        UniWrench.LOGGER.debug("Wrench Mode Packet Received with mode {} ", this.wrenchMode);

        handleServer(networkHandler);
    }

    @Environment(EnvType.SERVER)
    public void handleServer(NetworkHandler networkHandler) {
        ServerPlayerAccessor accessor = (ServerPlayerAccessor) networkHandler;
        ServerPlayerEntity player = accessor.getServerPlayer();

        ItemStack stack = player.inventory.main[this.slot];
        if (stack == null) {
            return;
        }
        
        if (stack.getItem() instanceof WrenchBase wrench) {
            if (wrench.isModeSwitchLocked(stack)) {
                UniWrench.LOGGER.warn("Player {} sent a packet to change a wrench mode when wrench was locked", player.name);
                return;
            }
            
            wrench.setWrenchMode(stack, this.wrenchMode);
        }
    }

    @Override
    public int size() {
        return 8;
    }

    @Override
    public @NotNull PacketType<WrenchModeC2SPacket> getType() {
        return TYPE;
    }
}
