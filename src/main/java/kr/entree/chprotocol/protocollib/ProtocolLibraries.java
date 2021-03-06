package kr.entree.chprotocol.protocollib;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.PacketContainer;
import com.laytonsmith.core.constructs.Target;
import com.laytonsmith.core.exceptions.CRE.CREIllegalArgumentException;
import kr.entree.chprotocol.data.PacketKind;
import lombok.experimental.UtilityClass;
import lombok.val;

/**
 * Created by JunHyung Im on 2020-07-05
 */
@UtilityClass
public class ProtocolLibraries {
    public PacketType.Sender getSide(String name, Target target) {
        switch (name) {
            case "IN":
                return PacketType.Sender.CLIENT;
            case "OUT":
                return PacketType.Sender.SERVER;
        }
        throw new CREIllegalArgumentException("Unknown sender type: " + name, target);
    }

    public PacketType findPacketType(String protocol, String side, String name, Target target) {
        val packetSide = getSide(side, target);
        try {
             return PacketType.findCurrent(PacketType.Protocol.valueOf(protocol), packetSide, name);
        } catch (Exception exception) {
            throw new CREIllegalArgumentException("Error while finding the packet.", target, exception);
        }
    }

    public PacketContainer createPacket(String protocol, String side, String name, Target target) {
        return ProtocolLibrary.getProtocolManager().createPacket(findPacketType(protocol, side, name, target));
    }

    public PacketKind getPacketKind(PacketType type) {
        return new PacketKind(
                type.getProtocol().name(),
                type.getSender().getPacketName().toUpperCase(),
                type.name().toUpperCase()
        );
    }
}
