package kr.entree.chprotocol.event;

import com.comphenix.protocol.events.PacketEvent;
import com.laytonsmith.abstraction.MCPlayer;
import com.laytonsmith.abstraction.bukkit.entities.BukkitMCPlayer;
import com.laytonsmith.core.constructs.Target;
import kr.entree.chprotocol.data.CPacket;
import kr.entree.chprotocol.data.PacketKind;
import kr.entree.chprotocol.protocollib.ProtocolLibraries;
import lombok.RequiredArgsConstructor;

/**
 * Created by JunHyung Im on 2020-07-05
 */
@RequiredArgsConstructor
public class ProtocolLibPacketEvent implements BindablePacketEvent {
    private final PacketEvent packetEvent;

    @Override
    public MCPlayer getPlayer() {
        return new BukkitMCPlayer(packetEvent.getPlayer());
    }

    @Override
    public PacketKind getKind() {
        return ProtocolLibraries.getPacketKind(packetEvent.getPacketType());
    }

    @Override
    public CPacket getPacket(Target target) {
        return CPacket.create(packetEvent.getPacket(), target);
    }

    @Override
    public Object getInternalPacket() {
        return packetEvent.getPacket();
    }

    @Override
    public Object _GetObject() {
        return packetEvent;
    }
}
