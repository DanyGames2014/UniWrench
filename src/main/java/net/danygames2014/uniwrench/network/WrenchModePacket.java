package net.danygames2014.uniwrench.network;

import net.danygames2014.uniwrench.init.ItemListener;
import net.danygames2014.uniwrench.item.WrenchBase;
import net.danygames2014.uniwrench.mixin.ClientPlayerAccessor;
import net.danygames2014.uniwrench.mixin.ServerPlayerAccessor;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.loader.FabricLoader;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.NetworkHandler;
import net.minecraft.network.packet.Packet;
import net.modificationstation.stationapi.api.network.packet.IdentifiablePacket;
import net.modificationstation.stationapi.api.network.packet.PacketHelper;
import net.modificationstation.stationapi.api.util.Identifier;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class WrenchModePacket extends Packet implements IdentifiablePacket {
    private static final Identifier identifier = ItemListener.MOD_ID.id("wrench_mode");
    private int wrenchMode;
    private int slot;

    public WrenchModePacket() {
    }

    public WrenchModePacket(int wrenchMode, int slot) {
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
        System.out.println("Packet Received = Wrench Mode : " + this.wrenchMode);

        switch (FabricLoader.INSTANCE.getEnvironmentType()){
            case CLIENT -> handleClient(networkHandler);
            case SERVER -> handleServer(networkHandler);
        }
    }

    @Environment(EnvType.CLIENT)
    public void handleClient(NetworkHandler networkHandler){
        ClientPlayerAccessor accessor = (ClientPlayerAccessor) networkHandler;
        Minecraft minecraft = accessor.getMinecraft();

        ItemStack stack  = minecraft.player.inventory.main[this.slot];
        if (stack.getItem() instanceof WrenchBase wrench) {
            wrench.setWrenchMode(stack, this.wrenchMode);
        }
    }

    @Environment(EnvType.SERVER)
    public void handleServer(NetworkHandler networkHandler){
        ServerPlayerAccessor accessor = (ServerPlayerAccessor) networkHandler;
        ServerPlayerEntity player = accessor.getServerPlayer();

        ItemStack stack  = player.inventory.main[this.slot];
        if (stack != null && stack.getItem() instanceof WrenchBase wrench) {
            // Wrench Mode -7000 = Request Update
            if (this.wrenchMode == -7000) {
                PacketHelper.sendTo(player, new WrenchModePacket(wrench.readMode(stack),this.slot));
            // Anything else = Set Mode on Server Side
            } else {
                wrench.setWrenchMode(stack, this.wrenchMode);
            }
        }
    }

    @Override
    public int size() {
        return 8;
    }

    @Override
    public Identifier getId() {
        return identifier;
    }

    public static void register() {
        IdentifiablePacket.register(identifier, true, true, WrenchModePacket::new);
    }
}
